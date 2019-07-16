package com.boobacool.grh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boobacool.grh.modeles.Antenne;
import com.boobacool.grh.modeles.Personnel;
import com.boobacool.grh.modeles.TypePersonnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Long>{

	@Query(value="select p from Personnel p where p.matricule= :m")
	public Personnel findByMatricule(@Param("m")String mat);
	
	public List<Personnel> findByAntenneAndTypePersonnel(Antenne ant, TypePersonnel tp);
	
	
}
