package headfix.mixins;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
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
        method = "dispenseSilently",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;getBlockEntity(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/entity/BlockEntity;"
        ),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void addNameToDispensed(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir, World world, Direction direction, BlockPos blockPos) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);
        if(blockEntity != null && blockEntity.getType() == BlockEntityType.SKULL) {
            blockEntity.readComponents(stack);
        }
    }
}
