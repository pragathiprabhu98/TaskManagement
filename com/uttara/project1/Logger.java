package com.uttara.project1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;



public class Logger {
	private String path="D:\\programjava\\Source\\1st.txt";
	private static final boolean devMode = true;

 public void log(String data)
 {
	 new Thread(new Runnable()
	 {
		 
	 public void run()
	 {
	Date dt=new Date();	 
	 BufferedWriter bw=null;
	 try
	 {
		 String msg=dt+":"+data;
		 bw=new BufferedWriter(new FileWriter(path,true));
		 bw.write(msg);
		 bw.newLine();
		 
		 if(Logger.devMode==true)
		 {
			 System.out.println("logger"+msg);
		 }
	 }
	 catch(IOException i)
	 {
		 i.printStackTrace();
	 }
	 finally
	 {
		 try
		 {
			 if(bw!=null)
				 bw.close();
		 }
		 catch(IOException i4)
		 {
			 i4.printStackTrace();
		 }
	 }
	 
 }
	 }).start();
 }
 
 private Logger()
 {
	 
 }
	private static Logger obj = null;
	
	public static Logger getInstance()
	{
		//System.out.println("in Logger getInstance(), obj = "+obj);
		if(obj == null)
			obj = new Logger();
		
		return obj;
	}
 
}
