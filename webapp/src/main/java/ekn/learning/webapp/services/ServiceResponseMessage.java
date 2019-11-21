package ekn.learning.webapp.services;

import java.time.LocalDateTime;

//TODO: Add timestamp in response
public class ServiceResponseMessage {
	private String operation;
	private String status;
	private LocalDateTime messageTimeStamp; 

	public ServiceResponseMessage() {
		this("Unknown Operation", "unknown status");
	}
	
	public ServiceResponseMessage(String operation, String status) {
		this.operation = operation;
		this.status = status;
		messageTimeStamp = LocalDateTime.now();
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getMessageTimeStamp() {
		return messageTimeStamp;
	}

	public void setMessageTimeStamp(LocalDateTime messageTimeStamp) {
		this.messageTimeStamp = messageTimeStamp;
	}
	
	
	
}
