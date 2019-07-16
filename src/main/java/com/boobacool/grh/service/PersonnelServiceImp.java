package com.boobacool.grh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.boobacool.grh.modeles.Personnel;
import com.boobacool.grh.repository.PersonnelRepository;
@Service
public class PersonnelServiceImp implements PersonnelService{

	@Autowired
	private PersonnelRepository personnelRepository;
	@Override
	public void ajouter(Personnel p) {
		personnelRepository.save(p);
		
	}

	@Override
	public void modifier(Personnel p) {
		//personnelRepository.save(p);
		
	}

	@Override
	public void supprimer(Long id) {
		personnelRepository.delete(id);
	
	}

	@Override
	public Page<Personnel> listep() {
		
		return personnelRepository.findAll( new PageRequest(1, 2));
	}

}
