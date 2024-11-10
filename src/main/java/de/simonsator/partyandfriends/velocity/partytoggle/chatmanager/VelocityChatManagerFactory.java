package de.simonsator.partyandfriends.velocity.partytoggle.chatmanager;

import java.util.List;

public class VelocityChatManagerFactory {
	public static UniversalChatManager createChatManager(List<String> ignoredPrefixes) {
		return new VelocityChatManager(ignoredPrefixes);
	}
}
