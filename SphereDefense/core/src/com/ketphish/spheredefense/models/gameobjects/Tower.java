package com.ketphish.spheredefense.models.gameobjects;

import java.util.List;

import com.ketphish.spheredefense.helpers.Vector2Helper;
import com.ketphish.spheredefense.managers.TowerManager;
import com.ketphish.spheredefense.models.GameObject;

public final class Tower 
extends GameObject {
	// Inner ---------------------------------------------
	public interface Event {
		void onShot(Tower tower);
	}
	
	// Fields --------------------------------------------
	private TowerManager towerManager;
	private Enemy enemy;
	private String bulletName;
	private float range;
	private float damage;
	private float attackRate;
	private float elapsedTime;
	private float buildRate;
	private int buyCost;
	private int sellCost;
	private boolean isBuilding;
	private Event event;
	
	// Constructors --------------------------------------
	public Tower() {
		isBuilding = true;
	}
	
	// Extends -------------------------------------------
	@Override
	public void update(float deltaTime) {
		if (isBuilding) {
			elapsedTime += deltaTime;
			if (elapsedTime > buildRate) {
				elapsedTime = attackRate + 1;
				isBuilding = false;
			}
		} else {
			if (elapsedTime < attackRate) {
				elapsedTime += deltaTime;
			} else if (enemy != null 
					&& !enemy.getIsInactive()
					&& checkIsInRange(enemy)) {
				elapsedTime = 0;
				if (event != null) {
					event.onShot(this);
				}
			}
		}
	}
	
	// Implementations -----------------------------------
	@Override
	public Tower clone() throws CloneNotSupportedException {
		Tower clone = (Tower) super.clone();
		clone.towerManager = this.towerManager;
		clone.event = this.event;
		return clone;
	}
	
	// Methods -------------------------------------------
	public boolean checkIsEnemy() {
		if (enemy != null) {
			if (enemy.getIsInactive()
					|| !checkIsInRange(enemy)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	private boolean checkIsInRange(Enemy enemy) {
		final float dst = Vector2Helper.dst(position, enemy.getPosition());
		return (dst <= range);
	}
	
	public void checkRange(List<Enemy> enemies) {
		for (Enemy enemy : enemies) {
			if (!enemy.getIsInactive()
					&& checkIsInRange(enemy)) {
				this.enemy = enemy;
			}
		}
	}
	
	// Properties ----------------------------------------
	public void setTowerManager(TowerManager towerManager) {
		this.towerManager = towerManager;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public String getBulletName() {
		return bulletName;
	}

	public void setBulletName(String bulletName) {
		this.bulletName = bulletName;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getAttackRate() {
		return attackRate;
	}

	public void setAttackRate(float attackRate) {
		this.attackRate = attackRate;
	}

	public float getLastAttacked() {
		return elapsedTime;
	}

	public void setLastAttacked(float lastAttacked) {
		this.elapsedTime = lastAttacked;
	}

	public float getBuildRate() {
		return buildRate;
	}

	public void setBuildRate(float buildRate) {
		this.buildRate = buildRate;
	}

	public float getElapsedTime() {
		return elapsedTime;
	}
	
	public int getBuyCost() {
		return buyCost;
	}

	public void setBuyCost(int buyCost) {
		this.buyCost = buyCost;
	}
	
	public int getSellCost() {
		return sellCost;
	}

	public void setSellCost(int sellCost) {
		this.sellCost = sellCost;
	}
	
	public boolean isBuilding() {
		return isBuilding;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
