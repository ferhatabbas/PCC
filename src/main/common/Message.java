package main.common;

import java.io.*;

public class Message implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Subject subject;
  private Object body;
  
  ////////// AJOUTEZ DES CONSTANTES DANS CET ENUM SELON VOS BESOINS //////////
  public enum Subject { STRING }
  
  public Message(Subject subject, Object body) {
    this.subject = subject;
    this.body = body;
  }
  
  public Subject getSubject() {
    return subject;
  }
  
  public Object getBody() {
    return body;
  }
  
  public void setSubject(Subject subject) {
    this.subject = subject;
  }
  
  public void setBody(Object body) {
    this.body = body;
  }
}
