package com.ibm.diypopups.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ibm.diypopups.model.Advertisements;
import com.ibm.diypopups.model.User;
import com.ibm.diypopups.model.creditcounter;
import com.ibm.diypopups.model.perdayclicks;
import com.ibm.diypopups.repository.AdvertisementRepository;
import com.ibm.diypopups.repository.DateRepository;
import com.ibm.diypopups.repository.UserRepository;
import com.ibm.diypopups.repository.creditcounterRepository;
//import com.ibm.fixmyship.service.UserServiceImpl;

@Component
public class UserServiceImpl {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AdvertisementRepository vidrepo;

	@Autowired
	creditcounterRepository credRepo;
	
	@Autowired
	DateRepository drepo;

	
	/*@Override
	public User save(User user) {
		return userRepo.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public Optional<User> findByUsernameOrEmail(String username, String email) {
		return userRepo.findByUsernameOrEmail(username, email);
	}

	@Override
	public List<User> findByIdIn(List<Long> userIds) {
		return userRepo.findByIdIn(userIds);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepo.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepo.findById(id);
	}*/
	
	public void add(User U) {
		//MultipartFile file=repo.findById(V.getId()).get(). 
		// @formatter:on

		//String filename=
		
		userRepo.save(U);
	}
	
	
	public void update(User U) {
		
		userRepo.save(U);
	}
	
	
	public void delete(Long id) {
		userRepo.delete(userRepo.findById(id).get());
	}
	
	public List<User> list() {
		
			List<User> eU = new ArrayList<>();
			for (User U : userRepo.findAll()) {
				eU.add(U);
			}
			return eU;
		}
		
	
	public User get(long id)
	{
		//return employees.get(empId);
		return userRepo.findById(id).get();
		 
		// @formatter:on

	}
	
	public List<Advertisements> listVids() {
		
		List<Advertisements> vds = new ArrayList<>();
		for (Advertisements V : vidrepo.findAll()) {
			vds.add(V);
		}
		return vds;
	}
	
	public void UpdateClick(Long id){
		//return  (List<Employee>)repository.findByDepartment(department);
		Advertisements v1=vidrepo.findById(id).get();
		int z=v1.getClicks()+1;
		v1.setClicks(z);
		vidrepo.save(v1);
		//return true;
	}
	
	public void CreditCount(long vid,
			long eid) 
		{
		creditcounter cct=new creditcounter();
		cct.setVid(vid);
		cct.setEid(eid);
		//vids v=new vids();
		perdayclicks p=new perdayclicks();
		int ct=credRepo.countCredit(vid, eid);
		Advertisements v1=vidrepo.findById(vid).get();
		int vc=v1.getVcredits();
		int dc=drepo.DailyClickLimit(eid);
		
		
		User eu;
		if(dc==0)
		{
			System.out.println(dc);
			p.setEid(eid);
			/*System.out.println(LocalDate.now());
			p.setSysdate(LocalDate.now());*/
			DateFormat dateformat= new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			p.setSysdate(dateformat.format(date));
			
			p.setClicklimit(5);
			
		}
		else {
			p=drepo.findById(drepo.GetUserSl(eid)).get();
			p.setClicklimit(p.getClicklimit());
		}
		
		/*if(p.getClicklimit()>0)
		{*/
		if(ct==0 && vc>0 && p.getClicklimit()>0) {
			
			//p.setEid(eid);
			//p.setSysdate(LocalDate.now());
			p.setClicklimit(p.getClicklimit()-1);
			drepo.save(p);
			credRepo.save(cct);
			v1.setVcredits(vc-2);
			//v1.setVcredits(vc-vcd);
			
			//cct=credRepo.findById(vid).get();
			
			eu=userRepo.findById(eid).get();
			int x=eu.getUpcredits();
			x+=2;
			eu.setUpcredits(x);
			
			vidrepo.save(v1);
			userRepo.save(eu);
			
		}
		}	
		
	
	
	/*public int deductCredits(int id) {
		int vc=0;
		vids v1=vidrepo.findById(id).get();
		if(v1.getVcredits()>0 ) {
			vc=v1.getVcredits()-2;
			
			}
		return vc;*/


		
	


	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}


}
