package com.ketphish.spheredefense.interfaces;

import com.ketphish.spheredefense.managers.InputManager;

public interface Touchable {
	boolean touchDown(InputManager sender);
	boolean touchDragged(InputManager sender);
	boolean touchUp(InputManager sender);
}
