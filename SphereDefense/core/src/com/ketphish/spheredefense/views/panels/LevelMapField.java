package com.ketphish.spheredefense.views.panels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.views.GamePlayScreen;
import com.ketphish.spheredefense.views.Panel;
import com.ketphish.spheredefense.views.panels.Button.ButtonState;

public final class LevelMapField
extends Panel {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private GamePlayScreen screen;
	private List<Label> labels;
	private Map<String, Button> buttons;
	
	// Constructors --------------------------------------
	public LevelMapField() {
		labels = new ArrayList<Label>();
	}
	
	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) { }
	
	@Override
	public void toggleVisibility() {
		for (Button button : buttons.values()) {
			button.toggleVisibility();
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void initButtons() {
		buttons = new HashMap<String, Button>();
	}
	
	public void showIdleButtons() {
		for (Button button : buttons.values()) {
			if (!button.isVisible()
					&& button.getState() == ButtonState.IDLE) {
				button.toggleVisibility();
			}
		}
	}
	
	public void hideIdleButtons() {
		for (Button button : buttons.values()) {
			if (button.isVisible()
					&& button.getState() == ButtonState.IDLE) {
				button.toggleVisibility();
			}
		}
	}
	
	public void showDisabledButton(Vector2 center) {
		for (Button button : buttons.values()) {
			if (button.getRectangle().contains(center)) {
				button.setButtonState(ButtonState.IDLE);
				button.toggleVisibility();
				break;
			}
		}
	}
	
	// Properties ----------------------------------------
	public GamePlayScreen getScreen() {
		return screen;
	}

	public void setScreen(GamePlayScreen screen) {
		this.screen = screen;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public Map<String, Button> getButtons() {
		return buttons;
	}

	public void setButtons(Map<String, Button> buttons) {
		this.buttons = new HashMap<String, Button>(buttons);
	}
	
}
