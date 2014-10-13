package com.ketphish.spheredefense.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

public final class Drawer
extends SpriteBatch
implements Disposable {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private Map<String, Texture> textures;
	private Map<String, List<Animation>> anims;
	private Map<String, BitmapFont> fonts;
	
	// Constructors --------------------------------------
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	public void draw(String textureName, Rectangle rectangle) {
		draw(getTexture(textureName),	
				rectangle.x, rectangle.y, 
				rectangle.width, rectangle.height);
	}
	
	public void draw(String textureName, Rectangle rectangle,
			Vector2 texturePosition) {
		draw(getTexture(textureName), 
				rectangle.x, rectangle.y, 
				rectangle.width, rectangle.height, 
				(int) texturePosition.x, (int) texturePosition.y, 
				(int) rectangle.width, (int) rectangle.height, 
				false, false);
	}
	
	public void draw(String spriteName, Rectangle rectangle,
			int spriteIndex, float time) {
		final TextureRegion frame = anims.get(spriteName).get(spriteIndex).getKeyFrame(time, true);
		draw(frame, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}
	
	public void drawString(String font, String text, 
			float x, float y, float scale, Color color) {
		BitmapFont f = getFont(font);
		f.setColor(color);
		f.setScale(scale);
		f.draw(this, text, x, y);
		if (scale != 1.0f) {
			f.setScale(1.0f);
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		disposeTextures();
		disposeFonts();
	}
	
	public void disposeTextures() {
		if (textures != null && !textures.isEmpty()) {
			for (Texture texture : textures.values()) {
				texture.dispose();
			}
			textures.clear();
		}
	}
	
	public void disposeAnims() {
		if (anims != null && !anims.isEmpty()) {
			for (List<Animation> animList : anims.values()) {
				animList.clear();
			}
			anims.clear();
		}
	}
	
	public void disposeFonts() {
		if (fonts != null && !fonts.isEmpty()) {
			for (BitmapFont font : fonts.values()) {
				font.dispose();
			}
			fonts.clear();
		}
	}
	
	// Properties ----------------------------------------
	public Texture getTexture(String textureName) {
		return textures.get(textureName);
	}
	
	public void setTextures(Map<String, Texture> textures) {
		this.textures = new HashMap<String, Texture>(textures);
	}
	
	public void setAnims(Map<String, List<Animation>> anims) {
		this.anims = new HashMap<String, List<Animation>>(anims);
	}
	
	private BitmapFont getFont(String name) {
		return fonts.get(name);
	}
	
	public void setFonts(Map<String, BitmapFont> fonts) {
		this.fonts = new HashMap<String, BitmapFont>(fonts);
	}
	
}
