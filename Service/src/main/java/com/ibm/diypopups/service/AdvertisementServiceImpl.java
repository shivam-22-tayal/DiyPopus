package com.ibm.diypopups.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import com.ibm.diypopups.model.Advertisements;
import com.ibm.diypopups.repository.AdvertisementRepository;
import com.ibm.diypopups.repository.UserRepository;

@Component
public class AdvertisementServiceImpl {

	@Autowired
AdvertisementRepository addRepo;
	
//	@Override
//	public List<Advertisements> getAllAdds() {
//		return addRepo.findAll();
//	}
//
//	@Override
	public Advertisements save(Advertisements Advertisements) {
		Advertisements.setVcredits(10);
		return addRepo.save(Advertisements);
	}
//
//	@Override
//	public Optional<Advertisements> findById(Long id) {
//		return addRepo.findById(id);
//	}
//
//	@Override
//	public List<Advertisements> findByUidNotIn(List<Long> userIds) {
//		return addRepo.findByUidNotIn(userIds);
//	}
//
//	@Override
//	public List<Advertisements> findByUidIn(List<Long> userIds) {
//		return addRepo.findByUidIn(userIds);
//	}

	
	public boolean addAdvertisement(Advertisements advt) {
		boolean flag=false;
		addRepo.save(advt);
		flag= true;
		return flag;
	}
	
	public void Update(Advertisements advt) {
		
		addRepo.save(advt);
	}
	
	public List<Advertisements> getAllAdvertisement() {
		
		/*String hql = "FROM Advertisement as ats ORDER BY ats.id";
		return (List<Advertisement>) entityManager.createQuery(hql).getResultList();*/
		List<Advertisements> adv=new ArrayList();
		
		for(Advertisements A:addRepo.findAll()) {
			
			adv.add(A);
		}
		return adv;
	}
	
	public Advertisements getAdvertisementById(long advtId) {
		
		return addRepo.findById( advtId).get();
	}
	
	public void delete(long id) {
		addRepo.delete(addRepo.findById( id).get());
	}
	
	public void UpdateClick(long id){
		//return  (List<Employee>)repository.findByDepartment(department);
		Advertisements v1=addRepo.findById( id).get();
		int z=v1.getClicks()+1;
		v1.setClicks(z);
		addRepo.save(v1);
		//return true;
	}

	public Optional<Advertisements> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Advertisements> findByEidIn(List<Long> userIds) {
		return addRepo.findByEidIn(userIds);
	}

	 
}
