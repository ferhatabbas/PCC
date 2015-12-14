package common;

import util.Utilitaires;

import java.util.Date;
import java.util.*;

/**
 * Created by ferhat on 2015-11-19.
 */
public class Historique  {
    private List<ActionReal> actionsReal;
    private static Historique instance=null;

    public Historique(){
        actionsReal=new ArrayList<ActionReal>();
    }

    public static Historique getIsntance(){
        if(instance == null){
            instance = new Historique();
        }
        return instance;
    }
    public void setAction(ActionReal act){
        act.setStatus(ActionReal.Status.ATTENTE);
        actionsReal.add(act);
    }
    public ActionReal recupAction(Date date){

        for(ActionReal obj :actionsReal) {
            if (obj.getDate().equals(date)) {
                return obj;
            }
        }
        return null;
    }
    public List<ActionReal> getActionsReal(){return actionsReal;}

}
