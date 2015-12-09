package common;

import java.io.*;

import org.json.*;

public class Action implements Cloneable, Serializable {
  private static final long serialVersionUID = 1L;
  
  private final String id;
  private String description;
  private int value;
  
  public Action(String id, String description, int value) {
    this.id = id;
    this.description = description;
    this.value = value;
  }
  
  public Action(JSONObject jsonObject) {
    id = (String) jsonObject.get("id");
    description = (String) jsonObject.get("description");
    value = (int) jsonObject.get("value");
  }
  
  public String getId() {
    return id;
  }
  
  public String getDescription() {
    return description;
  }
  
  public int getValue() {
    return value;
  }
  
  @Override
  public String toString() {
    String string = "";
    
    string += "         id: " + id + "\n";
    string += "description: " + description + "\n";
    string += "      value: " + value;
    
    return string;
  }
  
  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }
    
    if (!(object instanceof Action)) {
      return false;
    }
    
    Action action = (Action) object;
    return action.id.equals(id);
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }
  
  @Override
  public Action clone() {
    try {
      return (Action) super.clone();
    }
    catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
