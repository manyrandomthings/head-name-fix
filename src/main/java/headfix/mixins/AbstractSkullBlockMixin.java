package headfix.mixins;

import headfix.SetableNameable;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSkullBlock.class)
public abstract class AbstractSkullBlockMixin extends BlockMixin {
    @Override
    protected void onPlacedTailInject(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        // check if item has a name when being placed
        if(!world.isClient && itemStack.hasCustomName()) {
            // get block entity at placed location and check if block entity is a head block entity
            if(world.getBlockEntity(pos) instanceof SkullBlockEntity skullBlockEntity) {
                // set custom name of head to the name of the item being placed
                ((SetableNameable) skullBlockEntity).setCustomName(itemStack.getName());
            }
        }
    }
}
