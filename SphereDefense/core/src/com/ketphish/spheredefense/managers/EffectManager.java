package com.ketphish.spheredefense.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ketphish.spheredefense.interfaces.Updatable;
import com.ketphish.spheredefense.models.gameobjects.Bullet;
import com.ketphish.spheredefense.models.gameobjects.Effect;
import com.ketphish.spheredefense.models.gameobjects.Enemy;

public final class EffectManager
implements Updatable {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private Map<String, Effect> effects;
	private final List<Effect> prototypes;
	
	// Constructors --------------------------------------
	public EffectManager() {
		prototypes = new ArrayList<Effect>();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public void update(float deltaTime) {
		for (int i = 0; i < prototypes.size(); i++) {
			prototypes.get(i).update(deltaTime);
		}
	}

	// Methods -------------------------------------------
	public void addBulletHitEffect(Bullet bullet) {
		try {
			String effectName = null;
			if (bullet.getName().equals("cone_bullet_light")) {
				effectName = "cone_bullet_light_hit_explosion";
			} else if (bullet.getName().equals("cone_bullet_heavy")) {
				effectName = "cone_bullet_heavy_hit_explosion";
			} else if (bullet.getName().equals("pyramid_bullet_light")) {
				effectName = "pyramid_bullet_light_hit_explosion";
			} else if (bullet.getName().equals("pyramid_bullet_heavy")) {
				effectName = "pyramid_bullet_heavy_hit_explosion";
			} else if (bullet.getName().equals("cube_bullet_light")) {
				effectName = "cube_bullet_light_hit_explosion";
			} else if (bullet.getName().equals("cube_bullet_heavy")) {
				effectName = "cube_bullet_heavy_hit_explosion";
			} else if (bullet.getName().equals("cylinder_bullet_light")) {
				effectName = "cylinder_bullet_light_hit_explosion";
			} else if (bullet.getName().equals("cylinder_bullet_heavy")) {
				effectName = "cylinder_bullet_heavy_hit_explosion";
			} else {
				effectName = "auxiliary_hit_explosion";
			}
			final Effect effect = effects.get(effectName).clone();
			effect.setPosition(bullet.getEnemy().getPosition());
			prototypes.add(effect);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public void addBulletMissEffect(Bullet bullet) {
		try {
			String effectName = null;
			if (bullet.getName().equals("cone_bullet_light")) {
				effectName = "cone_bullet_light_miss_explosion";
			} else if (bullet.getName().equals("cone_bullet_heavy")) {
				effectName = "cone_bullet_heavy_miss_explosion";
			} else if (bullet.getName().equals("pyramid_bullet_light")) {
				effectName = "pyramid_bullet_light_miss_explosion";
			} else if (bullet.getName().equals("pyramid_bullet_heavy")) {
				effectName = "pyramid_bullet_heavy_miss_explosion";
			} else if (bullet.getName().equals("cube_bullet_light")) {
				effectName = "cube_bullet_light_miss_explosion";
			} else if (bullet.getName().equals("cube_bullet_heavy")) {
				effectName = "cube_bullet_heavy_miss_explosion";
			} else if (bullet.getName().equals("cylinder_bullet_light")) {
				effectName = "cylinder_bullet_light_miss_explosion";
			} else if (bullet.getName().equals("cylinder_bullet_heavy")) {
				effectName = "cylinder_bullet_heavy_miss_explosion";
			} else {
				effectName = "auxiliary_miss_explosion";
			}
			final Effect effect = effects.get(effectName).clone();
			effect.setPosition(bullet.getPosition().cpy());
			prototypes.add(effect);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public void addEnemyDestroyedEffect(Enemy enemy) {
		try {
			final Effect effect = effects.get("enemy_dead").clone();
			effect.setPosition(enemy.getPosition().cpy());
			prototypes.add(effect);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public void removeEffect(Effect effect) {
		prototypes.remove(effect);
	}
	
	// Properties ----------------------------------------
	public void setEffects(Map<String, Effect> effects) {
		for (Effect effect : effects.values()) {
			effect.setEffectManager(this);
		}
		this.effects = effects;
	}
	
	public List<Effect> getPrototypes() {
		return prototypes;
	}
	
}
