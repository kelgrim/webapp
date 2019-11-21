package ekn.learning.webapp.services;


//TODO: Add timestamp in response
public class ServiceResponseMessage {
	private String operation;
	private String status;

	public ServiceResponseMessage() {
		this("Unknown Operation", "unknown status");
	}
	
	public ServiceResponseMessage(String operation, String status) {
		this.operation = operation;
		this.status = status;
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
	
	
	
}
