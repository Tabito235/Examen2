package examen2.examen2.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import examen2.examen2.models.Citas;
import examen2.examen2.services.CitasService;

@Controller
public class CitasController {

    @Autowired
    private CitasService citasService;

    @GetMapping("/citas")
    public String verCitas(Model model) {
        List<Citas> citas = citasService.obtenerCitas();
        model.addAttribute("citas", citas);
        return "/citas/citas"; 
    }

    @GetMapping("/citas/nuevo")
    public String formularioNuevaCita(Model model) {
        return "/citas/nuevo"; 
    }

    @PostMapping("/citas/nuevo")
    public String agregarCita(
            @RequestParam("idPaciente") int idPaciente,
            @RequestParam("fecha") String fechaStr,
            @RequestParam("motivo") String motivo,
            Model model) {
        LocalDateTime fecha = LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        citasService.agregarCita(idPaciente, fecha, motivo);
        return "redirect:/citas";
    }

    @GetMapping("/citas/editar/{id}")
    public String formularioEditarCita(@PathVariable int id, Model model) {
        Citas cita = citasService.obtenerCitaPorId(id);
        model.addAttribute("cita", cita);
        return "/citas/editar"; // Vista para editar la cita
    }

    @PostMapping("/citas/editar")
    public String actualizarCita(
            @RequestParam("id") int id,
            @RequestParam("idPaciente") int idPaciente,
            @RequestParam("fecha") String fechaStr,
            @RequestParam("motivo") String motivo,
            Model model) {
        LocalDateTime fecha = LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        citasService.actualizarCita(id, idPaciente, fecha, motivo);
        return "redirect:/citas";
    }

    @GetMapping("/citas/eliminar/{id}")
    public String eliminarCita(@PathVariable int id) {
        citasService.eliminarCita(id);
        return "redirect:/citas";
    }
}
