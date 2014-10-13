package com.ketphish.spheredefense.importers;

import com.badlogic.gdx.assets.AssetManager;
import com.ketphish.spheredefense.interfaces.importers.AsyncDataImporter;

public abstract class AsyncAssetImporter<T, K>
implements AsyncDataImporter<T> {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	protected final AssetManager assetManager;
	protected K configItems; 

	// Constructors --------------------------------------
	public AsyncAssetImporter(AssetManager assetManager) {
		this.assetManager = assetManager;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	
	// Properties ----------------------------------------
	public void setConfigItems(K configItems) {
		this.configItems = configItems;
	}
	
}
