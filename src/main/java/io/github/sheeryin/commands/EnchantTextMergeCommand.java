package io.github.sheeryin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.stream.Collectors;

public class EnchantTextMergeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] argument) {

        Player player = (Player) sender;
        if (argument[0].equalsIgnoreCase("a")) {
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            if (itemInMainHand.getType().isAir()) {
                player.sendMessage("不能是空气");
                return true;
            }
            if (!Objects.requireNonNull(itemInMainHand.getItemMeta()).hasEnchants()) {
                player.sendMessage("没有附魔");
                return true;
            }
            ItemMeta itemMetaMerge = itemInMainHand.getItemMeta();
            itemMetaMerge.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            List<String> list = new ArrayList<>();
            list.add("");
            int index = 0;
            int number = 0;
            int enchantMergeLength = 3;
            Iterator<Map.Entry<Enchantment, Integer>> iterator = itemInMainHand.getItemMeta().getEnchants().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Enchantment, Integer> entry = iterator.next();
                index = index + 1;
                String enchantment = entry.getKey().getKey().toString().replace("minecraft:", "");
                int enchantmentLevel = entry.getValue();
                if (index <= enchantMergeLength) {
                    list.set(number, list.get(number) + "§f" + enchantment + " " + enchantmentLevel + "; ");
                } else {
                    index = 0;
                    number = number + 1;
                    list.add("§f" + enchantment + " " + enchantmentLevel + "; ");
                }
            }


            itemMetaMerge.setLore(list);
            player.getInventory().getItemInMainHand().setItemMeta(itemMetaMerge);
        }

        return true;


    }

}
