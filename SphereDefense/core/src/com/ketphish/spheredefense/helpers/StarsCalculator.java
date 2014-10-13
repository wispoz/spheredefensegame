package com.ketphish.spheredefense.helpers;

import java.util.List;

import com.ketphish.spheredefense.models.profile.Advance;
import com.ketphish.spheredefense.models.profile.Upgrade;

public final class StarsCalculator {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	
	// Constructors --------------------------------------

	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public int addStars(Advance advance, Upgrade upgrade) {
		final int stars = advance.getStarsBalance();
		final int price = upgrade.getPower().getCost();
		final int result = stars + price;
		advance.setStarsBalance(result);
		return result;
	}
	
	public int addAllStars(Advance advance, List<Upgrade> upgrades) {
		for (Upgrade upgrade : upgrades) {
			if (upgrade.isUpgraded()) {
				addStars(advance, upgrade);
			}
		}
		final int result = advance.getStarsBalance();
		return result;
	}
	
	public int subStars(Advance advance, Upgrade upgrade) {
		int result = 0;
		if (advance.getStarsBalance() > 0) {
			final int stars = advance.getStarsBalance();
			final int price = upgrade.getPower().getCost();
			if ((stars - price) >= 0) {
				result = stars - price;
				advance.setStarsBalance(result);
			}
		}
		return result;
	}
	
	// Properties ----------------------------------------
}
