package ltms;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ltms.dao.LeaseDao;
import ltms.dao.ListingsDao;
import ltms.dao.MaintenanceDao;
import ltms.dao.RatingDao;
import ltms.entity.LeaseEntity;
import ltms.entity.ListingsEntity;
import ltms.entity.MaintenanceEntity;
import ltms.entity.RatingEntity;
import ltms.entity.UsersEntity;

@Controller
public class ListingController {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private DataSource dataSource;

	@RequestMapping(value = "/listing", method = RequestMethod.GET)
	public ModelAndView listing() {
		System.out.println("in controller listing --");
		ModelAndView mv = new ModelAndView("listing");
		return mv;
	}

	@RequestMapping(value = "/listingSubmit", method = RequestMethod.POST)
	public ModelAndView listingSubmit(HttpServletRequest request,
			HttpSession sessionObj) {

		System.out.println("***dataSource = " + dataSource);

		UsersEntity loggedUser = (UsersEntity) sessionObj.getAttribute("loggedUser");

		ModelAndView mv = null;
		if (loggedUser == null || loggedUser.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {

			DiskFileItemFactory factory = new DiskFileItemFactory(100 * 1024, new File("c:\\files"));

			ServletFileUpload upload = new ServletFileUpload(factory);

			ListingsDao listingsDao = new ListingsDao();
			int listingId = listingsDao.getListingIdSeqNextVal(dataSource);
			ListingsEntity listing = new ListingsEntity();

			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();

					if (item.isFormField()) {
						if("addressline1".equals(item.getFieldName())) {
							listing.setAddressLine1(item.getString());
						} else if("addressline2".equals(item.getFieldName())) {
							listing.setAddressLine2(item.getString());
						} else if("city".equals(item.getFieldName())) {
							listing.setCity(item.getString());
						} else if("state".equals(item.getFieldName())) {
							listing.setState(item.getString());
						} else if("zipCode".equals(item.getFieldName())) {
							listing.setZipCode(item.getString());
						} else if("zoning".equals(item.getFieldName())) {
							listing.setZoning(item.getString());
						} else if("rent".equals(item.getFieldName())) {
							listing.setRent(item.getString());
						} else if("description".equals(item.getFieldName())) {
							listing.setDescription(item.getString());
						}
					} else {
						String fileName = item.getName();
						int lastIndexOfDot = fileName.lastIndexOf('.');
						String fileExtension = fileName.substring(lastIndexOfDot);
						String imagePath = "/listing" + listingId + fileExtension;
						listing.setImagePath(imagePath);
						String path = request.getSession().getServletContext().getRealPath("/resources/images") + imagePath;
						System.out.println("Path = " + path);
						File storeImageFile = new File(path);
						try {
							item.write(storeImageFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			listing.setListingId(listingId);
			listing.setLandlordId(loggedUser.getUserId());
			listing.setStatus("NEW");
			
			listingsDao.addListing(dataSource, listing);

			System.out.println("in controller listing --");
			mv = new ModelAndView("listingResult");

		}
		return mv;
	}

	@RequestMapping(value = "/viewListing", method = RequestMethod.GET)
	public ModelAndView viewListing(HttpServletRequest request) {
		ListingsDao listingsDao = new ListingsDao();
		List<ListingsEntity> listings = listingsDao.getAllListings(dataSource);
		ModelAndView mv = new ModelAndView("viewListing");
		request.setAttribute("listings", listings);
		return mv;
	}

	@RequestMapping(value = "/viewMyListing", method = RequestMethod.GET)
	public ModelAndView viewMyListing(HttpServletRequest request, HttpSession sessionObj) {
		ListingsDao listingsDao = new ListingsDao();
		UsersEntity user = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			String landlordId = user.getUserId();
			List<ListingsEntity> myListings = listingsDao.getMyListings(dataSource, landlordId);
			mv = new ModelAndView("viewMyListing");
			request.setAttribute("myListings", myListings);
		}
		return mv;
	}

	@RequestMapping(value = "/viewMyLease", method = RequestMethod.GET)
	public ModelAndView viewMyLease(HttpServletRequest request, HttpSession sessionObj) {
		LeaseDao leaseDao = new LeaseDao();
		UsersEntity user = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			String UserId = user.getUserId();
			List<LeaseEntity> myLease = leaseDao.getLeasesForUser(dataSource, user.getUserId(), user.getRole());
			mv = new ModelAndView("viewMyLease");
			request.setAttribute("myLeases", myLease);
		}
		return mv;
	}
	
	@RequestMapping(value = "verifyListing", method = RequestMethod.GET)
	public ModelAndView verifyListing(HttpSession sessionObj, HttpServletRequest request) {
		System.out.println("in controller verifyListing --");

		UsersEntity loggedUser = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (loggedUser == null || loggedUser.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			ListingsDao listingsDao = new ListingsDao();
			List<ListingsEntity> listings = listingsDao.getListingsForVerification(dataSource);
			request.setAttribute("listingsForVerification", listings);
			mv = new ModelAndView("verifyListing");
		}
		return mv;
	}

	@RequestMapping(value = "verifyListingSubmit", method = RequestMethod.POST)
	public ModelAndView verifyListingSubmit(@RequestParam("hdnListingIdnStatus") String hdnListingIdnStatus,
			@RequestParam("reason") String reason, HttpSession sessionObj, HttpServletRequest request) {
		System.out.println("in controller verifyListingSubmit --");

		UsersEntity loggedUser = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (loggedUser == null || loggedUser.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {

			System.out.println("in controller hdnListingIdnStatus --" + hdnListingIdnStatus);
			System.out.println("in controller reason --" + reason);

			String[] tokens = hdnListingIdnStatus.split(Pattern.quote(":"));
			String status = tokens[0];
			String listingId = tokens[1];

			ListingsDao listingsDao = new ListingsDao();

			int recordsUpdated = listingsDao.updateListing(dataSource, listingId, status, reason);
			if (recordsUpdated > 0) {
				request.setAttribute("successMessage", "Listing " + listingId + " is updated successfully.");
			}

			List<ListingsEntity> listings = listingsDao.getListingsForVerification(dataSource);
			request.setAttribute("listingsForVerification", listings);
			mv = new ModelAndView("verifyListing");
		}
		return mv;
	}

	@RequestMapping(value = "/deleteListing", method = RequestMethod.GET)
	public ModelAndView deleteListing(@RequestParam("listingId") String listingId, HttpSession sessionObj) {
		ListingsDao listingsDao = new ListingsDao();
		listingsDao.deletelisting(dataSource, listingId);
		ModelAndView mv = new ModelAndView("deleteListingResult");
		System.out.println("listingId = " + listingId);
		return mv;
	}

	@RequestMapping(value = "/lease", method = RequestMethod.GET)
	public ModelAndView lease(@RequestParam("listingId") String listingId, HttpServletRequest request,
			HttpSession sessionObj) {
		System.out.println("in controller lease --" + listingId);
		ListingsDao listingsDao = new ListingsDao();
		UsersEntity user = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			ListingsEntity selectedListing = listingsDao.getListingById(dataSource, listingId);
			mv = new ModelAndView("lease");
			request.setAttribute("selectedListing", selectedListing);
		}
		return mv;
	}

	@RequestMapping(value = "/leaseSubmit", method = RequestMethod.POST)
	public ModelAndView leaseSubmit(@RequestParam("listingId") Integer listingId,
			@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate,
			@RequestParam("deposit") Integer securityDeposit, @RequestParam("tenantId") String tenantId,
			HttpServletRequest request, HttpSession sessionObj) {

		/*
		 * String[] beanNames = applicationContext.getBeanDefinitionNames(); for
		 * (String beanName : beanNames) { System.out.println(beanName + " : " +
		 * applicationContext.getBean(beanName).getClass().toString()); }
		 */

		System.out.println("***dataSource = " + dataSource);

		UsersEntity user = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			LeaseDao leaseDao = new LeaseDao();
			LeaseEntity lease = new LeaseEntity();
			lease.setStartDate(startDate);
			lease.setEndDate(endDate);
			lease.setSecurityDeposit(securityDeposit);
			lease.setLandlordId(user.getUserId());
			lease.setListingId(listingId);
			lease.setTenantId(tenantId);

			leaseDao.addLease(dataSource, lease);

			System.out.println("in controller lease --");
			mv = new ModelAndView("leaseResult");
		}
		return mv;
	}

	@RequestMapping(value = "/maintenance", method = RequestMethod.GET)
	public ModelAndView maintenance(HttpServletRequest request, HttpSession sessionObj) {
		System.out.println("in controller maintenance --");
		UsersEntity user = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			LeaseDao leaseDao = new LeaseDao();
			List<LeaseEntity> leases = leaseDao.getLeasesForTenant(dataSource, user.getUserId());
			mv = new ModelAndView("maintenance");
			request.setAttribute("leases", leases);
		}
		return mv;
	}

	@RequestMapping(value = "/maintenanceSubmit", method = RequestMethod.POST)
	public ModelAndView maintenanceSubmit(@RequestParam("leaseId") String leaseIdParam,
			@RequestParam("issue") String issue, HttpServletRequest request, HttpSession sessionObj) {

		/*
		 * String[] beanNames = applicationContext.getBeanDefinitionNames(); for
		 * (String beanName : beanNames) { System.out.println(beanName + " : " +
		 * applicationContext.getBean(beanName).getClass().toString()); }
		 */

		System.out.println("***dataSource = " + dataSource);

		UsersEntity user = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			String[] parts = leaseIdParam.split(":");
			String listingId = parts[0];
			String landlordId = parts[1];
			String tenantId = parts[2];

			MaintenanceDao maintenanceDao = new MaintenanceDao();
			MaintenanceEntity maintenance = new MaintenanceEntity();
			maintenance.setIssue(issue);
			maintenance.setLandlordId(landlordId);
			maintenance.setListingId(Integer.parseInt(listingId));
			maintenance.setTenantId(tenantId);

			maintenanceDao.addMaintenance(dataSource, maintenance);

			System.out.println("in controller maintenanceSubmit --");
			mv = new ModelAndView("maintenanceResult");
		}
		return mv;
	}

	@RequestMapping(value = "/viewMaintenanceRequest", method = RequestMethod.GET)
	public ModelAndView viewMaintenanceRequest(HttpServletRequest request, HttpSession sessionObj) {
		System.out.println("in controller view Maintenance Request --");
		UsersEntity user = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			MaintenanceDao maintenanceDao = new MaintenanceDao();
			String landlordId = user.getUserId();
			List<MaintenanceEntity> maintenanceRequests = maintenanceDao.getMyMaintenanceRequests(dataSource,
					landlordId);
			mv = new ModelAndView("viewMaintenanceRequest");
			request.setAttribute("maintenanceRequests", maintenanceRequests);
		}
		return mv;
	}

	@RequestMapping(value = "/rating", method = RequestMethod.GET)
	public ModelAndView rating(HttpServletRequest request, HttpSession sessionObj) {
		System.out.println("in controller rating --");
		UsersEntity user = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			LeaseDao leaseDao = new LeaseDao();
			List<LeaseEntity> leasesForRating = leaseDao.getLeasesForRating(dataSource, user.getUserId());
			mv = new ModelAndView("rating");
			request.setAttribute("leasesForRating", leasesForRating);
		}
		return mv;
	}

	@RequestMapping(value = "/ratingSubmit", method = RequestMethod.POST)
	public ModelAndView ratingSubmit(@RequestParam("leaseId") String leaseIdParam,
			@RequestParam("review") String review, @RequestParam("rating") String rating, HttpServletRequest request,
			HttpSession sessionObj) {

		/*
		 * String[] beanNames = applicationContext.getBeanDefinitionNames(); for
		 * (String beanName : beanNames) { System.out.println(beanName + " : " +
		 * applicationContext.getBean(beanName).getClass().toString()); }
		 */

		System.out.println("***dataSource = " + dataSource);

		UsersEntity user = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			String[] parts = leaseIdParam.split(":");
			String leaseId = parts[0];
			String landlordId = parts[1];
			String tenantId = parts[2];

			RatingDao ratingDao = new RatingDao();
			RatingEntity ratingEntity = new RatingEntity();
			ratingEntity.setLeaseId(Integer.parseInt(leaseId));
			ratingEntity.setRating(rating);
			ratingEntity.setReview(review);

			ratingDao.addRating(dataSource, ratingEntity);

			System.out.println("in controller ratingSubmit --");
			mv = new ModelAndView("ratingResult");
		}
		return mv;
	}
}
