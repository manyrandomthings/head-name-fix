package headfix.mixins;

import com.google.gson.JsonObject;
import headfix.HeadFix;
import net.minecraft.loot.LootManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

// run before fabric api loot events
@Mixin(value = LootManager.class, priority = 999)
public abstract class LootManagerMixin {
    @Inject(method = "apply", at = @At("TAIL"))
    private void apply(Map<Identifier, JsonObject> jsonMap, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        HeadFix.findHeadLootTables();
    }
}
