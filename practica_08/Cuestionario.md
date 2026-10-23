## ¿Qué es SQLite y cuáles son sus principales características que lo diferencian de otros sistemas de bases de datos?

SQLite es un motor de base de datos relacional que guarda la información en un archivo. No necesita un servidor independiente, tiene poca configuración y permite usar SQL y transacciones. Es útil para aplicaciones locales y proyectos pequeños.

## ¿Qué es JDBC y cómo se utiliza para conectarse a una base de datos en Java?

JDBC es la API de Java para trabajar con bases de datos. Para conectarnos a SQLite agregamos su controlador JDBC al proyecto y utilizamos DriverManager.getConnection("jdbc:sqlite:biblioteca.db"). La conexión permite ejecutar las consultas.

## Explica el propósito de la clase DriverManager en JDBC.

DriverManager se encarga de buscar un controlador JDBC compatible con la dirección de conexión. Su método getConnection() devuelve un objeto Connection para trabajar con la base de datos.

## ¿Cómo se utiliza para obtener una conexión a una base de datos y qué parámetros son necesarios para conectarse a SQLite?

Se utiliza Connection con = DriverManager.getConnection("jdbc:sqlite:biblioteca.db"). El parámetro indica el tipo de conexión y la ruta del archivo. En una conexión local normal de SQLite no se necesita usuario ni contraseña, pero el controlador debe estar disponible.

## ¿Qué es una sentencia SQL y cómo puedes ejecutarla en SQLite usando JDBC?

Una sentencia SQL es una instrucción para consultar o modificar la base de datos. En JDBC podemos crear un Statement o PreparedStatement. Usamos executeQuery() para consultar registros y executeUpdate() para insertar, actualizar, eliminar o crear tablas.

## Explica la diferencia entre Statement y PreparedStatement en JDBC.

Statement ejecuta una instrucción SQL escrita directamente como texto. PreparedStatement permite colocar signos de interrogación como parámetros y asignar sus valores con métodos como setInt() y setString(). Esto facilita reutilizar la consulta.

## ¿Cuándo es recomendable usar PreparedStatement y qué ventajas tiene en términos de seguridad?

Conviene usarlo cuando una consulta recibe valores ingresados por el usuario. Los parámetros se tratan como datos y no como instrucciones SQL, lo que ayuda a evitar la inyección SQL. Los nombres de tablas y columnas deben validarse por separado.

## Describe los pasos para ejecutar una consulta SELECT en una tabla de SQLite y procesar los resultados usando JDBC.

Primero abrimos la conexión, preparamos el SELECT y ejecutamos executeQuery(). El resultado se guarda en un ResultSet. Recorremos los registros con while (rs.next()) y obtenemos sus valores con getInt() o getString(). Al finalizar cerramos los recursos.

## ¿Cómo puedes gestionar las transacciones en SQLite usando JDBC?

Desactivamos la confirmación automática con con.setAutoCommit(false). Después ejecutamos las operaciones y usamos con.commit() para guardarlas. Si ocurre un error o se cancela la operación, usamos con.rollback() para revertir los cambios pendientes.

## ¿Qué es la función executeUpdate() en JDBC y cuándo se utiliza?

executeUpdate() ejecuta instrucciones que no devuelven un conjunto de resultados, como INSERT, UPDATE y DELETE. Devuelve la cantidad de filas afectadas. También se usa con instrucciones como CREATE TABLE, que normalmente devuelven cero.

## Describe cómo puedes manejar errores y excepciones relacionadas con la base de datos cuando trabajas con JDBC.

Podemos usar try y catch para capturar SQLException y mostrar e.getMessage(). Si trabajamos con una transacción pendiente, debemos intentar revertirla con rollback(). Con try-with-resources cerramos los recursos aunque ocurra un error.

## ¿Cómo puedes realizar consultas parametrizadas usando PreparedStatement en JDBC para mejorar el rendimiento y la seguridad?

Preparamos una consulta como SELECT * FROM Usuario WHERE idUsuario = ?, asignamos el valor con ps.setInt(1, id) y ejecutamos ps.executeQuery(). Podemos reutilizar la misma sentencia con otros valores. Esto evita concatenar datos del usuario y puede reducir el trabajo de preparación en ejecuciones repetidas.

## Explica el proceso de cierre de recursos (como Connection, Statement y ResultSet) en JDBC y por qué es importante cerrar estos recursos correctamente.

Los recursos se cierran al terminar de utilizarlos, primero ResultSet, después Statement o PreparedStatement y finalmente Connection. Podemos declararlos en try-with-resources para que se cierren automáticamente en orden inverso. Esto libera recursos y evita mantener bloqueos innecesarios. Las transacciones deben confirmarse o revertirse antes de cerrar la conexión.