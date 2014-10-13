package com.ketphish.spheredefense.importers;

import com.ketphish.spheredefense.interfaces.importers.DataImporter;

public abstract class AssetImporter<T, K>
implements DataImporter<T> {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	protected K configItems; 

	// Constructors --------------------------------------

	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	
	// Properties ----------------------------------------
	public void setConfigItems(K configItems) {
		this.configItems = configItems;
	}
	
}
