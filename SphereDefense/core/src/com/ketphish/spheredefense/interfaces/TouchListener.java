package com.ketphish.spheredefense.interfaces;

import com.ketphish.spheredefense.managers.InputManager;

public interface TouchListener {
	boolean onTouchDown(InputManager sender);
	boolean onTouchUp(InputManager sender);
	void activate();
	void deactivate();
}
