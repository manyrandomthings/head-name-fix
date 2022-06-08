package headfix;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.loot.function.CopyNameLootFunction;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class HeadFix implements ModInitializer {
    public static final Set<Identifier> HEAD_LOOT_TABLES = new HashSet<>(Set.of(
        Blocks.CREEPER_HEAD.getLootTableId(),
        Blocks.DRAGON_HEAD.getLootTableId(),
        Blocks.PLAYER_HEAD.getLootTableId(),
        Blocks.SKELETON_SKULL.getLootTableId(),
        Blocks.WITHER_SKELETON_SKULL.getLootTableId(),
        Blocks.ZOMBIE_HEAD.getLootTableId()
    ));

    @Override
    public void onInitialize() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            // check if head loot table
            if(HEAD_LOOT_TABLES.contains(id)) {
                // add copy name function
                tableBuilder.apply(CopyNameLootFunction.builder(CopyNameLootFunction.Source.BLOCK_ENTITY).build());
            }
        });
    }
}
