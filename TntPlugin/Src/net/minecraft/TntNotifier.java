package net.minecraft;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TntNotifier extends JavaPlugin {

	private Logger log = Logger.getLogger("Minecraft");
	
	Updater upd = new Updater(this);
	public String address = "http://dl.dropbox.com/u/18231741/TntNotifier.jar";
	public String updatepath = "plugins"+ File.separator + "TntNotifier.jar";
	
	public void onEnable() {
		
		if(upd.updateCheck()){
            getServer().getPluginManager().disablePlugin(this);
            getServer().reload();
        }else{
        	
        PluginManager pm = this.getServer().getPluginManager();
    	pm.registerEvent(Event.Type.BLOCK_PLACE, new TntBlockListener(this), Priority.Highest, this);
    	
    	this.logText("Enabled");
    	
        }
	}
	
	public void onDisable() {
		this.logText("Disabled");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		if(cmd.getName().equalsIgnoreCase("tn")){
			if(sender instanceof Player){
				sender.sendMessage(ChatColor.GOLD + "This mod was coded by Immortal");
				this.logText("Some one used /tn");
                return true;
			}
		}
		
		return false;
	}
	protected void logText(String txt){
		PluginDescriptionFile pdFile = this.getDescription();
		this.log.info(pdFile.getName() + " " + pdFile.getVersion() + ": " + txt);
	}
	

}

