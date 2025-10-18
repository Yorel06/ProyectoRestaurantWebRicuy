package controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/ping")
public class PingResource {

    @GET
    public Response ping() {
        System.out.println("✅ Endpoint /ping activo");
        return Response.ok("API REST activa 🚀").build();
    }
}
