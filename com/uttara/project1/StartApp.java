package com.uttara.project1;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

public class StartApp {
	public static void main(String[] args)
	{
	
	

		try {
			Scanner sc1 = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);

			int ch1 = 0,ch2=0,ch4=0,ch6=0,ch7 =0,ch5=0,ch8=0;
			String catName = null,taskName = null,desc,tags,splDt;
			int priority = 0,i=0;
			boolean test = false;
			TaskModel model = new TaskModel();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			while(ch1!=7)
			{
				System.out.println("");
				System.out.println("Press 1 to create Category");
				System.out.println("Press 2 to load Category");
				System.out.println("Press 3 to remove Category");
				System.out.println("Press 4 to list Category");
				System.out.println("Press 5 to search Category");
				System.out.println("Press 6 to export");
				System.out.println("Press 7 to exit");
				while(!sc1.hasNextInt()) {
					System.out.println("Not a number no chars are allowed enter only number");
					sc1.next();
				}
				ch1 = sc1.nextInt();
				switch(ch1)
				{
				case 1:
					System.out.println("Creating category....");
					System.out.println("Enter category name: ");
					catName = sc2.nextLine();
					while(!TaskUtil.validateName(catName))
					{
						System.out.println("Category name must be single word,start with letter,only alphaneumeric...enter category name");
						catName = sc2.nextLine();
					}
					if(model.checkCategoryExists(catName))
					{
						System.out.println("you duplicate,enter unique catergory");
					}
					else
					{
						while(ch2!=6)
						{
							System.out.println("Press 1 to creat task");
							System.out.println("Press 2 to update task");
							System.out.println("Press 3 to remove task");
							System.out.println("Press 4 to list task");
							System.out.println("Press 5 to search task");
							System.out.println("Press 6 to go back");

							while(!sc1.hasNextInt()) {
								System.out.println("Not a number no chars are allowed enter only number");
								sc1.next();
							}
							ch2 = sc1.nextInt();

							switch(ch2)
							{
							case 1:
								System.out.println("creating task....");
								System.out.println("Enter task name:");
								taskName = sc2.nextLine();
								while(!TaskUtil.validateTaskName(taskName))
								{
									System.out.println("it should not be null and empty");
									taskName = sc2.nextLine();
								}
								System.out.println("Enter description:");
								desc = sc2.nextLine();
								while(!TaskUtil.validateDescName(desc))
								{
									System.out.println("it should not be null and empty");
									desc = sc2.nextLine();
								}
								System.out.println("Enter tags(comma seperated tags)");
								tags = sc2.nextLine();
								while(!TaskUtil.validateTags(tags))
								{
									System.out.println("it should not be null and empty");
									tags = sc2.nextLine();
								}
								System.out.println("enter planned end date (dd/MM/yyyy)");
								splDt = sc2.nextLine();
								while(!TaskUtil.validateDate(splDt))
								{
									System.out.println("it should be in this format dd/MM/yyyy"+"\n"+"check whether it is more than current date and with valid date and month  ");
									splDt = sc2.nextLine();
								}

								do
								{
									System.out.println("Enter priority(1-very low,10-very high):");
									if(sc1.hasNextInt())
									{
										priority = sc1.nextInt();
										test =true;
									}
									if(priority>0 && priority<11)
									{
										test=true;
									}
									else 
									{
										System.out.println("Not a number no chars are allowed enter only number"+"\n"+"It should be between 1 and 10");
										test=false;
										sc1.next();
									}
								}
								while(test==false);


								//priority = sc1.nextInt();


								TaskBean bean = new TaskBean(taskName,desc,tags,splDt,priority);
								String result = model.addTask(bean,catName);
								if(result.equals(Constant.SUCCESS))
									System.out.println("Task "+taskName+" got added succesfully");
								else
									System.out.println("Task addition failed..msg is"+result);
								break;

							case 2:
								System.out.println("Updating ");
								model.loadCategory();
								System.out.println("Enter catName: ");
								catName = sc2.nextLine();
								while(!TaskUtil.validateName(catName))
								{
									System.out.println("Category name must be single word,start with letter,only alphaneumeric...enter category name");
									catName = sc2.next();
								}
								List<TaskBean> tasks= model.getTasks(catName);
								if(tasks.size()>0)
								{
								for( i=0; i<tasks.size(); i++)
								{
								
									System.out.println(i+")"+tasks.get(i).getTaskName());	
								}
									
								}
								else
									throw new IllegalArgumentException("Task doesnt exists in this category ");
								System.out.println( " enter no of task to be edit ");
								do
								{

									if(sc1.hasNextInt())
									{
										ch5=sc1.nextInt();										
										test =true;
									}
									if(ch5<=i)
									{
										test=true;
									}
									
									else 
									{
										System.out.println("Not a number no chars are allowed enter only above number");
										test=false;
										sc1.next();
									}
								}
								while(test==false);
								TaskBean tb=tasks.get(ch5);

								tasks.remove(ch5);


								while(ch6!=6)
								{
									System.out.println(" enter 1 to update name ");
									System.out.println(" enter 2 to update desc  ");
									System.out.println(" enter 3 to update tags  ");
									System.out.println(" enter 4 to update date  ");
									System.out.println(" enter 5 to priority  ");
									System.out.println(" enter 6 to go back and update task ");
									System.out.println( "");

									System.out.println( " enter no  to be edit ");
									while(!sc1.hasNextInt()) {
										System.out.println("Not a number no chars are allowed enter only number");
										sc1.next();
									}
									ch6=sc1.nextInt();

									switch(ch6)
									{
									case 1:
										System.out.println(" enter new name ");
										String nn=sc2.nextLine();
										tb.setTaskName(nn);

										break;
									case 2:
										System.out.println(" enter new desc ");
										String nd = sc2.nextLine();
										tb.setDesc(nd);

										break;

									case 3:
										System.out.println(" enter new tags ");
										String nt= sc2.nextLine();
										while(!TaskUtil.validateTags(nt))
										{
											System.out.println("it should not be null and empty");
											nt = sc2.nextLine();
										}
										tb.setDesc(nt);

										break;

									case 4:
										System.out.println(" enter new date in dd/MM/yyyy");
										String nda = sc2.nextLine();

										Date d = sdf.parse(nda);

										tb.setPlannedDate(d);

										break;

									case 5:
										System.out.println(" enter new priority ");

										do
										{
											System.out.println("Enter priority(1-very low,10-very high):");
											if(sc1.hasNextInt())
											{
												priority = sc1.nextInt();
												test =true;
											}
											if(priority>0 && priority<11)
											{
												test=true;
											}
											else 
											{
												System.out.println("Not a number no chars are allowed enter only number"+"\n"+"It should be between 1 and 10");
												test=false;
												sc1.next();
											}
										}
										while(test==false);
										tb.setPriority(priority);
										break;


									case 6:

										tasks.add(tb);
										model.updateTask(tasks, catName);
										System.out.println(" Task updated succesfully");
										break;

									default:
										System.out.println("option not supported yet");
										break;
									}
								}

								break;

							case 3:
								System.out.println("removing task....");
								model.loadCategory();
								System.out.println("Enter catName: ");
								catName = sc2.nextLine();
								while(!TaskUtil.validateName(catName))
								{
									System.out.println("Category name must be single word,start with letter,only alphaneumeric...enter category name");
									catName = sc2.next();
								}
								List<TaskBean> tasks1= model.getTasks(catName);
								if(tasks1.size()>0)
								{
								for( i=0; i<tasks1.size(); i++)
								{
								
									System.out.println(i+")"+tasks1.get(i).getTaskName());	
								}
									
								}
								else
									throw new IllegalArgumentException("Task doesnt exists in this category ");
								System.out.println( " enter no of task to be delete ");
								do
								{

									if(sc1.hasNextInt())
									{
										ch5=sc1.nextInt();										
										test =true;
									}
									if(ch5<=i)
									{
										test=true;
									}
									
									else 
									{
										System.out.println("Not a number no chars are allowed enter only above number");
										test=false;
										sc1.next();
									}
								}
								while(test==false);

								tasks1.remove(ch5);
								System.out.println( "Removed Task");
								model.updateTask(tasks1, catName);
								break;


							case 4:
								System.out.println(" Printing List of tasks");
								System.out.println("");
								while(ch7!=4)
								{
									System.out.println("Enter 1 to nameWise list");
									System.out.println("Enter 2 to dueDatewise list");
									System.out.println("Enter 3 to prioritywise list");
									System.out.println("Enter 4 to go back");
									while(!sc1.hasNextInt()) {
										System.out.println("Not a number.....no chars are allowed enter only above number");
										sc1.next();
									}
									ch7 = sc1.nextInt();
									switch(ch7)
									{
									case 1 :
										System.out.println(" NameWise listing");
										model.loadCategory();
										System.out.println("Enter cat name:");
										catName = sc2.nextLine();
										List<TaskBean> task1 = model.getTasks(catName);
										System.out.println(task1);
										break;

									case 2:
										System.out.println(" DueDateWise listing");
										model.loadCategory();
										System.out.println("Enter cat name:");
										catName = sc2.nextLine();
										List<TaskBean> tasks12= model.getTasks(catName);
										TreeSet<TaskBean> st1 = new TreeSet<TaskBean>(new SortDueDate());
										st1.addAll(tasks12);
										for(TaskBean b:st1)
										{
											System.out.println(b);
										}

										break;


									case 3:
										System.out.println(" PriorityWise listing");
										model.loadCategory();
										System.out.println("Enter cat name:");
										catName = sc2.nextLine();
										List<TaskBean> tasks11= model.getTasks(catName);
										TreeSet<TaskBean> st = new TreeSet<TaskBean>(new SortPriority());
										st.addAll(tasks11);
										for(TaskBean b:st)
										{
											System.out.println(b);
										}

										break;

									case 4:
										System.out.println(" Going back");

										break;
									default:
										System.out.println("option not supported yet");
										break;
									}
									break;
								}
								break;

							case 5:

								System.out.println("Enter part name to search");
								String search = sc2.nextLine();
								List<TaskBean> bean1= model.getTasks(catName);
								Map<String,Integer> word = model.searchTask1(search,bean1);
								System.out.println("Match found = "+word);
								break;

							case 6:
								System.out.println("Going back");
								break;
							default:
								System.out.println("option not supported yet");
								break;
							}
						}
					}
					break;

				case 2:
					System.out.println("Loading category");
					model.loadCategory();
					System.out.println("Enter catName:");
					catName= sc2.nextLine();
					while(!model.checkCategoryExists(catName))
					{
						System.out.println("you duplicate,enter existing catergory");
						catName = sc2.next();
					}

					while(ch2!=6)
					{
						System.out.println("Press 1 to creat task");
						System.out.println("Press 2 to update task");
						System.out.println("Press 3 to remove task");
						System.out.println("Press 4 to list task");
						System.out.println("Press 5 to search task");
						System.out.println("Press 6 to go back");

						while(!sc1.hasNextInt()) {
							System.out.println("Not a number no chars are allowed enter only number");
							sc1.next();
						}
						ch2 = sc1.nextInt();

						switch(ch2)
						{
						case 1:
							System.out.println("creating task....");
							System.out.println("Enter task name:");
							taskName = sc2.nextLine();
							while(!TaskUtil.validateTaskName(taskName))
							{
								System.out.println("it should not be null and empty");
								taskName = sc2.nextLine();
							}
							System.out.println("Enter description:");
							desc = sc2.nextLine();
							while(!TaskUtil.validateDescName(desc))
							{
								System.out.println("it should not be null and empty");
								desc = sc2.nextLine();
							}
							System.out.println("Enter tags(comma seperated tags)");
							tags = sc2.nextLine();
							while(!TaskUtil.validateTags(tags))
							{
								System.out.println("it should not be null and empty");
								tags = sc2.nextLine();
							}
							System.out.println("enter planned end date (dd/MM/yyyy)");
							splDt = sc2.nextLine();
							while(!TaskUtil.validateDate(splDt))
							{
								System.out.println("it should be in this format dd/MM/yyyy"+"\n"+"check whether it is more than current date and with valid date and month  ");
								splDt = sc2.nextLine();
							}

							do
							{
								System.out.println("Enter priority(1-very low,10-very high):");
								if(sc1.hasNextInt())
								{
									priority = sc1.nextInt();
									test =true;
								}
								if(priority>0 && priority<11)
								{
									test=true;
								}
								else 
								{
									System.out.println("Not a number no chars are allowed enter only number"+"\n"+"It should be between 1 and 10");
									test=false;
									sc1.next();
								}
							}
							while(test==false);


							TaskBean bean = new TaskBean(taskName,desc,tags,splDt,priority);
							String result = model.addTask(bean,catName);
							if(result.equals(Constant.SUCCESS))
								System.out.println("Task "+taskName+" got added succesfully");
							else
								System.out.println("Task addition failed..msg is"+result);
							break;

						case 2:
							System.out.println("updating ");
							
							List<TaskBean> tasks1= model.getTasks(catName);
							if(tasks1.size()>0)
							{
							for( i=0; i<tasks1.size(); i++)
							{
							
								System.out.println(i+")"+tasks1.get(i).getTaskName());
								
									
							}
								
							}
							else
								throw new IllegalArgumentException("Task doesnt exists in this category ");
							do
							{
								System.out.println( "Enter no of task to be edit ");
								if(sc1.hasNextInt())
								{
									ch5 = sc1.nextInt();
									test =true;
								}
								if(ch5<=tasks1.size())
								{
									test=true;
								}
								
								else 
								{
									System.out.println("Chars are not allowed enter only above number");
									test=false;
									//sc1.next();
								}
							}
							while(test==false);


							TaskBean tb=tasks1.get(ch5);

							tasks1.remove(ch5);


							while(ch6!=6)
							{
								System.out.println(" enter 1 to update name ");
								System.out.println(" enter 2 to update desc  ");
								System.out.println(" enter 3 to update tags  ");
								System.out.println(" enter 4 to update date  ");
								System.out.println(" enter 5 to priority  ");
								System.out.println(" enter 6 to go back and update task ");
								System.out.println( "");

								System.out.println( " enter no  to be edit ");
								while(!sc1.hasNextInt()) {
									System.out.println("Not a number no chars are allowed enter only number");
									sc1.next();
								}
								ch6=sc1.nextInt();

								switch(ch6)
								{
								case 1:
									System.out.println(" enter new name ");
									String nn=sc2.nextLine();
									tb.setTaskName(nn);

									break;
								case 2:
									System.out.println(" enter new desc ");
									String nd = sc2.nextLine();
									tb.setDesc(nd);

									break;

								case 3:
									System.out.println(" enter new tags ");
									String nt= sc2.nextLine();
									tb.setDesc(nt);

									break;

								case 4:
									System.out.println(" enter new date in dd/MM/yyyy");
									splDt = sc2.nextLine();
									while(!TaskUtil.validateDate(splDt))
									{
										System.out.println("it should be in this format dd/MM/yyyy"+"\n"+"check whether it is more than current date and with valid date and month  ");
										splDt = sc2.nextLine();
									}
									Date d = sdf.parse(splDt);
									tb.setPlannedDate(d);

									break;

								case 5:
									System.out.println(" enter new priority ");
									do
									{
										System.out.println("Enter priority(1-very low,10-very high):");
										if(sc1.hasNextInt())
										{
											priority = sc1.nextInt();
											test =true;
										}
										if(priority>0 && priority<11)
										{
											test=true;
										}
										else 
										{
											System.out.println("Not a number no chars are allowed enter only number"+"\n"+"It should be between 1 and 10");
											test=false;
											sc1.next();
										}
									}
									while(test==false);
									tb.setPriority(priority);
									break;


								case 6:

									tasks1.add(tb);
									model.updateTask(tasks1, catName);
									System.out.println(" Task updated succesfully");

								default:
									System.out.println("option not supported yet");
									break;
								}
							}

							break;

						case 3:
							System.out.println("removing task....");
							
							List<TaskBean> tasks= model.getTasks(catName);
							if(tasks.size()>0)
							{
								for( i=0; i<tasks.size(); i++)
								{
									System.out.println(i+")"+tasks.get(i).getTaskName());
								}
								
							}
							
							
							System.out.println( " enter no of task to be delete ");
							do
							{
								if(sc1.hasNextInt())
								{
									ch5=sc1.nextInt();										
									test =true;
								}
								if(ch5<=i)
								{
									test=true;
								}

								else 
								{
									System.out.println("Not a number no chars are allowed enter only above number");
									test=false;
									sc1.next();
								}
							}
							while(test==false);

							tasks.remove(ch5);
							System.out.println( " Removed Task");
							model.updateTask(tasks, catName);
							break;


						case 4:
							System.out.println(" Printing List of tasks");
							System.out.println("");
							while(ch7!=4)
							{
								System.out.println("Enter 1 to nameWise list");
								System.out.println("Enter 2 to dueDatewise list");
								System.out.println("Enter 3 to prioritywise list");
								System.out.println("Enter 4 to go back");
								while(!sc1.hasNextInt()) {
									System.out.println("Not a number.....no chars are allowed enter only above number");
									sc1.next();
								}
								ch7 = sc1.nextInt();
								switch(ch7)
								{
								case 1 :
									System.out.println(" NameWise listing");
									model.loadCategory();
									System.out.println("Enter cat name:");
									catName = sc2.nextLine();
									List<TaskBean> task1 = model.getTasks(catName);
									System.out.println(task1);
									break;

								case 2:
									System.out.println("DueDateWise listing");
									model.loadCategory();
									System.out.println("Enter cat name:");
									catName = sc2.nextLine();

									while(!TaskUtil.validateName(catName))
									{
										System.out.println("Category name must be single word,start with letter,only alphaneumeric...enter category name");
										catName = sc2.nextLine();
									}
									List<TaskBean> tasks12= model.getTasks(catName);
									TreeSet<TaskBean> st1 = new TreeSet<TaskBean>(new SortDueDate());
									st1.addAll(tasks12);
									for(TaskBean b:st1)
									{
										System.out.println(b);
									}

									break;


								case 3:
									System.out.println("PriorityWise listing");
									model.loadCategory();
									System.out.println("Enter cat name:");
									catName = sc2.nextLine();
									while(!TaskUtil.validateName(catName))
									{
										System.out.println("Category name must be single word,start with letter,only alphaneumeric...enter category name");
										catName = sc2.nextLine();
									}
									List<TaskBean> tasks11= model.getTasks(catName);
									TreeSet<TaskBean> st = new TreeSet<TaskBean>(new SortPriority());
									st.addAll(tasks11);
									for(TaskBean b:st)
									{
										System.out.println(b);
									}

									break;

								case 4:
									System.out.println(" Going back");

									break;
								default:
									System.out.println("option not supported yet");
									break;
								}
                                break;
							}
							break;

						case 5:

							System.out.println("Enter part name to search");
							String search = sc2.nextLine();
							List<TaskBean> bean1= model.getTasks(catName);
							Map<String,Integer> word = model.searchTask1(search,bean1);
							System.out.println("Match found = "+word);
							break;

						case 6:
							System.out.println("Going back");
							break;
						default:
							System.out.println("option not supported yet");
							break;

						}
					}
					break;

				case 3:
					System.out.println("removing category");
					model.loadCategory();
					System.out.println("");
					System.out.println("Enter file name to delete");
					catName= sc2.nextLine();
					while(!model.checkCategoryExists(catName))
					{
						System.out.println("you duplicate,enter existing catergory");
						catName = sc2.next();
					}
					
					model.delCategory(catName);



					break;

				case 4:
					System.out.println("listing category");
					model.loadCategory();
					break;

				case 5:
					System.out.println("searching category");
					System.out.println("Enter file name to search");
					catName= sc2.next();
					String s = model.searchCategory(catName);
					System.out.println(s);

					break;

				case 6:
					System.out.println("exporting category");

					break;

				case 7:
					System.out.println("Tata bye..");
					break;
				default:
					System.out.println("option not supported yet");
					break;
				}
			}
		}
		catch(IllegalArgumentException t)
		{
			t.getMessage();
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}

	}

}


