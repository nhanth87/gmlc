package org.mobicents.gmlc.slee.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.logging.Logger;

public class MlpResponseExecutor implements Runnable {

    // private final SuplTransaction suplTransaction = SuplTransaction.Instance();
    private final ScheduledThreadPoolExecutor exec;
    private final String  slpId;

    private static final Logger logger = Logger.getLogger(MlpResponseExecutor.class.getName());

    public MlpResponseExecutor(ScheduledThreadPoolExecutor exec, String slpId) {
        this.exec = exec;
        this.slpId = slpId;
    }

    @Override
    public void run() {
        // int statusSession = (int) suplTransaction.getValue(slpId,"sessionStatus");
        // if (statusSession == SuplSessionStatus.SUPL_SESSION_END.getSuplSessionStatus()) {
        //     logger.info("The SUPL session is finished");
        //     exec.shutdown();
        // }
    }
}
