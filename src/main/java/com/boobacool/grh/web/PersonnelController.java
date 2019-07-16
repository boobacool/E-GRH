package com.boobacool.grh.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.boobacool.grh.modeles.Antenne;
import com.boobacool.grh.modeles.Personnel;
import com.boobacool.grh.modeles.Service;
import com.boobacool.grh.modeles.Specialite;
import com.boobacool.grh.modeles.TypePersonnel;
import com.boobacool.grh.repository.AntenneRepository;
import com.boobacool.grh.repository.PersonnelRepository;
import com.boobacool.grh.repository.ServiceRepository;
import com.boobacool.grh.repository.SpecialiteRepository;
import com.boobacool.grh.repository.TypePersonnelRepository;
import com.boobacool.grh.util.FormatDate;

@Controller
@RequestMapping(value="personnel")
public class PersonnelController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonnelController.class);
	
	@Autowired
	private ServiceRepository serviceRepository;
	@Autowired
	private SpecialiteRepository specialiteRepository;
	@Autowired
	private TypePersonnelRepository tPersonnelRepository;
	@Autowired
	private AntenneRepository antenneRepository;
	@Autowired
	private PersonnelRepository personnelRepository;
	
	String m=null;
	

	
	
	@RequestMapping(value = "/rajouterpers")
    public String formateurPage(Model model, @RequestParam(defaultValue = "0") int page) {
        Personnel pers= new Personnel();
        pers.setId(0L);
        model.addAttribute("pers", pers);
        List<Service> listeSrc=serviceRepository.findAll();
        model.addAttribute("listeSrc", listeSrc);
        List<Specialite> listesp=specialiteRepository.findAll();
        model.addAttribute("listesp", listesp);
        List<TypePersonnel> listetp=tPersonnelRepository.findAll();
        model.addAttribute("listetp", listetp);
        List<Antenne> listeant=antenneRepository.findAll();
        model.addAttribute("listeant", listeant);
        
        Page<Personnel> listepers= personnelRepository.findAll(new PageRequest(page, 10));
        int pagescount = listepers.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listepers", listepers);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "personnel";
    }	
	
	@RequestMapping(value="/savepers",method=RequestMethod.POST)
	public String savePers(@Valid Personnel pers, BindingResult bindingResult,Model model) throws ParseException {
		int page=0;
		
		if (bindingResult.hasErrors()) 
		{
			
			 bindingResult.getAllErrors().forEach(err -> {
				 m+=err.getDefaultMessage()+"\n";
	             });
			 model.addAttribute("m", m);
			
	        model.addAttribute("pers", pers);
	        List<Service> listeSrc=serviceRepository.findAll();
	        model.addAttribute("listeSrc", listeSrc);
	        List<Specialite> listesp=specialiteRepository.findAll();
	        model.addAttribute("listesp", listesp);
	        List<TypePersonnel> listetp=tPersonnelRepository.findAll();
	        model.addAttribute("listetp", listetp);
	        List<Antenne> listeant=antenneRepository.findAll();
	        model.addAttribute("listeant", listeant);
	        
	        Page<Personnel> listepers= personnelRepository.findAll(new PageRequest(page, 10));
	        int pagescount = listepers.getTotalPages();
	        int[] pages= new int[pagescount];
	        for(int i=0;i<pagescount;i++) pages[i]=i;
	        model.addAttribute("listepers", listepers);
	        model.addAttribute("pages", pages);
	        model.addAttribute("pageEnCours", page);
	        return "personnel";
		}
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String date=null;
		date=df.format(pers.getDateentree());
		//System.out.println(date);
		String t[] =date.split("-");
		Date dd=FormatDate.ajouterAnnee(t[2],t[1], t[0], pers.getNbreannee());
		pers.setDatesortie(dd);
		personnelRepository.save(pers);
		return "redirect:/personnel/rajouterpers";
		
	}
	@RequestMapping(value="/supprimerpers")
	public String deletePers(Long id)
	{
		personnelRepository.delete(id);
		return "redirect:/personnel/rajouterpers";
	}
	
	@RequestMapping(value="/modifierfteur")
	public String editFormateur(Personnel pers)
	{
		personnelRepository.save(pers);
		
		return "redirect:/personnel/rajouterpers";
	}
	
	
	@RequestMapping(value="/modifierpersR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		Personnel pers= personnelRepository.getOne(id);
        model.addAttribute("pers", pers);
        
        List<Service> listeSrc=serviceRepository.findAll();
        model.addAttribute("listeSrc", listeSrc);
        List<Specialite> listesp=specialiteRepository.findAll();
        model.addAttribute("listesp", listesp);
        List<TypePersonnel> listetp=tPersonnelRepository.findAll();
        model.addAttribute("listetp", listetp);
        List<Antenne> listeant=antenneRepository.findAll();
        model.addAttribute("listeant", listeant);
        
        Page<Personnel> listepers= personnelRepository.findAll(new PageRequest(page, 10));
        int pagescount = listepers.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listepers", listepers);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "personnel";
	}
	
	/*
	@RequestMapping(value="/fteurId/",method=RequestMethod.GET)
	@ResponseBody
	public List<Formateur> listeFormateur(@PathParam("id") Long id){
		Entreprise entre=entrepriseRepository.getOne(id);
		List<Formateur> liste= formateurRepository.findByEntreprise(entre);
		return liste;
	}
	*/
}
