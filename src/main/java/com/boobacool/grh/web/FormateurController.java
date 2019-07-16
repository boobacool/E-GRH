package com.boobacool.grh.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boobacool.grh.modeles.Entreprise;
import com.boobacool.grh.modeles.Formateur;
import com.boobacool.grh.modeles.Specialite;
import com.boobacool.grh.repository.EntrepriseRepository;
import com.boobacool.grh.repository.FormateurRepository;
import com.boobacool.grh.repository.SpecialiteRepository;
import com.boobacool.grh.util.FormatDate;

@Controller
@RequestMapping(value="/formateur")
public class FormateurController {
	
	@Autowired
	private FormateurRepository formateurRepository;
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private SpecialiteRepository specialiteRepository;
	
	
	@RequestMapping(value = "/rajouterfteur")
    public String formateurPage(Model model, @RequestParam(defaultValue = "0") int page) {
       Formateur fteur= new Formateur();
        fteur.setId(0L);
        model.addAttribute("fteur", fteur);
        List<Entreprise> listent=entrepriseRepository.findAll();
        model.addAttribute("listent", listent);
        List<Specialite> listesp=specialiteRepository.findAll();
        model.addAttribute("listesp", listesp);
        Page<Formateur> listefteurs= formateurRepository.findAll(new PageRequest(page, 10));
        int pagescount = listefteurs.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listefteurs", listefteurs);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "formateur";
    }	
	
	@RequestMapping(value="savefteur",method=RequestMethod.POST)
	public String saveFormateur(Formateur fteur,Model model) throws ParseException {
		//DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		//String date=null;
		//date=df.format(fteur.getDatenaiss());
		//System.out.println(date);
		//String t[] =date.split("-");
		//Date dd=FormatDate.ajouterAnnee(t[2],t[1], t[0], 10);		
		formateurRepository.save(fteur);
		return "redirect:/formateur/rajouterfteur";
		
	}
	@RequestMapping(value="/supprimerfteur")
	public String deleteFormateur(Long id)
	{
		formateurRepository.delete(id);
		return "redirect:/formateur/rajouterfteur";
	}
	
	@RequestMapping(value="/modifierfteur")
	public String editFormateur(Formateur fteur)
	{
		formateurRepository.save(fteur);
		
		return "redirect:/formateur/rajouterfteur";
	}
	
	
	@RequestMapping(value="/modifierfteurR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		Formateur fteur= formateurRepository.getOne(id);
        model.addAttribute("fteur", fteur);
        
        List<Entreprise> listent=entrepriseRepository.findAll();
        model.addAttribute("listent", listent);
        List<Specialite> listesp=specialiteRepository.findAll();
        model.addAttribute("listesp", listesp);
        Page<Formateur> listefteurs= formateurRepository.findAll(new PageRequest(page, 10));
        int pagescount = listefteurs.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listefteurs", listefteurs);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "formateur";
	}
	
	
	@RequestMapping(value="/fteurId/",method=RequestMethod.GET)
	@ResponseBody
	public List<Formateur> listeFormateur(@PathParam("id") Long id){
		Entreprise entre=entrepriseRepository.getOne(id);
		List<Formateur> liste= formateurRepository.findByEntreprise(entre);
		return liste;
	}

}
