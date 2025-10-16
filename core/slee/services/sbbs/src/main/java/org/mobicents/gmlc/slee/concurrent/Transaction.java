package org.mobicents.gmlc.slee.concurrent;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.locks.StampedLock;
//import java.util.HashMap;

/**
 * @author <a href="mailto:aferreiraguido@gmail.com"> Alejandro Ferreira Guido </a>
 * @author <a href="mailto:fernando.mendioroz@gmail.com"> Fernando Mendioroz </a>
 */
public class Transaction {

  protected Logger logger = Logger.getLogger(Transaction.class);
  ConcurrentHashMap<Long, DataElement> transactionDataElements;
  ConcurrentHashMap<String, Long> sessionTransaction;
  //HashMap<Long, DataElement> transactionDataElements;
  //StampedLock lock = new StampedLock();

  final Integer availableDialogs = 65536;
  Long[] dialogTransactions = new Long[availableDialogs];

  Long globalTransactionIdentity = -1L;

  static Transaction s_instance = null;
  public static Transaction Instance() {
    if (s_instance == null)
      s_instance = new Transaction();
    return s_instance;
  }

  private Transaction() {
    transactionDataElements = new ConcurrentHashMap<>();
    sessionTransaction = new ConcurrentHashMap<>();
    for(int i = 0; i < availableDialogs; i++) {
      dialogTransactions[i] = null;
    }
  }


  /*
   *  TRANSACTIONS
   */

  public synchronized Long create() {
    Long transaction;
    transaction = ++globalTransactionIdentity;
    if (logger.isDebugEnabled()) {
      logger.warn("\nCreated transaction with Id = " + transaction + "\nglobalTransactionIdentity = " + globalTransactionIdentity);
    }
    return transaction;
  }

  public void destroy(Long transaction) {
    //long stamp = lock.writeLock();
    try {
      if (transaction != null) {
        for (Long dialog: getMappedDialogs(transaction)) {
          dialogTransactions[dialog.intValue()] = null;
          if (logger.isDebugEnabled())
            logger.warn("\nDestroyed transaction " + transaction + " for dialog = " + dialog);
        }
        for (String sessionId: getMappedSessions(transaction)) {
          if (sessionTransaction != null)
            sessionTransaction.remove(sessionId);
          if (logger.isDebugEnabled()) {
            logger.warn("\nDestroyed transaction " + transaction + " for sessionId = " + sessionId);
            logger.warn("\nCurrent sessionTransaction size=" + sessionTransaction.size());
          }
          if (sessionTransaction.size() > 100)
            logger.warn("\nCurrent sessionTransaction ConcurrentHashMap size = " + sessionTransaction.size());
        }
        if (transactionDataElements != null) {
          transactionDataElements.remove(transaction);
          if (logger.isDebugEnabled()) {
            logger.warn("\nDestroyed transaction data element for transaction = " + transaction);
            logger.warn("\nCurrent transactionDataElements ConcurrentHashMap size = " + transactionDataElements.size());
          }
          if (transactionDataElements.size() > 100)
            logger.warn("\nCurrent transactionDataElements size = " + transactionDataElements.size());
        }
      }
    } finally {
      //lock.unlockWrite(stamp);
    }
  }


  /*
   *  DATA ELEMENTS
   */

  public Object getValue(Long transaction, String key) {
    //long stamp = lock.readLock();   // lock.tryOptimisticRead();
    Object value = null;
    try {
      if (transactionDataElements != null) {
        DataElement dataElement;
        if (transaction != null && key != null) {
          dataElement = transactionDataElements.get(transaction);
          value = dataElement.get(key);
        }
      }
    } finally {
      //lock.unlockRead(stamp);
    }
    return value;
  }

  public void setValue(Long transaction, String key, Object value) {
    //long stamp = lock.writeLock();
    try {
      DataElement dataElement = transactionDataElements.get(transaction);
      if (dataElement == null) {
        dataElement = new DataElement();
        dataElement.set(key, value);
        transactionDataElements.put(transaction, dataElement);
      } else {
        dataElement.set(key, value);
        transactionDataElements.replace(transaction, dataElement);
      }
    } finally {
      //lock.unlockWrite(stamp);
    }
  }


  /*
   *  DIALOGS
   */

  public List<Long> getMappedDialogs(Long transaction) {
    ArrayList<Long> dialogs = new ArrayList<>();
    for (int dialog = 0; dialog < availableDialogs; dialog++) {
      if (Objects.equals(dialogTransactions[dialog], transaction))
        dialogs.add((long) dialog);
    }
    return dialogs;
  }

  public Long getTransactionId(Long dialog) {
    Long transaction = null;
    if (dialog >= 0 && dialog < availableDialogs)
      transaction = dialogTransactions[dialog.intValue()];
    return transaction;
  }

  public void setDialog(Long transaction, Long dialog) {
    if (dialog >= 0 && dialog < availableDialogs)
      dialogTransactions[dialog.intValue()] = transaction;
  }

  public void unsetDialog(Long dialog) {
    if (dialog >= 0 && dialog < availableDialogs)
      dialogTransactions[dialog.intValue()] = null;
  }

  /*
   *  SESSIONS
   */

  public List<String> getMappedSessions(Long transaction) {
    ArrayList<String> sessions = new ArrayList<>();
    if (sessionTransaction != null) {
      if (transaction != null) {
        if (sessionTransaction.containsValue(transaction)) {
          Iterator iterator = sessionTransaction.entrySet().iterator();
          while (iterator.hasNext()) {
            ConcurrentHashMap.Entry concurrentHasMapEntry = (ConcurrentHashMap.Entry) iterator.next();
            if (concurrentHasMapEntry.getValue().equals(transaction))
              sessions.add((String) concurrentHasMapEntry.getKey());
          }
        }
      }
    }
    return sessions;
  }

  public Long getTransactionId(String sessionId) {
    return sessionTransaction.get(sessionId);
  }

  public void setSession(Long transaction, String sessionId) {
    if (sessionTransaction.containsValue(sessionId))
      sessionTransaction.replace(sessionId, transaction);
    else
      sessionTransaction.put(sessionId, transaction);
  }

  public void unsetSession(String sessionId) {
    sessionTransaction.remove(sessionId);
  }
}
