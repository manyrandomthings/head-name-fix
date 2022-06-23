package headfix;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.Block;
import net.minecraft.loot.function.CopyNameLootFunction;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class HeadFix implements ModInitializer {
    public static final Set<Identifier> HEAD_LOOT_TABLES = new HashSet<>();

    @Override
    public void onInitialize() {
        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            // check if head loot table
            if(HEAD_LOOT_TABLES.contains(id)) {
                // add copy name function
                supplier.withFunction(CopyNameLootFunction.builder(CopyNameLootFunction.Source.BLOCK_ENTITY).build());
            }
        });
    }

    // gets loot tables of all skull blocks
    public static void findHeadLootTables() {
        // clear existing items in set
        HEAD_LOOT_TABLES.clear();

        // loop through all blocks
        for(Block block : Registry.BLOCK) {
            // find blocks that are AbstractSkullBlock
            if(block instanceof AbstractSkullBlock) {
                // add to loot tables set
                HEAD_LOOT_TABLES.add(block.getLootTableId());
            }
        }
    }
}
