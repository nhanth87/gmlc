package org.mobicents.gmlc.slee.smpp;

import com.cloudhopper.commons.util.ByteBuffer;
import com.cloudhopper.commons.util.windowing.WindowFuture;
import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.pdu.DataSm;
import com.cloudhopper.smpp.pdu.DataSmResp;
import com.cloudhopper.smpp.pdu.PduRequest;
import com.cloudhopper.smpp.pdu.PduResponse;
import com.cloudhopper.smpp.tlv.Tlv;
import com.cloudhopper.smpp.type.Address;
import org.mobicents.gmlc.slee.utils.ByteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class SmppSender {
    private static final Logger logger = LoggerFactory.getLogger(SmppSender.class);
    private static int msgIdPool = 0;

    private static int getNextMsgId() {
        msgIdPool++;
        if (msgIdPool > 255)
            msgIdPool = 1;
        return msgIdPool;
    }
    private static ArrayList<String> splitStr(String buf, int maxLen) {
        ArrayList<String> res = new ArrayList<>();
        String prevBuf = buf;

        while (true) {
            if (prevBuf.length() <= maxLen) {
                res.add(prevBuf);
                break;
            }

            String segm = prevBuf.substring(0, maxLen);
            String newBuf = prevBuf.substring(maxLen, prevBuf.length());
            res.add(segm);
            prevBuf = newBuf;
        }
        return res;
    }
    public static void sendMt(String dst, byte [] suplInit) {
        logger.info("sending SubmitSm ");
        try {
            List<byte[]> listMessages = new ArrayList<>();
            int msgId = getNextMsgId();
            ByteBuffer byteBuffer = new ByteBuffer(suplInit.length + 5);
            byteBuffer.add((byte) 0x06);
            byteBuffer.add((byte) 0x05); // IE Identifier
            byteBuffer.add((byte) 0x04); // IE Data Length
            byteBuffer.add((byte) 0x1C6B); // Destination Port
            byteBuffer.add((byte) 0x23F0); // Originator Port
            byteBuffer.add(suplInit);

            byte[] wapPushBody = byteBuffer.toArray();
            String hexWapPushBody = ByteUtils.bytesToHex(wapPushBody);
            // SMS max legth is 140, so the Wap Push should be split up.
            if (hexWapPushBody.length() > 140) {
                // Let's split the wap push with max length 132
                // because we need to add UDH before the WAP Push fragment
                List<String> splitWapPushList = splitStr(hexWapPushBody, 132);
                byte[] udhBytes = new byte[6];
                udhBytes[0] = 5; // total UDH length
                udhBytes[1] = 0; // UDH id
                udhBytes[2] = 3; // UDH length
                udhBytes[3] = (byte) msgId; // refNum
                udhBytes[4] = (byte) splitWapPushList.size(); // segmCnt
                int smSequenceCounter = 0;
                for (String smsSplit : splitWapPushList) {
                    byte[] smsSplitOnBytes = ByteUtils.decodeHexString(smsSplit);
                    smSequenceCounter++;
                    udhBytes[5] = (byte) smSequenceCounter;
                    byte[] finalSmOnBytes = new byte[udhBytes.length + smsSplitOnBytes.length];
                    System.arraycopy(udhBytes, 0, finalSmOnBytes, 0, udhBytes.length);
                    System.arraycopy(smsSplitOnBytes, 0, finalSmOnBytes, udhBytes.length, smsSplitOnBytes.length);
                    listMessages.add(finalSmOnBytes);
                }
            }

            for (byte[] smBytes : listMessages) {
                DataSm dataSm = new DataSm();
                Address source = new Address((byte) 0x00, (byte) 0x00, "98765432");
                Address destination = new Address((byte) 0x01, (byte) 0x01, dst);

                dataSm.setSourceAddress(source);
                dataSm.setDestAddress(destination);
                dataSm.setProtocolId((byte) 0x00);
                dataSm.setPriority((byte) 0x00);
                dataSm.setReplaceIfPresent((byte) 0x00);
                dataSm.setDataCoding((byte) 0xF5);
                dataSm.setDefaultMsgId((byte) 0x00);
                dataSm.setEsmClass((byte) 0x40);
                dataSm.setValidityPeriod("000000001000000R");
                dataSm.setRegisteredDelivery((byte) 0x01);
                dataSm.setServiceType("WAP");
                dataSm.setShortMessage(null);

                Tlv tlv = new Tlv(SmppConstants.TAG_MESSAGE_PAYLOAD, smBytes);
                dataSm.addOptionalParameter(tlv);

                WindowFuture<Integer, PduRequest, PduResponse> future = SmppSessionManager.getInstance().sendRequestPdu(dataSm, 10000, false);
                if (!future.await()) {
                    logger.error("Failed to receive deliver_sm_resp within specified time");
                } else if (future.isSuccess()) {
                    DataSmResp dataSmResp = (DataSmResp) future.getResponse();
                    logger.info("SubmitSmResp: commandStatus [" + dataSmResp.getCommandStatus() + "=" + dataSmResp.getResultMessage() + "]");
                } else {
                    logger.error("Failed to properly receive deliver_sm_resp: " + future.getCause());
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
