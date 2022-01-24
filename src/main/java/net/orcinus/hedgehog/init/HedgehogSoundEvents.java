package net.orcinus.hedgehog.init;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.hedgehog.Hedgehog;

public class HedgehogSoundEvents {
    public static final SoundEvent ENTITY_HEDGEHOG_AMBIENT = hedgehog("ambient");
    public static final SoundEvent ENTITY_HEDGEHOG_SCARED = hedgehog("scared");
    public static final SoundEvent ENTITY_HEDGEHOG_HURT = hedgehog("hurt");
    public static final SoundEvent ENTITY_HEDGEHOG_DEATH = hedgehog("death");
    public static final SoundEvent ENTITY_HEDGEHOG_EATING = hedgehog("eating");
    private static SoundEvent hedgehog(String id) {
        return entity("hedgehog", id);
    }

    private static SoundEvent entity(String entity, String id) {
        return register("entity.%s.%s".formatted(entity, id));
    }

    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(Hedgehog.MODID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }
}
