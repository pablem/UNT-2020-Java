package autores.modelos;
import grupos.modelos.Grupo;
import grupos.modelos.MiembroEnGrupo;
import interfaces.IGestorAutores;
import interfaces.IGestorPublicaciones;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.Publicacion;

public class GestorAutores implements IGestorAutores {
    IGestorPublicaciones gestorPublicaciones=GestorPublicaciones.crear();
    private List<Autor> autores= new ArrayList<>();
//    private List<Profesor> profesores= new ArrayList<>();
//    private List<Alumno> alumnos= new ArrayList<>();

//Se implementa el patrón Singleton para crear un solo gestor autores:
    
    public static GestorAutores instancia; // variable de clase del tipo GestorAutores, instancia valor predeterminado es null
    private GestorAutores(){               // para evitar que el operador new instanciado ejecute el constructor
    }
    public static GestorAutores crear(){
            if(instancia==null){
            instancia= new GestorAutores();
            }
           return instancia;
    }
    
    //Se crea el comparador con el que se ordenan listas por nombres y apellidos:
    Comparator<Autor> autorComparador=(Autor autor1, Autor autor2) -> (autor1.verApellidos().concat(autor1.verNombres())).compareTo((autor2.verApellidos().concat(autor2.verNombres())));
    
    @Override
    public String nuevoAutor(int dni, String apellidos, String nombres, Cargo cargo, String clave, String claveRepetida) {
        if((dni!= 0) && (apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) 
            && (!nombres.isBlank()) && (cargo!=null) ){
            Autor autorNuevo= new Profesor(dni,apellidos,nombres,clave,cargo);              
            if(!this.autores.contains(autorNuevo)){
                this.autores.add(autorNuevo);
                return MENSAJE_EXITO;
            }
            else
                return MENSAJE_REPETIDO;
        }
        else 
            return MENSAJE_ERROR;
    }

    @Override
    public String nuevoAutor(int dni, String apellidos, String nombres, String cx, String clave, String claveRepetida) {
        if((dni!= 0) && (apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) 
                     && (!nombres.isBlank()) && (cx!=null) && (!cx.isBlank()) ){
            Autor autorNuevo= new Alumno(dni,apellidos,nombres,clave,cx);              
            if(!this.autores.contains(autorNuevo)){
                this.autores.add(autorNuevo);
                return MENSAJE_EXITO;
            }
            else 
                return MENSAJE_REPETIDO;
        }
        else 
            return MENSAJE_ERROR;   
    }

    @Override
    public String modificarAutor(Autor autor, String apellidos, String nombres, Cargo cargo, String clave, String claveRepetida) {
           if(autores.contains(autor)){
                                if((apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) 
                                     && (!nombres.isBlank()) && (cargo!=null) ){
                                      int indice=autores.indexOf(autor);
                                      if(indice == (-1)){
                                          return MENSAJE_MODIFICACION_SINAUTOR;
                                            }
                                      else{
                                          if(autor instanceof Profesor){
                                              ((Profesor)autores.get(indice)).asignarApellidos(apellidos);
                                              ((Profesor)autores.get(indice)).asignarNombres(nombres);
                                              ((Profesor)autores.get(indice)).asignarCargo(cargo);
                                              ((Profesor)autores.get(indice)).asignarClave(clave);
                                          return MENSAJE_MODIFICACION_EXITO;
                                          }
                                        else{return MESAJE_MODIFICAION_SINPROFESOR;}
                                            }
                                        }
                                else{return MENSAJE_MODIFICACION_BLANCO;}
                                    }
           else{return MENSAJE_MODIFICACION_SINAUTOR;}
    }

    @Override
    public String modificarAutor(Autor autor, String apellidos, String nombres, String cx, String clave, String claveRepetida) {
               if(autores.contains(autor)){
                                if((apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) 
                                     && (!nombres.isBlank()) && (cx!=null) && (!cx.isBlank())){
                                      int indice=autores.indexOf(autor);
                                      if(indice == (-1)){
                                          return MENSAJE_MODIFICACION_SINAUTOR;
                                            }
                                      else{
                                          if(autor instanceof Alumno){
                                              ((Alumno)autores.get(indice)).asignarApellidos(apellidos);
                                              ((Alumno)autores.get(indice)).asignarNombres(nombres);
                                              ((Alumno)autores.get(indice)).asignarCx(cx);
                                              ((Alumno)autores.get(indice)).asignarClave(clave);
                                          return MENSAJE_MODIFICACION_EXITO;
                                          }
                                          else{return MESAJE_MODIFICAION_SINALUMNO;}
                                            }
                                        }
                                else{return MENSAJE_MODIFICACION_BLANCO;}
                                    }
           else{return MENSAJE_MODIFICACION_SINAUTOR;}
    }

    @Override
    public boolean existeEsteAutor(Autor autor) {
        if(autores.contains(autor))
            return true;
        else
            return false;
    }

    @Override
    public List<Autor> verAutores() {
        this.autores.sort(autorComparador);
        return this.autores; 
    }

//    @Override
//    public List<Autor> verAutoresSinOrdenar() {
//        return autores;
//    }
    
    @Override
    public List<Profesor> verProfesores(){
        List<Profesor> profesores = new ArrayList<>();
        for(Autor a: this.autores){ // debería hacer control de !=null???
            if(a instanceof Profesor)
                profesores.add((Profesor) a);
        }
//        if(profesores != null){
//            Comparator<Profesor> profesorComparador=(Profesor profesor1, Profesor profesor2) -> 
//                    (profesor1.verApellidos().concat(profesor1.verNombres())).compareTo((profesor2.verApellidos().concat(profesor2.verNombres())));
            profesores.sort(autorComparador);
            return profesores;
//        }
//        else
//            return null;
    }
    
//    @Override
//    public List<Profesor> verProfesoresSinOrdenar() {
//        for(Autor r: autores){
//            if(r instanceof Profesor){
//            profesores.add(((Profesor)r));
//            }
//            }
//        return profesores;
//    }
    
    @Override
    public List<Alumno> verAlumnos(){
        List<Alumno> alumnos = new ArrayList<>();
        for(Autor a: autores){
            if(a instanceof Alumno)
                alumnos.add((Alumno) a);
        }
//        if(alumnos != null){
//            Comparator<Alumno> alumnoComparador=(Alumno alumno1, Alumno alumno2) ->
//            (alumno1.verApellidos().concat(alumno1.verNombres())).compareTo((alumno2.verApellidos().concat(alumno2.verNombres())));
            alumnos.sort(autorComparador);
            return alumnos;
//        }
//        else
//            return null;
    }

//    @Override
//    public List<Alumno> verAlumnosSinOrdenar() {
//        for(Autor r: autores){
//            if(r instanceof Alumno){
//            alumnos.add(((Alumno)r));
//            }
//            }
//        return alumnos;        
//    }

    @Override
    public Autor verAutor(int dni) {
        Autor profesorBuscado = new Profesor(dni,null,null,null,null);
        Autor alumnoBuscado = new Alumno(dni,null,null,null,null);
        int indice = autores.indexOf(profesorBuscado);
        if(indice == (-1)){
            indice = autores.indexOf(alumnoBuscado);
            if(indice == (-1)){
                    return null;
            }
            else{return autores.get(indice);}
        }
        else{return autores.get(indice);} 
    }

    @Override
    public String borrarAutor(Autor autor) {
        if( (autor != null) && (autor.verDni() != 0) && (autor.verApellidos() != null) && (!autor.verApellidos().isBlank())
            && (autor.verNombres() !=null) && (!autor.verNombres().isBlank())  ){
            for(Publicacion p: gestorPublicaciones.verPublicaciones()) {
                if(autor.equals(p.verMiembro().verAutor()))
                    return MENSAJE_REPETIDO;
            }
            if(this.existeEsteAutor(autor)) {
                this.autores.remove(autor);
                return MENSAJE_EXITO;
            }                
            else
                return MENSAJE_ERROR;
        }                  
        else 
            return MENSAJE_ERROR;
    }

    @Override
    public List<Profesor> buscarProfesores(String apellidos) {
        List<Profesor> profesoresBuscados = new ArrayList<>();
        if( (apellidos != null) && (!apellidos.isBlank()) ){
            for(Autor a: autores){
                if(a instanceof Profesor) {
                    if((a.verApellidos().equals(apellidos)) || (a.verApellidos().startsWith(apellidos))){
                        if((!profesoresBuscados.contains(a)))
                            profesoresBuscados.add((Profesor) a);
                    } 
                }
            }
            if(profesoresBuscados != null){
                profesoresBuscados.sort(autorComparador);
                return profesoresBuscados;
            }
        }
        return profesoresBuscados;
    }

@Override
    public List<Alumno> buscarAlumnos(String apellidos) {
        List<Alumno> alumnosBuscados = new ArrayList<>();
        if( (apellidos != null) && (!apellidos.isBlank()) ){
            for(Autor a: autores){
                if(a instanceof Alumno) {
                    if((a.verApellidos().equals(apellidos)) || (a.verApellidos().startsWith(apellidos))){
                        if((!alumnosBuscados.contains(a)))
                            alumnosBuscados.add((Alumno) a);
                    } 
                }
            }
            if(alumnosBuscados != null){
                alumnosBuscados.sort(autorComparador);
                return alumnosBuscados;
            }
        }
        return alumnosBuscados;
    }

//    @Override
//    public boolean hayAutoresConEsteGrupo(Grupo grupo) {
//        boolean existeGrupo=false;
//            for(int indiceAutor=0; indiceAutor<autores.size(); indiceAutor++){
//                for(int indiceGrupo=0; indiceGrupo<autores.get(indiceAutor).verGrupos().size(); indiceGrupo++ ){
//                    if( grupo.equals(autores.get(indiceAutor).verGrupos().get(indiceGrupo).verGrupo() ) ){
//                        existeGrupo=true;
//                        }
//                    }
//            }
//        return existeGrupo;
//    }
    
    @Override
    public boolean hayAutoresConEsteGrupo(Grupo grupo) {
        for(Autor a : autores){
            for(MiembroEnGrupo m: a.verGrupos()){
                if(grupo.equals(m.verGrupo()))
                    return true;   
                }
        }
        return false;
    }
    
    @Override
    public void mostrarAutores(){
    for(Autor a: this.autores)
        a.mostrar();
    }
    
    @Override
    public void mostrarProfesores(){
    for(Autor a: this.autores)
        if(a instanceof Profesor)
            ((Profesor)a).mostrar();
    }
    
    @Override
    public void mostrarAlumnos(){ 
    for(Autor a: this.autores)
        if(a instanceof Alumno)
            ((Alumno)a).mostrar();
    }    
}
