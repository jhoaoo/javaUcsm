# Practica 10 - Java Swing avanzado

Integrantes: Jhoaoo Sebasatian Llerena Quispe, Gustavo David Medina Barrios y Mauricio Gonzalo Montes Yato.

## Actividades
- [Actividad 1: Producto](actividad_1/RegistroProducto.java): binding manual usando un modelo y metodos get/set.
- [Actividad 2: Temperaturas](actividad_2/GraficoTemperaturas.java): grafico de lineas con Graphics y Graphics2D.
- [Actividad 3: Audio](actividad_3/ReproductorEfectos.java): botones con Clip. Coloca en la carpeta de ejecucion tus propios archivos WAV llamados aplausos.wav, campana.wav y explosion.wav.

Para ejecutar las actividades desde sus carpetas: javac NombreClase.java; java NombreClase.

## Proyecto grupal - MVC + SQLite
Inventario con formulario, tabla, registro, listado y eliminacion. Usa Java 17 y Maven.
Desde la carpeta proyecto_mvc ejecuta: mvn compile exec:java

El controlador JDBC SQLite se descarga mediante pom.xml. La tabla se crea automaticamente en productos.db. 
Modelo: Producto. Vista: VistaProductos. Controlador: ProductoControlador. Acceso SQL: ProductoDAO.

Se incluye un boceto digital en el informe; si se exige un boceto fisico en papel, reproduce ese diseno en una hoja.
