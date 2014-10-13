package com.ketphish.spheredefense.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.ketphish.spheredefense.factories.TowerMenuFactory;
import com.ketphish.spheredefense.models.Config.ConfigItem;
import com.ketphish.spheredefense.models.profile.Options.Language;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.GamePlayField;
import com.ketphish.spheredefense.views.panels.Label;
import com.ketphish.spheredefense.views.panels.menus.TowerMenu;

public final class GamePlayScreenConfigHandler
extends GameScreenConfigHandler {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private TowerMenu towerMenu;
	private final TowerMenuFactory towerMenuFactory;
	private boolean isEnemyHealthBar;
	private boolean isEnemyHealthBarRectangle;
	private boolean isEnemyHealthBarTexture;
	private boolean isEnemyHealthBarLabel;
	private boolean isEnemyHealthBarLabelRectangle;
	private boolean isEnemyHealthBarLabelTexture;
	private boolean isTowerBuildBar;
	private boolean isTowerBuildBarRectangle;
	private boolean isTowerBuildBarTexture;
	private boolean isTowerBuildBarLabel;
	private boolean isTowerBuildBarLabelRectangle;
	private boolean isTowerBuildBarLabelTexture;
	private boolean isGamePlayField;
	private boolean isGamePlayFieldRectangle;
	private boolean isTowersMenu;
	private boolean isTowerMenu;
	private boolean isTowerMenuRectangle;
	private boolean isTowerMenuTexture;
	private boolean isTowerMenuButtons;
	private boolean isTowerMenuButton;
	private boolean isTowerMenuButtonRectangle;
	private boolean isTowerMenuButtonTexture;
	
	// Constructors --------------------------------------
	public GamePlayScreenConfigHandler(
			Language language,
			int level) {
		super(language, level);
		towerMenuFactory = new TowerMenuFactory();
		isEnemyHealthBar = false;
		isEnemyHealthBarRectangle = false;
		isEnemyHealthBarTexture = false;
		isEnemyHealthBarLabel = false;
		isEnemyHealthBarLabelRectangle = false;
		isEnemyHealthBarLabelTexture = false;
		isTowerBuildBar = false;
		isTowerBuildBarRectangle = false;
		isTowerBuildBarTexture = false;
		isTowerBuildBarLabel = false;
		isTowerBuildBarLabelRectangle = false;
		isTowerBuildBarLabelTexture = false;
		isGamePlayField = false;
		isGamePlayFieldRectangle = false;
		isTowersMenu = false;
		isTowerMenu = false;
		isTowerMenuRectangle = false;
		isTowerMenuTexture = false;
		isTowerMenuButtons = false;
		isTowerMenuButton = false;
		isTowerMenuButtonRectangle = false;
		isTowerMenuButtonTexture = false;
	}
	
	// Extends -------------------------------------------
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (isPanels && !isEnemyHealthBar && qName.equalsIgnoreCase("enemy_health_bar")) {
			handleEnemyHealthBar(attributes);
			isEnemyHealthBar = true;
		} else if (isPanels && isEnemyHealthBar && !isEnemyHealthBarRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isEnemyHealthBarRectangle = true;
		} else if (isPanels && isEnemyHealthBar && !isEnemyHealthBarTexture && qName.equalsIgnoreCase("texture")) {
			handleEnemyHealthBarTexture(attributes);
			isEnemyHealthBarTexture = true;
		} else if (isPanels && isEnemyHealthBar && !isEnemyHealthBarLabel && qName.equalsIgnoreCase("label")) {
			handleEnemyHealthBarLabel(attributes);
			isEnemyHealthBarLabel = true;
		} else if (isPanels && isEnemyHealthBar && isEnemyHealthBarLabel && !isEnemyHealthBarLabelRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isEnemyHealthBarLabelRectangle = true;
		} else if (isPanels && isEnemyHealthBar && isEnemyHealthBarLabel && !isEnemyHealthBarLabelTexture && qName.equalsIgnoreCase("texture")) {
			handleEnemyHealthBarLabelTexture(attributes);
			isEnemyHealthBarLabelTexture = true;
		} else if (isPanels && !isTowerBuildBar && qName.equalsIgnoreCase("tower_build_bar")) {
			handleBuildTowerBar(attributes);
			isTowerBuildBar = true;
		} else if (isPanels && isTowerBuildBar && !isTowerBuildBarRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isTowerBuildBarRectangle = true;
		} else if (isPanels && isTowerBuildBar && !isTowerBuildBarTexture && qName.equalsIgnoreCase("texture")) {
			handleBuildTowerBarTexture(attributes);
			isTowerBuildBarTexture = true;
		} else if (isPanels && isTowerBuildBar && !isTowerBuildBarLabel && qName.equalsIgnoreCase("label")) {
			handleBuildTowerBarLabel(attributes);
			isTowerBuildBarLabel = true;
		} else if (isPanels && isTowerBuildBar && isTowerBuildBarLabel && !isTowerBuildBarLabelRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isTowerBuildBarLabelRectangle = true;
		} else if (isPanels && isTowerBuildBar && isTowerBuildBarLabel && !isTowerBuildBarLabelTexture && qName.equalsIgnoreCase("texture")) {
			handleBuildTowerBarLabelTexture(attributes);
			isTowerBuildBarLabelTexture = true;
		} else if (isPanels && !isGamePlayField && qName.equalsIgnoreCase("game_play_field")) {
			handlePanelAttributes(attributes);
			isGamePlayField = true;
		} else if (isPanels && isGamePlayField && !isGamePlayFieldRectangle && qName.equalsIgnoreCase("rectangle")) {
			handleGamePlayFieldRectangle(attributes);
			isGamePlayFieldRectangle = true;
		} else if (isPanels && !isTowersMenu && qName.equalsIgnoreCase("towers_menu")) {
			config.init(ConfigItem.TOWERS_MENU);
			isTowersMenu = true;
		} else if (isPanels && isTowersMenu && !isTowerMenu && qName.equalsIgnoreCase("tower_menu")) {
			handlePanelAttributes(attributes);
			isTowerMenu = true;
		} else if (isPanels && isTowersMenu && isTowerMenu && !isTowerMenuRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isTowerMenuRectangle = true;
		} else if (isPanels && isTowersMenu && isTowerMenu  && !isTowerMenuTexture && qName.equalsIgnoreCase("texture")) {
			handleTowerMenuTexture(attributes);
			isTowerMenuTexture = true;
		} else if (isPanels && isTowersMenu && isTowerMenu && !isTowerMenuButtons && qName.equalsIgnoreCase("buttons")) {
			initTowerMenuButtons();
			isTowerMenuButtons = true;
		} else if (isPanels && isTowersMenu && isTowerMenu && isTowerMenuButtons && !isTowerMenuButton && qName.equalsIgnoreCase("button")) {
			handlePanelAttributes(attributes);
			isTowerMenuButton = true;
		} else if (isPanels && isTowersMenu && isTowerMenu && isTowerMenuButtons && isTowerMenuButton && !isTowerMenuButtonRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isTowerMenuButtonRectangle = true;
		} else if (isPanels && isTowersMenu && isTowerMenu && isTowerMenuButtons && isTowerMenuButton && !isTowerMenuButtonTexture && qName.equalsIgnoreCase("texture")) {
			handleTowerMenuButtonTexture(attributes);
			isTowerMenuButtonTexture = true;
		} else {
			super.startElement(uri, localName, qName, attributes);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (isPanels && isEnemyHealthBar && qName.equalsIgnoreCase("enemy_health_bar")) {
			isEnemyHealthBar = false;
		} else if (isPanels && isEnemyHealthBar && isEnemyHealthBarLabel && qName.equalsIgnoreCase("label")) {
			isEnemyHealthBarLabel = false;
		} else if (isPanels && isEnemyHealthBar && isEnemyHealthBarLabel && isEnemyHealthBarLabelRectangle && qName.equalsIgnoreCase("rectangle")) {
			isEnemyHealthBarLabelRectangle = false;
		} else if (isPanels && isEnemyHealthBar && isEnemyHealthBarLabel && isEnemyHealthBarLabelTexture && qName.equalsIgnoreCase("texture")) {
			isEnemyHealthBarLabelTexture = false;
		} else if (isPanels && isTowerBuildBar && qName.equalsIgnoreCase("tower_build_bar")) {
			isTowerBuildBar = false;
		} else if (isPanels && isTowerBuildBar && isTowerBuildBarLabel && qName.equalsIgnoreCase("label")) {
			isTowerBuildBarLabel = false;
		} else if (isPanels && isTowerBuildBar && isTowerBuildBarLabel && isTowerBuildBarLabelRectangle && qName.equalsIgnoreCase("rectangle")) {
			isTowerBuildBarLabelRectangle = false;
		} else if (isPanels && isTowerBuildBar && isTowerBuildBarLabel && isTowerBuildBarLabelTexture && qName.equalsIgnoreCase("texture")) {
			isTowerBuildBarLabelTexture = false;
		} else if (isPanels && isGamePlayField && qName.equalsIgnoreCase("game_play_field")) {
			isGamePlayField = false;
		} else if (isPanels && isGamePlayField && isGamePlayFieldRectangle && qName.equalsIgnoreCase("rectangle")) {
			isGamePlayFieldRectangle = false;
		} else if (isPanels && isTowersMenu && qName.equalsIgnoreCase("towers_menu")) {
			isTowersMenu = false;
		} else if (isPanels && isTowersMenu && isTowerMenu && qName.equalsIgnoreCase("tower_menu")) {
			isTowerMenu = false;
			isTowerMenuRectangle = false;
			isTowerMenuTexture = false;
			config.getTowersMenu().put(towerMenu.getName(), towerMenu);
		} else if (isPanels && isTowersMenu && isTowerMenu && isTowerMenuButtons && qName.equalsIgnoreCase("buttons")) {
			isTowerMenuButtons = false;
		} else if (isPanels && isTowersMenu && isTowerMenu && isTowerMenuButtons && isTowerMenuButton && qName.equalsIgnoreCase("button")) {
			isTowerMenuButton = false;
		} else if (isPanels && isTowersMenu && isTowerMenu && isTowerMenuButtons && isTowerMenuButton && isTowerMenuButtonRectangle && qName.equalsIgnoreCase("rectangle")) {
			isTowerMenuButtonRectangle = false;
		} else if (isPanels && isTowersMenu && isTowerMenu && isTowerMenuButtons && isTowerMenuButton && isTowerMenuButtonTexture && qName.equalsIgnoreCase("texture")) {
			isTowerMenuButtonTexture = false;
		} else {
			super.endElement(uri, localName, qName);
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	private void handleEnemyHealthBar(Attributes attributes) {
		config.init(ConfigItem.ENEMY_HEALTH_BAR);
		handlePanelAttributes(attributes);
	}
	
	private void handleEnemyHealthBarTexture(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		final String textureName = getTextureName(id);
		config.getEnemyHealthBar().setName(panelName);
		config.getEnemyHealthBar().setRectangle(panelRectangle);
		config.getEnemyHealthBar().setTextureName(textureName);
		config.getEnemyHealthBar().setZIndex(panelZindex);
		config.getEnemyHealthBar().setVisible(isPanelVisible);
	}
	
	private void handleEnemyHealthBarLabel(Attributes attributes) {
		handlePanelAttributes(attributes);
	}
	
	private void handleEnemyHealthBarLabelTexture(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		final String textureName = getTextureName(id);
		final Label label = new Label(panelName, panelParentName, textureName, panelRectangle, panelZindex, isPanelVisible);
		config.getEnemyHealthBar().setProgress(label);
	}
	
	private void handleBuildTowerBar(Attributes attributes) {
		config.init(ConfigItem.TOWER_BUILD_BAR);
		handlePanelAttributes(attributes);
	}
	
	private void handleBuildTowerBarTexture(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		final String textureName = getTextureName(id);
		config.getTowerBuildBar().setName(panelName);
		config.getTowerBuildBar().setRectangle(panelRectangle);
		config.getTowerBuildBar().setTextureName(textureName);
		config.getTowerBuildBar().setZIndex(panelZindex);
		config.getTowerBuildBar().setVisible(isPanelVisible);
	}
	
	private void handleBuildTowerBarLabel(Attributes attributes) {
		handlePanelAttributes(attributes);
	}
	
	private void handleBuildTowerBarLabelTexture(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		final String textureName = getTextureName(id);
		final Label label = new Label(panelName, panelParentName, textureName, panelRectangle, panelZindex, isPanelVisible);
		config.getTowerBuildBar().setProgress(label);
	}

	public void handleGamePlayFieldRectangle(Attributes attributes) {
		super.handlePanelRectangle(attributes);
		final GamePlayField gamePlayField = new GamePlayField(
				panelName, panelParentName, panelRectangle, 
				panelZindex, isPanelVisible);
		config.setGamePlayField(gamePlayField);
	}
	
	private void handleTowerMenuTexture(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		final String textureName = getTextureName(id);
		towerMenu = towerMenuFactory.getTowerMenu(panelName);
		towerMenu.setName(panelName);
		towerMenu.setParentName(panelParentName);
		towerMenu.setTextureName(textureName);
		towerMenu.setRectangle(panelRectangle);
		towerMenu.setZIndex(panelZindex);
		towerMenu.setVisible(isPanelVisible);
	}

	private void initTowerMenuButtons() {
		towerMenu.initButtons();
	}

	private void handleTowerMenuButtonTexture(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		final String textureName = getTextureName(id);
		final Button button = new Button(
				panelName, panelParentName, textureName, 
				panelRectangle, panelZindex, isPanelVisible);
		towerMenu.getButtons().put(panelName, button);
	}
	
	// Properties ----------------------------------------
}
