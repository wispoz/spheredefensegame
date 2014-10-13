package com.ketphish.spheredefense.helpers;

import java.util.List;
import java.util.Map;

import com.ketphish.spheredefense.models.gameobjects.Auxiliary;
import com.ketphish.spheredefense.models.gameobjects.Tower;
import com.ketphish.spheredefense.models.profile.Power;
import com.ketphish.spheredefense.models.profile.Upgrade;

public final class UnitUpgrader {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final Map<String, Tower> towers;
	private final Map<String, Auxiliary> auxiliaries;
	private final List<Upgrade> upgrades;
	
	// Constructors --------------------------------------
	public UnitUpgrader(Map<String, Tower> towers,
			Map<String, Auxiliary> auxiliaries,
			List<Upgrade> upgrades) {
		this.towers = towers;
		this.auxiliaries = auxiliaries;
		this.upgrades = upgrades;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void upgrade() {
		for (Upgrade upgrade : upgrades) {
			if (upgrade.isUpgraded()) {
				if (upgrade.getUnit().getName().equals("Cone")) {
					upgradeConeTowerLight(upgrade.getPower());
					upgradeConeTowerHeavy(upgrade.getPower());
				} else if (upgrade.getUnit().getName().equals("Pyramid")) {
					upgradePyramidTowerLight(upgrade.getPower());
					upgradePyramidTowerHeavy(upgrade.getPower());
				} else if (upgrade.getUnit().getName().equals("Cube")) {
					upgradeCubeTowerLight(upgrade.getPower());
					upgradeCubeTowerHeavy(upgrade.getPower());
				} else if (upgrade.getUnit().getName().equals("Cylinder")) {
					upgradeCylinderTowerLight(upgrade.getPower());
					upgradeCylinderTowerHeavy(upgrade.getPower());
				} else if (upgrade.getUnit().getName().equals("EnergyCircle")) {
					upgradeEnergyCircle(upgrade.getPower());
				} else if (upgrade.getUnit().getName().equals("FireCircle")) {
					upgradeFireCircle(upgrade.getPower());
				}
			}
		}
	}
	
	private void upgradeConeTowerLight(Power power) {
		final Tower tower = towers.get("cone_tower_light");
		float range = tower.getRange();
		float attackRate = tower.getAttackRate();
		float damage = tower.getDamage();
		float buildRate = tower.getBuildRate();
		if (power.getName().equals("Low")) {
			range += 5;
			damage += 2;
			attackRate -= 0.2;
			buildRate -= 0.2;
		} else if (power.getName().equals("Medium")) {
			range += 10;
			damage += 3;
			attackRate -= 0.3;
			buildRate -= 0.3;
		} else if (power.getName().equals("High")) {
			range += 15;
			damage += 5;
			attackRate -= 0.5;
			buildRate -= 0.5;
		}
		tower.setRange(range);
		tower.setAttackRate(attackRate);
		tower.setDamage(damage);
		tower.setBuildRate(buildRate);
	}

	private void upgradeConeTowerHeavy(Power power) {
		final Tower tower = towers.get("cone_tower_heavy");
		float range = tower.getRange();
		float attackRate = tower.getAttackRate();
		float damage = tower.getDamage();
		float buildRate = tower.getBuildRate();
		if (power.getName().equals("Low")) {
			range += 5;
			damage += 2;
			attackRate -= 0.2;
			buildRate -= 0.2;
		} else if (power.getName().equals("Medium")) {
			range += 10;
			damage += 3;
			attackRate -= 0.3;
			buildRate -= 0.3;
		} else if (power.getName().equals("High")) {
			range += 15;
			damage += 5;
			attackRate -= 0.5;
			buildRate -= 0.5;
		}
		tower.setRange(range);
		tower.setAttackRate(attackRate);
		tower.setDamage(damage);
		tower.setBuildRate(buildRate);
	}

	private void upgradePyramidTowerLight(Power power) {
		final Tower tower = towers.get("pyramid_tower_light");
		float range = tower.getRange();
		float attackRate = tower.getAttackRate();
		float damage = tower.getDamage();
		float buildRate = tower.getBuildRate();
		if (power.getName().equals("Low")) {
			range += 5;
			damage += 2;
			attackRate -= 0.2;
			buildRate -= 0.2;
		} else if (power.getName().equals("Medium")) {
			range += 10;
			damage += 3;
			attackRate -= 0.3;
			buildRate -= 0.3;
		} else if (power.getName().equals("High")) {
			range += 15;
			damage += 5;
			attackRate -= 0.5;
			buildRate -= 0.5;
		}
		tower.setRange(range);
		tower.setAttackRate(attackRate);
		tower.setDamage(damage);
		tower.setBuildRate(buildRate);
	}

	private void upgradePyramidTowerHeavy(Power power) {
		final Tower tower = towers.get("pyramid_tower_heavy");
		float range = tower.getRange();
		float attackRate = tower.getAttackRate();
		float damage = tower.getDamage();
		float buildRate = tower.getBuildRate();
		if (power.getName().equals("Low")) {
			range += 5;
			damage += 2;
			attackRate -= 0.2;
			buildRate -= 0.2;
		} else if (power.getName().equals("Medium")) {
			range += 10;
			damage += 3;
			attackRate -= 0.3;
			buildRate -= 0.3;
		} else if (power.getName().equals("High")) {
			range += 15;
			damage += 5;
			attackRate -= 0.5;
			buildRate -= 0.5;
		}
		tower.setRange(range);
		tower.setAttackRate(attackRate);
		tower.setDamage(damage);
		tower.setBuildRate(buildRate);
	}

	private void upgradeCubeTowerLight(Power power) {
		final Tower tower = towers.get("cube_tower_light");
		float range = tower.getRange();
		float attackRate = tower.getAttackRate();
		float damage = tower.getDamage();
		float buildRate = tower.getBuildRate();
		if (power.getName().equals("Low")) {
			range += 5;
			damage += 2;
			attackRate -= 0.3;
			buildRate -= 0.3;
		} else if (power.getName().equals("Medium")) {
			range += 10;
			damage += 3;
			attackRate -= 0.4;
			buildRate -= 0.4;
		} else if (power.getName().equals("High")) {
			range += 15;
			damage += 5;
			attackRate -= 0.7;
			buildRate -= 0.7;
		}
		tower.setRange(range);
		tower.setAttackRate(attackRate);
		tower.setDamage(damage);
		tower.setBuildRate(buildRate);
	}

	private void upgradeCubeTowerHeavy(Power power) {
		final Tower tower = towers.get("cube_tower_heavy");
		float range = tower.getRange();
		float attackRate = tower.getAttackRate();
		float damage = tower.getDamage();
		float buildRate = tower.getBuildRate();
		if (power.getName().equals("Low")) {
			range += 5;
			damage += 2;
			attackRate -= 0.3;
			buildRate -= 0.3;
		} else if (power.getName().equals("Medium")) {
			range += 10;
			damage += 3;
			attackRate -= 0.4;
			buildRate -= 0.4;
		} else if (power.getName().equals("High")) {
			range += 15;
			damage += 5;
			attackRate -= 0.7;
			buildRate -= 0.7;
		}
		tower.setRange(range);
		tower.setAttackRate(attackRate);
		tower.setDamage(damage);
		tower.setBuildRate(buildRate);
	}

	private void upgradeCylinderTowerLight(Power power) {
		final Tower tower = towers.get("cylinder_tower_light");
		float range = tower.getRange();
		float attackRate = tower.getAttackRate();
		float damage = tower.getDamage();
		float buildRate = tower.getBuildRate();
		if (power.getName().equals("Low")) {
			range += 5;
			damage += 2;
			attackRate -= 0.4;
			buildRate -= 0.4;
		} else if (power.getName().equals("Medium")) {
			range += 10;
			damage += 3;
			attackRate -= 0.5;
			buildRate -= 0.5;
		} else if (power.getName().equals("High")) {
			range += 15;
			damage += 5;
			attackRate -= 0.9;
			buildRate -= 0.9;
		}
		tower.setRange(range);
		tower.setAttackRate(attackRate);
		tower.setDamage(damage);
		tower.setBuildRate(buildRate);
	}

	private void upgradeCylinderTowerHeavy(Power power) {
		final Tower tower = towers.get("cylinder_tower_heavy");
		float range = tower.getRange();
		float attackRate = tower.getAttackRate();
		float damage = tower.getDamage();
		float buildRate = tower.getBuildRate();
		if (power.getName().equals("Low")) {
			range += 5;
			damage += 2;
			attackRate -= 0.4;
			buildRate -= 0.4;
		} else if (power.getName().equals("Medium")) {
			range += 10;
			damage += 3;
			attackRate -= 0.5;
			buildRate -= 0.5;
		} else if (power.getName().equals("High")) {
			range += 15;
			damage += 5;
			attackRate -= 0.9;
			buildRate -= 0.9;
		}
		tower.setRange(range);
		tower.setAttackRate(attackRate);
		tower.setDamage(damage);
		tower.setBuildRate(buildRate);
	}
	
	private void upgradeEnergyCircle(Power power) {
		final Auxiliary auxiliary = auxiliaries.get("energy_circle");
		float attackRate = auxiliary.getAttackRate();
		float damage = auxiliary.getDamage();
		if (power.getName().equals("Low")) {
			damage += 2;
			attackRate -= 1;
		} else if (power.getName().equals("Medium")) {
			damage += 3;
			attackRate -= 2;
		} else if (power.getName().equals("High")) {
			damage += 5;
			attackRate -= 3;
		}
		auxiliary.setAttackRate(attackRate);
		auxiliary.setDamage(damage);
	}

	private void upgradeFireCircle(Power power) {
		final Auxiliary auxiliary = auxiliaries.get("fire_circle");
		float attackRate = auxiliary.getAttackRate();
		float damage = auxiliary.getDamage();
		if (power.getName().equals("Low")) {
			damage += 2;
			attackRate -= 2;
		} else if (power.getName().equals("Medium")) {
			damage += 3;
			attackRate -= 4;
		} else if (power.getName().equals("High")) {
			damage += 5;
			attackRate -= 6;
		}
		auxiliary.setAttackRate(attackRate);
		auxiliary.setDamage(damage);
	}

	// Properties ----------------------------------------
}
