package com.ketphish.spheredefense.importers.assets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.ketphish.spheredefense.importers.AssetImporter;

public final class SoundImporter 
extends AssetImporter<Map<String, Sound>, List<String>> {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public Map<String, Sound> importData() {
		final Map<String, Sound> sounds = new HashMap<String, Sound>();
		for (String path : configItems) {
			final Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
			final int index = path.lastIndexOf("/");
			final String name = path.substring(index + 1, path.length() - 4);
			sounds.put(name, sound);
		}
		return sounds;
	}
	
	@Override
	public Map<String, Sound> importData(String path) {
		return null;
	}
	
	@Override
	public Map<String, Sound> importData(String ... paths) {
		return null;
	}

	// Methods -------------------------------------------

	// Properties ----------------------------------------
}
