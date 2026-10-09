import java.util.*;
class Usuario {final String nombre;final String clave;Usuario(String n,String c){nombre=n;clave=c;}}
class Resena {final String usuario,producto,texto;final int estrellas;Resena(String u,String p,int e,String t){usuario=u;producto=p;estrellas=e;texto=t;}}
class Modelo {
 final Map<String,Usuario> usuarios=new HashMap<>(); final Map<String,Set<String>> compras=new HashMap<>(); final List<Resena> resenas=new ArrayList<>();
 boolean registrar(String n,String clave){if(n.isBlank()||clave.isBlank()||usuarios.containsKey(n))return false;usuarios.put(n,new Usuario(n,clave));return true;}
 boolean autenticar(String n,String c){return usuarios.containsKey(n)&&usuarios.get(n).clave.equals(c);}
 void comprar(String usuario,String producto){compras.computeIfAbsent(usuario,k->new HashSet<>()).add(producto);}
 boolean opinar(String usuario,String producto,int estrellas,String texto){if(estrellas<1||estrellas>5||texto.isBlank()||!compras.getOrDefault(usuario,Set.of()).contains(producto))return false;resenas.add(new Resena(usuario,producto,estrellas,texto));return true;}
}
class Vista{final Scanner s=new Scanner(System.in);String leer(String p){System.out.print(p);return s.nextLine();}void mostrar(String x){System.out.println(x);}}
class Controlador {
 final Modelo m=new Modelo();final Vista v=new Vista();String sesion=null;
 void ejecutar(){String op;do {op=v.leer("\n1 Registro 2 Iniciar sesión 3 Comprar producto 4 Reseñar 5 Ver reseñas 6 Salir sesión 0 Salir: ");switch(op){
 case "1":v.mostrar(m.registrar(v.leer("Usuario: "),v.leer("Clave: "))?"Registrado":"Registro inválido");break;
 case "2":String u=v.leer("Usuario: "),c=v.leer("Clave: ");if(m.autenticar(u,c)){sesion=u;v.mostrar("Sesión iniciada");}else v.mostrar("Credenciales inválidas");break;
 case "3":if(sesion==null){v.mostrar("Inicie sesión");break;}m.comprar(sesion,v.leer("Producto comprado: "));v.mostrar("Compra registrada");break;
 case "4":if(sesion==null){v.mostrar("Inicie sesión");break;}String p=v.leer("Producto: ");int e;try{e=Integer.parseInt(v.leer("Estrellas (1-5): "));}catch(Exception x){e=0;}v.mostrar(m.opinar(sesion,p,e,v.leer("Reseña: "))?"Reseña guardada":"Debe comprar antes de reseñar y usar 1-5 estrellas");break;
 case "5":for(Resena r:m.resenas)v.mostrar(r.producto+" - "+r.estrellas+"/5 - "+r.usuario+": "+r.texto);break;
 case "6":sesion=null;break;
 case "0":break;
 default:v.mostrar("Opción inválida");
 }}while(!op.equals("0"));}
}
public class Main{public static void main(String[]args){new Controlador().ejecutar();}}
