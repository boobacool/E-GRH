package com.boobacool.grh.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.boobacool.grh.modeles.Antenne;
import com.boobacool.grh.modeles.Conge;
import com.boobacool.grh.modeles.Contrat;
import com.boobacool.grh.modeles.Personnel;
import com.boobacool.grh.modeles.TypeConge;
import com.boobacool.grh.modeles.TypeContrat;
import com.boobacool.grh.modeles.TypePersonnel;
import com.boobacool.grh.repository.AntenneRepository;
import com.boobacool.grh.repository.CongeRepository;
import com.boobacool.grh.repository.PersonnelRepository;
import com.boobacool.grh.repository.TypeCongeRepository;
import com.boobacool.grh.repository.TypePersonnelRepository;
import com.boobacool.grh.util.FormatDate;

@Controller
@RequestMapping(value="conge")
public class CongeController {
	
	@Autowired
	private TypeCongeRepository tcongeRepository;
	@Autowired
	private CongeRepository congeRepository;
	
	@Autowired
	private PersonnelRepository personnelRepository;
	
	@Autowired
	private TypePersonnelRepository tpersonnelRepository;
	
	@Autowired
	private AntenneRepository antenneRepository;
	
	String m=null; 
	int val=0;
	

	
	
	@RequestMapping(value = "/rajoutercong")
    public String congePage(Model model) {
        Conge cong= new Conge();
        cong.setId(0L);
        model.addAttribute("cong", cong);
        List<TypeConge> listetcongs=tcongeRepository.findAll();
        model.addAttribute("listetcongs", listetcongs);
        List<Personnel> listep= new ArrayList<>();
        model.addAttribute("listep", listep);
        List<TypePersonnel> listetp=tpersonnelRepository.findAll();
        model.addAttribute("listetp", listetp);
        List<Antenne> listeant=antenneRepository.findAll();
        model.addAttribute("listeant", listeant);

        return "conge";
    }
	@RequestMapping(value="/modifiercongR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		Conge cong= congeRepository.getOne(id);
		model.addAttribute("cong", cong);
        List<TypeConge> listetcongs=tcongeRepository.findAll();
        model.addAttribute("listetcongs", listetcongs);
        List<Personnel> listep= new ArrayList<>();
        model.addAttribute("listep", listep);
        List<TypePersonnel> listetp=tpersonnelRepository.findAll();
        model.addAttribute("listetp", listetp);
        List<Antenne> listeant=antenneRepository.findAll();
        model.addAttribute("listeant", listeant);

        return "conge";
	}
	
	@RequestMapping(value="/savecong",method=RequestMethod.POST)
	public String saveCont(@Valid Conge cong, BindingResult bindingResult,Model model) throws ParseException {
		
		
		if (bindingResult.hasErrors()) 
		{
			
			 bindingResult.getAllErrors().forEach(err -> {
				 m+=err.getDefaultMessage()+"\n";
	             });
			 model.addAttribute("m", m);
			
			    model.addAttribute("cong", cong);
		        List<TypeConge> listetcongs=tcongeRepository.findAll();
		        model.addAttribute("listetcongs", listetcongs);
		        List<Personnel> listep= new ArrayList<>();
		        model.addAttribute("listep", listep);
		        List<TypePersonnel> listetp=tpersonnelRepository.findAll();
		        model.addAttribute("listetp", listetp);
		        List<Antenne> listeant=antenneRepository.findAll();
		        model.addAttribute("listeant", listeant);

		        return "conge";
	       
		}
		    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String date=null;
			date=df.format(cong.getDatedebut());
			String t[] =date.split("-");
			Date dd=FormatDate.ajouterJour(t[2],t[1], t[0], cong.getNbjours());	
		    cong.setDatefin(dd);
		    congeRepository.save(cong);
		    val=1;
		    model.addAttribute("val", val);
			
		    model.addAttribute("cong", cong);
	        List<TypeConge> listetcongs=tcongeRepository.findAll();
	        model.addAttribute("listetcongs", listetcongs);
	        List<Personnel> listep= new ArrayList<>();
	        model.addAttribute("listep", listep);
	        List<TypePersonnel> listetp=tpersonnelRepository.findAll();
	        model.addAttribute("listetp", listetp);
	        List<Antenne> listeant=antenneRepository.findAll();
	        model.addAttribute("listeant", listeant);

	        return "conge";
		
	}
	

	@RequestMapping(value = "/listeConge")
    public String contiPage(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(name="mc",defaultValue = "") String mc) {
        
        Page<Conge> listecont= congeRepository.rechercheConge("%"+mc+"%", "%"+mc+"%", "%"+mc+"%", new PageRequest(page, 10));
        int pagescount = listecont.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listecont", listecont);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        model.addAttribute("mc", mc);
        return "listeConge";
    }
	
	
	@RequestMapping(value="/supprimerconge")
	public String deleteConge(Long id)
	{
		congeRepository.delete(id);
		return "redirect:/conge/listeConge";
	}

}
