package com.ketphish.spheredefense.importers;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.badlogic.gdx.Gdx;
import com.ketphish.spheredefense.handlers.GameObjectsConfigHandler;
import com.ketphish.spheredefense.handlers.GameScreenConfigHandler;
import com.ketphish.spheredefense.handlers.GamePlayScreenConfigHandler;
import com.ketphish.spheredefense.handlers.LevelConfigHandler;
import com.ketphish.spheredefense.handlers.LevelMapConfigHandler;
import com.ketphish.spheredefense.models.Config;
import com.ketphish.spheredefense.models.profile.Options.Language;

public final class ConfigImporter {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final Language language;
	private final int level;
	
	// Constructors --------------------------------------
	public ConfigImporter(Language language, int level) {
		this.language = language;
		this.level = level;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	public Config importGameScreenConfig(String path) {
		final GameScreenConfigHandler handler = new GameScreenConfigHandler(language, level);
		try {
			getSAXParser().parse(Gdx.files.internal(path).read(), handler);
			return handler.getConfig();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Config importGamePlayScreenConfig(String path) {
		final GamePlayScreenConfigHandler handler = new GamePlayScreenConfigHandler(language, level);
		try {
			getSAXParser().parse(Gdx.files.internal(path).read(), handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handler.getConfig();
	}
	
	public Config importLevelMapConfig(Config config, String path) {
		final LevelMapConfigHandler handler = new LevelMapConfigHandler(language, level);
		handler.setConfig(config);
		try {
			getSAXParser().parse(Gdx.files.internal(path).read(), handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handler.getConfig();
	}
	
	public Config importGamObjectsConfig(Config config, String path) {
		final GameObjectsConfigHandler handler = new GameObjectsConfigHandler();
		handler.setConfig(config);
		try {
			getSAXParser().parse(Gdx.files.internal(path).read(), handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handler.getConfig();
	}
	
	public Config importLevelConfig(Config config, String path) {
		final LevelConfigHandler handler = new LevelConfigHandler(config);
		try {
			getSAXParser().parse(Gdx.files.internal(path).read(), handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handler.getConfig();
	}
	
	// Properties ----------------------------------------
	private SAXParser getSAXParser() throws ParserConfigurationException, SAXException {
		final SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		return saxParserFactory.newSAXParser();	
	}
	
}
