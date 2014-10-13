package com.ketphish.spheredefense.interfaces.importers;

public interface DataImporter<T> {
	public T importData();
	public T importData(String path);
	public T importData(String ... paths);
}
