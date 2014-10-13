package com.ketphish.spheredefense.models.trail;

import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.models.Entity;

public final class Snippet
extends Entity {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private Vector2 begin;
	private Vector2 end;
	
	// Constructors --------------------------------------
	public Snippet(int id, Vector2 begin, Vector2 end) {
		super(id);
		this.begin = begin.cpy();
		this.end = end.cpy();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public Vector2 getBegin() {
		return begin;
	}
	
	public void setBegin(Vector2 begin) {
		this.begin = begin;
	}

	public Vector2 getEnd() {
		return end;
	}
	
	public void setEnd(Vector2 end) {
		this.end = end;
	}
	
}
