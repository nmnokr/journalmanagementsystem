package rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.Article;
import dto.Editor;
 
import dto.Personel;
import service.ServiceFacade;

@Path("/Yazar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArticleRest {
	 

		@Path("/addMakale")
		@POST
		@PermitAll 
		public void addMakale(Article article) throws Exception {
			ServiceFacade.getInstance().addMakale(article);
		}
		@Path("/getAllIdMakale")
		@POST
		@PermitAll 
		public List<Article> getAllMakale(Long Arid) throws Exception {
			return ServiceFacade.getInstance().getAllArticle(Arid);
		}
       //Yazar aid göre makale listesi
		@Path("/searchMakaleaid")
		@POST
		@PermitAll  //daha sonra degiþtirecek
		public ArrayList<Article> searchMakaleaid(Long id) throws Exception {
			return ServiceFacade.getInstance().searchMakaleaid( id);
		}
		
		@Path("/Getallrefereesapproval")
		@POST
		@PermitAll  //daha sonra degiþtirecek
		public ArrayList<Article> Getallrefereesapproval(Long Aid) throws Exception {
			return ServiceFacade.getInstance().Getallrefereesapproval(Aid);
		}
		

		@Path("/searchMakaleArid")
		@POST
		@PermitAll
		public Article searchMakaleArid(Long Arid) throws Exception {
			return ServiceFacade.getInstance().searchMakaleArid(Arid);
		}
		
	
		

		
		
	}

