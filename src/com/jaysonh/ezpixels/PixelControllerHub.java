package com.jaysonh.ezpixels;

import java.util.*;
import processing.core.*;

public class PixelControllerHub
{
	
	public PixelControllerHub(String ipAddress)
	{
		log = LoggerJay.getInstance();
		this.ipAddress = ipAddress;
	}
	
	public PixelControllerHub  addStrip( int ledType, int numLeds, int mappingBeginX, int mappingBeginY, int mappingWidth, int mappingHeight )
	{
		LEDStrip strip = new LEDStrip( ledType, numLeds, mappingBeginX, mappingBeginY, mappingWidth, mappingHeight );
		
		ledStripList.add(strip);
		
		return this;
	}
	
	public void updateTexture( PGraphics texture )
	{
		if( ledStripList.size() == 0 )
		{
			log.print(LoggerJay.ERROR, "No LED Strips allocated");
		}
		
		for( LEDStrip strip : ledStripList )
		{
			strip.update( texture );
		}
	}
	
	public void drawPreview( PApplet parent, float offsetX, float offsetY, int width, int height )
	{
		float spacing = 10.0f;
		
		for( LEDStrip strip : ledStripList )
		{
			strip.draw(parent, width, height);
			parent.translate(0, spacing);
		}
	}
	
	private ArrayList<LEDStrip> ledStripList = new <LEDStrip>ArrayList();
	private LoggerJay log;// = LoggerJay.getInstance();
	private String ipAddress = "";
}