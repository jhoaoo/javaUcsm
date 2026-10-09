# Práctica 06 — MVC en Java (UCSM)

Esta carpeta contiene **3 actividades, 5 ejercicios propuestos y el cuestionario de 11 preguntas** de la Guía 06.

## Código Java listo para compilar por separado

| Sección | Proyecto |
|---|---|
| Actividad 1 | [Pedidos básicos](actividad_1/Main.java) |
| Actividad 2 | [Pedidos con búsqueda por nombre/tipo y conteo](actividad_2/Main.java) |
| Actividad 3 | [Pedidos con estados e historial](actividad_3/Main.java) |
| Ejercicio 1 | [Carrito con descuentos, envío e historial](ejercicio_1/Main.java) |
| Ejercicio 2 | [Carrito con usuarios y reseñas posteriores a la compra](ejercicio_2/Main.java) |
| Ejercicio 3 | [Inventario MVC](ejercicio_3/Main.java) |
| Ejercicio 4 | [Jugador, enemigo y combate por turnos](ejercicio_4/Main.java) |
| Ejercicio 5 | [Biblioteca MVC con herencia y genéricos](ejercicio_5/Main.java) |
| Cuestionario | [Preguntas y respuestas](cuestionario.md) |

Cada programa usa consola, clases Java sencillas y MVC. No requiere librerías externas.

### Cómo ejecutar
Entre a una carpeta del proyecto y ejecute:

```bash
javac Main.java
java Main
```

**No compile todos los Main.java juntos**: cada actividad y ejercicio tiene su propia clase Main y se ejecuta de manera independiente.

### Versiones originales del informe
Se conservan transcripciones sin modificar en `CODIGO_DEL_INFORME.md` en las carpetas correspondientes. Para ejecución utilice **Main.java**. Esas transcripciones no deben confundirse con las versiones corregidas.

### Verificación
El archivo [guia06-java.yml](../.github/workflows/guia06-java.yml) configura compilación y pruebas básicas en GitHub Actions. La existencia del workflow no garantiza que haya terminado correctamente: compruebe su estado en la pestaña Actions.

### Alcance académico
El Ejercicio 2 guarda credenciales en memoria y en texto plano **solo a efectos de demostración académica**, por lo que no debe utilizarse como un sistema real. Las compras, usuarios e inventarios son temporales mientras el programa se ejecuta.

## Referencias técnicas
- Oracle: https://docs.oracle.com/javase/tutorial/
- Java Collections: https://docs.oracle.com/javase/8/docs/technotes/guides/collections/
- Martin Fowler, arquitecturas de interfaz: https://martinfowler.com/eaaDev/uiArchs.html
- Spring MVC: https://docs.spring.io/spring-framework/reference/web/webmvc.html
