package com.ischolar.ischolar.repo;



import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ischolar.ischolar.entity.ApplicationForm;

public interface ApplicationDao extends JpaRepository<ApplicationForm, Integer>{
	
	@Query("from ApplicationForm as a where a.user.id = :userId")
	public Page<ApplicationForm> getByUserId(@Param("userId") int userId, Pageable p); 
	
	@Query("from ApplicationForm as a where a.institute = :institute")
	public Page<ApplicationForm> getByInstitute(@Param("institute") String institute, Pageable p); 

	@Modifying
	@Transactional
	@Query("update ApplicationForm as a set a.institute = :institute, a.editable = :editable, a.message = :message where a.id = :id")
	public void updateInstitute(@Param("institute") String institute, @Param("id") int id, @Param("editable") boolean editable, @Param("message") String message);
	
	@Query("from ApplicationForm as a where a.department = :department and a.institute = :institute")
	public Page<ApplicationForm> getByDepartment(@Param("institute") String institute, @Param("department") String department, Pageable p); 

	@Modifying
	@Transactional
	@Query("update ApplicationForm as a set a.department = :department, a.editable = :editable, a.message = :message where a.id = :id")
	public void updateDepartment(@Param("department") String department, @Param("id") int id, @Param("editable") boolean editable, @Param("message") String message);
	
	@Query("from ApplicationForm as a where a.department = :department and a.institute = :institute and a.ddo = :ddo")
	public Page<ApplicationForm> getByDDO(@Param("institute") String institute, @Param("department") String department,@Param("ddo") String ddo, Pageable p); 

	@Modifying
	@Transactional
	@Query("update ApplicationForm as a set a.ddo = :ddo, a.editable = :editable, a.message = :message where a.id = :id")
	public void updateDDO(@Param("ddo") String department, @Param("id") int id, @Param("editable") boolean editable, @Param("message") String message);
	
}
