package net.leawind.mc.thirdperson.api.core;


import net.leawind.mc.thirdperson.api.core.rotation.SmoothType;
import net.leawind.mc.thirdperson.impl.core.EntityAgentImpl;
import net.leawind.mc.thirdperson.impl.core.rotation.RotateTarget;
import net.leawind.mc.util.math.vector.api.Vector2d;
import net.leawind.mc.util.math.vector.api.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 相机附着的实体
 * <p>
 * 当操控玩家时，玩家的旋转由本类接管。
 */
public interface EntityAgent {
	@Contract("_ -> new")
	static @NotNull EntityAgent create (@NotNull Minecraft mc) {
		return new EntityAgentImpl(mc);
	}

	/**
	 * 相机实体 {@link Minecraft#cameraEntity} 是否已经存在
	 */
	boolean isCameraEntityExist ();

	/**
	 * 重置各种属性
	 * <p>
	 * 当初始化或进入第三人称时调用
	 */
	void reset ();

	/**
	 * 设置旋转目标
	 */
	void setRotateTarget (@NotNull RotateTarget rotateTarget);

	/**
	 * 设置平滑类型
	 * <p>
	 * 在 clientTick 和 renderTick 中要根据平滑类型采用不同的处理方式
	 */
	void setSmoothRotationType (@NotNull SmoothType smoothType);

	/**
	 * 设置平滑转向的半衰期
	 */
	void setSmoothRotationHalflife (double halflife);

	/**
	 * @param period 相邻两次 render tick 的时间差，单位：s
	 */
	void onRenderTickPre (double period, float partialTick);

	/**
	 * 在 client tick 之前
	 * <p>
	 * 通常频率固定为 20Hz
	 */
	void onClientTickPre ();

	/**
	 * 玩家当前是否在操控这个实体
	 */
	boolean isControlled ();

	/**
	 * 获取相机附着的实体
	 */
	@NotNull Entity getRawCameraEntity ();

	/**
	 * 获取玩家实体
	 */
	@NotNull LocalPlayer getRawPlayerEntity ();

	/**
	 * 直接从实体获取眼睛坐标
	 */
	@NotNull Vector3d getRawEyePosition (float partialTick);

	/**
	 * 直接从实体获取坐标
	 */
	@NotNull Vector3d getRawPosition (float partialTick);

	/**
	 * 直接从实体获取朝向
	 */
	@NotNull Vector2d getRawRotation (float partialTick);

	/**
	 * 获取平滑的眼睛坐标
	 */
	@NotNull Vector3d getSmoothEyePosition (float partialTick);

	/**
	 * 如果平滑系数为0，则返回完全不平滑的值
	 * <p>
	 * 如果平滑系数不为0，则采用 EXP_LINEAR 平滑
	 */
	@NotNull Vector3d getPossiblySmoothEyePosition (float partialTick);

	/**
	 * 实体是否在交互
	 * <p>
	 * 当控制玩家时，相当于是否按下了 使用|攻击|选取 键
	 * <p>
	 * 当附身其他实体时，另做判断
	 */
	boolean isInterecting ();

	/**
	 * 实体是否在飞行
	 */
	boolean isFallFlying ();

	/**
	 * 根据实体的手持物品和使用状态判断是否在瞄准
	 * <p>
	 * 不考虑玩家按键
	 */
	boolean isAiming ();

	/**
	 * 在上一个 clientTick 中是否在瞄准
	 */
	boolean wasAiming ();

	/**
	 * 在上一个 clientTick 中是否在交互
	 */
	boolean wasInterecting ();
}
