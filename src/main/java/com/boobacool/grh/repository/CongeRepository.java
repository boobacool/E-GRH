package com.boobacool.grh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boobacool.grh.modeles.Conge;
import com.boobacool.grh.modeles.Personnel;

public interface CongeRepository extends JpaRepository<Conge, Long>{
	
	@Query(value="SELECT c FROM Conge c INNER JOIN FETCH c.personnel p WHERE p.nom like :nom or p.prenom like :prenom or p.contact like :contact",
			countQuery = "SELECT c FROM Conge c INNER JOIN FETCH c.personnel p WHERE p.nom like :nom or p.prenom like :prenom or p.contact like :contact")
	Page<Conge> rechercheConge( @Param("nom")String nom,@Param("prenom")String prenom,@Param("contact")String contact, Pageable pageable);

	@Query(value="SELECT * FROM conge c WHERE c.personnel=:p and year(c.datedebut)=:an",nativeQuery=true)
	List<Conge> listeCongeParPers(@Param("p") Long p,@Param("an") int an);
	
	
	

}
