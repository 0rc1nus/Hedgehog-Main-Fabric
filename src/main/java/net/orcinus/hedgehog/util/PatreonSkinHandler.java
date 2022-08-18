package net.orcinus.hedgehog.util;

import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.orcinus.hedgehog.Hedgehog;

import java.util.Map;

public class PatreonSkinHandler {

    private static final Map<String, String> NAMES_TO_TEXTURE = Util.make(Maps.newHashMap(), map -> {
        map.put("SpeedBoy", "speed_consumer");
        map.put("Zefiro", "zefiro");
        map.put("JDC", "jdc");
        map.put("LaJidece", "jdc");
        map.put("Erinaceidus Jidecus", "jdc");
    });

    public static boolean matchesString(String name) {
        for (String id : NAMES_TO_TEXTURE.keySet()) {
            if (!id.equals(name)) continue;
            return true;
        }
        return false;
    }

    public static Identifier getTexture(String name) {
        Identifier id = null;
        for (String texture : NAMES_TO_TEXTURE.keySet()) {
            if (!name.equals(texture)) continue;
            id = new Identifier(Hedgehog.MODID, "textures/entity/" + NAMES_TO_TEXTURE.get(texture) + "_hedgehog.png");
        }
        return id;
    }

    public static Identifier getScaredTexture(String name) {
        Identifier id = null;
        for (String texture : NAMES_TO_TEXTURE.keySet()) {
            if (!name.equals(texture)) continue;
            id = new Identifier(Hedgehog.MODID, "textures/entity/scared_" + NAMES_TO_TEXTURE.get(texture) + "_hedgehog.png");
        }
        return id;
    }


}
