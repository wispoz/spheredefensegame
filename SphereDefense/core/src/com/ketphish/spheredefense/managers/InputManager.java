package com.ketphish.spheredefense.managers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.ketphish.spheredefense.SphereDefenseGame;
import com.ketphish.spheredefense.interfaces.Touchable;

public final class InputManager
implements InputProcessor {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final SphereDefenseGame game;
	private final List<Touchable> touchEvents;
	private Vector3 touchBounds;
	
	// Constructors --------------------------------------
	public InputManager(SphereDefenseGame game) {
		this.game = game;
		this.touchEvents = new ArrayList<Touchable>();
		this.touchBounds = new Vector3();
	}
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		setTouchBounds(screenX, screenY);
		for (Touchable touchEvent : touchEvents) {
			if (touchEvent.touchDown(this)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		setTouchBounds(screenX, screenY);
		for (Touchable touchEvent : touchEvents) {
			if (touchEvent.touchUp(this)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	// Methods -------------------------------------------
	public void register(Touchable event) {
		touchEvents.add(event);
	}
	
	public void unregister(Touchable event) {
		touchEvents.remove(event);
	}
	
	public void unregisterAll() {
		if (touchEvents.size() > 0) {
			touchEvents.clear();
		}
	}
	
	// Properties ----------------------------------------
	private void setTouchBounds(int screenX, int screenY) {
		touchBounds.set(screenX, screenY, 0);
		game.getCamera().unproject(touchBounds);
	}
	
	public float getTouchPosX() {
		return touchBounds.x;
	}
	
	public float getTouchPosY() {
		return touchBounds.y;
	}
	
}
