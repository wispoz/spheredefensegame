package com.ketphish.spheredefense.views.panels.menus;

import com.badlogic.gdx.math.Rectangle;

public final class TowerPyramidLightMenu
extends TowerMenu {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	
	// Constructors --------------------------------------
	public TowerPyramidLightMenu() {
		super();
	}
	
	public TowerPyramidLightMenu(String name, String parentName, 
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
		buttons.get("btn_build_pyramid_heavy").getRectangle().x = rectangle.x + BUTTON_OFFSET;
		buttons.get("btn_build_pyramid_heavy").getRectangle().y = rectangle.y + buttons.get("btn_build_pyramid_heavy").getRectangle().height + BUTTON_OFFSET * 2;
		buttons.get("btn_sale_pyramid_light").getRectangle().x = rectangle.x + BUTTON_OFFSET;
		buttons.get("btn_sale_pyramid_light").getRectangle().y = rectangle.y + BUTTON_OFFSET;
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	
	// Properties ----------------------------------------
}
