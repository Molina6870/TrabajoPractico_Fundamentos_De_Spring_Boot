package com.utn.tareas;

import com.utn.tareas.Tarea.Prioridad;
import com.utn.tareas.service.MensajeService;
import com.utn.tareas.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication implements CommandLineRunner {

    private final TareaService tareaService;
    private final MensajeService mensajeService;

    // Inyección de dependencias por constructor
    public TareasApplication(TareaService tareaService, MensajeService mensajeService) {
        this.tareaService = tareaService;
        this.mensajeService = mensajeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TareasApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // 1️ Bienvenida
        mensajeService.mostrarBienvenida();

        // 2️ Mostrar configuración actual
        tareaService.mostrarConfiguracion();

        // 3️ Listar todas las tareas iniciales
        System.out.println("\n Tareas iniciales:");
        tareaService.listarTodas().forEach(System.out::println);

        // 4️ Agregar una nueva tarea
        tareaService.agregarTarea("Entregar el TP de Spring Boot", Prioridad.ALTA);

        // 5️ Listar tareas pendientes
        System.out.println("\n Tareas pendientes:");
        tareaService.listarPendientes().forEach(System.out::println);

        // 6️ Marcar una tarea como completada
        tareaService.marcarComoCompletada(1L);

        // 7️ Mostrar estadísticas
        System.out.println();
        tareaService.mostrarEstadisticas();

        // 8️ Listar tareas completadas
        System.out.println("\n Tareas completadas:");
        tareaService.listarCompletadas().forEach(System.out::println);

        // 9️ Despedida
        System.out.println();
        mensajeService.mostrarDespedida();
    }
}
