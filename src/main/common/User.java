package main.common;

import java.io.Serializable;

/**
 * Created by User on 2015-11-10.
 */
public class User implements Serializable {
    private static final long serialVersionUID=1L;

    String id;
    String Nom;
    String Prenom;
    sexe Sexe;

    public enum sexe {Man, Woman}

    public User(String Login_c, String Nom_c, String Prenom_c, sexe Sexe_c) {
        id = Login_c;
        Nom = Nom_c;
        Prenom = Prenom_c;
        Sexe = Sexe_c;
    }
}
