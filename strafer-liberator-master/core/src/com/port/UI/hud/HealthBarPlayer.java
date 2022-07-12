package com.port.UI.hud;

import com.badlogic.gdx.graphics.Color;

import greenfoot.*;  

public class HealthBarPlayer extends HealthBar
{
    public HealthBarPlayer(String refText, String unitType, int initValue, int maxValue) {
        super(refText,unitType,initValue,maxValue);

		setSafeColor(Color.TEAL.lerp(Color.SKY, 0.7f));
		setDangerColor(Color.ORANGE);
		setBarWidth(179);
		setBarHeight(12);
		setReferenceText("");
		setTextColor(new Color(4, 69, 85, 214));
    }
    public void act() 
    {
        super.act();
    }    
}
