# Práctica 07 Manejo de archivos en Java

Soluciones independientes de las 7 actividades y los 4 ejercicios propuestos.
Requiere JDK 17 o superior. Abrir una terminal dentro de la carpeta del programa:

```bash
javac -encoding UTF-8 *.java
java Main
```

Para ejercicio_3 ejecutar `java ContadorPalabras`. Los programas Swing
(actividades 4 y 7, ejercicio 3) requieren un entorno gráfico.
Los archivos se resuelven desde el directorio de trabajo de la terminal.
Los datos de ejemplo pueden modificarse al ejecutar los gestores.

## Actividades

1. Metadatos de archivos y directorios con NIO y cierre del DirectoryStream.
2. Texto añadido a datos.txt usando try-with-resources y UTF-8.
3. Matriz binaria: dos int (filas, columnas) y después filas * columnas double.
4. Lectura completa con FileInputStream y visualización en JTextArea.
5. Agenda: cada contacto ocupa tres líneas (nombre, teléfono, dirección).
6. Serialización de tres Alumno con Persona y Fecha serializables.
7. Selector de archivos/directorios y visualización de metadatos.

## Ejercicios

1. Personaje y Gestor con altas, listado, modificación y eliminación.
   Formato UTF-8 sin cabecera: nombre;vida;ataque;defensa;alcance.
   Nombre único sin distinguir mayúsculas. Atributos int mayores que cero.
2. Versión ampliada independiente: ordenar por ataque, filtrar por vida y
   estadísticas. Se adoptan tres funciones adicionales para el equipo de tres.
3. Contador: secuencias de letras/dígitos Unicode, minúsculas para frecuencia,
   caracteres por puntos de código incluyendo espacios y signos, sin saltos.
   Se muestran todas las palabras empatadas en frecuencia máxima, ordenadas
   alfabéticamente. Archivo vacío: cero líneas, palabras, caracteres y promedio.
   Cancelar termina. Error de lectura/formato UTF-8 permite seleccionar otro.
4. Gestión de productos con MVC. Formato id;nombre;precio, identificador único
   positivo, nombre no vacío y precio finito no negativo. Archivo inexistente
   equivale a colección vacía. Se guarda al agregar/eliminar, no al buscar.
   Se utiliza un archivo temporal para no truncar el original durante escritura.

## Correcciones respecto de la guía

- Actividad 2: append=true para conservar el contenido.
- Actividad 3: dimensiones escritas una sola vez.
- Actividad 4: lectura completa, codificación y cierre de recursos.
- Actividad 5: BufferedReader en lugar de leer líneas en un arreglo de 32 bytes.
- Actividad 6: Persona serializable independiente de la Persona de la agenda.
- Actividad 7: se procede únicamente con APPROVE_OPTION.
- Ejercicio 1: nombre String; solo los cuatro atributos numéricos son int > 0.

No se añaden documentos con datos personales al repositorio.
