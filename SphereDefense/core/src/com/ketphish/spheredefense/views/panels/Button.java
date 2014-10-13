package com.ketphish.spheredefense.views.panels;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.interfaces.TouchListener;
import com.ketphish.spheredefense.interfaces.Touchable;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.managers.InputManager;

public final class Button
extends Label
implements TouchListener {
	// Inner -------------------------------------------------
	public enum ButtonState {
		IDLE,
		TOUCH,
		DISABLED
	}
	
	public interface Event {
		void onTouchDown(Button sender);
		void onTouchUp(Button sender);
	}
	
	// Fields --------------------------------------------
	private Vector2 texturePosition;
	private ButtonState buttonState;
	private Touchable firedEvent;
	private List<Event> events;

	// Constructors --------------------------------------
	public Button(String name, String parentName,
			String textureName, Rectangle rectangle,
			float zIndex, boolean isVisible) {
		super(name, parentName, textureName, rectangle, zIndex, isVisible);
		this.texturePosition = new Vector2();
		this.events = new ArrayList<Event>();
	}
	
	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) {
		if (isVisible) {
			drawer.draw(textureName, rectangle, texturePosition);
		}
	}
	
	// Implementations -----------------------------------
	@Override
	public boolean onTouchDown(InputManager sender) {
		if (isTouched(sender)) {
			buttonState = ButtonState.TOUCH;
			changeTexturePosition();
			if (events.size() > 0) {
				for (Event event : events) {
					event.onTouchDown(this);
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean onTouchUp(InputManager sender) {
		if (isTouched(sender)) {
			buttonState = ButtonState.IDLE;
			changeTexturePosition();
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
		if (buttonState != ButtonState.DISABLED) {
			buttonState = ButtonState.IDLE;
		}
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
		if (buttonState != ButtonState.DISABLED) {
			buttonState = ButtonState.DISABLED;
		}
		screen.getGame().getInputManager().unregister(firedEvent);
	}
	
	// Methods -------------------------------------------
	private boolean isTouched(InputManager sender) {
		if (isVisible && buttonState != ButtonState.DISABLED) {
			float x = sender.getTouchPosX();
			float y = sender.getTouchPosY();
			if (rectangle.contains(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	private void changeTexturePosition() {
		switch(buttonState) {
			case IDLE:
				texturePosition.y = .0f;
				break;
			case TOUCH:
				texturePosition.y = rectangle.height;
				break;
			case DISABLED:
				texturePosition.y = rectangle.height * 2;
				break;
			default:
				break;
		}
	}
	
	// Properties ----------------------------------------
	public List<Event> getEvents() {
		return events;
	}

	public Vector2 getTexturePosition() {
		return texturePosition;
	}
	
	public void setButtonState(ButtonState state) {
		this.buttonState = state;
		changeTexturePosition();
	}
	
	public ButtonState getState() {
		return buttonState;
	}
	
}
