package server;

import common.Message;
import common.User;

import java.util.ArrayList;

/**
 * Created by Sylvain on 2015-12-05.
 */
public class Choregraph implements RequestHandler {
    @Override
    public Message reply(Message request) {
        Message reponse = new Message();

        switch (request.getSubject()){
            case SYNC:
                return Server.MESSAGE_SERVICE.receive((String) request.getBody());

            case P2P:
                Server.MESSAGE_SERVICE.send(request.getTo(), request);
                return new Message();

            case CONNECT:
                reponse.setSubject(Message.Subject.CONNECT);

                ArrayList<String> requeteBody = (ArrayList<String>) request.getBody();

                try {
                    boolean success = Server.DATABASE.connection(requeteBody.get(0), requeteBody.get(1));
                    if(success){
                        //Retourner instance du user
                        reponse.setBody(Server.DATABASE.recupererUser(requeteBody.get(0)));
                    }
                    return  reponse;
                }
                catch  (Exception e){
                    System.out.println("Connection impossible");
                }

            case ACTIONS:
                reponse.setSubject(Message.Subject.ACTIONS);
                reponse.setBody(Server.DATABASE.getActions());
                return reponse;

            //case CREATE:


        }

        return  null;
    }


}
