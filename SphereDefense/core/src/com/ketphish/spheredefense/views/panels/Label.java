package com.ketphish.spheredefense.views.panels;

import com.badlogic.gdx.math.Rectangle;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.views.Panel;

public class Label
extends Panel {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	protected String textureName;
	
	// Constructors --------------------------------------
	public Label() {
		super();
	}
	
	public Label(String name, String parentName,
			String textureName, Rectangle rectangle, 
			float zIndex, boolean isVisible) {
		super(name, parentName, rectangle, zIndex, isVisible);
		this.textureName = textureName;
	}
	
	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) {
		if (isVisible) {
			drawer.draw(textureName, rectangle);
		}
	}
	
	// Implementations -----------------------------------
	@Override
	public Label clone() throws CloneNotSupportedException {
		Label clone = (Label) super.clone();
		return clone;
	}
	
	// Methods -------------------------------------------
	
	// Properties ----------------------------------------
	public String getTextureName() {
		return textureName;
	}

	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}
	
}
