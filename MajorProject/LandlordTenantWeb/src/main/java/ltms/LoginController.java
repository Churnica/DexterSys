package ltms;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import ltms.dao.UsersDao;
import ltms.entity.UsersEntity;
import ltms.service.MailService;
import ltms.service.MailServiceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private DataSource dataSource;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about() {
		return new ModelAndView("about");
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact() {
		return new ModelAndView("contactus");
	}

	@RequestMapping(value = "/forgotpwd", method = RequestMethod.POST)
	public ModelAndView forgotpwd(@RequestParam("txtUsername") String txtUsername, 
			HttpSession sessionObj, HttpServletRequest request) {
		UsersDao usersDao = new UsersDao();
		UsersEntity user = usersDao.getUser(dataSource, txtUsername);
		ModelAndView mv = null;
		
		if (user != null && user.getUserId() != null) {
			request.setAttribute("userId", txtUsername);
			mv = new ModelAndView("forgotpwd");
		} else {
			request.setAttribute("errorMessage", "Userid does not exist.");
			mv = new ModelAndView("login");
		}
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("txtUsername") String txtUsername,
			@RequestParam("txtPassword") String txtPassword, HttpSession sessionObj, HttpServletRequest request) {
		System.out.println("in controller --" + txtUsername + "--" + txtPassword);

		UsersDao usersDao = new UsersDao();
		UsersEntity user = usersDao.getUser(dataSource, txtUsername, txtPassword);
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			request.setAttribute("errorMessage", "Invalid userid or password.");
			mv = new ModelAndView("login");
		} else {
			sessionObj.setAttribute("loggedUser", user);
			mv = new ModelAndView("landingPage");
		}
		return mv;
	}

	@RequestMapping(value = "/loginExists/{userId}", method = RequestMethod.GET)
	public String isLoginExist(@PathVariable String userId) {
		UsersDao usersDao = new UsersDao();
		UsersEntity user = usersDao.getUser(dataSource, userId);
		if (user != null && user.getUserId() != null) {
			return "UserId already exists";
		}
		return " ";
	}

	@RequestMapping(value = "verifyUser", method = RequestMethod.GET)
	public ModelAndView verifyUser(HttpSession sessionObj, HttpServletRequest request) {
		System.out.println("in controller verifyUser --");

		UsersDao usersDao = new UsersDao();
		List<UsersEntity> users = usersDao.getUserForVerification(dataSource);
		UsersEntity loggedUser = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (loggedUser == null || loggedUser.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			request.setAttribute("usersForVerification", users);
			mv = new ModelAndView("verifyUser");
		}
		return mv;
	}

	@RequestMapping(value = "forgotPwdSubmit", method = RequestMethod.POST)
	public ModelAndView forgotPwdSubmit(@RequestParam("username") String username, HttpSession sessionObj, HttpServletRequest request) {
		System.out.println("in controller forgotPwdSubmit --");

		System.out.println("in controller username --" + username);
		//System.out.println("in controller email --" + email);

		UsersEntity loggedUser = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = new ModelAndView("login");
		
		UsersDao usersDao = new UsersDao();
		UsersEntity usersEntity = usersDao.getUser(dataSource, username);

		if(usersEntity != null && usersEntity.getUserId() != null) {
			
			Random rand = new Random();
			int minimum = 1;
			int maximum = 9999;
			int randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
			String password = "password" + randomNum;
			
			usersDao.updateUserPassword(dataSource, usersEntity.getUserId(), password);

			String toAddress = usersEntity.getEmail(); // + "," + email;
			String subject = "Your new password for Conveyance.com is here";
			String message = "<p style='color:black;'>Dear <b>" + usersEntity.getFirstName() + " "
					+ usersEntity.getLastName() + "</b>,<br /><br />Your username for Conveyance.com is <i>" + usersEntity.getUserId()
					+ "</i> and your new password is <i>" + password
					+ "</i><br /><br /><i>Mansi Joshi</i><br />Web Administrator,<br />Conveyance Inc, 2016</p>";
	
			MailService mailService = new MailService();
			MailServiceRequest mailServiceRequest = new MailServiceRequest();
			mailServiceRequest.setToAddress(toAddress);
			mailServiceRequest.setEmailSubject(subject);
			mailServiceRequest.setEmailMessage(message);
			mailService.sendEmail(mailServiceRequest);
	
			request.setAttribute("errorMessage", 
					"Your new password for Conveyance.com is emailed to your email address " + toAddress + ".<br />"
					+ "Please login with your new password.<br />");
		} else {
			request.setAttribute("errorMessage", "We could not find any profile for the criteria you entered to retrive your password."
					+ "<br />Please check again.<br />");
		}

		return mv;
	}

	@RequestMapping(value = "verifyUserSubmit", method = RequestMethod.POST)
	public ModelAndView verifyUserSubmit(@RequestParam("hdnUserIdnStatus") String hdnUserIdnStatus,
			@RequestParam("reason") String reason, HttpSession sessionObj, HttpServletRequest request) {
		System.out.println("in controller verifyUserSubmit --");

		System.out.println("in controller hdnUserIdnStatus --" + hdnUserIdnStatus);
		System.out.println("in controller reason --" + reason);

		String[] tokens = hdnUserIdnStatus.split(Pattern.quote(":"));
		String status = tokens[0];
		String userId = tokens[1];

		UsersDao usersDao = new UsersDao();
		int recordsUpdated = usersDao.updateUser(dataSource, userId, status, reason);
		if (recordsUpdated > 0) {
			request.setAttribute("successMessage", "User " + userId + " is updated successfully.");
		}

		List<UsersEntity> users = usersDao.getUserForVerification(dataSource);
		UsersEntity loggedUser = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (loggedUser == null || loggedUser.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			request.setAttribute("usersForVerification", users);
			mv = new ModelAndView("verifyUser");
		}
		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		System.out.println("in controller register --");
		ModelAndView mv = new ModelAndView("register");
		return mv;
	}

	@RequestMapping(value = "/logoff", method = RequestMethod.GET)
	public ModelAndView logoff(HttpSession sessionObj) {
		ModelAndView mv = null;
		sessionObj.invalidate();
		mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(value = "/landingPage", method = RequestMethod.GET)
	public ModelAndView landingPage(HttpSession sessionObj) {
		System.out.println("in controller landingPage --");
		UsersEntity user = (UsersEntity) sessionObj.getAttribute("loggedUser");
		ModelAndView mv = null;
		if (user == null || user.getRole() == null) {
			// Should send to login page
			mv = new ModelAndView("login");
		} else {
			sessionObj.setAttribute("loggedUser", user);
			mv = new ModelAndView("landingPage");
		}
		return mv;
	}

	@RequestMapping(value = "/registerSubmit", method = RequestMethod.POST)
	public ModelAndView registerSubmit(@RequestParam("userId") String userId, @RequestParam("password") String password,
			@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
			@RequestParam("phone") String phone, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("role") String role) {

		/*
		 * String[] beanNames = applicationContext.getBeanDefinitionNames(); for
		 * (String beanName : beanNames) { System.out.println(beanName + " : " +
		 * applicationContext.getBean(beanName).getClass().toString()); }
		 */

		System.out.println("***dataSource = " + dataSource);

		UsersDao usersDao = new UsersDao();
		UsersEntity user = new UsersEntity();
		user.setUserId(userId);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		user.setAddress(address);
		user.setRole(role);
		user.setStatus("NEW");

		usersDao.addUser(dataSource, user);

		System.out.println("in controller register submit--");
		ModelAndView mv = new ModelAndView("registerResult");
		return mv;
	}
}
