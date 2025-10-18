package dao;

import dto.MesaDTO;
import dto.ReservaRequestDTO;
import dto.ReservaResponseDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://LAPTOP-J2BFP0RH:59593;databaseName=bd_rikuy;encrypt=false";
        String user = "sa";
        String password = "kevin2025";
        System.out.println("🟡 Intentando conectar a SQL Server en: " + url);
        //return DriverManager.getConnection(url, user, password);
        Connection conn = DriverManager.getConnection(url, user, password);
        if (conn != null) {
            System.out.println("✅ Conexión exitosa a la BD");
        } else {
            System.out.println("❌ No se pudo establecer conexión");
        }

        return conn;

    }
    public List<ReservaResponseDTO> listarReservasCliente() {
    List<ReservaResponseDTO> lista = new ArrayList<>();
    String sql = "{CALL sp_listar_reservas_cliente}";

    try (Connection conn = getConnection();
         CallableStatement cs = conn.prepareCall(sql);
         ResultSet rs = cs.executeQuery()) {

        while (rs.next()) {
            ReservaResponseDTO dto = new ReservaResponseDTO();
            dto.setId(rs.getInt("id"));
            dto.setNumeroMesa(rs.getInt("numeroMesa"));
            dto.setFecha(rs.getDate("fecha"));
            dto.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
            dto.setHoraFin(rs.getTime("horaFin").toLocalTime());
            dto.setEstado(rs.getString("estado"));
            dto.setObservacion(rs.getString("observacion"));
            lista.add(dto);
        }

        System.out.println("✅ Reservas del cliente cargadas correctamente. Total: " + lista.size());

    } catch (SQLException e) {
        System.err.println("❌ Error al listar reservas del cliente: " + e.getMessage());
        e.printStackTrace();
    }

    return lista;
}

    // 🔹 Ver detalles de una reserva específica
public ReservaResponseDTO obtenerDetalleReservaCliente(int idReserva) {
    ReservaResponseDTO dto = null;
    String sql = "{CALL sp_detalle_reserva_cliente(?)}";

    try (Connection conn = getConnection();
         CallableStatement cs = conn.prepareCall(sql)) {

        cs.setInt(1, idReserva);
        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                dto = new ReservaResponseDTO();
                dto.setId(rs.getInt("id"));
                dto.setNumeroMesa(rs.getInt("numeroMesa"));
                dto.setFecha(rs.getDate("fecha"));
                dto.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                dto.setHoraFin(rs.getTime("horaFin").toLocalTime());
                dto.setEstado(rs.getString("estado"));
                dto.setObservacion(rs.getString("observacion"));
            }
        }
        System.out.println("✅ Detalle obtenido correctamente: " + idReserva);
    } catch (SQLException e) {
        System.err.println("❌ Error al obtener detalle de reserva: " + e.getMessage());
        e.printStackTrace();
    }
    return dto;
}

// 🔹 Cancelar una reserva (actualizar estado)
public boolean cancelarReservaCliente(int idReserva) {
    String sql = "{CALL sp_cancelar_reserva_cliente(?)}";
    try (Connection conn = getConnection();
         CallableStatement cs = conn.prepareCall(sql)) {

        cs.setInt(1, idReserva);
        int filas = cs.executeUpdate();
        System.out.println("🟡 Reserva cancelada: " + idReserva);
        return filas > 0;
    } catch (SQLException e) {
        System.err.println("❌ Error al cancelar reserva: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}


    public List<ReservaResponseDTO> listarReservas() {
        List<ReservaResponseDTO> lista = new ArrayList<>();
        String sql = "{CALL sp_listar_reservas}";
        try (Connection conn = getConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                ReservaResponseDTO dto = new ReservaResponseDTO();
                dto.setId(rs.getInt("id"));
                dto.setNombreUsuario(rs.getString("nombreUsuario"));
                dto.setNumeroMesa(rs.getInt("numeroMesa"));
                dto.setFecha(rs.getDate("fecha"));
                dto.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                dto.setHoraFin(rs.getTime("horaFin").toLocalTime());
                dto.setEstado(rs.getString("estado"));
                dto.setObservacion(rs.getString("observacion"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<MesaDTO> listarMesasDisponibles() {
    List<MesaDTO> lista = new ArrayList<>();
    String sql = "{CALL sp_listar_mesas_disponibles}";

    try (Connection conn = getConnection();
         CallableStatement cs = conn.prepareCall(sql);
         ResultSet rs = cs.executeQuery()) {

        while (rs.next()) {
            MesaDTO mesa = new MesaDTO();
            mesa.setId(rs.getInt("id"));
            mesa.setNumero(rs.getInt("numero"));
            lista.add(mesa);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}

    
    public int crearReservaCliente(ReservaRequestDTO reserva) {
    String sql = "{CALL sp_crear_reserva_cliente(?, ?, ?, ?, ?)}";
    int idGenerado = -1;

    try (Connection conn = getConnection();
         CallableStatement cs = conn.prepareCall(sql)) {

        cs.setInt(1, reserva.getIdMesa());
        cs.setDate(2, new java.sql.Date(reserva.getFecha().getTime()));
        cs.setTime(3, java.sql.Time.valueOf(reserva.getHoraInicio()));
        cs.setTime(4, java.sql.Time.valueOf(reserva.getHoraFin()));
        cs.setString(5, reserva.getObservacion());

        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                idGenerado = rs.getInt("idReservaCreada");
            }
        }

        System.out.println("✅ Reserva creada con ID: " + idGenerado);

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return idGenerado;
}

}

