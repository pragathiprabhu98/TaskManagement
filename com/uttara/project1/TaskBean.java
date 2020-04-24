package com.uttara.project1;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskBean{

	private String taskName;
	private String desc;
	private String tags;
	private Date plannedDate;
	private int priority;

	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

public TaskBean(String taskName, String desc, String tags, String plannedDate,int priority) throws ParseException {
	super();
	this.taskName = taskName;
	this.desc = desc;
	this.tags = tags;
	this.plannedDate = sdf.parse(plannedDate);
	this.priority = priority;
}

public TaskBean() {
	super();

}

@Override
public String toString() {
	return taskName+":"+desc+":"+tags+":"+sdf.format(plannedDate)+":"+priority+":"+sdf.format(new Date());
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((desc == null) ? 0 : desc.hashCode());
	result = prime * result + ((plannedDate == null) ? 0 : plannedDate.hashCode());
	result = prime * result + priority;
	result = prime * result + ((tags == null) ? 0 : tags.hashCode());
	result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	TaskBean other = (TaskBean) obj;
	if (desc == null) {
		if (other.desc != null)
			return false;
	} else if (!desc.equals(other.desc))
		return false;
	if (plannedDate == null) {
		if (other.plannedDate != null)
			return false;
	} else if (!plannedDate.equals(other.plannedDate))
		return false;
	if (priority != other.priority)
		return false;
	if (tags == null) {
		if (other.tags != null)
			return false;
	} else if (!tags.equals(other.tags))
		return false;
	if (taskName == null) {
		if (other.taskName != null)
			return false;
	} else if (!taskName.equals(other.taskName))
		return false;
	return true;
}
/*public TaskBean(String taskName, String desc, String tags, String plannedDate, int priority) {
	super();
	this.taskName = taskName;
	this.desc = desc;
	this.tags = tags;
	this.plannedDate = plannedDate;
	this.priority = priority;
}*/
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public String getTags() {
	return tags;
}
public void setTags(String tags) {
	this.tags = tags;
}
public Date getPlannedDate() {
	return plannedDate;
}
public void setPlannedDate(Date plannedDate) {
	this.plannedDate = plannedDate;
}
public int getPriority() {
	return priority;
}
public void setPriority(int priority) {
	this.priority = priority;
}

}

