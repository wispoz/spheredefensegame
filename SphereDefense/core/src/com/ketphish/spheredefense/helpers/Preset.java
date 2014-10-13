package com.ketphish.spheredefense.helpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public final class Preset {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	public final int MIN_AREA_WIDTH = 800;
	public final int MIN_AREA_HEIGHT = 480;
	
	public final int MAX_AREA_WIDTH = 854;
	public final int MAX_AREA_HEIGHT = 600;
	
	public final int LEVELS_COUNT = 12;
	public final int MAX_STARS_COUNT_PER_LEVEL = 3;
	
	private static Preset instance;
	private float viewportWidth;
	private float viewportHeight;
	
	// Constructors --------------------------------------
	private Preset() { }
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	public Vector2 translateMaxToMinAreaPosition(final Vector2 positionMax) {
		final float offsetX = (MAX_AREA_WIDTH - MIN_AREA_WIDTH) / 2.0f;
		final float offsetY = (MAX_AREA_HEIGHT - MIN_AREA_HEIGHT) / 2.0f;
		final Vector2 positionMin = new Vector2();
		positionMin.x = positionMax.x - offsetX;
		positionMin.y = MAX_AREA_HEIGHT - offsetY - positionMax.y;
		return positionMin;
	}
	
	public float translateTopToBottomY(final float height, final float y) {
		return (MIN_AREA_HEIGHT - height - y);
	}
	
	public void floatPositionToViewport(Rectangle rectangle, boolean isLeft, boolean isBottom) {
		floatXToViewport(rectangle, isLeft);
		floatYToViewport(rectangle, isBottom);
	}
	
	public void floatXToViewport(Rectangle rectangle, boolean isLeft) {
		if (isLeft) {
			rectangle.x = (MIN_AREA_WIDTH - viewportWidth) / 2 + rectangle.x;
		} else {
			rectangle.x = -(MIN_AREA_WIDTH - viewportWidth) / 2 + rectangle.x;
		}
	}
	
	public void floatYToViewport(Rectangle rectangle, boolean isBottom) {
		if (isBottom) {
			rectangle.y = (MIN_AREA_HEIGHT - viewportHeight) / 2 + rectangle.y;
		} else {
			rectangle.y = -(MIN_AREA_HEIGHT - viewportHeight) / 2 + rectangle.y;
		}
	}
	
	// Properties ----------------------------------------
	public static Preset getInstance() {
		if (instance == null) {
			instance = new Preset();
		}
		return instance;
	}
	
	public void setViewportSize(float viewportWidth, float viewportHeight) {
		this.viewportWidth = viewportWidth;
		this.viewportHeight = viewportHeight;
	}
	
}
