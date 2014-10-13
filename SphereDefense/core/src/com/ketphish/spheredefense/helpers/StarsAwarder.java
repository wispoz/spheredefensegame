package com.ketphish.spheredefense.helpers;

public final class StarsAwarder {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------

	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public int award(int residueEnergy, int startingEnergy) {
		if (startingEnergy != 0) {
			final float oneHalf = (float) startingEnergy * 0.5f;
			final float threeFourth = (float) startingEnergy * 0.75f;
			if (residueEnergy >= threeFourth) {
				return 3;
			} else if (residueEnergy >= oneHalf) {
				return 2;
			}
			return 1;
		}
		return 0;
	}
	
	// Properties ----------------------------------------
}
