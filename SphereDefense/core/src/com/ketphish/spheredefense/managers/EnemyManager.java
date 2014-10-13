package com.ketphish.spheredefense.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.interfaces.Updatable;
import com.ketphish.spheredefense.models.gameobjects.Enemy;
import com.ketphish.spheredefense.models.level.Foe;
import com.ketphish.spheredefense.models.level.Wave;
import com.ketphish.spheredefense.models.trail.Snippet;
import com.ketphish.spheredefense.models.trail.Trail;

public final class EnemyManager
implements Updatable {
	// Inner ---------------------------------------------
	public interface Event {
		void onWaveFoesEnd(EnemyManager sender);
		void onWaveChanged(EnemyManager sender);
		void onWavesFinished(EnemyManager sender);
		void onEnemiesDestroyed(EnemyManager sender);
		void onFirstEnemyAdded(EnemyManager sender);
	}
	
	// Fields --------------------------------------------
	private Map<String, Enemy> enemies;
	private List<Trail> trails;
	private List<Wave> waves;
	private final List<Enemy> prototypes;
	private float startTime;
	private float elapsedTime;
	private int currentWave;
	private int currentFoe;
	private boolean isIdle;
	private boolean isWavesStarted;
	private boolean isWavesFinished;
	private Event event;
	
	// Constructors --------------------------------------
	public EnemyManager() {
		prototypes = new ArrayList<Enemy>();
		elapsedTime = 0;
		currentWave = 0;
		currentFoe = 0;
		isIdle = true;
		isWavesStarted = false;
		isWavesFinished = false;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public void update(float deltaTime) {
		if (!isIdle) {
			elapsedTime += deltaTime;
			if (isWavesStarted && !isWavesFinished) {
				if (currentFoe == waves.get(currentWave).getFoes().size()) {
					if (elapsedTime > waves.get(currentWave).getDuration()) {
						changeWave();
					} else if (event != null 
							&& currentWave + 1 != waves.size()) {
						event.onWaveFoesEnd(this);
					}
				} else {
					changeFoe();
				}
			} else if (currentWave == 0 && elapsedTime > startTime) {
				isWavesStarted = true;
				elapsedTime = 0;
			}
		}
		for (int i = 0; i < prototypes.size(); i++) {
			prototypes.get(i).update(deltaTime);
		}
		if (event != null && prototypes.size() == 0) {
			event.onEnemiesDestroyed(this);
		}
	}
	
	private void changeWave() {
		currentWave++;
		if (currentWave == waves.size()) {
			currentWave = 0;
			isIdle = true;
			isWavesFinished = true;
			if (event != null) {
				event.onWavesFinished(this);
			}
		} else {
			if (event != null) {
				event.onWaveChanged(this);
			}
		}
		elapsedTime = 0;
		currentFoe = 0;
	}

	private void changeFoe() {
		final Foe foe = waves.get(currentWave).getFoes().get(currentFoe);
		if (elapsedTime > foe.getStartTime()) {
			final String enemyName = foe.getEnemyName();
			final int trailId = foe.getTrailId();
			try {
				addNewEnemy(enemyName, trailId);
				currentFoe++;
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
	}

	private void addNewEnemy(String enemyName, int trailId) throws CloneNotSupportedException {
		final Enemy enemy = enemies.get(enemyName).clone();
		for (Trail trail : trails) {
			if (trail.getId() == trailId) {
				enemy.setTrail(trail);
			}
		}
		prototypes.add(enemy);
		if (event != null && prototypes.size() == 1) {
			event.onFirstEnemyAdded(this);
		}
	}

	// Methods -------------------------------------------
	public void startWaves() {
		isIdle = false;
	}
	
	public void startNewWave() {
		elapsedTime = waves.get(currentWave).getDuration() + 1;
	}
	
	private void translateTrailsPositions() {
		for (Trail trail : trails) {
			for (Snippet snippet : trail.getSnippets()) {
				snippet.setBegin(Preset.getInstance().translateMaxToMinAreaPosition(snippet.getBegin()));
				snippet.setEnd(Preset.getInstance().translateMaxToMinAreaPosition(snippet.getEnd()));
			}
		}
	}
	
	public void removeEnemy(Enemy enemy) {
		prototypes.remove(enemy);
	}
	
	// Properties ----------------------------------------
	public Map<String, Enemy> getEnemies() {
		return enemies;
	}
	
	public void setEnemies(Map<String, Enemy> enemies) {
		for (Enemy enemy : enemies.values()) {
			enemy.setEnemyManager(this);
		}
		this.enemies = enemies;
	}
	
	public void setTrails(List<Trail> trails) {
		this.trails = trails;
		translateTrailsPositions();
	}

	public void setWaves(List<Wave> waves) {
		this.waves = waves;
	}

	public List<Enemy> getPrototypes() {
		return prototypes;
	}
	
	public int getCurrentWave() {
		return currentWave;
	}
	
	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}

	public boolean isWavesFinished() {
		return isWavesFinished;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}
