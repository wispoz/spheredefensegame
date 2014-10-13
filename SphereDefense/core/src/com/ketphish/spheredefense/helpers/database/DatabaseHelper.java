package com.ketphish.spheredefense.helpers.database;

import java.util.List;

import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

public final class DatabaseHelper {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final String DATABASE_NAME = "profile.db";
	private final int DATABASE_VERSION = 1;
	private final String TABLES_COUNT_QUERY = "SELECT COUNT(*) FROM sqlite_master WHERE type='table'";
	private final int TABLES_COUNT = 8;
	private Database database;
	private DatabaseTablesNames tablesNames;
	private DatabaseCursor cursor;
	
	// Constructors --------------------------------------
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void activateDatabase() {
		database = DatabaseFactory.getNewDatabase(DATABASE_NAME, DATABASE_VERSION, null, null);
		database.setupDatabase();
    	try {
    		openDatabase();
    		tablesNames = new DatabaseTablesNames();
    		cursor = database.rawQuery(TABLES_COUNT_QUERY);
			if (cursor.next() && cursor.getInt(0) < TABLES_COUNT) {
				CreateTablesQueries clearingTablesQueries = new CreateTablesQueries(tablesNames);
				List<String> queries = clearingTablesQueries.getQueries();
				for (String query : queries) {
					database.execSQL(query);
				}
			}
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeDatabase();
		}
	}

	public void openDatabase() {
		try {
			database.openOrCreateDatabase();
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDatabase() {
		try {
			database.closeDatabase();
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		}
	}

	public void createNewDataByDefault() {
    	try {
    		openDatabase();
				DropTablesQueries ctq = new DropTablesQueries(tablesNames);
				List<String> queries = ctq.getQueries();
				for (String query : queries) {
					database.execSQL(query);
				}
				CreateTablesQueries createTablesQueries = new CreateTablesQueries(tablesNames);
				queries = createTablesQueries.getQueries();
				for (String query : queries) {
					database.execSQL(query);
				}
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		} finally {
			closeDatabase();
		}
	}

	// Properties ----------------------------------------
	public Database getDatabase() {
		return database;
	}
	
	public DatabaseCursor getCursor() {
		return cursor;
	}
	
	public void setCursor(DatabaseCursor cursor) {
		this.cursor = cursor;
	}
	
	public DatabaseTablesNames getTablesNames() {
		return tablesNames;
	}
	
}
