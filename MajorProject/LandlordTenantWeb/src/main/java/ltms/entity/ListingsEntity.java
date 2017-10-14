package ltms.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListingsEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String zipCode;

    private String zoning;

    private String rent;

    private String description;

    private String status;
    
    private Integer listingId;
    
    private String landlordId;
    
    private String landlordFirstname;
    
    private String landlordLastname;
    
    private String landlordEmail;
    
    private String landlordPhone;
    
    private String imagePath;
    
    private Date leaseEndDate;
    
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getZoning() {
		return zoning;
	}

	public void setZoning(String zoning) {
		this.zoning = zoning;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getListingId() {
		return listingId;
	}

	public void setListingId(Integer listingId) {
		this.listingId = listingId;
	}
	
	public String getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}

	public String getLandlordFirstname() {
		return landlordFirstname;
	}

	public void setLandlordFirstname(String landlordFirstname) {
		this.landlordFirstname = landlordFirstname;
	}

	public String getLandlordLastname() {
		return landlordLastname;
	}

	public void setLandlordLastname(String landlordLastname) {
		this.landlordLastname = landlordLastname;
	}

	public String getLandlordEmail() {
		return landlordEmail;
	}

	public void setLandlordEmail(String landlordEmail) {
		this.landlordEmail = landlordEmail;
	}

	public String getLandlordPhone() {
		return landlordPhone;
	}

	public void setLandlordPhone(String landlordPhone) {
		this.landlordPhone = landlordPhone;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getLeaseEndDate() {
		return leaseEndDate;
	}
	
	public String getLeaseEndDateDisplayString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format((leaseEndDate != null) ? leaseEndDate : new Date());
	}

	public void setLeaseEndDate(Date leaseEndDate) {
		this.leaseEndDate = leaseEndDate;
	}
	
	
}