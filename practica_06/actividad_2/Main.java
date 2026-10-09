import java.util.*;
class Pedido {String nombre,tipo;Pedido(String n,String t){nombre=n;tipo=t;}}
class PedidoModelo {
 final List<Pedido> pedidos=new ArrayList<>();
 void agregar(String n,String t){pedidos.add(new Pedido(n,t));}
 boolean eliminar(int id){if(id<0||id>=pedidos.size())return false;pedidos.remove(id);return true;}
 boolean actualizar(int id,String n){if(id<0||id>=pedidos.size()||n.isBlank())return false;pedidos.get(id).nombre=n;return true;}
 List<Pedido> buscar(String dato){List<Pedido> r=new ArrayList<>();for(Pedido p:pedidos)if(p.nombre.toLowerCase().contains(dato.toLowerCase())||p.tipo.toLowerCase().contains(dato.toLowerCase()))r.add(p);return r;}
 Map<String,Integer> contarTipos(){Map<String,Integer> r=new LinkedHashMap<>();for(Pedido p:pedidos)r.put(p.tipo,r.getOrDefault(p.tipo,0)+1);return r;}
}
class PedidoVista {
 final Scanner sc=new Scanner(System.in);
 String leer(String p){System.out.print(p);return sc.nextLine();}
 int numero(String p){try{return Integer.parseInt(leer(p));}catch(NumberFormatException e){return -1;}}
 void mostrar(List<Pedido> lista){for(int i=0;i<lista.size();i++)System.out.println(i+" - "+lista.get(i).nombre+" ("+lista.get(i).tipo+")");if(lista.isEmpty())System.out.println("Sin pedidos");}
 void mensaje(String m){System.out.println(m);}
}
class PedidoControlador {
 final PedidoModelo m;final PedidoVista v;
 PedidoControlador(PedidoModelo m,PedidoVista v){this.m=m;this.v=v;}
 void iniciar(){int op;do{op=v.numero("1 Agregar 2 Listar 3 Eliminar 4 Actualizar 5 Buscar 6 Contar 0 Salir: ");
 switch(op){
 case 1:String n=v.leer("Plato: "),t=v.leer("Tipo: ");if(n.isBlank()||t.isBlank())v.mensaje("Datos vacíos");else m.agregar(n,t);break;
 case 2:v.mostrar(m.pedidos);break;
 case 3:v.mostrar(m.pedidos);v.mensaje(m.eliminar(v.numero("Posición: "))?"Eliminado":"Posición inválida");break;
 case 4:v.mostrar(m.pedidos);int id=v.numero("Posición: ");v.mensaje(m.actualizar(id,v.leer("Nuevo nombre: "))?"Actualizado":"Datos inválidos");break;
 case 5:v.mostrar(m.buscar(v.leer("Nombre o tipo: ")));break;
 case 6:v.mensaje("Total: "+m.pedidos.size());for(var e:m.contarTipos().entrySet())v.mensaje(e.getKey()+": "+e.getValue());break;
 case 0:break;default:v.mensaje("Opción inválida");
 }}while(op!=0);}
}
public class Main{public static void main(String[]args){new PedidoControlador(new PedidoModelo(),new PedidoVista()).iniciar();}}