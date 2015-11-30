package test;


import junit.framework.TestCase;
import main.server.Database;
import main.util.PasswordService;

/**
 * Created by ferhat on 2015-11-18.
 */
public class TestSylvain extends TestCase {


    public void testpassword() throws  Exception{
       PasswordService ps = new PasswordService();
        
        String essai = "toto";
        essai = ps.encrypt(essai);
        System.out.println(essai);

        String essai2 = "toto";
        essai2 = ps.encrypt(essai2);
        System.out.println(essai2);
        if(essai2.equals(essai))
            System.out.println("TRUE");
        else
            System.out.println("FALSE");


    }


}
