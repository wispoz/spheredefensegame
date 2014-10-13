package com.ketphish.spheredefense.camera;

import com.badlogic.gdx.Gdx;

public final class VirtualViewport {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
    private final float virtualWidth;  
    private final float virtualHeight; 
    
	// Constructors --------------------------------------
    public VirtualViewport(float virtualWidth, float virtualHeight) {  
        this(virtualWidth, virtualHeight, false);  
    }  
  
    public VirtualViewport(float virtualWidth, float virtualHeight, boolean shrink) {  
        this.virtualWidth = virtualWidth;  
        this.virtualHeight = virtualHeight;  
    } 
    
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
    public float getWidth() {  
        return getWidth(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
    }  
  
    public float getHeight() {  
        return getHeight(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
    }  
    
    public float getWidth(float screenWidth, float screenHeight) {  
    	final float virtualAspect = virtualWidth / virtualHeight;  
    	final float aspect = screenWidth / screenHeight;  
        if (aspect > virtualAspect || (Math.abs(aspect - virtualAspect) < 0.01f)) {  
            return virtualHeight * aspect;  
        } else {  
            return virtualWidth;  
        }  
    }  
  
    public float getHeight(float screenWidth, float screenHeight) {  
    	final float virtualAspect = virtualWidth / virtualHeight;  
    	final float aspect = screenWidth / screenHeight;  
        if (aspect > virtualAspect || (Math.abs(aspect - virtualAspect) < 0.01f)) {  
            return virtualHeight;  
        } else {  
            return virtualWidth / aspect;  
        }  
    } 
    
	// Properties ----------------------------------------
    public float getVirtualWidth() {  
        return virtualWidth;  
    }  
  
    public float getVirtualHeight() {  
        return virtualHeight;  
    }   
    
}
