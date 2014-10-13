package com.ketphish.spheredefense.models.gameobjects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.helpers.Vector2Helper;
import com.ketphish.spheredefense.managers.EnemyManager;
import com.ketphish.spheredefense.models.GameObject;
import com.ketphish.spheredefense.models.trail.Trail;

public final class Enemy
extends GameObject {
	// Inner ---------------------------------------------
	public enum State {
		MOVING,
		DESTROYED,
		SURVIVED
	}
	
	public interface Event {
		void onMoving(Enemy sender);
		void onHit(Enemy sender);
		void onSurvived(Enemy sender);
		void onDestroyed(Enemy sender);
	}
	
	// Fields --------------------------------------------
	private EnemyManager enemyManager;
	private Trail trail;
	private Map<String, Integer> resistances;
	private Vector2 start;
	private Vector2 target;
	private Vector2 direction;
	private float speed;
	private float health;
	private float maxHealth;
	private int damage;
	private int price;
	private int currentSnippet;
	private State state;
	private Event event;
	
	// Constructors --------------------------------------
	public Enemy() {
		resistances = new HashMap<String, Integer>();
		start = new Vector2();
		target = new Vector2();
		direction = new Vector2();
		currentSnippet = 0;
		state = State.MOVING;
	}
	
	// Extends -------------------------------------------
	@Override
	public void update(float deltaTime) {
		if (!getIsInactive()) {
			updatePosition(deltaTime);
			if (checkIsNextSnippet()
					&& checkIsCurrentSnippetEnd()) {
				if (!changeSnippet()) {
					removeEnemy(true);
				}
			}
		}
	}

	private boolean changeSnippet() {
		currentSnippet++;
		if (checkIsNextSnippet()) {
			start.set(trail.getSnippets().get(currentSnippet).getBegin());
			target.set(trail.getSnippets().get(currentSnippet).getEnd());
			direction.set(Vector2Helper.sub(target, start).nor());
			return true;
		}
		return false;
	}

	private void removeEnemy(boolean isSurvived) {
		if (isSurvived) {
			state = State.SURVIVED;
			if (event != null) {
				event.onSurvived(this);
			}
		} else {
			state = State.DESTROYED;
			if (event != null) {
				event.onDestroyed(this);
			}
		}
		enemyManager.removeEnemy(this);
	}
	
	// Implementations -----------------------------------
	@Override
	public Enemy clone() throws CloneNotSupportedException {
		Enemy clone = (Enemy) super.clone();
		clone.enemyManager = this.enemyManager;
		clone.resistances = new HashMap<String, Integer>(this.resistances);
		clone.start = this.start.cpy();
		clone.target = this.target.cpy();
		clone.direction = this.direction.cpy();
		clone.event = this.event;
		return clone;
	}
	
	// Methods -------------------------------------------
	private void initVectors() {
		start.set(trail.getSnippets().get(currentSnippet).getBegin());
		position.set(start);
		target.set(trail.getSnippets().get(currentSnippet).getEnd());
		direction.set(Vector2Helper.sub(target, start).nor());
	}

	public void dealDamage(String bulletName, float damage) {
		if (!getIsDestroyed()) {
			if (resistances.containsKey(bulletName)) {
				final int pct = resistances.get(bulletName);
				damage -= ((float) pct / 10f);
			}
			health -= damage;
			if (health <= 0) {
				removeEnemy(false);
			} else if (event != null) {
				event.onHit(this);
			}
		}
	}
	
	private void updatePosition(float deltaTime) {
		position.mulAdd(direction, speed * deltaTime);
		if (event != null) {
			event.onMoving(this);
		}
	}
	
	public boolean checkIsNextSnippet() {
		return (currentSnippet < trail.getSnippets().size());
	}
	
	public boolean checkIsCurrentSnippetEnd() {
		return (Vector2Helper.dst(position, start) > Vector2Helper.dst(target, start));
	}
	
	// Properties ----------------------------------------
	public void setEnemyManager(EnemyManager enemyManager) {
		this.enemyManager = enemyManager;
	}
	
	public void setTrail(Trail trail) {
		this.trail = trail;
		initVectors();
	}

	public Map<String, Integer> getResistances() {
		return resistances;
	}
	
	public Vector2 getDirection() {
		return direction;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
		maxHealth = health;
	}
	
	public float getMaxHealth() {
		return maxHealth;
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public State getState() {
		return state;
	}
	
	public boolean getIsInactive() {
		return (state != State.MOVING);
	}
	
	public boolean getIsDestroyed() {
		return (state == State.DESTROYED); 
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
