package com.ketphish.spheredefense.builders;

import com.ketphish.spheredefense.factories.GameScreenFactory;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.models.Config;
import com.ketphish.spheredefense.views.GamePlayScreen;
import com.ketphish.spheredefense.views.GameScreen;
import com.ketphish.spheredefense.views.Screen;

public final class ScreenBuilder {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------

	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public Screen buildScreen(ScreenState next, Config config,
			ProfileManager profileManager) {
		if (next != ScreenState.GAME_PLAY) {
			final GameScreen screen = (GameScreen) new GameScreenFactory().getScreen(next);
			screen.setLabels(config.getLabels());
			screen.setTextFields(config.getTextFields());
			screen.setButtons(config.getButtons());
			screen.adjustPanels(true);
			screen.bindData(profileManager);
			return screen;
		} else {
			final GamePlayScreen screen = (GamePlayScreen) new GameScreenFactory().getScreen(next);
			screen.setLabels(config.getLabels());
			screen.setTextFields(config.getTextFields());
			screen.setButtons(config.getButtons());
			screen.setGamePlayField(config.getGamaPlayField());
			screen.setLevelMapField(config.getLevelMapField());
			screen.setTowersMenu(config.getTowersMenu());
			screen.setBulletField(config.getBulletField());
			config.getTowerField().setBuildBar(config.getTowerBuildBar());
			screen.setTowerField(config.getTowerField());
			config.getEnemyField().setHealthBar(config.getEnemyHealthBar());
			screen.setEnemyField(config.getEnemyField());
			screen.setEffectField(config.getEffectField());
			screen.adjustPanels(false);
			screen.bindData(profileManager);
			return screen;
		}
	}
	
	// Properties ----------------------------------------
}
