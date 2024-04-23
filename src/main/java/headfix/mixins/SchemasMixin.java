package headfix.mixins;

import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import headfix.datafixer.HeadCustomNameFix;
import net.minecraft.datafixer.Schemas;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiFunction;

@Mixin(Schemas.class)
public abstract class SchemasMixin {
    @Shadow @Final private static BiFunction<Integer, Schema, Schema> EMPTY_IDENTIFIER_NORMALIZE;

    @Inject(method = "build", at = @At("TAIL"))
    private static void addCustomNameFix(DataFixerBuilder builder, CallbackInfo ci) {
        builder.addFixer(new HeadCustomNameFix(builder.addSchema(3820, 1, EMPTY_IDENTIFIER_NORMALIZE)));
    }
}
