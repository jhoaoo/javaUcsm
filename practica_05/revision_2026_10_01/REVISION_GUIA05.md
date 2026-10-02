# Revisión de la Guía 05 — 01/10/2026

Se contrastó el Word aportado con los enunciados y la rúbrica de la guía. Se revisaron cuatro actividades, cuatro ejercicios propuestos y siete preguntas. Las observaciones de la guía se distinguen de las diferencias del Word original. No se asigna una nota ni se deduce puntualidad o participación individual sin evidencia.

## Observaciones de la guía

### Página 1: Incorrecto para el método E[] propuesto

La introducción incluye int y double al describir arreglos de tipos para un mismo método genérico. imprimirArreglo(E[]) acepta Integer[] y Double[], no int[] ni double[]. Un arreglo primitivo puede ser un argumento de tipo de referencia en otras firmas, como List<int[]>; eso no convierte int en argumento de tipo ni int[] en Integer[].

### Página 2: Restricción innecesaria

Se exige cumplir requisitos de comparación para unificar imprimirArreglo. Imprimir con %s no requiere Comparable: el método <E> funciona con objetos sin orden natural.

### Páginas 5 y 6: Afirmaciones e identificación imprecisas

Los genéricos mejoran la comprobación de tipos, pero no garantizan por sí solos mayor rendimiento ni eliminan toda ClassCastException cuando se usan conversiones inseguras o tipos raw. La palabra bool no es un tipo primitivo de Java; corresponde boolean.

### Páginas 6 y 12: Comentario falso

El comentario de push anuncia que devuelve verdadero, pero su firma public void push(E valorAMeter) no retorna un booleano. tamanio representa capacidad, no cantidad actualmente almacenada.

### Página 9: Conversión inexistente

El texto afirma que arreglos double e int se convierten automáticamente a versiones de objetos por autoboxing. El autoboxing convierte valores individuales, no arreglos completos. El código mostrado declara Double[] e Integer[], que ya son arreglos de referencia.

### Páginas 10 y 15: Terminología incorrecta y contrato incompleto

La guía y la rúbrica hablan de sobrecargar equals, pero el ejemplo @Override public boolean equals(Object obj) sobrescribe Object.equals. Cambiar el parámetro a Persona produciría una sobrecarga diferente. Al definir igualdad por atributos debe definirse hashCode coherente; el ejemplo de Persona lo omite y nombre.equals falla si nombre es null.

### Página 13 actividad 3: Trampa con null

Invocar equals sobre un primer argumento null produce NullPointerException. Debe observarse y explicarse, aunque se añada una versión segura. «Tipos integrados» no habilita parámetros de tipo primitivos.

### Página 13 actividad 4: Imprecisión sobre método genérico

esIgual(Pila<E>) usa E de la clase Pila<E>. Un método genérico propiamente dicho declara sus propios parámetros de tipo. La firma solicitada es válida y se conserva; cantidad y capacidad se distinguen.

### Páginas 12 y 13: Condición válida y error editorial

Rechazar superior <= inferior es una regla explícita de la actividad 1: no se marca como falso. La repetición del numeral V para ejercicios y cuestionario es un error de numeración sin efecto en la solución.

## Correspondencia de los ejercicios

### Actividad 1

**Evaluación:** Correcto con una condición restrictiva explícita.

El intervalo es inclusivo. La guía exige rechazar superior <= inferior, por lo que dos índices iguales se rechazan aunque apunten a un elemento válido. Esta condición se respeta; no debe corregirse silenciosamente a superior < inferior. El método original del Word ya cumple la validación: las comprobaciones combinadas también rechazan un inferior excesivo o un superior negativo. Se añaden pruebas de los límites y de la excepción, no una acusación de fallo inexistente.

**Fuentes de la solución:**

- [actividades/ImprimirArreglo.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/actividades/ImprimirArreglo.java)
- [actividades/InvalidSubscriptException.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/actividades/InvalidSubscriptException.java)

### Actividad 2

**Evaluación:** Correcto con errores en los comentarios del código base.

contains debe recorrer del tope al fondo y no extraer elementos. En el código de la guía, tamanio expresa capacidad, no cantidad ocupada; además, push es void aunque su comentario anuncia que devuelve verdadero. El Word busca correctamente para valores no nulos, pero falla si una celda activa contiene null. Se conserva la pila de capacidad fija y se usa Objects.equals para aceptar null. La conversión interna Object[] a E[] queda encapsulada; no se crea un arreglo mediante new E[].

**Fuentes de la solución:**

- [actividades/Pila.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/actividades/Pila.java)
- [actividades/ExcepcionPilaLlena.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/actividades/ExcepcionPilaLlena.java)
- [actividades/ExcepcionPilaVacia.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/actividades/ExcepcionPilaVacia.java)
- [actividades/PruebaPila.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/actividades/PruebaPila.java)

### Actividad 3

**Evaluación:** Trampa de ejecución con null e imprecisión sobre tipos integrados.

La versión simple primero.equals(segundo) produce NullPointerException cuando primero es null. Esa es una consecuencia que el ejercicio pide observar, no un resultado false. Los valores primitivos se convierten individualmente a sus envolventes por autoboxing; no son argumentos de tipo primitivos. Se presenta esIgualALiteral para demostrar el fallo y esIgualA como alternativa segura: dos null dan true y solo uno da false. Object compara identidad por defecto; String e Integer comparan valores. La respuesta del Word evitaba la excepción sin explicar el comportamiento literal y no incluía null con null.

**Fuentes de la solución:**

- [actividades/IgualGenerico.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/actividades/IgualGenerico.java)

### Actividad 4

**Evaluación:** Correcto en comportamiento e impreciso en terminología.

esIgual(Pila<E>) utiliza E declarado por la clase Pila<E>; no declara un parámetro de tipo propio, por lo que no es un método genérico en sentido estricto. Tamaño se interpreta como cantidad ocupada, no capacidad máxima. Dos pilas con distinta capacidad pueden ser iguales si contienen los mismos valores en el mismo orden. Se reutiliza la misma Pila de la actividad 2, sin sustituirla por otra implementación. Se consulta el arreglo sin pop y se comprueba que los topes permanezcan iguales. El Word había reemplazado la pila por ArrayList y no protegía la comparación de elementos null.

**Fuentes de la solución:**

- [actividades/Pila.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/actividades/Pila.java)
- [actividades/PruebaIgualdadPilas.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/actividades/PruebaIgualdadPilas.java)

### Ejercicio propuesto 1

**Evaluación:** Correcto.

Par<F, S> tiene dos tipos independientes, constructor, getters, setters y toString con el formato pedido. La implementación original era correcta; se añade una demostración explícita de ambos getters y se reutiliza la clase en los ejercicios siguientes.

**Fuentes de la solución:**

- [propuestos/Par.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/propuestos/Par.java)
- [propuestos/PruebaAccesoresPar.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/propuestos/PruebaAccesoresPar.java)

### Ejercicio propuesto 2

**Evaluación:** Correcto.

Se comparan los dos componentes en su posición, con Objects.equals y comprobación del otro par null. La prueba se coloca en la clase PruebaPar, como exige la guía. En el Word la prueba estaba dentro de Par, la explicación repetía el ejercicio 1 y la comparación podía fallar con null. esIgual es el método solicitado y no sustituye equals(Object).

**Fuentes de la solución:**

- [propuestos/Par.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/propuestos/Par.java)
- [propuestos/PruebaPar.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/propuestos/PruebaPar.java)

### Ejercicio propuesto 3

**Evaluación:** Correcto.

Main declara <F, S> antes de void y recibe Par<F, S>. Se ejecuta con String e Integer, Double y Boolean, y Persona e Integer. Se reutiliza Par completo, sin perder los métodos de los ejercicios anteriores. Persona aporta toString e implementa equals(Object) junto con hashCode para mantener su contrato de igualdad.

**Fuentes de la solución:**

- [propuestos/Main.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/propuestos/Main.java)
- [propuestos/Persona.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/propuestos/Persona.java)

### Ejercicio propuesto 4

**Evaluación:** Correcto con temática libre.

Contenedor<F, S> conserva un ArrayList<Par<F, S>> y define exactamente agregarPar, obtenerPar, obtenerTodosLosPares y mostrarPares. El programa principal demuestra los cuatro. La versión original del Word solo tenía agregar, mostrar y cantidad: no cumplía la interfaz pedida. La temática elegida en el repositorio es inventario de equipos de un laboratorio de redes; la temática de productos del Word también era válida. Se sincroniza el documento con el programa publicado. obtenerTodosLosPares devuelve una copia superficial: la lista es independiente, pero los objetos Par siguen compartidos.

**Fuentes de la solución:**

- [propuestos/Contenedor.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/propuestos/Contenedor.java)
- [propuestos/PruebaContenedor.java](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/propuestos/PruebaContenedor.java)

## Cuestionario

### Pregunta 1

extends establece un límite superior del parámetro de tipo. <T extends Number> acepta Number y sus subclases; <T extends Comparable<T>> requiere una implementación de la interfaz Comparable<T>. En una cota genérica se usa extends tanto para una clase como para una interfaz. Ejemplo: public static <T extends Number> double duplicar(T numero) { return numero.doubleValue() * 2; }. duplicar(7) devuelve 14.0.

[Referencia oficial](https://docs.oracle.com/javase/tutorial/java/generics/bounded.html) · [Ejemplos ejecutables](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/cuestionario/EjemplosGenericos.java)

### Pregunta 2

Los argumentos de tipo deben ser tipos de referencia: List<int> no compila y List<Integer> sí. El autoboxing permite añadir el valor 5 a una List<Integer>, pero no convierte int[] en Integer[]. Tampoco se permite new T(), new T[] ni instanceof List<String>; sí se permite instanceof List<?>. El borrado de tipos impide sobrecargas que terminen con la misma firma, como m(List<String>) y m(List<Integer>).

[Referencia oficial](https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html) · [Ejemplos ejecutables](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/cuestionario/EjemplosGenericos.java)

### Pregunta 3

Un inventario de laboratorio utiliza Contenedor<String, Integer> para asociar un equipo con su cantidad. La misma clase puede utilizarse como Contenedor<String, Double> para productos y precios. El compilador detecta un valor de tipo incorrecto al agregar un par y los getters conservan los tipos declarados. La solución evita repetir una clase por temática y hace explícito qué representan los dos componentes. Contenedor exige dos argumentos de tipo; Contenedor<Producto> no corresponde a la clase de esta práctica.

[Referencia oficial](https://docs.oracle.com/javase/tutorial/java/generics/methods.html) · [Ejemplos ejecutables](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/cuestionario/EjemplosGenericos.java)

### Pregunta 4

Aunque Integer es subtipo de Number, List<Integer> no es subtipo de List<Number>. Los tipos parametrizados son invariantes. Una referencia List<? extends Number> puede recibir List<Integer> para leer sus elementos como Number, pero no permite agregar cualquier Number. List<? super Integer> permite insertar Integer. La herencia de clases genéricas también es posible, por ejemplo class ListaEspecial<T> extends ArrayList<T>. Los comodines no convierten una lista en otra: expresan qué operaciones son seguras.

[Referencia oficial](https://docs.oracle.com/javase/tutorial/java/generics/inheritance.html) · [Ejemplos ejecutables](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/cuestionario/EjemplosGenericos.java)

### Pregunta 5

El wildcard es ?, que representa un tipo desconocido como argumento de un tipo parametrizado. List<?> permite leer elementos como Object, pero no insertar un valor no nulo arbitrario. List<? extends Number> establece una cota superior y List<? super Integer> una cota inferior. Un wildcard no es un identificador reutilizable como T.

[Referencia oficial](https://docs.oracle.com/javase/tutorial/java/generics/wildcards.html) · [Ejemplos ejecutables](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/cuestionario/EjemplosGenericos.java)

### Pregunta 6

Un parámetro nombrado permite relacionar tipos entre entradas y salida. Ejemplo: public static <T> T primero(List<T> lista) { return lista.get(0); }; con List<Integer> devuelve Integer. Un wildcard sirve cuando esa relación no se necesita: public static void mostrar(List<?> lista) { for (Object e : lista) System.out.println(e); }. Este método acepta listas de distintos tipos para leerlas. Ambos ejemplos se ejecutan en EjemplosGenericos.

[Referencia oficial](https://docs.oracle.com/javase/tutorial/java/generics/methods.html) · [Ejemplos ejecutables](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/cuestionario/EjemplosGenericos.java)

### Pregunta 7

La expresión <T extends Comparable<T>> declara T con la obligación de implementar Comparable<T>, de modo que un T pueda compararse con otro T mediante compareTo. Se utiliza al calcular máximos u ordenar objetos según su orden natural. Ejemplo: public static <T extends Comparable<T>> T maximo(T a, T b) { return a.compareTo(b) >= 0 ? a : b; }. maximo(4, 9) devuelve 9 y maximo("Ana", "Luis") devuelve "Luis". El operador >= se aplica al int devuelto por compareTo, no directamente a los objetos T. La pregunta de la guía está bien formulada; la sustitución por «¿Qué significa >?» ocurrió en el Word.

[Referencia oficial](https://docs.oracle.com/javase/tutorial/java/generics/boundedTypeParams.html) · [Ejemplos ejecutables](https://github.com/jhoaoo/javaUcsm/blob/main/practica_05/src/cuestionario/EjemplosGenericos.java)

## Validación

El código ya existente en practica_05 fue compilado con javac de Java 21 y ejecutado dos veces. La suite produjo 57/57 verificaciones correctas y se ejecutaron las nueve demostraciones. El Word contiene esos resultados y enlaces directos a los archivos fuente. La revisión visual abarcó las 15 páginas; la copia pública coincide en las páginas 2 a 15 y su primera página se inspeccionó por separado.

La copia publicada del Word omite los DNI. El archivo local conserva la identificación del formato original. Los documentos de entrada permanecen intactos. La revisión añade archivos en una carpeta nueva y conserva el código y las evidencias anteriores.

La compilación adicional con -Xlint:all -Werror no pudo confirmarse: el entorno informó un fallo al escribir archivos de clase. La compilación ordinaria y las pruebas sí finalizaron correctamente; no se atribuye un resultado exitoso a esa comprobación adicional.
