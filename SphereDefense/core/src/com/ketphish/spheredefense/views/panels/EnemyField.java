package com.ketphish.spheredefense.views.panels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.models.gameobjects.Enemy;
import com.ketphish.spheredefense.views.Panel;
import com.ketphish.spheredefense.views.panels.progressbars.EnemyHealthBar;

public final class EnemyField
extends Panel {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private Map<String, AnimLabel> animLabels;
	private List<Enemy> enemies;
	private EnemyHealthBar healthBar;
	private float time;
	
	// Constructors --------------------------------------
	public EnemyField() {
		animLabels = new HashMap<String, AnimLabel>();
		time = 0;
	}
	
	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) { }
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public Array<Panel> getEnemyPanels(float deltaTime) {
		final Array<Panel> panels = new Array<Panel>();
		if (enemies != null && enemies.size() > 0) {
			time += deltaTime;
			for (Enemy enemy : enemies) {
				try {
					final AnimLabel animLabel = animLabels.get(enemy.getName()).clone();
					final EnemyHealthBar bar = (EnemyHealthBar) healthBar.clone();
					animLabel.getRectangle().x = enemy.getPosition().x - animLabel.getRectangle().width / 2;
					animLabel.getRectangle().y = enemy.getPosition().y - animLabel.getRectangle().height / 2;
					animLabel.setZIndex(animLabel.getRectangle().y);
					animLabel.setTime(time);
					final float angle = (float) Math.ceil(enemy.getDirection().angle());
					if (angle > 60 && angle < 120) {
						animLabel.setSpriteIndex(3);
					} else if (angle > 240 && angle < 300) {
						animLabel.setSpriteIndex(0);
					} else if (angle >= 120 && angle <= 240) {
						animLabel.setSpriteIndex(1);
					} else if (angle <= 60 || angle >= 300) {
						animLabel.setSpriteIndex(2);
					}
					bar.getRectangle().x = animLabel.getRectangle().x;
					bar.getRectangle().y = getHealthBarY(animLabel.getRectangle());
					bar.getRectangle().width = animLabel.getRectangle().width;
					bar.getProgress().getRectangle().x = bar.getRectangle().x;
					bar.getProgress().getRectangle().y = bar.getRectangle().y;
					bar.getProgress().getRectangle().width = enemy.getHealth() * (animLabel.getRectangle().width / enemy.getMaxHealth());
					bar.setZIndex(animLabel.getZIndex());
					panels.add(animLabel);
					panels.add(bar);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		return panels;
	}
	
	private float getHealthBarY(Rectangle rectangle) {
		final float diagonal = (float) Math.ceil(rectangle.height * Math.sqrt(2.0));
		final float originY = rectangle.y + rectangle.height / 2;
		return (originY + diagonal / 2);
	}

	// Properties ----------------------------------------
	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
	
	public Map<String, AnimLabel> getAnimLabels() {
		return animLabels;
	}
	
	public void setHealthBar(EnemyHealthBar healthBar) {
		this.healthBar = healthBar;
	}
	
}
