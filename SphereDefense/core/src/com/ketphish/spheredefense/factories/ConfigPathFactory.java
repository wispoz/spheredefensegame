package com.ketphish.spheredefense.factories;

import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;

public final class ConfigPathFactory {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	
	// Constructors --------------------------------------
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public String getScreenConfigPath(ScreenState next) {
		final StringBuilder builder = new StringBuilder();
		builder.append("configs/screens/");
		switch (next) {
		case MAIN_MENU:
			builder.append("main_menu_screen.xml");
			break;
		case START:
			builder.append("start_screen.xml");
			break;
		case UPGRADES:
			builder.append("upgrades_screen.xml");
			break;
		case ENCYCLOPEDIA:
			builder.append("encyclopedia_screen.xml");
			break;
		case OPTIONS:
			builder.append("options_screen.xml");
			break;
		case ABOUT:
			builder.append("about_screen.xml");
			break;
		case REVIEW:
			builder.append("review_screen.xml");
			break;
		case GAME_PLAY:
			builder.append("game_play_screen.xml");
			break;
		default:
			throw new IllegalArgumentException("Unknown screen state");
		}
		return builder.toString();
	}
	
	public String getLevelMapPath(int level) {
		final StringBuilder builder = new StringBuilder();
		builder.append("configs/level_maps/");
		switch (level) {
		case 1:
			builder.append("level_map_1.xml");
			break;
		case 2:
			builder.append("level_map_2.xml");
			break;
		case 3:
			builder.append("level_map_3.xml");
			break;
		case 4:
			builder.append("level_map_4.xml");
			break;
		case 5:
			builder.append("level_map_5.xml");
			break;
		case 6:
			builder.append("level_map_6.xml");
			break;
		case 7:
			builder.append("level_map_7.xml");
			break;
		case 8:
			builder.append("level_map_8.xml");
			break;
		case 9:
			builder.append("level_map_9.xml");
			break;
		case 10:
			builder.append("level_map_10.xml");
			break;
		case 11:
			builder.append("level_map_11.xml");
			break;
		case 12:
			builder.append("level_map_12.xml");
			break;
		default:
			throw new IllegalArgumentException("Unknown level");
		}
		return builder.toString();
	}
	
	public String getLevelConfigPath(int level) {
		final StringBuilder builder = new StringBuilder();
		builder.append("configs/levels/");
		switch (level) {
		case 1:
			builder.append("level_1.xml");
			break;
		case 2:
			builder.append("level_2.xml");
			break;
		case 3:
			builder.append("level_3.xml");
			break;
		case 4:
			builder.append("level_4.xml");
			break;
		case 5:
			builder.append("level_5.xml");
			break;
		case 6:
			builder.append("level_6.xml");
			break;
		case 7:
			builder.append("level_7.xml");
			break;
		case 8:
			builder.append("level_8.xml");
			break;
		case 9:
			builder.append("level_9.xml");
			break;
		case 10:
			builder.append("level_10.xml");
			break;
		case 11:
			builder.append("level_11.xml");
			break;
		case 12:
			builder.append("level_12.xml");
			break;
		default:
			throw new IllegalArgumentException("Unknown level");
		}
		return builder.toString();
	}
	
	public String getGameObjectsConfigPath() {
		return "configs/game_objects.xml";
	}
	
	// Properties ----------------------------------------
	
}
