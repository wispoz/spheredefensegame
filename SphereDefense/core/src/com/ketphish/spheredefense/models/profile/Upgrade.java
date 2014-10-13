package com.ketphish.spheredefense.models.profile;

import com.ketphish.spheredefense.models.Entity;

public final class Upgrade
extends Entity
implements Cloneable {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final Unit unit;
	private final Power power;
	private boolean isUpgraded;

	// Constructors --------------------------------------
	public Upgrade(int id, Unit unit, 
			Power power, boolean isUpgraded) {
		super(id);
		this.unit = unit;
		this.power = power;
		this.isUpgraded = isUpgraded;
	}
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------
	@Override
	public Upgrade clone() throws CloneNotSupportedException {
		return (Upgrade) super.clone();
	}
	
	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public Unit getUnit() {
		return unit;
	}
	
	public Power getPower() {
		return power;
	}
	
	public boolean isUpgraded() {
		return isUpgraded;
	}

	public void isUpgraded(boolean isUpgraded) {
		this.isUpgraded = isUpgraded;
	}
	
}
