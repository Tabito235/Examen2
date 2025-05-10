package examen2.examen2.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import examen2.examen2.models.Citas;

@Service
public class CitasService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Citas> obtenerCitas() {
        String sql = "SELECT id, id_paciente, fecha, motivo FROM citas";
        return jdbcTemplate.query(sql, citaRowMapper());
    }

    private RowMapper<Citas> citaRowMapper() {
        return (rs, rowNum) -> {
            Citas cita = new Citas();
            cita.setId(rs.getInt("id"));
            cita.setIdPaciente(rs.getInt("id_paciente"));
            cita.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
            cita.setMotivo(rs.getString("motivo"));
            return cita;
        };
    }

    public void agregarCita(int idPaciente, LocalDateTime fecha, String motivo) {
        String sql = "INSERT INTO citas (id_paciente, fecha, motivo) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, idPaciente, Timestamp.valueOf(fecha), motivo);
    }

    public void actualizarCita(int id, int idPaciente, LocalDateTime fecha, String motivo) {
        String sql = "UPDATE citas SET id_paciente = ?, fecha = ?, motivo = ? WHERE id = ?";
        jdbcTemplate.update(sql, idPaciente, Timestamp.valueOf(fecha), motivo, id);
    }

    public void eliminarCita(int id) {
        String sql = "DELETE FROM citas WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Citas obtenerCitaPorId(int id) {
        String sql = "SELECT id, id_paciente, fecha, motivo FROM citas WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, citaRowMapper(), id);
    }
}
