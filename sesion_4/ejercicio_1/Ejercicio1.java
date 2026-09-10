import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class VocalException extends Exception { public VocalException(char c) { super("Vocal detectada: " + c); } }
class NumeroException extends Exception { public NumeroException(char c) { super("Numero detectado: " + c); } }
class BlancoException extends Exception { public BlancoException() { super("Caracter en blanco detectado."); } }
class SalidaException extends Exception { public SalidaException() { super("Caracter de salida detectado."); } }

class LeerEntrada {
    private Reader stream;
    public LeerEntrada(InputStream fuente) { stream = new InputStreamReader(fuente); }
    public char getChar() throws IOException { return (char) stream.read(); }
}

public class Ejercicio1 {
    private LeerEntrada entrada = new LeerEntrada(System.in);

    public void procesar() throws IOException, VocalException, NumeroException,
            BlancoException, SalidaException {
        char c = entrada.getChar();
        if (c == 'q' || c == 'Q') throw new SalidaException();
        if ("aeiouAEIOU".indexOf(c) >= 0) throw new VocalException(c);
        if (Character.isDigit(c)) throw new NumeroException(c);
        if (Character.isWhitespace(c)) throw new BlancoException();
        System.out.println("Otro caracter: " + c);
    }

    public static void main(String[] args) throws IOException {
        Ejercicio1 app = new Ejercicio1();
        boolean continuar = true;
        System.out.println("Escriba caracteres. Use q para salir.");
        while (continuar) {
            try {
                app.procesar();
            } catch (VocalException | NumeroException | BlancoException e) {
                System.out.println(e.getMessage());
            } catch (SalidaException e) {
                System.out.println(e.getMessage());
                continuar = false;
            }
        }
    }
}
