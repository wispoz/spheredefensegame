package com.ketphish.spheredefense.models.profile;

import com.ketphish.spheredefense.models.Entity;

public final class Options
extends Entity
implements Cloneable {
	// Inner ---------------------------------------------
	public interface Event {
		void onIsMusicChange(Options sender);
	}
	
	public enum Language {
		ENGLISH	("english"),
		RUSSIAN	("russian");
		
		private final String name;
		
		private Language(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
	}
	
	// Fields --------------------------------------------
	private boolean isMusic;
	private boolean isSound;
	private Language language;
	private Event event;

	// Constructors --------------------------------------
	public Options(int id) {
		super(id);
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	@Override
	public Options clone() throws CloneNotSupportedException {
		Options options = (Options) super.clone();
		options.setEvent(null);
		return options;
	}
	
	// Methods -------------------------------------------

	// Properties ----------------------------------------
	public boolean isMusic() {
		return isMusic;
	}

	public void setIsMusic(boolean isMusic) {
		if (this.isMusic != isMusic) {
			this.isMusic = isMusic;
			if (event != null) {
				event.onIsMusicChange(this);
			}
		}
	}

	public boolean isSound() {
		return isSound;
	}

	public void setIsSound(boolean isSound) {
		if (this.isSound != isSound) {
			this.isSound = isSound;
		}
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		if (this.language != language) {
			this.language = language;
		}
	}
	
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
