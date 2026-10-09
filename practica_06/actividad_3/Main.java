import java.util.*;
class Pedido {
 final int id;final String nombre;String estado="Pendiente";
 Pedido(int id,String n){this.id=id;nombre=n;}
 public String toString(){return id+" - "+nombre+" ("+estado+")";}
}
class PedidoModelo {
 final List<Pedido> pedidos=new ArrayList<>(),historial=new ArrayList<>();int siguiente=1;
 void agregar(String s){pedidos.add(new Pedido(siguiente++,s));}
 Pedido buscar(int id){for(Pedido p:pedidos)if(p.id==id)return p;return null;}
 boolean completar(int id){Pedido p=buscar(id);if(p==null||!p.estado.equals("Pendiente"))return false;p.estado="Completo";if(!historial.contains(p))historial.add(p);return true;}
 boolean eliminar(int id){Pedido p=buscar(id);if(p==null)return false;p.estado="Eliminado";if(!historial.contains(p))historial.add(p);pedidos.remove(p);return true;}
 List<Pedido> porEstado(String s){List<Pedido> r=new ArrayList<>();for(Pedido p:pedidos)if(p.estado.equals(s))r.add(p);return r;}
 int pendientes(){return porEstado("Pendiente").size();}
}
class PedidoVista {
 final Scanner sc=new Scanner(System.in);
 String leer(String p){System.out.print(p);return sc.nextLine();}
 int numero(String p){try{return Integer.parseInt(leer(p));}catch(Exception e){return -1;}}
 void mostrar(List<Pedido> l){if(l.isEmpty())System.out.println("Sin pedidos");for(Pedido p:l)System.out.println(p);}
 void mensaje(String m){System.out.println(m);}
}
class PedidoControlador {
 final PedidoModelo m;final PedidoVista v;
 PedidoControlador(PedidoModelo m,PedidoVista v){this.m=m;this.v=v;}
 void iniciar(){int op;do{op=v.numero("1 Agregar 2 Completar 3 Pendientes 4 Completos 5 Contar 6 Eliminar 7 Historial 8 Todos 0 Salir: ");
 switch(op){
 case 1:String n=v.leer("Pedido: ");if(!n.isBlank())m.agregar(n);else v.mensaje("Pedido vacío");break;
 case 2:v.mensaje(m.completar(v.numero("ID: "))?"Completado":"No válido");break;
 case 3:v.mostrar(m.porEstado("Pendiente"));break;
 case 4:v.mostrar(m.porEstado("Completo"));break;
 case 5:v.mensaje("Pendientes: "+m.pendientes());break;
 case 6:v.mensaje(m.eliminar(v.numero("ID: "))?"Eliminado":"No existe");break;
 case 7:v.mostrar(m.historial);break;
 case 8:v.mostrar(m.pedidos);break;
 case 0:break;default:v.mensaje("Opción inválida");
 }}while(op!=0);}
}
public class Main{public static void main(String[]args){new PedidoControlador(new PedidoModelo(),new PedidoVista()).iniciar();}}