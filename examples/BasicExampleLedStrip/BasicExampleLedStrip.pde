import com.jaysonh.ezpixels.*;

int textureWidth  = 400;
int textureHeight = 400;
 
EZPixels ezPixels;
PGraphics offscreen;
void setup()
{
    size(600,600); 
 
    
    offscreen = createGraphics(textureWidth, textureHeight);
    
    
    ezPixels = new EZPixels(this); 
    ezPixels.addController("192.168.0.1")
      .addStrip( LedType.NONE, 30, 5,  0, textureWidth, textureHeight )
      .addStrip( LedType.NONE, 30, 5, 20, textureWidth, textureHeight );
}
int t = 0;
void draw()
{
  background(0,125,234);
  t+=5;
  offscreen.beginDraw();
    offscreen.background(0,0,t%255);
    offscreen.endDraw();
  
  ezPixels.update( offscreen );
  ezPixels.drawPreview(textureWidth, textureHeight);
}