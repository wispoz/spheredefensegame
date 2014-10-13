package com.ketphish.spheredefense.models.trail;

import java.util.ArrayList;
import java.util.List;

import com.ketphish.spheredefense.models.Entity;

public final class Trail
extends Entity {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	protected final List<Snippet> snippets;

	// Constructors --------------------------------------
	public Trail(int id) {
		super(id);
		snippets = new ArrayList<Snippet>();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void addSnippet(Snippet snippet) {
		snippets.add(snippet);
	}
	
	// Properties ----------------------------------------
	public List<Snippet> getSnippets() {
		return snippets;
	}
	
}
