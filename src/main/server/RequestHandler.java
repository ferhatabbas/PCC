package main.server;

import main.common.*;

////////// SYLVAIN, TU DOIS CODER LA LOGIQUE DU SERVEUR DANS UNE //////////
////////// CLASSE QUI IMPLEMENTE CETTE INTERFACE                 //////////
public interface RequestHandler {
  
  public Message reply(Message request);

}
