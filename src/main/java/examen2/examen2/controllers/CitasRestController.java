package examen2.examen2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import examen2.examen2.models.Citas;
import examen2.examen2.services.CitasService;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitasRestController {

    @Autowired
    private CitasService citasService;

    @GetMapping
    public List<Citas> listarCitas() {
        return citasService.obtenerCitas();
    }

    @PostMapping
    public void agregarCita(@RequestBody Citas cita) {
        citasService.agregarCita(cita.getIdPaciente(), cita.getFecha(), cita.getMotivo());
    }

    @PostMapping("/{id}")
    public void actualizarCita(@PathVariable int id, @RequestBody Citas cita) {
        citasService.actualizarCita(id, cita.getIdPaciente(), cita.getFecha(), cita.getMotivo());
    }

    @DeleteMapping("/{id}")
    public void eliminarCita(@PathVariable int id) {
        citasService.eliminarCita(id);
    }
}
