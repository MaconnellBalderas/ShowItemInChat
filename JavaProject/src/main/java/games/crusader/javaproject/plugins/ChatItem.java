package games.crusader.javaproject.plugins;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.Map;

public class ChatItem implements Listener {

    @EventHandler
    public void ShowItem(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        String message = e.getMessage().toLowerCase();

        if(message.contains("[item]")){
            e.setCancelled(true);

            String itemInHand = player.getInventory().getItemInMainHand().getType().name().replace("_", " ").toLowerCase();

            TextComponent tc = new TextComponent("" + message.replace("[item]", itemInHand + ""));

            ArrayList<String> formattedEnchantments = new ArrayList<String>();
            Map<Enchantment, Integer> enchantments = player.getInventory().getItemInMainHand().getEnchantments();

            for(Enchantment enchant : enchantments.keySet()){
                String enchantName = "";
                    if(enchant.getName().equals("DAMAGE_ALL")){
                        enchantName = "Sharpness";
                    } else if(enchant.getName().equals("DAMAGE_ARTHROPODS")) {
                        enchantName = "Bane Of Arthropods";
                    } else if(enchant.getName().equals("DAMAGE_UNDEAD")) {
                        enchantName = "Smite";
                    } else if(enchant.getName().equals("DIG_SPEED")) {
                        enchantName = "Efficiency";
                    } else if(enchant.getName().equals("DURABILITY")) {
                        enchantName = "Unbreaking";
                    } else if(enchant.getName().equals("FIRE_ASPECT")) {
                        enchantName = "Fire";
                    } else if(enchant.getName().equals("KNOCKBACK")) {
                        enchantName = "Knockback";
                    } else if(enchant.getName().equals("LOOT_BONUS_BLOCKS")) {
                        enchantName = "Fortune";
                    } else if(enchant.getName().equals("LOOT_BONUS_MOBS")) {
                        enchantName = "Looting";
                    } else if(enchant.getName().equals("OXYGEN")) {
                        enchantName = "Respiration";
                    } else if(enchant.getName().equals("PROTECTION_ENVIRONMENTAL")) {
                        enchantName = "Protection";
                    } else if(enchant.getName().equals("PROTECTION_EXPLOSIONS")) {
                        enchantName = "Blast Protection";
                    } else if(enchant.getName().equals("PROTECTION_FALL")) {
                        enchantName = "Feather Falling";
                    } else if(enchant.getName().equals("PROTECTION_FIRE")) {
                        enchantName = "Fire Protection";
                    } else if(enchant.getName().equals("PROTECTION_PROJECTILE")) {
                        enchantName = "Projectile Projection";
                    } else if(enchant.getName().equals("SILK_TOUCH")) {
                        enchantName = "Silk Touch";
                    } else if(enchant.getName().equals("WATER_WORKER")) {
                        enchantName = "Aqua Affinity";
                    } else if(enchant.getName().equals("ARROW_FIRE")) {
                        enchantName = "Flame";
                    } else if(enchant.getName().equals("ARROW_DAMAGE")) {
                        enchantName = "Power";
                    } else if(enchant.getName().equals("ARROW_KNOCKBACK")) {
                        enchantName = "Punch";
                    } else if(enchant.getName().equals("ARROW_INFINITE")) {
                        enchantName = "Infinity";
                    } else if(enchant.getName().equals("")){
                        enchantName = "No Enchants";
                    }

                int enchantNumber = enchantments.get(enchant);

                formattedEnchantments.add(enchantName + " " + enchantNumber);
            }

            String ench = "";

            for(String s : formattedEnchantments){
                ench += s + "\n";
            }

            String NameDetail = player.getInventory().getItemInMainHand().getType().name().replace("_", " ").toLowerCase();

            int AmountDetail = player.getInventory().getItemInMainHand().getAmount();

            int DurabilityDetail = player.getInventory().getItemInMainHand().getType().getMaxDurability();

            int currentDurability = player.getInventory().getItemInMainHand().getType().getMaxDurability() - player.getInventory().getItemInMainHand().getDurability();

            String totalDurability;

            if(DurabilityDetail == 0){
                totalDurability = "No Durability";
            } else {
                totalDurability = currentDurability + "/" + DurabilityDetail;
            }

            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("" +  NameDetail + "\n" + ench + "\n"  + AmountDetail + " " + itemInHand + "\n"  + totalDurability).create()));

            player.spigot().sendMessage( tc );

        }


    }


}
