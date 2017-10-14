package ltms.entity;

import java.util.Date;
import java.io.Serializable;

public class RatingEntity {

	private static final long serialVersionUID = 1L;

	private Integer ratingId;
	
	private String review;
	
	private String rating;
	
	private Integer leaseId;

	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getLeaseId() {
		return leaseId;
	}

	public void setLeaseId(Integer leaseId) {
		this.leaseId = leaseId;
	}
	
}
