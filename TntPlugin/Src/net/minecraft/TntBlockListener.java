package net.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TntBlockListener extends BlockListener{
	
	private TntNotifier plugin;
	
	public TntBlockListener(TntNotifier instance){
		this.plugin = instance;
	}

	public void onBlockPlace(BlockPlaceEvent event){
		if(event.isCancelled()) return;
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		if(block.getType() == Material.TNT){
			block.setType(Material.AIR);
			
			player.sendMessage(ChatColor.GREEN + "Sorry " + player.getName() + " are not allowed to place this block!");
			
			plugin.logText(player.getName()+ "has tried to placed tnt!");
		}
		
	}
}
