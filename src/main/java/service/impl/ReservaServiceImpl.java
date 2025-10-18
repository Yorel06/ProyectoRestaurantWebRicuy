package service.impl;

import dto.ReservaResponseDTO;
import dao.ReservaDao;
import dto.MesaDTO;
import dto.ReservaRequestDTO;
import java.util.List;
import service.ReservaService;

public class ReservaServiceImpl implements ReservaService {
    
    private final ReservaDao reservaDao = new ReservaDao();

    @Override
    public List<ReservaResponseDTO> listarReservas() {
        return reservaDao.listarReservas();
    }
    
    @Override
    public List<ReservaResponseDTO> listarReservasCliente() {
    return reservaDao.listarReservasCliente(); 
    }
    
    @Override
    public ReservaResponseDTO obtenerDetalleReservaCliente(int idReserva) {
    return reservaDao.obtenerDetalleReservaCliente(idReserva);
    }

    @Override
    public boolean cancelarReservaCliente(int idReserva) {
    return reservaDao.cancelarReservaCliente(idReserva);
    }
    @Override
    public int crearReservaCliente(ReservaRequestDTO reserva) {
    return reservaDao.crearReservaCliente(reserva);
    }

    @Override
    public List<MesaDTO> listarMesasDisponibles() {
    return reservaDao.listarMesasDisponibles();
    }


}