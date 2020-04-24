package com.uttara.project1;

import java.io.BufferedReader;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TaskModel {
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public List<TaskBean> getTasks(String catName) throws NumberFormatException, IOException, ParseException
	{
		BufferedReader br = null;
		String line;
		try
		{
			List<TaskBean> tasks = new ArrayList<TaskBean>();
			TaskBean task;
			br = new BufferedReader(new FileReader(catName+".todo"));

			while((line = br.readLine())!= null)
			{
				String[] sa = line.split(":");
				task = new TaskBean(sa[0],sa[1],sa[2],sa[3],Integer.parseInt(sa[4]));
				tasks.add(task);
			}
			return tasks;
		}

		finally
		{
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public void delCategory(String catName) throws IOException
	{

		File f = new File("/home/uttara/eclipse-workspace/SecondApp");
		if(f.exists()&& f.isDirectory())
		{
			File[] fa = f.listFiles();
			for(File fi:fa)
			{
				if(fi.getName().contains(catName+".todo"))

					if(fi.delete())
					{
						System.out.println(" deleted");
					}
					else
						System.out.println(" not deleted");



			}
		}
	}

	public String searchCategory(String catName) throws IOException
	{

		File f = new File("/home/uttara/eclipse-workspace/SecondApp");
		if(f.exists()&& f.isDirectory())
		{
			File[] fa = f.listFiles();
			for(File fi:fa)
			{
				if(fi.getName().contains(catName+".todo"))
					return "Match found "+catName;


			}
		}
		return "Match not found for this category = "+catName;
	}


	public void loadCategory() throws IOException
	{


		File f = new File("/home/uttara/eclipse-workspace/SecondApp");
		if(f.exists()&& f.isDirectory())
		{
			File[] fa = f.listFiles();
			for(File fi:fa)
			{
				if(fi.getName().contains(".todo"))
				{
					BufferedReader br =null;

					try
					{
						br = new BufferedReader(new FileReader(fi));
						System.out.println("printing file name  "+fi.getName());

					}
					finally
					{
						if(br!=null)
							br.close();
					}


				}
			}
		}
	}

	public String searchTask(String partName,List<TaskBean> al) throws IOException
	{

		int count=0,tCount = 0,taskc = 0,dCount=0,tagCount=0;


		for(TaskBean  word:al)
		{
			if(word.getTaskName().contains(partName))
				taskc++;
			System.out.print("Number of occ in taskname = "+taskc+"\n");
			if(word.getDesc().contains(partName))
				dCount++;
			System.out.print("Number of occ in desc = "+dCount+"\n");

			if(word.getTags().contains(partName))
				tagCount++;
			System.out.print("Number of occ in tasgs  = "+tagCount+"\n");

		}

		return partName+" "+tCount;
	}

	public Map<String,Integer> searchTask1(String partName,List<TaskBean> al) throws IOException
	{
		Map<String,Integer> hm = new TreeMap<String,Integer>();

		int c1=0,c2=0,c3=0,c4=0,c5=0;
		for(TaskBean tb:al)
		{
			if(wordOcc(tb.getTaskName(),partName)>=1)
			{
				c1=c1+wordOcc(tb.getTaskName(),partName);
				hm.put("2) in Task Name = ", c1);
			}

			if(wordOcc(tb.getDesc(),partName)>=1)
			{
				c2 = c2+wordOcc(tb.getDesc(),partName);
				hm.put(" 3) in Task Desc = ", c2);
			}
			if(wordOcc(tb.getTags(),partName)>=1)
			{
				c3 = c3+wordOcc(tb.getTags(),partName);
				hm.put("4)in tags = ", c3);
			}
		}
		hm.put(" 1) Total No of Occ = ", c1+c2+c3);

		
		return hm;
	}



	public String addTask(TaskBean task,String catName)
	{
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter(catName+".todo",true));
			bw.write(task.toString());
			bw.newLine();

			return Constant.SUCCESS;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return "oops something bad happened msg"+e.getMessage();
		}
		finally
		{
			if(bw!=null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public boolean checkCategoryExists(String catName)
	{
		return new File(catName+".todo").exists();
	}


	public void updateTask(List<TaskBean> al,String catName) throws IOException
	{
		BufferedWriter bw = null;


		bw=new BufferedWriter(new FileWriter(catName+".todo"));
		for(TaskBean task:al)
		{
			bw.write(task.toString());
			bw.newLine();
		}
		bw.close();



	}


	public int wordOcc(String sen, String word)
	{

		int c=-1;
		int i=0;
		int p=0;
		while(i>=0)
		{
			i=sen.indexOf(word,p);
			p=i+word.length();
			c++;

		}
		return c;


	}
}