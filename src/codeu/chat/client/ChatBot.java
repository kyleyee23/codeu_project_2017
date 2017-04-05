package codeu.chat.client;

import codeu.chat.client.Controller;
import codeu.chat.util.Logger;
import codeu.chat.common.Message;
import codeu.chat.common.Uuid;

public final class ChatBot {

	private final static Logger.Log LOG = Logger.newLog(ChatBot.class);
	private final Controller controller;
	private final Uuid author;
	private final Uuid conversation;
	
	private final static String botName = "@ChatBot";	//current ChatBot name

	public ChatBot(Controller controller, Uuid author, Uuid conversation) {
		this.controller = controller;
		this.author = author;
		this.conversation = conversation;
	}

	// checks if the message body includes ChatBot's name.
	// method is case sensitive.
	public static boolean checkBotCall(String body){
    	for (int i = body.length() - botName.length(); i >= 0; i--){
      		final char ch = body.charAt(i);
      		if (ch != botName.charAt(0))
        		continue;
      		if (body.regionMatches(false, i, botName, 0, botName.length())) //checks if two string regions are equal
            	return true;
    	}
    	return false;
	}

	// generate response to message body.
	public Message getResponse(String body) {
		final Message newMessage = controller.newChatBotMessage(author, conversation, body);
		if (newMessage == null) {
        	System.out.format("Error: no response - %s.\n");
    	} else {
        	LOG.info("New message:, Author= %s UUID= %s", author, newMessage.id);
    	}
    	return newMessage;
    }

    // checks if a client has quit session with ChatBot.
	public boolean isRunning(String body) {
		if (body.equals("exit")){
			return false;
		}
		return true;
	}
}
