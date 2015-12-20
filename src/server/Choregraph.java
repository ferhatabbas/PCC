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
        TransactionTOU Tou = new TransactionTOU();
        ArrayList<String> requeteBody;
        switch (request.getSubject()){
            case SYNC:
                System.out.println("demmande de sync");
                return Server.MESSAGE_SERVICE.receive((String) request.getBody());

            case P2P:
                System.out.println("demande de P2p" + " from " + request.getFrom() + " to " + request.getTo());

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
            case TOU_REQUEST:
               int step = (int) request.getBody();
                return Tou.run(step);

            case TOU_HEY:
                if(Tou.hey()) //il y a une nouveaute
                return Tou.run();
                else
                    return new Message(Message.Subject.TOU_NOTHING);

            case TOU_MYPOSITION:
                ArrayList latlng = (ArrayList) request.getBody();
              // TODO Sauvegarder ca et le mettre dans le couple ? User ? il faut que le partenaire puisse recuperer cette position
                return Tou.run();
            case HISTORIQUE:
                reponse.setSubject(Message.Subject.HISTORIQUE);
                reponse.setBody(Server.DATABASE.getHistorique((Couple)request.getBody(),null,null));
                System.out.println(Server.DATABASE.getHistorique((Couple) request.getBody(), null, null));
                return reponse;
        }

        return  null;
    }



}
