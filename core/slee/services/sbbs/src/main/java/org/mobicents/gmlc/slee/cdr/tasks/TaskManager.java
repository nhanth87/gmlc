package org.mobicents.gmlc.slee.cdr.tasks;

import org.mobicents.gmlc.GmlcPropertiesManagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TaskManager {

    private static final GmlcPropertiesManagement gmlcPropertiesManagement = GmlcPropertiesManagement.getInstance();
    private boolean isStart = false;

    ExecutorService executor;

    public void start() {
        if (!isStart){
            isStart = true;
            executor = Executors.newFixedThreadPool(gmlcPropertiesManagement.getGlassThreadPoll());
        }

    }

    public void stop() {
        isStart = false;
        executor.shutdown();
    }

    public void addTask(TaskCDR taskCDR) {
        executor.execute(taskCDR);
    }


}
