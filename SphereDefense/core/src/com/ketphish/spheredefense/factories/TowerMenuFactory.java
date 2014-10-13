package com.ketphish.spheredefense.factories;

import com.ketphish.spheredefense.views.panels.menus.BuildTowerLightMenu;
import com.ketphish.spheredefense.views.panels.menus.TowerConeHeavyMenu;
import com.ketphish.spheredefense.views.panels.menus.TowerConeLightMenu;
import com.ketphish.spheredefense.views.panels.menus.TowerCubeHeavyMenu;
import com.ketphish.spheredefense.views.panels.menus.TowerCubeLightMenu;
import com.ketphish.spheredefense.views.panels.menus.TowerCylinderHeavyMenu;
import com.ketphish.spheredefense.views.panels.menus.TowerCylinderLightMenu;
import com.ketphish.spheredefense.views.panels.menus.TowerMenu;
import com.ketphish.spheredefense.views.panels.menus.TowerPyramidHeavyMenu;
import com.ketphish.spheredefense.views.panels.menus.TowerPyramidLightMenu;

public final class TowerMenuFactory {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------

	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public TowerMenu getTowerMenu(String name) {
		if (name.equals("build_tower_light_menu")) {
			return new BuildTowerLightMenu();
		} else if (name.equals("tower_cone_light_menu")) {
			return new TowerConeLightMenu();
		} else if (name.equals("tower_pyramid_light_menu")) {
			return new TowerPyramidLightMenu();
		} else if (name.equals("tower_cube_light_menu")) {
			return new TowerCubeLightMenu();
		} else if (name.equals("tower_cylinder_light_menu")) {
			return new TowerCylinderLightMenu();
		} else if (name.equals("tower_cone_heavy_menu")) {
			return new TowerConeHeavyMenu();
		} else if (name.equals("tower_pyramid_heavy_menu")) {
			return new TowerPyramidHeavyMenu();
		} else if (name.equals("tower_cube_heavy_menu")) {
			return new TowerCubeHeavyMenu();
		} else if (name.equals("tower_cylinder_heavy_menu")) {
			return new TowerCylinderHeavyMenu();
		}
		return null;
	}
	
	// Properties ----------------------------------------
}
