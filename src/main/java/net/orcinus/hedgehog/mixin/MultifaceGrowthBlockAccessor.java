package net.orcinus.hedgehog.mixin;

import net.minecraft.block.MultifaceGrowthBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(MultifaceGrowthBlock.class)
public interface MultifaceGrowthBlockAccessor {
    @Accessor
    static Map<Direction, BooleanProperty> getFACING_PROPERTIES() {
        throw new UnsupportedOperationException();
    }
}
