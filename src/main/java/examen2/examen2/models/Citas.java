package examen2.examen2.models;

import java.time.LocalDateTime;

public class Citas {
    private int id;
    private int idPaciente;
    private LocalDateTime fecha;
    private String motivo;

    public Citas() {
    }

    public Citas(LocalDateTime fecha, int id, int idPaciente, String motivo) {
        this.fecha = fecha;
        this.id = id;
        this.idPaciente = idPaciente;
        this.motivo = motivo;
    }

    public int getId() {
        return id;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
