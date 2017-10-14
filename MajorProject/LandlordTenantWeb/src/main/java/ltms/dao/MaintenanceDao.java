package ltms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import ltms.entity.MaintenanceEntity;

public class MaintenanceDao {

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
	
	public List<MaintenanceEntity> getMyMaintenanceRequests(DataSource dataSource, 
			String landlordId) {
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		String query = "SELECT MAINTENANCEID, TOKENDATE, ISSUE, "
				+ "M.LANDLORDID AS LANDLORDID, M.LISTINGID AS LISTINGID, TENANTID, "
				+ "ADDRESSLINE1, ADDRESSLINE2, CITY, STATE, ZIPCODE FROM MAINTENANCE M, LISTINGS L "
				+ "WHERE M.LANDLORDID = '" + landlordId + "' AND L.LISTINGID = M.LISTINGID";
		MaintenanceEntity maintenance = null;
		List<MaintenanceEntity> maintenanceList = new ArrayList<MaintenanceEntity>();
		
		System.out.println("query = " + query);
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				System.out.println("Inside maintenance populate ");
				maintenance = new MaintenanceEntity();
				maintenance.setMaintanceId(result.getInt("MAINTENANCEID"));
				maintenance.setTokenDate(result.getDate("TOKENDATE"));
				maintenance.setIssue(result.getString("ISSUE"));
				maintenance.setLandlordId(result.getString("LANDLORDID"));
				maintenance.setListingId(result.getInt("LISTINGID"));
				maintenance.setTenantId(result.getString("TENANTID"));
				maintenance.setListingAddressline1(result.getString("ADDRESSLINE1"));
				maintenance.setListingAddressline2(result.getString("ADDRESSLINE2"));
				maintenanceList.add(maintenance);
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
		return maintenanceList;
	}
	
	public void addMaintenance(DataSource dataSource, MaintenanceEntity maintenance) {
		
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			String query = "INSERT INTO MAINTENANCE (MAINTENANCEID, TOKENDATE, ISSUE, "
					+ "LANDLORDID, LISTINGID, TENANTID) VALUES (MAINTENANCE_SEQ.NEXTVAL, "
					+ "SYSDATE, '"
					+ maintenance.getIssue() + "','"
					+ maintenance.getLandlordId() + "','"
					+ maintenance.getListingId() + "','"
					+ maintenance.getTenantId() + "')";
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
