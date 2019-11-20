package ekn.learning.webapp.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ekn.learning.webapp.exceptions.MessageInvalidArgumentsException;
import ekn.learning.webapp.exceptions.MessageNotFoundException;
import ekn.learning.webapp.exceptions.MessageNotFoundForUserException;
import ekn.learning.webapp.model.Message;

@Repository
public class MessageJdbcRepository {

	@Autowired
	 JdbcTemplate jdbcTemplate;
	
	public Message findMessageById(int messageId) {
		  try {
		    	return jdbcTemplate.queryForObject(""
		    			+ "select id,"
		    			+ "sender_id as senderId, "
		    			+ "recipient_id as recipientId, "
		    			+ "message_text as messageText, "
		    			+ "message_read as messageRead, "
		    			+ "message_timestamp as messageTimestamp "
		    			+ "from tbl_messages where id=?", 
		    
		    		new Object[] {
		    				messageId
		        	},
		    		new BeanPropertyRowMapper <Message> (Message.class));
		    }
		    catch(EmptyResultDataAccessException e){
		    	throw new MessageNotFoundException(messageId);
		    }
		
	}
	
	public List<Message> getUserInbox(int userId){
		try {
			String sql = "select * "
					+ "from tbl_messages "
					+ "where recipient_id="+ userId + ";";
			List<Message> messages = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Message.class) );
			
			if (messages.size() == 0) throw new MessageNotFoundForUserException(userId);
			else return messages;
		}
		catch (Exception e) {
			throw new MessageNotFoundForUserException(userId);
		}
	}
	
	public List<Message> getUserSentMessages(int userId){
		try {
			String sql = "select * "
					+ "from tbl_messages "
					+ "where sender_id="+ userId + ";";
			List<Message> messages = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Message.class) );
			
			if (messages.size() == 0) throw new MessageNotFoundForUserException(userId);
			else return messages;
		}
		catch (Exception e) {
			throw new MessageNotFoundForUserException(userId);
		}
	}
	
	public int insertMessage(Message message) {
		String sql = "insert into tbl_messages (sender_id, recipient_id, message_text)"
				+ "values (?, ?, ?);";
		
		int senderId = message.getSenderId();
		int recipientId = message.getRecipientId();
		String messageText = message.getMessageText();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {		
			jdbcTemplate.update(	
				    new PreparedStatementCreator() {
				        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				            PreparedStatement ps = connection.prepareStatement(sql); 
				            ps.setInt(1, senderId);
				            ps.setInt(2, recipientId);
				            ps.setString(3, messageText);
				            return ps;
				        }
				    },
				    keyHolder);
		}
		catch(DataIntegrityViolationException e) {
			throw new MessageInvalidArgumentsException(message);
		}
		
		return (int) keyHolder.getKey();
		
	}
	
	//TODO: Implement update functionality
	public int updateMessage(int messageId, Message message) {
		try {
			String sql = String.format(""
					+ "update tbl_messages "
					+ "set "
					+ "sender_id 	= %s, "
					+ "recipient_id = %s, "
					+ "message_text = '%s' "
					+ ""
					+ "where id 	= %s"
					, message.getSenderId(), message.getRecipientId(), 
					message.getMessageText(), messageId);
			
			System.out.println(sql);
			int result = jdbcTemplate.update(sql);
			if (result <= 0) throw new MessageNotFoundException(messageId);
			return result;
			
		}
		catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			throw new MessageInvalidArgumentsException(message);
		}
		
	}
	
	
	public int deleteMessage(int messageId) {
		try {
			String sql = ""
					+ "delete from tbl_messages "
					+ "where id = " + messageId;
			
			int result = jdbcTemplate.update(sql);
			if (result == 0) throw new MessageNotFoundException(messageId);
			
			return result;
		}
		catch (RuntimeException e) {
			//e.printStackTrace();
			throw new MessageNotFoundException(messageId);
		}
	}
	
	
	
}
