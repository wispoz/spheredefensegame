package com.ketphish.spheredefense.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.badlogic.gdx.ScreenAdapter;
import com.ketphish.spheredefense.SphereDefenseGame;
import com.ketphish.spheredefense.helpers.Preset;
import com.ketphish.spheredefense.managers.Drawer;
import com.ketphish.spheredefense.views.panels.Button;

public abstract class Screen
extends ScreenAdapter {
	// Inner ---------------------------------------------
	
	// Fields --------------------------------------------
	protected SphereDefenseGame game;
	protected Drawer drawer;
	protected final List<Panel> panels;
	
	// Constructors --------------------------------------
	public Screen() {
		panels = new ArrayList<Panel>();
	}
	
	// Extends -------------------------------------------
	public void draw(float deltaTime) {
		for (Panel panel : panels) {
			panel.draw(drawer, deltaTime);
		}
	}
	
	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	protected abstract void onButtonTouch(Button sender);

	protected void addPanel(Panel panel) {
		panel.setScreen(this);
		panels.add(panel);
	}
	
	public void activate() {
		for (Panel panel : panels) {
			if (panel instanceof Button) {
				((Button) panel).activate();
			}
		}
	}
	
	public void deactivate() {
		for (Panel panel : panels) {
			if (panel instanceof Button) {
				((Button) panel).deactivate();
			}
		}
	}
	
	public void adjustPanels(boolean isSorting) {
		setPanelsPositionByParent();
		translatePanelsPosition();
		if (isSorting) {
			sortPanelsByZIndex();
		}
	}
	
	protected void setPanelsPositionByParent() {
		for (Panel panel : panels) {
			if (!panel.getParentName().equals("")) {
				final String parentName = panel.getParentName();
				final float x = panel.getRectangle().x;
				final float y = panel.getRectangle().y;
				for (Panel parentPanel : panels) {
					if (parentName.equals(parentPanel.getName())) {
						final float parentX = parentPanel.getRectangle().x;
						final float parentY = parentPanel.getRectangle().y;
						panel.getRectangle().x = x + parentX;
						panel.getRectangle().y = y + parentY;
						break;
					}
				}
			}
		}
	}
	
	protected void translatePanelsPosition() {
		for (Panel panel : panels) {
			final float height = panel.getRectangle().height;
			final float y = panel.getRectangle().y;
			panel.getRectangle().y = Preset.getInstance().translateTopToBottomY(height, y);
		}
	}
	
	protected void sortPanelsByZIndex() {
		Comparator<Panel> comparator = new Comparator<Panel>() {
			@Override
			public int compare(Panel o1, Panel o2) {
				final float zIndex1 = o1.getZIndex();
				final float zIndex2 = o2.getZIndex();
				if (zIndex1 < zIndex2) {
					return -1;
				} else if (zIndex1 > zIndex2) {
					return 1;
				}
				return 0;
			}
		};
		Collections.sort(panels, comparator);
	}
	
	// Properties ----------------------------------------
	public SphereDefenseGame getGame() {
		return game;
	}
	
	public void setGame(SphereDefenseGame game) {
		this.game = game;
	}
	
	public Drawer getDrawer() {
		return drawer;
	}

	public void setDrawer(Drawer drawer) {
		this.drawer = drawer;
	}
	
}
