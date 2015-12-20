package server;

import com.sun.xml.bind.v2.TODO;
import common.Couple;
import common.Message;
import common.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sylvain on 2015-12-05.
 */
public class Choregraph implements RequestHandler {
    @Override
    public Message reply(Message request) {
        Message reponse = new Message();

        ArrayList<String> requeteBody;
        switch (request.getSubject()){
            case SYNC:
                System.out.println("demmande de sync" + " from " +  request.getBody());
                return Server.MESSAGE_SERVICE.receive((String) request.getBody());

            case P2P:
                System.out.println("demande de P2p" + " from " + request.getFrom() + " to " + request.getTo());
                System.out.println((ArrayList) request.getBody());

                Server.MESSAGE_SERVICE.send(request.getTo(), request);

                return new Message();

            case CONNECT:
                System.out.println("Connection debut");
                reponse.setSubject(Message.Subject.CONNECT);

                requeteBody = (ArrayList<String>) request.getBody();
                try {
                    boolean success = Server.DATABASE.connection(requeteBody.get(0), requeteBody.get(1));
                    if(success){
                        //Retourner instance du couple
                        User user = Server.DATABASE.recupererUser(requeteBody.get(0));
                        reponse.setBody(Server.DATABASE.recupererCouple(user));
                    }
                    else{
                        reponse.setBody(null);
                    }

                    return  reponse;
                }
                catch  (Exception e){
                    System.out.println("Connection impossible");
                }
                break;
            case ACTIONS:
                reponse.setSubject(Message.Subject.ACTIONS);
                reponse.setBody(Server.DATABASE.getActionDescList());
                System.out.println(Server.DATABASE.getActionDescList());
                return reponse;
            //break;

            case ADD_AR:
                System.out.println("debut add_ar");
                reponse.setSubject(Message.Subject.ADD_AR);
                requeteBody = (ArrayList<String>) request.getBody();
                try {
                    Server.DATABASE.addActionReal(requeteBody.get(0), requeteBody.get(1), Server.DATABASE.recupererIdAction(requeteBody.get(2)));
                    return reponse;
                }
                catch  (Exception e){
                    System.out.println("Connection impossible");
                }
                break;

            case HISTORIQUE:
                requeteBody = (ArrayList<String>) request.getBody();
                reponse.setSubject(Message.Subject.HISTORIQUE);
                reponse.setBody(Server.DATABASE.getHistorique(requeteBody.get(0),requeteBody.get(1)));
               // System.out.println(Server.DATABASE.getHistorique((Couple) request.getBody(), null, null));
                return reponse;

            case TOU_REQUEST:
                System.out.println("demande de TOU REQUEST" + " from " + request.getFrom() + " to " + request.getTo());

                Server.MESSAGE_SERVICE.send(request.getTo(), request);

                return new Message(Message.Subject.TOU_ACK);

            case TOU_POSITION:
                System.out.println("demande de TOU POSITION" + " from " + request.getFrom() + " to " + request.getTo());
                System.out.println("position" + request.getBody());
                Server.MESSAGE_SERVICE.send(request.getTo(), request);

                return new Message(Message.Subject.TOU_ACK);
            case TOU_REFUSE:
                System.out.println("demande de TOU REFUSE" + " from " + request.getFrom() + " to " + request.getTo());

                Server.MESSAGE_SERVICE.send(request.getTo(), request);

                return new Message(Message.Subject.TOU_ACK);

        }

        return  null;
    }



}
