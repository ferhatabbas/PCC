package main.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ferhat on 2015-11-12.
 */
public class ActionReal {
    private HashMap<Action,Date> Actions_Date;
    private HashMap<Action,String> Actions_commentaire;

    public ActionReal(){
        Actions_Date=new HashMap<>();
        Actions_commentaire= new HashMap<>();

    }

    public void AddAction(Action action,Date date){
        Actions_Date.put(action,date);
    }
    public void AddCommentaire(Action action, String commentaire){
        Actions_commentaire.put(action,commentaire);
    }


}

