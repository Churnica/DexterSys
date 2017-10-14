package ltms.entity;

import java.io.Serializable;
import java.util.Date;

public class LeaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Integer leaseId;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer securityDeposit;
	
	private String landlordId;
	
	private String tenantId;
	
	private Integer listingId;
	
	private String listingAddressline1;
	
	private String listingAddressline2;

	public Integer getLeaseId() {
		return leaseId;
	}

	public void setLeaseId(Integer leaseId) {
		this.leaseId = leaseId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(Integer securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public String getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getListingId() {
		return listingId;
	}

	public void setListingId(Integer listingId) {
		this.listingId = listingId;
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
