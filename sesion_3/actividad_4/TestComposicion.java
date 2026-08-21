public class TestComposicion {
    public static void main(String[] args) {
        Persona persona = new Persona(1, "Jhoaoo", "Llerena");
        persona.getCuenta().setSaldo(500);
        System.out.println(persona);
    }
}
