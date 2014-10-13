package com.ketphish.spheredefense.views.panels.menus;

import com.badlogic.gdx.math.Rectangle;

public final class TowerCylinderLightMenu
extends TowerMenu {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	
	// Constructors --------------------------------------
	public TowerCylinderLightMenu() {
		super();
	}
	
	public TowerCylinderLightMenu(String name, String parentName, 
			String textureName, Rectangle rectangle, 
			float zIndex, boolean isVisible) {
		super(name, parentName, textureName, rectangle, zIndex, isVisible);
	}
	
	// Extends -------------------------------------------
	@Override
	public void updatePosition(Rectangle rect) {
		super.updatePosition(rect);
		updateButtonPositions();
	}
	
	@Override
	protected void updateButtonPositions() {
		buttons.get("btn_build_cylinder_heavy").getRectangle().x = rectangle.x + BUTTON_OFFSET;
		buttons.get("btn_build_cylinder_heavy").getRectangle().y = rectangle.y + buttons.get("btn_build_cylinder_heavy").getRectangle().height + BUTTON_OFFSET * 2;
		buttons.get("btn_sale_cylinder_light").getRectangle().x = rectangle.x + BUTTON_OFFSET;
		buttons.get("btn_sale_cylinder_light").getRectangle().y = rectangle.y + BUTTON_OFFSET;
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	
	// Properties ----------------------------------------
}
