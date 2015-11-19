package main.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ferhat on 2015-11-12.
 */
public class ActionReal {
    private Action action;
    private Date date;
    private String Commentaire;
    private Status status;
    private Response reponse;
    public enum Status { ATTENTE,VALIDER,REFUSER,RIEN }
    public enum Response { YES, NO ,RIEN }

    public ActionReal(Action action,String Commentaire,Status status,Response resp){

        this.action=action;
        this.date=new Date();
        this.Commentaire=Commentaire;
        status=Status.ATTENTE;
        resp=Response.RIEN;
    }
/*

    public void AddAction(Action action,Date date){
        Actions_Date.put(action,date);
    }
    public void AddCommentaire(Action action, String commentaire){
        Actions_commentaire.put(action,commentaire);
    }
*/


}

