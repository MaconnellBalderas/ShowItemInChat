package games.crusader.javaproject;

import games.crusader.javaproject.plugins.ChatItem;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class JavaProjects extends JavaPlugin {

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
        pm.registerEvents(new ChatItem(), this);
    }


}
