package com.ibm.diypopups.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.diypopups.model.Advertisements;
//import com.ibm.fixmyship.model.Post;
//@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisements, Long>{
	//List<Advertisements> findByUidNotIn(List<Long> userIds);
	List<Advertisements> findByEidIn(List<Long> userIds);
}