package com.utn.tareas;

import com.utn.tareas.Tarea;
import com.utn.tareas.Tarea.Prioridad;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TareaRepository {

    private final List<Tarea> tareas = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(1);

    public TareaRepository() {
        tareas.add(new Tarea(contador.getAndIncrement(), "Estudiar Spring Boot", false, Prioridad.ALTA));
        tareas.add(new Tarea(contador.getAndIncrement(), "Lavar la ropa", true, Prioridad.MEDIA));
        tareas.add(new Tarea(contador.getAndIncrement(), "Comprar frutas", false, Prioridad.BAJA));
    }

    public List<Tarea> obtenerTodas() {
        return tareas;
    }

    public Optional<Tarea> buscarPorId(Long id) {
        return tareas.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public void guardar(Tarea tarea) {
        tarea.setId(contador.getAndIncrement());
        tareas.add(tarea);
    }

    public void eliminarPorId(Long id) {
        tareas.removeIf(t -> t.getId().equals(id));
    }
}
