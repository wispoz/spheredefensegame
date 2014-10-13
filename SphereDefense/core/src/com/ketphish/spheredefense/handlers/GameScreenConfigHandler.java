package com.ketphish.spheredefense.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.ketphish.spheredefense.models.Config;
import com.ketphish.spheredefense.models.Config.ConfigItem;
import com.ketphish.spheredefense.models.profile.Options.Language;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.Label;
import com.ketphish.spheredefense.views.panels.TextField;

public class GameScreenConfigHandler
extends DefaultHandler {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	protected Config config;
	protected Rectangle panelRectangle;
	protected String rootPath;
	protected String panelName;
	protected String panelParentName;
	protected String textFieldText;
	protected int languageId;
	protected float panelZindex;
	protected int level;
	protected Language language;
	protected boolean isPanelVisible;
	protected boolean isConfig;
	protected boolean isLanguages;
	protected boolean isLanguage;
	protected boolean isLanguageId;
	protected boolean isAssets;
	protected boolean isTextures;
	protected boolean isTexture;
	protected boolean isFonts;
	protected boolean isFont;
	protected boolean isMusic;
	protected boolean isTrack;
	protected boolean isSounds;
	protected boolean isSound;
	protected boolean isPanels;
	protected boolean isLabels;
	protected boolean isLabel;
	protected boolean isLabelRectangle;
	protected boolean isLabelTexture;
	protected boolean isLabelTextureSelected;
	protected boolean isTextFields;
	protected boolean isTextField;
	protected boolean isTextFieldRectangle;
	protected boolean isTextFieldText;
	protected boolean isTextFieldTextSelected;
	protected boolean isTextFieldFont;
	protected boolean isTextFieldFontSelected;
	protected boolean isButtons;
	protected boolean isButton;
	protected boolean isButtonRectangle;
	protected boolean isButtonTexture;
	protected boolean isButtonTextureSelected;
	
	// Constructors --------------------------------------
	public GameScreenConfigHandler() {
		rootPath = "";
		panelName = "";
		panelParentName = "";
		textFieldText = "";
		isConfig = false;
		isLanguages = false;
		isLanguage = false;
		isAssets = false;
		isTextures = false;
		isTexture = false;
		isFonts = false;
		isFont = false;
		isMusic = false;
		isTrack = false;
		isSounds = false;
		isSound = false;
		isPanels = false;
		isLabels = false;
		isLabel = false;
		isLabelRectangle = false;
		isLabelTexture = false;
		isLabelTextureSelected = false;
		isTextFields = false;
		isTextField = false;
		isTextFieldRectangle = false;
		isTextFieldText = false;
		isTextFieldTextSelected = false;
		isTextFieldFont = false;
		isTextFieldFontSelected = false;
		isButtons = false;
		isButton = false;
		isButtonRectangle = false;
		isButtonTexture = false;
		isButtonTextureSelected = false;
	}
	
	public GameScreenConfigHandler(
			Language language,
			int level) {
		this();
		this.language = language;
		this.level = level;
	}
	
	// Extends -------------------------------------------
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (!isConfig && qName.equalsIgnoreCase("config")) {
			isConfig = true;
			if (config == null) {
				config = new Config();
			}
		} else if (isConfig && !isLanguage && qName.equalsIgnoreCase("languages")) {
			isLanguages = true;
		} else if (isConfig && isLanguages && !isLanguage && !isLanguageId && qName.equalsIgnoreCase("language")) {
			isLanguage = true;
			isLanguageId = handleLanguageAttr(attributes);
		} else if (isConfig && !isAssets && qName.equalsIgnoreCase("assets")) {
			isAssets = true;
		} else if (isConfig && isAssets && !isTextures && qName.equalsIgnoreCase("textures")) {
			initAsset(ConfigItem.TEXTURES, attributes);
			isTextures = true;
		} else if (isConfig && isAssets && isTextures && !isTexture && qName.equalsIgnoreCase("texture")) {
			handleAssetAtrributes(ConfigItem.TEXTURES, attributes);
			isTexture = true;
		} else if (isConfig && isAssets && !isFonts && qName.equalsIgnoreCase("fonts")) {
			initAsset(ConfigItem.FONTS, attributes);
			isFonts = true;
		} else if (isConfig && isAssets && !isFont && qName.equalsIgnoreCase("font")) {
			handleAssetAtrributes(ConfigItem.FONTS, attributes);
			isFont = true;
		} else if (isConfig && isAssets && !isMusic && qName.equalsIgnoreCase("music")) {
			initAsset(ConfigItem.MUSIC, attributes);
			isMusic = true;
		} else if (isConfig && isAssets && isMusic && qName.equalsIgnoreCase("track")) {
			handleAssetAtrributes(ConfigItem.MUSIC, attributes);
			isTrack = true;
		} else if (isConfig && isAssets && !isSounds && qName.equalsIgnoreCase("sounds")) {
			initAsset(ConfigItem.SOUNDS, attributes);
			isSounds = true;
		} else if (isConfig && isAssets && isSounds && !isSound && qName.equalsIgnoreCase("sound")) {
			handleAssetAtrributes(ConfigItem.SOUNDS, attributes);
			isSound = true;
		} else if (isConfig && !isPanels && qName.equalsIgnoreCase("panels")) {
			isPanels = true;
		} else if (isConfig && !isLabels && qName.equalsIgnoreCase("labels")) {
			initPanel(ConfigItem.LABELS);
			isLabels = true;
		} else if (isConfig && !isLabel && qName.equalsIgnoreCase("label")) {
			handlePanelAttributes(attributes);
			isLabel = true;
		} else if (isConfig && isLabel && !isLabelRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isLabelRectangle = true;
		} else if (isConfig && isLabel && !isLabelTexture && !isLabelTextureSelected && qName.equalsIgnoreCase("texture")) {
			isLabelTextureSelected = handlePanelTexture(ConfigItem.LABELS, attributes);
			isLabelTexture = true;
		} else if (isConfig && isPanels && !isTextFields && qName.equalsIgnoreCase("text_fields")) {
			initPanel(ConfigItem.TEXT_FIELDS);
			isTextFields = true;
		} else if (isConfig && isPanels && isTextFields && !isTextField && qName.equalsIgnoreCase("text_field")) {
			handlePanelAttributes(attributes);
			isTextField = true;
		} else if (isConfig && isPanels && isTextFields && isTextField && !isTextFieldRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isTextFieldRectangle = true;
		} else if (isConfig && isPanels && isTextFields && isTextField && !isTextFieldText && !isTextFieldTextSelected && qName.equalsIgnoreCase("text")) {
			isTextFieldTextSelected = handlePanelText(attributes);
			isTextFieldText = true;
		} else if (isConfig && isPanels && isTextFields && isTextField && !isTextFieldFont && !isTextFieldFontSelected && qName.equalsIgnoreCase("font")) {
			isTextFieldFontSelected = handlePanelFont(attributes);
			isTextFieldFont = true;
		} else if (isConfig && isPanels && !isButtons && qName.equalsIgnoreCase("buttons")) {
			initPanel(ConfigItem.BUTTONS);
			isButtons = true;
		} else if(isConfig && isPanels && isButtons && !isButton && qName.equalsIgnoreCase("button")) {
			handlePanelAttributes(attributes);
			isButton = true;
		} else if (isConfig && isPanels && isButtons && isButton && !isButtonRectangle && qName.equalsIgnoreCase("rectangle")) {
			handlePanelRectangle(attributes);
			isButtonRectangle = true;
		} else if (isConfig && isPanels && isButtons && isButton && !isButtonTexture && !isButtonTextureSelected && qName.equalsIgnoreCase("texture")) {
			isButtonTextureSelected = handlePanelTexture(ConfigItem.BUTTONS, attributes);
			isButtonTexture = true;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (isTextFieldText && isTextFieldTextSelected) {
			setPanelText(ch, start, length);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (isConfig && qName.equalsIgnoreCase("config")) {
			isConfig = false;
		} else if (isConfig && isLanguages && qName.equalsIgnoreCase("languages")) {
			isLanguages = false;
		} else if (isConfig && isLanguages && isLanguage && qName.equalsIgnoreCase("language")) {
			isLanguage = false;
		} else if (isConfig && isAssets && qName.equalsIgnoreCase("assets")) {
			isAssets = false;
		} else if (isConfig && isAssets && isTextures && qName.equalsIgnoreCase("textures")) {
			isTextures = false;
		} else if (isConfig && isAssets && isTextures && isTexture && qName.equalsIgnoreCase("texture")) {
			isTexture = false;
		} else if (isConfig && isAssets && isFonts && qName.equalsIgnoreCase("fonts")) {
			isFonts = false;
		} else if (isConfig && isAssets && isFonts && isFont && qName.equalsIgnoreCase("font")) {
			isFont = false;
		} else if (isConfig && isAssets && isMusic && qName.equalsIgnoreCase("music")) {
			isMusic = false;
		} else if (isConfig && isAssets && isMusic && isTrack && qName.equalsIgnoreCase("track")) {
			isTrack = false;
		} else if (isConfig && isAssets && isSounds && qName.equalsIgnoreCase("sounds")) {
			isSounds = false;
		} else if (isConfig && isAssets && isSounds && isSound && qName.equalsIgnoreCase("sound")) {
			isSound = false;
		} else if (isConfig && isPanels && qName.equalsIgnoreCase("panels")) {
			isPanels = false;
		} else if (isConfig && isPanels && isLabels && qName.equalsIgnoreCase("labels")) {
			isLabels = false;
		} else if (isConfig && isPanels && isLabels && isLabel && isLabel && qName.equalsIgnoreCase("label")) {
			isLabel = false;
			isLabelTextureSelected = false;
		} else if (isConfig && isPanels && isLabels && isLabel && isLabelRectangle && qName.equalsIgnoreCase("rectangle")) {
			isLabelRectangle = false;
		} else if (isConfig && isPanels && isLabels && isLabel && isLabelTexture && qName.equalsIgnoreCase("texture")) {
			isLabelTexture = false;
		} else if (isConfig && isPanels && isTextFields && qName.equalsIgnoreCase("text_fields")) {
			isTextFields = false;
		} else if (isConfig && isPanels && isTextFields && isTextField && qName.equalsIgnoreCase("text_field")) {
			isTextField = false;
			isTextFieldTextSelected = false;
			isTextFieldFontSelected = false;
		} else if (isConfig && isPanels && isTextFields && isTextField && isTextFieldRectangle && qName.equalsIgnoreCase("rectangle")) {
			isTextFieldRectangle = false;
		} else if (isConfig && isPanels && isTextFields && isTextField && isTextFieldText && qName.equalsIgnoreCase("text")) {
			isTextFieldText = false;
		} else if (isConfig && isPanels && isTextFields && isTextField && isTextFieldFont && qName.equalsIgnoreCase("font")) {
			isTextFieldFont = false;
		} else if (isConfig && isPanels && isButtons && qName.equalsIgnoreCase("buttons")) {
			isButtons = false;
		} else if(isConfig && isPanels && isButtons && isButton && qName.equalsIgnoreCase("button")) {
			isButton = false;
			isButtonTextureSelected = false;
		} else if (isConfig && isPanels && isButtons && isButton && isButtonRectangle && qName.equalsIgnoreCase("rectangle")) {
			isButtonRectangle = false;
		} else if (isConfig && isPanels && isButtons && isButton && isButtonTexture && qName.equalsIgnoreCase("texture")) {
			isButtonTexture = false;
		}
	}
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public boolean handleLanguageAttr(Attributes attributes) {
		final String name = attributes.getValue("name");
		if (name.equalsIgnoreCase(language.getName())) {
			languageId = Integer.valueOf(attributes.getValue("id"));
			return true;
		}
		return false;
	}
	
	public void initAsset(ConfigItem item, Attributes attributes) {
		config.init(item);
		rootPath = attributes.getValue("root_path");
	}
	
	public void initPanel(ConfigItem item) {
		config.init(item);
	}
	
	public boolean handleAssetAtrributes(ConfigItem item, Attributes attributes) {
		final boolean isLanguageConflict = isLanguageConflict(attributes);
		final boolean isLevelConflict = isLevelConflict(attributes);
		if (!isLanguageConflict && !isLevelConflict) {
			final String fileName = attributes.getValue("file_name");
			switch (item) {
			case TEXTURES:
				int id = Integer.valueOf(attributes.getValue("id"));
				config.getTextures().put(id, String.format("%s%s", rootPath, fileName));
				break;
			case FONTS:
				id = Integer.valueOf(attributes.getValue("id"));
				config.getFonts().put(id, String.format("%s%s", rootPath, fileName));
				break;
			case MUSIC:
				config.setMusic(String.format("%s%s", rootPath, fileName));
				break;
			case SOUNDS:
				config.getSounds().add(String.format("%s%s", rootPath, fileName));
				break;
			default:
				break;
			}
			return true;
		}
		return false;
	}

	public void handlePanelAttributes(Attributes attributes) {
		panelName = attributes.getValue("name");
		if (attributes.getValue("parent_name") != null) {
			panelParentName = attributes.getValue("parent_name");
		}
		if (attributes.getValue("z_index") != null) {
			panelZindex = Float.valueOf(attributes.getValue("z_index"));
		} else if (panelZindex != 0) {
			panelZindex = 0;
		}
		if (attributes.getValue("visible") != null) {
			isPanelVisible = Boolean.valueOf(attributes.getValue("visible"));
		} else if (!isPanelVisible) {
			isPanelVisible = true;
		} 
	}
	
	public void handlePanelRectangle(Attributes attributes) {
		final float x = Float.valueOf(attributes.getValue("x"));
		final float y = Float.valueOf(attributes.getValue("y"));
		final float width = Float.valueOf(attributes.getValue("width"));
		final float height = Float.valueOf(attributes.getValue("height"));
		panelRectangle = new Rectangle(x, y, width, height);
	}
	
	public boolean handlePanelTexture(ConfigItem item, Attributes attributes) {
		final boolean isLanguageConflict = isLanguageConflict(attributes);
		final boolean isLevelConflict = isLevelConflict(attributes);
		if (!isLanguageConflict && !isLevelConflict) {
			final int id = Integer.valueOf(attributes.getValue("id"));
			final String textureName = getTextureName(id);
			switch(item) {
			case LABELS:
				final Label panel = new Label(
						panelName, panelParentName, textureName, 
						panelRectangle, panelZindex, isPanelVisible);
				config.getLabels().put(panelName, panel);
				break;
			case BUTTONS:
				final Button button = new Button(
						panelName, panelParentName, textureName, 
						panelRectangle, panelZindex, isPanelVisible);
				config.getButtons().put(panelName, button);
				break;
			default:
				break;
			}
			resetPanelValues();
			return true;
		}
		return false;
	}

	public boolean handlePanelText(Attributes attributes) {
		return !isLanguageConflict(attributes) && !isLevelConflict(attributes);
	}
	
	public void setPanelText(char[] ch, int start, int length) {
		textFieldText = new String(ch, start, length);
	}
	
	public boolean handlePanelFont(Attributes attributes) {
		final boolean isLanguageConflict = isLanguageConflict(attributes);
		final boolean isLevelConflict = isLevelConflict(attributes);
		if (!isLanguageConflict && !isLevelConflict) {
			final int id = Integer.valueOf(attributes.getValue("id"));
			final String fontName = getFontName(id);
			final float fontScale = Float.valueOf(attributes.getValue("scale"));
			final float r = Float.valueOf(attributes.getValue("r"));
			final float g = Float.valueOf(attributes.getValue("g"));
			final float b = Float.valueOf(attributes.getValue("b"));
			final Color color = new Color(r, g, b, 1);
			final TextField textField = 
					new TextField(panelName, panelParentName, textFieldText, 
							fontName, panelRectangle, 
							fontScale, color, 
							panelZindex, isPanelVisible);
			config.getTextFields().put(panelName, textField);
			resetPanelValues();
			return true;
		}
		return false;
	}

	protected String getTextureName(int id) {
		final String fileName = config.getTextures().get(id);
		final int index = fileName.lastIndexOf("/");
		return fileName.substring(index + 1, fileName.length() - 4);
	}
	
	protected String getFontName(int id) {
		final String fileName = config.getFonts().get(id);
		final int index = fileName.lastIndexOf("/");
		return fileName.substring(index + 1, fileName.length() - 4);
	}

	protected boolean isLanguageConflict(Attributes attributes) {
		if (language != null 
				&& attributes.getValue("language_id") != null) {
			final int assetLanguageId = Integer.valueOf(attributes.getValue("language_id"));
			if (languageId != assetLanguageId) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean isLevelConflict(Attributes attributes) {
		if (level != 0 && attributes.getValue("level") != null) {
			final int assetLevel = Integer.valueOf(attributes.getValue("level"));
			if (level != assetLevel) {
				return true;
			}
		}
		return false;
	}

	protected void resetPanelValues() {
		if (!panelName.equals("")) {
			panelName = "";
		}
		if (!panelParentName.equals("")) {
			panelParentName = "";
		}
		if (!textFieldText.equals("")) {
			textFieldText = "";
		}
		if (panelZindex != 0) {
			panelZindex = 0;
		}
		if (!isPanelVisible) {
			isPanelVisible = true;
		}
	} 
	
	// Properties ----------------------------------------
	public Config getConfig() {
		return config;
	}
	
	public void setConfig(Config config) {
		this.config = config;
	}
	
}
