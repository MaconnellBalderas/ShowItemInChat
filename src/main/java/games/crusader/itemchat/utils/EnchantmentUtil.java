package games.crusader.itemchat.utils;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentUtil {

    public static String getPrettyName(Enchantment enchant) {
        String enchantName = null;
        if (enchant.getName().equals("DAMAGE_ALL")) {
            enchantName = "Sharpness";
        } else if (enchant.getName().equals("DAMAGE_ARTHROPODS")) {
            enchantName = "Bane Of Arthropods";
        } else if (enchant.getName().equals("DAMAGE_UNDEAD")) {
            enchantName = "Smite";
        } else if (enchant.getName().equals("DIG_SPEED")) {
            enchantName = "Efficiency";
        } else if (enchant.getName().equals("DURABILITY")) {
            enchantName = "Unbreaking";
        } else if (enchant.getName().equals("FIRE_ASPECT")) {
            enchantName = "Fire";
        } else if (enchant.getName().equals("KNOCKBACK")) {
            enchantName = "Knockback";
        } else if (enchant.getName().equals("LOOT_BONUS_BLOCKS")) {
            enchantName = "Fortune";
        } else if (enchant.getName().equals("LOOT_BONUS_MOBS")) {
            enchantName = "Looting";
        } else if (enchant.getName().equals("OXYGEN")) {
            enchantName = "Respiration";
        } else if (enchant.getName().equals("PROTECTION_ENVIRONMENTAL")) {
            enchantName = "Protection";
        } else if (enchant.getName().equals("PROTECTION_EXPLOSIONS")) {
            enchantName = "Blast Protection";
        } else if (enchant.getName().equals("PROTECTION_FALL")) {
            enchantName = "Feather Falling";
        } else if (enchant.getName().equals("PROTECTION_FIRE")) {
            enchantName = "Fire Protection";
        } else if (enchant.getName().equals("PROTECTION_PROJECTILE")) {
            enchantName = "Projectile Projection";
        } else if (enchant.getName().equals("SILK_TOUCH")) {
            enchantName = "Silk Touch";
        } else if (enchant.getName().equals("WATER_WORKER")) {
            enchantName = "Aqua Affinity";
        } else if (enchant.getName().equals("ARROW_FIRE")) {
            enchantName = "Flame";
        } else if (enchant.getName().equals("ARROW_DAMAGE")) {
            enchantName = "Power";
        } else if (enchant.getName().equals("ARROW_KNOCKBACK")) {
            enchantName = "Punch";
        } else if (enchant.getName().equals("ARROW_INFINITE")) {
            enchantName = "Infinity";
        } else if (enchant.getName().equals("")) {
            enchantName = "No Enchants";
        }
    return enchantName;
    }
}
