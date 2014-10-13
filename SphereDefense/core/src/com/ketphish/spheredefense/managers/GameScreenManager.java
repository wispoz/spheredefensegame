package com.ketphish.spheredefense.managers;

import com.badlogic.gdx.utils.Disposable;
import com.ketphish.spheredefense.SphereDefenseGame;
import com.ketphish.spheredefense.views.GamePlayScreen;
import com.ketphish.spheredefense.views.Screen;
import com.ketphish.spheredefense.views.gamescreens.LoadingScreen;

public final class GameScreenManager
implements Disposable {
	// Inner ---------------------------------------------
	public enum ScreenState {
		LOADING,
		MAIN_MENU,
		START,
		UPGRADES,
		REVIEW,
		GAME_PLAY,
		ENCYCLOPEDIA,
		OPTIONS,
		ABOUT
	}
	
	public interface Event {
		void onChangeGameScreenState(GameScreenManager sender);
	}
	
	// Fields --------------------------------------------
	private final SphereDefenseGame game;
	private LoadingScreen loadingScreen;
	private Screen activeScreen;
	private ScreenState prev;
	private ScreenState current;
	private ScreenState next;
	private Event event;
	
	// Constructors --------------------------------------
	public GameScreenManager(SphereDefenseGame game) {
		this.game = game;
	}
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	public void draw(float deltaTime) {
		if (current == ScreenState.LOADING) {
			loadingScreen.render(deltaTime);
		} else if (current != ScreenState.GAME_PLAY) {
			activeScreen.draw(deltaTime);
		} else {
			((GamePlayScreen) activeScreen).draw(deltaTime);
		}
	}
	
	public void changeScreenState(ScreenState next) {
		if (this.next != next && current != ScreenState.LOADING) {
			if (current != null) {
				prev = current;
			}
			this.next = next;
			current = ScreenState.LOADING;
			if (activeScreen != null) {
				activeScreen.deactivate();
				activeScreen = null;
			}
			initLoadingScreen();
			if (event != null) {
				event.onChangeGameScreenState(this);
			}
		}
	}
	
	public void changeToNextScreenState() {
		if (current == ScreenState.LOADING) {
			prev = current;
			current = next;
			next = ScreenState.LOADING;
			loadingScreen.reset();
		}
	}

	private void initLoadingScreen() {
		if (loadingScreen == null) {
			loadingScreen = new LoadingScreen(game.getDrawer());
			loadingScreen.init();
		}
	}
	
	@Override
	public void dispose() {
		loadingScreen.dispose();
	}
	
	// Properties ----------------------------------------
	public void setNextScreen(Screen screen) {
		screen.setGame(game);
		screen.setDrawer(game.getDrawer());
		screen.activate();
		this.activeScreen = screen;
	}
	
	public Screen getActiveScreen() {
		return activeScreen;
	}
	
	public ScreenState getPrevState() {
		return prev;
	}

	public ScreenState getCurrentState() {
		return current;
	}

	public ScreenState getNextState() {
		return next;
	}

	public void setEvent(Event event) {
		if (this.event == null) {
			this.event = event;
		}
	}

}
