package com.ketphish.spheredefense.helpers.database;

import java.util.ArrayList;
import java.util.List;

public final class CreateTablesQueries {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final DatabaseTablesNames tables;
	private final List<String> queries;
	
	// Constructors --------------------------------------
	public CreateTablesQueries(DatabaseTablesNames tables) {
		this.tables = tables;
		queries = new ArrayList<String>();
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public List<String> getQueries() {
		addCreateTablesQueries();
		addInsertTablesQueries();
		return queries;
	}

	private void addCreateTablesQueries() {
		queries.add(
				"CREATE TABLE IF NOT EXISTS " + tables.ADVANCE_TABLE + 
				"(Id INTEGER PRIMARY KEY," + 
				"CurrentLevel INTEGER NOT NULL CHECK(CurrentLevel > 0 AND CurrentLevel <= 12) DEFAULT 1," +
				"StarsBalance INTEGER NOT NULL CHECK(StarsBalance >= 0 AND StarsBalance <= 36) DEFAULT 0)");
		queries.add(
				"CREATE TABLE IF NOT EXISTS " + tables.AUDIO_TABLE + 
				"(Id INTEGER PRIMARY KEY," + 
				"State TEXT NOT NULL UNIQUE)");
		queries.add(
				"CREATE TABLE IF NOT EXISTS " + tables.LANGUAGE_TABLE + 
				"(Id INTEGER PRIMARY KEY," + 
				"Name TEXT NOT NULL UNIQUE)");
		queries.add(
				"CREATE TABLE IF NOT EXISTS " + tables.OPTIONS_TABLE + 
				"(Id INTEGER PRIMARY KEY," + 
				"MusicStateId INTEGER NOT NULL DEFAULT 1," +
				"SoundStateId INTEGER NOT NULL DEFAULT 1," +
				"LanguageId INTEGER NOT NULL DEFAULT 1," +
				"FOREIGN KEY(MusicStateId) REFERENCES " + tables.AUDIO_TABLE + "(Id)," +
				"FOREIGN KEY(SoundStateId) REFERENCES " + tables.AUDIO_TABLE + "(Id)," +
				"FOREIGN KEY(LanguageId) REFERENCES " + tables.LANGUAGE_TABLE + "(Id))");
		queries.add(
				"CREATE TABLE IF NOT EXISTS " + tables.TROPHY_TABLE + 
				"(Id INTEGER PRIMARY KEY," + 
				"Level INTEGER NOT NULL UNIQUE CHECK(Level > 0 AND Level <= 12)," +
				"StarsAmount INTEGER NOT NULL CHECK(StarsAmount >= 0 AND StarsAmount <= 3) DEFAULT 0)");
		queries.add(
				"CREATE TABLE IF NOT EXISTS " + tables.UNIT_TABLE + 
				"(Id INTEGER PRIMARY KEY," + 
				"Name TEXT NOT NULL UNIQUE)");
		queries.add(
				"CREATE TABLE IF NOT EXISTS " + tables.POWER_TABLE + 
				"(Id INTEGER PRIMARY KEY," + 
				"Name TEXT NOT NULL UNIQUE, " + 
				"Cost INTEGER NOT NULL CHECK(Cost >= 0 AND Cost <= 3), " +
				"UNIQUE(Cost))");
		queries.add("PRAGMA foreign_keys = ON");
		queries.add(
				"CREATE TABLE IF NOT EXISTS " + tables.UPGRADE_TABLE + 
				"(Id INTEGER PRIMARY KEY," +
				"UnitId INTEGER NOT NULL," +
				"PowerId INTEGER NOT NULL," +
				"IsUpgraded INTEGER NOT NULL CHECK(IsUpgraded >= 0 AND IsUpgraded <= 1) DEFAULT 0," +
				"FOREIGN KEY(UnitId) REFERENCES " + tables.UNIT_TABLE + "(Id)," + 
				"FOREIGN KEY(PowerId) REFERENCES " + tables.POWER_TABLE + "(Id)," +
				"UNIQUE(UnitId, PowerId))");
	}
	
	private void addInsertTablesQueries() {
		queries.add("INSERT OR IGNORE INTO " + tables.ADVANCE_TABLE + "('Id') VALUES (NULL)");
		queries.add("INSERT OR IGNORE INTO " + tables.AUDIO_TABLE + "('State') VALUES ('on')");
		queries.add("INSERT OR IGNORE INTO " + tables.AUDIO_TABLE + "('State') VALUES ('off')");
		queries.add("INSERT OR IGNORE INTO " + tables.LANGUAGE_TABLE + "('Name') VALUES ('english')");
		queries.add("INSERT OR IGNORE INTO " + tables.LANGUAGE_TABLE + "('Name') VALUES ('russian')");
		queries.add("INSERT OR IGNORE INTO " + tables.OPTIONS_TABLE + "('Id') VALUES (1)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (1)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (2)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (3)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (4)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (5)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (6)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (7)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (8)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (9)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (10)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (11)");
		queries.add("INSERT OR IGNORE INTO " + tables.TROPHY_TABLE + "('Level') VALUES (12)");
		queries.add("INSERT OR IGNORE INTO " + tables.UNIT_TABLE + "('Name') VALUES ('Cone')");
		queries.add("INSERT OR IGNORE INTO " + tables.UNIT_TABLE + "('Name') VALUES ('Pyramid')");
		queries.add("INSERT OR IGNORE INTO " + tables.UNIT_TABLE + "('Name') VALUES ('Cube')");
		queries.add("INSERT OR IGNORE INTO " + tables.UNIT_TABLE + "('Name') VALUES ('Cylinder')");
		queries.add("INSERT OR IGNORE INTO " + tables.UNIT_TABLE + "('Name') VALUES ('EnergyCircle')");
		queries.add("INSERT OR IGNORE INTO " + tables.UNIT_TABLE + "('Name') VALUES ('FireCircle')");
		queries.add("INSERT OR IGNORE INTO " + tables.POWER_TABLE + "('Name', 'Cost') VALUES ('Low', 1)");
		queries.add("INSERT OR IGNORE INTO " + tables.POWER_TABLE + "('Name', 'Cost') VALUES ('Medium', 2)");
		queries.add("INSERT OR IGNORE INTO " + tables.POWER_TABLE + "('Name', 'Cost') VALUES ('High', 3)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (1, 1)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (1, 2)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (1, 3)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (2, 1)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (2, 2)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (2, 3)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (3, 1)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (3, 2)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (3, 3)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (4, 1)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (4, 2)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (4, 3)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (5, 1)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (5, 2)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (5, 3)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (6, 1)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (6, 2)");
		queries.add("INSERT OR IGNORE INTO " + tables.UPGRADE_TABLE + "('UnitId', 'PowerId') VALUES (6, 3)");
	}
	
	// Properties ----------------------------------------
}
