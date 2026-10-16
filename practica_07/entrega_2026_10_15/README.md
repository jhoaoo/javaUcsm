# Guía 07 — Manejo de archivos en Java (UCSM)

**Fecha del informe:** 15 de octubre de 2026.

Esta es una **entrega independiente** para conservar intacto el contenido previo de `practica_07`. Incluye las siete actividades y los cuatro ejercicios propuestos de la Sesión N.º 07.

## Actividades
1. [Rutas, archivos y directorios](actividad_1/Main.java)
2. [Escritura de texto y try-with-resources](actividad_2/Main.java)
3. [Matriz binaria con DataInputStream/DataOutputStream](actividad_3/Main.java)
4. [JTextArea y lectura de texto con FileInputStream](actividad_4/Main.java)
5. [Agenda de contactos desde agenda.txt](actividad_5/Main.java)
6. [Serialización de Alumno, Persona y Fecha](actividad_6/Main.java)
7. [JFileChooser para archivos y directorios](actividad_7/Main.java)

## Ejercicios propuestos
1. [Gestor de personajes persistente](ejercicio_1/Main.java)
2. [Tres funcionalidades adicionales](ejercicio_2/Main.java)
3. [Contador de palabras con JFileChooser](ejercicio_3/Main.java)
4. [Gestión de productos persistente con MVC](ejercicio_4/Main.java)

## Ejecución
Cada carpeta contiene un programa **independiente** que se compila por separado con JDK 17 o superior:

```bash
cd practica_07/entrega_2026_10_15/actividad_1
javac -encoding UTF-8 Main.java
java Main
```

Los datos persistentes se guardan en la carpeta desde la cual se ejecuta cada programa. Las actividades 4 y 7 y el ejercicio 3 requieren interfaz gráfica (Java Swing); las demás funcionan por consola.

## Verificación
Se realizó compilación local de los **11 programas Java**. Se ejecutaron pruebas básicas por consola de actividades 1, 2, 3, 5, 6 y ejercicios 1, 2 y 4. Los tres programas gráficos únicamente se verificaron mediante compilación, no mediante interacción con una ventana gráfica.

## Referencias
- [Oracle, Basic I/O](https://docs.oracle.com/javase/tutorial/essential/io/)
- [Oracle, java.nio.file](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/nio/file/package-summary.html)
- [Oracle, Object Serialization](https://docs.oracle.com/en/java/javase/21/docs/specs/serialization/)
- [Oracle, JFileChooser](https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/JFileChooser.html)

Los códigos del informe Word apuntan directamente a los archivos Java de esta carpeta.