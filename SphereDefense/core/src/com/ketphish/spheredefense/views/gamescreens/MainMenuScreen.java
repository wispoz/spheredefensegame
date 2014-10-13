package com.ketphish.spheredefense.views.gamescreens;

import com.ketphish.spheredefense.helpers.PlayerPreferences;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.models.profile.Profile;
import com.ketphish.spheredefense.views.GameScreen;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.Button.ButtonState;

public final class MainMenuScreen
extends GameScreen {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	
	// Constructors --------------------------------------
	
	// Extends -------------------------------------------
	@Override
	public void bindData(ProfileManager profileManager) {
		final Profile profile = profileManager.getProfile();
		if (profile.getAdvance().getCurrentLevel() == 1) {
			buttons.get("btn_load").setButtonState(ButtonState.DISABLED);
		}
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
	
	@Override
	public void onButtonTouch(Button sender) {
		if (sender.getName().equals("btn_new")) {
			final ButtonState loadState = buttons.get("btn_load").getState();
			if (loadState != ButtonState.DISABLED) {
				showQuestionDialog();
			} else {
				setIsNewButtonTouched(true);
				changeGameScreenState(ScreenState.START);
			}
		} else if (sender.getName().equals("btn_load")) {
			setIsNewButtonTouched(false);
			changeGameScreenState(ScreenState.START);
		} else if (sender.getName().equals("btn_encyclopedia")) {
			changeGameScreenState(ScreenState.ENCYCLOPEDIA);
		} else if (sender.getName().equals("btn_options")) {
			changeGameScreenState(ScreenState.OPTIONS);
		} else if (sender.getName().equals("btn_about")) {
			changeGameScreenState(ScreenState.ABOUT);
		} else if (sender.getName().equals("btn_yes")) {
			setIsNewButtonTouched(true);
			changeGameScreenState(ScreenState.START);
		} else if (sender.getName().equals("btn_no")) {
			hideQuestionDialog();
		}
	}
	
	private void setIsNewButtonTouched(boolean isTouched) {
		getPrefs().setIsNewGame(isTouched);
	}

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	private void changeGameScreenState(ScreenState screenState) {
		game.getGameScreenManager().changeScreenState(screenState);
	}
	
	private void showQuestionDialog() {
		changeButtonsState(ButtonState.DISABLED);
		toggleQuestionDialogVisibility();
	}
	
	private void hideQuestionDialog() {
		changeButtonsState(ButtonState.IDLE);
		toggleQuestionDialogVisibility();
	}

	private void changeButtonsState(ButtonState state) {
		buttons.get("btn_new").setButtonState(state);
		buttons.get("btn_load").setButtonState(state);
		buttons.get("btn_encyclopedia").setButtonState(state);
		buttons.get("btn_options").setButtonState(state);
		buttons.get("btn_about").setButtonState(state);
	}
	
	private void toggleQuestionDialogVisibility() {
		labels.get("bg_question").toggleVisibility(); 
		labels.get("lbl_question").toggleVisibility();
		buttons.get("btn_yes").toggleVisibility();
		buttons.get("btn_no").toggleVisibility();
	}
	
	// Properties ----------------------------------------
	private PlayerPreferences getPrefs() {
		return game.getGameLogic()
				.getProfileManager()
				.getPlayerPrefs();
	}
	
}
