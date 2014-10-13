package com.ketphish.spheredefense.importers.assets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.ketphish.spheredefense.importers.AsyncAssetImporter;

public final class TextureImporter 
extends AsyncAssetImporter<Map<String, Texture>, List<String>> {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------
	public TextureImporter(AssetManager assetManager) {
		super(assetManager);
	}
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------
	@Override
	public void asyncImportData() {
		if (configItems != null && !configItems.isEmpty()) {
			for (String path : configItems) {
				assetManager.load(path, Texture.class);
			}
		}
	}
	
	@Override
	public Map<String, Texture> importData() {
		final Map<String, Texture> textures = new HashMap<String, Texture>();
		for (String path : configItems) {
			final Texture texture = assetManager.get(path);
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			final int index = path.lastIndexOf("/");
			final String name = path.substring(index + 1, path.length() - 4);
			textures.put(name, texture);
		}
		configItems.clear();
		return textures;
	}
	
	@Override
	public Map<String, Texture> importData(String path) {
		return null;
	}
	
	@Override
	public Map<String, Texture> importData(String... paths) {
		return null;
	}
	
	// Methods -------------------------------------------
	
	// Properties ----------------------------------------
}
