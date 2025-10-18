package dto;

import lombok.Data;
import java.sql.Date;
import java.time.LocalTime;

@Data
public class ReservaResponseDTO {
    private int id;
    private String nombreUsuario;
    private int numeroMesa;
    private Date fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin; // ⬅️ cambia a LocalTime
    private String estado;
    private String observacion;
}