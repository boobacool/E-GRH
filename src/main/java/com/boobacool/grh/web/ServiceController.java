package com.boobacool.grh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boobacool.grh.modeles.Service;
import com.boobacool.grh.repository.ServiceRepository;

@Controller
@RequestMapping(value="/service")
public class ServiceController {
	
	@Autowired
	private ServiceRepository serviceRepository;

	
	@RequestMapping(value = "/rajoutersrc")
    public String servicePage(Model model, @RequestParam(defaultValue = "0") int page) {
       Service s= new Service();
        s.setId(0L);
        model.addAttribute("s", s);
        
        Page<Service> listes= serviceRepository.findAll(new PageRequest(page, 10));
        int pagescount = listes.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listes", listes);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "service";
    }	
	
	@RequestMapping(value="savesrc",method=RequestMethod.POST)
	public String saveService(Service s,Model model) {
		serviceRepository.save(s);
		return "redirect:/service/rajoutersrc";
		
	}
	@RequestMapping(value="/supprimersrc")
	public String deleteService(Long id)
	{
		serviceRepository.delete(id);
		return "redirect:/service/rajoutersrc";
	}
	
	@RequestMapping(value="/modifiersrc")
	public String editTp(Service s)
	{
		serviceRepository.save(s);
		
		return "redirect:/service/rajoutersrc";
	}
	
	
	@RequestMapping(value="/modifiersrcR")
	public String editRedirect(Long id,Model model, @RequestParam(defaultValue = "0") int page)
	{
		Service s= serviceRepository.getOne(id);
        model.addAttribute("s", s);
        
        Page<Service> listes= serviceRepository.findAll(new PageRequest(page, 10));
        int pagescount = listes.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listes", listes);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        return "service";
	}
}
