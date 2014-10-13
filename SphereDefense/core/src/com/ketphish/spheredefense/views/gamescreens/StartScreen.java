package com.ketphish.spheredefense.views.gamescreens;

import static com.ketphish.spheredefense.managers.GameScreenManager.ScreenState.MAIN_MENU;
import static com.ketphish.spheredefense.managers.GameScreenManager.ScreenState.UPGRADES;

import java.util.List;

import com.ketphish.spheredefense.helpers.PlayerPreferences;
import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.models.profile.Profile;
import com.ketphish.spheredefense.models.profile.Trophy;
import com.ketphish.spheredefense.views.GameScreen;
import com.ketphish.spheredefense.views.panels.Button;

public final class StartScreen
extends GameScreen {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	
	// Constructors --------------------------------------
	
	// Extends -------------------------------------------
	@Override
	public void bindData(ProfileManager profileManager) {
		final Profile profile = profileManager.getProfile();
		int unavailableLevel = profile.getAdvance().getCurrentLevel() + 1;
		final int levelsCount = Preset.getInstance().LEVELS_COUNT;
		for (Button button : buttons.values()) {
			if (button.getName().endsWith(String.valueOf(unavailableLevel))) {
				button.toggleVisibility();
				if (unavailableLevel <= levelsCount) {
					unavailableLevel++;
				} else {
					break;
				}
			}
		}
		showTrophies(profile.getTrophies(), profile.getAdvance().getCurrentLevel());
		String stars = String.valueOf(profile.getAdvance().getStarsBalance());
		final int starsPerLevel = Preset.getInstance().MAX_STARS_COUNT_PER_LEVEL;
		final String maxStarsCount = String.valueOf(levelsCount * starsPerLevel);
		if (stars.length() < 2) {
			stars = String.format("%s%s", " ", stars);
		}
		textFields.get("txt_stars").setText(stars);
		textFields.get("txt_stars_total").setText(maxStarsCount);
	}
	
	private void showTrophies(List<Trophy> trophies, int currentLevel) {
		for (int i = 0; i < currentLevel; i++) {
			final int stars = trophies.get(i).getStarsAmount();
			if (stars > 0) {
				final int level = trophies.get(i).getLevel();
				final String lblName = getLabelName(level);
				if (stars > 1) {
					final String textureName = getTextureName(stars);
					labels.get(lblName).setTextureName(textureName);
				}
				labels.get(lblName).toggleVisibility();
			}
		}
	}

	private String getLabelName(int level) {
		switch(level) {
		case 1:
			return "lbl_level_stars_1";
		case 2:
			return "lbl_level_stars_2";
		case 3:
			return "lbl_level_stars_3";
		case 4:
			return "lbl_level_stars_4";
		case 5:
			return "lbl_level_stars_5";
		case 6:
			return "lbl_level_stars_6";
		case 7:
			return "lbl_level_stars_7";
		case 8:
			return "lbl_level_stars_8";
		case 9:
			return "lbl_level_stars_9";
		case 10:
			return "lbl_level_stars_10";
		case 11:
			return "lbl_level_stars_11";
		case 12:
			return "lbl_level_stars_12";
		default:
			return null;
		}
	}

	private String getTextureName(int stars) {
		switch(stars) {
		case 2:
			return "lbl_level_stars_2";
		case 3:
			return "lbl_level_stars_3";
		default:
			return null;
		}
	}

	@Override
	public void adjustPanels(boolean isSorting) {
		super.adjustPanels(isSorting);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_back").getRectangle(), true, true);
		Preset.getInstance().floatYToViewport(buttons.get("btn_upgrades").getRectangle(), true);
		Preset.getInstance().floatPositionToViewport(labels.get("lbl_stars").getRectangle(), false, true);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_stars").getRectangle(), false, true);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_separator").getRectangle(), false, true);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_stars_total").getRectangle(), false, true);
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
		if (sender.getName().equals("btn_back")) {
			game.getGameScreenManager().changeScreenState(MAIN_MENU);
		} else if (sender.getName().equals("btn_upgrades")) {
			game.getGameScreenManager().changeScreenState(UPGRADES);
		} else {
			final String[] nameParts = sender.getName().trim().split("_");
			final int levelNum = Integer.valueOf(nameParts[2]);
			getPrefs().setLevel(levelNum);
			game.getGameScreenManager().changeScreenState(ScreenState.REVIEW);
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
	private PlayerPreferences getPrefs() {
		return game.getGameLogic()
				.getProfileManager()
				.getPlayerPrefs();
	}
	
}
