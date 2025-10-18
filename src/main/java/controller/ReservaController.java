package controller;


import dto.MesaDTO;
import dto.ReservaRequestDTO;
import dto.ReservaResponseDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import service.impl.ReservaServiceImpl;
import java.util.List;

@Path("/reservas")
@Produces(MediaType.APPLICATION_JSON)
public class ReservaController {

    private final ReservaServiceImpl service = new ReservaServiceImpl();

    @GET
    public List<ReservaResponseDTO> listarReservas() {
        System.out.println("🟢 Entró al método listarReservas()");
        return service.listarReservas();
    }
    
    @GET
    @Path("/cliente")
    public List<ReservaResponseDTO> listarReservasCliente() {
        System.out.println("🟢 Entró al método listarReservasCliente()");
        return service.listarReservasCliente(); // llama al método del DAO que hicimos
    }
    
        // 🔹 Detalle de una reserva del cliente
    @GET
    @Path("/cliente/{id}")
    public ReservaResponseDTO obtenerDetalle(@PathParam("id") int idReserva) {
        System.out.println("🟢 Detalle de reserva cliente ID: " + idReserva);
        return service.obtenerDetalleReservaCliente(idReserva);
    }

    // 🔹 Cancelar reserva del cliente
    @PUT
    @Path("/cliente/{id}/cancelar")
    public String cancelarReserva(@PathParam("id") int idReserva) {
        boolean exito = service.cancelarReservaCliente(idReserva);
        return exito ? "✅ Reserva cancelada correctamente."
                     : "⚠️ No se pudo cancelar la reserva.";
    }
    
    @POST
    @Path("/cliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public String crearReservaCliente(ReservaRequestDTO reserva) {
    System.out.println("🟢 Creando nueva reserva del cliente...");
    int id = service.crearReservaCliente(reserva);
    if (id > 0) {
        return "✅ Reserva creada correctamente con ID: " + id;
    } else {
        return "❌ No se pudo registrar la reserva.";
    }
    }

    @GET
    @Path("/cliente/mesas-disponibles")
        public List<MesaDTO> listarMesasDisponibles() {
        return service.listarMesasDisponibles();
    }


}
