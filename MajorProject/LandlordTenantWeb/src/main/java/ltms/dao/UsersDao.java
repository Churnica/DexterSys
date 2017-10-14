package ltms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import ltms.entity.UsersEntity;
import ltms.service.MailService;
import ltms.service.MailServiceRequest;

public class UsersDao {
	
	public List<UsersEntity> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UsersEntity getUser(DataSource dataSource, String userId, String password) {
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		
		String query = "SELECT USERID, FIRSTNAME, LASTNAME, PASSWORD, PHONE, "
				+ "EMAIL, ADDRESS, ROLE, STATUS FROM USERS WHERE USERID = '"
				+ userId + "' AND PASSWORD = '" + password + "' AND STATUS = 'ACTIVE'";
		UsersEntity user = null;
		
		System.out.println("query = " + query);
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if (result.next()) {
				System.out.println("Inside user populate ");
				user = new UsersEntity();
				user.setUserId(result.getString("USERID"));
				user.setFirstName(result.getString("FIRSTNAME"));
				user.setLastName(result.getString("LASTNAME"));
				user.setPassword(result.getString("PASSWORD"));
				user.setPhone(result.getString("PHONE"));
				user.setEmail(result.getString("EMAIL"));
				user.setAddress(result.getString("ADDRESS"));
				user.setRole(result.getString("ROLE"));
				user.setStatus(result.getString("STATUS"));	
			}
		} catch(SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return user;
	}
	
	public UsersEntity getUserByIdOrEmail(DataSource dataSource, String inputChoice, String field) {
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		
		String userIdQuery = "SELECT USERID, FIRSTNAME, LASTNAME, PASSWORD, PHONE, "
				+ "EMAIL, ADDRESS, ROLE, STATUS FROM USERS WHERE USERID = '"
				+ field + "' AND STATUS = 'ACTIVE'";
		
		String emailQuery = "SELECT USERID, FIRSTNAME, LASTNAME, PASSWORD, PHONE, "
				+ "EMAIL, ADDRESS, ROLE, STATUS FROM USERS WHERE EMAIL = '"
				+ field + "' AND STATUS = 'ACTIVE'";
		
		UsersEntity user = null;
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = null;
			
			if("User Name".equals(inputChoice)) {
				result = statement.executeQuery(userIdQuery);
			} else {
				result = statement.executeQuery(emailQuery);
			}
			
			if (result.next()) {
				System.out.println("Inside user populate ");
				user = new UsersEntity();
				user.setUserId(result.getString("USERID"));
				user.setFirstName(result.getString("FIRSTNAME"));
				user.setLastName(result.getString("LASTNAME"));
				user.setPassword(result.getString("PASSWORD"));
				user.setPhone(result.getString("PHONE"));
				user.setEmail(result.getString("EMAIL"));
				user.setAddress(result.getString("ADDRESS"));
				user.setRole(result.getString("ROLE"));
				user.setStatus(result.getString("STATUS"));	
			}
		} catch(SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return user;
	}
	
	public int updateUser(DataSource dataSource, String userId, String status, String reason) {
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		
		int recordsUpdated = 0;
		
		String query = "UPDATE USERS SET STATUS = '" + status + "', REASON = '" 
				+ reason + "' WHERE USERID = '" + userId + "'";
		UsersEntity user = null;
		
		System.out.println("query = " + query);
		
		try {
			
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			recordsUpdated = statement.executeUpdate(query);
			
			UsersEntity usersEntity = getUser(dataSource, userId);
			String toAddress = usersEntity.getEmail();
			String subject = "User Id Verification";
			String message = "<p style='color:black;'>Dear <b>" + usersEntity.getFirstName() + " "
					+ usersEntity.getLastName() + "</b>,<br /><br />Your user id " + usersEntity.getUserId()
					+ " is <i>" + status + "</i> !<br />The reason is <i>"+ reason
					+ "</i><br /><br /><i>Mansi Joshi</i><br />Web Administrator,<br />Conveyance Inc, 2016</p>";

			MailService mailService = new MailService();
			MailServiceRequest mailServiceRequest = new MailServiceRequest();
			mailServiceRequest.setToAddress(toAddress);
			mailServiceRequest.setEmailSubject(subject);
			mailServiceRequest.setEmailMessage(message);
			mailService.sendEmail(mailServiceRequest);
			
		} catch(SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return recordsUpdated;
	}
	
	public int updateUserPassword(DataSource dataSource, String userId, String password) {
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		
		int recordsUpdated = 0;
		
		String query = "UPDATE USERS SET PASSWORD = '" + password + "' WHERE USERID = '" + userId + "'";
		UsersEntity user = null;
		
		System.out.println("query = " + query);
		
		try {
			
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			recordsUpdated = statement.executeUpdate(query);
		} catch(SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return recordsUpdated;
	}

	public UsersEntity getUser(DataSource dataSource, String userId) {
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		
		String query = "SELECT USERID, FIRSTNAME, LASTNAME, PASSWORD, PHONE, "
				+ "EMAIL, ADDRESS, ROLE, STATUS FROM USERS WHERE USERID = '"
				+ userId + "'";
		UsersEntity user = null;
		
		System.out.println("query = " + query);
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			if (result.next()) {
				System.out.println("Inside user populate ");
				user = new UsersEntity();
				user.setUserId(result.getString("USERID"));
				user.setFirstName(result.getString("FIRSTNAME"));
				user.setLastName(result.getString("LASTNAME"));
				user.setPassword(result.getString("PASSWORD"));
				user.setPhone(result.getString("PHONE"));
				user.setEmail(result.getString("EMAIL"));
				user.setAddress(result.getString("ADDRESS"));
				user.setRole(result.getString("ROLE"));
				user.setStatus(result.getString("STATUS"));	
			}
		} catch(SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return user;
	}
	
	public List<UsersEntity> getUserForVerification(DataSource dataSource) {
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		
		String query = "SELECT USERID, FIRSTNAME, LASTNAME, PASSWORD, PHONE, "
				+ "EMAIL, ADDRESS, ROLE, STATUS FROM USERS WHERE STATUS = 'NEW'";
		List<UsersEntity> users = new ArrayList<UsersEntity>();
		UsersEntity user = null;
		
		System.out.println("query = " + query);
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				System.out.println("Inside user populate ");
				user = new UsersEntity();
				user.setUserId(result.getString("USERID"));
				user.setFirstName(result.getString("FIRSTNAME"));
				user.setLastName(result.getString("LASTNAME"));
				user.setPassword(result.getString("PASSWORD"));
				user.setPhone(result.getString("PHONE"));
				user.setEmail(result.getString("EMAIL"));
				user.setAddress(result.getString("ADDRESS"));
				user.setRole(result.getString("ROLE"));
				user.setStatus(result.getString("STATUS"));	
				users.add(user);
			}
		} catch(SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return users;
	}

	public void addUser(DataSource dataSource, UsersEntity user) {
		
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"insert into users (USERID, FIRSTNAME, LASTNAME, PASSWORD, PHONE, "
					+ "EMAIL, ADDRESS, ROLE, STATUS) values ('"
					+ user.getUserId() + "','"
					+ user.getFirstName() + "','"
					+ user.getLastName() + "','"
					+ user.getPassword() + "','"
					+ user.getPhone() + "','"
					+ user.getEmail() + "','"
					+ user.getAddress() + "','"
					+ user.getRole() + "','"
					+ user.getStatus() + "')");
		} catch(SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch(SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		
	}

}
