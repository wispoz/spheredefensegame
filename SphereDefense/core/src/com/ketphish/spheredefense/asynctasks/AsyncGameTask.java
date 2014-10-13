package com.ketphish.spheredefense.asynctasks;

import static com.ketphish.spheredefense.managers.GameScreenManager.ScreenState.GAME_PLAY;
import static com.ketphish.spheredefense.managers.GameScreenManager.ScreenState.MAIN_MENU;
import static com.ketphish.spheredefense.managers.GameScreenManager.ScreenState.OPTIONS;
import static com.ketphish.spheredefense.managers.GameScreenManager.ScreenState.START;
import static com.ketphish.spheredefense.managers.GameScreenManager.ScreenState.UPGRADES;

import java.util.List;
import java.util.Map;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.ketphish.spheredefense.builders.ScreenBuilder;
import com.ketphish.spheredefense.helpers.PlayerPreferences;
import com.ketphish.spheredefense.managers.AudioManager;
import com.ketphish.spheredefense.managers.DataManager;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.managers.LogicManager;
import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.models.Config;
import com.ketphish.spheredefense.models.profile.Advance;
import com.ketphish.spheredefense.models.profile.Options;
import com.ketphish.spheredefense.models.profile.Options.Language;
import com.ketphish.spheredefense.models.profile.Profile;
import com.ketphish.spheredefense.models.profile.Trophy;
import com.ketphish.spheredefense.models.profile.Upgrade;
import com.ketphish.spheredefense.views.GamePlayScreen;
import com.ketphish.spheredefense.views.Screen;

public final class AsyncGameTask
implements AsyncTask<Boolean> {
	// Inner ---------------------------------------------
	public interface Event {
		void onComplete(Screen screen);
	}
	
	// Fields --------------------------------------------
	private final DataManager dataManager;
	private final ProfileManager profileManager;
	private final AudioManager audioManager;
	private final PlayerPreferences playerPrefs;
	private LogicManager logicManager;
	private final ScreenState prevScreenState;
	private final ScreenState nextScreenState;
	private final Event event;
	
	// Constructors --------------------------------------
	public AsyncGameTask(DataManager dataManager, 
			ProfileManager profileManager, 
			AudioManager audioManager, 
			PlayerPreferences playerPrefs, 
			ScreenState prevScreenState, 
			ScreenState nextScreenState, 
			Event event) {
		this.dataManager = dataManager;
		this.profileManager = profileManager;
		this.audioManager = audioManager;
		this.playerPrefs = playerPrefs;
		this.prevScreenState = prevScreenState;
		this.nextScreenState = nextScreenState;
		this.event = event;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public Boolean call() throws Exception {
		callDatabaseTask();
		if (prevScreenState == null 
				&& nextScreenState == MAIN_MENU) {
			callAudioManagerTask();
		}
		Config config = callConfigImport();
		callTexturesImport(config);
		callAnimsImport(config);
		callFontsImport(config);
		callMusicImport(config);
		callSoundsImport(config);
		final Screen screen = callCreateScreen(config);
		if (nextScreenState == GAME_PLAY) {
			callLogicManagerTask(config, screen);
		}
		config = null;
		if (event != null) {
			event.onComplete(screen);
		}
		return true;
	}
	
	// Methods -------------------------------------------
	private void callDatabaseTask() {
		if (prevScreenState == null 
				&& nextScreenState == MAIN_MENU) {
			dataManager.initDatabase();
	    	final Advance advance = dataManager.getAdvance();
			final Options options = dataManager.getOptions();
			profileManager.getProfile().setAdvance(advance);
			profileManager.getProfile().setOptions(options);
			final PlayerPreferences prefs = profileManager.getPlayerPrefs();
			prefs.setLevel(advance.getCurrentLevel());
		} else if (prevScreenState == MAIN_MENU 
				&& nextScreenState == START) {
			if (profileManager.getProfile().getTrophies() == null) {
				final List<Trophy> trophies = dataManager.getTropies();
				profileManager.getProfile().setTrophies(trophies);
			}
			final PlayerPreferences prefs = profileManager.getPlayerPrefs();
			if (prefs.isNewGame()
					&& profileManager.getProfile().getAdvance().getCurrentLevel() != 1) {
				dataManager.clearDatabaseTables();
		    	final Advance advance = dataManager.getAdvance();
				final Options options = dataManager.getOptions();
				final List<Trophy> trophies = dataManager.getTropies();
				profileManager.getProfile().setAdvance(advance);
				profileManager.getProfile().setOptions(options);
				profileManager.getProfile().setTrophies(trophies);
				prefs.setLevel(advance.getCurrentLevel());
			}
		} else if (prevScreenState == START 
				&& nextScreenState == UPGRADES
				&& profileManager.getProfile().getUpgrades() == null) {
			final List<Upgrade> upgrades = dataManager.getUpgrades();
			profileManager.getProfile().setUpgrades(upgrades);
		} else if (prevScreenState == OPTIONS 
				&& nextScreenState == MAIN_MENU
				&& profileManager.updateGameOptions()) {
			final Options options = profileManager.getProfile().getOptions();
			dataManager.updateOptions(options);
		} else if (prevScreenState == UPGRADES 
				&& nextScreenState == START) {
			if (profileManager.updateUpgrades()) {
				final Profile profile = profileManager.getProfile();
				dataManager.updateUpgrades(profile.getUpgrades());
				if (profileManager.updateStarsBalance()) {
					dataManager.updateAdvance(profile.getAdvance());
				}
			}
			profileManager.getUpgradesHistory().clear();
			profileManager.getUpgradesCache().clear();
		} else if (prevScreenState == ScreenState.REVIEW
				&& nextScreenState == GAME_PLAY
				&& profileManager.getProfile().getUpgrades() == null) {
			final List<Upgrade> upgrades = dataManager.getUpgrades();
			profileManager.getProfile().setUpgrades(upgrades);
		} else if (prevScreenState == GAME_PLAY 
				&& nextScreenState == START) {
			if (profileManager.updateGamePlayOptions()) {
				final Options options = profileManager.getProfile().getOptions();
				dataManager.updateOptions(options);
			}
			final PlayerPreferences prefs = profileManager.getPlayerPrefs();
			if (prefs.getIsLevelPassed()
					&& profileManager.checkIsNewStars()) {
				dataManager.updateAdvance(profileManager.getProfile().getAdvance());
				dataManager.updateTrophy(profileManager.getProfile().getTrophies());
				prefs.setIsLevelPassed(false);
				prefs.setLevelStarsAmount(0);
			}
			final int currentLevel = profileManager.getProfile().getAdvance().getCurrentLevel(); 
			if (prefs.getLevel() != currentLevel) {
				prefs.setLevel(currentLevel);
			}
		}
	}
	
	private void callAudioManagerTask() {
		final Options options = profileManager.getProfile().getOptions();
		if (options.getEvent() == null) {
			final Options.Event event = new Options.Event() {
				@Override
				public void onIsMusicChange(Options sender) {
					audioManager.updateMusicState(sender.isMusic());
				}
			};
			options.setEvent(event);
		}
	}
	
	private Config callConfigImport() {
		final Language language = profileManager.getProfile().getOptions().getLanguage();
		final int level = playerPrefs.getLevel();
		if (nextScreenState != ScreenState.GAME_PLAY) {
			return dataManager.importGameScreenConfig(language, level, nextScreenState);
		}
		return dataManager.importGamePlayScreenConfig(language, level, nextScreenState);
	}
	
	private void callTexturesImport(Config config) {
		if (config.getTextures() != null) {
			dataManager.initTextureImporter(config.getTextures());
		}
	}
	
	private void callAnimsImport(Config config) {
		if (config.getSprites() != null) {
			dataManager.initAnimsImporter(config.getSprites());
		}
	}
	
	private void callFontsImport(Config config) {
		if (config.getFonts() != null) {
			dataManager.initFontImporter(config.getFonts());
		}
	}
	
	private void callMusicImport(Config config) {
		if (prevScreenState == null 
				&& nextScreenState == MAIN_MENU
				|| prevScreenState == ScreenState.REVIEW 
				&& nextScreenState == GAME_PLAY
				|| prevScreenState == GAME_PLAY 
				&& nextScreenState == START
				|| prevScreenState == GAME_PLAY 
				&& nextScreenState == GAME_PLAY
				&& config.getMusic() != null) {
			final Music music = dataManager.importMusic(config.getMusic());
			audioManager.setMusic(music);
		}
	}
	
	private void callSoundsImport(Config config) {
		if (config.getSounds() != null) {
			final Map<String, Sound> sounds = dataManager.importSounds(config.getSounds());
			audioManager.setSounds(sounds);
		}
	}
	
	public Screen callCreateScreen(Config config) {
		final ScreenBuilder builder = new ScreenBuilder();
		return builder.buildScreen(nextScreenState, config, profileManager);
	}
	
	private void callLogicManagerTask(Config config, Screen screen) {
		logicManager.initManagers(config);
		logicManager.activate(audioManager, (GamePlayScreen) screen);
	}
	
	// Properties ----------------------------------------
	public void setLogicManager(LogicManager logicManager) {
		this.logicManager = logicManager;
	}
	
}
