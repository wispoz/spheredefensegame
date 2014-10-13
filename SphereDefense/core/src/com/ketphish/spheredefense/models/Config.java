package com.ketphish.spheredefense.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ketphish.spheredefense.helpers.SpriteInfo;
import com.ketphish.spheredefense.models.gameobjects.Auxiliary;
import com.ketphish.spheredefense.models.gameobjects.Bullet;
import com.ketphish.spheredefense.models.gameobjects.Effect;
import com.ketphish.spheredefense.models.gameobjects.Enemy;
import com.ketphish.spheredefense.models.gameobjects.Tower;
import com.ketphish.spheredefense.models.level.Level;
import com.ketphish.spheredefense.models.trail.Trail;
import com.ketphish.spheredefense.views.panels.BulletField;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.EffectField;
import com.ketphish.spheredefense.views.panels.EnemyField;
import com.ketphish.spheredefense.views.panels.GamePlayField;
import com.ketphish.spheredefense.views.panels.Label;
import com.ketphish.spheredefense.views.panels.LevelMapField;
import com.ketphish.spheredefense.views.panels.TextField;
import com.ketphish.spheredefense.views.panels.TowerField;
import com.ketphish.spheredefense.views.panels.menus.TowerMenu;
import com.ketphish.spheredefense.views.panels.progressbars.EnemyHealthBar;
import com.ketphish.spheredefense.views.panels.progressbars.TowerBuildBar;

public final class Config {
	// Inner ---------------------------------------------
	public enum ConfigItem {
		TEXTURES,
		SPRITES,
		FONTS,
		MUSIC,
		SOUNDS,
		LABELS,
		TEXT_FIELDS,
		BUTTONS,
		ENEMY_HEALTH_BAR,
		TOWER_BUILD_BAR,
		GAME_PLAY_FIELD,
		LEVEL_MAP_FIELD,
		TOWERS_MENU,
		TRAILS,
		BULLETS,
		TOWERS,
		AUXILIARIES,
		ENEMIES, 
		EFFECTS,
		LEVEL
	}

	// Fields --------------------------------------------
	private HashMap<Integer, String> textures;
	private HashMap<Integer, SpriteInfo> sprites;
	private HashMap<Integer, String> fonts;
	private String music;
	private ArrayList<String> sounds;
	private LinkedHashMap<String, Label> labels;
	private LinkedHashMap<String, TextField> textFields;
	private LinkedHashMap<String, Button> buttons;
	private EnemyHealthBar enemyHealthBar;
	private TowerBuildBar towerBuildBar;
	private GamePlayField gamePlayField;
	private LevelMapField levelMapField;
	private Map<String, TowerMenu> towersMenu;
	private List<Trail> trails;
	private Map<String, Bullet> bullets;
	private BulletField bulletField;
	private Map<String, Tower> towers;
	private TowerField towerField;
	private Map<String, Auxiliary> auxiliaries;
	private Map<String, Enemy> enemies;
	private EnemyField enemyField;
	private Map<String, Effect> effects;
	private EffectField effectField;
	private Level level;
	
	// Constructors --------------------------------------

	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public void init(ConfigItem item) {
		switch (item) {
		case TEXTURES:
			if (textures == null) {
				textures = new HashMap<Integer, String>();
			}
			break;
		case SPRITES:
			if (sprites == null) {
				sprites = new HashMap<Integer, SpriteInfo>();
			}
			break;
		case FONTS:
			if (fonts == null) {
				fonts = new HashMap<Integer, String>();
			}
			break;
		case SOUNDS:
			if (sounds == null) {
				sounds = new ArrayList<String>();
			}
			break;
		case LABELS:
			if (labels == null) {
				labels = new LinkedHashMap<String, Label>();
			}
			break;
		case TEXT_FIELDS:
			if (textFields == null) {
				textFields = new LinkedHashMap<String, TextField>();
			}
			break;
		case BUTTONS:
			if (buttons == null) {
				buttons = new LinkedHashMap<String, Button>();
			}
			break;
		case ENEMY_HEALTH_BAR:
			enemyHealthBar = new EnemyHealthBar();
			break;
		case TOWER_BUILD_BAR:
			towerBuildBar = new TowerBuildBar();
			break;
		case GAME_PLAY_FIELD:
			break;
		case LEVEL_MAP_FIELD:
			if (levelMapField == null) {
				levelMapField = new LevelMapField();
			}
			break;
		case TOWERS_MENU:
			if (towersMenu == null) {
				towersMenu = new HashMap<String, TowerMenu>();
			}
			break;
		case TRAILS:
			if (trails == null) {
				trails = new ArrayList<Trail>();
			}
			break;
		case BULLETS:
			if (bullets == null) {
				bullets = new HashMap<String, Bullet>();
			}
			if (bulletField == null) {
				bulletField = new BulletField();
			}
			break;
		case TOWERS:
			if (towers == null) {
				towers = new HashMap<String, Tower>();
			}
			if (towerField == null) {
				towerField = new TowerField();
			}
			break;
		case AUXILIARIES:
			if (auxiliaries == null) {
				auxiliaries = new HashMap<String, Auxiliary>();
			}
			break;
		case ENEMIES:
			if (enemies == null) {
				enemies = new HashMap<String, Enemy>();
			}
			if (enemyField == null) {
				enemyField = new EnemyField();
			}
			break;
		case EFFECTS:
			if (effects == null) {
				effects = new HashMap<String, Effect>();
			}
			if (effectField == null) {
				effectField = new EffectField();
			}
			break;
		case LEVEL:
			break;
		default:
			break;
		}
	}
	
	// Properties ----------------------------------------
	public HashMap<Integer, String> getTextures() {
		return textures;
	}

	public HashMap<Integer, SpriteInfo> getSprites() {
		return sprites;
	}
	
	public HashMap<Integer, String> getFonts() {
		return fonts;
	}

	public String getMusic() {
		return music;
	}
	
	public void setMusic(String music) {
		this.music = music;
	}

	public ArrayList<String> getSounds() {
		return sounds;
	}

	public LinkedHashMap<String, Label> getLabels() {
		return labels;
	}

	public LinkedHashMap<String, TextField> getTextFields() {
		return textFields;
	}

	public LinkedHashMap<String, Button> getButtons() {
		return buttons;
	}
	
	public EnemyHealthBar getEnemyHealthBar() {
		return enemyHealthBar;
	}
	
	public TowerBuildBar getTowerBuildBar() {
		return towerBuildBar;
	}
	
	public GamePlayField getGamaPlayField() {
		return gamePlayField;
	} 
	
	public void setGamePlayField(GamePlayField gamePlayField) {
		this.gamePlayField = gamePlayField;
	}
	
	public LevelMapField getLevelMapField() {
		return levelMapField;
	}
	
	public Map<String, TowerMenu> getTowersMenu() {
		return towersMenu;
	}
	
	public List<Trail> getTrails() {
		return trails;
	}
	
	public Map<String, Bullet> getBullets() {
		return bullets;
	}
	
	public BulletField getBulletField() {
		return bulletField;
	}
	
	public Map<String, Tower> getTowers() {
		return towers;
	}

	public TowerField getTowerField() {
		return towerField;
	}
	
	public Map<String, Auxiliary> getAuxiliaries() {
		return auxiliaries;
	}
	
	public Map<String, Enemy> getEnemies() {
		return enemies;
	}

	public EnemyField getEnemyField() {
		return enemyField;
	}
	
	public Map<String, Effect> getEffects() {
		return effects;
	}

	public EffectField getEffectField() {
		return effectField;
	}
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
}
