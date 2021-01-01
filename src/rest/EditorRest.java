package rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.Article;
import dto.Author;
import dto.Editor;
 
import service.ServiceFacade;


@Path("/Editor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EditorRest {

 

	@Path("/addEditor")
	@POST
	@RolesAllowed("HR")

	public void addPersonel(Editor editor) throws Exception {
		ServiceFacade.getInstance().addEditor(editor);
	}
	//status 0 olan beklenen makaleler
	@Path("/WaitingGetAllMakale")
	@GET
	@PermitAll

	public List<Article> getAllWaitingArticle() throws Exception {
		return ServiceFacade.getInstance().getAllWaitingArticle();
	}
	
	@Path("/onayMakaleEditor")
	@POST
	@PermitAll
	public void onayMakaleEditor(Article article) throws Exception {
		ServiceFacade.getInstance().onayMakaleEditor(article);
	}
	

	@Path("/searchRefereeArid")
	@POST
	@PermitAll
	public List<Author>  searchRefereeArid(Long nace) throws Exception {
		return ServiceFacade.getInstance().searchRefereeArid(nace);
	}
	
	
	@Path("/redMakaleEditor")
	@POST
	@PermitAll
	public void redMakaleEditor(Article article) throws Exception {
		ServiceFacade.getInstance().redMakaleEditor(article);
	}
	
	

}