package net.ethylene.server.events;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.utils.time.TimeUnit;

public class ItemEvents {
    public static void init(GlobalEventHandler handler) {
        handler.addListener(ItemDropEvent.class, event -> {
            ItemEntity itemEntity = new ItemEntity(event.getItemStack());
            Pos playePos = event.getPlayer().getPosition();
            double eyeHeight = event.getPlayer().getEyeHeight() - (double) 0.3F;
            Vec velocity = event.getPlayer().getPosition().direction().mul(6);

            itemEntity.setPickupDelay(40, TimeUnit.SERVER_TICK);
            itemEntity.setMergeable(true);
            itemEntity.setMergeRange(1);
            itemEntity.setVelocity(velocity);
            itemEntity.setInstance(event.getPlayer().getInstance(), new Pos(playePos.x(), eyeHeight + playePos.y(), playePos.z()));
        });

        handler.addListener(PickupItemEvent.class, event -> {
            if (event.getEntity() instanceof Player player) {
                player.getInventory().addItemStack(event.getItemStack());
            }
        });
    }
}
