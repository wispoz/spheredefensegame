package com.ketphish.spheredefense.models.profile;

import java.util.ArrayList;
import java.util.List;

public final class Profile {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private Advance advance;
	private Options options;
	private List<Trophy> trophies;
	private List<Upgrade> upgrades;
	
	// Constructors --------------------------------------
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	
	// Properties ----------------------------------------
	public Advance getAdvance() {
		return advance;
	}
	
	public void setAdvance(final Advance advance) {
		this.advance = advance;
	}
	
	public Options getOptions() {
		return options;
	}

	public void setOptions(final Options options) {
		this.options = options;
	}
	
	public List<Trophy> getTrophies() {
		return trophies;
	}
	
	public void setTrophies(final List<Trophy> trophies) {
		if (this.trophies != null) {
			this.trophies.clear();
			this.trophies = null;
		}
		this.trophies = new ArrayList<Trophy>(trophies);
	}
	
	public List<Upgrade> getUpgrades() {
		return upgrades;
	}
	
	public void setUpgrades(final List<Upgrade> upgrades) {
		if (this.upgrades != null) {
			this.upgrades.clear();
			this.upgrades = null;
		}
		this.upgrades = new ArrayList<Upgrade>(upgrades);
	}
	
}
