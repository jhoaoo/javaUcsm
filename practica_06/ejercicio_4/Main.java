import java.util.*;
class Item {final String nombre,tipo;final int poder;int cantidad;Item(String n,String t,int p,int c){nombre=n;tipo=t;poder=p;cantidad=c;}}
class InventarioModelo {final List<Item> items=new ArrayList<>();void agregar(Item i){items.add(i);}Item buscar(String n){for(Item i:items)if(i.nombre.equalsIgnoreCase(n))return i;return null;}}
class Jugador {
 final String nombre;int salud,nivel;final InventarioModelo inventario=new InventarioModelo();Item equipado;
 Jugador(String n,int s,int l){nombre=n;salud=s;nivel=l;}
 boolean equipar(String n){Item i=inventario.buscar(n);if(i==null||!i.tipo.equalsIgnoreCase("Arma")||i.cantidad<=0)return false;equipado=i;return true;}
 void atacar(Enemigo e){e.recibirDanio(equipado==null?2:equipado.poder);}
 boolean usarObjeto(String n){Item i=inventario.buscar(n);if(i==null||!i.tipo.equalsIgnoreCase("Pocion")||i.cantidad<=0)return false;i.cantidad--;salud=Math.min(100,salud+i.poder);return true;}
 void recibirDanio(int n){salud=Math.max(0,salud-n);}
}
class Enemigo {
 final String nombre,tipo;final int nivel;int salud;
 Enemigo(String n,int s,int l,String t){nombre=n;salud=s;nivel=l;tipo=t;}
 void atacar(Jugador j){j.recibirDanio(3+nivel);}
 void recibirDanio(int n){salud=Math.max(0,salud-n);}
}
class CombateVista {final Scanner sc=new Scanner(System.in);String leer(String m){System.out.print(m);return sc.nextLine();}void mensaje(String s){System.out.println(s);}void estado(Jugador j,Enemigo e){mensaje(j.nombre+": "+j.salud+" HP | "+e.nombre+": "+e.salud+" HP");}}
class CombateControlador {
 final Jugador j;final Enemigo e;final CombateVista v;final Random azar=new Random();
 CombateControlador(Jugador j,Enemigo e,CombateVista v){this.j=j;this.e=e;this.v=v;}
 void iniciar(){v.mensaje("Combate: "+j.nombre+" vs "+e.nombre);
 while(j.salud>0&&e.salud>0){v.estado(j,e);String op=v.leer("1 Atacar 2 Poción 3 Equipar: ");
 boolean turno=false;
 switch(op){
 case "1":j.atacar(e);v.mensaje("Ataque realizado");turno=true;break;
 case "2":turno=j.usarObjeto("Pocion");v.mensaje(turno?"Salud recuperada":"Sin pociones");break;
 case "3":turno=j.equipar(v.leer("Arma: "));v.mensaje(turno?"Arma equipada":"No disponible");break;
 default:v.mensaje("Opción incorrecta");
 }
 if(!turno||e.salud<=0)continue;
 if(azar.nextBoolean()){e.atacar(j);v.mensaje(e.nombre+" ataca");}else v.mensaje(e.nombre+" se defiende");
 }
 v.estado(j,e);v.mensaje(j.salud>0?"Ganaste":"Perdiste");
 }
}
public class Main {public static void main(String[]args){Jugador j=new Jugador("Jugador",100,1);j.inventario.agregar(new Item("Espada","Arma",15,1));j.inventario.agregar(new Item("Pocion","Pocion",25,3));j.equipar("Espada");new CombateControlador(j,new Enemigo("Goblin",45,2,"Bestia"),new CombateVista()).iniciar();}}