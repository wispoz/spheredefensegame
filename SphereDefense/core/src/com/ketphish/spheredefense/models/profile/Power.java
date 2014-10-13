package com.ketphish.spheredefense.models.profile;

public final class Power
extends Unit {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	protected final int cost;
	
	// Constructors --------------------------------------
	public Power(int id, String name, int cost) {
		super(id, name);
		this.cost = cost;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public int getCost() {
		return cost;
	}
	
}
