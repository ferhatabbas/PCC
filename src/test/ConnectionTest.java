package test;


import junit.framework.TestCase;
import main.server.Database;

/**
 * Created by ferhat on 2015-11-18.
 */
public class ConnectionTest extends TestCase {
    private Database data;
    protected void setUp() throws Exception {
        super.setUp();
        data = new Database();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        data = null;
    }
    public void testConnection1() {

        assertTrue(data.Connection("id1","1234"));

    }
    public void testConnection2() {

        assertFalse(data.Connection("id2","1234"));

    }

}
