package common;

import java.io.Serializable;

/**
 * Created by User on 2015-11-10.
 */
public class User implements Serializable {
    private static final long serialVersionUID=1L;
    public enum Sex {MAN, WOMAN}

    //private ActionReal actions;
    private String id;
    private String nom;
    private String prenom;
    private Sex sexe;
    private String password;

    public User(String log, String nom, String prenom, Sex sexe,String password) {
        this.id = log;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.password=password;
    }
    public User(String log) {
        this.id = log;
        this.nom = " ";
        this.prenom = " ";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        prenom = prenom;
    }

    public Sex getSexe() {
        return sexe;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setSexe(Sex sexe) {
        sexe = sexe;
    }


    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (!(object instanceof User)) {
            return false;
        }

        User user = (User) object;
        return user.id.equals(id);
    }

    public int hashCode(){
    return id.hashCode();
    }

}
