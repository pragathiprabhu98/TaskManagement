package com.uttara.project1;

import java.util.Comparator;



public class SortDueDate implements Comparator<TaskBean> {

	@Override
	public int compare(TaskBean o1, TaskBean o2) {
		return o1.getPlannedDate().compareTo(o2.getPlannedDate());

	}

}