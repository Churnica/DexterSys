package ltms.entity;

import java.util.Date;
import java.io.Serializable;

public class MaintenanceEntity {

	private static final long serialVersionUID = 1L;

	private Integer maintanceId;
	
	private Date tokenDate;
	
	private String issue;
	
	private Integer listingId;
	
	private String tenantId;
	
	private String landlordId;

	private String listingAddressline1;
	
	private String listingAddressline2;
	
	public Integer getMaintanceId() {
		return maintanceId;
	}

	public void setMaintanceId(Integer maintanceId) {
		this.maintanceId = maintanceId;
	}

	public Date getTokenDate() {
		return tokenDate;
	}

	public void setTokenDate(Date tokenDate) {
		this.tokenDate = tokenDate;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public Integer getListingId() {
		return listingId;
	}

	public void setListingId(Integer listingId) {
		this.listingId = listingId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}

	public String getListingAddressline1() {
		return listingAddressline1;
	}

	public void setListingAddressline1(String listingAddressline1) {
		this.listingAddressline1 = listingAddressline1;
	}

	public String getListingAddressline2() {
		return listingAddressline2;
	}

	public void setListingAddressline2(String listingAddressline2) {
		this.listingAddressline2 = listingAddressline2;
	}
	
}
