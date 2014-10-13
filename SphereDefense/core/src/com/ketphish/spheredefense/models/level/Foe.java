package com.ketphish.spheredefense.models.level;

import com.ketphish.spheredefense.models.Entity;

public final class Foe
extends Entity {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final String enemyName;
	private final int trailId;
	private final float startTime;
	
	// Constructors --------------------------------------
	public Foe(int id, String enemyName, int trailId, float startTime) {
		super(id);
		this.enemyName = enemyName;
		this.trailId = trailId;
		this.startTime = startTime;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public String getEnemyName() {
		return enemyName;
	}

	public int getTrailId() {
		return trailId;
	}

	public float getStartTime() {
		return startTime;
	}
	
}
