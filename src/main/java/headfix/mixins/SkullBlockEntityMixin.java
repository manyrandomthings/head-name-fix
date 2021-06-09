package headfix.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import headfix.SkullBlockEntityAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;

@Mixin(SkullBlockEntity.class)
public abstract class SkullBlockEntityMixin extends BlockEntity implements Nameable, SkullBlockEntityAccessor {
    private Text customName;

    public SkullBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    // for saving CustomName to nbt from SkullBlockEntity object
    @Inject(method = "writeNbt", at = @At("TAIL"))
    private void addCustomNameToNBT(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        if(this.customName != null) {
            // saves name to nbt tag
            nbt.putString("CustomName", Text.Serializer.toJson(this.customName));
        }
    }

    // for loading CustomName from nbt to SkullBlockEntity object
    @Inject(method = "readNbt", at = @At("TAIL"))
    private void getCustomNameFromNBT(NbtCompound tag, CallbackInfo ci) {
        if(tag.contains("CustomName", 8)) {
            // loads name from nbt tag
            this.customName = Text.Serializer.fromJson(tag.getString("CustomName"));
        }
    }

    public void setCustomName(Text customName) {
        this.customName = customName;
    }

    // both required for Nameable
    public Text getCustomName() {
        return this.customName;
    }

    public Text getDisplayName() {
        return this.customName;
    }
}
