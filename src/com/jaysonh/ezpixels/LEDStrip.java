package com.jaysonh.ezpixels;

import java.util.HashMap;
import java.util.ArrayList;
import processing.core.*;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 * @example Hello 
 */

public class LEDStrip {
	

	LEDStrip( int ledType, int numLeds, int mappingBeginX, int mappingBeginY, int mappingWidth, int mappingHeight )
	{
		log = LoggerJay.getInstance();
		this.ledType = ledType;
		this.numLeds = numLeds;
		for(int i =0; i < numLeds;i++)
		{
			ledsList.add( new LED( ledType ) );
		}
		
		setMapping( mappingBeginX, mappingBeginY, mappingWidth, mappingHeight );
	}
	
	public void draw(PApplet parent, int w, int h)
	{
		parent.noStroke();

		for(int i =0; i <ledsList.size();i++)
		{
			float x = parent.map(i,0,ledsList.size(), 0, w);
			float y = 0.0f;
			
			parent.fill( ledsList.get(i).getCol(parent) );
			parent.rect( x,  y,  prevSize,  prevSize);
		}
	}
	
	public void update(PGraphics texture)
	{
		for(LED led : ledsList )
		{
			int x = led.getX();
			int y = led.getY();
			
			led.setCol( texture.get(x,y) );
		}
	}
	
	public int getNumLEDS()
	{
		return ledsList.size();
	}
	
	public int getLedType()
	{
		return ledType;
	}
	
	public void clear()
	{
		ledsList.clear();
	}
	
	void setMapping( int beginX, int beginY, int width, int height )
	{
		float stridefX = Math.round( (float)( width - beginX )/(float)numLeds);
		int strideX = (int)stridefX;
		if( strideX < 1) strideX = 1;
		

		float stridefY = Math.round( (float)( height - beginY )/(float)numLeds);
		int strideY = (int)stridefY;
		if( strideY < 1) strideY = 1;
		
		for( int i = 0; i < numLeds;i++)
		{
			int indexX = beginX + strideX * i;
			int indexY = beginY;//beginY + strideY * i;
			
			if(indexX >= width)  indexX = width  - 1;
			if(indexY >= height) indexY = height - 1;
			log.print(LoggerJay.VERBOSE, "setting index: " + indexX + "," + indexY);
			
			ledsList.get(i).setMapping( indexX, indexY );
			//ledsPixelMap.put( index, ledsList.get(i) );
		}
	}
	
	private float prevSize = 5.0f;
	
	private int ledType;
	private int numLeds;
	private ArrayList<LED> ledsList = new ArrayList<LED>();
	private HashMap<Integer, LED> ledsPixelMap = new HashMap<Integer, LED>();
	
	private LoggerJay log;// = LoggerJay.getInstance();
}