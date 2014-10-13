package com.ketphish.spheredefense.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public final class OrthographicCameraWithVirtualViewport
extends OrthographicCamera {
	// Inner ---------------------------------------------

	// Fields --------------------------------------------
	private VirtualViewport virtualViewport; 
    private final Vector3 tmp = new Vector3();  
    private final Vector2 origin = new Vector2();  
    
	// Constructors --------------------------------------
    public OrthographicCameraWithVirtualViewport(VirtualViewport virtualViewport) {  
        this(virtualViewport, 0f, 0f);  
    }  
  
    public OrthographicCameraWithVirtualViewport(VirtualViewport virtualViewport, float cx, float cy) {  
        this.virtualViewport = virtualViewport;  
        this.origin.set(cx, cy);  
    }  
    
	// Extends -------------------------------------------
    @Override  
    public void update() {  
        final float left = zoom * -viewportWidth / 2 + virtualViewport.getVirtualWidth() * origin.x;  
        final float right = zoom * viewportWidth / 2 + virtualViewport.getVirtualWidth() * origin.x;  
        final float top = zoom * viewportHeight / 2 + virtualViewport.getVirtualHeight() * origin.y;  
        final float bottom = zoom * -viewportHeight / 2 + virtualViewport.getVirtualHeight() * origin.y;  
        projection.setToOrtho(left, right, bottom, top, Math.abs(near), Math.abs(far));  
        view.setToLookAt(position, tmp.set(position).add(direction), up);  
        combined.set(projection);  
        Matrix4.mul(combined.val, view.val); 
        invProjectionView.set(combined);  
        Matrix4.inv(invProjectionView.val);  
        frustum.update(invProjectionView);
    } 
    
	// Implementations -----------------------------------

	// Methods -------------------------------------------
    public void updateViewport() {  
        setToOrtho(false, virtualViewport.getWidth(), virtualViewport.getHeight());  
    }  
    
	// Properties ----------------------------------------
    public void setVirtualViewport(VirtualViewport virtualViewport) {  
        this.virtualViewport = virtualViewport;  
    }  
    
    public void setPosition(float x, float y) {  
        position.set(x - viewportWidth * origin.x, y - viewportHeight * origin.y, 0f);  
    }  
    
}
