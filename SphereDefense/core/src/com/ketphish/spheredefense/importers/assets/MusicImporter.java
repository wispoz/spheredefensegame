package com.ketphish.spheredefense.importers.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.ketphish.spheredefense.importers.AssetImporter;

public final class MusicImporter 
extends AssetImporter<Music, String> {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	
	// Constructors --------------------------------------
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------
	@Override
	public Music importData() {
		return null;
	}
	
	@Override
	public Music importData(String path) {
		return Gdx.audio.newMusic(Gdx.files.internal(path));
	}
	
	@Override
	public Music importData(String ... paths) {
		return null;
	}
		
	// Methods -------------------------------------------

	// Properties ----------------------------------------
}
