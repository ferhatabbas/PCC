package server;

import java.util.*;

import common.*;

public class MessageService {
  private Map<String, Queue<Message>> messageQueues;
  
  public MessageService(List<String> userIds) {
    messageQueues = new HashMap<String, Queue<Message>>();
    for (String userId : userIds) {
      messageQueues.put(userId, new ArrayDeque<Message>());
    }
  }
  
  // Sends a message to a user. Does nothing if the given user id doesn't exist.
  public synchronized void send(String userId, Message message) {
    if (userIdExists(userId)) {
      messageQueues.get(userId).add(message);
    }
  }
  
  // Receives the next message sent to the given user. Returns a NULL message if
  // there is no message for this user, or the given user id doesn't exist.
  public synchronized Message receive(String userId) {
    if (!userIdExists(userId)) {
      return new Message(Message.Subject.NULL);
    }
    
    Message message = messageQueues.get(userId).poll();
    if (message == null) {
      return new Message(Message.Subject.NULL);
    }
    else {
      return message;
    }
  }
  
  public boolean userIdExists(String userId) {
    return messageQueues.containsKey(userId);
  }
}
