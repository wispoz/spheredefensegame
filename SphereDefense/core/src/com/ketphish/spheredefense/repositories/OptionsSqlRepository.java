package com.ketphish.spheredefense.repositories;

import com.badlogic.gdx.sql.SQLiteGdxException;
import com.ketphish.spheredefense.helpers.database.DatabaseHelper;
import com.ketphish.spheredefense.models.profile.Options;
import com.ketphish.spheredefense.models.profile.Options.Language;

public final class OptionsSqlRepository
extends SqlRepository<Options> {

	// Inner ---------------------------------------------

	// Fields --------------------------------------------

	// Constructors --------------------------------------
	public OptionsSqlRepository(DatabaseHelper dbHelper) {
		super(dbHelper);
	}
	
	// Extends -------------------------------------------
	@Override
	public Options select() {
		Options options = null;
		final String query = 
				"SELECT o.Id, a1.State, a2.State, l.Name " +
				"FROM " + dbHelper.getTablesNames().OPTIONS_TABLE + " o " + 
				"JOIN " + dbHelper.getTablesNames().AUDIO_TABLE + " a1 ON a1.Id = o.MusicStateId " +
				"JOIN " + dbHelper.getTablesNames().AUDIO_TABLE + " a2 ON a2.Id = o.SoundStateId " +
				"JOIN " + dbHelper.getTablesNames().LANGUAGE_TABLE + " l ON l.Id = o.LanguageId";
		try {
			dbHelper.openDatabase();
			dbHelper.setCursor(dbHelper.getDatabase().rawQuery(query));
			if (dbHelper.getCursor().next()) {
				final int id = dbHelper.getCursor().getInt(0);
				final boolean isMusic = getIsAudio(dbHelper.getCursor().getString(1));
				final boolean isSound = getIsAudio(dbHelper.getCursor().getString(2));
				final Language language = getLanguage(dbHelper.getCursor().getString(3));
				options = new Options(id);
				options.setIsMusic(isMusic);
				options.setIsSound(isSound);
				options.setLanguage(language);
			}
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		} finally {
			dbHelper.getCursor().close();
			dbHelper.closeDatabase();
		}
		return options;
	}

	@Override
	public void update(Options data) {
		final String musicState = (data.isMusic()) ? "on" : "off";
		final String soundState = (data.isSound()) ? "on" : "off";
		final String language = data.getLanguage().getName();
		final String query = 
				"UPDATE " + dbHelper.getTablesNames().OPTIONS_TABLE + " " +
				"SET " +
				"MusicStateId = (SELECT Id FROM " + dbHelper.getTablesNames().AUDIO_TABLE + " WHERE State = '" + musicState + "'), " + 
				"SoundStateId = (SELECT Id FROM " + dbHelper.getTablesNames().AUDIO_TABLE + " WHERE State = '" + soundState + "'), " + 
				"LanguageId = (SELECT Id FROM " + dbHelper.getTablesNames().LANGUAGE_TABLE + " WHERE Name = '" + language + "') " + 
				"WHERE Id = " + data.getId();
		try {
			dbHelper.openDatabase();
			dbHelper.getDatabase().execSQL(query);
		} catch (SQLiteGdxException e) {
			e.printStackTrace();
		} finally {
			dbHelper.getCursor().close();
			dbHelper.closeDatabase();
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	private boolean getIsAudio(String audioState) {
		if (audioState.equals("on")) {
			return true;
		} 
		return false;
	}

	private Language getLanguage(String languageName) {
		if (languageName.equals("english")) {
			return Options.Language.ENGLISH;
		}
		return Options.Language.RUSSIAN;
	}
	
	// Properties ----------------------------------------
}
