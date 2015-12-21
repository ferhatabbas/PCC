package server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server {
  private static final int NB_THREADS = 100;
  public static final ScheduledExecutorService EXECUTOR = Executors.newScheduledThreadPool(NB_THREADS);
  public static final Database DATABASE = new Database();
  public static final MessageService MESSAGE_SERVICE = new MessageService(DATABASE.getUserIds());
  public static final RestfulClient RESTFUL_CLIENT = new RestfulClient();
  
  public static void main(String[] args) throws Exception {
    EXECUTOR.execute(new TcpServer(new ChoregraphFactory()));
    new ActionSynchronizer().start();
    System.out.println(new SimpleDateFormat("dd-MM-yyyy hh:mm").format(new Date()));
  }
  
  private static void testPeriodicTask() {
    long delay = 1000L;
    Runnable task = new Runnable() {
      public void run() {
        System.out.println("hello, world each " + delay / 1000L + "s");
      }
    };
    EXECUTOR.scheduleAtFixedRate(task, delay, delay, TimeUnit.MILLISECONDS);
  }

  private static void testRestfulClient() {
    Runnable task = new Runnable() {
      public void run() {
        RESTFUL_CLIENT.test();
      }
    };
    EXECUTOR.execute(task);
  }
}
