package server;

import java.io.*;
import java.net.*;

import common.*;

public class TcpServer implements Runnable {
  private final RequestHandlerFactory requestHandlerFactory;
  private final ServerSocket serverSocket;
  
  public TcpServer(RequestHandlerFactory requestHandlerFactory) throws IOException {
    this.requestHandlerFactory = requestHandlerFactory;
    serverSocket = new ServerSocket(Common.SERVER_PORT);
  }
  
  public void run() {
    while (true) {
      try {
        final Socket connection = serverSocket.accept();
        
        Runnable task = new Runnable() {
          public void run() {
            handleConnection(connection);
          }
        };
        Server.EXECUTOR.execute(task);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  private void handleConnection(Socket connection) {
    try {
      ObjectOutput objectOut = new ObjectOutputStream(connection.getOutputStream());
      objectOut.flush();
      ObjectInput objectIn = new ObjectInputStream(connection.getInputStream());
      Message request = (Message) objectIn.readObject();
      
      Message reply = requestHandlerFactory.newRequestHandler().reply(request);
      objectOut.writeObject(reply);
      objectOut.flush();
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
