package net.ethylene.server.init;

import org.everbuild.blocksandstuff.blocks.BlockBehaviorRuleRegistrations;
import org.everbuild.blocksandstuff.blocks.BlockPlacementRuleRegistrations;
import org.everbuild.blocksandstuff.blocks.PlacedHandlerRegistration;
import org.everbuild.blocksandstuff.fluids.MinestomFluids;

public class Blocks {
    public static void init() {
        BlockPlacementRuleRegistrations.registerDefault();
        BlockBehaviorRuleRegistrations.registerDefault();
        PlacedHandlerRegistration.registerDefault();
        MinestomFluids.enableFluids();
        MinestomFluids.enableVanillaFluids();
    }
}
