package com.ketphish.spheredefense.interfaces.importers;

public interface AsyncDataImporter<T>
extends DataImporter<T> {
	void asyncImportData();
}
