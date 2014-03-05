package dropdash.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.auth.Auth;
import com.yammer.metrics.annotation.Timed;

import dropdash.DropdashController;
import dropdash.auth.Subject;

@Path("/drop-dash")
@Produces(MediaType.APPLICATION_JSON)
public class DropdashResource {

	DropdashController controller;

	public DropdashResource(DropdashController controller) {
		this.controller = controller;
	}

	@GET
	@Path("/df")
	@Timed
	public Response df(@Auth Subject subject) {
		return Response.ok(controller.df()).build();
	}

	@GET
	@Path("/bandwidth")
	@Timed
	public Response bandwidth(@Auth Subject subject) {
		return Response.ok(controller.bandwidth()).build();
	}

	@GET
	@Path("/hostname")
	@Timed
	public Response hostname(@Auth Subject subject) {
		return Response.ok(controller.hostname()).build();
	}

	@GET
	@Path("/ip")
	@Timed
	public Response ip(@Auth Subject subject) {
		return Response.ok(controller.ip()).build();
	}

	@GET
	@Path("/issue")
	@Timed
	public Response issue(@Auth Subject subject) {
		String str = controller.issue();
		Response build = Response.ok(str).build();
		return build;
	}

	@GET
	@Path("/loadavg")
	@Timed
	public Response loadavg(@Auth Subject subject) {
		return Response.ok(controller.loadavg()).build();
	}

	@GET
	@Path("/mem")
	@Timed
	public Response mem(@Auth Subject subject) {
		return Response.ok(controller.mem()).build();
	}

	@GET
	@Path("/numberofcores")
	@Timed
	public Response numberofcores(@Auth Subject subject) {
		return Response.ok(controller.numberofcores()).build();
	}

	@GET
	@Path("/online")
	@Timed
	public Response online(@Auth Subject subject) {
		return Response.ok(controller.online()).build();
	}

	@GET
	@Path("/ping")
	@Timed
	public Response ping(@Auth Subject subject) {
		return Response.ok(controller.ping()).build();
	}

	@GET
	@Path("/ps")
	@Timed
	public Response ps(@Auth Subject subject) {
		return Response.ok(controller.ps()).build();
	}

	@GET
	@Path("/speed")
	@Timed
	public Response speed(@Auth Subject subject) {
		return Response.ok(controller.speed()).build();
	}

	@GET
	@Path("/time")
	@Timed
	public Response time(@Auth Subject subject) {
		return Response.ok(controller.time()).build();
	}

	@GET
	@Path("/top")
	@Timed
	public Response top(@Auth Subject subject) {
		return Response.ok(controller.top()).build();
	}

	@GET
	@Path("/uptime")
	@Timed
	public Response uptime(@Auth Subject subject) {
		return Response.ok(controller.uptime()).build();
	}

	@GET
	@Path("/users")
	@Timed
	public Response users(@Auth Subject subject) {
		return Response.ok(controller.users()).build();
	}

	@GET
	@Path("/where")
	@Timed
	public Response whereis(@Auth Subject subject) {
		return Response.ok(controller.where()).build();
	}

	@GET
	@Path("/netstat")
	@Timed
	public Response netstat(@Auth Subject subject) {
		return Response.ok(controller.netstat()).build();
	}

	@GET
	@Path("/lastlog")
	@Timed
	public Response lastlog(@Auth Subject subject) {
		return Response.ok(controller.lastlog()).build();
	}
}
