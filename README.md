# Procesamiento de Eventos Asíncrono con Spring WebFlux

Tarea de Programación Avanzada — Universidad de las Fuerzas Armadas ESPE.

Módulo reactivo (sin base de datos) que procesa un flujo de eventos de reserva
usando Project Reactor (`Flux`), programación funcional (`Predicate`, `Consumer`)
y un modelo de datos 100% inmutable.

---

## 1. Crear el proyecto base (rama `main`)

Genera el proyecto en https://start.spring.io con:

- Project: **Maven**
- Language: **Java**
- Spring Boot: la versión estable que ofrezca el Initializr
- Group: `com.espe`  ·  Artifact: `reservations-webflux`
- Java: **21 (LTS)**
- Dependencia: **Spring Reactive Web** (WebFlux)

Luego copia las clases de `src/main/java/com/espe/reservations/` de este proyecto.

```bash
git init
git add .
git commit -m "chore: inicializa proyecto spring webflux"
# Conecta tu repo remoto (crealo antes, PUBLICO, en GitHub):
git remote add origin https://github.com/TU_USUARIO/reservations-webflux.git
git push -u origin main
```

## 2. Actividad 1 — Modelo inmutable (rama `feature/modelo-inmutable`)

```bash
git checkout -b feature/modelo-inmutable
# (agrega la clase model/ReservationEvent.java)
git add .
git commit -m "feat: agrega entidad inmutable ReservationEvent con copias defensivas"
git push -u origin feature/modelo-inmutable
```

## 3. Actividad 2 — Lógica funcional (rama `feature/logica-funcional`)

```bash
git checkout main
git checkout -b feature/logica-funcional
# (agrega la clase functional/ReservationFilters.java)
git add .
git commit -m "feat: define Predicate y Consumer con lambdas en ReservationFilters"
git push -u origin feature/logica-funcional
```

## 4. Actividad 3 — Flujo reactivo (rama `feature/api-reactiva`)

```bash
git checkout main
git checkout -b feature/api-reactiva
# (agrega la clase controller/ReservationController.java)
git add .
git commit -m "feat: expone endpoint reactivo /api/reservations/stream con filter, doOnNext y defaultIfEmpty"
git push -u origin feature/api-reactiva
```

> Nota: cada rama de feature solo debería contener el archivo de esa actividad
> (más lo que herede de `main`). Para eso siempre parte desde `main`
> (`git checkout main`) antes de crear la siguiente rama.

## 5. Integrar todo a `main`

Opción recomendada (evidencia el flujo profesional): abre un **Pull Request**
por cada rama en GitHub y haz merge. Alternativa por terminal:

```bash
git checkout main
git merge feature/modelo-inmutable
git merge feature/logica-funcional
git merge feature/api-reactiva
git push origin main
```

---

## 6. Ejecutar y probar (evidencia con curl, NO Postman)

```bash
./mvnw spring-boot:run
```

En otra terminal, con el flag `-N` para ver el streaming en tiempo real:

```bash
curl -N http://localhost:8080/api/reservations/stream
```

Debes ver **solo las 3 reservas válidas** llegando como eventos (`data:`),
y en la consola del servidor las líneas `Procesando reserva -> ...` del Consumer.

### (Opcional) Hacer visible el "no bloqueo"

Para que el streaming se vea llegar dato por dato en el curl, puedes añadir en el
controlador, antes del `.filter(...)`:

```java
.delayElements(java.time.Duration.ofSeconds(1))
```

No es obligatorio por el enunciado, pero hace muy evidente el comportamiento
reactivo en la captura de terminal.

---

## 7. Entregables

1. Enlace **público** del repositorio en GitHub (se revisa historial de ramas y commits).
2. PDF con capturas del código + captura obligatoria de la terminal ejecutando `curl`.
