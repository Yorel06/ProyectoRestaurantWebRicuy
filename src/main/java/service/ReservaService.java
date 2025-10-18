package service;

import dto.MesaDTO;
import dto.ReservaRequestDTO;
import dto.ReservaResponseDTO;
import java.util.List;

public interface ReservaService {
    List<ReservaResponseDTO> listarReservas();
    List<ReservaResponseDTO> listarReservasCliente();    
    ReservaResponseDTO obtenerDetalleReservaCliente(int idReserva);
    boolean cancelarReservaCliente(int idReserva);
    int crearReservaCliente(ReservaRequestDTO reserva);
    List<MesaDTO> listarMesasDisponibles();


}
