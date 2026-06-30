package com.yourname.plugin.OreSystem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;

public class OreListener implements Listener {
    public int DiamondChance = 10;

    @EventHandler
    public void onClick(InventoryClickEvent event){

        if(!(event.getView().getTitle().equals("OreMain"))){
            return;
        }

        event.setCancelled(true);

        if(event.getCurrentItem() == null){
            return;
        }

        Material type = event.getCurrentItem().getType();

        if(type == Material.DIAMOND_BLOCK){
            Inventory Diamondgui = Bukkit.createInventory(null, 27, "Diamond Setting");
            Player player = (Player) event.getWhoClicked();
            ItemStack diachen = new ItemStack(Material.CLOCK);

            ItemMeta meta = diachen.getItemMeta();
            meta.displayName(Component.text("Diamond Chance: " + DiamondChance));
            diachen.setItemMeta(meta);

            Diamondgui.setItem(12, diachen);

            player.openInventory(Diamondgui);

        }


    }
}
