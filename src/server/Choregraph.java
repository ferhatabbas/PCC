package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

            case CREATE:


        }

        return  null;
    }


}
