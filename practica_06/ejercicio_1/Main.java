import java.util.*;
class Producto {
 final String nombre; final double precio; int stock;
 Producto(String n,double p,int s){nombre=n;precio=p;stock=s;}
}
class Compra {final String detalle;final double total;Compra(String d,double t){detalle=d;total=t;}}
class TiendaModelo {
 final List<Producto> productos=new ArrayList<>();final Map<Integer,Integer> carrito=new LinkedHashMap<>();final List<Compra> historial=new ArrayList<>();
 TiendaModelo(){productos.add(new Producto("Teclado",80,10));productos.add(new Producto("Mouse",40,12));productos.add(new Producto("Monitor",500,3));}
 boolean agregar(int id,int cantidad){if(id<0||id>=productos.size()||cantidad<=0)return false;int total=carrito.getOrDefault(id,0)+cantidad;if(total>productos.get(id).stock)return false;carrito.put(id,total);return true;}
 void quitar(int id){carrito.remove(id);}
 double subtotal(){double s=0;for(var e:carrito.entrySet())s+=productos.get(e.getKey()).precio*e.getValue();return s;}
 double total(double descuento){double s=subtotal();return s*(1-descuento/100)+(s>=200?0:15);}
 boolean comprar(double descuento){if(carrito.isEmpty())return false;for(var e:carrito.entrySet())productos.get(e.getKey()).stock-=e.getValue();historial.add(new Compra(carrito.toString(),total(descuento)));carrito.clear();return true;}
}
class TiendaVista {
 final Scanner sc=new Scanner(System.in);
 String leer(String s){System.out.print(s);return sc.nextLine();}
 void mensaje(String s){System.out.println(s);}
 void productos(TiendaModelo m){for(int i=0;i<m.productos.size();i++){Producto p=m.productos.get(i);mensaje(i+": "+p.nombre+" S/ "+p.precio+" stock "+p.stock);}}
 void carrito(TiendaModelo m){for(var e:m.carrito.entrySet())mensaje(m.productos.get(e.getKey()).nombre+" x"+e.getValue());mensaje("Subtotal S/ "+m.subtotal());}
}
class TiendaControlador {
 final TiendaModelo m;final TiendaVista v;
 TiendaControlador(TiendaModelo m,TiendaVista v){this.m=m;this.v=v;}
 int numero(String s){try{return Integer.parseInt(v.leer(s));}catch(Exception ex){return -1;}}
 void iniciar(){int opcion;do{
 opcion=numero("\n1 Listar 2 Agregar 3 Carrito 4 Quitar 5 Comprar 6 Historial 0 Salir: ");
 switch(opcion){
 case 1:v.productos(m);break;
 case 2:v.productos(m);v.mensaje(m.agregar(numero("ID: "),numero("Cantidad: "))?"Agregado":"Datos o stock inválidos");break;
 case 3:v.carrito(m);break;
 case 4:m.quitar(numero("ID a quitar: "));break;
 case 5:if(m.carrito.isEmpty()){v.mensaje("Carrito vacío");break;}int d=numero("Descuento porcentual (0-100): ");if(d<0||d>100){v.mensaje("Descuento inválido");break;}v.mensaje("Total con envío S/ "+m.total(d));v.mensaje(m.comprar(d)?"Compra realizada":"No se pudo comprar");break;
 case 6:for(Compra c:m.historial)v.mensaje(c.detalle+" total S/ "+c.total);break;
 case 0:break;
 default:v.mensaje("Opción incorrecta");
 }
 }while(opcion!=0);}
}
public class Main{public static void main(String[]args){new TiendaControlador(new TiendaModelo(),new TiendaVista()).iniciar();}}
