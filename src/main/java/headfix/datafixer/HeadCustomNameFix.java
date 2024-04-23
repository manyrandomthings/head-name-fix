package headfix.datafixer;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.datafixer.fix.ChoiceFix;

import java.util.Optional;

public class HeadCustomNameFix extends ChoiceFix {
    public HeadCustomNameFix(Schema outputSchema) {
        super(outputSchema, false, "CustomName fix for heads", TypeReferences.BLOCK_ENTITY, "minecraft:skull");
    }

    protected Typed<?> transform(Typed<?> inputType) {
        return inputType.update(DSL.remainderFinder(), this::fixCustomName);
    }

    private <T> Dynamic<T> fixCustomName(Dynamic<T> dynamic) {
        Optional<Dynamic<T>> optional = dynamic.get("CustomName").result();
        if(optional.isEmpty()) {
            return dynamic;
        }
        return dynamic.remove("CustomName").set("custom_name", optional.get());
    }
}

