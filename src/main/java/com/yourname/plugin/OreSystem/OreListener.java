package com.yourname.plugin.OreSystem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class OreListener implements Listener {

    public Set<UUID> waitingDescripion = new HashSet<>();

    public int DiamondChance = 0;
    public String DiamondDescription = "";

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        String title = event.getView().getTitle();

        if (title.equals("OreMain")) {

            event.setCancelled(true);

            if (event.getCurrentItem() == null)
                return;

            Inventory diamondGui = Bukkit.createInventory(null, 27, "Diamond Setting");

            Player player = (Player) event.getWhoClicked();

            // Clock
            ItemStack diachen = new ItemStack(Material.CLOCK);
            ItemMeta chanceMeta = diachen.getItemMeta();
            chanceMeta.displayName(Component.text("Diamond Chance: " + DiamondChance));
            diachen.setItemMeta(chanceMeta);

            // Description
            ItemStack description = new ItemStack(Material.INK_SAC);
            ItemMeta descMeta = description.getItemMeta();
            descMeta.displayName(Component.text("Description"));
            descMeta.lore(List.of(
                    Component.text(DiamondDescription)
            ));
            description.setItemMeta(descMeta);

            diamondGui.setItem(12, diachen);
            diamondGui.setItem(14, description);

            player.openInventory(diamondGui);
        }

        if (title.equals("Diamond Setting")) {

            event.setCancelled(true);

            if (event.getCurrentItem() == null)
                return;

            // Chance
            if (event.getCurrentItem().getType() == Material.CLOCK) {

                DiamondChance += 10;

                if (DiamondChance > 100) {
                    DiamondChance = 0;
                }

                ItemStack item = event.getCurrentItem();
                ItemMeta meta = item.getItemMeta();

                meta.displayName(Component.text("Diamond Chance: " + DiamondChance));

                item.setItemMeta(meta);
            }

            // Description
            if (event.getCurrentItem().getType() == Material.INK_SAC) {

                Player player = (Player) event.getWhoClicked();

                waitingDescripion.add(player.getUniqueId());

                player.closeInventory();

                player.sendMessage("§eEnter Description...");
            }
        }
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {

        Player player = event.getPlayer();

        if (!waitingDescripion.contains(player.getUniqueId())) {
            return;
        }

        event.setCancelled(true);

        DiamondDescription = PlainTextComponentSerializer.plainText()
                .serialize(event.message());

        waitingDescripion.remove(player.getUniqueId());

        player.sendMessage("§aDescription Saved!");
    }
}