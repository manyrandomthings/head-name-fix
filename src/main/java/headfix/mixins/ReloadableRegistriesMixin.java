package headfix.mixins;

import headfix.HeadFix;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.ReloadableRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// run before fabric api loot events
@Mixin(value = ReloadableRegistries.class, priority = 999)
public abstract class ReloadableRegistriesMixin {
    @Inject(method = "method_61240", at = @At(value = "INVOKE", target = "Ljava/util/Map;forEach(Ljava/util/function/BiConsumer;)V"))
    private static void findTables(CallbackInfoReturnable<MutableRegistry<?>> cir) {
        HeadFix.findHeadLootTables();
    }
}
