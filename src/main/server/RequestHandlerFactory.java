package main.server;

public interface RequestHandlerFactory {
  
  public RequestHandler newRequestHandler();
}
