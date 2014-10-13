package com.ketphish.spheredefense.views.panels.menus;

import com.badlogic.gdx.math.Rectangle;

public final class TowerConeHeavyMenu
extends TowerMenu {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	
	// Constructors --------------------------------------
	public TowerConeHeavyMenu() {
		super();
	}
	
	public TowerConeHeavyMenu(String name, String parentName, 
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
		buttons.get("btn_sale_cone_heavy").getRectangle().x = rectangle.x + BUTTON_OFFSET;
		buttons.get("btn_sale_cone_heavy").getRectangle().y = rectangle.y + BUTTON_OFFSET;
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	
	// Properties ----------------------------------------
}
