package com.ketphish.spheredefense.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.helpers.Vector2Helper;
import com.ketphish.spheredefense.interfaces.Updatable;
import com.ketphish.spheredefense.models.gameobjects.Tower;

public final class TowerManager
implements Updatable {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final LogicManager logicManager;
	private Map<String, Tower> towers;
	private final List<Tower> prototypes;
	
	// Constructors --------------------------------------
	public TowerManager(LogicManager logicManager) {
		this.logicManager = logicManager;
		prototypes = new ArrayList<Tower>();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public void update(float deltaTime) {
		for (Tower tower : prototypes) {
			if (!tower.checkIsEnemy()) {
				tower.checkRange(logicManager.getEnemyManager().getPrototypes());
			}
			tower.update(deltaTime);
		}
	}
	
	// Methods -------------------------------------------
	public int addTower(String towerName, Vector2 position) {
		final int cost = towers.get(towerName).getBuyCost();
		if (logicManager.getDiamondsAmount() >= cost) {
			try {
				final Tower tower = towers.get(towerName).clone();
				tower.setPosition(position);
				prototypes.add(tower);
				return cost;
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	public int removeTower(Vector2 position) {
		int index = 0;
		for (Tower tower : prototypes) {
			if (Vector2Helper.compare(tower.getPosition(), position)) {
				break;
			}
			index++;
		}
		final int cost = prototypes.get(index).getSellCost();
		prototypes.remove(index);
		return cost;
	}
	
	// Properties ----------------------------------------
	public void setTowers(Map<String, Tower> towers) {
		for (Tower tower : towers.values()) {
			tower.setTowerManager(this);
		}
		this.towers = towers;
	}
	
	public Map<String, Tower> getTowers() {
		return towers;
	}
	
	public List<Tower> getPrototypes() {
		return prototypes;
	}
	
}
