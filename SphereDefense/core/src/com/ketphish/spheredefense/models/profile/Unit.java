package com.ketphish.spheredefense.models.profile;

import com.ketphish.spheredefense.models.Entity;

public class Unit
extends Entity {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	protected final String name;
	
	// Constructors --------------------------------------
	public Unit(int id, String name) {
		super(id);
		this.name = name;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public String getName() {
		return name;
	}
	
}
