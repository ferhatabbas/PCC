package main.common;

import java.io.Serializable;

/**
 * Created by User on 2015-11-10.
 */
public class User implements Serializable {
    private static final long serialVersionUID=1L;


    //private ActionReal actions;
    private String id;
    private String Nom;
    private String Prenom;
    private sexe Sexe;
    private String password;

    public enum sexe {Man, Woman}

    public User(String Login_c, String Nom_c, String Prenom_c, sexe Sexe_c) {
        id = Login_c;
        Nom = Nom_c;
        Prenom = Prenom_c;
        Sexe = Sexe_c;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public sexe getSexe() {
        return Sexe;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setSexe(sexe sexe) {
        Sexe = sexe;
    }
    public boolean equals (Object o){
        boolean result = false;
        if(o instanceof User){
            if(this.id == ((User) o).id) result=true;
        }

        return result;
    }
    public int hashCode(){
    return id.hashCode();
    }
   /* public ActionReal getActions() {
        return actions;
    }

    public void setActions(ActionReal actions) {
        this.actions = actions;
    }
*/
}
