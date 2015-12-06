package main.server;

import main.common.*;

public class EchoRequestHandler implements RequestHandler {
  
  public Message reply(Message request) {
    return request;
  }
}
