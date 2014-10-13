package com.ketphish.spheredefense.models.gameobjects;

import java.util.List;

import com.ketphish.spheredefense.models.GameObject;

public final class Auxiliary
extends GameObject {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	private Enemy enemy;
	private String bulletName;
	private float attackRate;
	private float damage;
	private float elapsedTime;
	private boolean isReloaded;
	
	// Constructors --------------------------------------
	public Auxiliary() {
		isReloaded = true;
		elapsedTime = 0;
	}
	
	// Extends -------------------------------------------
	@Override
	public void update(float deltaTime) {
		if (!isReloaded) {
			elapsedTime += deltaTime;
			if (elapsedTime > attackRate) {
				isReloaded = true;
				elapsedTime = 0;
			}
		}
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	public void findEnemy(List<Enemy> enemies) {
		float maxHealth = enemies.get(0).getHealth();
		int index = 0;
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getHealth() > maxHealth) {
				index = i;
			}
		}
		enemy = enemies.get(index);
	}
	
	public void reload() {
		isReloaded = false;
	}
	
	// Properties ----------------------------------------
	public Enemy getEnemy() {
		return enemy;
	}
	
	public String getBulletName() {
		return bulletName;
	}

	public void setBulletName(String bulletName) {
		this.bulletName = bulletName;
	}

	public float getAttackRate() {
		return attackRate;
	}

	public void setAttackRate(float attackRate) {
		this.attackRate = attackRate;
	}
	
	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	public boolean isReloaded() {
		return isReloaded;
	}
	
}
