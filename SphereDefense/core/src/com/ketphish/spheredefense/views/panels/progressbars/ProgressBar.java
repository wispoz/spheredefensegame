package com.ketphish.spheredefense.views.panels.progressbars;

import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.views.panels.Label;

public abstract class ProgressBar
extends Label {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	protected Label progress;
	
	// Constructors --------------------------------------

	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) {
		if (isVisible) {
			super.draw(drawer, deltaTime);
			progress.draw(drawer, deltaTime);
		}
	}
	
	@Override
	public ProgressBar clone() throws CloneNotSupportedException {
		final ProgressBar clone = (ProgressBar) super.clone();
		clone.progress = this.progress.clone();
		return clone;
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public Label getProgress() {
		return progress;
	}
	
	public void setProgress(Label progress) {
		this.progress = progress;
	}
	
	@Override
	public void setZIndex(float zIndex) {
		super.setZIndex(zIndex);
		if (progress != null) {
			progress.setZIndex(zIndex + .1f);
		}
	}
	
}
