package com.ketphish.spheredefense.views.gamescreens;

import com.ketphish.spheredefense.helpers.PlayerPreferences;
import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.models.profile.Advance;
import com.ketphish.spheredefense.views.GameScreen;
import com.ketphish.spheredefense.views.panels.Button;

public final class ReviewScreen
extends GameScreen {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	
	// Constructors --------------------------------------

	// Extends -------------------------------------------
	@Override
	public void bindData(ProfileManager profileManager) { }
	
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
	
	@Override
	public void adjustPanels(boolean isSorting) {
		super.adjustPanels(isSorting);
		Preset.getInstance().floatYToViewport(labels.get("lbl_title").getRectangle(), false);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_back").getRectangle(), true, true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_start").getRectangle(), false, true);
	}
	 
	@Override
	public void onButtonTouch(Button sender) {
		if (sender.getName().equals("btn_back")) {
			final int currentLevel = getAdvance().getCurrentLevel();
			getPrefs().setLevel(currentLevel);
			game.getGameScreenManager().changeScreenState(ScreenState.START);
		} else if (sender.getName().equals("btn_start")) {
			game.getGameScreenManager().changeScreenState(ScreenState.GAME_PLAY);
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	private Advance getAdvance() {
		return game.getGameLogic().getProfileManager()
				.getProfile().getAdvance();
	}
	
	private PlayerPreferences getPrefs() {
		return game.getGameLogic()
				.getProfileManager()
				.getPlayerPrefs();
	}
	
}
