package model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.time.LocalTime;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reserva")
@Data
@NoArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idUsuario;
    private int idMesa;
    private Date fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin; // ⬅️ cambia a LocalTime
    private String estado;
    private String observacion;
}

