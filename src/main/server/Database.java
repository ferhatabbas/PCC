package main.server;

import main.common.Couple;
import main.common.User;

import java.util.*;

/**
 * Created by User on 2015-11-10.
 */
public class Database {
    private List<User> userlist;
    private HashSet<Couple> couplelist;
//    private Map<User,String> password;

    public Database(){
        userlist=new ArrayList<User>();
        couplelist=new HashSet<Couple>();
        setTestData();


    }

    public boolean Connection(String ID,String Pass) {
        boolean result = false;

        for (User obj : userlist) {
            if ((obj.getId().hashCode() == ID.hashCode())) {
                if (obj.getPassword().equals(Pass)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    private void setTestData() {
        User user1 = new User("id1","firstName1","lastname1",User.sexe.Man);
        User user2 = new User("id2","firstName2","lastname2",User.sexe.Woman);
        Couple couple1=new Couple(user1,user2);
        user1.setPassword("1234");
        user2.setPassword("3421");
        couplelist.add(couple1);
        userlist.add(user1);
        userlist.add(user2);


    }

}
