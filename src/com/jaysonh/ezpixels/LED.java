package com.jaysonh.ezpixels;

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

public class LED {

	public LED( int ledType )
	{
		init();
		this.ledType = ledType;		
	}
	
	public LED()
	{
		init();
	}
	
	public void init()
	{
		r = g = b = w = 0;
		mapping = 0;
		ledType = 0;
	}
	
	public void setMapping( int mapX, int mapY )
	{
		this.mapX = mapX;
		this.mapY = mapY;
	}
	
	public int getMapping()
	{
		return mapping;
	}
	
	public int getX()
	{
		return mapX;
	}
	
	public int getY()
	{
		return mapY;
	}
	
	public void setCol( int col )
	{
		int redValue   = (col >> 16) & 0xFF;
		int greenValue = (col >> 8 ) & 0xFF;
		int blueValue  =  col 		 & 0xFF;

		setRGBW( redValue, greenValue, blueValue, 0);
	}
	
	public void setRGBW( int r, int g, int b, int w )
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.w = w;
	}
	
	public int getCol(PApplet app)
	{
		return app.color(r,g,b);
	}
	
	private int ledType = 0;
	private int mapping = 0;
	private int r,g,b,w;
	private int mapX, mapY;
}