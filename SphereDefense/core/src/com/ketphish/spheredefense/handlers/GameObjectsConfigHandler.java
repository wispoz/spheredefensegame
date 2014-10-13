package com.ketphish.spheredefense.handlers;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.badlogic.gdx.math.Vector2;
import com.ketphish.spheredefense.helpers.SpriteInfo;
import com.ketphish.spheredefense.models.Config.ConfigItem;
import com.ketphish.spheredefense.models.gameobjects.Auxiliary;
import com.ketphish.spheredefense.models.gameobjects.Bullet;
import com.ketphish.spheredefense.models.gameobjects.Effect;
import com.ketphish.spheredefense.models.gameobjects.Enemy;
import com.ketphish.spheredefense.models.gameobjects.Tower;
import com.ketphish.spheredefense.models.level.Foe;
import com.ketphish.spheredefense.models.level.Wave;
import com.ketphish.spheredefense.views.panels.AnimLabel;
import com.ketphish.spheredefense.views.panels.Label;

public final class GameObjectsConfigHandler
extends GameScreenConfigHandler {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private Bullet bullet;
	private AnimLabel bulletLabel;
	private Tower tower;
	private Label towerLabel;
	private Auxiliary auxiliary;
	private Enemy enemy;
	private AnimLabel enemyLabel;
	private Effect effect;
	private AnimLabel effectLabel;
	private String spriteRootPath;
	private boolean isSprites;
	private boolean isSprite;
	private boolean isBullets;
	private boolean isBullet;
	private boolean isBulletTexture;
	private boolean isBulletRectangle;
	private boolean isTowers;
	private boolean isTower;
	private boolean isTowerTexture;
	private boolean isTowerRectangle;
	private boolean isTowerOffset;
	private boolean isTowerBullet;
	private boolean isTowerBuild;
	private boolean isTowerAttack;
	private boolean isAuxiliaries;
	private boolean isAuxiliary;
	private boolean isAuxiliaryPosition;
	private boolean isAuxiliaryBullet;
	private boolean isAuxiliaryAttack;
	private boolean isEnemies;
	private boolean isEnemy;
	private boolean isEnemyResistance;
	private boolean isEnemySprite;
	private boolean isEnemyRectangle;
	private boolean isEnemyOffset;
	private boolean isEffects;
	private boolean isEffect;
	private boolean isEffectTexture;
	private boolean isEffectRectangle;
	
	// Constructors --------------------------------------
	public GameObjectsConfigHandler() {
		super();
		isSprites = false;
		isSprite = false;
		isBullets = false;
		isBullet = false;
		isBulletTexture = false;
		isBulletRectangle = false;
		isTowers = false;
		isTower = false;
		isTowerTexture = false;
		isTowerRectangle = false;
		isTowerOffset = false;
		isTowerBullet = false;
		isTowerBuild = false;
		isTowerAttack = false;
		isAuxiliaries = false;
		isAuxiliary = false;
		isAuxiliaryPosition = false;
		isAuxiliaryBullet = false;
		isAuxiliaryAttack = false;
		isEnemies = false;
		isEnemy = false;
		isEnemyResistance = false;
		isEnemySprite = false;
		isEnemyRectangle = false;
		isEnemyOffset = false;
		isEffects = false;
		isEffect = false;
		isEffectTexture = false;
		isEffectRectangle = false;
	}
	
	// Extends -------------------------------------------
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (isConfig && isAssets && !isSprites && qName.equalsIgnoreCase("sprites")) {
			config.init(ConfigItem.SPRITES);
			spriteRootPath = attributes.getValue("root_path");
			isSprites = true;
		} else if (isConfig && isAssets && isSprites && !isSprite && qName.equalsIgnoreCase("sprite")) {
			if (attributes.getValue("enemy_name") != null) {
				final String enemyName = attributes.getValue("enemy_name");
				if (checkIsEnemy(enemyName)) {
					handleSprite(attributes);
					isSprite = true;
				}
			} else {
				handleSprite(attributes);
				isSprite = true;
			}
		} else if (isConfig && !isBullets && qName.equalsIgnoreCase("bullets")) {
			config.init(ConfigItem.BULLETS);
			isBullets = true;
		} else if (isConfig && isBullets && !isBullet && qName.equalsIgnoreCase("bullet")) {
			handleBullet(attributes);
			isBullet = true;
		} else if (isConfig && isBullets && isBullet && !isBulletTexture && qName.equalsIgnoreCase("sprite")) {
			handleBulletTexture(attributes);
			isBulletTexture = true;
		} else if (isConfig && isBullets && isBullet && !isBulletRectangle && qName.equalsIgnoreCase("rectangle")) {
			handleBulletRectangle(attributes);
			isBulletRectangle = true;
		} else if (isConfig && !isTowers && qName.equalsIgnoreCase("towers")) {
			config.init(ConfigItem.TOWERS);
			isTowers = true;
		} else if (isConfig && isTowers && !isTower && qName.equalsIgnoreCase("tower")) {
			handleTower(attributes);
			isTower = true;
		} else if (isConfig && isTowers && isTower && !isTowerTexture && qName.equalsIgnoreCase("texture")) {
			handleTowerTexture(attributes);
			isTowerTexture = true;
		} else if (isConfig && isTowers && isTower && !isTowerRectangle && qName.equalsIgnoreCase("rectangle")) {
			handleTowerRectangle(attributes);
			isTowerRectangle = true;
		} else if (isConfig && isTowers && isTower && !isTowerOffset && qName.equalsIgnoreCase("offset")) {
			handleTowerOffset(attributes);
			isTowerOffset = true;
		} else if (isConfig && isTowers && isTower && !isTowerBullet && qName.equalsIgnoreCase("bullet")) {
			handleTowerBullet(attributes);
			isTowerBullet = true;
		} else if (isConfig && isTowers && isTower && !isTowerBuild && qName.equalsIgnoreCase("build")) {
			handleTowerBuild(attributes);
			isTowerBuild = true;
		} else if (isConfig && isTowers && isTower && !isTowerAttack && qName.equalsIgnoreCase("attack")) {
			handleTowerAttack(attributes);
			isTowerAttack = true;
		} else if (isConfig && !isAuxiliaries && qName.equalsIgnoreCase("auxiliaries")) {
			config.init(ConfigItem.AUXILIARIES);
			isAuxiliaries = true;
		} else if (isConfig && isAuxiliaries && !isAuxiliary && qName.equalsIgnoreCase("auxiliary")) {
			handleAuxiliary(attributes);
			isAuxiliary = true;
		} else if (isConfig && isAuxiliaries && isAuxiliary && !isAuxiliaryPosition && qName.equalsIgnoreCase("position")) {
			handleAuxiliaryPosition(attributes);
			isAuxiliaryPosition = true;
		} else if (isConfig && isAuxiliaries && isAuxiliary && !isAuxiliaryBullet && qName.equalsIgnoreCase("bullet")) {
			handleAuxiliaryBullet(attributes);
			isAuxiliaryBullet = true;
		} else if (isConfig && isAuxiliaries && isAuxiliary && !isAuxiliaryAttack && qName.equalsIgnoreCase("attack")) {
			handleAuxiliaryAttack(attributes);
			isAuxiliaryAttack = true;
		} else if (isConfig && !isEnemies && qName.equalsIgnoreCase("enemies")) {
			config.init(ConfigItem.ENEMIES);
			isEnemies = true;
		} else if (isConfig && isEnemies && !isEnemy && qName.equalsIgnoreCase("enemy")) {
			if (checkIsEnemy(attributes.getValue("name"))) {
				handleEnemy(attributes);
				isEnemy = true;
			}
		} else if (isConfig && isEnemies && isEnemy && !isEnemyResistance && qName.equalsIgnoreCase("resistance")) {
			handleEnemyResistance(attributes);
			isEnemyResistance = true;
		} else if (isConfig && isEnemies && isEnemy && !isEnemySprite && qName.equalsIgnoreCase("sprite")) {
			handleEnemySprite(attributes);
			isEnemySprite = true;
		} else if (isConfig && isEnemies && isEnemy && !isEnemyRectangle && qName.equalsIgnoreCase("rectangle")) {
			handleEnemyRectangle(attributes);
			isEnemyRectangle = true;
		} else if (isConfig && isEnemies && isEnemy && !isEnemyOffset && qName.equalsIgnoreCase("offset")) {
			handleEnemyOffset(attributes);
			isEnemyOffset = true;
		} else if (isConfig && !isEffects && qName.equalsIgnoreCase("effects")) {
			config.init(ConfigItem.EFFECTS);
			isEffects = true;
		} else if (isConfig && isEffects && !isEffect && qName.equalsIgnoreCase("effect")) {
			handleEffect(attributes);
			isEffect = true;
		} else if (isConfig && isEffects && isEffect && !isEffectTexture && qName.equalsIgnoreCase("sprite")) {
			handleEffectTexture(attributes);
			isEffectTexture = true;
		} else if (isConfig && isEffects && isEffect && !isEffectRectangle && qName.equalsIgnoreCase("rectangle")) {
			handleEffectRectangle(attributes);
			isEffectRectangle = true;
		} else if (!isBullets && !isTowers && !isAuxiliaries && !isEnemies && !isEffects) {
			super.startElement(uri, localName, qName, attributes);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (isConfig && isAssets && isSprites && qName.equalsIgnoreCase("sprites")) {
			isSprites = false;
		} else if (isConfig && isAssets && isSprites && isSprite && qName.equalsIgnoreCase("sprite")) {
			isSprite = false;
		} else if (isConfig && isBullets && qName.equalsIgnoreCase("bullets")) {
			isBullets = false;
		} else if (isConfig && isBullets && isBullet && qName.equalsIgnoreCase("bullet")) {
			isBullet = false;
		} else if (isConfig && isBullets && isBullet && isBulletTexture && qName.equalsIgnoreCase("sprite")) {
			isBulletTexture = false;
		} else if (isConfig && isBullets && isBullet && isBulletRectangle && qName.equalsIgnoreCase("rectangle")) {
			isBulletRectangle = false;
		} else if (isConfig && isTowers && qName.equalsIgnoreCase("towers")) {
			isTowers = false;
		} else if (isConfig && isTowers && isTower && qName.equalsIgnoreCase("tower")) {
			isTower = false;
		} else if (isConfig && isTowers && isTower && isTowerTexture && qName.equalsIgnoreCase("texture")) {
			isTowerTexture = false;
		} else if (isConfig && isTowers && isTower && isTowerRectangle && qName.equalsIgnoreCase("rectangle")) {
			isTowerRectangle = false;
		} else if (isConfig && isTowers && isTower && isTowerOffset && qName.equalsIgnoreCase("offset")) {
			isTowerOffset = false;
		} else if (isConfig && isTowers && isTower && isTowerBullet && qName.equalsIgnoreCase("bullet")) {
			isTowerBullet = false;
		} else if (isConfig && isTowers && isTower && isTowerBuild && qName.equalsIgnoreCase("build")) {
			isTowerBuild = false;
		} else if (isConfig && isTowers && isTower && isTowerAttack && qName.equalsIgnoreCase("attack")) {
			isTowerAttack = false;
		} else if (isConfig && isAuxiliaries && qName.equalsIgnoreCase("auxiliries")) {
			isAuxiliaries = false;
		} else if (isConfig && isAuxiliaries && isAuxiliary && qName.equalsIgnoreCase("auxiliary")) {
			isAuxiliary = false;
		} else if (isConfig && isAuxiliaries && isAuxiliary && isAuxiliaryPosition && qName.equalsIgnoreCase("position")) {
			isAuxiliaryPosition = false;
		} else if (isConfig && isAuxiliaries && isAuxiliary && isAuxiliaryBullet && qName.equalsIgnoreCase("bullet")) {
			isAuxiliaryBullet = false;
		} else if (isConfig && isAuxiliaries && isAuxiliary && isAuxiliaryAttack &&  qName.equalsIgnoreCase("attack")) {
			isAuxiliaryAttack = false;
		} else if (isConfig && isEnemies && qName.equalsIgnoreCase("enemies")) {
			isEnemies = false;
		} else if (isConfig && isEnemies && isEnemy && qName.equalsIgnoreCase("enemy")) {
			isEnemy = false;
		} else if (isConfig && isEnemies && isEnemy && isEnemyResistance && qName.equalsIgnoreCase("resistance")) {
			isEnemyResistance = false;
		}  else if (isConfig && isEnemies && isEnemy && isEnemySprite && qName.equalsIgnoreCase("sprite")) {
			isEnemySprite = false;
		} else if (isConfig && isEnemies && isEnemy && isEnemyRectangle && qName.equalsIgnoreCase("rectangle")) {
			isEnemyRectangle = false;
		} else if (isConfig && isEnemies && isEnemy && isEnemyOffset && qName.equalsIgnoreCase("offset")) {
			isEnemyOffset = false;
		} else if (isConfig && isEffects && qName.equalsIgnoreCase("effects")) {
			isEffects = false;
		} else if (isConfig && isEffects && isEffect && qName.equalsIgnoreCase("effect")) {
			isEffect = false;
		} else if (isConfig && isEffects && isEffect && isEffectTexture && qName.equalsIgnoreCase("sprite")) {
			isEffectTexture = false;
		} else if (isConfig && isEffects && isEffect && isEffectRectangle && qName.equalsIgnoreCase("rectangle")) {
			isEffectRectangle = false;
		} else if (!isBullets && !isTowers && !isAuxiliaries && !isEnemies && !isEffects) {
			super.endElement(uri, localName, qName);
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	private boolean checkIsEnemy(String enemyName) {
		final List<Wave> waves = config.getLevel().getWaves();
		for (Wave wave : waves) {
			for (Foe foe : wave.getFoes()) {
				if (foe.getEnemyName().equals(enemyName)) {
					return true;
				}
			}
		}
		return false;
	}
	
	protected String getSpriteName(int id) {
		final String fileName = config.getSprites().get(id).getFileName();
		final int index = fileName.lastIndexOf("/");
		return fileName.substring(index + 1, fileName.length() - 4);
	}
	
	private void handleSprite(Attributes attributes) {
		final int id = Integer.valueOf(attributes.getValue("id"));
		final String fileName = String.format("%s%s", spriteRootPath, attributes.getValue("file_name"));
		final int rows = Integer.valueOf(attributes.getValue("rows"));
		final int cols = Integer.valueOf(attributes.getValue("cols"));
		final boolean isGroups = Boolean.valueOf(attributes.getValue("groups"));
		final float speed = Float.valueOf(attributes.getValue("speed"));
		final SpriteInfo sprite = new SpriteInfo(fileName, rows, cols, isGroups, speed);
		config.getSprites().put(id, sprite);
	}
	
	private void handleBullet(Attributes attributes) {
		bullet = new Bullet();
		bulletLabel = new AnimLabel();
		final String name = attributes.getValue("name");
		final float speed = Float.valueOf(attributes.getValue("speed"));
		bullet.setName(name);
		bullet.setSpeed(speed);
		config.getBullets().put(name, bullet);
		bulletLabel.setName(name);
	}
	
	private void handleBulletTexture(Attributes attributes) {
		final int spriteId = Integer.valueOf(attributes.getValue("id"));
		final String spriteName = getSpriteName(spriteId);
		bulletLabel.setTextureName(spriteName);
//		final int textureId = Integer.valueOf(attributes.getValue("id"));
//		final String textureName = getTextureName(textureId);
//		bulletLabel.setTextureName(textureName);
	}
	
	private void handleBulletRectangle(Attributes attributes) {
		handlePanelRectangle(attributes);
		bulletLabel.setRectangle(panelRectangle);
		config.getBulletField().getLabels().put(bulletLabel.getName(), bulletLabel);
	}
	
	private void handleTower(Attributes attributes) {
		tower = new Tower();
		towerLabel = new Label();
		final String name = attributes.getValue("name");
		final int buyCost = Integer.valueOf(attributes.getValue("buy_cost"));
		final int sellCost = Integer.valueOf(attributes.getValue("sell_cost"));
		final float range = Float.valueOf(attributes.getValue("range"));
		final float damage = Float.valueOf(attributes.getValue("damage"));
		tower.setName(name);
		tower.setBuyCost(buyCost);
		tower.setSellCost(sellCost);
		tower.setRange(range);
		tower.setDamage(damage);
		towerLabel.setName(name);
	}
	
	private void handleTowerTexture(Attributes attributes) {
		final int textureId = Integer.valueOf(attributes.getValue("id"));
		final String textureName = getTextureName(textureId);
		towerLabel.setTextureName(textureName);
		if (attributes.getValue("z_index") != null) {
			final int zIndex = Integer.valueOf(attributes.getValue("z_index"));
			towerLabel.setZIndex(zIndex);
		}
	}
	
	private void handleTowerRectangle(Attributes attributes) {
		handlePanelRectangle(attributes);
		towerLabel.setRectangle(panelRectangle);
		config.getTowerField().getLabels().put(towerLabel.getName(), towerLabel);
	}
	
	private void handleTowerOffset(Attributes attributes) {
		final float x = Float.valueOf(attributes.getValue("x"));
		final float y = Float.valueOf(attributes.getValue("y"));
		tower.setOffset(new Vector2(x, y));
	}
	
	private void handleTowerBullet(Attributes attributes) {
		final String bulletName = attributes.getValue("name");
		tower.setBulletName(bulletName);
	}
	
	private void handleTowerBuild(Attributes attributes) {
		final float buildRate = Float.valueOf(attributes.getValue("rate"));
		tower.setBuildRate(buildRate);
	}
	
	private void handleTowerAttack(Attributes attributes) {
		final float attackRate = Float.valueOf(attributes.getValue("rate"));
		tower.setAttackRate(attackRate);
		config.getTowers().put(tower.getName(), tower);
	}
	
	private void handleAuxiliary(Attributes attributes) {
		auxiliary = new Auxiliary();
		final String name = attributes.getValue("name");
		final float damage = Float.valueOf(attributes.getValue("damage"));
		auxiliary.setName(name);
		auxiliary.setDamage(damage);
	}

	private void handleAuxiliaryPosition(Attributes attributes) {
		final float x = Float.valueOf(attributes.getValue("x"));
		final float y = Float.valueOf(attributes.getValue("y"));
		auxiliary.setPosition(new Vector2(x, y));
	}
	
	private void handleAuxiliaryBullet(Attributes attributes) {
		final String name = attributes.getValue("name");
		auxiliary.setBulletName(name);
	}

	private void handleAuxiliaryAttack(Attributes attributes) {
		final float attackRate = Float.valueOf(attributes.getValue("rate"));
		auxiliary.setAttackRate(attackRate);
		config.getAuxiliaries().put(auxiliary.getName(), auxiliary);
	}
	
	private void handleEnemy(Attributes attributes) {
		enemy = new Enemy();
		enemyLabel = new AnimLabel();
		final String name = attributes.getValue("name");
		final float speed = Float.valueOf(attributes.getValue("speed"));
		final float health = Float.valueOf(attributes.getValue("health"));
		final int damage = Integer.valueOf(attributes.getValue("damage"));
		final int price = Integer.valueOf(attributes.getValue("price"));
		enemy.setName(name);
		enemy.setSpeed(speed);
		enemy.setHealth(health);
		enemy.setDamage(damage);
		enemy.setPrice(price);
		enemyLabel.setName(name);
		config.getEnemies().put(enemy.getName(), enemy);
	}

	private void handleEnemyResistance(Attributes attributes) {
		final String bulletName = attributes.getValue("bullet_name");
		final int pct = Integer.valueOf(attributes.getValue("pct"));
		enemy.getResistances().put(bulletName, pct);
	}
	
	private void handleEnemySprite(Attributes attributes) {
		final int spriteId = Integer.valueOf(attributes.getValue("id"));
		final String spriteName = getSpriteName(spriteId);
		enemyLabel.setTextureName(spriteName);
	}

	private void handleEnemyRectangle(Attributes attributes) {
		handlePanelRectangle(attributes);
		enemyLabel.setRectangle(panelRectangle);
		config.getEnemyField().getAnimLabels().put(enemyLabel.getName(), enemyLabel);
	}

	private void handleEnemyOffset(Attributes attributes) {
		final float x = Float.valueOf(attributes.getValue("x"));
		final float y = Float.valueOf(attributes.getValue("y"));
		enemy.setOffset(new Vector2(x, y));
	}
	
	private void handleEffect(Attributes attributes) {
		effect = new Effect();
		effectLabel = new AnimLabel();
		final String name = attributes.getValue("name");
		final float duration = Float.valueOf(attributes.getValue("duration"));
		effect.setName(name);
		effect.setDuration(duration);
		effectLabel.setName(name);
		config.getEffects().put(effect.getName(), effect);
	}

	private void handleEffectTexture(Attributes attributes) {
//		final int textureId = Integer.valueOf(attributes.getValue("id"));
//		final String textureName = getTextureName(textureId);
//		effectLabel.setTextureName(textureName);
		final int spriteId = Integer.valueOf(attributes.getValue("id"));
		final String spriteName = getSpriteName(spriteId);
		effectLabel.setTextureName(spriteName);
	}

	private void handleEffectRectangle(Attributes attributes) {
		handlePanelRectangle(attributes);
		effectLabel.setRectangle(panelRectangle);
		config.getEffectField().getLabels().put(effectLabel.getName(), effectLabel);
	}
	
	// Properties ----------------------------------------
}
