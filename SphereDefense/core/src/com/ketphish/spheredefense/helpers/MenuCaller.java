package com.ketphish.spheredefense.helpers;

import com.badlogic.gdx.math.Vector2;

public final class MenuCaller {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private String name;
	private final Vector2 center;
	
	// Constructors --------------------------------------
	public MenuCaller() {
		center = new Vector2();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector2 getCenter() {
		return center;
	}

}
