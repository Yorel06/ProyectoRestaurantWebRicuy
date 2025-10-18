package dto;

import lombok.Data;
import java.sql.Date;
import java.time.LocalTime;

 @Data
public class ReservaRequestDTO {
    private int idUsuario;    // ID del usuario que hace la reserva
    private int idMesa;       // ID de la mesa reservada
    private Date fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin; // ⬅️ cambia a LocalTime
    private String estado;
    private String observacion;

}                         