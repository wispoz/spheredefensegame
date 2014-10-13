package com.ketphish.spheredefense.views;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ketphish.spheredefense.managers.ProfileManager;
import com.ketphish.spheredefense.views.panels.Button;
import com.ketphish.spheredefense.views.panels.Label;
import com.ketphish.spheredefense.views.panels.TextField;

public abstract class GameScreen
extends Screen {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	protected Map<String, Label> labels;
	protected Map<String, TextField> textFields;
	protected Map<String, Button> buttons;

	// Constructors --------------------------------------
	public GameScreen() {
		super();
	}
	
	// Extends -------------------------------------------
	
	// Implementations -----------------------------------

	// Methods -------------------------------------------
	public abstract void bindData(ProfileManager profileManager);
	
	// Properties ----------------------------------------
	public Map<String, Label> getLabels() {
		return labels;
	}

	public void setLabels(Map<String, Label> labels) {
		if (labels != null) {
			this.labels = new LinkedHashMap<String, Label>(labels);
			for (Label panel : this.labels.values()) {
				addPanel(panel);
			}
		}
	}

	public Map<String, TextField> getTextFields() {
		return textFields;
	}
	
	public void setTextFields(Map<String, TextField> textFields) {
		if (textFields != null) {
			this.textFields = new LinkedHashMap<String, TextField>(textFields);
			for (TextField panel : this.textFields.values()) {
				addPanel(panel);
			}
		}
	}

	public Map<String, Button> getButtons() {
		return buttons;
	}
	
	public void setButtons(Map<String, Button> buttons) {
		if (buttons != null) {
			this.buttons = new LinkedHashMap<String, Button>(buttons);
			for (Button panel : this.buttons.values()) {
				addPanel(panel);
			}
		}
	}
	
}
