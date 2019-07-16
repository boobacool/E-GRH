package com.boobacool.grh.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boobacool.grh.modeles.Antenne;
import com.boobacool.grh.modeles.Contrat;
import com.boobacool.grh.modeles.Entreprise;
import com.boobacool.grh.modeles.Formateur;
import com.boobacool.grh.modeles.Personnel;
import com.boobacool.grh.modeles.Specialite;
import com.boobacool.grh.modeles.TypeContrat;
import com.boobacool.grh.modeles.TypePersonnel;
import com.boobacool.grh.repository.AntenneRepository;
import com.boobacool.grh.repository.ContratRepository;
import com.boobacool.grh.repository.PersonnelRepository;
import com.boobacool.grh.repository.TypeContratRepository;
import com.boobacool.grh.repository.TypePersonnelRepository;

@Controller
@RequestMapping(value="contrat")
public class ContratController {
	
	
	@Autowired
	private TypeContratRepository tcontratRepository;
	@Autowired
	private ContratRepository contratRepository;
	
	@Autowired
	private PersonnelRepository personnelRepository;
	
	@Autowired
	private TypePersonnelRepository tpersonnelRepository;
	
	@Autowired
	private AntenneRepository antenneRepository;
	
	String m=null; 
	int val=0;
	

	
	
	@RequestMapping(value = "/rajoutercont")
    public String contratPage(Model model) {
        Contrat cont= new Contrat();
        cont.setId(0L);
        model.addAttribute("cont", cont);
        List<TypeContrat> listetconts=tcontratRepository.findAll();
        model.addAttribute("listetconts", listetconts);
        List<Personnel> listep= new ArrayList<>();
        model.addAttribute("listep", listep);
        List<TypePersonnel> listetp=tpersonnelRepository.findAll();
        model.addAttribute("listetp", listetp);
        List<Antenne> listeant=antenneRepository.findAll();
        model.addAttribute("listeant", listeant);

        return "contrat";
    }
	
	@RequestMapping(value="/modifiercontR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		Contrat cont= contratRepository.getOne(id);
		model.addAttribute("cont", cont);
        List<TypeContrat> listetconts=tcontratRepository.findAll();
        model.addAttribute("listetconts", listetconts);
        List<Personnel> listep= new ArrayList<>();
        model.addAttribute("listep", listep);
        List<TypePersonnel> listetp=tpersonnelRepository.findAll();
        model.addAttribute("listetp", listetp);
        List<Antenne> listeant=antenneRepository.findAll();
        model.addAttribute("listeant", listeant);
        return "contrat";
	}
	
	
	@RequestMapping(value="/savecont",method=RequestMethod.POST)
	public String saveCont(@Valid Contrat cont, BindingResult bindingResult,Model model) throws ParseException {
		
		
		if (bindingResult.hasErrors()) 
		{
			
			 bindingResult.getAllErrors().forEach(err -> {
				 m+=err.getDefaultMessage()+"\n";
	             });
			 model.addAttribute("m", m);
			
			    model.addAttribute("cont", cont);
		        List<TypeContrat> listetconts=tcontratRepository.findAll();
		        model.addAttribute("listetconts", listetconts);
		        List<Personnel> listep= new ArrayList<>();
		        model.addAttribute("listep", listep);
		        List<TypePersonnel> listetp=tpersonnelRepository.findAll();
		        model.addAttribute("listetp", listetp);
		        List<Antenne> listeant=antenneRepository.findAll();
		        model.addAttribute("listeant", listeant);

		        return "contrat";
	       
		}
		List<Contrat> listePers=contratRepository.findByPersonnel(cont.getPersonnel());
		if(listePers.size()!=0) {
			for(Contrat c:listePers) {
				c.setEtat(0);
			}
			contratRepository.save(listePers);
		}
		cont.setEtat(1);
		contratRepository.save(cont);
		val=1;
		model.addAttribute("val", val);	
		return "redirect:/contrat/rajoutercont";
		
	}
	@RequestMapping(value="/supprimercont")
	public String deleteCont(Long id)
	{
		contratRepository.delete(id);
		return "redirect:/contrat/rajoutercont";
	}
	
	
	
	@RequestMapping(value= {"/contId/","/contId/{ida}/{idtp}"},method=RequestMethod.GET)
	@ResponseBody
	public List<Personnel> listePersonnel(@PathParam("ida") Long ida,@PathParam("idtp") Long idtp){
		Antenne an=antenneRepository.getOne(ida);
		TypePersonnel tp=tpersonnelRepository.getOne(idtp);
		
		List<Personnel> liste= personnelRepository.findByAntenneAndTypePersonnel(an, tp);
		return liste;
	}
	
	
	@RequestMapping(value = "/listeContrat")
    public String contiPage(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(name="mc",defaultValue = "") String mc) {
        
        System.out.println(mc);
        Page<Contrat> listecont= contratRepository.rechercheContrat("%"+mc+"%", "%"+mc+"%", "%"+mc+"%", new PageRequest(page, 10));
        int pagescount = listecont.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listecont", listecont);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        model.addAttribute("mc", mc);
        return "listeContrat";
    }
	
	
	@RequestMapping(value="/supprimercontrat")
	public String deleteContrat(Long id)
	{
		contratRepository.delete(id);
		return "redirect:/contrat/listeContrat";
	}

	
}
