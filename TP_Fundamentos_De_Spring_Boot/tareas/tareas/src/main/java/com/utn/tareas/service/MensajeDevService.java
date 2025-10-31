package com.utn.tareas.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class MensajeDevService implements MensajeService {

    @Override
    public void mostrarBienvenida() {
        System.out.println(" Bienvenido al Gestor de Tareas [ENTORNO: DEV]");
        System.out.println(" Aquí podés probar, depurar y ver detalles de ejecución.");
    }

    @Override
    public void mostrarDespedida() {
        System.out.println(" Finalizó la ejecución en entorno de desarrollo. ¡Buen trabajo!");
    }
}
