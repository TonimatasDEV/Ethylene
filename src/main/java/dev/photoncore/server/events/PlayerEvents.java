package dev.photoncore.server.events;

import dev.photoncore.server.Main;
import dev.photoncore.server.init.Levels;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerBlockPlaceEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;

public class PlayerEvents {
    public static void init(GlobalEventHandler handler) {
        handler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            if (Main.stopping) {
                event.getPlayer().kick("The server is stopping.");
                return;
            }
            
            Player player = event.getPlayer();
            event.setSpawningInstance(Levels.OVERWORLD);
            player.setRespawnPoint(new Pos(0, 42, 0));
            player.setSkin(PlayerSkin.fromUsername(player.getUsername()));
            player.setGameMode(GameMode.CREATIVE);
        });
        
        handler.addListener(PlayerBlockPlaceEvent.class, event -> {
            Block block = event.getBlock();
            BlockHandler blockHandler = MinecraftServer.getBlockManager().getHandler(block.namespace().toString());
            
            if (blockHandler != null) {
                event.setBlock(block.withHandler(blockHandler));
            }
        });
    }
}
