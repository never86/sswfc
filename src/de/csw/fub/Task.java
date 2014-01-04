/**
 * 
 */
package de.csw.fub;

import java.util.Date;

/**
 * @author never86
 * 
 */
public class Task {
	public Date receivedTime = new Date();
	public Date resolvedTime = new Date();
	public String workflowName = "-";
	public String payload = "-";
	public String solution = "-";
	public String actualSolver = "-";
	public String expectedSolver = "-";
	public String sender = "-";
	public String state = "0";
	public String id = "-";
	public String cid = "-";
	public String type = "";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public Date getResolvedTime() {
		return resolvedTime;
	}

	public void setResolvedTime(Date resolvedTime) {
		this.resolvedTime = resolvedTime;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getActualSolver() {
		return actualSolver;
	}

	public void setActualSolver(String actualSolver) {
		this.actualSolver = actualSolver;
	}

	public String getExpectedSolver() {
		return expectedSolver;
	}

	public void setExpectedSolver(String expectedSolver) {
		this.expectedSolver = expectedSolver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

}
