package com.ketphish.spheredefense.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ketphish.spheredefense.helpers.PlayerPreferences;
import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.helpers.StarsCalculator;
import com.ketphish.spheredefense.models.profile.Advance;
import com.ketphish.spheredefense.models.profile.Options;
import com.ketphish.spheredefense.models.profile.Profile;
import com.ketphish.spheredefense.models.profile.Upgrade;

public final class ProfileManager {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final Profile profile;
	private final PlayerPreferences playerPrefs;
	private Advance advanceCache;
	private Options optionsCache;
	private Map<String, Upgrade> upgradesCache;
	private LinkedList<String> upgradesHistory;
	private StarsCalculator starsCalc;
	
	// Constructors --------------------------------------
	public ProfileManager() {
		profile = new Profile();
		playerPrefs = new PlayerPreferences();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void initAdvanceCache() {
		try {
			advanceCache = profile.getAdvance().clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateStarsBalance() {
		boolean isNewStarsBalance = false;
		final Advance advance  = profile.getAdvance();
		if (advanceCache.getStarsBalance() != advance.getStarsBalance()) {
			advance.setStarsBalance(advanceCache.getStarsBalance());
			isNewStarsBalance = true;
		}
		return isNewStarsBalance;
	}
	
	public void initOptionsCache() {
		try {
			this.optionsCache = profile.getOptions().clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateGameOptions() {
		boolean isNewStates = false;
		final Options options = profile.getOptions();
		if (options.isMusic() != optionsCache.isMusic() ||
				options.isSound() != optionsCache.isSound()
				|| options.getLanguage() != optionsCache.getLanguage()) {
			options.setIsMusic(optionsCache.isMusic());
			options.setIsSound(optionsCache.isSound());
			options.setLanguage(optionsCache.getLanguage());
			isNewStates = true;
		}
		return isNewStates;
	}
	
	public boolean updateGamePlayOptions() {
		boolean isNewStates = false;
		final Options options = profile.getOptions();
		if (options.isMusic() != optionsCache.isMusic() 
				|| options.isSound() != optionsCache.isSound()) {
			isNewStates = true;
		}
		return isNewStates;
	}
	
	public void initUpgradesCache() {
		upgradesCache = new HashMap<String, Upgrade>();
	}
	
	public void bindButtonNamesToUpgradesCache(List<String> names) {
		final List<Upgrade> upgrades = profile.getUpgrades();
		for (String name : names) {
			final String[] nameParts = name.split("_");
			for (Upgrade upgrade : upgrades) {
				final String forceName = upgrade.getUnit().getName().toLowerCase();
				final String rankName = upgrade.getPower().getName().toLowerCase();
				if (nameParts[1].contains(forceName)
						&& nameParts[2].contains(rankName)) {
					try {
						upgradesCache.put(name, upgrade.clone());
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}
	
	public int resetUpgradesCache() {
		final int stars = addAllStars();
		for (Upgrade upgrade : upgradesCache.values()) {
			upgrade.isUpgraded(false);
		}
		if (upgradesHistory.size() > 0) {
			upgradesHistory.clear();
		}
		return stars;
	}
	
	public int cancelUpgradesCache() {
		final String last = upgradesHistory.getLast();
		final Upgrade upgrade = upgradesCache.get(last);
		upgrade.isUpgraded(false);
		final int stars = addStars(upgrade);
		upgradesHistory.removeLast();
		return stars;
	}
	
	public int updateUpgradesCache(String buttonName) {
		final Upgrade upgrade = upgradesCache.get(buttonName);
		upgrade.isUpgraded(true);
		final int stars = subStars(upgrade);
		upgradesHistory.add(buttonName);
		return stars;
	}
	
	public boolean updateUpgrades() {
		boolean isNewUpgrades = false;
		final List<Upgrade> upgrades = profile.getUpgrades();
		for (Upgrade upgradeCache : upgradesCache.values()) {
			for (Upgrade upgrade : upgrades) {
				if (upgradeCache.getId() == upgrade.getId()
						&& upgradeCache.isUpgraded() != upgrade.isUpgraded()) {
					upgrade.isUpgraded(upgradeCache.isUpgraded());
					if (!isNewUpgrades) {
						isNewUpgrades = true;
					}
					break;
				}
			}
		}
		return isNewUpgrades;
	}
	
	public void initUpgradesHistory() {
		upgradesHistory = new LinkedList<String>();
	}
	
	public void initStarsCalc() {
		starsCalc = new StarsCalculator();
	}
	
	public int addStars(Upgrade upgrade) {
		return starsCalc.addStars(advanceCache, upgrade);
	}
	
	public int addAllStars() {
		final List<Upgrade> upgrades = new ArrayList<Upgrade>(upgradesCache.values());
		return starsCalc.addAllStars(advanceCache, upgrades);
	}
	
	public int subStars(Upgrade upgrade) {
		return starsCalc.subStars(advanceCache, upgrade);
	}
	
	public boolean checkIsNewStars() {
		if (playerPrefs.getLevel() == profile.getAdvance().getCurrentLevel()) {
			if (profile.getAdvance().getCurrentLevel() < Preset.getInstance().LEVELS_COUNT) {
				profile.getAdvance().setCurrentLevel(playerPrefs.getLevel() + 1);
			}
			final int starsAmount = profile.getAdvance().getStarsBalance();
			profile.getAdvance().setStarsBalance(starsAmount + playerPrefs.getLevelStarsAmount());
			profile.getTrophies().get(playerPrefs.getLevel() - 1).setStarsAmount(playerPrefs.getLevelStarsAmount());
			return true;
		} else if (playerPrefs.getLevel() < profile.getAdvance().getCurrentLevel()) {
			final int index = playerPrefs.getLevel() - 1;
			if (playerPrefs.getLevelStarsAmount() > profile.getTrophies().get(index).getStarsAmount()) {
				final int prevStarsAmount = profile.getTrophies().get(index).getStarsAmount();
				final int strife = playerPrefs.getLevelStarsAmount() - prevStarsAmount;
				profile.getTrophies().get(index).setStarsAmount(playerPrefs.getLevelStarsAmount());
				final int starsBalance = profile.getAdvance().getStarsBalance();
				profile.getAdvance().setStarsBalance(starsBalance + strife);
				return true;
			}
		}
		return false;
	}
	
	public void resetOptionsCache() {
		optionsCache.setIsMusic(profile.getOptions().isMusic());
		optionsCache.setIsSound(profile.getOptions().isSound());
		optionsCache.setLanguage(profile.getOptions().getLanguage());
	}
	
	// Properties ----------------------------------------
	public Profile getProfile() {
		return profile;
	}

	public PlayerPreferences getPlayerPrefs() {
		return playerPrefs;
	}
	
	public Advance getAdvanceCache() {
		return advanceCache;
	}
	
	public Options getOptionsCache() {
		return optionsCache;
	}
	
	public Map<String, Upgrade> getUpgradesCache() {
		return upgradesCache;
	}

	public LinkedList<String> getUpgradesHistory() {
		return upgradesHistory;
	}
	
}
