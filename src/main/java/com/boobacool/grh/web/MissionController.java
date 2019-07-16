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
import com.boobacool.grh.modeles.Mission;
import com.boobacool.grh.modeles.Personnel;
import com.boobacool.grh.modeles.TypeMission;
import com.boobacool.grh.modeles.TypePersonnel;
import com.boobacool.grh.repository.AntenneRepository;
import com.boobacool.grh.repository.MissionRepository;
import com.boobacool.grh.repository.PersonnelRepository;
import com.boobacool.grh.repository.TypeMissionRepository;
import com.boobacool.grh.repository.TypePersonnelRepository;
import com.boobacool.grh.util.FormatDate;

@Controller
@RequestMapping(value = "mission")
public class MissionController {

	@Autowired
	private TypeMissionRepository tmissionRepository;
	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private PersonnelRepository personnelRepository;

	@Autowired
	private TypePersonnelRepository tpersonnelRepository;

	@Autowired
	private AntenneRepository antenneRepository;

	String m = null;
	int val = 0;

	@RequestMapping(value = "/rajoutermiss")
	public String missPage(Model model) {
		Mission miss = new Mission();
		miss.setId(0L);
		model.addAttribute("miss", miss);
		List<TypeMission> listetm = tmissionRepository.findAll();
		model.addAttribute("listetm", listetm);
		List<Personnel> listep = new ArrayList<>();
		model.addAttribute("listep", listep);
		List<TypePersonnel> listetp = tpersonnelRepository.findAll();
		model.addAttribute("listetp", listetp);
		List<Antenne> listeant = antenneRepository.findAll();
		model.addAttribute("listeant", listeant);

		return "mission";
	}

	@RequestMapping(value = "/modifiermissR")
	public String editRedirect(Long id, Model model, @RequestParam(defaultValue = "0") int page) {
		Mission miss = missionRepository.getOne(id);
		model.addAttribute("miss", miss);
		List<TypeMission> listetm = tmissionRepository.findAll();
		model.addAttribute("listetm", listetm);
		List<Personnel> listep = new ArrayList<>();
		model.addAttribute("listep", listep);
		List<TypePersonnel> listetp = tpersonnelRepository.findAll();
		model.addAttribute("listetp", listetp);
		List<Antenne> listeant = antenneRepository.findAll();
		model.addAttribute("listeant", listeant);

		return "mission";
	}

	@RequestMapping(value = "/savemiss", method = RequestMethod.POST)
	public String saveMiss(@Valid Mission miss, BindingResult bindingResult, Model model) throws ParseException {

		if (bindingResult.hasErrors()) {

			bindingResult.getAllErrors().forEach(err -> {
				m += err.getDefaultMessage() + "\n";
			});
			model.addAttribute("m", m);

			model.addAttribute("miss", miss);
			List<TypeMission> listetm = tmissionRepository.findAll();
			model.addAttribute("listetm", listetm);
			List<Personnel> listep = new ArrayList<>();
			model.addAttribute("listep", listep);
			List<TypePersonnel> listetp = tpersonnelRepository.findAll();
			model.addAttribute("listetp", listetp);
			List<Antenne> listeant = antenneRepository.findAll();
			model.addAttribute("listeant", listeant);

			return "mission";

		}
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String date=null;
		date=df.format(miss.getDatedebut());
		String t[] =date.split("-");
		Date dd=FormatDate.ajouterJour(t[2],t[1], t[0], miss.getNbjours());	
	    miss.setDatefin(dd);
	    miss.setEtat(0);

		missionRepository.save(miss);
		val = 1;
		model.addAttribute("val", val);
		return "redirect:/mission/rajoutermiss";

	}

	@RequestMapping(value = "/supprimermiss")
	public String deleteMiss(Long id) {
		missionRepository.delete(id);
		return "redirect:/mission/rajoutermiss";
	}

	@RequestMapping(value = "/listeMission")
	public String contiPage(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(name = "mc", defaultValue = "") String mc) {

		Page<Mission> listemiss = missionRepository.rechercheMission("%" + mc + "%", "%" + mc + "%", "%" + mc + "%",
				new PageRequest(page, 10));
		int pagescount = listemiss.getTotalPages();
		int[] pages = new int[pagescount];
		for (int i = 0; i < pagescount; i++)
			pages[i] = i;
		model.addAttribute("listemiss", listemiss);
		model.addAttribute("pages", pages);
		model.addAttribute("pageEnCours", page);
		model.addAttribute("mc", mc);
		return "listeMission";
	}

	@RequestMapping(value = "/supprimermission")
	public String deleteMission(Long id) {
		missionRepository.delete(id);
		return "redirect:/mission/listeMission";
	}
}
