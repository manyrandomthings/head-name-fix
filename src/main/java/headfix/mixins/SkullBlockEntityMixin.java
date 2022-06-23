package headfix.mixins;

import headfix.SetableNameable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkullBlockEntity.class)
public abstract class SkullBlockEntityMixin extends BlockEntity implements SetableNameable {
    private static final String CUSTOM_NAME_TAG = "CustomName";
    private Text customName;

    public SkullBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    // for saving CustomName to nbt from SkullBlockEntity object
    @Inject(method = "writeNbt", at = @At("TAIL"))
    private void addCustomNameToNBT(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        if(this.customName != null) {
            // saves name to nbt tag
            nbt.putString(CUSTOM_NAME_TAG, Text.Serializer.toJson(this.customName));
        }
    }

    // for loading CustomName from nbt to SkullBlockEntity object
    @Inject(method = "readNbt", at = @At("TAIL"))
    private void getCustomNameFromNBT(NbtCompound tag, CallbackInfo ci) {
        if(tag.contains(CUSTOM_NAME_TAG, NbtElement.STRING_TYPE)) {
            // loads name from nbt tag
            this.customName = Text.Serializer.fromJson(tag.getString(CUSTOM_NAME_TAG));
        }
    }

    @Override
    public void setCustomName(Text customName) {
        this.customName = customName;
    }

    @Override
    public Text getCustomName() {
        return this.customName;
    }

    @Override
    public Text getName() {
        // return custom name if exists
        if(this.customName != null)  {
            return this.customName;
        }

        // return block name
        return this.getCachedState().getBlock().getName();
    }
}
