package com.ketphish.spheredefense.views.gamescreens;

import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.views.GameScreen;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.Button.ButtonState;

public final class EncyclopediaScreen
extends GameScreen {
	// Inner ---------------------------------------------
	private enum State {
		IDLE, 
		TOWERS_GUIDE,
		ENEMIES_GUIDE,
		AUXILIARIES_GUIDE
	}
	
	// Fields --------------------------------------------
	private String towerLabelName;
	private String towerName;
	private String auxiliaryLabelName;
	private String auxiliaryName;
	private String towerAuxRange;
	private String towerAuxDamage;
	private String towerAuxAttackRate;
	private String enemyLabelName;
	private String enemyName;
	private String enemyHealth;
	private String enemySpeed;
	private String enemyResistance;
	private String enemyDamage;
	private String enemyIncome;
	private State state;
	
	// Constructors --------------------------------------
	public EncyclopediaScreen() {
		state = State.IDLE;
	}
	
	// Extends -------------------------------------------
	@Override
	public void bindData(ProfileManager profileManager) { }
	
	@Override
	public void adjustPanels(boolean isSorting) {
		super.adjustPanels(isSorting);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_back").getRectangle(), true, true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_ok").getRectangle(), false, true);
		Preset.getInstance().floatPositionToViewport(buttons.get("btn_help").getRectangle(), false, true);
		Preset.getInstance().floatYToViewport(labels.get("lbl_main_title").getRectangle(), false);
		Preset.getInstance().floatYToViewport(labels.get("lbl_title_towers").getRectangle(), false);
		Preset.getInstance().floatYToViewport(labels.get("lbl_title_enemies").getRectangle(), false);
		Preset.getInstance().floatYToViewport(labels.get("lbl_title_auxiliaries").getRectangle(), false);
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
	}
	
	@Override
	public void onButtonTouch(Button sender) {
		if (sender.getName().equals("btn_towers")) {
			disableMainScreen();
			showTowersGuide();
		} else if (sender.getName().equals("btn_cone_light")) {
			labels.get(towerLabelName).toggleVisibility();
			towerLabelName = "lbl_cone_light";
			labels.get(towerLabelName).toggleVisibility();
			textFields.get(towerName).toggleVisibility();
			towerName = "txt_cone_light";
			textFields.get(towerName).toggleVisibility();
			textFields.get(towerAuxRange).toggleVisibility();
			towerAuxRange = "txt_cone_light_range";
			textFields.get(towerAuxRange).toggleVisibility();
			textFields.get(towerAuxDamage).toggleVisibility();
			towerAuxDamage = "txt_cone_light_damage";
			textFields.get(towerAuxDamage).toggleVisibility();
			textFields.get(towerAuxAttackRate).toggleVisibility();
			towerAuxAttackRate = "txt_cone_light_time";
			textFields.get(towerAuxAttackRate).toggleVisibility();
		} else if (sender.getName().equals("btn_cone_heavy")) {
			labels.get(towerLabelName).toggleVisibility();
			towerLabelName = "lbl_cone_heavy";
			labels.get(towerLabelName).toggleVisibility();
			textFields.get(towerName).toggleVisibility();
			towerName = "txt_cone_heavy";
			textFields.get(towerName).toggleVisibility();
			textFields.get(towerAuxRange).toggleVisibility();
			towerAuxRange = "txt_cone_heavy_range";
			textFields.get(towerAuxRange).toggleVisibility();
			textFields.get(towerAuxDamage).toggleVisibility();
			towerAuxDamage = "txt_cone_heavy_damage";
			textFields.get(towerAuxDamage).toggleVisibility();
			textFields.get(towerAuxAttackRate).toggleVisibility();
			towerAuxAttackRate = "txt_cone_heavy_time";
			textFields.get(towerAuxAttackRate).toggleVisibility();
		} else if (sender.getName().equals("btn_pyramid_light")) {
			labels.get(towerLabelName).toggleVisibility();
			towerLabelName = "lbl_pyramid_light";
			labels.get(towerLabelName).toggleVisibility();
			textFields.get(towerName).toggleVisibility();
			towerName = "txt_pyramid_light";
			textFields.get(towerName).toggleVisibility();
			textFields.get(towerAuxRange).toggleVisibility();
			towerAuxRange = "txt_pyramid_light_range";
			textFields.get(towerAuxRange).toggleVisibility();
			textFields.get(towerAuxDamage).toggleVisibility();
			towerAuxDamage = "txt_pyramid_light_damage";
			textFields.get(towerAuxDamage).toggleVisibility();
			textFields.get(towerAuxAttackRate).toggleVisibility();
			towerAuxAttackRate = "txt_pyramid_light_time";
			textFields.get(towerAuxAttackRate).toggleVisibility();
		} else if (sender.getName().equals("btn_pyramid_heavy")) {
			labels.get(towerLabelName).toggleVisibility();
			towerLabelName = "lbl_pyramid_heavy";
			labels.get(towerLabelName).toggleVisibility();
			textFields.get(towerName).toggleVisibility();
			towerName = "txt_pyramid_heavy";
			textFields.get(towerName).toggleVisibility();
			textFields.get(towerAuxRange).toggleVisibility();
			towerAuxRange = "txt_pyramid_heavy_range";
			textFields.get(towerAuxRange).toggleVisibility();
			textFields.get(towerAuxDamage).toggleVisibility();
			towerAuxDamage = "txt_pyramid_heavy_damage";
			textFields.get(towerAuxDamage).toggleVisibility();
			textFields.get(towerAuxAttackRate).toggleVisibility();
			towerAuxAttackRate = "txt_pyramid_heavy_time";
			textFields.get(towerAuxAttackRate).toggleVisibility();
		} else if (sender.getName().equals("btn_cube_light")) {
			labels.get(towerLabelName).toggleVisibility();
			towerLabelName = "lbl_cube_light";
			labels.get(towerLabelName).toggleVisibility();
			textFields.get(towerName).toggleVisibility();
			towerName = "txt_cube_light";
			textFields.get(towerName).toggleVisibility();
			textFields.get(towerAuxRange).toggleVisibility();
			towerAuxRange = "txt_cube_light_range";
			textFields.get(towerAuxRange).toggleVisibility();
			textFields.get(towerAuxDamage).toggleVisibility();
			towerAuxDamage = "txt_cube_light_damage";
			textFields.get(towerAuxDamage).toggleVisibility();
			textFields.get(towerAuxAttackRate).toggleVisibility();
			towerAuxAttackRate = "txt_cube_light_time";
			textFields.get(towerAuxAttackRate).toggleVisibility();
		} else if (sender.getName().equals("btn_cube_heavy")) {
			labels.get(towerLabelName).toggleVisibility();
			towerLabelName = "lbl_cube_heavy";
			labels.get(towerLabelName).toggleVisibility();
			textFields.get(towerName).toggleVisibility();
			towerName = "txt_cube_heavy";
			textFields.get(towerName).toggleVisibility();
			textFields.get(towerAuxRange).toggleVisibility();
			towerAuxRange = "txt_cube_heavy_range";
			textFields.get(towerAuxRange).toggleVisibility();
			textFields.get(towerAuxDamage).toggleVisibility();
			towerAuxDamage = "txt_cube_heavy_damage";
			textFields.get(towerAuxDamage).toggleVisibility();
			textFields.get(towerAuxAttackRate).toggleVisibility();
			towerAuxAttackRate = "txt_cube_heavy_time";
			textFields.get(towerAuxAttackRate).toggleVisibility();
		} else if (sender.getName().equals("btn_cylinder_light")) {
			labels.get(towerLabelName).toggleVisibility();
			towerLabelName = "lbl_cylinder_light";
			labels.get(towerLabelName).toggleVisibility();
			textFields.get(towerName).toggleVisibility();
			towerName = "txt_cylinder_light";
			textFields.get(towerName).toggleVisibility();
			textFields.get(towerAuxRange).toggleVisibility();
			towerAuxRange = "txt_cylinder_light_range";
			textFields.get(towerAuxRange).toggleVisibility();
			textFields.get(towerAuxDamage).toggleVisibility();
			towerAuxDamage = "txt_cylinder_light_damage";
			textFields.get(towerAuxDamage).toggleVisibility();
			textFields.get(towerAuxAttackRate).toggleVisibility();
			towerAuxAttackRate = "txt_cylinder_light_time";
			textFields.get(towerAuxAttackRate).toggleVisibility();
		} else if (sender.getName().equals("btn_cylinder_heavy")) {
			labels.get(towerLabelName).toggleVisibility();
			towerLabelName = "lbl_cylinder_heavy";
			labels.get(towerLabelName).toggleVisibility();
			textFields.get(towerName).toggleVisibility();
			towerName = "txt_cylinder_heavy";
			textFields.get(towerName).toggleVisibility();
			textFields.get(towerAuxRange).toggleVisibility();
			towerAuxRange = "txt_cylinder_heavy_range";
			textFields.get(towerAuxRange).toggleVisibility();
			textFields.get(towerAuxDamage).toggleVisibility();
			towerAuxDamage = "txt_cylinder_heavy_damage";
			textFields.get(towerAuxDamage).toggleVisibility();
			textFields.get(towerAuxAttackRate).toggleVisibility();
			towerAuxAttackRate = "txt_cylinder_heavy_time";
			textFields.get(towerAuxAttackRate).toggleVisibility();
		} else if (sender.getName().equals("btn_energycircle")) {
			labels.get(auxiliaryLabelName).toggleVisibility();
			auxiliaryLabelName = "lbl_energycircle";
			labels.get(auxiliaryLabelName).toggleVisibility();
			textFields.get(auxiliaryName).toggleVisibility();
			auxiliaryName = "txt_energycircle";
			textFields.get(auxiliaryName).toggleVisibility();
			textFields.get(towerAuxRange).toggleVisibility();
			towerAuxRange = "txt_energycircle_range";
			textFields.get(towerAuxRange).toggleVisibility();
			textFields.get(towerAuxDamage).toggleVisibility();
			towerAuxDamage = "txt_energycircle_damage";
			textFields.get(towerAuxDamage).toggleVisibility();
			textFields.get(towerAuxAttackRate).toggleVisibility();
			towerAuxAttackRate = "txt_energycircle_time";
			textFields.get(towerAuxAttackRate).toggleVisibility();
		} else if (sender.getName().equals("btn_firecircle")) {
			labels.get(auxiliaryLabelName).toggleVisibility();
			auxiliaryLabelName = "lbl_firecircle";
			labels.get(auxiliaryLabelName).toggleVisibility();
			textFields.get(auxiliaryName).toggleVisibility();
			auxiliaryName = "txt_firecircle";
			textFields.get(auxiliaryName).toggleVisibility();
			textFields.get(towerAuxRange).toggleVisibility();
			towerAuxRange = "txt_firecircle_range";
			textFields.get(towerAuxRange).toggleVisibility();
			textFields.get(towerAuxDamage).toggleVisibility();
			towerAuxDamage = "txt_firecircle_damage";
			textFields.get(towerAuxDamage).toggleVisibility();
			textFields.get(towerAuxAttackRate).toggleVisibility();
			towerAuxAttackRate = "txt_firecircle_time";
			textFields.get(towerAuxAttackRate).toggleVisibility();
		} else if (sender.getName().equals("btn_barret")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_barret";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_barret";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_barret_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_barret_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_barret_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_barret_damage";
			textFields.get(enemyDamage).toggleVisibility();		
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_barret_income";
			textFields.get(enemyIncome).toggleVisibility();		
		} else if (sender.getName().equals("btn_beadle")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_beadle";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_beadle";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_beadle_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_beadle_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_beadle_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_beadle_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_beadle_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_black_mage")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_black_mage";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_black_mage";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_black_mage_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_black_mage_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_black_mage_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_black_mage_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_black_mage_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_brux")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_brux";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_brux";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_brux_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_brux_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_brux_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_brux_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_brux_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_dewie")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_dewie";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_dewie";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_dewie_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_dewie_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_dewie_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_dewie_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_dewie_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_drax")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_drax";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_drax";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_drax_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_drax_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_drax_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_drax_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_drax_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_golbez")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_golbez";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_golbez";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_golbez_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_golbez_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_golbez_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_golbez_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_golbez_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_xulk")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_xulk";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_xulk";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_xulk_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_xulk_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_xulk_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_xulk_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_xulk_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_jenova")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_jenova";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_jenova";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_jenova_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_jenova_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_jenova_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_jenova_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_jenova_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_killer")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_killer";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_killer";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_killer_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_killer_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_killer_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_killer_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_killer_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_luminar")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_luminar";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_luminar";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_luminar_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_luminar_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_luminar_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_luminar_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_luminar_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_phoenix")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_phoenix";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_phoenix";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_phoenix_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_phoenix_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_phoenix_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_phoenix_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_phoenix_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_pirate")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_pirate";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_pirate";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_pirate_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_pirate_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_pirate_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_pirate_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_pirate_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_ryuk")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_ryuk";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_ryuk";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_ryuk_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_ryuk_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_ryuk_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_ryuk_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_ryuk_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_shadow")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_shadow";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_shadow";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_shadow_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_shadow_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_shadow_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_shadow_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_shadow_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_zemus")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_zemus";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_zemus";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_zemus_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_zemus_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_zemus_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_zemus_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_zemus_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_leviathan")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_leviathan";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_leviathan";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_leviathan_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_leviathan_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_leviathan_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_leviathan_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_leviathan_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_ifrit")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_ifrit";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_ifrit";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_ifrit_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_ifrit_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_ifrit_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_ifrit_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_ifrit_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_bahamut")) {
			labels.get(enemyLabelName).toggleVisibility();
			enemyLabelName = "lbl_bahamut";
			labels.get(enemyLabelName).toggleVisibility();
			textFields.get(enemyName).toggleVisibility();
			enemyName = "txt_bahamut";
			textFields.get(enemyName).toggleVisibility();
			textFields.get(enemyHealth).toggleVisibility();
			enemyHealth = "txt_bahamut_health";
			textFields.get(enemyHealth).toggleVisibility();
			textFields.get(enemySpeed).toggleVisibility();
			enemySpeed = "txt_bahamut_speed";
			textFields.get(enemySpeed).toggleVisibility();
			textFields.get(enemyResistance).toggleVisibility();
			enemyResistance = "txt_bahamut_resistance";
			textFields.get(enemyResistance).toggleVisibility();
			textFields.get(enemyDamage).toggleVisibility();
			enemyDamage = "txt_bahamut_damage";
			textFields.get(enemyDamage).toggleVisibility();	
			textFields.get(enemyIncome).toggleVisibility();
			enemyIncome = "txt_bahamut_income";
			textFields.get(enemyIncome).toggleVisibility();	
		} else if (sender.getName().equals("btn_auxiliaries")) {
			disableMainScreen();
			showAuxiliariessGuide();
		} else if (sender.getName().equals("btn_enemies")) {
			disableMainScreen();
			showEnemiesGuide();
		} else if (sender.getName().equals("btn_help")) {
			toggleHelpInfo();
		} else if (sender.getName().equals("btn_ok")) {
			hideGuide();
			enableMainScreen();
		} else if (sender.getName().equals("btn_back")) {
			game.getGameScreenManager().changeScreenState(ScreenState.MAIN_MENU);
		}
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	private void enableMainScreen() {
		state = State.IDLE;
		buttons.get("btn_back").setButtonState(ButtonState.IDLE);
		buttons.get("btn_towers").setButtonState(ButtonState.IDLE);
		buttons.get("btn_enemies").setButtonState(ButtonState.IDLE);
		buttons.get("btn_auxiliaries").setButtonState(ButtonState.IDLE);
		buttons.get("btn_ok").toggleVisibility();
		buttons.get("btn_help").toggleVisibility();
	}
	
	private void disableMainScreen() {
		buttons.get("btn_back").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_towers").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_enemies").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_auxiliaries").setButtonState(ButtonState.DISABLED);
		buttons.get("btn_ok").toggleVisibility();
		buttons.get("btn_help").toggleVisibility();
	}
	
	private void hideGuide() {
		switch (state) {
		case TOWERS_GUIDE:
			if (textFields.get("txt_range_info").isVisible()) {
				toggleTowersAuxiliariesHelpInfo();
			}
			hideTowersGuide();
			break;
		case AUXILIARIES_GUIDE:
			if (textFields.get("txt_range_info").isVisible()) {
				toggleTowersAuxiliariesHelpInfo();
			}
			hideAuxiliariessGuide();
			break;
		case ENEMIES_GUIDE:
			if (textFields.get("txt_enemy_health_info").isVisible()) {
				toggleEnemiesHelpInfo();
			}
			hideEnemiesGuide();
			break;
		default:
			break;
		}
	}
	
	private void toggleHelpInfo() {
		switch (state) {
		case TOWERS_GUIDE:
		case AUXILIARIES_GUIDE:
			toggleTowersAuxiliariesHelpInfo();
			break;
		case ENEMIES_GUIDE:
			toggleEnemiesHelpInfo();
			break;
		default:
			break;
		}
	}
	
	private void toggleTowersAuxiliariesHelpInfo() {
		textFields.get("txt_range_info").toggleVisibility();
		textFields.get("txt_health_info").toggleVisibility();
		textFields.get("txt_time_info").toggleVisibility();
	}

	private void toggleEnemiesHelpInfo() {
		textFields.get("txt_enemy_health_info").toggleVisibility();
		textFields.get("txt_enemy_speed_info").toggleVisibility();
		textFields.get("txt_enemy_resistance_info").toggleVisibility();
		textFields.get("txt_enemy_sphere_energy_info").toggleVisibility();
		textFields.get("txt_enemy_income_info").toggleVisibility();
	}

	private void showTowersGuide() {
		state = State.TOWERS_GUIDE;
		towerLabelName = "lbl_cone_light";
		towerName = "txt_cone_light";
		towerAuxRange = "txt_cone_light_range";
		towerAuxDamage = "txt_cone_light_damage";
		towerAuxAttackRate = "txt_cone_light_time";
		labels.get(towerLabelName).toggleVisibility();
		labels.get("bg_splash").toggleVisibility();
		labels.get("lbl_title_towers").toggleVisibility();
		labels.get("lbl_measure_towers").toggleVisibility();
		textFields.get(towerName).toggleVisibility();
		textFields.get(towerAuxRange).toggleVisibility();
		textFields.get(towerAuxDamage).toggleVisibility();
		textFields.get(towerAuxAttackRate).toggleVisibility();
		buttons.get("btn_cone_light").toggleVisibility();
		buttons.get("btn_cone_heavy").toggleVisibility();
		buttons.get("btn_pyramid_light").toggleVisibility();
		buttons.get("btn_pyramid_heavy").toggleVisibility();
		buttons.get("btn_cube_light").toggleVisibility();
		buttons.get("btn_cube_heavy").toggleVisibility();
		buttons.get("btn_cylinder_light").toggleVisibility();
		buttons.get("btn_cylinder_heavy").toggleVisibility();
	}
	
	private void hideTowersGuide() {
		labels.get(towerLabelName).toggleVisibility();
		labels.get("bg_splash").toggleVisibility();
		labels.get("lbl_title_towers").toggleVisibility();
		labels.get("lbl_measure_towers").toggleVisibility();
		textFields.get(towerName).toggleVisibility();
		textFields.get(towerAuxRange).toggleVisibility();
		textFields.get(towerAuxDamage).toggleVisibility();
		textFields.get(towerAuxAttackRate).toggleVisibility();
		buttons.get("btn_cone_light").toggleVisibility();
		buttons.get("btn_cone_heavy").toggleVisibility();
		buttons.get("btn_pyramid_light").toggleVisibility();
		buttons.get("btn_pyramid_heavy").toggleVisibility();
		buttons.get("btn_cube_light").toggleVisibility();
		buttons.get("btn_cube_heavy").toggleVisibility();
		buttons.get("btn_cylinder_light").toggleVisibility();
		buttons.get("btn_cylinder_heavy").toggleVisibility();
	}

	private void showAuxiliariessGuide() {
		state = State.AUXILIARIES_GUIDE;
		auxiliaryLabelName = "lbl_energycircle";
		auxiliaryName = "txt_energycircle";
		towerAuxRange = "txt_energycircle_range";
		towerAuxDamage = "txt_energycircle_damage";
		towerAuxAttackRate = "txt_energycircle_time";
		labels.get(auxiliaryLabelName).toggleVisibility();
		labels.get("bg_splash").toggleVisibility();
		labels.get("lbl_title_auxiliaries").toggleVisibility();
		labels.get("lbl_measure_auxiliaries").toggleVisibility();
		textFields.get(auxiliaryName).toggleVisibility();
		textFields.get(towerAuxRange).toggleVisibility();
		textFields.get(towerAuxDamage).toggleVisibility();
		textFields.get(towerAuxAttackRate).toggleVisibility();
		buttons.get("btn_energycircle").toggleVisibility();
		buttons.get("btn_firecircle").toggleVisibility();
	}
	
	private void hideAuxiliariessGuide() {
		labels.get(auxiliaryLabelName).toggleVisibility();
		labels.get("bg_splash").toggleVisibility();
		labels.get("lbl_title_auxiliaries").toggleVisibility();
		labels.get("lbl_measure_auxiliaries").toggleVisibility();
		textFields.get(auxiliaryName).toggleVisibility();
		textFields.get(towerAuxRange).toggleVisibility();
		textFields.get(towerAuxDamage).toggleVisibility();
		textFields.get(towerAuxAttackRate).toggleVisibility();
		buttons.get("btn_energycircle").toggleVisibility();
		buttons.get("btn_firecircle").toggleVisibility();
	}
	
	private void showEnemiesGuide() {
		state = State.ENEMIES_GUIDE;
		enemyLabelName = "lbl_barret";
		enemyName = "txt_barret";
		enemyHealth = "txt_barret_health";
		enemySpeed = "txt_barret_speed";
		enemyResistance = "txt_barret_resistance";
		enemyDamage = "txt_barret_damage";
		enemyIncome = "txt_barret_income";
		labels.get(enemyLabelName).toggleVisibility();
		labels.get("bg_splash").toggleVisibility();
		labels.get("lbl_title_enemies").toggleVisibility();
		labels.get("lbl_measure_enemies").toggleVisibility();
		textFields.get(enemyName).toggleVisibility();
		textFields.get(enemyHealth).toggleVisibility();
		textFields.get(enemySpeed).toggleVisibility();
		textFields.get(enemyResistance).toggleVisibility();
		textFields.get(enemyDamage).toggleVisibility();
		textFields.get(enemyIncome).toggleVisibility();
		buttons.get("btn_barret").toggleVisibility();
		buttons.get("btn_beadle").toggleVisibility();
		buttons.get("btn_black_mage").toggleVisibility();
		buttons.get("btn_brux").toggleVisibility();
		buttons.get("btn_dewie").toggleVisibility();
		buttons.get("btn_drax").toggleVisibility();
		buttons.get("btn_golbez").toggleVisibility();
		buttons.get("btn_xulk").toggleVisibility();
		buttons.get("btn_jenova").toggleVisibility();
		buttons.get("btn_killer").toggleVisibility();
		buttons.get("btn_luminar").toggleVisibility();
		buttons.get("btn_phoenix").toggleVisibility();
		buttons.get("btn_pirate").toggleVisibility();
		buttons.get("btn_ryuk").toggleVisibility();
		buttons.get("btn_shadow").toggleVisibility();
		buttons.get("btn_zemus").toggleVisibility();
		buttons.get("btn_leviathan").toggleVisibility();
		buttons.get("btn_ifrit").toggleVisibility();
		buttons.get("btn_bahamut").toggleVisibility();
	}
	
	private void hideEnemiesGuide() {
		labels.get(enemyLabelName).toggleVisibility();
		labels.get("bg_splash").toggleVisibility();
		labels.get("lbl_title_enemies").toggleVisibility();
		labels.get("lbl_measure_enemies").toggleVisibility();
		textFields.get(enemyName).toggleVisibility();
		textFields.get(enemyHealth).toggleVisibility();
		textFields.get(enemySpeed).toggleVisibility();
		textFields.get(enemyResistance).toggleVisibility();
		textFields.get(enemyDamage).toggleVisibility();
		textFields.get(enemyIncome).toggleVisibility();
		buttons.get("btn_barret").toggleVisibility();
		buttons.get("btn_beadle").toggleVisibility();
		buttons.get("btn_black_mage").toggleVisibility();
		buttons.get("btn_brux").toggleVisibility();
		buttons.get("btn_dewie").toggleVisibility();
		buttons.get("btn_drax").toggleVisibility();
		buttons.get("btn_golbez").toggleVisibility();
		buttons.get("btn_xulk").toggleVisibility();
		buttons.get("btn_jenova").toggleVisibility();
		buttons.get("btn_killer").toggleVisibility();
		buttons.get("btn_luminar").toggleVisibility();
		buttons.get("btn_phoenix").toggleVisibility();
		buttons.get("btn_pirate").toggleVisibility();
		buttons.get("btn_ryuk").toggleVisibility();
		buttons.get("btn_shadow").toggleVisibility();
		buttons.get("btn_zemus").toggleVisibility();
		buttons.get("btn_leviathan").toggleVisibility();
		buttons.get("btn_ifrit").toggleVisibility();
		buttons.get("btn_bahamut").toggleVisibility();
	}

	// Properties ----------------------------------------
}
