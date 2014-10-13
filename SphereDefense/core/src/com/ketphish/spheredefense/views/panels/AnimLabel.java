package com.ketphish.spheredefense.views.panels;

import com.ketphish.spheredefense.managers.Drawer;

public class AnimLabel
extends Label {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private int spriteIndex;
	private float time;
	
	// Constructors --------------------------------------
	public AnimLabel() {
		spriteIndex = 0;
		time = 0;
	}
	
	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) {
		if (isVisible) {
			drawer.draw(textureName, rectangle, spriteIndex, time);
		}
	}
	
	@Override
	public AnimLabel clone() throws CloneNotSupportedException {
		final AnimLabel clone = (AnimLabel) super.clone();
		return clone;
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
	
	public void setTime(float time) {
		this.time = time;
	}
	
}
