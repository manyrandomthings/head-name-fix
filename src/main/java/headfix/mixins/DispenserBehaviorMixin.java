package headfix.mixins;

import headfix.SetableNameable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$15")
public abstract class DispenserBehaviorMixin {
    @Inject(
        method = "dispenseSilently(Lnet/minecraft/util/math/BlockPointer;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/WitherSkullBlock;onPlaced(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/SkullBlockEntity;)V"
        ),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void addNameToDispensed(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir, World world, Direction direction, BlockPos blockPos, BlockEntity blockEntity) {
        if(stack.hasCustomName()) {
            ((SetableNameable) blockEntity).setCustomName(stack.getName());
        }
    }
}
