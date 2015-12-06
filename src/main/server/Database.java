package main.server;
import jdk.nashorn.internal.runtime.ListAdapter;
import main.common.*;
import main.util.Utilitaires;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static main.util.Utilitaires.encrypte;


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

        setTestData();


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

    public void createAR(String sender, String receiver, String idaction){
        // recuperer la position de chaque user ( le sender et le receiver) et recuperer l<action dans les listes de donnees
        int senderUser= recupererUserid(sender);
        int receiverUser= recupererUserid(receiver);
        int posAction= recupererActionid(idaction);

        int pos=0;
        // cree l'action a realiser
        ActionReal act = new ActionReal(actions.get(posAction),userlist.get(senderUser),userlist.get(receiverUser));
        // trouver le couple des deux users puis leur associer l<action
        Couple couple= new Couple(userlist.get(senderUser),userlist.get(receiverUser));
        while(!couplelist.get(pos).equals(couple)) pos++;
        historique.get(couplelist.get(pos)).setAction(act);



    }



    public List<ActionReal> actionRealiser(Couple couple, Date from, Date to) throws ParseException {
        // creation du calendrier pour l'incrementation de la date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date i=format.parse(format.format(from));
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


    public void UpdateAR(String comment, Utilitaires.Status stat, String sender, String idAction){

    }
    private int recupererActionid(String idaction){

        int i=0;
        while (i != actions.size()){
            if (actions.get(i).getIDaction().equals(idaction)){
                return i;
            }
            i++;
        }
        return -1;
    }
    private int recupererUserid(String prenom){

        int i=0;
        while (i != userlist.size()){
            if (userlist.get(i).getNom().equals(prenom)){
                return i;
            }
            i++;
        }
        return -1;
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

    public User creeUser( String id,
            String nom,
            String prenom,
            Utilitaires.Sex sexe,
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



    public boolean creeCouple(User user1,User user2){
        Couple couple;
        if (user1 == null || user2 == null){
            return false;
        }
        else {
                couple=new Couple(user1,user2);
                couplelist.add(couple);
                historique.put(couple,Historique.getIsntance());
                return true;
            }
    }

    public List<Action> getActions(){
        return this.actions;
    }

    private void setTestData(){
        Action action1= new Action("act1","fait le menage",10);
        Action action2= new Action("act2","oublie mon anniversaire",-10);
        User user1 = creeUser("id1","firstName1","lastname1", Utilitaires.Sex.MAN,"hello");
        User user2 = creeUser("id2","firstName2","lastname2", Utilitaires.Sex.WOMAN,"bonjour");
        creeCouple(user1,user2);
        userlist.add(user1);
        userlist.add(user2);
        actions.add(action1);
        actions.add(action2);




    }

}
