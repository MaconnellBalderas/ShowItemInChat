package games.crusader.itemchat.listeners;

import games.crusader.itemchat.utils.EnchantmentUtil;
import games.crusader.itemchat.utils.StringUtil;
import net.md_5.bungee.api.chat.ComponentBuilder;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ChatListener implements Listener {
    public static final String keyword = "[item]";


    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void ShowItem(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().toLowerCase();

        String formattedMessage = event.getFormat();
        formattedMessage = formattedMessage.replace("%1$s", player.getDisplayName());
        formattedMessage = formattedMessage.replace("%2$s", event.getMessage());


        if (message.contains(keyword)) {
            event.setCancelled(true);
            String itemName = ChatColor.AQUA + player.getInventory().getItemInMainHand().getType().name().replace("_", " ").toLowerCase();
            List<String> parts = StringUtil.split(keyword, formattedMessage);

            HoverEvent hoverEvent = getHoverEvent(player.getInventory().getItemInMainHand());

            TextComponent tcMessage;
            String lastString = parts.get(parts.size() - 1);
            if (lastString.equals(keyword)) {
                tcMessage = new TextComponent(itemName);
                tcMessage.setHoverEvent(hoverEvent);
            } else {
                tcMessage = new TextComponent(lastString);
            }

            for (int i = parts.size() - 2; i >= 0; i--) {
                TextComponent temp;
                String text = parts.get(i);
                if (text.equals(keyword)){
                    temp = new TextComponent(itemName);
                    temp.setHoverEvent(hoverEvent);
                }
                else {
                    temp = new TextComponent(text);
                    temp.setHoverEvent(null);
                }
                temp.addExtra(tcMessage);
                tcMessage = temp;

            }

//            Option 1
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayer.spigot().sendMessage(tcMessage);
            }

        }

    }

    private HoverEvent getHoverEvent(ItemStack itemStack) {
        String itemName = "Air";
        int amount = 0;
        String totalDurability = "No Durability";
        String ench = "";

        if (itemStack != null) {
            ItemMeta meta = itemStack.getItemMeta();
            if (meta != null && meta.getDisplayName() != null) {
                itemName = meta.getDisplayName();
            } else {
                itemName = itemStack.getType().name().replace("_", " ").toLowerCase();
            }

            List<String> formattedEnchantments = new ArrayList<>();
            Map<Enchantment, Integer> enchantments = itemStack.getEnchantments();

            for (Enchantment enchant : enchantments.keySet()) {
                String enchantName = EnchantmentUtil.getPrettyName(enchant);
                int enchantNumber = enchantments.get(enchant);
                formattedEnchantments.add(enchantName + " " + enchantNumber);
            }

            ench = String.join("\n", formattedEnchantments);

            amount = itemStack.getAmount();

            int maxDurability = itemStack.getType().getMaxDurability();

            int durability = itemStack.getType().getMaxDurability() - itemStack.getDurability();

            if (maxDurability == 0) {
                totalDurability = "No Durability";
            } else {
                totalDurability = durability + "/" + maxDurability;
            }
        }

        return new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.AQUA + itemName + "\n" + ChatColor.RESET + ench + "\n" + amount + " " + itemName + "\n" + totalDurability).create());
    }


}
