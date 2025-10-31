# TrabajoPractico_Fundamentos_De_Spring_Boot

## Autor

Nombre y Apellido: Juan Ignacio Molina
Legajo:50104
---

# Gestor de Tareas UTN

Aplicación desarrollada con **Spring Boot** que permite gestionar una lista de tareas (To-Do List) aplicando los conceptos fundamentales del framework: **inyección de dependencias**, **estereotipos**, **configuración externa** mediante `application.properties` y **profiles** para distintos entornos (`dev` y `prod`).

---

## ⚙️ Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Maven** (gestor de dependencias)
- **Spring Boot DevTools**
- **Lombok** *(opcional, para simplificar código)*
- IDE sugerido: IntelliJ IDEA

---

## Instrucciones para clonar y ejecutar el proyecto

1. Clonar el repositorio desde GitHub:
   ```bash
   git clone https://github.com/usuario/tareas.git
   ```
2. Entrar al directorio del proyecto:
   ```bash
   cd tareas
   ```
3. Compilar y ejecutar con Maven o desde el IDE:
   ```bash
   mvn spring-boot:run
   ```
   o simplemente ejecutando la clase:
   ```
   TareasApplication.java
   ```

4. Verás en la consola la ejecución del flujo completo:  
   bienvenida → configuración → listado → estadísticas → despedida.

---

## Cómo cambiar entre Profiles (`dev` / `prod`)

Spring Boot permite definir **diferentes configuraciones por entorno**.  
Esto se maneja desde el archivo `application.properties`:

```properties
spring.profiles.active=dev
```

- Si está en `dev`, se cargará `application-dev.properties`:
  - Límite de 10 tareas  
  - Estadísticas visibles  
  - Mensajes detallados  

- Si está en `prod`, se cargará `application-prod.properties`:
  - Límite de 1000 tareas  
  - Estadísticas desactivadas  
  - Mensajes simples  

Para cambiar de entorno, solo reemplazá el valor de la propiedad:
```properties
spring.profiles.active=prod
```

---

## Estructura del proyecto

```
tareas/
├── src/
│   ├── main/
│   │   ├── java/com/utn/tareas/
│   │   │   ├── model/
│   │   │   │   └── Tarea.java
│   │   │   ├── repository/
│   │   │   │   └── TareaRepository.java
│   │   │   ├── service/
│   │   │   │   ├── TareaService.java
│   │   │   │   ├── MensajeService.java
│   │   │   │   ├── MensajeDevService.java
│   │   │   │   └── MensajeProdService.java
│   │   │   └── TareasApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-prod.properties
│   └── test/
│       └── ...
└── pom.xml
```

---

## Conceptos aplicados

- **Inyección de dependencias** mediante constructor  
- **Estereotipos**: `@Service`, `@Repository`, `@SpringBootApplication`  
- **Configuración externa** con `@Value` y `application.properties`  
- **Profiles**: `@Profile("dev")` y `@Profile("prod")`  
- **CommandLineRunner** para ejecutar lógica al inicio de la aplicación  

---

## Conclusiones personales

	Durante el desarrollo de este trabajo práctico, se comprendió cómo **Spring Boot facilita la construcción de aplicaciones modulares y configurables**, separando la lógica de negocio de la configuración y los entornos.  

	El uso de **inyección de dependencias**, **stereotypes** y **profiles** permitió entender la arquitectura base de un proyecto profesional y cómo adaptarlo a distintas necesidades (desarrollo vs producción).
