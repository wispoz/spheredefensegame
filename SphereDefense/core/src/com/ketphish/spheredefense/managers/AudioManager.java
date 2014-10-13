package com.ketphish.spheredefense.managers;

import java.util.Map;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import com.ketphish.spheredefense.SphereDefenseGame;
import com.ketphish.spheredefense.models.profile.Options;

public final class AudioManager
implements Disposable {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private final SphereDefenseGame game;
	private Music music;
	private Map<String, Sound> sounds;
	
	// Constructors --------------------------------------
	public AudioManager(SphereDefenseGame game) {
		this.game = game;
	}
	
	// Extends -------------------------------------------

	// Implementations -----------------------------------
	
	// Methods -------------------------------------------
	public void playSound(String soundName, float volume) {
		if (getOptions().isSound()) {
			if (sounds.containsKey(soundName)) {
				sounds.get(soundName).play(volume);
			}
		}
	}
	
	public void playMusic() {
		if (!music.isPlaying() && getOptions().isMusic()) {
			music.play();
			music .setVolume(0.5f);
			music.setLooping(true);
		}
	}
	
	public void stopMusic() {
		if (music.isPlaying()) {
			music.stop();
		}
	}
	
	public void updateMusicState(boolean isMusic) {
		if (isMusic) {
			playMusic();
		} else {
			stopMusic();
		}
	}
	
	public void destroy() {
		disposeMusic();
		disposeSounds();
	}
	
	public void disposeMusic() {
		if (music != null) {
			stopMusic();
			music.dispose();
		}
	}
	
	public void disposeSounds() {
		if (sounds != null && sounds.size() > 0) {
			for (Sound sound : sounds.values()) {
				sound.stop();
				sound.dispose();
			}
			sounds.clear();
		}
	}
	
	@Override
	public void dispose() {
		disposeMusic();
		disposeSounds();
	}
	
	// Properties ----------------------------------------
	public void setMusic(Music music) {
		if (music != null) {
			if (this.music != null 
					&& this.music.isPlaying()) {
				this.music.stop();
			}
			this.music = music;
		}
	}
	
	public void setSounds(Map<String, Sound> sounds) {
		if (sounds != null) {
			this.sounds = sounds;
		}
	}
	
	private Options getOptions() {
		return game.getGameLogic().getProfileManager()
				.getProfile().getOptions();
	}

}
