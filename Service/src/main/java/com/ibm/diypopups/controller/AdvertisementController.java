package com.ibm.diypopups.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.diypopups.model.Advertisements;
import com.ibm.diypopups.model.User;
import com.ibm.diypopups.repository.UserRepository;
import com.ibm.diypopups.security.CurrentUser;
import com.ibm.diypopups.security.UserPrincipal;
//import com.ibm.diypopups.service.AdvertisementServiceImpl;
import com.ibm.diypopups.service.AdvertisementServiceImpl;
//import com.ibm.diypopups.service.UserService;
import com.ibm.diypopups.service.UserServiceImpl;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/advt")
public class AdvertisementController {

	@Autowired
	private AdvertisementServiceImpl advertisementService;
	
	@Autowired
	private UserServiceImpl userservice;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/status")
	public ResponseEntity<String> status(){
		return new ResponseEntity<String>("Services running", HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Advertisements>> getAllAdvertisements(){
		List<Advertisements> list = advertisementService.getAllAdvertisement();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Advertisements> findAdvertisement(@PathVariable long id){
		Optional<Advertisements> Advertisements = advertisementService.findById(id);
		if(Advertisements.isPresent()) {
			return new ResponseEntity<>(Advertisements.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);		
	}
	
	@PostMapping
	public ResponseEntity<?> addAdvertisements(@RequestBody Advertisements Advertisements, @CurrentUser UserPrincipal currentUser){
		System.out.println(currentUser.getId());
		User user=userRepo.findById(currentUser.getId()).get();
		
		Advertisements.setEid(currentUser.getId());
		int x=user.getDowncredits();
		if(x>0)
		{
		user.setDowncredits(x-20);
		
		Advertisements.setVcredits(10);
		Advertisements gotAdvertisements = advertisementService.save(Advertisements);
		userRepo.save(user);
		
		
		if(gotAdvertisements == null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		
		//currentUser.setDowncredits(x-2);
		
		return new ResponseEntity<>(gotAdvertisements, HttpStatus.CREATED);
	}
		else {
			return new ResponseEntity<>("No credits left",HttpStatus.OK);
		}
	}
	/*
	@GetMapping("/others")
	public ResponseEntity<?> getAdvertisementByOthers(@CurrentUser UserPrincipal currentUser){
		List<Long> userIds = new ArrayList<>();
		userIds.add(currentUser.getId());
		List<Advertisements> advertisementByOthers = advertisementService.findByUidNotIn(userIds);
		if(advertisementByOthers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(advertisementByOthers, HttpStatus.OK);
	}
*/	
	@GetMapping("/my")
	public ResponseEntity<?> getMyAdvertisements(@CurrentUser UserPrincipal currentUser){
		List<Long> userIds = new ArrayList<>();
		userIds.add(currentUser.getId());
		List<Advertisements> advertisementByOthers = advertisementService.findByEidIn(userIds);
		if(advertisementByOthers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(advertisementByOthers, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}/clicks")

	public boolean clickify(@PathVariable int id) {
		
		advertisementService.UpdateClick(id);
		return true;
	}
	@CrossOrigin(origins="*")
	@GetMapping("/AdId")
	public Long getAdId(@RequestBody Advertisements ad ) {
		
		return ad.getId();
	}
}
