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


//            Option 1 splits tcItem and tcMessage to seperate parts but can not replace [item] with tcItem; string != TextComponent
            String item = message.replace( message, itemInHand);

            TextComponent tcItem = new TextComponent( ChatColor.AQUA + item );

            TextComponent tcMessage = new TextComponent("<" + player.getDisplayName() + "> " + message.replace("[item]", tcItem ));

//            Option 2 hover event on entire sentence but color coded the item to show the player what it is; entire sentence == hover event
//            TextComponent tcItem = new TextComponent("<" + player.getDisplayName() + "> " +  message.replace("[item]", ChatColor.AQUA + itemInHand) );


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

            tcItem.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( ChatColor.AQUA + itemInHand + "\n" + ChatColor.RESET + ench + "\n"  + amount + " " + itemInHand + "\n"  + totalDurability).create()));

//            tcMessage.addExtra(tcItem);

            //Option 1
            for(Player totalPlayers : onlinePlayers){
                totalPlayers.spigot().sendMessage( tcMessage );
            }

            //Option 2
//            for(Player totalPlayers : onlinePlayers){
//                totalPlayers.spigot().sendMessage( tcItem );
//            }
        }

    }


}
