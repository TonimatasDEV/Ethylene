package dev.photoncore.server.events;

import dev.photoncore.server.Main;
import dev.photoncore.server.init.Levels;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
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
        
        handler.addListener(PlayerBlockInteractEvent.class, event -> {
            Block blockInHand = event.getPlayer().getItemInHand(event.getHand()).material().block();
            
            if (blockInHand != null) {
                Point relativePoint = event.getBlockPosition().relative(event.getBlockFace());
                
                if (event.getCursorPosition().y() == 0.5) {
                    relativePoint = event.getBlockPosition();
                    event.setCancelled(true);
                }
                
                Block relativeBlock = event.getInstance().getBlock(relativePoint);

                if (relativeBlock.compare(blockInHand) && blockInHand.properties().get("type").equals("bottom")) {
                    event.getInstance().setBlock(relativePoint, relativeBlock.withProperty("type", "double"), true);
                }
            }
        });
        
        handler.addListener(PlayerBlockPlaceEvent.class, event -> {
            Block block = event.getBlock();
            BlockHandler blockHandler = MinecraftServer.getBlockManager().getHandler(block.namespace().toString());

            if (event.getCursorPosition().y() == 0.5) {
                Point clickedPoint = event.getBlockPosition().relative(event.getBlockFace().getOppositeFace());
                Block clickedBlock = event.getInstance().getBlock(clickedPoint);

                if (clickedBlock.compare(block) && !clickedBlock.properties().get("type").equals("double")) {
                    event.getInstance().setBlock(event.getBlockPosition(), block.withProperty("type", "double"), true);
                    event.setCancelled(true);
                    return;
                }
            }
            
            
            if (blockHandler != null) {
                event.setBlock(block.withHandler(blockHandler));
            }
        });
    }
}
