package net.ethylene.server.listeners;

import net.ethylene.server.init.Levels;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.*;
import net.minestom.server.inventory.PlayerInventory;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class PlayerListeners {
    public static void init(GlobalEventHandler handler) {
        handler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            if (MinecraftServer.isStopping()) {
                event.getPlayer().kick("The server is stopping.");
                return;
            }

            Player player = event.getPlayer();
            event.setSpawningInstance(Levels.OVERWORLD);
            player.setRespawnPoint(new Pos(0, 42, 0));
            player.setSkin(PlayerSkin.fromUsername(player.getUsername()));
            player.setGameMode(GameMode.CREATIVE);
        });

        handler.addListener(AsyncPlayerPreLoginEvent.class, event -> Audiences.all().sendMessage(Component.text(event.getGameProfile().name() + " joined the game").style(Style.style(NamedTextColor.YELLOW))));
        handler.addListener(PlayerDisconnectEvent.class, event -> Audiences.all().sendMessage(Component.text(event.getPlayer().getUsername() + " left the game").style(Style.style(NamedTextColor.YELLOW))));
        
        handler.addListener(PlayerPickBlockEvent.class, event -> {
            Material material = event.getBlock().registry().material();

            if (material == null) {
                return;
            }

            Player player = event.getPlayer();
            ItemStack itemStack = ItemStack.of(material);
            PlayerInventory inventory = event.getPlayer().getInventory();

            for (int slot = 0; slot < 9; slot++) {
                ItemStack item = inventory.getItemStack(slot);
                if (item.material() == material) {
                    player.setHeldItemSlot((byte) slot);
                    return;
                }
            }

            if (event.getPlayer().getItemInMainHand().isAir()) {
                event.getPlayer().setItemInMainHand(itemStack);
                return;
            }

            for (int slot = 0; slot < 9; slot++) {
                ItemStack item = inventory.getItemStack(slot);
                if (item.isAir()) {
                    player.setHeldItemSlot((byte) slot);
                    inventory.setItemStack(slot, itemStack);
                    return;
                }
            }
            
            // TODO: Full hotbar things
            player.setItemInMainHand(itemStack);
        });
    }
}
