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

import ltms.entity.ListingsEntity;
import ltms.entity.UsersEntity;
import ltms.service.MailService;
import ltms.service.MailServiceRequest;

public class ListingsDao {

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

	public List<ListingsEntity> getAllListings(DataSource dataSource) {
		System.out.print("dataSource = " + dataSource);

		Connection connection = null;

		String query = "SELECT ADDRESSLINE1, ADDRESSLINE2, CITY, STATE, ZIPCODE, "
				+ "ZONING, RENT, DESCRIPTION, LISTINGID, L.STATUS AS STATUS, IMAGEPATH, "
				+ "LANDLORDID, FIRSTNAME, LASTNAME, EMAIL, PHONE "
				+ "FROM LISTINGS L, USERS U WHERE L.STATUS='ACTIVE' AND USERID = LANDLORDID";
		ListingsEntity listing = null;
		List<ListingsEntity> listings = new ArrayList<ListingsEntity>();

		System.out.println("query = " + query);

		String leaseDateQuery = "SELECT LISTINGID, MAX(ENDDATE) AS ENDDATE FROM LEASE WHERE LISTINGID IN ( "
				+ "SELECT LISTINGID FROM LISTINGS L, USERS U WHERE L.STATUS='ACTIVE' AND USERID = L.LANDLORDID) "
				+ "GROUP BY LISTINGID ORDER BY LISTINGID";

		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				System.out.println("Inside listings populate ");
				listing = new ListingsEntity();
				listing.setAddressLine1(result.getString("ADDRESSLINE1"));
				listing.setAddressLine2(result.getString("ADDRESSLINE2"));
				listing.setCity(result.getString("CITY"));
				listing.setState(result.getString("STATE"));
				listing.setZipCode(result.getString("ZIPCODE"));
				listing.setZoning(result.getString("ZONING"));
				listing.setRent(result.getString("RENT"));
				listing.setDescription(result.getString("DESCRIPTION"));
				listing.setListingId(result.getInt("LISTINGID"));
				listing.setStatus(result.getString("STATUS"));
				listing.setImagePath(result.getString("IMAGEPATH"));
				listing.setLandlordFirstname(result.getString("FIRSTNAME"));
				listing.setLandlordLastname(result.getString("LASTNAME"));
				listing.setLandlordEmail(result.getString("EMAIL"));
				listing.setLandlordPhone(result.getString("PHONE"));
				listings.add(listing);
			}
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}

		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(leaseDateQuery);

			while (result.next()) {
				System.out.println("Inside lease date populate ");
				for (ListingsEntity listing1 : listings) {
					if (listing1.getListingId().equals(result.getInt("LISTINGID"))) {
						listing1.setLeaseEndDate(result.getDate("ENDDATE"));
					}
				}
			}
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}

		return listings;
	}

	public List<ListingsEntity> getMyListings(DataSource dataSource, String landlordId) {
		System.out.print("dataSource = " + dataSource);

		Connection connection = null;

		String query = "SELECT ADDRESSLINE1, ADDRESSLINE2, CITY, STATE, ZIPCODE, "
				+ "ZONING, RENT, DESCRIPTION, LISTINGID, LANDLORDID, STATUS, IMAGEPATH "
				+ "FROM LISTINGS WHERE STATUS='ACTIVE' AND LANDLORDID = '" + landlordId + "'";
		ListingsEntity listing = null;
		List<ListingsEntity> listings = new ArrayList<ListingsEntity>();

		System.out.println("query = " + query);

		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				System.out.println("Inside listings populate ");
				listing = new ListingsEntity();
				listing.setAddressLine1(result.getString("ADDRESSLINE1"));
				listing.setAddressLine2(result.getString("ADDRESSLINE2"));
				listing.setCity(result.getString("CITY"));
				listing.setState(result.getString("STATE"));
				listing.setZipCode(result.getString("ZIPCODE"));
				listing.setZoning(result.getString("ZONING"));
				listing.setRent(result.getString("RENT"));
				listing.setDescription(result.getString("DESCRIPTION"));
				listing.setListingId(result.getInt("LISTINGID"));
				listing.setStatus(result.getString("STATUS"));
				listing.setImagePath(result.getString("IMAGEPATH"));
				listings.add(listing);
			}
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return listings;
	}

	public List<ListingsEntity> getListingsForVerification(DataSource dataSource) {
		System.out.print("dataSource = " + dataSource);

		Connection connection = null;

		String query = "SELECT ADDRESSLINE1, ADDRESSLINE2, CITY, STATE, ZIPCODE, "
				+ "ZONING, RENT, DESCRIPTION, LISTINGID, LANDLORDID, STATUS, IMAGEPATH "
				+ "FROM LISTINGS WHERE STATUS='NEW'";
		ListingsEntity listing = null;
		List<ListingsEntity> listings = new ArrayList<ListingsEntity>();

		System.out.println("query = " + query);

		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			while (result.next()) {
				System.out.println("Inside listings populate ");
				listing = new ListingsEntity();
				listing.setAddressLine1(result.getString("ADDRESSLINE1"));
				listing.setAddressLine2(result.getString("ADDRESSLINE2"));
				listing.setCity(result.getString("CITY"));
				listing.setState(result.getString("STATE"));
				listing.setZipCode(result.getString("ZIPCODE"));
				listing.setZoning(result.getString("ZONING"));
				listing.setRent(result.getString("RENT"));
				listing.setDescription(result.getString("DESCRIPTION"));
				listing.setListingId(result.getInt("LISTINGID"));
				listing.setStatus(result.getString("STATUS"));
				listing.setImagePath(result.getString("IMAGEPATH"));
				listings.add(listing);
			}
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return listings;
	}

	public int getListingIdSeqNextVal(DataSource dataSource) {

		System.out.print("dataSource = " + dataSource);

		Connection connection = null;

		int listingId = 0;

		String query = "SELECT LISTING_SEQ.NEXTVAL LISTING_SEQ FROM DUAL";

		System.out.println("query = " + query);

		try {

			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			if (result.next()) {
				System.out.println("Returning listing sequence next val ");
				listingId = result.getInt("LISTING_SEQ");
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return listingId;
	}

	public int updateListing(DataSource dataSource, String listingId, String status, String reason) {
		System.out.print("dataSource = " + dataSource);

		Connection connection = null;

		int recordsUpdated = 0;

		String updateQuery = "UPDATE LISTINGS SET STATUS = '" + status + "', REASON = '" + reason
				+ "' WHERE LISTINGID = '" + listingId + "'";

		System.out.println("updateQuery = " + updateQuery);

		try {

			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			recordsUpdated = statement.executeUpdate(updateQuery);

			ListingsEntity listingsEntity = getListingByIdAllStatus(dataSource, listingId);
			
			System.out.println("listingsEntity.getAddressLine1 = " + listingsEntity.getAddressLine1());
			System.out.println("listingsEntity.getLandlordId = " + listingsEntity.getLandlordId());
			
			String landlordId = listingsEntity.getLandlordId();

			UsersDao usersDao = new UsersDao();
			UsersEntity usersEntity = usersDao.getUser(dataSource, landlordId);
			String toAddress = usersEntity.getEmail();
			String subject = "Update Listing";
			String message = "<p style='color:black;'>Dear <b>" + usersEntity.getFirstName() + " "
					+ usersEntity.getLastName() + "</b>,<br /><br />Your listing " + listingsEntity.getAddressLine1()
					+ " is <i>" + status + "</i> !<br /><br /><i>Mansi Joshi</i><br />Web Administrator,<br />Conveyance Inc, 2016</p>";

			MailService mailService = new MailService();
			MailServiceRequest mailServiceRequest = new MailServiceRequest();
			mailServiceRequest.setToAddress(toAddress);
			mailServiceRequest.setEmailSubject(subject);
			mailServiceRequest.setEmailMessage(message);
			mailService.sendEmail(mailServiceRequest);

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return recordsUpdated;
	}
	
	private ListingsEntity getListingByIdAllStatus(DataSource dataSource, String listingId) {
		System.out.print("dataSource = " + dataSource);

		Connection connection = null;

		String query = "SELECT ADDRESSLINE1, ADDRESSLINE2, CITY, STATE, ZIPCODE, "
				+ "ZONING, RENT, DESCRIPTION, LISTINGID, LANDLORDID, STATUS, IMAGEPATH "
				+ "FROM LISTINGS WHERE LISTINGID = " + listingId;
		ListingsEntity listing = null;

		System.out.println("query = " + query);

		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			if (result.next()) {
				System.out.println("Inside listings populate ");
				listing = new ListingsEntity();
				listing.setAddressLine1(result.getString("ADDRESSLINE1"));
				listing.setAddressLine2(result.getString("ADDRESSLINE2"));
				listing.setCity(result.getString("CITY"));
				listing.setState(result.getString("STATE"));
				listing.setZipCode(result.getString("ZIPCODE"));
				listing.setZoning(result.getString("ZONING"));
				listing.setRent(result.getString("RENT"));
				listing.setDescription(result.getString("DESCRIPTION"));
				listing.setListingId(result.getInt("LISTINGID"));
				listing.setLandlordId(result.getString("LANDLORDID"));
				listing.setStatus(result.getString("STATUS"));
				listing.setImagePath(result.getString("IMAGEPATH"));
			}
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return listing;
	}
	
	public ListingsEntity getListingById(DataSource dataSource, String listingId) {
		System.out.print("dataSource = " + dataSource);

		Connection connection = null;

		String query = "SELECT ADDRESSLINE1, ADDRESSLINE2, CITY, STATE, ZIPCODE, "
				+ "ZONING, RENT, DESCRIPTION, LISTINGID, LANDLORDID, STATUS, IMAGEPATH "
				+ "FROM LISTINGS WHERE STATUS='ACTIVE' AND LISTINGID = " + listingId;
		ListingsEntity listing = null;

		System.out.println("query = " + query);

		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			if (result.next()) {
				System.out.println("Inside listings populate ");
				listing = new ListingsEntity();
				listing.setAddressLine1(result.getString("ADDRESSLINE1"));
				listing.setAddressLine2(result.getString("ADDRESSLINE2"));
				listing.setCity(result.getString("CITY"));
				listing.setState(result.getString("STATE"));
				listing.setZipCode(result.getString("ZIPCODE"));
				listing.setZoning(result.getString("ZONING"));
				listing.setRent(result.getString("RENT"));
				listing.setDescription(result.getString("DESCRIPTION"));
				listing.setListingId(result.getInt("LISTINGID"));
				listing.setStatus(result.getString("STATUS"));
				listing.setImagePath(result.getString("IMAGEPATH"));
			}
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
		return listing;
	}

	public void addListing(DataSource dataSource, ListingsEntity listing) {

		System.out.print("dataSource = " + dataSource);

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			String query = "insert into listings (ADDRESSLINE1, ADDRESSLINE2, CITY, STATE, ZIPCODE, "
					+ "ZONING, RENT, DESCRIPTION, LISTINGID, LANDLORDID, STATUS, IMAGEPATH) values ('"
					+ listing.getAddressLine1() + "','" + listing.getAddressLine2() + "','" + listing.getCity() + "','"
					+ listing.getState() + "','" + listing.getZipCode() + "','" + listing.getZoning() + "','"
					+ listing.getRent() + "','" + listing.getDescription() + "'," + listing.getListingId() + ",'"
					+ listing.getLandlordId() + "', '" + listing.getStatus() + "','" + listing.getImagePath() + "')";
			System.out.println("query = " + query);
			statement.executeUpdate(query);
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
	}

	public void deletelisting(DataSource dataSource, String listingId) {
		System.out.print("dataSource = " + dataSource);
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			String query = "update listings set status='DELETED' where LISTINGID = " + Integer.parseInt(listingId);
			System.out.println("query = " + query);
			statement.executeUpdate(query);
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException sqe) {
				sqe.printStackTrace();
			}
		}
	}

}
