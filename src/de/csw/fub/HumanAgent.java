package de.csw.fub;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import de.csw.fub.util.ProjectUtil;

public class HumanAgent {

	private Document getDocOfHTSDB() {
		try {
			return new SAXReader().read(new File(ProjectUtil.getHTSDBPath()));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void write(Document document) throws IOException {

        // lets write to a file
        XMLWriter writer = new XMLWriter(
            new FileWriter(ProjectUtil.getHTSDBPath()));
        writer.write( document );
        writer.close();
    }


	public List getAllTasks() {
		List tasks = new ArrayList();
		List elements = getDocOfHTSDB().getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			tasks.add(parseElement(ele));
		}

		return tasks;
	}
	
	public Task getTask(String id) {
		List elements = getDocOfHTSDB().getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			if(ele.elementText("id").equalsIgnoreCase(id))
			return parseElement(ele);
		}
		return null;
	}
	
	public void updateTask(String id, String solver, String solution) {
		Document doc = getDocOfHTSDB();
		List elements = doc.getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			if(ele.elementText("id").equalsIgnoreCase(id)){
				ele.element("actualSolver").setText(solver);
				ele.element("solution").setText(solution);
				ele.element("state").setText("1");
				ele.element("resolvedTime").setText(new Date().toLocaleString());
			}
		}
		
		try {
			write(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List getAllCompletedTasks() {
		List tasks = new ArrayList();
		List elements = getDocOfHTSDB().getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			if (ele.elementText("state")!= null && ele.elementText("state").equalsIgnoreCase("1")) {
				tasks.add(parseElement(ele));
			}
		}
		return tasks;
	}
	
	public List getAllIncompletedTasks() {
		List tasks = new ArrayList();
		List elements = getDocOfHTSDB().getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			if (ele.elementText("state")!= null && ele.elementText("state").equalsIgnoreCase("0")) {
				tasks.add(parseElement(ele));
			}
		}
		return tasks;
	}
	
	public List getAllExceptions() {
		List exceptions = new ArrayList();
		List elements = getDocOfHTSDB().getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			if (ele.attributeValue("type").equalsIgnoreCase("Exception"))
				exceptions.add(parseElement(ele));
		}
		return exceptions;
	}
	
	public List getSolvedExceptions() {
		List exceptions = new ArrayList();
		List elements = getDocOfHTSDB().getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			if (ele.attributeValue("type").equalsIgnoreCase("Exception") && ele.elementText("state").equalsIgnoreCase("1")) {
				exceptions.add(parseElement(ele));
			}
		}
		return exceptions;
	}
	
	public List getUnsolvedExceptions() {
		List exceptions = new ArrayList();
		List elements = getDocOfHTSDB().getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			if (ele.attributeValue("type").equalsIgnoreCase("Exception") && ele.elementText("state").equalsIgnoreCase("0")) 
				exceptions.add(parseElement(ele));
		}
		return exceptions;
	}
	
	public List getAllHumanTasks() {
		List humanTasks = new ArrayList();
		List elements = getDocOfHTSDB().getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			if (ele.attributeValue("type").equalsIgnoreCase("Human Task")) 
				humanTasks.add(parseElement(ele));
		}
		return humanTasks;
	}
	
	public List getCompletedHumanTasks() {
		List humanTasks = new ArrayList();
		List elements = getDocOfHTSDB().getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			if (ele.attributeValue("type").equalsIgnoreCase("Human Task") && ele.elementText("state").equalsIgnoreCase("1")) 
				humanTasks.add(parseElement(ele));
		}
		return humanTasks;
	}
	
	public List getIncompletedHumanTasks() {
		List humanTasks = new ArrayList();
		List elements = getDocOfHTSDB().getRootElement().elements();
		for (int i = 0; i < elements.size(); i++) {
			Element ele = (Element) elements.get(i);
			if (ele.attributeValue("type").equalsIgnoreCase("Human Task") && ele.elementText("state").equalsIgnoreCase("0")) 
				humanTasks.add(parseElement(ele));
		}
		return humanTasks;
	}
	
	private Task parseElement(Element ele){
		Task task = new Task();
		try {
			task.setReceivedTime(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").parse(ele
					.elementText("receivedTime")));
			task.setResolvedTime(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").parse(ele
					.elementText("resolvedTime")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		task.setSender(ele.elementText("sender"));
		task.setWorkflowName(ele.elementText("workflowName"));
		task.setPayload(ele.elementText("payload"));
		
		task.setActualSolver(ele.elementText("actualSolver"));
		task.setExpectedSolver(ele.elementText("expectedSolver"));
		task.setType(ele.attributeValue("type"));
		task.setId(ele.elementText("id"));
		task.setCid(ele.elementText("cid"));
		task.setSolution(ele.elementText("solution"));
		
		if(task.getType().equalsIgnoreCase("Exception")){
			if(ele.elementText("state").equalsIgnoreCase("1"))
			task.setState("Solved");
			else if(ele.elementText("state").equalsIgnoreCase("0"))
				task.setState("Pending");
		}
		
		if(task.getType().equalsIgnoreCase("Human Task")){
			if(ele.elementText("state").equalsIgnoreCase("1"))
			task.setState("Completed");
			else if(ele.elementText("state").equalsIgnoreCase("0"))
				task.setState("Incompleted");
		}
		
		return task;
	}
	
	
}
