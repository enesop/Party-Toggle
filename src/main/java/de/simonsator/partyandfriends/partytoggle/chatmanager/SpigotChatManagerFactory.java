package de.simonsator.partyandfriends.partytoggle.chatmanager;

import java.util.List;

public class SpigotChatManagerFactory {
	public static UniversalChatManager createChatManager(List<String> ignoredPrefixes) {
		return new SpigotChatManager(ignoredPrefixes);
	}
}
