package com.ketphish.spheredefense.views.gamescreens;

import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.models.profile.Options;
import com.ketphish.spheredefense.models.profile.Options.Language;
import com.ketphish.spheredefense.models.profile.Profile;
import com.ketphish.spheredefense.views.GameScreen;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.Button.ButtonState;

public final class OptionsScreen
extends GameScreen {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------

	// Constructors --------------------------------------
	
	// Extends -------------------------------------------
	@Override
	public void bindData(ProfileManager profileManager) {
		final Profile profile = profileManager.getProfile();
		if (!profile.getOptions().isMusic()) {
			buttons.get("btn_music").setTextureName("btn_music_off");
		}
		if (!profile.getOptions().isSound()) {
			buttons.get("btn_sound").setTextureName("btn_sound_off");
		}
		if (profile.getOptions().getLanguage() == Language.RUSSIAN) {
			buttons.get("btn_language").setTextureName("btn_russian");
		}
		buttons.get("btn_apply").setButtonState(ButtonState.DISABLED);
		profileManager.initOptionsCache();
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
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_back").getRectangle(), true, true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_apply").getRectangle(), false, true);
	}
	
	@Override
	public void onButtonTouch(Button sender) {
		boolean isOptionButtonTouched = true;
		if (sender.getName().equals("btn_music")) {
			if (getOptionsCache().isMusic()) {
				getOptionsCache().setIsMusic(false);
				buttons.get("btn_music").setTextureName("btn_music_off");
			} else {
				getOptionsCache().setIsMusic(true);
				buttons.get("btn_music").setTextureName("btn_music_on");
			}
		} else if (sender.getName().equals("btn_sound")) {
			if (getOptionsCache().isSound()) {
				getOptionsCache().setIsSound(false);
				buttons.get("btn_sound").setTextureName("btn_sound_off");
			} else {
				getOptionsCache().setIsSound(true);
				buttons.get("btn_sound").setTextureName("btn_sound_on");
			}
		} else if (sender.getName().equals("btn_language")) {
			if (getOptionsCache().getLanguage() == Language.ENGLISH) {
				getOptionsCache().setLanguage(Language.RUSSIAN);
				buttons.get("btn_language").setTextureName("btn_russian");
			} else {
				getOptionsCache().setLanguage(Language.ENGLISH);
				buttons.get("btn_language").setTextureName("btn_english");
			}
		} else if (sender.getName().equals("btn_back")) {
			if (buttons.get("btn_apply").getState() == ButtonState.IDLE) {
				getProfileManager().resetOptionsCache();
			}
			isOptionButtonTouched = false;
		} else if (sender.getName().equals("btn_apply")) {
			isOptionButtonTouched = false;
		}
		if (isOptionButtonTouched) {
			toggleApplyButtonState();
		} else {
			game.getGameScreenManager().changeScreenState(ScreenState.MAIN_MENU);
		}
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	private void toggleApplyButtonState() {
		if (getOptionsCache().isMusic() == getOptions().isMusic()
				&& getOptionsCache().isSound() == getOptions().isSound()
				&& getOptionsCache().getLanguage() == getOptions().getLanguage()) {
			buttons.get("btn_apply").setButtonState(ButtonState.DISABLED);
		} else {
			buttons.get("btn_apply").setButtonState(ButtonState.IDLE);
		}
	}

	// Properties ----------------------------------------
	private ProfileManager getProfileManager() {
		return game.getGameLogic().getProfileManager();
	}
	
	private Options getOptionsCache() {
		return getProfileManager().getOptionsCache();
	}
	
	private Options getOptions() {
		return getProfileManager().getProfile().getOptions();
	}
	
}
