package com.ketphish.spheredefense.models.level;

import java.util.ArrayList;
import java.util.List;

import com.ketphish.spheredefense.models.Entity;

public final class Wave
extends Entity {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	private final List<Foe> foes;
	private final float duration;
	
	// Constructors --------------------------------------
	public Wave(int id, float duration) {
		super(id);
		this.duration = duration;
		foes = new ArrayList<Foe>();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public List<Foe> getFoes() {
		return foes;
	}

	public float getDuration() {
		return duration;
	}
	
}
