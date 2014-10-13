package com.ketphish.spheredefense.repositories;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.sql.SQLiteGdxException;
import com.ketphish.spheredefense.helpers.database.DatabaseHelper;
import com.ketphish.spheredefense.models.profile.Trophy;

public final class TrophySqlRepository
extends SqlRepository<List<Trophy>> {

	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------
	public TrophySqlRepository(DatabaseHelper dbHelper) {
		super(dbHelper);
	}

	// Extends -------------------------------------------
	@Override
	public List<Trophy> select() {
		List<Trophy> bonuses = new ArrayList<Trophy>();
		final String query = 
				"SELECT * " +
				"FROM " + dbHelper.getTablesNames().TROPHY_TABLE;
		try {
			dbHelper.openDatabase();
			dbHelper.setCursor(dbHelper.getDatabase().rawQuery(query));
			
			while(dbHelper.getCursor().next()) {
				int id = dbHelper.getCursor().getInt(0);
				int level = dbHelper.getCursor().getInt(1);
				int starsQuantity = dbHelper.getCursor().getInt(2);
				
				bonuses.add(new Trophy(id, level, starsQuantity));
			}
			
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		} finally {
			dbHelper.getCursor().close();
			dbHelper.closeDatabase();
		}
		
		return bonuses;
	}

	@Override
	public void update(List<Trophy> data) {
		StringBuilder mainQuery = new StringBuilder();
		StringBuilder idQuery = new StringBuilder();
		mainQuery.append("UPDATE " + dbHelper.getTablesNames().TROPHY_TABLE + " ");
		mainQuery.append("SET ");
		mainQuery.append("StarsAmount = CASE ");
		idQuery.append("WHERE Id IN(");
		
		for (Trophy trophy : data) {
			mainQuery.append("WHEN Id = " + trophy.getId() + " THEN " + trophy.getStarsAmount() + " ");
			idQuery.append(trophy.getId() + ", ");
		}
		
		mainQuery.append("END ");
		idQuery.delete(idQuery.length() - 2, idQuery.length());
		idQuery.append(")");
		mainQuery.append(idQuery);
		
		try {
			dbHelper.openDatabase();
			dbHelper.getDatabase().execSQL(mainQuery.toString());
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		} finally {
			dbHelper.closeDatabase();
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------

	// Properties ----------------------------------------
}
