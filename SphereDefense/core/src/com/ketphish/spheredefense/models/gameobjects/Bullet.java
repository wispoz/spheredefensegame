package com.ketphish.spheredefense.models.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.helpers.Vector2Helper;
import com.ketphish.spheredefense.managers.BulletManager;
import com.ketphish.spheredefense.models.GameObject;

public final class Bullet
extends GameObject {
	// Inner ---------------------------------------------
	public interface Event {
		void onMissEnemy(Bullet sender);
		void onHitEnemy(Bullet sender);
	}
	
	// Fields --------------------------------------------
	private BulletManager bulletManager;
	private Enemy enemy;
	private Vector2 start;
	private Vector2 target;
	private Vector2 direction;
	private float damage;
	private float speed;
	private float maxDistance;
	private boolean isHit;
	private Event event;

	// Constructors --------------------------------------
	public Bullet() {
		start = new Vector2();
		target = new Vector2();
		direction = new Vector2();
		maxDistance = 0;
		isHit = false;
	}
	
	// Extends -------------------------------------------
	@Override
	public void update(float deltaTime) {
		if (!enemy.getIsInactive() 
				&& Vector2Helper.dst(position, start) >= Vector2Helper.dst(target, start)) {
			isHit = true;
			removeBullet();
		} else if (maxDistance == 0 
				&& enemy.getIsInactive()
				|| maxDistance != 0 
				&& Vector2Helper.dst(position, start) >= maxDistance) {
			isHit = false;
			removeBullet();
		} else if (!enemy.getIsInactive()) {
			target.set(enemy.getPositionWithOffset());
			direction.set(Vector2Helper.sub(target, position).nor());
			position.mulAdd(direction, speed * deltaTime);
		} else {
			position.mulAdd(direction, speed * deltaTime);
		}
	}
	
	private void removeBullet() {
		if (isHit) {
			enemy.dealDamage(name, damage);
			if (event != null) {
				event.onHitEnemy(this);
			}
			bulletManager.remove(this);
		} else if (event != null) {
			event.onMissEnemy(this);
		}
		bulletManager.remove(this);
	}

	// Implementations -----------------------------------
	@Override
	public Bullet clone() throws CloneNotSupportedException {
		Bullet clone = (Bullet) super.clone();
		clone.bulletManager = this.bulletManager;
		clone.start = this.start.cpy();
		clone.target = this.target.cpy();
		clone.direction = this.direction.cpy();
		clone.event = this.event;
		return clone;
	}
	
	// Methods -------------------------------------------
	private void initVectors() {
		start.set(position);
		target.set(enemy.getPositionWithOffset());
		direction.set(Vector2Helper.sub(target, start).nor());
	}
	
	// Properties ----------------------------------------
	public void setBulletManager(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
	}

	public Enemy getEnemy() {
		return enemy;
	}
	
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
		initVectors();
	}

	public Vector2 getDirection() {
		return direction;
	}
	
	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void setMaxDistance(float maxDistance) {
		this.maxDistance = maxDistance;
	}
	
	public boolean isHit() {
		return isHit;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
