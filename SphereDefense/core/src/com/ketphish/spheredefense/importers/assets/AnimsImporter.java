package com.ketphish.spheredefense.importers.assets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ketphish.spheredefense.helpers.SpriteInfo;
import com.ketphish.spheredefense.importers.AsyncAssetImporter;

public final class AnimsImporter 
extends AsyncAssetImporter<Map<String, List<Animation>>, List<SpriteInfo>> {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------
	public AnimsImporter(AssetManager assetManager) {
		super(assetManager);
	}
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------
	@Override
	public void asyncImportData() {
		if (configItems != null && !configItems.isEmpty()) {
			for (SpriteInfo info : configItems) {
				assetManager.load(info.getFileName(), Texture.class);
			}
		}
	}
	
	@Override
	public Map<String, List<Animation>> importData() {
		final Map<String, List<Animation>> anims = new HashMap<String, List<Animation>>();
		for (SpriteInfo info : configItems) {
			final String path = info.getFileName();
			final Texture texture = assetManager.get(path);
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			final int index = path.lastIndexOf("/");
			final String name = path.substring(index + 1, path.length() - 4);
			anims.put(name, new ArrayList<Animation>());
			final int tileWidth = texture.getWidth() / info.getCols();
			final int tileHeight = texture.getHeight() / info.getRows();
			final TextureRegion[][] tmp = TextureRegion.split(texture, tileWidth, tileHeight);
			if (!info.isGroups()) {
				final TextureRegion[] textureFrames = new TextureRegion[info.getRows() * info.getCols()];
		        int textureIndex = 0;
		        for (int i = 0; i < info.getRows(); i++) {
		            for (int j = 0; j < info.getCols(); j++) {
		            	textureFrames[textureIndex++] = tmp[i][j];
		            }
		        }
		        final Animation animation = new Animation(info.getSpeed(), textureFrames);
		        anims.get(name).add(animation);
			} else {
		        for (int i = 0; i < info.getRows(); i++) {
		        	final TextureRegion[] textureFrames = new TextureRegion[info.getCols()];
		        	int textureIndex = 0;
		            for (int j = 0; j < info.getCols(); j++) {
		            	textureFrames[textureIndex++] = tmp[i][j];
		            }
		            final Animation animation = new Animation(info.getSpeed(), textureFrames);
		            anims.get(name).add(animation);
		        }
			}
		}
		configItems.clear();
		return anims;
	}
	
	@Override
	public Map<String, List<Animation>> importData(String path) {
		return null;
	}
	
	@Override
	public Map<String, List<Animation>> importData(String... paths) {
		return null;
	}
	
	// Methods -------------------------------------------
	
	// Properties ----------------------------------------
}
