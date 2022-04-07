/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;

//import autores.vistas.VentanaAMAlumno;
//import autores.vistas.VentanaAMProfesor;
//import grupos.vistas.VentanaAMGrupo;
//import tipos.vistas.VentanaATipo;
//import idiomas.vistas.VentanaAIdioma;
//import lugares.vistas.VentanaALugar;
//import palabrasclaves.vistas.VentanaAPalabraClave;

import java.util.ArrayList;
import java.time.LocalDate;
import autores.modelos.Autor;
import autores.modelos.GestorAutores;
import autores.modelos.Cargo;
import grupos.modelos.GestorGrupos;
import grupos.modelos.Grupo;
import grupos.modelos.Rol;
import grupos.modelos.MiembroEnGrupo;
import idiomas.modelos.GestorIdiomas;
import tipos.modelos.Tipo;
import idiomas.modelos.Idioma;
import interfaces.*;
import java.util.List;
import lugares.modelos.GestorLugares;
import lugares.modelos.Lugar;
import palabrasclaves.modelos.GestorPalabrasClaves;
import palabrasclaves.modelos.PalabraClave;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.Publicacion;
import tipos.modelos.GestorTipos;

/**
 * @author G07
 */
public class ControladorPrincipal {
       
    public static void main(String[] args) {
        
//<editor-fold defaultstate="collapsed" desc="Sin interfaz gráfica PARTE 1">
//ArrayList<Autor>        autores        = new ArrayList<>();
//ArrayList<Grupo>        grupos         = new ArrayList<>();
//ArrayList<Tipo>         tipos          = new ArrayList<>();
//ArrayList<Idioma>       idiomas        = new ArrayList<>();
//ArrayList<Lugar>        lugares        = new ArrayList<>();
//ArrayList<PalabraClave> palabrasClaves = new ArrayList<>();
//ArrayList<Publicacion> publicaciones   = new ArrayList<>();

System.out.println("------------------GRUPOS------------------");

IGestorGrupos gg = GestorGrupos.crear();
System.out.println(gg.nuevoGrupo("Grupo 2", "Descripcion 2"));
System.out.println(gg.nuevoGrupo("Grupo 1", "Descripcion 1"));
System.out.println(gg.nuevoGrupo("Grupo 5", "Descripcion 5"));
System.out.println(gg.nuevoGrupo("Grupo 4", "Descripcion 4"));
System.out.println(gg.nuevoGrupo("Grupo 3", "Descripcion 3"));
System.out.println(gg.nuevoGrupo("Grupo 3", "Descripcion 3")); // nombre repetido
System.out.println(gg.nuevoGrupo("", "Descripcion 3"));        // nombre en blanco
gg.mostrarGrupos();

System.out.println("------------------AUTORES------------------");

IGestorAutores ga = GestorAutores.crear();
System.out.println(ga.nuevoAutor(2, "Apellido2", "Nombre2", "2", "Clave2", "Clave2"));
System.out.println(ga.nuevoAutor(1, "Apellido1", "Nombre1", "1", "Clave1", "Clave1"));
System.out.println(ga.nuevoAutor(5, "Apellido5", "Nombre5", "5", "Clave5", "Clave5"));
System.out.println(ga.nuevoAutor(4, "Apellido4", "Nombre4", "4", "Clave4", "Clave4"));
System.out.println(ga.nuevoAutor(3, "Apellido3", "Nombre3", "3", "Clave3", "Clave3"));
System.out.println(ga.nuevoAutor(1, "Apellido6", "Nombre6", "6", "Clave6", "Clave6")); //dni repetido con un alumno
System.out.println(ga.nuevoAutor(20, "Apellido20", "Nombre20", Cargo.ASOCIADO,"Clave20", "Clave20"));
System.out.println(ga.nuevoAutor(10, "Apellido10", "Nombre10", Cargo.TITULAR, "Clave10", "Clave10"));
System.out.println(ga.nuevoAutor(30, "Apellido30", "Nombre30", Cargo.ADJUNTO, "Clave30", "Clave30"));
System.out.println(ga.nuevoAutor(10, "Apellido60", "Nombre60", Cargo.ADG,     "Clave60", "Clave60")); //dni repetido con otro profesor
System.out.println(ga.nuevoAutor(1,  "Apellido70", "Nombre70", Cargo.ADG,     "Clave70", "Clave70")); //dni repetido con otro alumno
System.out.println(ga.nuevoAutor(10, "Apellido7", "Nombre7", "7", "Clave7", "Clave7")); //dni repetido con un profesor
System.out.println(ga.nuevoAutor(8,  "Apellido8", "Nombre8", "1", "Clave8", "Clave8")); //cx repetido con un alumno

ga.mostrarAlumnos();
ga.mostrarProfesores();

System.out.println("------------------ METODOS VARIOS (TP4 modificado) ------------------");
/*Main parte 3*/
/*Luego de crear grupos y autores, tomar 1 grupo y agregarle 2 autores
como miembros. Mostrar el grupo verificando que tenga los miembros
asignados. Verificar también que no se pueda agregar
un mismo autor más de una vez, por más que sean en roles distintos.*/
gg.verGrupo("Grupo 1").agregarMiembro(ga.verAutor(10), Rol.ADMINISTRADOR);
gg.verGrupo("Grupo 1").agregarMiembro(ga.verAutor(10), Rol.COLABORADOR); //autor repetido
gg.verGrupo("Grupo 1").agregarMiembro(ga.verAutor(1),  Rol.COLABORADOR);
gg.verGrupo("Grupo 1").mostrar();

System.out.println();
/*Tomar 1 de los 2 autores que se asignó al grupo anterior y
agregarlo a otro grupo distinto.
Mostrar los 2 autores verificando que pertenezcan a los grupos
a los que fueron asignados.
Verificar también que no se pueda agregarle a un autor
un mismo grupo más de una vez, por más que sean roles distintos.*/

ga.verAutor(10).agregarGrupo(gg.verGrupo("Grupo 2"), Rol.COLABORADOR);
ga.verAutor(10).agregarGrupo(gg.verGrupo("Grupo 2"), Rol.ADMINISTRADOR); //grupo repetido
ga.verAutor(10).mostrar();
ga.verAutor(1).mostrar();

System.out.println();
/*
Tomar el grupo al que se le agregaron los 2 autores como miembros,
quitarle 1 y mostrarlo, verificando que el autor
ya no es miembro del grupo.
*/
gg.verGrupo("Grupo 1").quitarMiembro(ga.verAutor(10));
gg.verGrupo("Grupo 1").mostrar();

System.out.println();
/*
Crear un nuevo grupo para los super administradores.
Este grupo DEBE llevar por nombre "Super Administradores".
Intentar asignarle como miembro un autor cualquiera
con el rol de colaborador, verificando que se lo
agrega pero con el rol de administrador.
*/
gg.nuevoGrupo("Super Administradores", "Grupo para los super administradores"); //grupo para los super administradores
gg.verGrupo("Super Administradores").agregarMiembro(ga.verAutor(10), Rol.COLABORADOR);
gg.verGrupo("Super Administradores").mostrar();

System.out.println();
/*
Verificar que el último grupo creado es de super administradores
y cualquiera de los otros grupos no
(usar el método esSuperAdministradores() definido en la clase Grupo).
*/
System.out.println(gg.verGrupo("Super Administradores").esSuperAdministradores());
System.out.println(gg.verGrupo("Grupo 1").esSuperAdministradores());

System.out.println();
/*Tomar el autor que se agregó al grupo de super administradores,
verificar que el mismo es super administrador
y los otros autores no (usar el método esSuperAdministrador()
definido en la clase Autor).*/
System.out.println(ga.verAutor(10).esSuperAdministrador());
System.out.println(ga.verAutor(1).esSuperAdministrador());

System.out.println();
/*Main parte 3*/
System.out.println("------------------TIPOS------------------");
IGestorTipos gt = GestorTipos.crear();
System.out.println(gt.nuevoTipo("Tipo 2"));
System.out.println(gt.nuevoTipo("Tipo 1"));
System.out.println(gt.nuevoTipo("Tipo 5"));
System.out.println(gt.nuevoTipo("Tipo 4"));
System.out.println(gt.nuevoTipo("Tipo 3"));
System.out.println(gt.nuevoTipo("Tipo 1")); // nombre repetido
System.out.println(gt.nuevoTipo(""));       // nombre en blanco
gt.mostrarTipos();

System.out.println("------------------LUGARES------------------");
IGestorLugares gl = GestorLugares.crear();
System.out.println(gl.nuevoLugar("Lugar 2"));
System.out.println(gl.nuevoLugar("Lugar 1"));
System.out.println(gl.nuevoLugar("Lugar 5"));
System.out.println(gl.nuevoLugar("Lugar 4"));
System.out.println(gl.nuevoLugar("Lugar 3"));
System.out.println(gl.nuevoLugar("Lugar 1")); // nombre repetido
System.out.println(gl.nuevoLugar(""));        // nombre en blanco
gl.mostrarLugares();

System.out.println("------------------IDIOMAS------------------");
IGestorIdiomas gi = GestorIdiomas.crear();
System.out.println(gi.nuevoIdioma("Idioma 2"));
System.out.println(gi.nuevoIdioma("Idioma 1"));
System.out.println(gi.nuevoIdioma("Idioma 5"));
System.out.println(gi.nuevoIdioma("Idioma 4"));
System.out.println(gi.nuevoIdioma("Idioma 3"));
System.out.println(gi.nuevoIdioma("Idioma 1")); // nombre repetido
System.out.println(gi.nuevoIdioma(""));         // nombre en blanco
gi.mostrarIdiomas();

System.out.println("------------------PALABRAS CLAVE------------------");
IGestorPalabrasClaves gpp = GestorPalabrasClaves.crear();
System.out.println(gpp.nuevaPalabraClave("PalabraClave2"));
System.out.println(gpp.nuevaPalabraClave("PalabraClave1"));
System.out.println(gpp.nuevaPalabraClave("PalabraClave5"));
System.out.println(gpp.nuevaPalabraClave("PalabraClave4"));
System.out.println(gpp.nuevaPalabraClave("PalabraClave3"));
System.out.println(gpp.nuevaPalabraClave("PalabraClave1")); // nombre repetido
System.out.println(gpp.nuevaPalabraClave(""));              // nombre en blanco
gpp.mostrarPalabrasClaves();

System.out.println("------------------PUBLICACIONES------------------");
IGestorPublicaciones gp = GestorPublicaciones.crear();
//PUBLICACION 2
MiembroEnGrupo mg2 = new MiembroEnGrupo(ga.verAutor(20), gg.verGrupo("Grupo 1"), Rol.ADMINISTRADOR);
LocalDate fecha2= LocalDate.of(2020, 06, 24);
List<PalabraClave> palabras2 = new ArrayList<>();
palabras2.add(gpp.verPalabraClave("PalabraClave4"));
palabras2.add(gpp.verPalabraClave("PalabraClave5"));
palabras2.add(gpp.verPalabraClave("PalabraClave1"));
System.out.println(gp.nuevaPublicacion("Título 2", mg2, fecha2, gt.verTipo("Tipo 2"), gi.verIdioma("Idioma 2"), 
        gl.verLugar("Lugar 2"), palabras2, "Enlace 2", "Resumen 2"));
//PUBLICACION 2

//PUBLICACION 1
MiembroEnGrupo mg1 = new MiembroEnGrupo(ga.verAutor(10), gg.verGrupo("Grupo 1"), Rol.ADMINISTRADOR);
LocalDate fecha1 = LocalDate.of(2020, 06, 24);
List<PalabraClave> palabras1 = new ArrayList<>();
palabras1.add(gpp.verPalabraClave("PalabraClave1"));
palabras1.add(gpp.verPalabraClave("PalabraClave2"));
palabras1.add(gpp.verPalabraClave("PalabraClave3"));
System.out.println(gp.nuevaPublicacion("Título 1", mg1, fecha1, gt.verTipo("Tipo 1"), gi.verIdioma("Idioma 1"),
        gl.verLugar("Lugar 1"), palabras1, "Enlace 1", "Resumen 1"));
//PUBLICACION 1

//PUBLICACION 5
MiembroEnGrupo mg5 = new MiembroEnGrupo(ga.verAutor(30), gg.verGrupo("Grupo 5"), Rol.COLABORADOR);
LocalDate fecha5= LocalDate.of(2020, 8, 15);
ArrayList<PalabraClave> palabras5 = new ArrayList<>();
palabras5.add(gpp.verPalabraClave("PalabraClave3"));
palabras5.add(gpp.verPalabraClave("PalabraClave4"));
palabras5.add(gpp.verPalabraClave("PalabraClave5"));
System.out.println(gp.nuevaPublicacion("Título 5", mg5, fecha5, gt.verTipo("Tipo 5"), gi.verIdioma("Idioma 3"), 
        gl.verLugar("Lugar 5"), palabras5, "Enlace 5", "Resumen 5"));
//PUBLICACION 5

//PUBLICACION 4
MiembroEnGrupo mg4 = new MiembroEnGrupo(ga.verAutor(20), gg.verGrupo("Grupo 4"), Rol.COLABORADOR);
LocalDate fecha4 = LocalDate.of(2020, 03, 15);
List<PalabraClave> palabras4 = new ArrayList<>();
palabras4.add(gpp.verPalabraClave("PalabraClave2"));
System.out.println(gp.nuevaPublicacion("Título 4", mg4, fecha4, gt.verTipo("Tipo 5"), gi.verIdioma("Idioma 3"), 
        gl.verLugar("Lugar 5"), palabras4, "Enlace 4", "Resumen 4"));
//PUBLICACION 4

//PUBLICACION 3
MiembroEnGrupo mg3 = new MiembroEnGrupo(ga.verAutor(30), gg.verGrupo("Grupo 2"), Rol.COLABORADOR);
LocalDate fecha3 = LocalDate.of(2020, 06, 24);
List<PalabraClave> palabras3 = new ArrayList<>();
palabras3.add(gpp.verPalabraClave("PalabraClave1"));
palabras3.add(gpp.verPalabraClave("PalabraClave3"));
palabras3.add(gpp.verPalabraClave("PalabraClave4"));
System.out.println(gp.nuevaPublicacion("Título 3", mg3, fecha3, gt.verTipo("Tipo 1"), gi.verIdioma("Idioma 2"), 
        gl.verLugar("Lugar 2"), palabras3, "Enlace 3", "Resumen 3"));
//PUBLICACION 3

//PUBLICACION 6
MiembroEnGrupo mg6 = new MiembroEnGrupo(ga.verAutor(20), gg.verGrupo("Grupo 5"), Rol.COLABORADOR);
LocalDate fecha6= LocalDate.of(2020, 8, 18);
ArrayList<PalabraClave> palabras6 = new ArrayList<>();
palabras6.add(gpp.verPalabraClave("PalabraClave3"));
palabras6.add(gpp.verPalabraClave("PalabraClave4"));
System.out.println(gp.nuevaPublicacion("Título 1", mg6, fecha6, gt.verTipo("Tipo 4"), gi.verIdioma("Idioma 3"), // título repetido
        gl.verLugar("Lugar 4"), palabras6, "Enlace 6", "Resumen 6"));
//PUBLICACION 6

gp.mostrarPublicaciones();

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sin interfaz gráfica PARTE 2">

System.out.println("------------------ METODOS VARIOS (TP6: VER, BUSCAR, BORRAR, ...) ------------------");

// Se probo y el metodo singleton funciona :)
//CAMBIAR EL CODIGO MAIN IMPLEMENTANDO GESTORES Y PROBAR QUE FUNCIONEN COREECTAMENTE PARA CADA OBJETO DEL PROYECTO
System.out.println("----------------------TIPOS----------------------");
System.out.println("\nTipos ordenados:");
for(Tipo t: gt.verTipos())
    t.mostrar();

System.out.println("\nBorrar 'Tipo 3' sin publicación asignada:");
System.out.println(gt.borrarTipo(gt.verTipo("Tipo 3")));

System.out.println("\nBorrar 'Tipo 1' con publicación asignada:");
System.out.println(gt.borrarTipo(gt.verTipo("Tipo 3")));

System.out.println("\nBorrar Tipo NULL");
System.out.println(gt.borrarTipo(null));

System.out.println("\nMostrar el resultado del borrado:");
gt.mostrarTipos();

System.out.println("\nBuscar y mostrar 'Tipo 2': ");
    for(Tipo t: gt.buscarTipos("Tipo 2"))
        t.mostrar();
    
System.out.println("\nBuscar y mostrar 'Tip': ");
    for(Tipo t: gt.buscarTipos("Tip"))
        t.mostrar();
    
System.out.println("\nMostrar si existe el 'Tipo 5': ");
System.out.println(gt.existeEsteTipo(gt.verTipo("Tipo 5")));

System.out.println();
System.out.println("---------------------AUTORES---------------------");
System.out.println("\nAutores ordenados:");
for(Autor a: ga.verAutores())
    a.mostrar();

System.out.println("\nBorrar 'DNI 1' sin publicación asignada:");
System.out.println(ga.borrarAutor(ga.verAutor(1)));

System.out.println("\nBorrar 'DNI 10' con publicación asignada:");
System.out.println(ga.borrarAutor(ga.verAutor(10)));

System.out.println("\nCrear, buscar y mostrar 'Perez': ");
System.out.println(ga.nuevoAutor(8, "Perez", "Romina", "8", "Clave8", "Clave8"));
System.out.println(ga.nuevoAutor(9, "Perez", "María",  "9", "Clave9", "Clave9"));
    for(Autor a: ga.buscarAlumnos("Perez"))
        a.mostrar();

System.out.println("\nCrear y mostrar si hay autores en 'Grupo Vacio':");
System.out.println(gg.nuevoGrupo("Grupo Vacio", ""));
System.out.println(ga.hayAutoresConEsteGrupo(gg.verGrupo("Grupo Vacio")));

System.out.println("\nCrear y mostrar si hay autores en 'Grupo 2':");
System.out.println(ga.hayAutoresConEsteGrupo(gg.verGrupo("Grupo 2")));

System.out.println();
System.out.println("------------------PUBLICACIONES------------------");
System.out.println("\nModifica Publicacion 'Título 1':");
System.out.println(gp.modificarPublicacion(gp.verPublicaion("Título 1"), mg1, fecha1, gt.verTipo("Tipo 1"), 
        gi.verIdioma("Idioma 1"), gl.verLugar("Lugar 1"), palabras1, "Enlace Modificado", "Resumen Modificado"));

System.out.println("\nBorrar Publicacion 'Título 2':");
System.out.println(gp.borrarPublicacion(gp.verPublicaion("Título 2")));

System.out.println("\nMostrar resultado (ordenado por nombre):");
for(Publicacion p: gp.verPublicaciones())
    p.mostrar();

//
////System.out.println("--------Publicaciones-----------");
//IGestorPublicaciones gestorPublicaciones=GestorPublicaciones.crear();
//
////System.out.println(gestorPublicaciones.nuevaPublicacion("Primero", mg6, fecha6, gestorTipos.verTipos().get(0), idioma6, lugar6, palabrasClaves, "www.buscar.dot", "Primero"));
////System.out.println(gestorPublicaciones.nuevaPublicacion("Primero", mg6, fecha6, gestorTipos.verTipos().get(0), idioma6, lugar6, palabrasClaves, "www.buscar.dot", "Primero"));
////System.out.println(gestorPublicaciones.nuevaPublicacion("Segundo", mg5, fecha5, gestorTipos.verTipos().get(2), idioma5, lugar5, palabrasClaves, "www", "Segundo"));
//
//System.out.println(gestorTipos.borrarTipo(tipo1));
//System.out.println(gestorTipos.borrarTipo(gestorTipos.verTipos().get(1)));
//System.out.println("\nTipos Luego de borrar:");
//gestorTipos.mostrarTipos(gestorTipos.verTiposSinOrdenar() );
//System.out.println("\nTipos Ordenados Luego de borrar:");
//gestorTipos.mostrarTipos(gestorTipos.verTipos() );
//
//System.out.println("\nTipos buscados:");
//gestorTipos.mostrarTipos(gestorTipos.buscarTipos("Tesa"));
//
//
////Prueba Palabras Claves
//System.out.println("--------Palabras Claves-----------");
//
//IGestorPalabrasClaves gestorPalabrasClaves=GestorPalabrasClaves.crear();
//
//System.out.println(gestorPalabrasClaves.nuevaPalabraClave("Clave 3"));
//System.out.println(gestorPalabrasClaves.nuevaPalabraClave("Clave 1"));
//System.out.println(gestorPalabrasClaves.nuevaPalabraClave("Clave 6"));
//System.out.println(gestorPalabrasClaves.nuevaPalabraClave("Clave 9"));
//System.out.println(gestorPalabrasClaves.nuevaPalabraClave("Clave 0"));
//PalabraClave palabraClave10 = new PalabraClave("Clave ");
//
//gestorPalabrasClaves.mostrarPalabrasClaves(gestorPalabrasClaves.verPalabrasClaves());
//
//System.out.println(gestorPublicaciones.nuevaPublicacion("Prueba Palabra Clave", mg1, fecha1, gestorTipos.verTipos().get(0), idioma1, lugar1,gestorPalabrasClaves.verPalabrasClaves(), "wt", "PC"));
//
//
//System.out.println(gestorPalabrasClaves.borrarPalabraClave(gestorPalabrasClaves.verPalabraClave("Clave 1")));
//System.out.println(gestorPalabrasClaves.borrarPalabraClave(gestorPalabrasClaves.verPalabraClave("Clave 5")));
//System.out.println(gestorPalabrasClaves.borrarPalabraClave(gestorPalabrasClaves.verPalabraClave("palabraClave1")));
//
//gestorPalabrasClaves.mostrarPalabrasClaves(gestorPalabrasClaves.verPalabrasClaves());
//
//System.out.println("\n Buscar palabras ");
//
//gestorPalabrasClaves.mostrarPalabrasClaves(gestorPalabrasClaves.buscarPalabrasClaves("Cl"));
//System.out.println("\n Palabras Ordenadas ");
//gestorPalabrasClaves.mostrarPalabrasClaves(gestorPalabrasClaves.verPalabrasClaves());
//
//System.out.println(gestorPalabrasClaves.existeEstaPalabraClave(palabraClave10));
//
////Prueba Lugares
//System.out.println("--------Lugares-----------");
//IGestorLugares gestorLugares=GestorLugares.crear();
//
//System.out.println(gestorLugares.nuevoLugar("lugar5"));
//System.out.println(gestorLugares.nuevoLugar("lugar9"));
//System.out.println(gestorLugares.nuevoLugar("lugar0"));
//System.out.println(gestorLugares.nuevoLugar("lugar1"));
//System.out.println(gestorLugares.nuevoLugar("lugar1"));
//Lugar lug=new Lugar("lugar5");
//
////System.out.println(gestorPublicaciones.nuevaPublicacion("Prueba Lugar", mg6, fecha6, gestorTipos.verTipos().get(0), idioma6, gestorLugares.verLugar("lugar1"), palabrasClaves, "www.buscar.dot", "Primero"));
//
//
//gestorLugares.mostrarLugares(gestorLugares.buscarLugares("lug"));
//
//gestorLugares.mostrarLugares(gestorLugares.verLugares());
//
//System.out.println(gestorLugares.existeEsteLugar(lug));
//
////Prueba Idioma
//System.out.println("--------Idioma-----------");
//IGestorIdiomas gestorIdiomas=GestorIdiomas.crear();
//
//System.out.println(gestorIdiomas.nuevoIdioma("idioma5"));
//System.out.println(gestorIdiomas.nuevoIdioma("idioma9"));
//System.out.println(gestorIdiomas.nuevoIdioma("idioma0"));
//System.out.println(gestorIdiomas.nuevoIdioma("idioma1"));
//System.out.println(gestorIdiomas.nuevoIdioma("idioma1"));
//Idioma idi=new Idioma("idioma9");
//
//System.out.println(gestorPublicaciones.nuevaPublicacion("Prueba Lugar", mg6, fecha6, gestorTipos.verTipos().get(0), gestorIdiomas.verIdioma("idioma0"), gestorLugares.verLugar("lugar1"), palabrasClaves, "www.buscar.dot", "Primero"));
//
////System.out.println(gestorIdiomas.borrarIdioma(idi));
//System.out.println("\nBuscar: ");
//gestorIdiomas.mostrarIdiomas(gestorIdiomas.buscarIdiomas("Id"));
//System.out.println("\nIdiomas Sin Ordenar: ");
//gestorIdiomas.mostrarIdiomas(gestorIdiomas.verIdiomasSinOrdenar());
//System.out.println("\nIdiomas Ordenados: ");
//gestorIdiomas.mostrarIdiomas(gestorIdiomas.verIdiomas());
//
//System.out.println(gestorIdiomas.existeEsteIdioma(idi));
//
////Prueba Grupo
//System.out.println("--------Grupo-----------");
//IGestorGrupos gestorGrupos=GestorGrupos.crear();
//IGestorAutores gestorAutores=GestorAutores.crear();
//Grupo gru=new Grupo("grupo5","descripcion5");
//Grupo grup=new Grupo("gra5","descripcion5");
//
////System.out.println("--------Autores para Probar grupo-----------");
////System.out.println(gestorAutores.nuevoAutor(37731415, "Caigura", "Zaida", Cargo.ADG, "nono", "nono"));
////System.out.println(gestorAutores.nuevoAutor(3715, "Cigura", "Zaida", "cx 34343", "nono", "nono"));
////System.out.println(gestorAutores.nuevoAutor(731415, "Cai", "lala","cx 777", "ono", "ono"));
////System.out.println(gestorAutores.nuevoAutor(3773145, "Ramirez", "Ruth", Cargo.ADG, "non", "non"));
////System.out.println(gestorAutores.nuevoAutor(37731415, "Caigura", "Zaida", Cargo.ADG, "nono", "nono"));
//
////
////System.out.println("--------Grupos Creados-----------");
////System.out.println(gestorGrupos.nuevoGrupo("grupo5","descripcion5"));
////System.out.println(gestorGrupos.nuevoGrupo("grupo0","descripcion0"));
////System.out.println(gestorGrupos.nuevoGrupo("grupo3","descripcion3"));
////System.out.println(gestorGrupos.nuevoGrupo("grupo5","descripcion"));
////System.out.println(gestorGrupos.nuevoGrupo("grupo9","descripcion9"));
////gestorAutores.verAutores().get(0).agregarGrupo(gru, Rol.COLABORADOR);
////gestorAutores.verAutores().get(0).agregarGrupo(grup, Rol.COLABORADOR);
//
////System.out.println(gestorGrupos.borrarGrupo(gru));
//
//System.out.println("\nBuscar: ");
//gestorGrupos.mostrarGrupos(gestorGrupos.buscarGrupos("gr"));
//System.out.println("\nGrupos Sin Ordenar: ");
//gestorGrupos.mostrarGrupos(gestorGrupos.verGruposSinOrdenar());
//System.out.println("\nGrupos Ordenados: ");
//gestorGrupos.mostrarGrupos(gestorGrupos.verGrupos());
//System.out.println(gestorGrupos.existeEsteGrupo(gru));
//
//
////Prueba Autores
//System.out.println("--------Autores-----------");
//IGestorAutores gestorAutores1=GestorAutores.crear();
//
//System.out.println(gestorAutores.nuevoAutor(37731415, "Ramirez", "aida", "cx", "nono", "nono"));
//System.out.println(gestorAutores.nuevoAutor(3715, "Cigura", "Zeida", Cargo.ADG, "nono", "nono"));
//System.out.println(gestorAutores.nuevoAutor(731415, "Cigura", "Aala",Cargo.ADG, "ono", "ono"));
//System.out.println(gestorAutores.nuevoAutor(3773145, "Ramirez", "Ruth", Cargo.ADG, "non", "non"));
//System.out.println(gestorAutores.nuevoAutor(37731405, "Caaa", "Zaida", "jdjjd", "nono", "nono"));
//
//gestorAutores.verAutor(3715).agregarGrupo(grup, Rol.COLABORADOR);
//
//System.out.println("\n--------Pubicaciones para probar autores-----------");
//MiembroEnGrupo mg11 = new MiembroEnGrupo(gestorAutores.verAutor(3715), gru, Rol.COLABORADOR);
//MiembroEnGrupo mg12 = new MiembroEnGrupo(gestorAutores.verAutor(731415), grupos.get(4), Rol.COLABORADOR);
//MiembroEnGrupo mg13 = new MiembroEnGrupo(gestorAutores.verAutor(3773145), grupos.get(4), Rol.COLABORADOR);
//
////System.out.println(gestorPublicaciones.nuevaPublicacion("Primero", mg11, fecha6, gestorTipos.verTipos().get(0), idioma6, lugar6, palabrasClaves, "www.buscar.dot", "Primero"));
////System.out.println(gestorPublicaciones.nuevaPublicacion("Tercero", mg12, fecha6, gestorTipos.verTipos().get(1), idioma6, lugar6, palabrasClaves, "www.buscar.dot", "Primero"));
////System.out.println(gestorPublicaciones.nuevaPublicacion("Segundo", mg13, fecha5, gestorTipos.verTipos().get(2), idioma5, lugar5, palabrasClaves, "www", "Segundo"));
//
//
////System.out.println("\nBorrar");
////System.out.println(gestorAutores.borrarAutor(aut));
//System.out.println("\n buscar profesores");
//gestorAutores.mostrarProfesores(gestorAutores.buscarProfesores("C"));
//System.out.println("\n buscar alumnos");
//gestorAutores.mostrarAlumnos(gestorAutores.buscarAlumnos("Ci"));
//
//System.out.println("\n Autores NO Ordenados");
//gestorAutores.mostrarAutores(gestorAutores.verAutoresSinOrdenar());
//System.out.println("\n Autores Ordenados");
//gestorAutores.mostrarAutores(gestorAutores.verAutores());
//
//System.out.println("\n Ver profesores Ordenados");
//gestorAutores.mostrarProfesores(gestorAutores.verProfesores());
//System.out.println("\n Ver alumnos Ordenados");
//gestorAutores.mostrarAlumnos(gestorAutores.verAlumnos());
//
//System.out.println(gestorAutores.hayAutoresConEsteGrupo(grup));

//</editor-fold>
        
//     //<editor-fold defaultstate="collapsed" desc="Intefaz gráfica"> 
//         VentanaAMGrupo ventanaGrupo = new VentanaAMGrupo(null); //se instancia la ventana
///*
////        ventanaGrupo.setLocationRelativeTo(null); //se centra la ventana
////        ventanaGrupo.setVisible(true); //se hace visible la ventana
//        
////        VentanaAMAlumno ventanaAlumno = new VentanaAMAlumno(null); //se instancia la ventana
////        ventanaAlumno.setLocationRelativeTo(null); //se centra la ventana
////        ventanaAlumno.setVisible(true); //se hace visible la ventana
//*/        
//        VentanaAMProfesor ventanaProfesor = new VentanaAMProfesor(null); //se instancia la ventana
//        ventanaProfesor.setLocationRelativeTo(null); //se centra la ventana
//        ventanaProfesor.setVisible(true); //se hace visible la ventana        
///*        
////        VentanaAIdioma ventanaIdioma = new VentanaAIdioma(null); //se instancia la ventana
////        ventanaIdioma.setLocationRelativeTo(null); //se centra la ventana
////        ventanaIdioma.setVisible(true); //se hace visible la ventana                
//        
////        VentanaALugar ventanaLugar = new VentanaALugar(null); //se instancia la ventana
////        ventanaLugar.setLocationRelativeTo(null); //se centra la ventana
////        ventanaLugar.setVisible(true); //se hace visible la ventana                        
//        
////        VentanaAPalabraClave ventanaPalabraClave = new VentanaAPalabraClave(null); //se instancia la ventana
////        ventanaPalabraClave.setLocationRelativeTo(null); //se centra la ventana
////        ventanaPalabraClave.setVisible(true); //se hace visible la ventana                                
//        
////        VentanaATipo ventanaTipo = new VentanaATipo(null); //se instancia la ventana
////        ventanaTipo.setLocationRelativeTo(null); //se centra la ventana
////        ventanaTipo.setVisible(true); //se hace visible la ventana   
//*/
//
//     //</editor-fold>

    }

}