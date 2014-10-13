package com.ketphish.spheredefense.models;

public abstract class Entity {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	protected final int id;
	
	// Constructors --------------------------------------
	public Entity(int id) {
		this.id = id;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public int getId() {
		return id;
	}
	
}
