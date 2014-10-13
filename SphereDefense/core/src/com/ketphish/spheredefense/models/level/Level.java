package com.ketphish.spheredefense.models.level;

import java.util.ArrayList;
import java.util.List;

public final class Level {
	// Inner ---------------------------------------------
	public interface Event {
		void onEnergyAmountChanged(Level sender);
		void onDiamondsAmountChanged(Level sender);
	}
	
	// Fields --------------------------------------------
	private final List<Wave> waves;
	private final float startTime;
	private int energy;
	private int diamonds;
	private int startingEnergy;
	private int enemyCount;
	private Event event;
	
	// Constructors --------------------------------------
	public Level(int energy, int diamonds, float startTime) {
		this.energy = energy;
		this.diamonds = diamonds;
		startingEnergy = this.energy;
		this.startTime = startTime;
		waves = new ArrayList<Wave>();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void setEnemyCount() {
		for (Wave wave : waves) {
			enemyCount += wave.getFoes().size();
		}
	}
	
	// Properties ----------------------------------------
	public List<Wave> getWaves() {
		return waves;
	}
	
	public float getStartTime() {
		return startTime;
	}
	
	public int getEnemyCount() {
		return enemyCount;
	}
	
	public int getStartingEnergy() {
		return startingEnergy;
	}
	
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
		if (event != null) {
			event.onEnergyAmountChanged(this);
		}
	}
	
	public int getDiamonds() {
		return diamonds;
	}
	
	public void setDiamonds(int diamonds) {
		this.diamonds = diamonds;
		if (event != null) {
			event.onDiamondsAmountChanged(this);
		}
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}
