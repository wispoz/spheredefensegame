package com.ketphish.spheredefense.views.gamescreens;

import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.views.GameScreen;
import com.ketphish.spheredefense.views.panels.Button;

public final class AboutScreen
extends GameScreen {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	
	// Constructors --------------------------------------
	
	// Extends -------------------------------------------
	@Override
	public void bindData(ProfileManager profileManager) { }
	
	@Override
	public void adjustPanels(boolean isSorting) {
		super.adjustPanels(isSorting);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_back").getRectangle(), true, true);
		Preset.getInstance().floatYToViewport(labels.get("lbl_title").getRectangle(), false);
	}
	
	@Override
	public void activate() {
		Button.Event event = new Button.Event() {
			@Override
			public void onTouchDown(Button sender) {
				game.getAudioManager().playSound("btn_touch", .5f);
			}
			@Override
			public void onTouchUp(Button sender) {
				onButtonTouch(sender);
			}
		};
		for (Button button : buttons.values()) {
			button.getEvents().add(event);
		}
		super.activate();
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	
	@Override
	public void onButtonTouch(Button sender) {
		if (sender.getName().equals("btn_back")) {
			game.getGameScreenManager().changeScreenState(ScreenState.MAIN_MENU);
		}
	}

	// Properties ----------------------------------------
}
