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


class Usuario {final String nombre,clave;Usuario(String n,String c){nombre=n;clave=c;}}
class Resena {final String usuario,producto,texto;final int puntuacion;Resena(String u,String p,int n,String t){usuario=u;producto=p;puntuacion=n;texto=t;}}
class UsuariosModelo {
 final Map<String,Usuario> usuarios=new HashMap<>();
 final Map<String,Set<Integer>> comprados=new HashMap<>();
 final List<Resena> resenas=new ArrayList<>();
 boolean registrar(String n,String c){if(n.isBlank()||c.isBlank()||usuarios.containsKey(n))return false;usuarios.put(n,new Usuario(n,c));return true;}
 boolean entrar(String n,String c){Usuario u=usuarios.get(n);return u!=null&&u.clave.equals(c);}
 void guardarCompra(String u,Collection<Integer> ids){comprados.computeIfAbsent(u,k->new HashSet<>()).addAll(ids);}
 boolean resenar(String u,int id,int nota,String texto){if(nota<1||nota>5||texto.isBlank()||!comprados.getOrDefault(u,Set.of()).contains(id))return false;return true;}
}
class TiendaSeguraControlador {
 final TiendaModelo m=new TiendaModelo();final TiendaVista v=new TiendaVista();final UsuariosModelo usuarios=new UsuariosModelo();
 String sesion=null;double descuento=0;
 int numero(String pregunta){try{return Integer.parseInt(v.leer(pregunta));}catch(Exception e){return -1;}}
 void iniciar(){int op;do{
 op=numero("\n1 Registro 2 Login 3 Productos 4 Agregar carrito 5 Ver carrito 6 Eliminar del carrito 7 Descuento 8 Comprar 9 Historial 10 Reseña 11 Ver reseñas 12 Salir sesión 0 Salir: ");
 switch(op){
 case 1:v.mensaje(usuarios.registrar(v.leer("Usuario: "),v.leer("Contraseña (solo demostración): "))?"Registrado":"Datos inválidos");break;
 case 2:String u=v.leer("Usuario: "),c=v.leer("Contraseña: ");if(usuarios.entrar(u,c)){sesion=u;v.mensaje("Sesión iniciada");}else v.mensaje("Credenciales inválidas");break;
 case 3:v.productos(m);break;
 case 4:if(sesion==null){v.mensaje("Inicie sesión");break;}v.productos(m);v.mensaje(m.agregar(numero("ID: "),numero("Cantidad: "))?"Agregado":"Stock o ID inválido");break;
 case 5:v.carrito(m);break;
 case 6:if(sesion==null){v.mensaje("Inicie sesión");break;}m.quitar(numero("ID: "));break;
 case 7:int d=numero("Porcentaje (0-100): ");if(d>=0&&d<=100){descuento=d;v.mensaje("Descuento configurado");}else v.mensaje("Porcentaje inválido");break;
 case 8:if(sesion==null||m.carrito.isEmpty()){v.mensaje("Inicie sesión y agregue productos");break;}v.mensaje("Total con envío S/ "+m.total(descuento));List<Integer> ids=new ArrayList<>(m.carrito.keySet());if(m.comprar(descuento)){usuarios.guardarCompra(sesion,ids);v.mensaje("Compra realizada");descuento=0;}break;
 case 9:for(Compra compra:m.historial)v.mensaje(compra.detalle+" total S/ "+compra.total);break;
 case 10:if(sesion==null){v.mensaje("Inicie sesión");break;}int id=numero("ID de producto comprado: "),nota=numero("Calificación (1-5): ");String texto=v.leer("Reseña: ");if(id>=0&&id<m.productos.size()&&usuarios.resenar(sesion,id,nota,texto)){usuarios.resenas.add(new Resena(sesion,m.productos.get(id).nombre,nota,texto));v.mensaje("Reseña guardada");}else v.mensaje("Debe haber comprado el producto; nota entre 1 y 5");break;
 case 11:for(Resena r:usuarios.resenas)v.mensaje(r.producto+" "+r.puntuacion+"/5 por "+r.usuario+": "+r.texto);break;
 case 12:sesion=null;m.carrito.clear();descuento=0;break;
 case 0:break;default:v.mensaje("Opción incorrecta");
 }}while(op!=0);}
}
public class Main {public static void main(String[]args){new TiendaSeguraControlador().iniciar();}}
