package com.utn.tareas;

import com.utn.tareas.Tarea;
import com.utn.tareas.Tarea.Prioridad;
import com.utn.tareas.TareaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    // Propiedades externas (inyectadas desde application.properties)
    @Value("${app.nombre}")
    private String nombreApp;

    @Value("${app.max-tareas}")
    private int maxTareas;

    @Value("${app.mostrar-estadisticas}")
    private boolean mostrarEstadisticas;

    // Inyección de dependencias por constructor
    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    // Agregar una nueva tarea (con validación del límite)
    public void agregarTarea(String descripcion, Prioridad prioridad) {
        if (tareaRepository.obtenerTodas().size() >= maxTareas) {
            System.out.println(" No se puede agregar más tareas. Se alcanzó el límite de " + maxTareas);
            return;
        }

        Tarea nueva = new Tarea(null, descripcion, false, prioridad);
        tareaRepository.guardar(nueva);
        System.out.println(" Tarea agregada: " + descripcion);
    }

    // Listar todas las tareas
    public List<Tarea> listarTodas() {
        return tareaRepository.obtenerTodas();
    }

    // Listar tareas pendientes
    public List<Tarea> listarPendientes() {
        return tareaRepository.obtenerTodas().stream()
                .filter(t -> !t.isCompletada())
                .collect(Collectors.toList());
    }

    //Listar tareas completadas
    public List<Tarea> listarCompletadas() {
        return tareaRepository.obtenerTodas().stream()
                .filter(Tarea::isCompletada)
                .collect(Collectors.toList());
    }

    // Marcar una tarea como completada
    public boolean marcarComoCompletada(Long id) {
        return tareaRepository.buscarPorId(id).map(t -> {
            t.setCompletada(true);
            System.out.println(" Tarea marcada como completada: " + t.getDescripcion());
            return true;
        }).orElse(false);
    }

    // Mostrar estadísticas (si están habilitadas)
    public void mostrarEstadisticas() {
        if (!mostrarEstadisticas) {
            System.out.println(" Las estadísticas están deshabilitadas por configuración.");
            return;
        }

        long total = tareaRepository.obtenerTodas().size();
        long completadas = tareaRepository.obtenerTodas().stream().filter(Tarea::isCompletada).count();
        long pendientes = total - completadas;

        System.out.println(String.format(" Total: %d | Completadas: %d | Pendientes: %d",
                total, completadas, pendientes));
    }

    // Mostrar configuración actual
    public void mostrarConfiguracion() {
        System.out.println("   Configuración actual:");
        System.out.println("   Nombre aplicación: " + nombreApp);
        System.out.println("   Máx. tareas: " + maxTareas);
        System.out.println("   Mostrar estadísticas: " + mostrarEstadisticas);
    }
}