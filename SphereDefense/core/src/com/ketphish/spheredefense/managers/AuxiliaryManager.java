package com.ketphish.spheredefense.managers;

import java.util.List;
import java.util.Map;

import com.ketphish.spheredefense.interfaces.Updatable;
import com.ketphish.spheredefense.models.gameobjects.Auxiliary;
import com.ketphish.spheredefense.models.gameobjects.Enemy;

public final class AuxiliaryManager
implements Updatable {
	// Inner ---------------------------------------------
	public interface Event {
		void onFireCircleAvailable(AuxiliaryManager sender);
		void onEnergyCircleAvailable(AuxiliaryManager sender);
		void onShotFireCircle(AuxiliaryManager sender);
		void onShotEnergyCircle(AuxiliaryManager sender);
	}
	
	// Fields --------------------------------------------
	private final LogicManager logicManager;
	private Map<String, Auxiliary> auxiliaries;
	private boolean isIdle;
	private Event event;
	
	// Constructors --------------------------------------
	public AuxiliaryManager(LogicManager logicManager) {
		this.logicManager = logicManager;
		isIdle = true;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public void update(float deltaTime) {
		if (!isIdle) {
			for (Auxiliary auxil : auxiliaries.values()) {
				auxil.update(deltaTime);
				if (auxil.isReloaded()) {
					if (auxil.getName().equals("fire_circle")) {
						if (event != null) {
							event.onFireCircleAvailable(this);
						}
					}
					if (auxil.getName().equals("energy_circle")) {
						if (event != null) {
							event.onEnergyCircleAvailable(this);
						}
					}
				}
			}
		}
	}

	// Methods -------------------------------------------
	public void shootFireCircle(List<Enemy> enemies) {
		auxiliaries.get("fire_circle").findEnemy(enemies);
		logicManager.getBulletManager().addBullet(auxiliaries.get("fire_circle"));
		if (event != null) {
			event.onShotFireCircle(this);
		}
		auxiliaries.get("fire_circle").reload();
	}
	
	public void shootEnergyCircle(List<Enemy> enemies) {
		auxiliaries.get("energy_circle").findEnemy(enemies);
		logicManager.getBulletManager().addBullet(auxiliaries.get("energy_circle"));
		if (event != null) {
			event.onShotEnergyCircle(this);
		}
		auxiliaries.get("energy_circle").reload();
	}
	
	public void activate() {
		isIdle = false;
	}
	
	public void deActivate() {
		isIdle = true;
	}
	
	// Properties ----------------------------------------
	public Map<String, Auxiliary> getAuxiliaries() {
		return auxiliaries;
	}
	
	public void setAuxiliaries(Map<String, Auxiliary> auxiliaries) {
		this.auxiliaries = auxiliaries;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}
