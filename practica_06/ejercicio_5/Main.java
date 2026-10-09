import java.util.*;
class Libro {
 final int codigo;final String titulo,autor;boolean prestado;
 Libro(int c,String t,String a){codigo=c;titulo=t;autor=a;}
 String info(){return codigo+" - "+titulo+" - "+autor+" - "+(prestado?"Prestado":"Disponible");}
}
class LibroDigital extends Libro {
 final double tamanoMB;LibroDigital(int c,String t,String a,double mb){super(c,t,a);tamanoMB=mb;}
 @Override String info(){return super.info()+" - Digital ("+tamanoMB+" MB)";}
}
class Repositorio<T> {final List<T> elementos=new ArrayList<>();void agregar(T v){elementos.add(v);}void eliminar(T v){elementos.remove(v);}List<T> todos(){return elementos;}}
class LibroFactory {static Libro crear(int tipo,int codigo,String t,String a,double mb){if(tipo==1)return new Libro(codigo,t,a);if(tipo==2&&mb>0)return new LibroDigital(codigo,t,a,mb);throw new IllegalArgumentException("Tipo o tamaño inválido");}}
class BibliotecaModelo {
 final Repositorio<Libro> libros=new Repositorio<>();
 Libro buscar(int id){for(Libro l:libros.todos())if(l.codigo==id)return l;return null;}
 void registrar(Libro l){if(buscar(l.codigo)!=null)throw new IllegalArgumentException("Código repetido");libros.agregar(l);}
 void prestar(int id){Libro l=obtener(id);if(l.prestado)throw new IllegalArgumentException("Ya prestado");l.prestado=true;}
 void devolver(int id){Libro l=obtener(id);if(!l.prestado)throw new IllegalArgumentException("No prestado");l.prestado=false;}
 void eliminar(int id){libros.eliminar(obtener(id));}
 Libro obtener(int id){Libro l=buscar(id);if(l==null)throw new IllegalArgumentException("No encontrado");return l;}
 int contar(boolean p){int n=0;for(Libro l:libros.todos())if(l.prestado==p)n++;return n;}
}
class BibliotecaVista {
 final Scanner sc=new Scanner(System.in);
 String leer(String q){System.out.print(q);return sc.nextLine();}
 int numero(String q){return Integer.parseInt(leer(q));}
 double decimal(String q){return Double.parseDouble(leer(q));}
 void mensaje(String m){System.out.println(m);}
 void mostrar(List<Libro> ls){if(ls.isEmpty())mensaje("Sin libros");for(Libro l:ls)mensaje(l.info());}
}
class BibliotecaControlador {
 final BibliotecaModelo m;final BibliotecaVista v;
 BibliotecaControlador(BibliotecaModelo m,BibliotecaVista v){this.m=m;this.v=v;}
 void iniciar(){int op=-1;do{try{op=v.numero("1 Registrar 2 Listar 3 Buscar 4 Prestar 5 Devolver 6 Eliminar 7 Contar 0 Salir: ");
 switch(op){
 case 1:int tipo=v.numero("Tipo (1 físico, 2 digital): "),id=v.numero("Código: ");String t=v.leer("Título: "),a=v.leer("Autor: ");if(t.isBlank()||a.isBlank())throw new IllegalArgumentException("Datos vacíos");double mb=tipo==2?v.decimal("MB: "):0;m.registrar(LibroFactory.crear(tipo,id,t,a,mb));v.mensaje("Registrado");break;
 case 2:v.mostrar(m.libros.todos());break;
 case 3:v.mensaje(m.obtener(v.numero("Código: ")).info());break;
 case 4:m.prestar(v.numero("Código: "));v.mensaje("Prestado");break;
 case 5:m.devolver(v.numero("Código: "));v.mensaje("Devuelto");break;
 case 6:m.eliminar(v.numero("Código: "));v.mensaje("Eliminado");break;
 case 7:v.mensaje("Disponibles: "+m.contar(false)+" | Prestados: "+m.contar(true));break;
 case 0:break;default:v.mensaje("Opción inválida");
 }}catch(NumberFormatException e){v.mensaje("Número inválido");}catch(IllegalArgumentException e){v.mensaje(e.getMessage());}}while(op!=0);}
}
public class Main{public static void main(String[]args){new BibliotecaControlador(new BibliotecaModelo(),new BibliotecaVista()).iniciar();}}