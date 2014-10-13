package com.ketphish.spheredefense.views.panels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.models.gameobjects.Effect;
import com.ketphish.spheredefense.views.Panel;

public final class EffectField
extends Panel {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private Map<String, AnimLabel> labels;
	private List<Effect> effects;
	private float time;
	
	// Constructors --------------------------------------
	public EffectField() {
		labels = new HashMap<String, AnimLabel>();
	}
	
	// Extends -------------------------------------------
	@Override
	public void draw(Drawer drawer, float deltaTime) { }
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public Array<Panel> getEffectPanels(float deltaTime) {
		final Array<Panel> panels = new Array<Panel>();
		if (effects != null && effects.size() > 0) {
			time += deltaTime;
			for (Effect effect : effects) {
				try {
					final AnimLabel label = labels.get(effect.getName()).clone();
					label.getRectangle().x = effect.getPosition().x - label.getRectangle().width / 2;
					label.getRectangle().y = effect.getPosition().y - label.getRectangle().height / 2;
					label.setZIndex(label.getRectangle().y);
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
	public Map<String, AnimLabel> getLabels() {
		return labels;
	}

	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}
	
}
