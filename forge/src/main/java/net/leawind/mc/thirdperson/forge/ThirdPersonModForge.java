package net.leawind.mc.thirdperson.forge;


import dev.architectury.platform.forge.EventBuses;
import net.leawind.mc.thirdperson.ThirdPersonMod;
import net.leawind.mc.thirdperson.config.ConfigManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ThirdPersonMod.MOD_ID)
public class ThirdPersonModForge {
	public ThirdPersonModForge () {
		// 仅在客户端运行
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			EventBuses.registerModEventBus(ThirdPersonMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
			ThirdPersonMod.init();
			// 配置屏幕
			ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((mc, screen) -> ConfigManager.get().buildConfigScreen(screen)));
		});
	}
}
