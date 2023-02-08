package io.github.sheeryin;

import io.github.sheeryin.Language.EnchantmentAlign;
import io.github.sheeryin.commands.EnchantTextMergeCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class EnchantTextMergeMain extends JavaPlugin {

    private static EnchantTextMergeMain instance;

    public static EnchantTextMergeMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getLogger().info("§f[§e附魔合并§f] 插件开始加载");
        Objects.requireNonNull(this.getCommand("enchanttextmerge")).setExecutor(new EnchantTextMergeCommand());
        EnchantmentAlign enchantmentAlign = new EnchantmentAlign();
        enchantmentAlign.minecraftEnchantmentContrast();
    }

    public void onDisable() {
        Bukkit.getLogger().info("§f[§e附魔合并§f] 插件开始卸载");
    }
}
