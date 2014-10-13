package com.ketphish.spheredefense.views;

import com.badlogic.gdx.math.Rectangle;
import com.ketphish.spheredefense.interfaces.Drawable;

public abstract class Panel
implements Drawable, Cloneable {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	protected Screen screen;
	protected Rectangle rectangle;
	protected String name;
	protected String parentName;
	protected float zIndex;
	protected boolean isVisible;

	// Constructors --------------------------------------
	public Panel() {
		name = "";
		parentName = "";
		rectangle = new Rectangle();
		zIndex = 0;
		isVisible = true;
	}
	
	public Panel(String name, String parentName, 
			Rectangle rectangle, 
			float zIndex, boolean isVisible) {
		this.name = name;
		this.parentName = parentName;
		this.rectangle = rectangle;
		this.zIndex = zIndex;
		this.isVisible = isVisible;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	protected Panel clone() throws CloneNotSupportedException {
		Panel clone = (Panel) super.clone();
		clone.screen = this.screen;
		clone.rectangle = new Rectangle(this.rectangle);
		return clone;
	}

	// Methods -------------------------------------------
	public void toggleVisibility() {
		this.isVisible = (isVisible) ? false : true;
	}
	
	// Properties ----------------------------------------
	public Screen getScreen() {
		return screen;
	}
	
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getParentName() {
		return parentName;
	}
	
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void setRectangle(Rectangle rectangle) {
		this.rectangle.set(rectangle);
	}
	
	public float getZIndex() {
		return zIndex;
	}

	public void setZIndex(float zIndex) {
		this.zIndex = zIndex;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
}
