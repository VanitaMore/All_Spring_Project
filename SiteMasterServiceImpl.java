package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ContactMaster;
import com.example.demo.model.ShiftMaster;
import com.example.demo.model.SiteMaster;
import com.example.demo.repository.ContactMasterRepository;
import com.example.demo.repository.ShiftMasterRepository;
import com.example.demo.repository.SiteMasterRepository;

@Service
public class SiteMasterServiceImpl implements SiteMasterService {

	@Autowired 
	SiteMasterRepository siteMasterRepository;
	@Autowired
	ShiftMasterRepository shiftMasterRepository;
	
	@Autowired
	ContactMasterRepository contactMasterRepository;
	
	@Override
	public List<SiteMaster> getSiteMasterDetail() {
		return siteMasterRepository.findAll();
	}
	
	@Override
	public SiteMaster createNewSite(SiteMaster siteMaster) {
		
		List<ShiftMaster> shiftMasterList=siteMaster.getShiftMasters();
		List<ContactMaster> contactMasterList=siteMaster.getContactMaster();
		
		siteMaster.setShiftMasters(new ArrayList<ShiftMaster>());
		siteMaster.setContactMaster(new ArrayList<ContactMaster>());
		
		SiteMaster	siteMaster1 =siteMasterRepository.save(siteMaster);
		
		shiftMasterList.forEach(shiftmaster->{
			shiftmaster.setSiteMaster(siteMaster1);
			shiftMasterRepository.save(shiftmaster);
		});
		
		contactMasterList.forEach(conMaster->{
			conMaster.setSiteMasterContact(siteMaster1);
			contactMasterRepository.save(conMaster);
		});
		//System.out.println(siteMaster1.getBusinessId());
		
		System.out.println("shift Master--->"+shiftMasterList);
		
		return siteMasterRepository.getOne(siteMaster1.getSiteId());
		
	}

	/*
	 * @Override
	 * 
	 * @Transactional public SiteMaster createNewSite(SiteMaster siteMaster) {
	 * SiteMaster siteMaster1
	 * =siteMasterRepository.save(setSiteMasterData(siteMaster));
	 * 
	 * shiftMasterRepository.saveAll(setShiftMasterData(siteMaster.getShiftMasters()
	 * , siteMaster1));
	 * siteMaster1.setShiftMasters(shiftMasterRepository.findShiftMasterBySiteMaster
	 * (siteMaster1)); return siteMaster1;
	 * 
	 * }
	 * 
	 * private SiteMaster setSiteMasterData(SiteMaster siteMaster) { SiteMaster
	 * siteMasterOne=new SiteMaster();
	 * siteMasterOne.setAddress1(siteMaster.getAddress1());
	 * siteMasterOne.setCity(siteMaster.getCity());
	 * siteMasterOne.setCountry(siteMaster.getCountry());
	 * siteMasterOne.setCreatedDate(siteMaster.getCreatedDate());
	 * siteMasterOne.setModifiedDate(siteMaster.getModifiedDate()); return
	 * siteMasterOne;
	 * 
	 * }
	 * 
	 * private List<ShiftMaster> setShiftMasterData(List<ShiftMaster>
	 * shiftMasters,SiteMaster siteMaster ){
	 * 
	 * List<ShiftMaster> shiftMastersList=new ArrayList<ShiftMaster>();
	 * shiftMasters.forEach(shiftmaster->{ ShiftMaster shiftMaster=new
	 * ShiftMaster();
	 * 
	 * shiftMaster.setSiteMaster(siteMaster); shiftMaster.setCreatedDate(new
	 * Date()); shiftMaster.setModifiedDate(new Date());
	 * shiftMastersList.add(shiftMaster); });
	 * 
	 * System.out.println(shiftMasters); return shiftMastersList;
	 * 
	 * }
	 */
	
	/*
	 * @Override public SiteMaster insertData(SiteMaster siteMaster) {
	 * 
	 * List<ShiftMaster> shiftMasterList=siteMaster.getShiftMasters();
	 * siteMaster.setShiftMasters(new ArrayList<ShiftMaster>()); SiteMaster
	 * siteMaster1 =siteMasterRepository.save(siteMaster);
	 * shiftMasterList.forEach(shiftmaster->{
	 * shiftmaster.setSiteMaster(siteMaster1);
	 * shiftMasterRepository.save(shiftmaster); });
	 * System.out.println("shift Master--->"+shiftMasterList);
	 * 
	 * return siteMasterRepository.getOne(siteMaster1.getSiteId());
	 * 
	 * }
	 * 
	 * @Override public SiteMaster insertData(SiteMaster siteMaster) {
	 * 
	 * SiteMaster siteMaster1 =siteMasterRepository.save(siteMaster);
	 * List<ShiftMaster> shiftMasterList=siteMaster1.getShiftMasters();
	 * shiftMasterList.forEach(shiftmaster->{
	 * shiftmaster.setSiteMaster(siteMaster1);
	 * shiftMasterRepository.save(shiftmaster); });
	 * System.out.println("shift Master--->"+shiftMasterList);
	 * 
	 * return siteMasterRepository.getOne(siteMaster1.getSiteId());
	 * 
	 * }
	 */
	

	@Override
	public void siteDelete(String siteID) {
		siteMasterRepository.deleteById(siteID);
	}
	
    

}
