package ekn.learning.webapp.model;

import java.sql.Timestamp;

public class Message {
	private int id;
	private int senderId;
	private int recipientId;
	private String messageText;
	private Timestamp messageTimestamp;
	private boolean messageRead;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	public boolean isMessageRead() {
		return messageRead;
	}
	public void setMessageRead(boolean messageRead) {
		this.messageRead = messageRead;
	}
	//TODO: Change this to better format
	public Timestamp getMessageTimeStamp() {
		return messageTimestamp;
	}
	public void setMessageTimeStamp(Timestamp messageTimeStamp) {
		this.messageTimestamp = messageTimeStamp;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", senderId=" + senderId + ", recipientId=" + recipientId + ", messageText="
				+ messageText + ", messageTimestamp=" + messageTimestamp + ", messageRead=" + messageRead + "]";
	}
	
	
	
	
}
