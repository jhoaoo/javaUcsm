import java.util.*;
class Item {
 final String nombre,tipo,descripcion;int cantidad;
 Item(String n,int c,String t,String d){nombre=n;cantidad=c;tipo=t;descripcion=d;}
 boolean usarItem(){if(cantidad<=0)return false;cantidad--;return true;}
 public String toString(){return nombre+" x"+cantidad+" - "+tipo+" - "+descripcion;}
}
class InventarioModelo {
 final List<Item> items=new ArrayList<>();
 void agregar(Item i){items.add(i);}
 boolean eliminar(String nombre){Item i=buscar(nombre);return i!=null&&items.remove(i);}
 Item buscar(String nombre){for(Item i:items)if(i.nombre.equalsIgnoreCase(nombre))return i;return null;}
}
class InventarioVista {
 final Scanner sc=new Scanner(System.in);
 String leer(String pregunta){System.out.print(pregunta);return sc.nextLine();}
 int numero(String pregunta){try{return Integer.parseInt(leer(pregunta));}catch(Exception e){return -1;}}
 void mostrar(List<Item> items){if(items.isEmpty())System.out.println("Inventario vacío");for(Item i:items)System.out.println(i);}
 void mensaje(String m){System.out.println(m);}
}
class InventarioControlador {
 final InventarioModelo m;final InventarioVista v;
 InventarioControlador(InventarioModelo m,InventarioVista v){this.m=m;this.v=v;}
 void iniciar(){int op;do{op=v.numero("1 Agregar 2 Inventario 3 Buscar 4 Eliminar 5 Usar 0 Salir: ");
 switch(op){
 case 1:String n=v.leer("Nombre: ");int c=v.numero("Cantidad: ");String t=v.leer("Tipo: "),d=v.leer("Descripción: ");if(n.isBlank()||t.isBlank()||c<0)v.mensaje("Datos inválidos");else m.agregar(new Item(n,c,t,d));break;
 case 2:v.mostrar(m.items);break;
 case 3:Item b=m.buscar(v.leer("Buscar nombre: "));v.mensaje(b==null?"No encontrado":b.toString());break;
 case 4:v.mensaje(m.eliminar(v.leer("Eliminar nombre: "))?"Eliminado":"No encontrado");break;
 case 5:Item i=m.buscar(v.leer("Usar: "));v.mensaje(i!=null&&i.usarItem()?"Usado":"No disponible");break;
 case 0:break;default:v.mensaje("Opción inválida");
 }}while(op!=0);}
}
public class Main{public static void main(String[]args){new InventarioControlador(new InventarioModelo(),new InventarioVista()).iniciar();}}