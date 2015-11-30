package main.util;


/**
 * Created by ferhat on 2015-11-24.
 */
public class Utilitaires {

    public enum sex {Man, Woman}
    public enum Status { ATTENTE,VALIDER,REFUSER,RIEN }
    public enum Response { YES, NO ,RIEN }

    public static String encrypte(String password) throws Exception {
        PasswordService ps = new PasswordService();
        return ps.encrypt(password);
    }


}
