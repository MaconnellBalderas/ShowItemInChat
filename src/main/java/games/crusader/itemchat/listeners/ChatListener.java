package games.crusader.itemchat.listeners;

import games.crusader.itemchat.utils.EnchantmentUtil;
import  net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;

public class ChatListener implements Listener {

     @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void ShowItem(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().toLowerCase();

        if(message.contains("[item]")){
            event.setCancelled(true);
            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

            

            String itemInHand = player.getInventory().getItemInMainHand().getType().name().replace("_", " ").toLowerCase();

            TextComponent tc = new TextComponent("" + message.replace("[item]", itemInHand + ""));

            List<String> formattedEnchantments = new ArrayList<String>();
            Map<Enchantment, Integer> enchantments = player.getInventory().getItemInMainHand().getEnchantments();

            for(Enchantment enchant : enchantments.keySet()){
                String enchantName = EnchantmentUtil.getPrettyName(enchant);
                int enchantNumber = enchantments.get(enchant);
                formattedEnchantments.add(enchantName + " " + enchantNumber);
            }

            String ench =  String.join("\n", formattedEnchantments);

            int amount = player.getInventory().getItemInMainHand().getAmount();

            int maxDurability = player.getInventory().getItemInMainHand().getType().getMaxDurability();

            int durability = player.getInventory().getItemInMainHand().getType().getMaxDurability() - player.getInventory().getItemInMainHand().getDurability();

            String totalDurability;

            if(maxDurability == 0){
                totalDurability = "No Durability";
            } else {
                totalDurability = durability + "/" + maxDurability;
            }

            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( ChatColor.AQUA + itemInHand + "\n" + ChatColor.RESET + ench + "\n"  + amount + " " + itemInHand + "\n"  + totalDurability).create()));
            player.spigot().sendMessage( tc );

        }


    }


}
