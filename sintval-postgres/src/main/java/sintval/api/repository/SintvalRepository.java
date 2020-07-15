package sintval.api.repository;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import sintval.api.entity.UID;



@Repository
public class SintvalRepository {
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	private SintvalJPA jparepo;
	
	public UID getUserById(UID uid) {
		return jparepo.findUser(uid.getFirstName(), uid.getLastName(), uid.getUID());
	
	}
	
	
	public String getEmail(String UID) {
		UID u = jparepo.findUserbyUID(UID);
		return u.getEmail();
	}
	public int updateOTP_SendMail(String uid) {
		String c = generateOTP();
		jparepo.updateOTP(c, uid);
		SimpleMailMessage mail = new SimpleMailMessage();
		String email = getEmail(uid);
		int otp = Integer.parseInt(c);
		mail.setTo(email);
		mail.setSubject("Sintval Authentication Code");
		mail.setText("Hi! Your OTP is:" + otp);
		javaMailSender.send(mail);
		return 1;
	}
	
	
	public String generateOTP() 
	    { 
	        String numbers = "0123456789"; 
	        Random rndm_method = new Random(); 
	        char[] otp = new char[6]; 
	        for (int i = 0; i < 6; i++) 
	        {  
	            otp[i] = 
	             numbers.charAt(rndm_method.nextInt(numbers.length())); 
	        }
	        String str2 = String.valueOf(otp);
	        return str2; 
	
	}
	public int verifyOTP(UID uid) {
		UID u = jparepo.vOTP(uid.getOTP(), uid.getUID());
		if(u.getFirstName()==null)
			{return 0;}
		else 
			return 1;
	}
}
