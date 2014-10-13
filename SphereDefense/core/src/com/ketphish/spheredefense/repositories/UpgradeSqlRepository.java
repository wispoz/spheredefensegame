package com.ketphish.spheredefense.repositories;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.sql.SQLiteGdxException;
import com.ketphish.spheredefense.helpers.database.DatabaseHelper;
import com.ketphish.spheredefense.models.profile.Unit;
import com.ketphish.spheredefense.models.profile.Power;
import com.ketphish.spheredefense.models.profile.Upgrade;

public final class UpgradeSqlRepository
extends SqlRepository<List<Upgrade>> {

	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------
	public UpgradeSqlRepository(DatabaseHelper dbHelper) {
		super(dbHelper);
	}

	// Extends -------------------------------------------
	@Override
	public List<Upgrade> select() {
		List<Upgrade> upgrades = new ArrayList<Upgrade>();
		final String query = 
				"SELECT upg.Id, upg.UnitId, upg.PowerId, u.Name, p.Name, p.Cost, upg.IsUpgraded " + 
				"FROM " + 
				dbHelper.getTablesNames().UPGRADE_TABLE + " AS upg, " + 
				dbHelper.getTablesNames().UNIT_TABLE + " AS u, " + 
				dbHelper.getTablesNames().POWER_TABLE + " AS p " + 
				"WHERE " + 
				"upg.UnitId = u.Id AND " + 
				"upg.PowerId = p.Id";
		try {
			dbHelper.openDatabase();
			dbHelper.setCursor(dbHelper.getDatabase().rawQuery(query));
			while (dbHelper.getCursor().next()) {
				final int upgradeId = dbHelper.getCursor().getInt(0);
				final int unitId = dbHelper.getCursor().getInt(1);
				final int powerId = dbHelper.getCursor().getInt(2);
				final String unitName = dbHelper.getCursor().getString(3);
				final String powerName = dbHelper.getCursor().getString(4);
				final int powerCost = dbHelper.getCursor().getInt(5);
				final boolean isUpgraded = (dbHelper.getCursor().getInt(6) == 1) ? true : false;
				final Unit unit = new Unit(unitId, unitName);
				final Power power = new Power(powerId, powerName, powerCost);
				final Upgrade upgrade = new Upgrade(upgradeId, unit, power, isUpgraded);
				upgrades.add(upgrade);
			}
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		} finally {
			dbHelper.getCursor().close();
			dbHelper.closeDatabase();
		}
		return upgrades;
	}

	@Override
	public void update(List<Upgrade> data) {
		StringBuilder mainQuery = new StringBuilder();
		StringBuilder idQuery = new StringBuilder();
		mainQuery.append("UPDATE " + dbHelper.getTablesNames().UPGRADE_TABLE + " ");
		mainQuery.append("SET ");
		mainQuery.append("IsUpgraded = CASE ");
		idQuery.append("WHERE Id IN(");
		for (Upgrade upgrade : data) {
			int isUpgraded = (!upgrade.isUpgraded()) ? 0 : 1;
			mainQuery.append("WHEN Id = " + upgrade.getId() + " THEN " + isUpgraded + " ");
			idQuery.append(upgrade.getId() + ", ");
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
