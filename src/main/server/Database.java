package main.server;

import main.common.Couple;
import main.common.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2015-11-10.
 */
public class Database {
    private List<User> userlist;
    private List<Couple> couplelist;
    private Map<User,String> password;

    public Database(){
        userlist=new ArrayList<User>();
        couplelist=new ArrayList<Couple>();
        password= new HashMap<User,String>();
        setTestData();


    }

    private void setTestData() {
        User user1 = new User("id1","firstName1","lastname1",User.sexe.Man);
        User user2 = new User("id2","firstName2","lastname2",User.sexe.Woman);
        Couple couple1=new Couple(user1,user2);

    }

}
