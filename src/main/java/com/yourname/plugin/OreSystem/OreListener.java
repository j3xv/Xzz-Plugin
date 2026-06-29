package com.yourname.plugin.OreSystem;

import java.net.http.WebSocket.Listener;

import org.bukkit.event.inventory.InventoryClickEvent;

public class OreListener implements Listener {
    public void onEnable(InventoryClickEvent event){

        if(!(event.getView().getTitle().equals("OreMain"))){
            return;
        }

        event.setCancelled(true);


    }
}
