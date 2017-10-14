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

import ltms.entity.LeaseEntity;
import ltms.entity.ListingsEntity;

public class LeaseDao {
	
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
			
	public List<LeaseEntity> getLeasesForTenant(DataSource dataSource, String tenantId) {
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		
		String query = "SELECT LEASEID, TENANTID, LEASE.LANDLORDID AS LANDLORDID, ADDRESSLINE1, ADDRESSLINE2 "
				+ "FROM LEASE, LISTINGS WHERE LEASE.TENANTID = '"
				+ tenantId + "' AND LEASE.LISTINGID = LISTINGS.LISTINGID";
		LeaseEntity lease = null;
		List<LeaseEntity> leases = new ArrayList<LeaseEntity>();
		
		System.out.println("query = " + query);
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				System.out.println("Inside leases populate ");
				lease = new LeaseEntity();
				lease.setLeaseId(result.getInt("LEASEID"));
				lease.setTenantId(result.getString("TENANTID"));
				lease.setLandlordId(result.getString("LANDLORDID"));
				lease.setListingAddressline1(result.getString("ADDRESSLINE1"));
				lease.setListingAddressline2(result.getString("ADDRESSLINE2"));
				leases.add(lease);
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
		return leases;
	}
	
	public List<LeaseEntity> getLeasesForUser(DataSource dataSource, String UserId, String Role) {
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		
		String landlordquery = "SELECT STARTDATE, ENDDATE, SECURITYDEPOSIT, LEASEID, TENANTID, LEASE.LANDLORDID AS LANDLORDID, ADDRESSLINE1, ADDRESSLINE2 "
				+ "FROM LEASE, LISTINGS WHERE LEASE.LANDLORDID = '"
				+ UserId + "' AND LEASE.LISTINGID = LISTINGS.LISTINGID";
		String tenantquery = "SELECT STARTDATE, ENDDATE, SECURITYDEPOSIT, LEASEID, TENANTID, LEASE.LANDLORDID AS LANDLORDID, ADDRESSLINE1, ADDRESSLINE2 "
				+ "FROM LEASE, LISTINGS WHERE LEASE.TENANTID = '"
				+ UserId + "' AND LEASE.LISTINGID = LISTINGS.LISTINGID";
		LeaseEntity lease = null;
		List<LeaseEntity> leases = new ArrayList<LeaseEntity>();
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = null;
			if("LANDLORD".equals(Role)) {
				System.out.println("query = " + landlordquery);
				result=statement.executeQuery(landlordquery);
				
				}
			else{
				System.out.println("query = " + tenantquery);
				result =statement.executeQuery(tenantquery);
			}
			
			while (result.next()) {
				System.out.println("Inside leases populate ");
				lease = new LeaseEntity();
				lease.setLeaseId(result.getInt("LEASEID"));
				lease.setStartDate(result.getDate("STARTDATE"));
				lease.setEndDate(result.getDate("ENDDATE"));
				lease.setSecurityDeposit(result.getInt("SECURITYDEPOSIT"));
				lease.setTenantId(result.getString("TENANTID"));
				lease.setLandlordId(result.getString("LANDLORDID"));
				lease.setListingAddressline1(result.getString("ADDRESSLINE1"));
				lease.setListingAddressline2(result.getString("ADDRESSLINE2"));
				leases.add(lease);
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
		return leases;
	}
	
	public List<LeaseEntity> getLeasesForRating(DataSource dataSource, String tenantId) {
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		
		String query = "SELECT LEASEID, TENANTID, LEASE.LANDLORDID AS LANDLORDID, ADDRESSLINE1, ADDRESSLINE2 "
				+ "FROM LEASE, LISTINGS WHERE LEASE.TENANTID = '"
				+ tenantId + "' AND LEASE.LISTINGID = LISTINGS.LISTINGID";
		LeaseEntity lease = null;
		List<LeaseEntity> leases = new ArrayList<LeaseEntity>();
		
		System.out.println("query = " + query);
		
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while (result.next()) {
				System.out.println("Inside leases populate ");
				lease = new LeaseEntity();
				lease.setLeaseId(result.getInt("LEASEID"));
				lease.setTenantId(result.getString("TENANTID"));
				lease.setLandlordId(result.getString("LANDLORDID"));
				lease.setListingAddressline1(result.getString("ADDRESSLINE1"));
				lease.setListingAddressline2(result.getString("ADDRESSLINE2"));
				leases.add(lease);
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
		return leases;
	}
	
	public void addLease(DataSource dataSource, LeaseEntity lease) {
		
		System.out.print("dataSource = " + dataSource);
        
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			String query = "INSERT INTO LEASE (LEASEID, STARTDATE, ENDDATE, SECURITYDEPOSIT, "
					+ "LANDLORDID, LISTINGID, TENANTID) VALUES (LEASE_SEQ.NEXTVAL,TO_DATE('"
					+ toMysqlDateStr(lease.getStartDate()) + "','YYYY-MM-DD'),TO_DATE('"
					+ toMysqlDateStr(lease.getEndDate()) + "','YYYY-MM-DD'),'"
					+ lease.getSecurityDeposit() + "','"
					+ lease.getLandlordId() + "','"
					+ lease.getListingId() + "','"
					+ lease.getTenantId() + "')";
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
