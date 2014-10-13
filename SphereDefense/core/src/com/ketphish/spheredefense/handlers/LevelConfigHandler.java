package com.ketphish.spheredefense.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ketphish.spheredefense.models.Config;
import com.ketphish.spheredefense.models.level.Foe;
import com.ketphish.spheredefense.models.level.Level;
import com.ketphish.spheredefense.models.level.Wave;

public final class LevelConfigHandler
extends DefaultHandler {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final Config config;
	private Wave wave;
	private boolean isConfig;
	private boolean isLevel;
	private boolean isLevelSelected;
	private boolean isWaves;
	private boolean isWave;
	private boolean isFoe;
	
	// Constructors --------------------------------------
	public LevelConfigHandler(Config config) {
		this.config = config;
		isConfig = false;
		isLevel = false;
		isLevelSelected = false;
		isWaves = false;
		isWave = false;
		isFoe = false;
	}
	
	// Extends -------------------------------------------
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (!isConfig && qName.equalsIgnoreCase("config")) {
			isConfig = true;
		} else if (isConfig && !isLevelSelected && !isLevel && qName.equalsIgnoreCase("level")) {
			handleLevel(attributes);
			isLevel = true;
			isLevelSelected = true;
		} else if (isConfig && isLevel && !isWaves && qName.equalsIgnoreCase("waves")) {
			isWaves = true;
		} else if (isConfig && isLevel && isWaves && !isWave && qName.equalsIgnoreCase("wave")) {
			handleWave(attributes);
			isWave = true;
		} else if (isConfig && isLevel && isWaves && isWave && !isFoe && qName.equalsIgnoreCase("foe")) {
			handleFoe(attributes);
			isFoe = true;
		} 
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (isConfig && qName.equalsIgnoreCase("config")) {
			isConfig = false;
		} else if (isConfig && isLevel && qName.equalsIgnoreCase("level")) {
			isLevel = false;
		} else if (isConfig && isLevel && isWaves && qName.equalsIgnoreCase("waves")) {
			isWaves = false;
		} else if (isConfig && isLevel && isWaves && isWave && qName.equalsIgnoreCase("wave")) {
			config.getLevel().getWaves().add(wave);
			isWave = false;
		} else if (isConfig && isLevel && isWaves && isWave && isFoe && qName.equalsIgnoreCase("foe")) {
			isFoe = false;
		} 
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	private void handleLevel(Attributes attributes) {
		final int energy = Integer.valueOf(attributes.getValue("energy"));
		final int diamonds = Integer.valueOf(attributes.getValue("diamonds"));
		final float startTime = Integer.valueOf(attributes.getValue("start_time"));
		final Level level = new Level(energy, diamonds, startTime);
		config.setLevel(level);
	}

	private void handleWave(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		final float duration = Float.valueOf(attributes.getValue("duration"));
		wave = new Wave(id, duration);
	}

	private void handleFoe(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		final String name = attributes.getValue("name");
		final int trailId = Integer.valueOf(attributes.getValue("trail_id"));
		final float startTime = Float.valueOf(attributes.getValue("start_time"));
		final Foe foe = new Foe(id, name, trailId, startTime);
		wave.getFoes().add(foe);
	}
	
	// Properties ----------------------------------------
	public Config getConfig() {
		return config;
	}
	
}
