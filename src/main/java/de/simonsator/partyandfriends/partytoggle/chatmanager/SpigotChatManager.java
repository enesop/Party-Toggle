package de.simonsator.partyandfriends.partytoggle.chatmanager;

import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class SpigotChatManager extends UniversalChatManager implements Listener {
	public SpigotChatManager(List<String> ignoredPrefixes) {
		super(ignoredPrefixes);
	}

	@EventHandler
	public void onWrite(AsyncPlayerChatEvent pEvent) {
		Player player = pEvent.getPlayer();
		String message = pEvent.getMessage();
		if (message.startsWith("/") || startsWithIgnoredPrefix(message))
			return;
		if (hasPartyChatNotEnabled(player.getUniqueId()))
			return;
		pEvent.setCancelled(executeChat(PAFPlayerManager.getInstance().getPlayer(player.getUniqueId()), message));
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent pEvent) {
		remove(pEvent.getPlayer().getUniqueId());
	}
}

