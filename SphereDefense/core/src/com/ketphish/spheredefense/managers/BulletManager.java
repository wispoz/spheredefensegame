package com.ketphish.spheredefense.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ketphish.spheredefense.interfaces.Updatable;
import com.ketphish.spheredefense.models.gameobjects.Auxiliary;
import com.ketphish.spheredefense.models.gameobjects.Bullet;
import com.ketphish.spheredefense.models.gameobjects.Tower;

public final class BulletManager
implements Updatable {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private Map<String, Bullet> bullets;
	private final List<Bullet> prototypes;
	
	// Constructors --------------------------------------
	public BulletManager() {
		prototypes = new ArrayList<Bullet>();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public void update(float deltaTime) {
		for (int i = 0; i < prototypes.size(); i++) {
			prototypes.get(i).update(deltaTime);
		}
	}

	// Methods -------------------------------------------
	public void addBullet(Tower tower) {
		final String bulletName = tower.getBulletName();
		try {
			final Bullet bullet = bullets.get(bulletName).clone();
			bullet.setPosition(tower.getPosition());
			bullet.setDamage(tower.getDamage());
			bullet.setEnemy(tower.getEnemy());
			bullet.setMaxDistance(tower.getRange());
			prototypes.add(bullet);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public void addBullet(Auxiliary auxil) {
		try {
			final Bullet bullet = bullets.get(auxil.getBulletName()).clone();
			bullet.setPosition(auxil.getPosition());
			bullet.setDamage(auxil.getDamage());
			bullet.setEnemy(auxil.getEnemy());
			bullet.setMaxDistance(0);
			prototypes.add(bullet);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	} 
	
	public void remove(Bullet bullet) {
		prototypes.remove(bullet);
	}
	
	// Properties ----------------------------------------
	public Map<String, Bullet> getBullets() {
		return bullets;
	}
	
	public void setBullets(Map<String, Bullet> bullets) {
		for (Bullet bullet : bullets.values()) {
			bullet.setBulletManager(this);
		}
		this.bullets = bullets;
	}
	
	public List<Bullet> getPrototypes() {
		return prototypes;
	}
	
}
