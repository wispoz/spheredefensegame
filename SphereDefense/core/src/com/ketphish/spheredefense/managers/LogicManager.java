package com.ketphish.spheredefense.managers;

import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.helpers.LogicBinder;
import com.ketphish.spheredefense.helpers.UnitUpgrader;
import com.ketphish.spheredefense.interfaces.Updatable;
import com.ketphish.spheredefense.models.Config;
import com.ketphish.spheredefense.models.gameobjects.Tower;
import com.ketphish.spheredefense.models.level.Level;
import com.ketphish.spheredefense.models.profile.Upgrade;
import com.ketphish.spheredefense.views.GamePlayScreen;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.Button.ButtonState;
import com.ketphish.spheredefense.views.panels.GamePlayField;

public final class LogicManager
implements Updatable {
	// Inner ---------------------------------------------
	public interface Event {
		void onVictory(LogicManager sender);
		void onLoss(LogicManager logicManager);
	}
	
	public enum Mode {
		PASSIVE,
		ACTIVE
	}
	
	// Fields --------------------------------------------
	private final GameLogic gameLogic;
	private final LogicBinder logicBinder;
	private TowerManager towerManager;
	private AuxiliaryManager auxiliaryManager;
	private BulletManager bulletManager;
	private EnemyManager enemyManager;
	private EffectManager effectManager;
	private Level level;
	private int enemyCount;
	private Mode mode;
	private Event event;
	
	// Constructors --------------------------------------
	public LogicManager(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		this.logicBinder = new LogicBinder(this);
		mode = Mode.PASSIVE;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public void update(float deltaTime) {
		if (mode == Mode.ACTIVE) {
			towerManager.update(deltaTime);
			auxiliaryManager.update(deltaTime);
			bulletManager.update(deltaTime);
			enemyManager.update(deltaTime);
			effectManager.update(deltaTime);
			if (level.getEnergy() == 0) {
				event.onLoss(this);
				gameLogic.getProfileManager()
				.getPlayerPrefs().setIsLevelPassed(false);
			} else if (level.getEnemyCount() == enemyCount 
					&& level.getEnergy() > 0) {
				event.onVictory(this);
				gameLogic.getProfileManager().getPlayerPrefs().setIsLevelPassed(true);
			}
		}
	}
	
	// Methods -------------------------------------------
	public void activate(final AudioManager audioManager,
			final GamePlayScreen screen) {
		logicBinder.bind(audioManager, screen);
		mode = Mode.ACTIVE;
	}

	public void initManagers(Config config) {
		level = config.getLevel();
		level.setEnemyCount();
		if (towerManager == null) {
			towerManager = new TowerManager(this);
			towerManager.setTowers(config.getTowers());
		}
		if (auxiliaryManager == null) {
			auxiliaryManager = new AuxiliaryManager(this);
			auxiliaryManager.setAuxiliaries(config.getAuxiliaries());
		}
		if (bulletManager == null) {
			bulletManager = new BulletManager();
			bulletManager.setBullets(config.getBullets());
		}
		if (enemyManager == null) {
			enemyManager = new EnemyManager();
			enemyManager.setEnemies(config.getEnemies());
			enemyManager.setTrails(config.getTrails());
			enemyManager.setWaves(level.getWaves());
			enemyManager.setStartTime(level.getStartTime());
		}
		if (effectManager == null) {
			effectManager = new EffectManager();
			effectManager.setEffects(config.getEffects());
		}
		upgradeUnits();
	}
	
	private void upgradeUnits() {
		final List<Upgrade> upgrades = gameLogic.getProfileManager().getProfile().getUpgrades();
		final UnitUpgrader upgrader = new UnitUpgrader(
				towerManager.getTowers(), 
				auxiliaryManager.getAuxiliaries(), 
				upgrades);
		upgrader.upgrade();
	}
	
	public void onGamePlayFieldTouch(GamePlayField sender, GamePlayScreen screen) {
		if (mode == Mode.PASSIVE) {
			mode = Mode.ACTIVE;
		} else {
			screen.hideTowerMenu();
			final Vector2 touchPosition = sender.getTouchPosition().cpy();
			for (Tower tower : towerManager.getPrototypes()) {
				final Rectangle rectangle = screen.getTowerField().calcRectangle(tower);
				if (rectangle.contains(touchPosition)) {
					final Rectangle towerRectangle = screen.getTowerField().calcRectangle(tower);
					screen.showTowerMenu(sender, tower.getName(), towerRectangle);
					screen.showTowerRange(towerRectangle, tower.getRange());
					break;
				}
			}
		}
	}
	
	public void onPauseButtonTouch(Button sender) {
		mode = Mode.PASSIVE;
	}
	
	public void onOptionsButtonTouch(Button sender) {
		mode = Mode.PASSIVE;
	}
	
	public void onPlayButtonTouch(Button sender) {
		enemyManager.startWaves();
		auxiliaryManager.activate();
	}
	
	public void onNextWaveButtonTouch(Button sender) {
		enemyManager.startNewWave();
	}
	
	public void onFireCircleButtonTouch(Button sender) {
		sender.setButtonState(ButtonState.DISABLED);
		auxiliaryManager.shootFireCircle(enemyManager.getPrototypes());
	}
	
	public void onEnergyCircleButtonTouch(Button sender) {
		sender.setButtonState(ButtonState.DISABLED);
		auxiliaryManager.shootEnergyCircle(enemyManager.getPrototypes());
	}
	
	public void onBuildTowerLightButtonTouch(Vector2 center, Button sender) {
		String towerName = null;
		if (sender.getName().equals("btn_build_cone_light")) {
			towerName = "cone_tower_light";
		} else if (sender.getName().equals("btn_build_pyramid_light")) {
			towerName = "pyramid_tower_light";
		} else if (sender.getName().equals("btn_build_cube_light")) {
			towerName = "cube_tower_light";
		} else if (sender.getName().equals("btn_build_cylinder_light")) {
			towerName = "cylinder_tower_light";
		}
		final int cost = towerManager.addTower(towerName, center);
		subDiamods(cost);
	}
	
	public void onBuildTowerHeavyButtonTouch(GamePlayScreen screen, Button sender) {
		String towerName = null;
		if (sender.getName().equals("btn_build_cone_heavy")) {
			towerName = "cone_tower_heavy";
		} else if (sender.getName().equals("btn_build_pyramid_heavy")) {
			towerName = "pyramid_tower_heavy";
		} else if (sender.getName().equals("btn_build_cube_heavy")) {
			towerName = "cube_tower_heavy";
		} else if (sender.getName().equals("btn_build_cylinder_heavy")) {
			towerName = "cylinder_tower_heavy";
		}
		towerManager.removeTower(screen.getMenuCaller().getCenter());
		final int cost = towerManager.addTower(towerName, screen.getMenuCaller().getCenter());
		subDiamods(cost);
	}
	
	public void onSaleTowerButtonTouch(GamePlayScreen screen, Button sender) {
		final int cost = towerManager.removeTower(screen.getMenuCaller().getCenter());
		addDiamonds(cost);
	}
	
	public void onBulletShot(Tower tower) {
		bulletManager.addBullet(tower);
	}
	
	public void addDiamonds(int diamonds) {
		final int sum = level.getDiamonds() + diamonds;
		level.setDiamonds(sum);
	}
	
	public void subDiamods(int diamonds) {
		if (level.getDiamonds() >= diamonds) {
			final int sum = level.getDiamonds() - diamonds;
			level.setDiamonds(sum);
		}
	}
	
	public void subEnergy(int amount) {
		final int energy = level.getEnergy();
		if (energy <= amount) {
			level.setEnergy(0);
		} else {
			level.setEnergy(energy - amount);
		} 
	}
	
	// Properties ----------------------------------------
	public GameLogic getGameLogic() {
		return gameLogic;
	}
	
	public TowerManager getTowerManager() {
		return towerManager;
	}

	public AuxiliaryManager getAuxiliaryManager() {
		return auxiliaryManager;
	}

	public BulletManager getBulletManager() {
		return bulletManager;
	}
	
	public EnemyManager getEnemyManager() {
		return enemyManager;
	}
	
	public EffectManager getEffectManager() {
		return effectManager;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public int getDiamondsAmount() {
		return level.getDiamonds();
	}

	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public LogicBinder getLogicBinder() {
		return logicBinder;
	}

	public Level getLevel() {
		return level;
	}

	public void setTowerManager(TowerManager towerManager) {
		this.towerManager = towerManager;
	}

	public void setAuxiliaryManager(AuxiliaryManager auxiliaryManager) {
		this.auxiliaryManager = auxiliaryManager;
	}

	public void setBulletManager(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
	}

	public void setEnemyManager(EnemyManager enemyManager) {
		this.enemyManager = enemyManager;
	}

	public void setEffectManager(EffectManager effectManager) {
		this.effectManager = effectManager;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}
