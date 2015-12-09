package server;

import java.util.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import org.json.*;

import common.*;

public class RestfulClient {
  private static final String URI = "http://localhost:8080/restful-service/actions";
  
  // Returns a list of all the actions from our home web service, or null if
  // there is an error.
  public List<Action> getActionList() {
    List<Action> actionList = null;
    
    Response response = ClientBuilder.newClient().target(URI).request().get();
    
    if (response.getStatus() == 200) {
      actionList = new ArrayList<Action>();
      JSONArray jsonArray = new JSONArray(new JSONTokener((String) response.readEntity(String.class)));
      for (int i = 0; i < jsonArray.length(); ++i) {
        actionList.add(new Action(jsonArray.getJSONObject(i)));
      }
    }
    
    return actionList;
  }
  
  // Returns a specific action from our home web service, or null if there is an
  // error.
  public Action getAction(String id) {
    Action action = null;
    
    Response response = ClientBuilder.newClient().target(URI + "/" + id).request().get();
    
    if (response.getStatus() == 200) {
      action = new Action(new JSONObject(new JSONTokener((String) response.readEntity(String.class))));
    }
    
    return action;
  }
  
  // Creates a new action inside our home web service database. Returns the URI
  // of the new action if the operation was successful or an empty string
  // otherwise.
  public String createAction(Action action) {
    String uri = "";
    
    Response response = ClientBuilder.newClient().target(URI).request().post(Entity.json(new JSONObject(action).toString()));
    
    if (response.getStatus() == 201) {
      uri = response.getLocation().toString();
    }
    
    return uri;
  }
  
  // Updates an action inside our home web service database. Returns 'true' if
  // the operation was successful or 'false' otherwise.
  public boolean updateAction(Action update) {
    Response response = ClientBuilder.newClient().target(URI).request().put(Entity.json(new JSONObject(update).toString()));
    
    return response.getStatus() == 204;
  }
  
  // For testing only.
  public void test() {
    System.out.println("////////// ACTION LIST //////////");
    for (Action action : getActionList()) {
      System.out.println(action);
    }
    System.out.println();
    
    System.out.println("////////// GET id1 //////////");
    System.out.println(getAction("id1"));
    System.out.println();
    
    System.out.println("////////// CREATE id4 //////////");
    Action action4 = new Action("id4", "description4", 4);
    System.out.println(action4);
    System.out.println(createAction(action4));
    System.out.println();
    
    System.out.println("////////// UPDATE id4 //////////");
    Action action5 = new Action("id4", "description5", 5);
    System.out.println(action5);
    System.out.println(updateAction(action5));
    System.out.println();
    
    System.out.println("////////// ACTION LIST //////////");
    for (Action action : getActionList()) {
      System.out.println(action);
    }
    System.out.println();
  }
}
