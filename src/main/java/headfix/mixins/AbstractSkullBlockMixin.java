package headfix.mixins;

import headfix.SetableNameable;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BlockState;
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
        // check if server side, item has a name, and block entity is SetableNameable
        if(!world.isClient && itemStack.hasCustomName() && world.getBlockEntity(pos) instanceof SetableNameable setableNameable) {
            // set custom name of head to the name of the item being placed
            setableNameable.setCustomName(itemStack.getName());
        }
    }
}
