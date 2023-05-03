package com.jaysonh.ezpixels;

import java.util.HashMap;
import java.util.ArrayList;
import processing.core.*;

public class LoggerJay
{
	private static LoggerJay instance;
	
	private LoggerJay()
	{
		mode = NONE;
	}
	
	
	public static LoggerJay getInstance()
	{
		if(instance == null)
		{
			instance = new LoggerJay();
		}
		return instance;
	}
	
	public void setMode(int mode)
	{
		this.mode = mode;
	}
	
	public void print(int printMode, String txt )
	{
		if(printMode >= mode )
		{
			System.out.println(txt);
		}
	}
	
	public static final int NONE    = 0;
	public static final int VERBOSE = 1;
	public static final int ERROR   = 2;
	
	private int mode = NONE;
}