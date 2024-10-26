package dev.polariscore.server.events;

import dev.polariscore.server.init.Levels;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.*;
import net.minestom.server.gamedata.tags.Tag;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;

public class PlayerEvents {
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

        handler.addListener(PlayerBlockInteractEvent.class, event -> {
            Block blockInHand = event.getPlayer().getItemInHand(event.getHand()).material().block();

            // Slab start
            if (blockInHand != null) {
                Point relativePoint = event.getBlockPosition().relative(event.getBlockFace());

                if (event.getCursorPosition().y() == 0.5) {
                    relativePoint = event.getBlockPosition();
                    event.setCancelled(true);
                }

                Block relativeBlock = event.getInstance().getBlock(relativePoint);

                Tag tag = MinecraftServer.getTagManager().getTag(Tag.BasicType.BLOCKS, "minecraft:slabs");
                assert tag != null;

                if ((tag.contains(blockInHand.namespace()) && tag.contains(relativeBlock.namespace())) && relativeBlock.compare(blockInHand) && blockInHand.properties().get("type").equals("bottom")) {
                    event.getInstance().setBlock(relativePoint, relativeBlock.withProperty("type", "double"), true);
                }
            }
            // Slab end
        });

        handler.addListener(PlayerBlockPlaceEvent.class, event -> {
            Block block = event.getBlock();
            BlockHandler blockHandler = MinecraftServer.getBlockManager().getHandler(block.namespace().toString());

            // Slab start
            if (event.getCursorPosition().y() == 0.5) {
                Point clickedPoint = event.getBlockPosition().relative(event.getBlockFace().getOppositeFace());
                Block clickedBlock = event.getInstance().getBlock(clickedPoint);

                if (clickedBlock.compare(block) && !clickedBlock.properties().get("type").equals("double")) {
                    event.getInstance().setBlock(event.getBlockPosition(), block.withProperty("type", "double"), true);
                    event.setCancelled(true);
                    return;
                }
            }
            // Slab end

            if (blockHandler != null) {
                event.setBlock(block.withHandler(blockHandler));
            }
        });
    }
}
