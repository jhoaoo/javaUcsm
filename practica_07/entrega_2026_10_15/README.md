# Guía 07 — Manejo de Archivos en Java

**Curso:** Lenguajes de Programación III — UCSM  
**Fecha del informe:** 15/10/2026

En esta práctica trabajamos con archivos de texto, archivos binarios y objetos. Los programas son sencillos y están separados para poder ejecutarlos uno por uno.

## Actividades

1. **[Archivos y carpetas](actividad_1/Main.java).** Escribimos una ruta para mostrar información del archivo o carpeta.
2. **[Guardar texto](actividad_2/Main.java).** Guardamos lo que escribimos por teclado en `datos.txt` hasta ingresar FIN.
3. **[Matriz binaria](actividad_3/Main.java).** Guardamos una matriz en un archivo y después la volvemos a leer.
4. **[Ventana con texto](actividad_4/Main.java).** Elegimos un archivo y mostramos su contenido en una ventana.
5. **[Agenda](actividad_5/Main.java).** Leemos contactos de un archivo y buscamos uno por su nombre.
6. **[Guardar alumnos](actividad_6/Main.java).** Guardamos objetos Alumno y los recuperamos mediante serialización.
7. **[Selector de archivos](actividad_7/Main.java).** Elegimos un archivo o carpeta sin escribir la ruta.

## Ejercicios propuestos

1. **[Personajes](ejercicio_1/Main.java).** Creamos personajes que se pueden agregar, mostrar, modificar y borrar. Se guardan en `personajes.txt`.
2. **[Nuevas opciones](ejercicio_2/Main.java).** Agregamos ordenar por ataque, mostrar estadísticas y cambiar un solo atributo.
3. **[Contador de palabras](ejercicio_3/Main.java).** Elegimos un texto y contamos líneas, palabras, caracteres y las palabras más repetidas.
4. **[Productos con MVC](ejercicio_4/Main.java).** Separamos modelo, vista y controlador para guardar, listar, buscar y eliminar productos en `productos.txt`.

## Cómo ejecutar

Entra a la carpeta de la actividad o ejercicio y abre una terminal. Por ejemplo:

```bash
javac Main.java
java Main
```

Cada `Main.java` se compila **por separado**. No necesitas librerías adicionales; basta con tener instalado Java.

**Importante:** las actividades 4 y 7 y el ejercicio 3 muestran ventanas de Java Swing. Deben ejecutarse en una computadora con entorno gráfico.

## Comprobación

Los 11 programas fueron compilados con Java. También se hicieron pruebas básicas por consola de las actividades 1, 2, 3, 5 y 6 y de los ejercicios 1, 2 y 4. No se realizó una prueba de interacción gráfica de las ventanas.

## Fuentes de consulta

- [Oracle: Basic I/O](https://docs.oracle.com/javase/tutorial/essential/io/)
- [Oracle: java.nio.file](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/nio/file/package-summary.html)
- [Oracle: serialización](https://docs.oracle.com/en/java/javase/21/docs/specs/serialization/)
- [Oracle: JFileChooser](https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/JFileChooser.html)

Los enlaces «Fuente:» del Word llevan a los programas de esta carpeta.
