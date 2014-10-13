package com.ketphish.spheredefense.models.profile;

import com.ketphish.spheredefense.models.Entity;

public final class Advance
extends Entity
implements Cloneable {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private int currentLevel;
	private int starsBalance;
	
	// Constructors --------------------------------------
	public Advance(int id) {
		super(id);
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public Advance clone() throws CloneNotSupportedException {
		return (Advance) super.clone();
	}
	
	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	public int getStarsBalance() {
		return starsBalance;
	}
	
	public void setStarsBalance(int starsBalance) {
		this.starsBalance = starsBalance;
	}
	
}
