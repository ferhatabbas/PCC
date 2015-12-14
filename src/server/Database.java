package server;
import jdk.nashorn.internal.runtime.ListAdapter;
import common.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static util.Utilitaires.encrypte;


/**
 * Created by User on 2015-11-10.
 */
public class Database {
    private List<User> userlist;
    private List<Couple> couplelist;
    private List<Action> actions;
    private Map<Couple,Historique> historique;
    //private List<ActionReal> actionReal;

    public Database() {
        userlist=new ArrayList<User>();
        couplelist=new ArrayList<Couple>();
        actions=new ArrayList<Action>();
        historique= new HashMap<Couple,Historique>();
        // just pour faire des test
        addUserDumby();
        setTestData();


    }
    public void addUserDumby() {
        User user1 = new User("fafa", "fafa", null, null, "fafa");
        User user2 = new User("fifi", "fifi", null, null, "fifi");
        User user3 = new User("toto", "toto", null, null, "toto");
        User user4 = new User("lolo", "lolo", null, null, "lolo");
        this.userlist.add(user1);
        this.userlist.add(user2);
        this.userlist.add(user3);
        this.userlist.add(user4);
        this.couplelist.add(new Couple(user1,user2));
        this.couplelist.add(new Couple(user3,user4));
    }
    public boolean connection(String ID,String Pass) {
        boolean result = false;
        String pass= null;
        try {
            pass = encrypte(Pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User obj : userlist) {
            if ((obj.getId().hashCode() == ID.hashCode())) {
                if (obj.getPassword().hashCode()==pass.hashCode()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }


    public List<Action> getActions(){
        return this.actions;
    }


    public void addActionReal(String sender, String receiver, String idaction){
        User senderUser= recupererUser(sender);
        User receiverUser= recupererUser(receiver);
        int posAction= recupererActionid(idaction);
        Couple couple= recupererCouple(senderUser);
        int pos=0;
        // cree l'action a realiser
        ActionReal act = new ActionReal(actions.get(posAction),senderUser,receiverUser);
        // trouver le couple des deux users puis leur associer l'action
        if(historique.containsKey(couple)){
            historique.get(couple).setAction(act);
        }
        else{
            historique.put(couple,new Historique(act));
        }
    }



    public List<ActionReal> getHistorique(Couple couple, Date from, Date to) {
        // creation du calendrier pour l'incrementation de la date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date i= null;
        try {
            i = format.parse(format.format(from));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();


        List<ActionReal> act = new ArrayList<ActionReal>();
        // creation de la liste des Actions associer a l'intervale de Date fournis en parametre
        while ( i!=to){
            act.add(historique.get(couple).recupAction(i));
            cal.setTime(i);
            cal.add(Calendar.DATE, 1);
            i=cal.getTime();
        }
        return act;
    }
    // ceci est la premiere version du Update je doit changer ma classe historique pour simplifier et factoriser mon code

    public void UpdateAR(Couple couple,String idAction,String comment, ActionReal.Status stat ){ // j'ai ajouter comme parametre couple pour pouvoir retrouver l<action realiser dans l'historique et la update
        List<ActionReal> act =historique.get(couple).getActionsReal();
        for(Action a:actions){
            if(a.equals(idAction)){
                for(ActionReal ar :act){
                    if(ar.getAction().equals(a)){
                        if(stat == ActionReal.Status.VALIDER){
                            ar.setStatus(stat);
                            if(couple.getPartener1().equals(ar.getEvaluer())){
                                couple.SetPCpartener1(ar.getAction().getValue());
                                ar.setCommentaire(comment);
                            }else if(couple.getPartener2().equals(ar.getEvaluer())){
                                couple.SetPCpartener2(ar.getAction().getValue());
                                ar.setCommentaire(comment);
                            }
                        }

                    }
                }

            }
        }



    }
    public String recupererIdAction(String actionDesc){

        int i=0;
        while (i != actions.size()){
            if (actions.get(i).getDescription().equals(actionDesc)){
                return actions.get(i).getId();
            }
            i++;
        }
        return null;
    }
    
    private int recupererActionid(String idaction){

        int i=0;
        while (i != actions.size()){
            if (actions.get(i).getId().equals(idaction)){
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public Couple recupererCouple(User user){

        int i=0;
        while (i != couplelist.size()){
            if (couplelist.get(i).getPartener1().equals(user) || couplelist.get(i).getPartener2().equals(user)){
                return couplelist.get(i);
            }
            i++;
        }
        return null;
    }

    public User recupererUser(String user){

        int i=0;
        while (i != userlist.size()){
            if (userlist.get(i).getId().equals(user)){
                return userlist.get(i) ;
            }
            i++;
        }
        return null;
    }
    
    public User creeUser( String id,
            String nom,
            String prenom,
            User.Sex sexe,
            String password){
        User user=null;
        String passecrypt= null;
        try {
            passecrypt = encrypte(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean trouve = false;
        for(User obj : userlist){
            if(obj.getId().hashCode()==id.hashCode())
            {
                trouve =true;
                break;
            }
            else if(obj.getPassword().hashCode()==passecrypt.hashCode()){
                trouve = true;
                break;
            }
        }
        if(trouve != true){
            user=new User(id,nom,prenom,sexe,passecrypt);
        }
        return user;
    }



    public Couple addCouple(User user1,User user2){
        Couple couple;
        if (user1 == null || user2 == null){
            return null;
        }
        else {
                couple=new Couple(user1,user2);
                couplelist.add(couple);
                historique.put(couple,Historique.getIsntance());
                return couple;
            }
    }

    public List<String> getActionDescList(){
        List<String> actionsDesc = new ArrayList<String>();
        int i=0;
        while (i != actions.size()){
            actionsDesc.add(actions.get(i).getDescription());
            i++;
        }
        return actionsDesc;
    }
    private int recupererCouple(Couple couple){

        int i=0;
        while (i != couplelist.size()){
            if (couplelist.get(i).equals(couple)){
                return i;
            }
            i++;
        }
        return -1;
    }
    private int recupererUser(User user){

        int i=0;
        while (i != userlist.size()){
            if (userlist.get(i).equals(user)){
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public List<String> getUserIds() {
      List<String> userIds = new ArrayList<String>();
      for (User user : userlist) {
        userIds.add(user.getId());
      }
      return userIds;
    }
    
    private void setTestData(){
        Action action1= new Action("act1","fait le menage", 10);
        Action action2= new Action("act2","oublie mon anniversaire",-10);
        User user1 = creeUser("id1","firstName1","lastname1", User.Sex.MAN,"hello");
        User user2 = creeUser("id2","firstName2","lastname2", User.Sex.WOMAN,"bonjour");
        addCouple(user1,user2);
        userlist.add(user1);
        userlist.add(user2);
        actions.add(action1);
        actions.add(action2);




    }

}
