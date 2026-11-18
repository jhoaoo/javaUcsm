# Práctica 10 - Java Swing avanzado

Integrantes: Jhoaoo Sebasatian Llerena Quispe, Gustavo David Medina Barrios y Mauricio Gonzalo Montes Yato.

## Actividades
- [Actividad 1: Producto](actividad_1/RegistroProducto.java): binding manual usando un modelo y métodos get/set.
- [Actividad 2: Temperaturas](actividad_2/GraficoTemperaturas.java): gráfico de líneas con Graphics y Graphics2D.
- [Actividad 3: Audio](actividad_3/ReproductorEfectos.java): botones con Clip. Incluye los WAV originales y sintéticos [aplausos](actividad_3/aplausos.wav), [campana](actividad_3/campana.wav) y [explosion](actividad_3/explosion.wav).

Para ejecutar cada actividad, abre su carpeta desde la terminal y utiliza: javac NombreClase.java; java NombreClase.

## Proyecto grupal - MVC + SQLite
Inventario con formulario, tabla, registro, listado y eliminación. Usa Java 17 y Maven.
Desde la carpeta proyecto_mvc ejecuta: mvn compile exec:java

El controlador JDBC SQLite se descarga mediante pom.xml. La tabla se crea automáticamente en productos.db.
Modelo: Producto. Vista: VistaProductos. Controlador: ProductoControlador. Acceso SQL: ProductoDAO.

El informe incluye un boceto digital; si el docente exige dibujo físico, debe reproducirse en papel.
