package net.leawind.mc.thirdperson.fabric;


import net.fabricmc.api.ClientModInitializer;
import net.leawind.mc.thirdperson.ThirdPerson;

@SuppressWarnings("unused")
public class ThirdPersonFabric implements ClientModInitializer {
	public void onInitializeClient () {
		ThirdPerson.init();
	}
}
