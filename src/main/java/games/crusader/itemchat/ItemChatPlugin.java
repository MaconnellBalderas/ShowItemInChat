package games.crusader.itemchat;

import games.crusader.itemchat.listeners.ChatListener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ItemChatPlugin extends JavaPlugin {

    public void onEnable() {
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = getLogger();

        registerCommands();
        registerEvents();

        logger.info(pdfFile.getName() + " has been enabled (V." + pdfFile.getVersion() + ")");
    }

    public void onDisable(){
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = getLogger();

        logger.info(pdfFile.getName() + " has been disabled (V." + pdfFile.getVersion() + ")");
    }

    public void registerCommands() {
        //heal
    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        //new "Class Name"
        pm.registerEvents(new ChatListener(), this);
    }


}
