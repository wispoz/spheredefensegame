package com.ketphish.spheredefense.models.profile;

import com.ketphish.spheredefense.models.Entity;

public final class Trophy
extends Entity {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final int level;
	private int starsAmount;
	
	// Constructors --------------------------------------
	public Trophy(int id, int level, int starsAmount) {
		super(id);
		this.level = level;
		this.starsAmount = starsAmount;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public int getLevel() {
		return level;
	}
	
	public int getStarsAmount() {
		return starsAmount;
	}
	
	public void setStarsAmount(int starsAmount) {
		this.starsAmount = starsAmount;
	}
	
}
