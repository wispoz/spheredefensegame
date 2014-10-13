package com.ketphish.spheredefense.views.panels.menus;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Rectangle;
import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.views.GamePlayScreen;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.Label;

public abstract class TowerMenu
extends Label {
	// Inner ---------------------------------------------
	public enum State {
		ACTIVE,
		INACTIVE
	}
	
	public enum ScreenQuadrant {
		ONE,
		TWO,
		THREE,
		FOUR
	}
	
	// Fields --------------------------------------------
	protected final float BUTTON_OFFSET = 5.0f;
	protected GamePlayScreen screen;
	protected Map<String, Button> buttons;
	protected State state;
	
	// Constructors --------------------------------------
	public TowerMenu() {
		state = State.INACTIVE;
	}
	public TowerMenu(String name, String parentName, 
			String textureName, Rectangle rectangle, 
			float zIndex, boolean isVisible) {
		super(name, parentName, textureName, rectangle, zIndex, isVisible);
		state = State.INACTIVE;
	}
	
	// Extends -------------------------------------------
	@Override
	public void toggleVisibility() {
		for (Button button : buttons.values()) {
			button.toggleVisibility();
		}
		super.toggleVisibility();
		state = (isVisible) ? State.ACTIVE : State.INACTIVE;
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	protected abstract void updateButtonPositions();
	
	public void initButtons() {
		buttons = new HashMap<String, Button>();
	}
	
	public void updatePosition(Rectangle rect) {
		final ScreenQuadrant screenQuadrant = getQuadrant(rect);
		switch (screenQuadrant) {
		case ONE:
			setBottomLeftPosition(rect);
			break;
		case TWO:
			setBottomRightPosition(rect);
			break;
		case THREE:
			setTopRightPosition(rect);
			break;
		case FOUR:
			setTopLeftPosition(rect);
			break;
		}
	}
	
	protected ScreenQuadrant getQuadrant(Rectangle rect) {
		final float width = Preset.getInstance().MIN_AREA_WIDTH;
		final float height = Preset.getInstance().MIN_AREA_HEIGHT;
		if (rect.x > width / 2 && rect.y > height / 2) {
			return ScreenQuadrant.ONE;
		} else if (rect.x < width / 2 && rect.y > height / 2) {
			return ScreenQuadrant.TWO;
		} else if (rect.x < width / 2 && rect.y < height / 2) {
			return ScreenQuadrant.THREE;
		}
		return ScreenQuadrant.FOUR;
	}
	
	protected void setBottomLeftPosition(Rectangle rect) {
		rectangle.x = rect.x + rect.width / 2 - rectangle.width;
		rectangle.y = rect.y + rect.height / 2 - rectangle.height;
	}
	
	protected void setBottomRightPosition(Rectangle rect) {
		rectangle.x = rect.x + rect.width / 2;
		rectangle.y = rect.y + rect.height / 2 - rectangle.height;
	}
	
	protected void setTopRightPosition(Rectangle rect) {
		rectangle.x = rect.x + rect.width / 2;
		rectangle.y = rect.y + rect.height / 2;
	}

	protected void setTopLeftPosition(Rectangle rect) {
		rectangle.x = rect.x + rect.width / 2 - rectangle.width;
		rectangle.y = rect.y + rect.height / 2;
	}
	
	public void destroy() {
		if (buttons != null) {
			for (Button button : buttons.values()) {
				button.deactivate();
			}
			buttons.clear();
			buttons = null;
		}
	}
	
	// Properties ----------------------------------------
	public GamePlayScreen getScreen() {
		return screen;
	}

	public void setScreen(GamePlayScreen screen) {
		this.screen = screen;
	}

	public Map<String, Button> getButtons() {
		return buttons;
	}

	public void setButtons(Map<String, Button> buttons) {
		this.buttons = new HashMap<String, Button>(buttons);
	}
	
	public State getState() {
		return state;
	}
	
}
