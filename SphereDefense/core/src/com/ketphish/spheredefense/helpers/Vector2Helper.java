package com.ketphish.spheredefense.helpers;

import com.badlogic.gdx.math.Vector2;

public final class Vector2Helper {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	
	// Constructors --------------------------------------
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	public static Vector2 add(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x + v2.x, v1.y + v2.y);
	}
	
	public static Vector2 sub(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x - v2.x, v1.y - v2.y);
	}
	
	public static float dst(Vector2 v1, Vector2 v2) {
		return Vector2.dst(v1.x, v1.y, v2.x, v2.y);
	}
	
	public static boolean compare(Vector2 v1, Vector2 v2) {
		return (v1.x == v2.x && v1.y == v2.y);
	}
	
	public static Vector2 scl(Vector2 v1, float scalar) {
		return new Vector2(v1.x * scalar, v1.y * scalar);
	}
	
	// Properties ----------------------------------------
}
