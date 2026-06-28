package com.yourname.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import com.yourname.plugin.OreSystem.OreGui;


public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Plugin Enabled!");

    getCommand("oregui").setExecutor(new OreGui());

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Disabled!");
    }
}