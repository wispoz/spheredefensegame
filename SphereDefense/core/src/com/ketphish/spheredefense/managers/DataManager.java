package com.ketphish.spheredefense.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;
import com.ketphish.spheredefense.factories.ConfigPathFactory;
import com.ketphish.spheredefense.helpers.SpriteInfo;
import com.ketphish.spheredefense.helpers.database.DatabaseHelper;
import com.ketphish.spheredefense.importers.ConfigImporter;
import com.ketphish.spheredefense.importers.assets.AnimsImporter;
import com.ketphish.spheredefense.importers.assets.FontImporter;
import com.ketphish.spheredefense.importers.assets.MusicImporter;
import com.ketphish.spheredefense.importers.assets.SoundImporter;
import com.ketphish.spheredefense.importers.assets.TextureImporter;
import com.ketphish.spheredefense.managers.GameScreenManager.ScreenState;
import com.ketphish.spheredefense.models.Config;
import com.ketphish.spheredefense.models.profile.Advance;
import com.ketphish.spheredefense.models.profile.Options;
import com.ketphish.spheredefense.models.profile.Options.Language;
import com.ketphish.spheredefense.models.profile.Trophy;
import com.ketphish.spheredefense.models.profile.Upgrade;
import com.ketphish.spheredefense.repositories.AdvanceSqlRepository;
import com.ketphish.spheredefense.repositories.OptionsSqlRepository;
import com.ketphish.spheredefense.repositories.TrophySqlRepository;
import com.ketphish.spheredefense.repositories.UpgradeSqlRepository;

public final class DataManager
implements Disposable {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	private final DatabaseHelper databaseHelper;
	private final AssetManager assetManager;
	private TextureImporter textureImporter;
	private AnimsImporter animsImporter;
	private FontImporter fontImporter;
	private boolean isAssetLoading;
	
	// Constructors --------------------------------------
	public DataManager() {
		databaseHelper = new DatabaseHelper();
		assetManager = new AssetManager();
		isAssetLoading = false;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	public boolean getIsAssetLoaded() {
		if (isAssetLoading && assetManager.update()) {
			isAssetLoading = false;
			return true;
		}
		return false;
	}
	
	@Override
	public void dispose() {
		assetManager.dispose();
	}
	
	// Methods -------------------------------------------
	public void reset() {
		assetManager.clear();
		if (textureImporter != null) {
			textureImporter = null;
		}
		if (animsImporter != null) {
			animsImporter = null;
		}
		if (fontImporter != null) {
			fontImporter = null;
		}
	}
	
	public void initDatabase() {
		databaseHelper.activateDatabase();
	}

	public Advance getAdvance() {
		return new AdvanceSqlRepository(databaseHelper).select();
	}

	public Options getOptions() {
		return new OptionsSqlRepository(databaseHelper).select();
	}

	public List<Trophy> getTropies() {
		return new TrophySqlRepository(databaseHelper).select();
	}

	public List<Upgrade> getUpgrades() {
		return new UpgradeSqlRepository(databaseHelper).select();
	}

	public void updateOptions(Options options) {
		new OptionsSqlRepository(databaseHelper).update(options);
	}

	public void updateUpgrades(List<Upgrade> upgrades) {
		new UpgradeSqlRepository(databaseHelper).update(upgrades);
	}

	public void updateAdvance(Advance advance) {
		new AdvanceSqlRepository(databaseHelper).update(advance);
	}
	
	public void updateTrophy(List<Trophy> trophies) {
		new TrophySqlRepository(databaseHelper).update(trophies);
	}
	
	public void clearDatabaseTables() {
		databaseHelper.createNewDataByDefault();
	}
	
	public Config importGameScreenConfig(Language language, int level, ScreenState next) {
		final ConfigImporter importer = new ConfigImporter(language, level);
		final ConfigPathFactory pathFactory = new ConfigPathFactory();
		final String path = pathFactory.getScreenConfigPath(next);
		return importer.importGameScreenConfig(path);
	}

	public Config importGamePlayScreenConfig(Language language, int level, ScreenState next) {
		final ConfigImporter importer = new ConfigImporter(language, level);
		final ConfigPathFactory pathFactory = new ConfigPathFactory();
		String path = pathFactory.getScreenConfigPath(next);
		Config config = importer.importGamePlayScreenConfig(path);
		path = pathFactory.getLevelMapPath(level);
		importer.importLevelMapConfig(config, path);
		path = pathFactory.getLevelConfigPath(level);
		importer.importLevelConfig(config, path);
		path = pathFactory.getGameObjectsConfigPath();
		importer.importGamObjectsConfig(config, path);
		return config;
	}
	
	public void initTextureImporter(HashMap<Integer, String> textures) {
		textureImporter = new TextureImporter(assetManager);
		textureImporter.setConfigItems(new ArrayList<String>(textures.values()));
		textureImporter.asyncImportData();
		if (!isAssetLoading) {
			isAssetLoading = true;
		}
	}
	
	public void initAnimsImporter(HashMap<Integer, SpriteInfo> sprites) {
		animsImporter = new AnimsImporter(assetManager);
		animsImporter.setConfigItems(new ArrayList<SpriteInfo>(sprites.values()));
		animsImporter.asyncImportData();
		if (!isAssetLoading) {
			isAssetLoading = true;
		}
	}
	
	public void initFontImporter(HashMap<Integer, String> fonts) {
		fontImporter = new FontImporter(assetManager);
		fontImporter.setConfigItems(new ArrayList<String>(fonts.values()));
		fontImporter.asyncImportData();
		if (!isAssetLoading) {
			isAssetLoading = true;
		}
	}
	
	public Music importMusic(String music) {
		final MusicImporter importer = new MusicImporter();
		return importer.importData(music);
	}

	public Map<String, Sound> importSounds(ArrayList<String> sounds) {
		final SoundImporter importer = new SoundImporter();
		importer.setConfigItems(sounds);
		return importer.importData();
	}
	
	public Map<String, Texture> importTextures() {
		return textureImporter.importData();
	}
	
	public Map<String, List<Animation>> importAnims() {
		return animsImporter.importData();
	}
	
	public Map<String, BitmapFont> importFonts() {
		return fontImporter.importData();
	}
	
	// Properties ----------------------------------------
	public AssetManager getAssetManager() {
		return assetManager;
	}
	
	public boolean isTexturesImported() {
		return (textureImporter != null);
	}
	
	public boolean isAnimsImported() {
		return (animsImporter != null);
	}
	
	public boolean isFontsImported() {
		return (fontImporter != null);
	}

}
