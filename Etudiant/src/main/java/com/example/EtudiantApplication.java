package com.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.dao.EtudiantRepository;
import com.example.entities.Etudiant;

@SpringBootApplication
public class EtudiantApplication implements CommandLineRunner {

	@Autowired
	EtudiantRepository etudiantRepositoey;
	public static void main(String[] args) {
		
		SpringApplication.run(EtudiantApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {

		/*DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		etudiantRepositoey.save(new Etudiant("zakaria", df.parse("1994-06-21"),"chaouche.zakaria@yahoo.com", "zaki.jpg"));
		etudiantRepositoey.save(new Etudiant("rabah", df.parse("1962-06-11"),"chaouche.rabah@yahoo.com", "rabah.jpg"));
		etudiantRepositoey.save(new Etudiant("atmane", df.parse("1999-06-13"),"chaouche.atmane@yahoo.com", "atmane.jpg"));
		etudiantRepositoey.save(new Etudiant("youcef", df.parse("1998-06-26"),"chaouche.youcef@yahoo.com", "youcef.jpg"));
		etudiantRepositoey.save(new Etudiant("badro", df.parse("1988-06-29"),"chaouche.badro@yahoo.com", "badro.jpg"));
		etudiantRepositoey.save(new Etudiant("chemssou", df.parse("1991-06-30"),"chaouche.chemssou@yahoo.com", "chemssou.jpg"));
		etudiantRepositoey.save(new Etudiant("yacine", df.parse("1973-06-22"),"chaouche.yacine@yahoo.com", "yacine.jpg"));*/
	

		
		/*List<Etudiant> list=etudiantRepositoey.charcherEtudiants(df.parse("1994-06-11"),df.parse("1994-07-21"));
		
		for (Etudiant e: list) {
			
			System.out.println(e.getNom());
			
		}
		
		Page<Etudiant> etud=etudiantRepositoey.charcherEtudiants("%A%", new PageRequest(0, 5));
		
		etud.forEach(e->System.out.println(e.getNom())); */
	}

}
