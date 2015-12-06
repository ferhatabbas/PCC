package main.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.common.Message;
import main.common.User;

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
                        //recuperer instance du user

                        //Retourner instance du user

                        reponse.setBody();
                    }
                    return  reponse;
                }
                catch  (Exception e){
                    System.out.println("Connection impossible");
                }

            case ACTIONS:
                Server.DATABASE.
                return null;

            case CREATE:


        }

        return  null;
    }


}
