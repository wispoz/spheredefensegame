package com.ketphish.spheredefense.managers;

import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.ketphish.spheredefense.SphereDefenseGame;
import com.ketphish.spheredefense.asynctasks.AsyncGameTask;
import com.ketphish.spheredefense.interfaces.Updatable;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.views.Screen;

public final class GameLogic
implements Updatable {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	private final SphereDefenseGame game;
	private final ProfileManager profileManager;
	private LogicManager logicManager;
	private boolean isAsyncTasks;
	private boolean isAsyncGameTaskExecuting;
	
	// Constructors --------------------------------------
	public GameLogic(SphereDefenseGame game) {
		this.game = game;
		profileManager = new ProfileManager();
		isAsyncTasks = false;
		isAsyncGameTaskExecuting = false;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public void update(float deltaTime) {
		if (isAsyncTasks && !isAsyncGameTaskExecuting) {
			final DataManager dataManager = game.getDataManager();
			final AudioManager audioManager = game.getAudioManager();
			if (dataManager.getIsAssetLoaded()) {
				final Drawer drawer = game.getDrawer();
				if (dataManager.isTexturesImported()) {
					drawer.setTextures(dataManager.importTextures());
				}
				if (dataManager.isAnimsImported()) {
					drawer.setAnims(dataManager.importAnims());
				}
				if (dataManager.isFontsImported()) {
					drawer.setFonts(dataManager.importFonts());
				}
				audioManager.playMusic();
				game.getGameScreenManager().changeToNextScreenState();
				isAsyncTasks = false;
			}
		} else if (!isAsyncTasks 
				&& !isAsyncGameTaskExecuting 
				&& logicManager != null) {
			logicManager.update(deltaTime);
		}
	}
	
	// Methods -------------------------------------------
	public void initGameScreenManagerEvent() {
		final GameScreenManager gameScreenManager = game.getGameScreenManager();
		final DataManager dataManager = game.getDataManager();
		final Drawer drawer = game.getDrawer();
		gameScreenManager.setEvent(new GameScreenManager.Event() {
			@Override
			public void onChangeGameScreenState(final GameScreenManager sender) {
				drawer.disposeTextures();
				drawer.disposeAnims();
				drawer.disposeFonts();
				dataManager.reset();
				final ScreenState prev = sender.getPrevState();
				final ScreenState next = sender.getNextState();
				final AsyncGameTask.Event taskEvent = new AsyncGameTask.Event() {
					@Override
					public void onComplete(Screen screen) {
						sender.setNextScreen(screen);
						isAsyncGameTaskExecuting = false;
					}
				};
				final AsyncGameTask task = new AsyncGameTask(
						dataManager, 
						profileManager,
						game.getAudioManager(),
						profileManager.getPlayerPrefs(),
						prev, next, taskEvent);
				if (next == ScreenState.GAME_PLAY) {
					initLogicManager();
					task.setLogicManager(logicManager);
				}
				isAsyncTasks = true;
				isAsyncGameTaskExecuting = true;
				final AsyncExecutor executor = new AsyncExecutor(1);
				executor.submit(task);
			}
		});
	}
	
	public void initLogicManager() {
		logicManager = new LogicManager(this);
	}
	
	// Properties ----------------------------------------
	public SphereDefenseGame getGame() {
		return game;
	}
	
	public ProfileManager getProfileManager() {
		return profileManager;
	}
	
	public LogicManager getLogicManager() {
		return logicManager;
	}
	
}
