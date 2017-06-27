package controller;

import model.Genere;
import view.GenereView;


public class GenereController {
	   private Genere model;
	   private GenereView view;

	   public GenereController(Genere model, GenereView view){
	      this.model = model;
	      this.view = view;
	   }
	   
		// ------------------------------------------------ RECUPERO INFO BASE(controller)

	   public Integer getGenereId(){
		   return model.getId();
	   }

	   public String getGenereNome(){
	      return model.getNome();		
	   }
	   
		// ------------------------------------------------ SETTAGGIO DATI BASE(controller)

	   public void setGenereId(Integer id){
		   model.setId(id);
	   }

	   public void setGenereNome(String nome){
		   model.setNome(nome);		
	   }
	   
	   
	   public void updateView(){				
	      view.printGenere(model.getId(), model.getNome());
	   }	
	   
	   
	   
	   
	}



