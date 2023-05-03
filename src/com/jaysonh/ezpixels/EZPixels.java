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

public class EZPixels {
	
	private PApplet parent;
	
	private int numStrips;
	private int ledPerStrip;
	private PGraphics texture;
	
	public final static String VERSION = "##library.prettyVersion##";
	

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the Library.
	 * 
	 * @example Hello
	 * @param theParent the parent PApplet
	 */
	public EZPixels(PApplet parent) {
		this.parent = parent;

	}
	
	public EZPixels(PApplet parent, int numStrips, int ledPerStrip)
	{
		this.parent   = parent;
		this.numStrips = numStrips;
		this.ledPerStrip = ledPerStrip;
	}
	
	public PixelControllerHub addController(String ipAddress){
		PixelControllerHub hub = new PixelControllerHub(ipAddress);
				
		pixelHubList.add(hub);
		return hub;
	}
	
	public void update(PGraphics texture)
	{	
		this.texture = texture;
		
		setLEDSFromTexture();
	}
	
	public void drawPreview(int w, int h)
	{
		for(int i = 0; i < pixelHubList.size();i++)
		{
			float offsetX = 0.0f;
			float offsetY = parent.map( i, 0, pixelHubList.size(), 0, w );
			
			parent.pushMatrix();
			parent.translate(offsetX, offsetY);
			pixelHubList.get(i).drawPreview( parent, offsetX, offsetY, w, h );
			parent.popMatrix();
		}
	}
	
	/**
	 * 
	 * @param xStride
	 * @param yStride
	 * @param texWidth
	 * @param texHeight
	 * 
	 * Assumes that LED strips are placed vertically
	 */
	void setLEDMapping( int xStride, int yStride, int texWidth, int texHeight)
	{
		/*if( getNumStrips() > 0 && getLEDSPerStrip() > 0)
		{
			for(int x = 0; x < texWidth; x+= xStride )
			{
				for(int y = 0; x < texHeight; y+= yStride )
				{
					
				}
			}
		}*/
	}
	
	/*public int getNumStrips()
	{
		return ledStrips.size();
	}
	
	public int getLEDSPerStrip()
	{
		if( ledStrips.size() > 0)
			return ledStrips.get(0).getNumLEDS();
		else
			return 0;
	}*/
	
	void setLEDSFromTexture()
	{
		for( PixelControllerHub hub : pixelHubList )
		{
			hub.updateTexture( texture );
		}
	}
	
	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
	
	public PixelControllerHub getHub( int hubIndx )
	{
		if( hubIndx >= 0 && hubIndx < pixelHubList.size() )
		{
			return pixelHubList.get( hubIndx );	
		}else
		{
			System.err.println("Invalid hub index, " + hubIndx + " is out of range 0 - " + pixelHubList.size());
			return null;
		}
	}
	private ArrayList<PixelControllerHub> pixelHubList = new <PixelControllerHub> ArrayList();
}

