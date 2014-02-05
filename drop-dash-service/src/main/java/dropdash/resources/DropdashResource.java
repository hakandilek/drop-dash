package dropdash.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import dropdash.DropdashController;

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
	public Response df() {
		return Response.ok(controller.df()).build();
	}

	@GET
	@Path("/hostname")
	@Timed
	public Response hostname() {
		return Response.ok(controller.hostname()).build();
	}

	@GET
	@Path("/ip")
	@Timed
	public Response ip() {
		return Response.ok(controller.ip()).build();
	}

	@GET
	@Path("/issue")
	@Timed
	public Response issue() {
		return Response.ok(controller.issue()).build();
	}

	@GET
	@Path("/loadavg")
	@Timed
	public Response loadavg() {
		return Response.ok(controller.loadavg()).build();
	}

	@GET
	@Path("/mem")
	@Timed
	public Response mem() {
		return Response.ok(controller.mem()).build();
	}

	@GET
	@Path("/numberofcores")
	@Timed
	public Response numberofcores() {
		return Response.ok(controller.numberofcores()).build();
	}

	@GET
	@Path("/online")
	@Timed
	public Response online() {
		return Response.ok(controller.online()).build();
	}

	@GET
	@Path("/ps")
	@Timed
	public Response ps() {
		return Response.ok(controller.ps()).build();
	}

	@GET
	@Path("/speed")
	@Timed
	public Response speed() {
		return Response.ok(controller.speed()).build();
	}

	@GET
	@Path("/test")
	@Timed
	public Response test() {
		return Response.ok(controller.test()).build();
	}

	@GET
	@Path("/top")
	@Timed
	public Response top() {
		return Response.ok(controller.top()).build();
	}

	@GET
	@Path("/uptime")
	@Timed
	public Response uptime() {
		return Response.ok(controller.uptime()).build();
	}

	@GET
	@Path("/users")
	@Timed
	public Response users() {
		return Response.ok(controller.users()).build();
	}

	@GET
	@Path("/whereis")
	@Timed
	public Response whereis() {
		return Response.ok(controller.whereis()).build();
	}
}
