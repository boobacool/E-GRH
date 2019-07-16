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
import com.boobacool.grh.modeles.TypeConge;
import com.boobacool.grh.modeles.TypeContrat;
import com.boobacool.grh.modeles.TypeMission;
import com.boobacool.grh.repository.TypeCongeRepository;
import com.boobacool.grh.repository.TypeContratRepository;
import com.boobacool.grh.repository.TypeMissionRepository;

@Controller
@RequestMapping(value="/tm")
public class TypeMissionController {
	
	
	@Autowired
	private TypeMissionRepository typeMissionRepository;
	
	
	@RequestMapping(value = "/rajoutertm")
    public String tcoPage(Model model, @RequestParam(defaultValue = "0") int page) {
       TypeMission tm= new TypeMission();
        tm.setId(0L);
        model.addAttribute("tm", tm);      
        Page<TypeMission> listetm= typeMissionRepository.findAll(new PageRequest(page, 10));
        int pagescount = listetm.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listetm", listetm);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "tm";
    }	
	
	@RequestMapping(value="savetm",method=RequestMethod.POST)
	public String saveTypeConge(TypeMission tm,Model model) {
		typeMissionRepository.save(tm);
		return "redirect:/tm/rajoutertm";
		
	}
	@RequestMapping(value="/supprimertm")
	public String deletetm(Long id)
	{
		typeMissionRepository.delete(id);
		return "redirect:/tm/rajoutertm";
	}
	
	@RequestMapping(value="/modifiertm")
	public String edittm(TypeMission tm)
	{
		typeMissionRepository.save(tm);
		
		return "redirect:/tm/rajoutertm";
	}
	
	
	@RequestMapping(value="/modifiertmR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		TypeMission tm= typeMissionRepository.getOne(id);
        model.addAttribute("tm", tm);    
        Page<TypeMission> listetm= typeMissionRepository.findAll(new PageRequest(page, 10));
        int pagescount = listetm.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listetm", listetm);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "tm";
	}

}
