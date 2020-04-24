package com.uttara.project1;
import java.util.*;



public class SortPriority  implements Comparator<TaskBean>{

	public int compare(TaskBean o1,TaskBean o2)
	{
		return o1.getPriority()-o2.getPriority();

	}

}
