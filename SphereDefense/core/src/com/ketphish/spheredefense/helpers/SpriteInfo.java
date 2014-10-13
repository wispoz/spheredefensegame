package com.ketphish.spheredefense.helpers;

public class SpriteInfo {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final String fileName;
	private final int rows;
	private final int cols;
	private final boolean isGroups;
	private final float speed;
	
	// Constructors --------------------------------------
	public SpriteInfo(String fileName, int rows,int cols,
			boolean isGroups, float speed) {
		this.fileName = fileName;
		this.rows = rows;
		this.cols = cols;
		this.isGroups = isGroups;
		this.speed = speed;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public String getFileName() {
		return fileName;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public boolean isGroups() {
		return isGroups;
	}

	public float getSpeed() {
		return speed;
	}
	
}
