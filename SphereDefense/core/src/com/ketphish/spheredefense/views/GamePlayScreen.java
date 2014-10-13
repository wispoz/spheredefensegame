package com.ketphish.spheredefense.views;

import static com.ketphish.spheredefense.managers.GameScreenManager.ScreenState.GAME_PLAY;
import static com.ketphish.spheredefense.managers.GameScreenManager.ScreenState.START;

import java.util.Comparator;
import java.util.Map;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.ketphish.spheredefense.helpers.MenuCaller;
import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.models.profile.Options;
import com.ketphish.spheredefense.views.panels.BulletField;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.Button.ButtonState;
import com.ketphish.spheredefense.views.panels.EffectField;
import com.ketphish.spheredefense.views.panels.EnemyField;
import com.ketphish.spheredefense.views.panels.GamePlayField;
import com.ketphish.spheredefense.views.panels.Label;
import com.ketphish.spheredefense.views.panels.LevelMapField;
import com.ketphish.spheredefense.views.panels.TowerField;
import com.ketphish.spheredefense.views.panels.menus.TowerMenu;

public final class GamePlayScreen 
extends GameScreen {
	// Inner ---------------------------------------------
	public enum State {
		GAME,
		PAUSE,
		OPTIONS,
		GAME_FINISHED
	}

	// Fields --------------------------------------------
	private LevelMapField levelMapField;
	private GamePlayField gamePlayField;
	private TowerField towerField;
	private BulletField bulletField;
	private EnemyField enemyField;
	private EffectField effectField;
	private final MenuCaller menuCaller;
	private Map<String, TowerMenu> towersMenu;
	private final Comparator<Panel> comparator;
	private boolean isTowerMenu;
	private State state;
	
	// Constructors --------------------------------------
	public GamePlayScreen() {
		state = State.GAME;
		menuCaller = new MenuCaller();
		comparator = new Comparator<Panel>() {
			@Override
			public int compare(Panel o1, Panel o2) {
				final float zIndex1 = o1.getZIndex();
				final float zIndex2 = o2.getZIndex();
				if (zIndex1 < zIndex2) {
					return 1;
				} else if (zIndex1 > zIndex2) {
					return -1;
				}
				return 0;
			}
		};
		isTowerMenu = false;
	}
	
	// Extends -------------------------------------------
	@Override
	public void draw(float deltaTime) {
		final Array<Panel> panels = new Array<Panel>();
		for (Panel panel : super.panels) {
			panels.add(panel);
		}
		if (state == State.GAME) {
			panels.addAll(towerField.getTowerPanels());
			panels.addAll(bulletField.getBulletPanels(deltaTime));
			panels.addAll(enemyField.getEnemyPanels(deltaTime));
			panels.addAll(effectField.getEffectPanels(deltaTime));
		}
		panels.sort(comparator);
		for (Panel panel : panels) {
			panel.draw(drawer, deltaTime);
		}
	}
	
	@Override
	public void bindData(ProfileManager profileManager) {
		if (!profileManager.getProfile().getOptions().isMusic()) {
			buttons.get("btn_music").setTextureName("btn_music_off");
		}
		if (!profileManager.getProfile().getOptions().isSound()) {
			buttons.get("btn_sound").setTextureName("btn_sound_off");
		}
		profileManager.initOptionsCache();
	}
	
	@Override
	public void adjustPanels(boolean isSorting) {
		super.adjustPanels(isSorting);
		for (Label label : levelMapField.getLabels()) {
			if (!label.getName().equals("bg_main")
					&& !label.getName().equals("lbl_road")) {
				label.setZIndex(label.getRectangle().y);
			}
		}
		for (Button button : levelMapField.getButtons().values()) {
			button.setZIndex(button.getRectangle().y);
		}
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_firecircle").getRectangle(), true, true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_energycircle").getRectangle(), true, true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_play").getRectangle(), false, true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_next_wave").getRectangle(), false, true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_pause").getRectangle(), false, false);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_options").getRectangle(), false, false);
		Preset.getInstance().floatPositionToViewport(labels.get("lbl_status_bar").getRectangle(), true, false);
		Preset.getInstance().floatPositionToViewport(labels.get("lbl_energy").getRectangle(), true, false);
		Preset.getInstance().floatPositionToViewport(labels.get("lbl_diamond").getRectangle(), true, false);
		Preset.getInstance().floatPositionToViewport(labels.get("lbl_wave").getRectangle(), true, false);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_energy").getRectangle(), true, false);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_diamonds").getRectangle(), true, false);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_current_wave").getRectangle(), true, false);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_separator").getRectangle(), true, false);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_waves_count").getRectangle(), true, false);
	}
	
	@Override
	protected void onButtonTouch(Button sender) {
		hideTowerMenu();
		if (sender.getName().equals("btn_pause")) {
			state = State.PAUSE;
			togglePause();
		} else if (sender.getName().equals("btn_options")) {
			state = State.OPTIONS;
			toggleOptions();
		} else if (sender.getName().equals("btn_play")) {
			sender.toggleVisibility();
			buttons.get("btn_next_wave").toggleVisibility();
			buttons.get("btn_next_wave").setButtonState(ButtonState.DISABLED);
		} else if (sender.getName().equals("btn_next_wave")) {
			sender.setButtonState(ButtonState.DISABLED);
		} else if (sender.getName().equals("btn_music")) {
			if (getOptions().isMusic()) {
				getOptions().setIsMusic(false);
				buttons.get("btn_music").setTextureName("btn_music_off");
			} else {
				getOptions().setIsMusic(true);
				buttons.get("btn_music").setTextureName("btn_music_on");
			}
		} else if (sender.getName().equals("btn_sound")) {
			if (getOptions().isSound()) {
				getOptions().setIsSound(false);
				buttons.get("btn_sound").setTextureName("btn_sound_off");
			} else {
				getOptions().setIsSound(true);
				buttons.get("btn_sound").setTextureName("btn_sound_on");
			}
		} else if (sender.getName().equals("btn_restart_1")
				|| sender.getName().equals("btn_restart_2")) {
			gamePlayField.deactivate();
			game.getGameScreenManager().changeScreenState(GAME_PLAY);
		} else if (sender.getName().equals("btn_quit")) {
			gamePlayField.deactivate();
			game.getGameScreenManager().changeScreenState(START);
		} else if (sender.getName().equals("btn_continue")) {
			game.getGameScreenManager().changeScreenState(START);
		}
	}
	
	@Override
	public void activate() {
		super.activate();
		Button.Event buttonEvent = new Button.Event() {
			@Override
			public void onTouchDown(Button sender) {
				if (sender.getName().equals("btn_play")
						|| sender.getName().equals("btn_next_wave")
						|| sender.getName().equals("btn_pause")
						|| sender.getName().equals("btn_options")
						|| sender.getName().equals("btn_restart_1")
						|| sender.getName().equals("btn_restart_2")
						|| sender.getName().equals("btn_continue")
						|| sender.getName().equals("btn_quit")
						|| sender.getName().equals("btn_music")
						|| sender.getName().equals("btn_sound")) {
					game.getAudioManager().playSound("btn_touch", .5f);
				}
			}
			@Override
			public void onTouchUp(Button sender) {
				onButtonTouch(sender);
			}
		};
		for (Button button : buttons.values()) {
			button.getEvents().add(buttonEvent);
		}
		Button.Event towerPlaceHolderbuttonEvent = new Button.Event() {
			@Override
			public void onTouchDown(Button sender) {
				game.getAudioManager().playSound("btn_tower_place_holder_touch", .5f);
			}
			@Override
			public void onTouchUp(Button sender) {
				onTowerPlaceHolderButtonTouch(sender);
			}
		};
		for (Button button : levelMapField.getButtons().values()) {
			button.getEvents().add(towerPlaceHolderbuttonEvent);
		}
		GamePlayField.Event gamePlayFieldEvent = new GamePlayField.Event() {
			@Override
			public void onTouchUp(GamePlayField sender) {
				onGamePlayFieldTouch(sender);
			}
		};
		gamePlayField.getEvents().add(gamePlayFieldEvent);
		gamePlayField.activate();
		final Button.Event towerMenuButtonEvent = new Button.Event() {
			@Override
			public void onTouchUp(Button sender) {
				game.getAudioManager().playSound("btn_build_menu_touch", .5f);
				onButtonTouch(sender);
			}
			@Override
			public void onTouchDown(Button sender) { }
		};
		for (TowerMenu towerMenu : towersMenu.values()) {
			for (Button button : towerMenu.getButtons().values()) {
				button.getEvents().add(towerMenuButtonEvent);
			}
		}
	}
	
	@Override
	public void deactivate() {
		for (TowerMenu menu : towersMenu.values()) {
			for (Button button : menu.getButtons().values()) {
				button.deactivate();
			}
		}
		for (Button button : levelMapField.getButtons().values()) {
			button.deactivate();
		}
		for (Button button : buttons.values()) {
			button.deactivate();
		}
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	protected void onGamePlayFieldTouch(GamePlayField sender) {
		if (state == State.PAUSE) {
			state = State.GAME;
			togglePause();
		} else if (state == State.OPTIONS) {
			state = State.GAME;
			toggleOptions();
		}
		if (!isTowerMenu) {
			hideTowerMenu();
		} else {
			isTowerMenu = false;
		}
	}
	
	protected void onTowerPlaceHolderButtonTouch(Button sender) {
		hideTowerMenu();
		menuCaller.setName(sender.getName());
		sender.getRectangle().getCenter(menuCaller.getCenter());
		towersMenu.get("build_tower_light_menu").updatePosition(sender.getRectangle());
		towersMenu.get("build_tower_light_menu").toggleVisibility();
	}
	
	public void showTowerMenu(GamePlayField sender, 
			String towerName, Rectangle towerRectangle) {
		isTowerMenu = true;
		String towerMenuName = null;
		if (towerName.equals("cone_tower_light")) {
			towerMenuName = "tower_cone_light_menu";
		} else if (towerName.equals("pyramid_tower_light")) {
			towerMenuName = "tower_pyramid_light_menu";
		} else if (towerName.equals("cube_tower_light")) {
			towerMenuName = "tower_cube_light_menu";
		} else if (towerName.equals("cylinder_tower_light")) {
			towerMenuName = "tower_cylinder_light_menu";
		} else if (towerName.equals("cone_tower_heavy")) {
			towerMenuName = "tower_cone_heavy_menu";
		} else if (towerName.equals("pyramid_tower_heavy")) {
			towerMenuName = "tower_pyramid_heavy_menu";
		} else if (towerName.equals("cube_tower_heavy")) {
			towerMenuName = "tower_cube_heavy_menu";
		} else if (towerName.equals("cylinder_tower_heavy")) {
			towerMenuName = "tower_cylinder_heavy_menu";
		}
		towersMenu.get(towerMenuName).updatePosition(towerRectangle);
		menuCaller.setName(towerName);
		towerRectangle.getCenter(menuCaller.getCenter());
		if (towersMenu.get(towerMenuName).getState() == TowerMenu.State.INACTIVE) {
			towersMenu.get(towerMenuName).toggleVisibility();
		}
	}
	
	public void hideTowerMenu() {
		for (TowerMenu towerMenu : towersMenu.values()) {
			if (towerMenu.isVisible) {
				towerMenu.toggleVisibility();
				if (labels.get("lbl_tower_range").isVisible()) {
					labels.get("lbl_tower_range").toggleVisibility();
				}
				break;
			}
		}
	}
	
	public void showTowerRange(Rectangle rectangle, float range) {
		final float centerX = rectangle.x + rectangle.width / 2;
		final float centerY = rectangle.y + rectangle.height/ 2;
		final float size = range * 2;
		final float x = centerX - range;
		final float y = centerY - range;
		labels.get("lbl_tower_range").setRectangle(new Rectangle(x, y, size, size));
		labels.get("lbl_tower_range").toggleVisibility();
	}
	
	private void togglePause() {
		if (state == State.PAUSE) {
			levelMapField.hideIdleButtons();
		} else {
			levelMapField.showIdleButtons();
		}
		labels.get("bg_splash").toggleVisibility();
		labels.get("lbl_pause").toggleVisibility();
		buttons.get("btn_pause").toggleVisibility();
		buttons.get("btn_options").toggleVisibility();
		buttons.get("btn_play").toggleVisibility();
		buttons.get("btn_firecircle").toggleVisibility();
		buttons.get("btn_energycircle").toggleVisibility();
		hideTowerMenu();
	}
	
	private void toggleOptions() {
		if (state == State.OPTIONS) {
			levelMapField.hideIdleButtons();
		} else {
			levelMapField.showIdleButtons();
		}
		labels.get("bg_splash").toggleVisibility();
		labels.get("lbl_options").toggleVisibility();
		buttons.get("btn_music").toggleVisibility();
		buttons.get("btn_sound").toggleVisibility();
		buttons.get("btn_restart_1").toggleVisibility();
		buttons.get("btn_quit").toggleVisibility();
		buttons.get("btn_pause").toggleVisibility();
		buttons.get("btn_options").toggleVisibility();
		buttons.get("btn_play").toggleVisibility();
		buttons.get("btn_firecircle").toggleVisibility();
		buttons.get("btn_energycircle").toggleVisibility();
		hideTowerMenu();
	}
	
	public void showWinnerScreen(int starsAmount) {
		state = State.GAME_FINISHED;
		gamePlayField.deactivate();
		levelMapField.hideIdleButtons();
		labels.get("bg_splash").toggleVisibility();
		labels.get("lbl_win").toggleVisibility();
		switch (starsAmount) {
		case 1:
			labels.get("lbl_stars_1").toggleVisibility();
			break;
		case 2:
			labels.get("lbl_stars_2").toggleVisibility();
			break;
		case 3:
			labels.get("lbl_stars_3").toggleVisibility();
			break;
		default:
			break;
		}
		buttons.get("btn_firecircle").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_energycircle").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_pause").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_options").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_play").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_next_wave").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_continue").toggleVisibility();
		buttons.get("btn_restart_2").toggleVisibility();
	}
	
	public void showLoserScreen() {
		state = State.GAME_FINISHED;
		gamePlayField.deactivate();
		levelMapField.hideIdleButtons();
		labels.get("bg_splash").toggleVisibility();
		labels.get("lbl_loss").toggleVisibility();
		buttons.get("btn_firecircle").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_energycircle").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_pause").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_options").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_play").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_next_wave").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_restart_1").toggleVisibility();
		buttons.get("btn_quit").toggleVisibility();
	}
	
	// Properties ----------------------------------------
	public LevelMapField getLevelMapField() {
		return levelMapField;
	}
	
	public void setLevelMapField(LevelMapField levelMapField) {
		this.levelMapField = levelMapField;
		for (Label label : this.levelMapField.getLabels()) {
			addPanel(label);
		}
		for (Button button : this.levelMapField.getButtons().values()) {
			addPanel(button);
		}
	}
	
	public GamePlayField getGamePlayField() {
		return gamePlayField;
	}
	
	public void setGamePlayField(GamePlayField gamePlayField) {
		this.gamePlayField = gamePlayField;
		addPanel(this.gamePlayField);
	}
	
	public TowerField getTowerField() {
		return towerField;
	}
	
	public void setTowerField(TowerField towerField) {
		towerField.setScreen(this);
		this.towerField = towerField;
	}
	
	public BulletField getBulletField() {
		return bulletField;
	}
	
	public void setBulletField(BulletField bulletField) {
		bulletField.setScreen(this);
		this.bulletField = bulletField;
	}
	
	public EnemyField getEnemyField() {
		return enemyField;
	}
	
	public void setEnemyField(EnemyField enemyField) {
		enemyField.setScreen(this);
		this.enemyField = enemyField;
	}
	
	public EffectField getEffectField() {
		return effectField;
	}
	
	public void setEffectField(EffectField effectField) {
		effectField.setScreen(this);
		this.effectField = effectField;
	}

	public MenuCaller getMenuCaller() {
		return menuCaller;
	}
	
	public Map<String, TowerMenu> getTowersMenu() {
		return towersMenu;
	}
	
	public void setTowersMenu(Map<String, TowerMenu> towersMenu) {
		this.towersMenu = towersMenu;
		for (TowerMenu towerMenu : this.towersMenu.values()) {
			for (Button button : towerMenu.getButtons().values()) {
				addPanel(button);
			}
			addPanel(towerMenu);
		}
	}
	
	public State getState() {
		return state;
	}
	
	private Options getOptions() {
		return game.getGameLogic()
				.getProfileManager()
				.getProfile().getOptions();
	}

}
