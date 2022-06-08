package headfix;

import net.minecraft.text.Text;
import net.minecraft.util.Nameable;

public interface SetableNameable extends Nameable {
    // Allows customName to be set
    void setCustomName(Text customName);
}
