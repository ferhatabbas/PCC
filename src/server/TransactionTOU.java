package server;

import common.Message;

import java.util.ArrayList;

/**
 * Created by Sylvain on 2015-12-10.
 */
public class TransactionTOU {

    private int step;


    public TransactionTOU(){
        this.step=0;
    }

    public Message run(int newstep){
    if(step==newstep)
        step=newstep;
        switch (newstep){
            case 0: //rien de nouveau
               return new Message(Message.Subject.TOU_NOTHING);

            case 1: // demande TOU
                step=2;
                return new Message(Message.Subject.TOU_ACK);

                  }
        return null;
    }

    public Message run(){
        switch (step){
            case 0: //rien de nouveau
                System.out.println("ERREUR");
                break;

            case 1: // demande TOU
                step=2;
                return new Message(Message.Subject.TOU_ACK);

            case 2: // attente confirmation
                step=3;
                return new Message(Message.Subject.TOU_REQUEST);

            case 3: // attente reception requete client 2
                step=4;
                return new Message(Message.Subject.TOU_ACK);

            case 4: // recup position
                step =5;
                return new Message(Message.Subject.TOU_ACK);

            case 5: // il vient chercher la position
                step=6;
                ArrayList latlng = new ArrayList(); //todo recuperer la position et la mettre la dedans pour la retourner
                return  new Message(Message.Subject.TOU_POSITION,latlng );

            case 6: // fin
                step=0;
                return new Message(Message.Subject.TOU_END);

        }
        return null;
    }

        public boolean hey(){
        if(step!=0)
            return true;
        else
            return false;
    }
}



