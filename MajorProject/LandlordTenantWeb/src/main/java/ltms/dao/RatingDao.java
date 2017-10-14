package ltms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import ltms.entity.MaintenanceEntity;
import ltms.entity.RatingEntity;

public class RatingDao {

	public String toMysqlDateStr(Date date) {
	    String dateForMySql = "";
	    if (date == null) {
	        dateForMySql = null;
	    } else {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        dateForMySql = sdf.format(date);
	    }
	    return dateForMySql;
	}
	
	public void addRating(DataSource dataSource, RatingEntity rating) {
		
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			String query = "INSERT INTO RATING (RATINGID, REVIEW, RATING, "
					+ "LEASEID) VALUES (RATING_SEQ.NEXTVAL, '"
					+ rating.getReview() + "','"
					+ rating.getRating() + "','"
					+ rating.getLeaseId() + "')";
			System.out.println("query = " + query);
			statement.executeUpdate(query);
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
