package com.ketphish.spheredefense.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public final class PlayerPreferences {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final String PREFS_NAME = "Prefs";
	private final String IS_NEW_GAME = "isNewGame";
	private final String LEVEL = "level";
	private final String IS_LEVEL_PASSED = "isLevelPassed";
	private final String LEVEL_STARS_AMOUNT = "levelStarsAmount";
	private final Preferences prefs;

	// Constructors --------------------------------------
	public PlayerPreferences() {
		prefs = Gdx.app.getPreferences(PREFS_NAME);
		setLevel(1);
		setIsNewGame(true);
		setIsLevelPassed(false);
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void destroy() {
		prefs.clear();
		prefs.flush();
	}
	
	// Properties ----------------------------------------
	public void setIsNewGame(boolean value) {
		prefs.putBoolean(IS_NEW_GAME, value);
		prefs.flush();
	}
	
	public boolean isNewGame() {
		return prefs.getBoolean(IS_NEW_GAME);
	}
	
	public void setLevel(int level) {
		prefs.putInteger(LEVEL, level);
		prefs.flush();
	}
	
	public int getLevel() {
		return prefs.getInteger(LEVEL);
	}
	
	public void setIsLevelPassed(boolean isLevelPassed) {
		prefs.putBoolean(IS_LEVEL_PASSED, isLevelPassed);
		prefs.flush();
	}
	
	public boolean getIsLevelPassed() {
		return prefs.getBoolean(IS_LEVEL_PASSED);
	}
	
	public void setLevelStarsAmount(int starsAmount) {
		prefs.putInteger(LEVEL_STARS_AMOUNT, starsAmount);
		prefs.flush();
	}
	
	public int getLevelStarsAmount() {
		return prefs.getInteger(LEVEL_STARS_AMOUNT);
	}
	
}
