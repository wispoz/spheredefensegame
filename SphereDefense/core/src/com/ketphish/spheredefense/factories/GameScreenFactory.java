package com.ketphish.spheredefense.factories;

import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.views.GamePlayScreen;
import com.ketphish.spheredefense.views.Screen;
import com.ketphish.spheredefense.views.gamescreens.AboutScreen;
import com.ketphish.spheredefense.views.gamescreens.EncyclopediaScreen;
import com.ketphish.spheredefense.views.gamescreens.MainMenuScreen;
import com.ketphish.spheredefense.views.gamescreens.OptionsScreen;
import com.ketphish.spheredefense.views.gamescreens.ReviewScreen;
import com.ketphish.spheredefense.views.gamescreens.StartScreen;
import com.ketphish.spheredefense.views.gamescreens.UpgradesScreen;

public final class GameScreenFactory {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------

	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public Screen getScreen(ScreenState next) {
		switch (next) {
			case MAIN_MENU:
				return new MainMenuScreen();
			case START:
				return new StartScreen();
			case UPGRADES:
				return new UpgradesScreen();
			case ENCYCLOPEDIA:
				return new EncyclopediaScreen();
			case OPTIONS:
				return new OptionsScreen();
			case ABOUT:
				return new AboutScreen();
			case REVIEW:
				return new ReviewScreen();
			case GAME_PLAY:
				return new GamePlayScreen();
			default:
				return null;
		}
	}
	
	// Properties ----------------------------------------
}
