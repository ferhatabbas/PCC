package common;

import java.io.*;

public class Message implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Subject subject;
  private String to;
  private String from;
  private Object body;
  
  ////////// AJOUTEZ DES CONSTANTES DANS CET ENUM SELON VOS BESOINS //////////
  public enum Subject { SYNC, NULL, P2P, CONNECT, HISTORIQUE, ACTIONS ,ADD_AR, UPDATE_AR , ADD_COUPLE }
  
  public Message() {
    subject = Subject.NULL;
  }
  
  public Message(Subject subject) {
    this.subject = subject;
  }
  
  public Message(Subject subject, Object body) {
    this.subject = subject;
    this.body = body;
  }
  
  public Message(Subject subject, String to, String from, Object body) {
    this.subject = subject;
    this.to = to;
    this.from = from;
    this.body = body;
  }
  
  public Subject getSubject() {
    return subject;
  }
  
  public String getTo() {
    return to;
  }
  
  public String getFrom() {
    return from;
  }
  
  public Object getBody() {
    return body;
  }
  
  public void setSubject(Subject subject) {
    this.subject = subject;
  }
  
  public void setTo(String to) {
    this.to = to;
  }
  
  public void setFrom(String from) {
    this.from = from;
  }
  
  public void setBody(Object body) {
    this.body = body;
  }
}
