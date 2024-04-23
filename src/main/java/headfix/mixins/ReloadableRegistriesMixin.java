package headfix.mixins;

import com.google.gson.JsonElement;
import headfix.HeadFix;
import net.minecraft.loot.LootDataType;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.ReloadableRegistries;
import net.minecraft.resource.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// run before fabric api loot events
@Mixin(value = ReloadableRegistries.class, priority = 999)
public abstract class ReloadableRegistriesMixin {
    @Inject(method = "method_58279", at = @At(value = "INVOKE", target = "Ljava/util/Map;forEach(Ljava/util/function/BiConsumer;)V"))
    private static void findTables(LootDataType<?> type, ResourceManager resourceManager, RegistryOps<JsonElement> ops, CallbackInfoReturnable<MutableRegistry<?>> cir) {
        HeadFix.findHeadLootTables();
    }
}
