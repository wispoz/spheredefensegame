package com.ketphish.spheredefense.views.panels;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.interfaces.TouchListener;
import com.ketphish.spheredefense.interfaces.Touchable;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.managers.InputManager;
import com.ketphish.spheredefense.views.Panel;

public final class GamePlayField
extends Panel
implements TouchListener {
	// Inner ---------------------------------------------
	public interface Event {
		void onTouchUp(GamePlayField sender);
	}
	
	// Fields --------------------------------------------
	private final Vector2 touchPosition;
	private Touchable firedEvent;
	private final List<Event> events;
	
	// Constructors --------------------------------------
	public GamePlayField(String name, String parentName, 
			Rectangle rectangle,
			float zIndex, boolean isVisible) {
		super(name, parentName, rectangle, zIndex, isVisible);
		events = new ArrayList<Event>();
		touchPosition = new Vector2();
	}
	
	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) { }
	
	// Implementations -----------------------------------
	@Override
	public boolean onTouchDown(InputManager sender) {
		return false;
	}

	@Override
	public boolean onTouchUp(InputManager sender) {
		if (isTouched(sender)) {
			if (events.size() > 0) {
				for (Event event : events) {
					event.onTouchUp(this);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void activate() {
		firedEvent = new Touchable() {
			@Override
			public boolean touchDown(InputManager sender) {
				return onTouchDown(sender);
			}
			@Override
			public boolean touchDragged(InputManager sender) {
				return false;
			}
			@Override
			public boolean touchUp(InputManager sender) {
				return onTouchUp(sender);
			}
		};
		screen.getGame().getInputManager().register(firedEvent);
	}

	@Override
	public void deactivate() {
		if (events != null) {
			events.clear();
		}
		screen.getGame().getInputManager().unregister(firedEvent);
	}
	
	// Methods -------------------------------------------
	private boolean isTouched(InputManager sender) {
		if (isVisible) {
			final float x = sender.getTouchPosX();
			final float y = sender.getTouchPosY();
			if (rectangle.contains(x, y)) {
				touchPosition.x = x;
				touchPosition.y = y;
				return true;
			}
		}
		return false;
	}
	
	// Properties ----------------------------------------
	public Vector2 getTouchPosition() {
		return touchPosition;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	
}
