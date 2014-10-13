package com.ketphish.spheredefense.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.models.Config.ConfigItem;
import com.ketphish.spheredefense.models.profile.Options.Language;
import com.ketphish.spheredefense.models.trail.Snippet;
import com.ketphish.spheredefense.models.trail.Trail;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.Label;

public final class LevelMapConfigHandler
extends GameScreenConfigHandler {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	protected Trail trail;
	protected boolean isLevelMapField;
	protected boolean isLevelMapFieldLabels;
	protected boolean isLevelMapFieldLabel;
	protected boolean isLevelMapFieldLabelRectangle;
	protected boolean isLevelMapFieldLabelTexture;
	protected boolean isLevelMapFieldButtons;
	protected boolean isLevelMapFieldButton;
	protected boolean isLevelMapFieldButtonRectangle;
	protected boolean isLevelMapFieldButtonTexture;
	protected boolean isTrails;
	protected boolean isTrail;
	protected boolean isSnippet;
	
	// Constructors --------------------------------------
	public LevelMapConfigHandler(Language language, int level) {
		super(language, level);
		isLevelMapField = false;
		isLevelMapFieldLabel = false;
		isLevelMapFieldLabelRectangle = false;
		isLevelMapFieldLabelTexture = false;
		isLevelMapFieldButtons = false;
		isLevelMapFieldButton = false;
		isLevelMapFieldButtonRectangle = false;
		isLevelMapFieldButtonTexture = false;
		isTrails = false;
		isTrail = false;
		isSnippet = false;
	}
	
	// Extends -------------------------------------------
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (isConfig && isPanels && !isLevelMapField && qName.equalsIgnoreCase("level_map_field")) {
			config.init(ConfigItem.LEVEL_MAP_FIELD);
			isLevelMapField = true;
		} else if (isConfig && isPanels && isLevelMapField && !isLevelMapFieldLabels && qName.equalsIgnoreCase("labels")) {
			isLevelMapFieldLabels = true;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldLabels && !isLevelMapFieldLabel && qName.equalsIgnoreCase("label")) {
			handlePanelAttributes(attributes);
			isLevelMapFieldLabel = true;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldLabels && isLevelMapFieldLabel && !isLevelMapFieldLabelRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isLevelMapFieldLabelRectangle = true;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldLabels && isLevelMapFieldLabel && !isLevelMapFieldLabelTexture && qName.equalsIgnoreCase("texture")) {
			handlePanelTexture(ConfigItem.LABELS, attributes);
			isLevelMapFieldLabelTexture = true;
		} else if (isConfig && isPanels && isLevelMapField && !isLevelMapFieldButtons && qName.equalsIgnoreCase("buttons")) {
			config.getLevelMapField().initButtons();
			isLevelMapFieldButtons = true;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldButtons && !isLevelMapFieldButton && qName.equalsIgnoreCase("button")) {
			handlePanelAttributes(attributes);
			isLevelMapFieldButton = true;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldButtons && isLevelMapFieldButton && !isLevelMapFieldButtonRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isLevelMapFieldButtonRectangle = true;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldButtons && isLevelMapFieldButton && !isLevelMapFieldButtonTexture && qName.equalsIgnoreCase("texture")) {
			handlePanelTexture(ConfigItem.BUTTONS, attributes);
			isLevelMapFieldButtonTexture = true;
		} else if (isConfig && !isTrails && qName.equalsIgnoreCase("trails")) {
			config.init(ConfigItem.TRAILS);
			isTrails = true;
		} else if (isConfig && isTrails && !isTrail && qName.equalsIgnoreCase("trail")) {
			handleTrail(attributes);
			isTrail = true;
		} else if (isConfig && isTrails && isTrail && !isSnippet && qName.equalsIgnoreCase("snippet")) {
			handleSnippet(attributes);
			isSnippet = true;
		} else {
			super.startElement(uri, localName, qName, attributes);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (isConfig && isPanels && isLevelMapField && qName.equalsIgnoreCase("level_map_field")) {
			isLevelMapField = false;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldLabels && qName.equalsIgnoreCase("labels")) {
			isLevelMapFieldLabels = false;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldLabel && qName.equalsIgnoreCase("label")) {
			isLevelMapFieldLabel = false;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldLabel && isLevelMapFieldLabelRectangle && qName.equalsIgnoreCase("rectangle")) {
			isLevelMapFieldLabelRectangle = false;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldLabel && isLevelMapFieldLabelTexture && qName.equalsIgnoreCase("texture")) {
			isLevelMapFieldLabelTexture = false;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldButtons && qName.equalsIgnoreCase("buttons")) {
			isLevelMapFieldButtons = false;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldButtons && isLevelMapFieldButton && qName.equalsIgnoreCase("button")) {
			isLevelMapFieldButton = false;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldButtons && isLevelMapFieldButton && isLevelMapFieldButtonRectangle && qName.equalsIgnoreCase("rectangle")) {
			isLevelMapFieldButtonRectangle = false;
		} else if (isConfig && isPanels && isLevelMapField && isLevelMapFieldButtons && isLevelMapFieldButton && isLevelMapFieldButtonTexture && qName.equalsIgnoreCase("texture")) {
			isLevelMapFieldButtonTexture = false;
		} else if (isConfig && isTrails && qName.equalsIgnoreCase("trails")) {
			isTrails = false;
		} else if (isConfig && isTrails && isTrail && qName.equalsIgnoreCase("trail")) {
			config.getTrails().add(trail);
			isTrail = false;
		} else if (isConfig && isTrails && isTrail && isSnippet && qName.equalsIgnoreCase("snippet")) {
			isSnippet = false;
		} else {
			super.endElement(uri, localName, qName);
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public boolean handlePanelTexture(ConfigItem item, Attributes attributes) {
		final boolean isLanguageConflict = isLanguageConflict(attributes);
		final boolean isLevelConflict = isLevelConflict(attributes);
		if (!isLanguageConflict && !isLevelConflict) {
			final int id = Integer.valueOf(attributes.getValue("id"));
			final String textureName = getTextureName(id);
			switch (item) {
			case LABELS:
				final Label label = new Label(
						panelName, panelParentName, textureName, 
						panelRectangle, panelZindex, isPanelVisible);
				config.getLevelMapField().getLabels().add(label);
				break;
			case BUTTONS:
				final Button button = new Button(
						panelName, panelParentName, textureName, 
						panelRectangle,panelZindex, isPanelVisible);
				config.getLevelMapField().getButtons().put(panelName, button);
				break;
			default:
				break;
			}
			resetPanelValues();
			return true;
		}
		return false;
	}
	
	public void handleTrail(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		trail = new Trail(id);
	}
	
	public void handleSnippet(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		final float x1 = Float.valueOf(attributes.getValue("x1"));
		final float y1 = Float.valueOf(attributes.getValue("y1"));
		final float x2 = Float.valueOf(attributes.getValue("x2"));
		final float y2 = Float.valueOf(attributes.getValue("y2"));
		final Vector2 begin = new Vector2(x1, y1);
		final Vector2 end = new Vector2(x2, y2);
		final Snippet snippet = new Snippet(id, begin, end);
		trail.addSnippet(snippet);
	}
	
	// Properties ----------------------------------------
}
