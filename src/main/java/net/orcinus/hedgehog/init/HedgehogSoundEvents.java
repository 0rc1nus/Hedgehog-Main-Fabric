package net.orcinus.hedgehog.init;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.hedgehog.Hedgehog;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class HedgehogSoundEvents {
    private static final LinkedHashMap<Identifier, SoundEvent> SOUND_EVENTS = new LinkedHashMap<>();

    public static final SoundEvent ENTITY_HEDGEHOG_AMBIENT = registerSoundEvent("entity.hedgehog.ambient");
    public static final SoundEvent ENTITY_HEDGEHOG_SCARED = registerSoundEvent("entity.hedgehog.scared");
    public static final SoundEvent ENTITY_HEDGEHOG_HURT = registerSoundEvent("entity.hedgehog.hurt");
    public static final SoundEvent ENTITY_HEDGEHOG_DEATH = registerSoundEvent("entity.hedgehog.death");
    public static final SoundEvent ENTITY_HEDGEHOG_EATING = registerSoundEvent("entity.hedgehog.eating");

    public static SoundEvent registerSoundEvent(String name) {
        Identifier identifier = new Identifier(Hedgehog.MODID, name);
        SoundEvent soundEvent = new SoundEvent(identifier);
        SOUND_EVENTS.put(identifier, soundEvent);
        return soundEvent;
    }

    public static void init() {
        for (Identifier id : SOUND_EVENTS.keySet()) {
            Registry.register(Registry.SOUND_EVENT, id, SOUND_EVENTS.get(id));
        }
    }

}
