package com.ketphish.spheredefense.repositories;

import com.badlogic.gdx.sql.SQLiteGdxException;
import com.ketphish.spheredefense.helpers.database.DatabaseHelper;
import com.ketphish.spheredefense.models.profile.Advance;

public final class AdvanceSqlRepository
extends SqlRepository<Advance> {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------

	// Constructors --------------------------------------
	public AdvanceSqlRepository(DatabaseHelper dbHelper) {
		super(dbHelper);
	}

	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public Advance select() {
		Advance advance = null;
		final String query =  
				"SELECT * " + 
				"FROM " + dbHelper.getTablesNames().ADVANCE_TABLE;
		try {
			dbHelper.openDatabase();
			dbHelper.setCursor(dbHelper.getDatabase().rawQuery(query));
			if (dbHelper.getCursor().next()) {
				int id = dbHelper.getCursor().getInt(0);
				int lastLevel = dbHelper.getCursor().getInt(1);
				int starsBalance = dbHelper.getCursor().getInt(2);
				
				advance = new Advance(id);
				advance.setCurrentLevel(lastLevel);
				advance.setStarsBalance(starsBalance);
			}
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		} finally {
			dbHelper.getCursor().close();
			dbHelper.closeDatabase();
		}
		return advance;
	}

	@Override
	public void update(Advance data) { 
		final String query = 
				"UPDATE " + dbHelper.getTablesNames().ADVANCE_TABLE + " " +
				"SET " + 
				"CurrentLevel = " + data.getCurrentLevel() + ", " +
				"StarsBalance = " + data.getStarsBalance() + " " +
				"WHERE Id = " + data.getId();
		try {
			dbHelper.openDatabase();
			dbHelper.getDatabase().execSQL(query);
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		} finally {
			dbHelper.closeDatabase();
		}
	}
	
	// Methods -------------------------------------------

	// Properties ----------------------------------------
}
