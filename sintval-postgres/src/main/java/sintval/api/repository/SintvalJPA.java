package sintval.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sintval.api.entity.UID;
@Repository
@Transactional
public interface SintvalJPA extends JpaRepository<UID, Long>{

	@Query("FROM UID WHERE first_name = ?1 and last_name = ?2 and uid = ?3")
	UID findUser(String first_name, String last_name,String uid);
	
	@Query("FROM UID WHERE uid = ?1")
	UID findUserbyUID(String uid);
	
	@Query("FROM UID WHERE otp = ?1 and uid = ?2")
	UID vOTP(String otp,String uid);
	@Transactional
	@Modifying
	@Query("Update UID Set otp = ?1 where uid = ?2")
	void updateOTP(String otp, String uid);
	
}


