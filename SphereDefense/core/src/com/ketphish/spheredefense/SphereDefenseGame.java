package com.ketphish.spheredefense;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ketphish.spheredefense.builders.MultipleVirtualViewportBuilder;
import com.ketphish.spheredefense.camera.OrthographicCameraWithVirtualViewport;
import com.ketphish.spheredefense.camera.VirtualViewport;
import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.managers.AudioManager;
import com.ketphish.spheredefense.managers.DataManager;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.managers.GameLogic;
import com.ketphish.spheredefense.managers.GameScreenManager;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.managers.InputManager;

public final class SphereDefenseGame 
extends Game {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private AudioManager audioManager;
	private DataManager dataManager;
	private GameLogic gameLogic;
	private GameScreenManager gameScreenManager;
	private InputManager inputManager;
	private Drawer drawer;
	private OrthographicCameraWithVirtualViewport camera;
	
	// Constructors --------------------------------------
	
	// Extends -------------------------------------------
	@Override
	public void create () {
		audioManager = new AudioManager(this);
		dataManager = new DataManager();
		gameScreenManager = new GameScreenManager(this);
		inputManager = new InputManager(this);
		drawer = new Drawer();
		gameLogic = new GameLogic(this);
		gameLogic.initGameScreenManagerEvent();
		gameScreenManager.changeScreenState(ScreenState.MAIN_MENU);
		Gdx.input.setInputProcessor(inputManager);
		initCamera();
	}
	
	@Override
	public void render () {
		final float deltaTime = Gdx.graphics.getDeltaTime();
		update(deltaTime);
		draw(deltaTime);
	}

	@Override
	public void pause() {
		super.pause();
	}
	
	@Override
	public void resume() {
		super.resume();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		updateCamera();
	}
	
	@Override
	public void dispose() {
		audioManager.dispose();
		dataManager.dispose();
		inputManager.unregisterAll();
		drawer.dispose();
		super.dispose();
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	private void update(float deltaTime) {
		gameLogic.update(deltaTime);
	}
	
	private void draw(float deltaTime) {
		Gdx.graphics.getGL20().glClearColor(0, 0, 1, 1);
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		drawer.setProjectionMatrix(camera.combined);
		drawer.begin();
		gameScreenManager.draw(deltaTime);
		drawer.end();
	}
	
	private void initCamera() {
		final MultipleVirtualViewportBuilder builder = 
				new MultipleVirtualViewportBuilder(
						Preset.getInstance().MIN_AREA_WIDTH, 
						Preset.getInstance().MIN_AREA_HEIGHT, 
						Preset.getInstance().MAX_AREA_WIDTH, 
						Preset.getInstance().MAX_AREA_HEIGHT);  
        final VirtualViewport viewport = 
        		builder.getVirtualViewport(
        				Gdx.graphics.getWidth(), 
        				Gdx.graphics.getHeight());
		camera = new OrthographicCameraWithVirtualViewport(viewport);
        updateCamera();
	}
	
	private void updateCamera() {
		camera.updateViewport();
		camera.position.set(
				Preset.getInstance().MIN_AREA_WIDTH / 2.0f, 
				Preset.getInstance().MIN_AREA_HEIGHT / 2.0f, 0);
		Preset.getInstance().setViewportSize(camera.viewportWidth, 
				camera.viewportHeight);
	}
	
	// Properties ----------------------------------------
	public AudioManager getAudioManager() {
		return audioManager;
	}

	public DataManager getDataManager() {
		return dataManager;
	}

	public GameLogic getGameLogic() {
		return gameLogic;
	}

	public GameScreenManager getGameScreenManager() {
		return gameScreenManager;
	}

	public InputManager getInputManager() {
		return inputManager;
	}
	
	public Drawer getDrawer() {
		return drawer;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
	
}
