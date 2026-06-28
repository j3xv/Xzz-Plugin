package com.yourname.plugin.OreSystem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class OreGui implements CommandExecutor{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String labe,@NotNull String[] arg) {


       Inventory gui = Bukkit.createInventory(null, 27, "Main");
       Player p = (Player) sender;
       

       ItemStack diamond = new ItemStack(Material.DIAMOND_BLOCK);
       ItemStack gold = new ItemStack(Material.GOLD_BLOCK);
       ItemStack iron = new ItemStack(Material.IRON_BLOCK);
       ItemStack coal = new ItemStack(Material.COAL_BLOCK);

       gui.setItem(11, diamond);
       gui.setItem(13, gold);
       gui.setItem(15, iron);
       gui.setItem(22, coal);

       ItemMeta dia = diamond.getItemMeta();
       ItemMeta gld = gold.getItemMeta();
       ItemMeta iro = iron.getItemMeta();
       ItemMeta cal = coal.getItemMeta();

       dia.setDisplayName("§bDiamond Setting");
       gld.setDisplayName("§eGold Setting");
       iro.setDisplayName("§iIron Setting");
       cal.setDisplayName("§8Coal Setting");

       diamond.setItemMeta(dia);
       gold.setItemMeta(gld);
       iron.setItemMeta(iro);
       coal.setItemMeta(cal);

        p.openInventory(gui);
        return true;
    }
    
}
