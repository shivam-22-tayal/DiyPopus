package com.ibm.diypopups.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ibm.diypopups.model.creditcounter;

public interface creditcounterRepository extends CrudRepository<creditcounter, Integer> {
	
	@Query(value="Select count(*) from creditcounter cc where cc.vid= :vid and cc.eid= :eid", nativeQuery=true)
	public int countCredit(@Param("vid") long vid,@Param("eid") long eid);
}
