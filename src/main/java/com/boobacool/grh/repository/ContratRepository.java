package com.boobacool.grh.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boobacool.grh.modeles.Contrat;
import com.boobacool.grh.modeles.Personnel;

public interface ContratRepository extends JpaRepository<Contrat, Long>{
	
	@Query(value="update contrat c set c.etat=0 where c.personnel=:id", nativeQuery=true)
	public void miseAJour(@Param("id")Long id);
	
	public List<Contrat> findByPersonnel(Personnel p);
	
	@Query(value="SELECT c FROM Contrat c INNER JOIN FETCH c.personnel p WHERE p.nom like :nom or p.prenom like :prenom or p.contact like :contact",
			countQuery = "SELECT c FROM Contrat c INNER JOIN FETCH c.personnel p WHERE p.nom like :nom or p.prenom like :prenom or p.contact like :contact")
	Page<Contrat> rechercheContrat( @Param("nom")String nom,@Param("prenom")String prenom,@Param("contact")String contact, Pageable pageable);

	@Query(value="SELECT c FROM Contrat c where c.etat=1")
	public List<Contrat> listeActive();


}
