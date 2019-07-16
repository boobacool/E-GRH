package com.boobacool.grh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boobacool.grh.modeles.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long>{
	@Query(value="SELECT c FROM Mission c INNER JOIN FETCH c.personnel p WHERE p.nom like :nom or p.prenom like :prenom or p.contact like :contact",
			countQuery = "SELECT c FROM Mission c INNER JOIN FETCH c.personnel p WHERE p.nom like :nom or p.prenom like :prenom or p.contact like :contact")
	Page<Mission> rechercheMission( @Param("nom")String nom,@Param("prenom")String prenom,@Param("contact")String contact, Pageable pageable);

	@Query(value="SELECT * FROM mission c WHERE c.personnel=:p and year(c.datedebut)=:an",nativeQuery=true)
	List<Mission> listeMissionParPers(@Param("p") Long p,@Param("an") int an);
	
 
	@Query(value="SELECT c FROM mission m WHERE year(c.datedebut)=:an",nativeQuery=true)
	List<Mission> listeParAn(@Param("an")int an);
	

}
