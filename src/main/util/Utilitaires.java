package main.util;


/**
 * Created by ferhat on 2015-11-24.
 */
public class Utilitaires {

    public enum Sex {MAN, WOMAN}
    public enum Status { ATTENTE,VALIDER,REFUSER,RIEN }


    public static String encrypte(String password) throws Exception {
        PasswordService ps = new PasswordService();
        return ps.encrypt(password);
    }


}
