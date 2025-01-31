package net.leawind.mc.thirdperson.mixin;


import net.leawind.mc.thirdperson.ThirdPerson;
import net.leawind.mc.thirdperson.ThirdPersonEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * handleKeybinds 方法中会处理各种按键事件， 其中包括鼠标使用、攻击、选取按键
 * <p>
 * 在这之前，要立即调用 gameRender.pick 方法来更新玩家注视着的目标 (minecraft.hitResult)
 * <p>
 * 这个 pick 方法也被使用 mixin 修改了 pick 的方向，使玩家朝向相机准星的落点。
 */
@Mixin(value=net.minecraft.client.Minecraft.class, priority=2000)
public class MinecraftMixin {
	/**
	 * 注入到 handleKeybinds 头部，触发相应事件
	 */
	@Inject(method="handleKeybinds", at=@At(value="HEAD"))
	public void handleKeybinds_head (CallbackInfo ci) {
		if (ThirdPerson.isAvailable() && ThirdPerson.isThirdPerson()) {
			ThirdPersonEvents.onBeforeHandleKeybinds();
		}
	}
}
