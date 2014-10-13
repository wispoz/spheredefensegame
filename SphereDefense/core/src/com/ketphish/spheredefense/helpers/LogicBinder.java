package com.ketphish.spheredefense.helpers;

import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.managers.AudioManager;
import com.ketphish.spheredefense.managers.AuxiliaryManager;
import com.ketphish.spheredefense.managers.EnemyManager;
import com.ketphish.spheredefense.managers.LogicManager;
import com.ketphish.spheredefense.managers.LogicManager.Mode;
import com.ketphish.spheredefense.models.gameobjects.Bullet;
import com.ketphish.spheredefense.models.gameobjects.Enemy;
import com.ketphish.spheredefense.models.gameobjects.Tower;
import com.ketphish.spheredefense.models.level.Level;
import com.ketphish.spheredefense.views.GamePlayScreen;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.Button.ButtonState;
import com.ketphish.spheredefense.views.panels.GamePlayField;
import com.ketphish.spheredefense.views.panels.menus.TowerMenu;

public final class LogicBinder {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final LogicManager logicManager;
	
	// Constructors --------------------------------------
	public LogicBinder(LogicManager logicManager) {
		this.logicManager = logicManager;
	}
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void bind(final AudioManager audioManager,
			final GamePlayScreen screen) {
		bindLogicManager(screen);
		bindScreenComponents(screen);
		bindLevel(screen);
		bindTowerManager(audioManager);
		bindAuxiliaryManager(screen, audioManager);
		bindBulletManager(screen, audioManager);
		bindEnemyManager(audioManager, screen);
	}

	private void bindLogicManager(final GamePlayScreen screen) {
		logicManager.setEvent(new LogicManager.Event() {
			@Override
			public void onVictory(LogicManager sender) {
				logicManager.setMode(Mode.PASSIVE);
				final int energy = logicManager.getLevel().getEnergy();
				final int startingEnergy = logicManager.getLevel().getStartingEnergy();
				final StarsAwarder awarder = new StarsAwarder();
				final int starsAmount = awarder.award(energy, startingEnergy);
				logicManager.getGameLogic().getProfileManager().getPlayerPrefs()
				.setLevelStarsAmount(starsAmount);
				screen.showWinnerScreen(starsAmount);
			}
			@Override
			public void onLoss(LogicManager logicManager) {
				logicManager.setMode(Mode.PASSIVE);
				screen.showLoserScreen();
			}
		});
	}
	
	private void bindScreenComponents(final GamePlayScreen screen) {
		screen.getBulletField().setDefaultZIndex();
		screen.getBulletField().setBullets(logicManager.getBulletManager().getPrototypes());
		screen.getTowerField().setTowers(logicManager.getTowerManager().getPrototypes());
		screen.getEnemyField().setEnemies(logicManager.getEnemyManager().getPrototypes());
		screen.getEffectField().setEffects(logicManager.getEffectManager().getPrototypes());
		screen.getTextFields().get("txt_energy").setText(logicManager.getLevel().getEnergy() + "");
		screen.getTextFields().get("txt_diamonds").setText(logicManager.getLevel().getDiamonds() + "");
		screen.getTextFields().get("txt_current_wave").setText("01");
		String str = logicManager.getLevel().getWaves().size() < 10 
				? "0" + logicManager.getLevel().getWaves().size() 
						: "" + logicManager.getLevel().getWaves().size();
		screen.getTextFields().get("txt_waves_count").setText(str);
		screen.getButtons().get("btn_firecircle").setButtonState(ButtonState.DISABLED);
		screen.getButtons().get("btn_energycircle").setButtonState(ButtonState.DISABLED);
		final Button.Event buttonEvent = new Button.Event() {
			@Override
			public void onTouchUp(Button sender) {
				if (sender.getName().equals("btn_pause")) {
					logicManager.onPauseButtonTouch(sender);
				} else if (sender.getName().equals("btn_options")) {
					logicManager.onOptionsButtonTouch(sender);
				} else if (sender.getName().equals("btn_play")) {
					logicManager.onPlayButtonTouch(sender);
				} else if (sender.getName().equals("btn_next_wave")) {
					logicManager.onNextWaveButtonTouch(sender);
				} else if (sender.getName().equals("btn_firecircle")) {
					logicManager.onFireCircleButtonTouch(sender);
				} else if (sender.getName().equals("btn_energycircle")) {
					logicManager.onEnergyCircleButtonTouch(sender);
				} else if (sender.getName().equals("btn_build_cone_light")
						|| sender.getName().equals("btn_build_pyramid_light")
						|| sender.getName().equals("btn_build_cube_light")
						|| sender.getName().equals("btn_build_cylinder_light")) {
					screen.hideTowerMenu();
					screen.getLevelMapField().getButtons().get(screen.getMenuCaller().getName()).setVisible(false);
					screen.getLevelMapField().getButtons().get(screen.getMenuCaller().getName()).setButtonState(ButtonState.DISABLED);
					logicManager.onBuildTowerLightButtonTouch(screen.getMenuCaller().getCenter(), sender);
				} else if (sender.getName().equals("btn_build_cone_heavy")
						|| sender.getName().equals("btn_build_pyramid_heavy")
						|| sender.getName().equals("btn_build_cube_heavy")
						|| sender.getName().equals("btn_build_cylinder_heavy")) {
					screen.hideTowerMenu();
					logicManager.onBuildTowerHeavyButtonTouch(screen, sender);
				} else if (sender.getName().equals("btn_sale_cone_light")
						|| sender.getName().equals("btn_sale_pyramid_light")
						|| sender.getName().equals("btn_sale_cube_light")
						|| sender.getName().equals("btn_sale_cylinder_light")
						|| sender.getName().equals("btn_sale_cone_heavy")
						|| sender.getName().equals("btn_sale_pyramid_heavy")
						|| sender.getName().equals("btn_sale_cube_heavy")
						|| sender.getName().equals("btn_sale_cylinder_heavy")) {
					logicManager.onSaleTowerButtonTouch(screen, sender);
					screen.getLevelMapField().showDisabledButton(screen.getMenuCaller().getCenter());
				}
			}
			@Override
			public void onTouchDown(Button sender) { }
		};
		for (Button button : screen.getButtons().values()) {
			button.getEvents().add(buttonEvent);
		}
		for (TowerMenu towerMenu : screen.getTowersMenu().values()) {
			for (Button button : towerMenu.getButtons().values()) {
				button.getEvents().add(buttonEvent);
			}
		}
		screen.getGamePlayField().getEvents().add(new GamePlayField.Event() {
			@Override
			public void onTouchUp(GamePlayField sender) {
				logicManager.onGamePlayFieldTouch(sender, screen);
			}
		});
	}
	
	public void showTowerPlaceHolder(GamePlayScreen screen, Vector2 center) {
		for (Button button : screen.getLevelMapField().getButtons().values()) {
			if (button.getRectangle().contains(center)) {
				if (!button.isVisible() 
						&& button.getState() == ButtonState.DISABLED) {
					button.setButtonState(ButtonState.IDLE);
					button.toggleVisibility();
				}
				break;
			}
		}
	}
	
	private void bindLevel(final GamePlayScreen screen) {
		logicManager.getLevel().setEvent(new Level.Event() {
			@Override
			public void onEnergyAmountChanged(Level sender) {
				screen.getTextFields().get("txt_energy").setText(logicManager.getLevel().getEnergy() + "");
			}
			@Override
			public void onDiamondsAmountChanged(Level sender) {
				screen.getTextFields().get("txt_diamonds").setText(logicManager.getLevel().getDiamonds() + "");
				for (Tower tower : logicManager.getTowerManager().getTowers().values()) {
					final int buyCost = tower.getBuyCost();
					ButtonState state = ButtonState.IDLE;
					if (logicManager.getLevel().getDiamonds() < buyCost) {
						state = ButtonState.DISABLED;
					}
					if (tower.getName().equals("cone_tower_light")) {
						screen.getTowersMenu().get("build_tower_light_menu").getButtons().get("btn_build_cone_light").setButtonState(state);
					} else if (tower.getName().equals("pyramid_tower_light")) {
						screen.getTowersMenu().get("build_tower_light_menu").getButtons().get("btn_build_pyramid_light").setButtonState(state);
					} else if (tower.getName().equals("cube_tower_light")) {
						screen.getTowersMenu().get("build_tower_light_menu").getButtons().get("btn_build_cube_light").setButtonState(state);
					} else if (tower.getName().equals("cylinder_tower_light")) {
						screen.getTowersMenu().get("build_tower_light_menu").getButtons().get("btn_build_cylinder_light").setButtonState(state);
					} else if (tower.getName().equals("cone_tower_heavy")) {
						screen.getTowersMenu().get("tower_cone_light_menu").getButtons().get("btn_build_cone_heavy").setButtonState(state);
					} else if (tower.getName().equals("pyramid_tower_heavy")) {
						screen.getTowersMenu().get("tower_pyramid_light_menu").getButtons().get("btn_build_pyramid_heavy").setButtonState(state);
					} else if (tower.getName().equals("cube_tower_heavy")) {
						screen.getTowersMenu().get("tower_cube_light_menu").getButtons().get("btn_build_cube_heavy").setButtonState(state);
					} else if (tower.getName().equals("cylinder_tower_heavy")) {
						screen.getTowersMenu().get("tower_cylinder_light_menu").getButtons().get("btn_build_cylinder_heavy").setButtonState(state);
					}
				}
			}
		});
	}
	
	private void bindTowerManager(final AudioManager audioManager) {
		final Tower.Event towerEvent = new Tower.Event() {
			@Override
			public void onShot(Tower tower) {
				logicManager.onBulletShot(tower);
				if (tower.getName().equals("cone_tower_light")
						|| tower.getName().equals("pyramid_tower_light")
						|| tower.getName().equals("cube_tower_light")
						|| tower.getName().equals("cylinder_tower_light")) {
					audioManager.playSound("tower_light_shot", 1f);
				} else if (tower.getName().equals("cone_tower_heavy")
						|| tower.getName().equals("pyramid_tower_heavy")
						|| tower.getName().equals("cube_tower_heavy")
						|| tower.getName().equals("cylinder_tower_heavy")) {
					audioManager.playSound("tower_heavy_shot", 1f);
				}
			}
		};
		for (Tower tower : logicManager.getTowerManager().getTowers().values()) {
			tower.setEvent(towerEvent);
		}
	}
	
	private void bindAuxiliaryManager(final GamePlayScreen screen,
			final AudioManager audioManager) {
		logicManager.getAuxiliaryManager().setEvent(new AuxiliaryManager.Event() {
			@Override
			public void onFireCircleAvailable(AuxiliaryManager sender) {
				if (logicManager.getEnemyManager().getPrototypes().size() > 0
						&& screen.getButtons().get("btn_firecircle").getState() == ButtonState.DISABLED) {
					screen.getButtons().get("btn_firecircle").setButtonState(ButtonState.IDLE);
				}
			}
			@Override
			public void onEnergyCircleAvailable(AuxiliaryManager sender) {
				if (logicManager.getEnemyManager().getPrototypes().size() > 0
						&& screen.getButtons().get("btn_energycircle").getState() == ButtonState.DISABLED) {
					screen.getButtons().get("btn_energycircle").setButtonState(ButtonState.IDLE);
				}
			}
			@Override
			public void onShotFireCircle(AuxiliaryManager sender) {
				audioManager.playSound("auxiliary_shot", 1f);
			}
			@Override
			public void onShotEnergyCircle(AuxiliaryManager sender) {
				audioManager.playSound("auxiliary_shot", 1f);
			}
		});
	}
	
	private void bindBulletManager(final GamePlayScreen screen,
			final AudioManager audioManager) {
		final Bullet.Event bulletEvent = new Bullet.Event() {
			@Override
			public void onMissEnemy(Bullet sender) {
				logicManager.getEffectManager().addBulletMissEffect(sender);
				audioManager.playSound("miss_explosion", 1f);
			}
			@Override
			public void onHitEnemy(Bullet sender) {
				if (!sender.getEnemy().getIsDestroyed()) {
					logicManager.getEffectManager().addBulletHitEffect(sender);
				}
				audioManager.playSound("hit_explosion", .4f);
			}
		};
		for (Bullet bullet : logicManager.getBulletManager().getBullets().values()) {
			bullet.setEvent(bulletEvent);
		}
	}
	
	private void bindEnemyManager(final AudioManager audioManager,
			final GamePlayScreen screen) {
		final Enemy.Event enemyEvent = new Enemy.Event() {
			@Override
			public void onSurvived(Enemy sender) {
				logicManager.subEnergy(sender.getDamage());
				audioManager.playSound("enemy_survive", 1f);
				final int enemyCount = logicManager.getEnemyCount();
				logicManager.setEnemyCount(enemyCount + 1);
			}
			@Override
			public void onMoving(Enemy sender) { }
			@Override
			public void onHit(Enemy sender) { }
			@Override
			public void onDestroyed(Enemy sender) {
				logicManager.getEffectManager().addEnemyDestroyedEffect(sender);
				logicManager.addDiamonds(sender.getPrice());
				audioManager.playSound("enemy_destroyed", 1f);
				final int enemyCount = logicManager.getEnemyCount();
				logicManager.setEnemyCount(enemyCount + 1);
			}
		};
		for (Enemy enemy : logicManager.getEnemyManager().getEnemies().values()) {
			enemy.setEvent(enemyEvent);
		}
		logicManager.getEnemyManager().setEvent(new EnemyManager.Event() {
			@Override
			public void onWavesFinished(EnemyManager sender) {
				screen.getButtons().get("btn_next_wave").setButtonState(ButtonState.DISABLED);
			}
			@Override
			public void onWaveChanged(EnemyManager sender) {
				final int currentWave = sender.getCurrentWave() + 1;
				String str = currentWave < 10 ? "0" + currentWave : "" + currentWave;
				screen.getTextFields().get("txt_current_wave").setText(str);
				if (screen.getButtons().get("btn_next_wave").getState() == ButtonState.IDLE) {
					screen.getButtons().get("btn_next_wave").setButtonState(ButtonState.DISABLED);
				}
			}
			@Override
			public void onWaveFoesEnd(EnemyManager sender) {
				if (screen.getButtons().get("btn_next_wave").getState() == ButtonState.DISABLED) {
					screen.getButtons().get("btn_next_wave").setButtonState(ButtonState.IDLE);
				}
			}
			@Override
			public void onEnemiesDestroyed(EnemyManager sender) {
				if (screen.getButtons().get("btn_firecircle").getState() == ButtonState.IDLE) {
					screen.getButtons().get("btn_firecircle").setButtonState(ButtonState.DISABLED);
				}
				if (screen.getButtons().get("btn_energycircle").getState() == ButtonState.IDLE) {
					screen.getButtons().get("btn_energycircle").setButtonState(ButtonState.DISABLED);
				}
			}
			@Override
			public void onFirstEnemyAdded(EnemyManager sender) {
				if (logicManager.getAuxiliaryManager().getAuxiliaries().get("fire_circle").isReloaded()) {
					if (screen.getButtons().get("btn_firecircle").getState() == ButtonState.DISABLED) {
						screen.getButtons().get("btn_firecircle").setButtonState(ButtonState.IDLE);
					}
				}
				if (logicManager.getAuxiliaryManager().getAuxiliaries().get("energy_circle").isReloaded()) {
					if (screen.getButtons().get("btn_energycircle").getState() == ButtonState.DISABLED) {
						screen.getButtons().get("btn_energycircle").setButtonState(ButtonState.IDLE);
					}
				}
			}
		});
	}
	
	// Properties ----------------------------------------
}
