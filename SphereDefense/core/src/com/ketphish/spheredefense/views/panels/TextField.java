package com.ketphish.spheredefense.views.panels;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.views.Panel;

public final class TextField
extends Panel {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private String text;
	private String fontName;
	private float fontScale;
	private Color fontColor;
	
	// Constructors --------------------------------------
	public TextField(String name, String parentName,
			String text, String fontName,
			Rectangle rectangle,
			float fontScale, Color fontColor,
			float zIndex, boolean isVisible) {
		super(name, parentName, rectangle, zIndex, isVisible);
		this.text = text;
		this.fontName = fontName;
		this.fontScale = fontScale;
		this.fontColor = fontColor;
	}
	
	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) {
		if (isVisible) {
			drawer.drawString(fontName, text, 
					rectangle.x, rectangle.y, fontScale, fontColor);
		}
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	
	// Properties ----------------------------------------
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public float getFontScale() {
		return fontScale;
	}

	public void setFontScale(float fontScale) {
		this.fontScale = fontScale;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	
}
