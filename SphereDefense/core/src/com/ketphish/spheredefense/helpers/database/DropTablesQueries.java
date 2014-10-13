package com.ketphish.spheredefense.helpers.database;

import java.util.ArrayList;
import java.util.List;

public final class DropTablesQueries {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final DatabaseTablesNames tables;
	private final List<String> queries;
	
	// Constructors --------------------------------------
	public DropTablesQueries(DatabaseTablesNames dbTables) {
		this.tables = dbTables;
		queries = new ArrayList<String>();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public List<String> getQueries() {
		queries.add("DROP TABLE IF EXISTS " + tables.ADVANCE_TABLE);
		queries.add("DROP TABLE IF EXISTS " + tables.OPTIONS_TABLE);
		queries.add("DROP TABLE IF EXISTS " + tables.AUDIO_TABLE);
		queries.add("DROP TABLE IF EXISTS " + tables.LANGUAGE_TABLE);
		queries.add("DROP TABLE IF EXISTS " + tables.TROPHY_TABLE);
		queries.add("DROP TABLE IF EXISTS " + tables.UPGRADE_TABLE);
		queries.add("DROP TABLE IF EXISTS " + tables.UNIT_TABLE);
		queries.add("DROP TABLE IF EXISTS " + tables.POWER_TABLE);
		return queries;
	}
	
	// Properties ----------------------------------------
}
