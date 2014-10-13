package com.ketphish.spheredefense.views.gamescreens;

import static com.ketphish.spheredefense.managers.GameScreenManager.ScreenState.START;
import static com.ketphish.spheredefense.views.panels.Button.ButtonState.DISABLED;
import static com.ketphish.spheredefense.views.panels.Button.ButtonState.IDLE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.models.profile.Advance;
import com.ketphish.spheredefense.models.profile.Profile;
import com.ketphish.spheredefense.models.profile.Upgrade;
import com.ketphish.spheredefense.views.GameScreen;
import com.ketphish.spheredefense.views.panels.Button;

public final class UpgradesScreen
extends GameScreen {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	
	// Extends -------------------------------------------
	@Override
	public void bindData(ProfileManager profileManager) {
		profileManager.initUpgradesCache();
		final List<String> buttonNames = new ArrayList<String>(buttons.keySet());
		profileManager.bindButtonNamesToUpgradesCache(buttonNames);
		profileManager.initAdvanceCache();
		profileManager.initUpgradesHistory();
		profileManager.initStarsCalc();
		final Profile profile = profileManager.getProfile();
		final int stars = profile.getAdvance().getStarsBalance();
		changeStarsText(stars);
	}
	
	@Override
	public void adjustPanels(boolean isSorting) {
		super.adjustPanels(isSorting);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_reset").getRectangle(), true, true);
		Preset.getInstance().floatYToViewport(buttons.get("btn_undo").getRectangle(), true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_done").getRectangle(), false, true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_help").getRectangle(), false, true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_help_done").getRectangle(), false, true);
		Preset.getInstance().floatYToViewport(labels.get("lbl_title").getRectangle(), false);
		Preset.getInstance().floatYToViewport(labels.get("lbl_help_title").getRectangle(), false);
		Preset.getInstance().floatPositionToViewport(labels.get("lbl_stars").getRectangle(), true, true);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_stars").getRectangle(), true, true);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_separator").getRectangle(), true, true);
		Preset.getInstance().floatPositionToViewport(textFields.get("txt_stars_total").getRectangle(), true, true);
	}
	
	@Override
	public void activate() {
		Button.Event event = new Button.Event() {
			@Override
			public void onTouchDown(Button sender) {
				game.getAudioManager().playSound("btn_touch", .5f);
			}
			@Override
			public void onTouchUp(Button sender) {
				onButtonTouch(sender);
			}
		};
		for (Button button : buttons.values()) {
			button.getEvents().add(event);
		}
		super.activate();
		changeButtonState(getProfile().getAdvance().getStarsBalance());
	}
	
	@Override
	public void onButtonTouch(final Button sender) {
		final int stars = getAdvanceCache().getStarsBalance();
		if (sender.getName().equals("btn_reset")) {
			getProfileManager().resetUpgradesCache();
		} else if (sender.getName().equals("btn_undo")) {
			getProfileManager().cancelUpgradesCache();
		} else if (sender.getName().equals("btn_done")) {
			game.getGameScreenManager().changeScreenState(START);
			return;
		} else if (sender.getName().equals("btn_help")
				|| sender.getName().equals("btn_help_done")) {
			toggleHelpDialog();
		} else {
			getProfileManager().updateUpgradesCache(sender.getName());
		}
		if (stars != getAdvanceCache().getStarsBalance()) {
			changeStarsText(getAdvanceCache().getStarsBalance());
			changeButtonState(getAdvanceCache().getStarsBalance());
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	private void changeStarsText(final int stars) {
		String starsStr = String.valueOf(stars);
		final int levelCount = Preset.getInstance().LEVELS_COUNT;
		final int starsPerLevel = Preset.getInstance().MAX_STARS_COUNT_PER_LEVEL;
		final String maxStarsCount = String.valueOf(levelCount * starsPerLevel);
		if (starsStr.length() < 2) {
			starsStr = String.format("%s%s", " ", starsStr);
		}
		textFields.get("txt_stars").setText(starsStr);
		textFields.get("txt_stars_total").setText(maxStarsCount);
	}

	private void changeButtonState(int stars) {
		int upgradesCount = 0;
		for (String buttonName : getUpgradesCache().keySet()) {
			final Button button = buttons.get(buttonName);
			final Upgrade upgrade = getUpgradesCache().get(buttonName);
			if (upgrade.isUpgraded() && button.isVisible()
					|| !upgrade.isUpgraded() && !button.isVisible()) {
				button.toggleVisibility();
			}
			if (upgrade.isUpgraded()) {
				upgradesCount++;
			}
			if (stars == 0) {
				if (button.getState() == IDLE) {
					button.setButtonState(DISABLED);
				}
			} else {
				final int price = upgrade.getPower().getCost();
				if (stars >= price && button.getState() == DISABLED) {
					button.setButtonState(IDLE);
				} else if (stars < price && button.getState() == IDLE) {
					button.setButtonState(DISABLED);
				}
			}
		}
		final Button reset = buttons.get("btn_reset");
		if (upgradesCount > 1 && reset.getState() == DISABLED
				|| upgradesCount > 0 && getUpgradesHistory().size() == 0
				&& reset.getState() == DISABLED) {
			reset.setButtonState(IDLE);
		} else if (upgradesCount < 2 && getUpgradesHistory().size() > 0 && reset.getState() == IDLE
				|| upgradesCount == 0 && reset.getState() == IDLE) {
			reset.setButtonState(DISABLED);
		}
		final Button undo = buttons.get("btn_undo");
		if (getUpgradesHistory().size() > 0 
				&& undo.getState() == DISABLED) {
			undo.setButtonState(IDLE);
		} else if (getUpgradesHistory().size() == 0 
				&& undo.getState() == IDLE) {
			undo.setButtonState(DISABLED);
		}
	}
	
	private void toggleHelpDialog() {
		labels.get("bg_help").toggleVisibility();
		labels.get("lbl_help_title").toggleVisibility();
		textFields.get("txt_range").toggleVisibility();
		textFields.get("txt_damage").toggleVisibility();
		textFields.get("txt_time").toggleVisibility();
		buttons.get("btn_help_done").toggleVisibility();
		buttons.get("btn_help").toggleVisibility();
	}
	
	// Properties ----------------------------------------
	private ProfileManager getProfileManager() {
		return game.getGameLogic().getProfileManager();
	}
	
	private Profile getProfile() {
		return getProfileManager().getProfile();
	}
	
	private Advance getAdvanceCache() {
		return getProfileManager().getAdvanceCache();
	}
	
	private Map<String, Upgrade> getUpgradesCache() {
		return getProfileManager().getUpgradesCache();
	}
	
	private LinkedList<String> getUpgradesHistory() {
		return getProfileManager().getUpgradesHistory();
	}
	
}
