package com.ketphish.spheredefense.interfaces;

public interface Repository<T> {
	T select();
	void update(T data);
}
