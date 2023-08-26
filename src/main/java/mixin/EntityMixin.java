package mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.world.level.Level;

import java.io.File;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow
    public Level level;

    public void setLevel(Level level) {
        this.level = level;
    }

}