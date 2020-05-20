package headfix;

import net.minecraft.text.Text;

public interface SkullBlockEntityAccessor {
    // Allows customName to be get/set
    void setCustomName(Text customName);

    Text getCustomName();
}
