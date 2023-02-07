package io.github.sheeryin;

import io.github.sheeryin.commands.EnchantTextMergeCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
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

        File folder = new File(this.getDataFolder(), "test");
        if (!folder.exists()) {
            folder.mkdirs();
        } else {
            // 文件夹已存在，取消操作
            return;
        }

        File test = new File(folder, "test.yml");
        Bukkit.getLogger().info(test.toString());
        this.saveResource(test.getName(), true);


    }

    public void onDisable() {
        Bukkit.getLogger().info("§f[§e附魔合并§f] 插件开始卸载");
    }
}
