package com.ketphish.spheredefense.views.panels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.models.gameobjects.Tower;
import com.ketphish.spheredefense.views.Panel;
import com.ketphish.spheredefense.views.Screen;
import com.ketphish.spheredefense.views.panels.progressbars.TowerBuildBar;

public final class TowerField
extends Panel {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	private List<Tower> towers;
	private Map<String, Label> labels;
	private TowerBuildBar buildBar;
	
	// Constructors --------------------------------------
	public TowerField() {
		labels = new HashMap<String, Label>();
	}

	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) { }
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	public Array<Panel> getTowerPanels() {
		final Array<Panel> panels = new Array<Panel>();
		if (towers != null && towers.size() > 0) {
			for (Tower tower : towers) {
				try {
					final Label label = labels.get(tower.getName()).clone();
					label.getRectangle().x = tower.getPosition().x - label.getRectangle().width / 2;
					label.getRectangle().y = tower.getPosition().y - label.getRectangle().width / 2;
					label.setZIndex(label.getRectangle().y);
					if (tower.isBuilding()) {
						final TowerBuildBar bar = (TowerBuildBar) buildBar.clone();
						bar.getRectangle().x = label.getRectangle().x;
						bar.getRectangle().y = label.getRectangle().y - bar.getRectangle().height * 1.5f;
						bar.getRectangle().width = label.getRectangle().width;
						bar.getProgress().getRectangle().x = bar.getRectangle().x;
						bar.getProgress().getRectangle().y = bar.getRectangle().y;
						bar.getProgress().getRectangle().width = tower.getElapsedTime() * (label.getRectangle().width / tower.getBuildRate());
						bar.setZIndex(label.getZIndex());
						label.getRectangle().height = tower.getElapsedTime() * (label.getRectangle().height / tower.getBuildRate());
						panels.add(bar);
					}
					panels.add(label);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		return panels;
	}
	
	public Rectangle calcRectangle(Tower tower) {
		final Vector2 position = tower.getPosition().cpy();
		final float width = labels.get(tower.getName()).getRectangle().width;
		final float height = labels.get(tower.getName()).getRectangle().height;
		position.x = position.x - width / 2;
		position.y = position.y - height / 2;
		return new Rectangle(position.x, position.y, width, height);
	}
	
	// Properties ----------------------------------------
	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}
	
	public Map<String, Label> getLabels() {
		return labels;
	}
	
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	
	public void setBuildBar(TowerBuildBar buildBar) {
		this.buildBar = buildBar;
	}
	
}
