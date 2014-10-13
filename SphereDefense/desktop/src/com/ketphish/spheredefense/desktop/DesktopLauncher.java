package com.ketphish.spheredefense.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ketphish.spheredefense.SphereDefenseGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Sphere Defense";   
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new SphereDefenseGame(), config);
	}
}
