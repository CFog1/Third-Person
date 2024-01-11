package net.leawind.mc.thirdperson.fabric.config;


import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.leawind.mc.thirdperson.config.ConfigManager;

/**
 * Modmenu 入口
 */
@SuppressWarnings("unused")
public class ModmenuEntry implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory () {
		return ConfigManager.get()::buildConfigScreen;
	}
}
