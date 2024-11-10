package de.simonsator.partyandfriends.partytoggle.chatmanager;

import java.util.List;

public class BungeeChatManagerFactory {
	public static UniversalChatManager createChatManager(List<String> ignoredPrefixes) {
		return new BungeeChatManager(ignoredPrefixes);
	}
}
