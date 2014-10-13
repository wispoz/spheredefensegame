package com.ketphish.spheredefense.models;

import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.interfaces.Updatable;

public abstract class GameObject
implements Updatable, Cloneable {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	protected String name;
	protected Vector2 position;
	protected Vector2 offset;

	// Constructors --------------------------------------
	public GameObject() {
		position = new Vector2();
		offset = new Vector2();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	protected GameObject clone() throws CloneNotSupportedException {
		GameObject clone = (GameObject) super.clone();
		clone.position = this.position.cpy();
		clone.offset = this.offset.cpy();
		return clone;
	}
	
	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(Vector2 position) {
		this.position.set(position);
	}
	
	public Vector2 getOffset() {
		return offset;
	}

	public void setOffset(Vector2 offset) {
		this.offset.set(offset);
	}
	
	public Vector2 getPositionWithOffset() {
		return new Vector2(position.x + offset.x, position.y + offset.y);
	}
	
}
