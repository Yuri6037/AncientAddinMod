package net.yuri6037.AncientAddinMod.event;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyRegistry {

    public static final KeyBinding heavyHelmetZoomKey;
    public static final KeyBinding heavyArmorPresurizeToggleKey;
    public static final KeyBinding heavyArmorLighToggleKey;
    public static final KeyBinding heavyArmorTotalScreenToggleKey;

    public static KeyBinding registerKeyBinding(String desc, int defaultKey){
        KeyBinding bind = new KeyBinding("key.ancientAddin." + desc + ".desc" , defaultKey, "key.ancientAddin.category");
        ClientRegistry.registerKeyBinding(bind);
        return bind;
    }

    static {
        heavyHelmetZoomKey = registerKeyBinding("heavyHelmetZoom", Keyboard.KEY_LSHIFT);
        heavyArmorPresurizeToggleKey = registerKeyBinding("heavyArmorPresurize", Keyboard.KEY_X);
        heavyArmorLighToggleKey = registerKeyBinding("heavyArmorLigh", Keyboard.KEY_F);
        heavyArmorTotalScreenToggleKey = registerKeyBinding("heavyArmorTotalScreen", Keyboard.KEY_R);
    }
}
