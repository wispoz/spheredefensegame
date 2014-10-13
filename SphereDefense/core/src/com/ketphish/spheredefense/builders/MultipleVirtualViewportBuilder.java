package com.ketphish.spheredefense.builders;

import com.ketphish.spheredefense.camera.VirtualViewport;

public final class MultipleVirtualViewportBuilder {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
    private final float minWidth;  
    private final float minHeight;  
    private final float maxWidth;  
    private final float maxHeight;
    
	// Constructors --------------------------------------
    public MultipleVirtualViewportBuilder(
    		float minWidth, float minHeight, 
    		float maxWidth, float maxHeight) {  
        this.minWidth = minWidth;  
        this.minHeight = minHeight;  
        this.maxWidth = maxWidth;  
        this.maxHeight = maxHeight;  
    }  
    
	// Extends -------------------------------------------

	// Implementations -----------------------------------

	// Methods -------------------------------------------
    public VirtualViewport getVirtualViewport(float width, float height) {  
        if (width >= minWidth && width <= maxWidth && height >= minHeight && height <= maxHeight) { 
            return new VirtualViewport(width, height, true);  
        }
        final float aspect = width / height;  
        final float scaleForMinSize = minWidth / width;  
        final float scaleForMaxSize = maxWidth / width;  
        float virtualViewportWidth = width * scaleForMaxSize;  
        float virtualViewportHeight = virtualViewportWidth / aspect;  
        if (insideBounds(virtualViewportWidth, virtualViewportHeight)) { 
            return new VirtualViewport(virtualViewportWidth, virtualViewportHeight, false);  
        }
        virtualViewportWidth = width * scaleForMinSize;  
        virtualViewportHeight = virtualViewportWidth / aspect;  
        if (insideBounds(virtualViewportWidth, virtualViewportHeight)) { 
            return new VirtualViewport(virtualViewportWidth, virtualViewportHeight, false);  
        }
        return new VirtualViewport(minWidth, minHeight, true);  
    }
    
    private boolean insideBounds(float width, float height) {  
        if (width < minWidth || width > maxWidth) { 
            return false;  
        }
        if (height < minHeight || height > maxHeight) {  
            return false;  
        }
        return true;  
    } 
    
	// Properties ----------------------------------------
}
