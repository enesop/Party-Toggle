package de.simonsator.partyandfriends.velocity.partytoggle.chatmanager;

import de.simonsator.partyandfriends.velocity.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.velocity.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.velocity.api.party.PartyManager;
import de.simonsator.partyandfriends.velocity.api.party.PlayerParty;
import de.simonsator.partyandfriends.velocity.party.command.PartyChat;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class UniversalChatManager {
	private final HashSet<UUID> players = new HashSet<>();
	protected final List<String> IGNORED_PREFIXES;
	private final boolean IGNORED_PREFIXES_IS_EMPTY;

	public UniversalChatManager(List<String> ignoredPrefixes) {
		IGNORED_PREFIXES = ignoredPrefixes;
		IGNORED_PREFIXES_IS_EMPTY = ignoredPrefixes.isEmpty();
	}

	protected boolean executeChat(PAFPlayer pPlayer, String pMessage) {
		if (pPlayer instanceof OnlinePAFPlayer) {
			OnlinePAFPlayer onlinePAFPlayer = (OnlinePAFPlayer) pPlayer;
			final PlayerParty party = PartyManager.getInstance().getParty(onlinePAFPlayer);
			if (party == null)
				return false;
			PartyChat.getInstance().send(onlinePAFPlayer, pMessage.split(" "));
			return true;
		}
		return false;
	}

	public boolean changeState(UUID pUUID) {
		if (players.remove(pUUID))
			return false;
		players.add(pUUID);
		return true;
	}

	public boolean startsWithIgnoredPrefix(String pMessage) {
		if (IGNORED_PREFIXES_IS_EMPTY)
			return false;
		for (String prefix : IGNORED_PREFIXES) {
			if (pMessage.startsWith(prefix))
				return true;
		}
		return false;
	}

	protected boolean hasPartyChatNotEnabled(UUID pUUID) {
		return !players.contains(pUUID);
	}

	public void remove(UUID pUUID) {
		players.remove(pUUID);
	}
}
