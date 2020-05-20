package headfix.mixins;

import org.spongepowered.asm.mixin.Mixin;

import headfix.SkullBlockEntityAccessor;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(AbstractSkullBlock.class)
public abstract class AbstractSkullBlockMixin extends BlockWithEntity {
    protected AbstractSkullBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        // check if item has a name when being placed
        if(itemStack.hasCustomName()) {
            // get block entity at placed location
            BlockEntity blockEntity = world.getBlockEntity(pos);
            // check if block entity is a head block entity
            if(blockEntity instanceof SkullBlockEntity) {
                // set custom name of head to the name of the item being placed
                ((SkullBlockEntityAccessor) blockEntity).setCustomName(itemStack.getName());
            }
        }
    }
}
