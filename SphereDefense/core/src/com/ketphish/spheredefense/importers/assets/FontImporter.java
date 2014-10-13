package com.ketphish.spheredefense.importers.assets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.ketphish.spheredefense.importers.AsyncAssetImporter;

public final class FontImporter 
extends AsyncAssetImporter<Map<String, BitmapFont>, List<String>> {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------
	public FontImporter(AssetManager assetManager) {
		super(assetManager);
	}
	
	// Extends -------------------------------------------
		
	// Implementations -----------------------------------
	@Override
	public void asyncImportData() {
		if (configItems != null && !configItems.isEmpty()) {
			for (String path : configItems) {
				assetManager.load(path, BitmapFont.class);
			}
		}
	}
	
	@Override
	public Map<String, BitmapFont> importData() {
		final Map<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
		for (String path : configItems) {
			final BitmapFont font = assetManager.get(path);
			font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			final int index = path.lastIndexOf("/");
			final String name = path.substring(index + 1, path.length() - 4);
			fonts.put(name, font);
		}
		configItems.clear();
		return fonts;
	}
	
	@Override
	public Map<String, BitmapFont> importData(String path) {
		return null;
	}
	
	@Override
	public Map<String, BitmapFont> importData(String ... paths) {
		return null;
	}

	// Methods -------------------------------------------

	// Properties ----------------------------------------
}
