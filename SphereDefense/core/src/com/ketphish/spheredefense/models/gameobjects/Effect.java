package com.ketphish.spheredefense.models.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.managers.EffectManager;
import com.ketphish.spheredefense.models.GameObject;

public final class Effect
extends GameObject {
	// Inner ---------------------------------------------
	public interface Event {
		void onStarted(Effect effect);
		void onFinished(Effect effect);
	}
	
	// Fields --------------------------------------------
	private EffectManager effectManager;
	private float duration;
	private float elapsedTime;
	private boolean isStarted;
	private Event event;
	
	// Constructors --------------------------------------
	public Effect() {
		elapsedTime = 0;
		isStarted = false;
	}
	
	// Extends -------------------------------------------
	@Override
	public void update(float deltaTime) {
		if (elapsedTime <= duration) {
			elapsedTime += deltaTime;
			if (!isStarted) {
				isStarted = true;
				if (event != null) {
					event.onStarted(this);
				}
			}
		} else {
			if (event != null) {
				event.onFinished(this);
			}
			effectManager.removeEffect(this);
		}
	}
	
	// Implementations -----------------------------------
	@Override
	public Effect clone() throws CloneNotSupportedException {
		Effect clone = (Effect) super.clone();
		clone.effectManager = this.effectManager;
		clone.event = this.event;
		return clone;
	}
	
	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public void setEffectManager(EffectManager effectManager) {
		this.effectManager = effectManager;
	}
	
	@Override
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public void setDuration(float duration) {
		this.duration = duration;
	}
	
}
