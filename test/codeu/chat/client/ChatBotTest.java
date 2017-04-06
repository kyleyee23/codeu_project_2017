package codeu.chat.client;

import static org.junit.Assert.*;
import org.junit.Test;

import codeu.chat.client.ChatBot;

public class ChatBotTest {
	
	@Test
	public void testChatBotCallwithEmptyBody() {
		String body = "";
		boolean expected = false;
		boolean actual = ChatBot.checkBotCall(body);
		assertEquals(actual, expected);
	}

	@Test
	public void testChatBotCallwithonlyBotName() {
		String body = "@ChatBot";
		boolean expected = true;
		boolean actual = ChatBot.checkBotCall(body);
		assertEquals(actual, expected);
	}
	
	@Test
	public void testChatBotCallwithNoSpaces() {
		String body = "Hi@ChatBotHowareyou";
		boolean expected = true;
		boolean actual = ChatBot.checkBotCall(body);
		assertEquals(actual, expected);
	}
	
	@Test
	public void testChatBotCallwithCaseInsensitiveBotName() {
		String body = "@chatbot";
		boolean expected = false;
		boolean actual = ChatBot.checkBotCall(body);
		assertEquals(actual, expected);
	}
}
