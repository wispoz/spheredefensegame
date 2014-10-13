package com.ketphish.spheredefense.views.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.managers.Drawer;

public final class LoadingScreen
extends ScreenAdapter {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final String BG_MAIN = "textures/screens/loading/bg_main.png";
	private final String LOADING_SPRITE_PATH = "textures/screens/loading/loading_sprite.png";
	private final float X = (Preset.getInstance().MIN_AREA_WIDTH - Preset.getInstance().MAX_AREA_WIDTH) / 2.0f;
	private final float Y = (Preset.getInstance().MIN_AREA_HEIGHT - Preset.getInstance().MAX_AREA_HEIGHT) / 2.0f;
	private final float WIDTH = Preset.getInstance().MAX_AREA_WIDTH;
	private final float HEIGHT = Preset.getInstance().MAX_AREA_HEIGHT;
	private final int FRAMESX = 4;
	private final int FRAMESY = 2;
	private final Drawer drawer;
	private Animation animation;
	private TextureRegion frame;
	private Texture background;
	private Vector2 position;
	private int size;
	private float time;
	
	// Constructors --------------------------------------
	public LoadingScreen(Drawer drawer) {
		this.drawer = drawer;
		time = 0;
	}
	
	// Extends -------------------------------------------
	@Override
	public void render(float delta) {
		time += delta;
		frame = animation.getKeyFrame(time, true);
		drawer.draw(background, X, Y, WIDTH, HEIGHT);
		drawer.draw(frame, position.x, position.y, size, size);
	}

	@Override
	public void dispose() {
		super.dispose();
		if (animation != null) {
			for (TextureRegion region : animation.getKeyFrames()) {
				region.getTexture().dispose();
			}
		}
		if (frame != null) {
			frame.getTexture().dispose();
		}
		if (background != null) {
			background.dispose();
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void init() {
		final Texture texture = new Texture(Gdx.files.internal(LOADING_SPRITE_PATH));
		background = new Texture(Gdx.files.internal(BG_MAIN));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		size = texture.getWidth() / FRAMESX;
		final TextureRegion[][] tmp = TextureRegion.split(texture, size, size);
		final TextureRegion[] textureFrames = new TextureRegion[FRAMESX * FRAMESY];
        int index = 0;
        for (int i = 0; i < FRAMESY; i++) {
            for (int j = 0; j < FRAMESX; j++) {
            	textureFrames[index++] = tmp[i][j];
            }
        }
        animation = new Animation(.2f, textureFrames);
		final float x = (Preset.getInstance().MIN_AREA_WIDTH - size) / 2;
		final float y = (Preset.getInstance().MIN_AREA_HEIGHT - size) / 2;
		position = new Vector2(x, y);
	}

	public void reset() {
		time = 0;
	}
	
	// Properties ----------------------------------------
}
