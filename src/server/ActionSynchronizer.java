package server;

import java.util.*;
import java.util.concurrent.*;

import common.*;

public class ActionSynchronizer {
  private static final long SYNC_DELAY = 5000L;
  
  public void start() {
    Server.EXECUTOR.scheduleAtFixedRate(new Task(), SYNC_DELAY, SYNC_DELAY, TimeUnit.MILLISECONDS);
  }
  
  private class Task implements Runnable {
    
    @Override
    public void run() {
      List<Action> restfulServiceActionList = Server.RESTFUL_CLIENT.getActionList();
      List<Action> serverActionList = Server.DATABASE.getActionList();
      
      for (Action action : serverActionList) {
        if (!restfulServiceActionList.contains(action)) {
            Server.RESTFUL_CLIENT.createAction(action);
        }
      }
      
      for (Action action : restfulServiceActionList) {
        if (!serverActionList.contains(action)) {
            Server.DATABASE.addAction(action);
        }
      }
    }
  }
}
