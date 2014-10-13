package com.ketphish.spheredefense.views.panels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.models.gameobjects.Bullet;
import com.ketphish.spheredefense.views.Panel;

public final class BulletField
extends Panel {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final int DEFAULT_Z_INDEX = -100;
	private Map<String, AnimLabel> labels;
	private List<Bullet> bullets;
	private float time;
	
	// Constructors --------------------------------------
	public BulletField() {
		labels = new HashMap<String, AnimLabel>();
	}

	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) { }
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void setDefaultZIndex() {
		for (Label label : labels.values()) {
			label.setZIndex(DEFAULT_Z_INDEX);
		}
	}
	
	public Array<Panel> getBulletPanels(float deltaTime) {
		Array<Panel> panels = new Array<Panel>();
		if (bullets != null && bullets.size() > 0) {
			time += deltaTime;
			for (Bullet bullet : bullets) {
				try {
					final AnimLabel label = labels.get(bullet.getName()).clone();
					label.getRectangle().x = bullet.getPosition().x - label.getRectangle().width / 2;
					label.getRectangle().y = bullet.getPosition().y - label.getRectangle().height / 2;
					label.setTime(time);
					panels.add(label);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		return panels;
	}
	
	// Properties ----------------------------------------
	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	public Map<String, AnimLabel> getLabels() {
		return labels;
	}
	
}
