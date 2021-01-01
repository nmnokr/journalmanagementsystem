package rest;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.Article;
import service.ServiceFacade;

@Path("/Hakem")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RefereeRest {
	
	@Path("/onayMakaleHakem")
	@POST
	@PermitAll
	public void onayMakaleEditor(Long Aid) throws Exception {
		ServiceFacade.getInstance().onayMakaleHakem(Aid); 
	}
	@Path("/redMakaleHakem")
	@POST
	@PermitAll
	public void redMakaleEditor(Long Aid) throws Exception {
		ServiceFacade.getInstance().redMakaleHakem(Aid);
	}
	
}
