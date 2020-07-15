package sintval.api.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sintval.api.entity.UID;
import sintval.api.exception.InvalidInputParameterException;
import sintval.api.exception.UserNotFoundException;
import sintval.api.repository.SintvalRepository;
import sintval.api.repository.SintvalJPA;

@RestController
public class SintvalController {
	@Autowired
	SintvalRepository repository; 
	
	@Autowired
	SintvalJPA jparepo;
	@RequestMapping(value = "/external/validator/uid/v1/validate", method = RequestMethod.POST)
	public String getDetails(@Valid @RequestBody UID uid){ 
		if(uid.getFirstName()=="" | uid.getLastName()=="" | uid.getUID()=="")
		{throw new InvalidInputParameterException("Invalid Input");}
		
		UID a = jparepo.findUser(uid.getFirstName(),uid.getLastName(),uid.getUID());
		if(a==null)
		{throw new UserNotFoundException("User id '" + uid.getUID() + "' does no exist");}
		else {
			repository.updateOTP_SendMail(uid.getUID());
			return "OTP is sent to your registered email id. Please authenticate by entering received OTP.";}
	}	
	
	
	@RequestMapping(value = "/external/validator/uid/v1/verify/otp", method = RequestMethod.POST)
	public String otpVerification(@RequestBody UID uid)
	{	int a = 0;
		try {
		a = repository.verifyOTP(uid);
		}
		catch(Exception e) {
			a=0;
		}
		
		if(a==1)
			return "Validation successful";
		else
			return "Authentication failed. Please try again.";
	}
	
	
	@RequestMapping(value= "/external/validator/uid/v1/users", method = RequestMethod.POST )
	public String Insert_User(@Valid @RequestBody UID uid){
		int a = 0;
		try{
			jparepo.save(uid);
		
			a = 1;}
		catch(Exception e) {
			a=0;
		}
		if(a==1)
			{return "Record Inserted";
			
			}
		else
		{
			return "Server down";
		}
	}
	
	
	@RequestMapping(value= "/external/validator/uid/v1/users", method = RequestMethod.GET )
	public List<UID> findUser(){
		
		return jparepo.findAll();
	
	}

	
}


