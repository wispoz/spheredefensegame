package com.ketphish.spheredefense.repositories;

import com.ketphish.spheredefense.helpers.database.DatabaseHelper;
import com.ketphish.spheredefense.interfaces.Repository;

public abstract class SqlRepository<T>
implements Repository<T> {

	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	protected final DatabaseHelper dbHelper;
	
	// Constructors --------------------------------------
	public SqlRepository(DatabaseHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	
	// Methods -------------------------------------------

	// Properties ----------------------------------------
}
