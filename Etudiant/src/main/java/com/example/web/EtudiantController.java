package com.example.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.IOFileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.EtudiantRepository;
import com.example.entities.Etudiant;

@Controller
public class EtudiantController {

	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Value("${dir.images}")
	private String imageDir;
	
	@RequestMapping(value="/index")
	public String index(Model model,
			@RequestParam(name="page",defaultValue="0")int p,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		
		Page<Etudiant> pageEtudiants=etudiantRepository.chercherEtudiants("%"+mc+"%", new PageRequest(p, 5));
		model.addAttribute("pageEtudiants",pageEtudiants);
		
		int pageCount =pageEtudiants.getTotalPages();
		int[] pages=new int[pageCount];
		
		for (int i=0;i<pageCount;i++) pages[i]=i;
		
		model.addAttribute("pages",pages);
		model.addAttribute("pageCourant", p);
		model.addAttribute("motcle", mc);
		
		return "etudiants";
		
	} 
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formEtudiant(Model model) {
		
		model.addAttribute("etudiant", new Etudiant());
		return "formEtudiant";
	}
	
	@RequestMapping(value="/saveEtudiant",method=RequestMethod.POST)
	public String saveEtudiant(Model model,@Valid Etudiant et,
							   BindingResult bindingResult,
							   @RequestParam(name="picture") MultipartFile file) throws Exception {
		
		if (bindingResult.hasErrors()) {
			
			return "formEtudiant";
		}
		
		String message="probleme: etudiant non ajouté";

		
		if (!file.isEmpty()) {
			
			et.setPhoto(file.getOriginalFilename());
			etudiantRepository.save(et);
			file.transferTo(new File(imageDir+et.getId()));
			message="etudiant ajouter avec succee";
		}
		
		return "redirect:index?message="+message;
	}
	
	
	@RequestMapping(value="/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		
		File f=new File(imageDir+id);
		
		byte[] bytesArray = new byte[(int) f.length()]; 

		  FileInputStream fis = new FileInputStream(f);
		  fis.read(bytesArray); //read file into bytes[]
		  fis.close();
					
		  return bytesArray;
	}
	
	@RequestMapping(value="/supprimer")
	public String supprimer(Long id) {
		
		etudiantRepository.deleteById(id);
		return "redirect:index";
	}
	
	@RequestMapping(value="/edite")
	public String edite(Long id,Model model) {
		
		Etudiant etudiant=etudiantRepository.getOne(id);
		model.addAttribute("etudiant",etudiant);
		
		return "updateEtudiant";
	}
	
	@RequestMapping(value="/saveUpdate",method=RequestMethod.POST)
	public String saveUpdate(Model model,@Valid Etudiant et,
							   BindingResult bindingResult,
							   @RequestParam(name="picture") MultipartFile file) throws Exception {
		
		if (bindingResult.hasErrors()) {
			
			return "updateEtudiant";
		}
		

		
		if (!file.isEmpty()) {
			
			et.setPhoto(file.getOriginalFilename());
			etudiantRepository.save(et);
			file.transferTo(new File(imageDir+et.getId()));
		}
		
		return "redirect:index";
	}
	
	
	
	
}
