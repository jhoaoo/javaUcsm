# Práctica 05: Generics en Java

Universidad Católica de Santa María · Ingeniería de Sistemas · Lenguajes de Programación III, sección 04.

Integrantes tomados de la Práctica 03:

- Jhoaoo Sebastián Llerena Quispe — 2025000074.
- Gustavo David Medina Barrios — 2025001757.
- Mauricio Gonzalo Montes Yato — 2025002328.

Desarrollo basado en la Guía 05 adjunta. Fecha del informe: 30/09/2026.

## Actividades y ejercicios

| Apartado | Implementación | Programa ejecutable | Evidencia |
| --- | --- | --- | --- |
| Actividad 1: arreglos | [ImprimirArreglo.java](src/actividades/ImprimirArreglo.java) | `actividades.ImprimirArreglo` | [Salida](evidencias/ImprimirArreglo.png) |
| Actividad 2: contains | [Pila.java](src/actividades/Pila.java) | [PruebaPila](src/actividades/PruebaPila.java) | [Salida](evidencias/PruebaPila.png) |
| Actividad 3: igualdad | [IgualGenerico.java](src/actividades/IgualGenerico.java) | `actividades.IgualGenerico` | [Salida](evidencias/IgualGenerico.png) |
| Actividad 4: pilas | [Pila.java](src/actividades/Pila.java) | [PruebaIgualdadPilas](src/actividades/PruebaIgualdadPilas.java) | [Salida](evidencias/PruebaIgualdadPilas.png) |
| Propuesto 1: Par | [Par.java](src/propuestos/Par.java) | [PruebaAccesoresPar](src/propuestos/PruebaAccesoresPar.java) | [Salida](evidencias/PruebaAccesoresPar.png) |
| Propuesto 2: esIgual | [Par.java](src/propuestos/Par.java) | [PruebaPar](src/propuestos/PruebaPar.java) | [Salida](evidencias/PruebaPar.png) |
| Propuesto 3: imprimirPar | [Main.java](src/propuestos/Main.java) y [Persona.java](src/propuestos/Persona.java) | `propuestos.Main` | [Salida](evidencias/Main.png) |
| Propuesto 4: Contenedor | [Contenedor.java](src/propuestos/Contenedor.java) | [PruebaContenedor](src/propuestos/PruebaContenedor.java) | [Salida](evidencias/PruebaContenedor.png) |
| Cuestionario: ejemplos | [EjemplosGenericos.java](src/cuestionario/EjemplosGenericos.java) | `cuestionario.EjemplosGenericos` | [Salida](evidencias/EjemplosGenericos.png) |

El informe incluye las siete respuestas del cuestionario, las explicaciones, las referencias y las observaciones sobre la guía: [Practica_05_Generics_Java.pdf](informe/Practica_05_Generics_Java.pdf).

## Ejecutar con JDK 17

Desde esta carpeta:

```sh
javac -encoding UTF-8 -Xlint:all -Werror -d out @sources.txt
java -cp out actividades.ImprimirArreglo
java -cp out Verificacion
```

Para ejecutar todas las demostraciones: `sh ejecutar.sh` en Linux/macOS o `ejecutar.bat` en Windows. No se necesitan Maven, Gradle ni bibliotecas externas.

## Verificación

Se compilaron los 16 archivos de implementación y el archivo de pruebas con Java 17.0.20, `-Xlint:all` y `-Werror`. Resultado: **57/57 verificaciones correctas**, sin advertencias. [Pruebas](tests/Verificacion.java) · [Registro](evidencias/Verificacion.txt).

Las imágenes de código son representaciones legibles de los archivos fuente. Las imágenes de ejecución se generaron a partir de la salida real capturada en los archivos `.txt` del mismo nombre. No representan capturas de un IDE.

## Decisiones y aclaraciones

- Los intervalos de impresión son inclusivos. Se rechaza `superior <= inferior` conforme a la guía, incluso si ambos índices son válidos.
- `Pila` tiene capacidad fija y acepta `null`. `contains` recorre del tope al fondo. `esIgual` compara cantidad de elementos y orden, no capacidad.
- La conversión del arreglo `Object[]` a `E[]` se limita al constructor. El arreglo privado solo recibe `E` y no se expone. No se usan tipos sin parametrizar.
- `IgualGenerico` conserva una versión literal que demuestra la excepción con `null` y una versión segura que usa `equals` cuando el primer valor existe.
- `Par.esIgual` es el método solicitado. No reemplaza `Object.equals`.
- `Persona` sobrescribe `equals(Object)` y `hashCode()` de manera coherente.
- `obtenerTodosLosPares()` devuelve una copia superficial de la lista: quitar elementos de la copia no altera la lista interna, pero los objetos `Par` continúan compartidos.

## Problemas identificados en la guía

1. Los argumentos de tipo no pueden ser `int` ni `double`: se usan `Integer` y `Double`.
2. El autoboxing convierte valores individuales, no un arreglo `int[]` completo en `Integer[]`.
3. El comentario de `push` anuncia un booleano, aunque su firma es `void`.
4. Se usa “sobrecargar equals” donde corresponde “sobrescribir equals(Object)”. El ejemplo de `Persona` omite `hashCode` y no protege `nombre.equals(...)` cuando el nombre es `null`.
5. La actividad 3 necesita explicar el caso `null`, que produce `NullPointerException` en la versión literal.
6. Actividad 4 llama “método genérico” a `esIgual(Pila<E>)`: técnicamente utiliza el parámetro de la clase, sin declarar uno propio.
7. Se repite el numeral V para ejercicios y cuestionario.
8. La actividad 1 excluye intervalos de un solo elemento. Es una regla explícita, no un error de implementación.

## Fuentes oficiales

- [Métodos genéricos](https://docs.oracle.com/javase/tutorial/java/generics/methods.html).
- [Restricciones](https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html).
- [Herencia](https://docs.oracle.com/javase/tutorial/java/generics/inheritance.html).
- [Wildcards](https://docs.oracle.com/javase/tutorial/java/generics/wildcards.html).
- [Límites de tipo](https://docs.oracle.com/javase/tutorial/java/generics/bounded.html).
- [Object: equals y hashCode](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Object.html).
- [Objects](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Objects.html).
- [ArrayList](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html).
- [Comparable](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Comparable.html).
- [Autoboxing](https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html).
