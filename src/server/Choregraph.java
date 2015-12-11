package server;

import com.sun.xml.bind.v2.TODO;
import common.Message;

import java.util.ArrayList;


/**
 * Created by Sylvain on 2015-12-05.
 */
public class Choregraph implements RequestHandler {
    @Override
    public Message reply(Message request) {
        Message reponse = new Message();
        TransactionTOU Tou = new TransactionTOU();
        switch (request.getSubject()){
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


        }

        return  null;
    }



}
