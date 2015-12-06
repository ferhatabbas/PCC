package test;


import junit.framework.TestCase;
import main.common.User;
import main.server.Database;
import main.util.Utilitaires;

/**
 * Created by ferhat on 2015-11-18.
 */
public class ConnectionTest extends TestCase {
    private Database data;
    private User us;
    protected void setUp() throws Exception {
        super.setUp();
        data = new Database();
        us= new User("id3","firstName1","lastname1", Utilitaires.Sex.MAN,"hello");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        data = null;
    }
    public void testConnection1() throws Exception {

        assertTrue(data.connection("id1","hello"));

    }
    public void testConnection2() throws Exception {

        assertFalse(data.connection("id2","hello"));

    }

/*    public void testUser() throws Exception{
        assertEquals(-1,data.recupererUser(us));
    }*/

}
