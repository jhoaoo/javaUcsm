import java.util.ArrayList;
import java.util.List;

class ContactoDuplicadoException extends Exception {
    public ContactoDuplicadoException(String mensaje) { super(mensaje); }
}
class ContactoNoEncontradoException extends Exception {
    public ContactoNoEncontradoException(String mensaje) { super(mensaje); }
}

class Direccion {
    private String ciudad;
    private String distrito;
    public Direccion(String ciudad, String distrito) {
        this.ciudad = ciudad;
        this.distrito = distrito;
    }
    public String toString() { return distrito + ", " + ciudad; }
}

class Empresa {
    private String nombre;
    public Empresa(String nombre) { this.nombre = nombre; }
    public String toString() { return nombre; }
}

class Contacto {
    private int id;
    private String nombre;
    private String telefono;
    private String email;
    private Direccion direccion;
    private Empresa empresa;

    public Contacto(int id, String nombre, String telefono, String email,
                    Direccion direccion, Empresa empresa) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre invalido.");
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("Telefono invalido.");
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.empresa = empresa;
    }

    public int getId() { return id; }
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("Telefono invalido.");
        this.telefono = telefono;
    }
    public String toString() {
        return id + " - " + nombre + " - " + telefono + " - " + email +
               " - " + direccion + " - " + empresa;
    }
}

class GestorContactos {
    private List<Contacto> contactos = new ArrayList<>();

    public void agregar(Contacto contacto) throws ContactoDuplicadoException {
        for (Contacto c : contactos) {
            if (c.getId() == contacto.getId()) {
                throw new ContactoDuplicadoException("Ya existe un contacto con id " + contacto.getId());
            }
        }
        contactos.add(contacto);
    }

    public Contacto buscar(int id) throws ContactoNoEncontradoException {
        for (Contacto c : contactos) if (c.getId() == id) return c;
        throw new ContactoNoEncontradoException("No existe el contacto con id " + id);
    }

    public void modificarTelefono(int id, String telefono) throws ContactoNoEncontradoException {
        buscar(id).setTelefono(telefono);
    }

    public void eliminar(int id) throws ContactoNoEncontradoException {
        Contacto c = buscar(id);
        contactos.remove(c);
    }

    public void listar() {
        for (Contacto c : contactos) System.out.println(c);
    }
}

public class Ejercicio6 {
    public static void main(String[] args) {
        GestorContactos gestor = new GestorContactos();
        try {
            Contacto c1 = new Contacto(1, "Paola Rios", "999111222", "paola@correo.com",
                    new Direccion("Arequipa", "Cercado"), new Empresa("TechSur"));
            Contacto c2 = new Contacto(2, "Marco Leon", "988333444", "marco@correo.com",
                    new Direccion("Arequipa", "Paucarpata"), new Empresa("CloudAndes"));
            gestor.agregar(c1);
            gestor.agregar(c2);
            gestor.modificarTelefono(2, "955000111");
            gestor.listar();
            gestor.eliminar(1);
            System.out.println("Despues de eliminar:");
            gestor.listar();
            gestor.buscar(99);
        } catch (ContactoDuplicadoException | ContactoNoEncontradoException |
                 IllegalArgumentException e) {
            System.out.println("Gestion de contacto: " + e.getMessage());
        }
    }
}
