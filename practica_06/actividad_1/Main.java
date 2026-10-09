import java.util.*;
class Pedido { String plato; Pedido(String plato){this.plato=plato;} }
class PedidoModelo {
 private final List<Pedido> pedidos = new ArrayList<>();
 void agregar(String plato){pedidos.add(new Pedido(plato));}
 List<Pedido> listar(){return Collections.unmodifiableList(pedidos);}
}
class PedidoVista {
 private final Scanner sc=new Scanner(System.in);
 String leer(String pregunta){System.out.print(pregunta);return sc.nextLine();}
 void mostrar(List<Pedido> pedidos){if(pedidos.isEmpty())System.out.println("Sin pedidos");for(Pedido p:pedidos)System.out.println("- "+p.plato);}
 void mensaje(String s){System.out.println(s);}
}
class PedidoControlador {
 private final PedidoModelo modelo; private final PedidoVista vista;
 PedidoControlador(PedidoModelo m,PedidoVista v){modelo=m;vista=v;}
 void iniciar(){String opcion;do {
  opcion=vista.leer("1 Agregar | 2 Listar | 0 Salir: ");
  switch(opcion){
   case "1": String nombre=vista.leer("Plato: ").trim();if(!nombre.isEmpty()){modelo.agregar(nombre);vista.mensaje("Pedido agregado");}else vista.mensaje("Nombre vacío");break;
   case "2":vista.mostrar(modelo.listar());break;
   case "0":break;
   default:vista.mensaje("Opción inválida");
  }
 }while(!opcion.equals("0"));}
}
public class Main {public static void main(String[] args){new PedidoControlador(new PedidoModelo(),new PedidoVista()).iniciar();}}
