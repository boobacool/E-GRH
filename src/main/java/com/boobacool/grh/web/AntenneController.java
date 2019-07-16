package com.boobacool.grh.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boobacool.grh.modeles.Antenne;

import com.boobacool.grh.repository.AntenneRepository;

@Controller
@RequestMapping(value="/antenne")
public class AntenneController {
	
	@Autowired
	private AntenneRepository antenneRepository;
	
	
	@RequestMapping(value = "/rajouteran")
    public String servicePage(Model model, @RequestParam(defaultValue = "0") int page) {
       Antenne an= new Antenne();
        an.setId(0L);
        model.addAttribute("an", an);
        
        Page<Antenne> listeans= antenneRepository.findAll(new PageRequest(page, 10));
        int pagescount = listeans.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listeans", listeans);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "antenne";
    }	
	
	@RequestMapping(value="savean",method=RequestMethod.POST)
	public String saveAntenne(Antenne an,Model model) {
		antenneRepository.save(an);
		return "redirect:/antenne/rajouteran";
		
	}
	@RequestMapping(value="/supprimeran")
	public String deleteAntenne(Long id)
	{
		antenneRepository.delete(id);
		return "redirect:/antenne/rajouteran";
	}
	
	@RequestMapping(value="/modifieran")
	public String editTp(Antenne an)
	{
		antenneRepository.save(an);
		
		return "redirect:/antenne/rajouteran";
	}
	
	
	@RequestMapping(value="/modifieranR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		Antenne an= antenneRepository.getOne(id);
        model.addAttribute("an", an);
        
        Page<Antenne> listeans= antenneRepository.findAll(new PageRequest(page, 10));
        int pagescount = listeans.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listeans", listeans);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "antenne";
	}

	@RequestMapping(value="/restAntenne")
	@ResponseBody
	public List<Antenne> listeantenne() {
		return antenneRepository.findAll();
	}
}
