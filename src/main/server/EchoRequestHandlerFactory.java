package main.server;

public class EchoRequestHandlerFactory implements RequestHandlerFactory {
  
  public RequestHandler newRequestHandler() {
    return new EchoRequestHandler();
  }
}
