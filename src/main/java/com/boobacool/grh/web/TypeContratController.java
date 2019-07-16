package com.boobacool.grh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boobacool.grh.modeles.Specialite;
import com.boobacool.grh.modeles.TypeContrat;
import com.boobacool.grh.repository.TypeContratRepository;

@Controller
@RequestMapping(value="/tc")
public class TypeContratController {
	
	
	@Autowired
	private TypeContratRepository typeContratRepository;
	
	
	@RequestMapping(value = "/rajoutertc")
    public String specialitePage(Model model, @RequestParam(defaultValue = "0") int page) {
       TypeContrat tc= new TypeContrat();
        tc.setId(0L);
        model.addAttribute("tc", tc);      
        Page<TypeContrat> listetc= typeContratRepository.findAll(new PageRequest(page, 10));
        int pagescount = listetc.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listetc", listetc);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "tc";
    }	
	
	@RequestMapping(value="savetc",method=RequestMethod.POST)
	public String saveTypeContrat(TypeContrat tc,Model model) {
		typeContratRepository.save(tc);
		return "redirect:/tc/rajoutertc";
		
	}
	@RequestMapping(value="/supprimertc")
	public String deleteSpecialite(Long id)
	{
		typeContratRepository.delete(id);
		return "redirect:/tc/rajoutertc";
	}
	
	@RequestMapping(value="/modifiertc")
	public String editsp(TypeContrat tc)
	{
		typeContratRepository.save(tc);
		
		return "redirect:/tc/rajoutertc";
	}
	
	
	@RequestMapping(value="/modifiertcR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		TypeContrat tc= typeContratRepository.getOne(id);
        model.addAttribute("tc", tc);    
        Page<TypeContrat> listetc= typeContratRepository.findAll(new PageRequest(page, 10));
        int pagescount = listetc.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listetc", listetc);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "tc";
	}

}
