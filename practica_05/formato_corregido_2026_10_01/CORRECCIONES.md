# Qué se corrigió

## Presentación

La entrega anterior conservaba la cabecera, pero no la jerarquía del cuerpo. Ahora empieza con ACTIVIDADES, CONTEXTO DEL PROBLEMA y EXPERIENCIA DE PRÁCTICA, como el formato aportado. Se restauraron títulos, numeración, sangría y justificación. Los análisis generales se ubicaron después de los ejercicios y el cuestionario. Se corrigieron los saltos que dejaban páginas casi vacías.

Se revisaron todos los caracteres del documento. No se detectaron caracteres de reemplazo ni símbolos ilegibles. Se retiraron viñetas y comillas tipográficas del texto incorporado. Se conservaron los signos obligatorios del código Java.

## Afirmaciones de la guía

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

## Diferencias de las respuestas originales

### Actividad 1

El intervalo es inclusivo. La guía exige rechazar superior <= inferior, por lo que dos índices iguales se rechazan aunque apunten a un elemento válido. Esta condición se respeta; no debe corregirse silenciosamente a superior < inferior. El método original del Word ya cumple la validación: las comprobaciones combinadas también rechazan un inferior excesivo o un superior negativo. Se añaden pruebas de los límites y de la excepción, no una acusación de fallo inexistente.

### Actividad 2

contains debe recorrer del tope al fondo y no extraer elementos. En el código de la guía, tamanio expresa capacidad, no cantidad ocupada; además, push es void aunque su comentario anuncia que devuelve verdadero. El Word busca correctamente para valores no nulos, pero falla si una celda activa contiene null. Se conserva la pila de capacidad fija y se usa Objects.equals para aceptar null. La conversión interna Object[] a E[] queda encapsulada; no se crea un arreglo mediante new E[].

### Actividad 3

La versión simple primero.equals(segundo) produce NullPointerException cuando primero es null. Esa es una consecuencia que el ejercicio pide observar, no un resultado false. Los valores primitivos se convierten individualmente a sus envolventes por autoboxing; no son argumentos de tipo primitivos. Se presenta esIgualALiteral para demostrar el fallo y esIgualA como alternativa segura: dos null dan true y solo uno da false. Object compara identidad por defecto; String e Integer comparan valores. La respuesta del Word evitaba la excepción sin explicar el comportamiento literal y no incluía null con null.

### Actividad 4

esIgual(Pila<E>) utiliza E declarado por la clase Pila<E>; no declara un parámetro de tipo propio, por lo que no es un método genérico en sentido estricto. Tamaño se interpreta como cantidad ocupada, no capacidad máxima. Dos pilas con distinta capacidad pueden ser iguales si contienen los mismos valores en el mismo orden. Se reutiliza la misma Pila de la actividad 2, sin sustituirla por otra implementación. Se consulta el arreglo sin pop y se comprueba que los topes permanezcan iguales. El Word había reemplazado la pila por ArrayList y no protegía la comparación de elementos null.

### Ejercicio propuesto 1

Par<F, S> tiene dos tipos independientes, constructor, getters, setters y toString con el formato pedido. La implementación original era correcta; se añade una demostración explícita de ambos getters y se reutiliza la clase en los ejercicios siguientes.

### Ejercicio propuesto 2

Se comparan los dos componentes en su posición, con Objects.equals y comprobación del otro par null. La prueba se coloca en la clase PruebaPar, como exige la guía. En el Word la prueba estaba dentro de Par, la explicación repetía el ejercicio 1 y la comparación podía fallar con null. esIgual es el método solicitado y no sustituye equals(Object).

### Ejercicio propuesto 3

Main declara <F, S> antes de void y recibe Par<F, S>. Se ejecuta con String e Integer, Double y Boolean, y Persona e Integer. Se reutiliza Par completo, sin perder los métodos de los ejercicios anteriores. Persona aporta toString e implementa equals(Object) junto con hashCode para mantener su contrato de igualdad.

### Ejercicio propuesto 4

Contenedor<F, S> conserva un ArrayList<Par<F, S>> y define exactamente agregarPar, obtenerPar, obtenerTodosLosPares y mostrarPares. El programa principal demuestra los cuatro. La versión original del Word solo tenía agregar, mostrar y cantidad: no cumplía la interfaz pedida. La temática elegida en el repositorio es inventario de equipos de un laboratorio de redes; la temática de productos del Word también era válida. Se sincroniza el documento con el programa publicado. obtenerTodosLosPares devuelve una copia superficial: la lista es independiente, pero los objetos Par siguen compartidos.

El cuestionario necesitaba ejemplos y la corrección de Contenedor con dos parámetros de tipo. En la pregunta 7, la guía sí contenía <T extends Comparable<T>>; su sustitución por una pregunta sobre > estaba en el Word.

La copia local conserva la identificación del formato. La copia pública omite los DNI. Los originales y la primera revisión se conservaron.
