package rest;

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
import dto.Rfid;
import service.ServiceFacade;

@Path("/Rfid")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RfidRest {
	@Path("/UpdateRfid")
	@POST
	@PermitAll

	public void UpdateRfid(Rfid rfid) throws Exception {
		ServiceFacade.getInstance().UpdateRfid(rfid);

	}
	@Path("/GetAllRfid")
	@GET
	@PermitAll
	public List<Rfid> GetAllRfid() throws Exception {
		return ServiceFacade.getInstance().GetAllRfid();
	}
	
	@Path("/addRfid")
	@GET
	@PermitAll
	 
	public void addRfid(Rfid rfid) throws Exception {
		ServiceFacade.getInstance().addRfid(rfid);
	}
}
