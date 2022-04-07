package grupos.modelos;
import autores.modelos.GestorAutores;
import interfaces.IGestorAutores;
import interfaces.IGestorGrupos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestorGrupos implements IGestorGrupos{
    IGestorAutores gestorAutores=GestorAutores.crear();
    private List<Grupo> grupos= new ArrayList<>();

//Se implementa el patrón Singleton para crear un solo gestor autores:
    
    public static GestorGrupos instancia; // variable de clase del tipo GestorAutores, instancia valor predeterminado es null
    private GestorGrupos(){               // para evitar que el operador new instanciado ejecute el constructor
    }
    public static GestorGrupos crear(){
            if(instancia==null){
            instancia= new GestorGrupos();
            }
           return instancia;
    }
    
    //Se crea el comparador con el que se ordenan listas por nombre:
    Comparator<Grupo> grupoComparador=(Grupo grupo1, Grupo grupo2) -> grupo1.verNombre().compareTo(grupo2.verNombre());
    
    @Override
    public String nuevoGrupo(String nombre, String descripcion) {
        if((nombre!= null) && (!nombre.isBlank())){
            Grupo grupoNuevo = new Grupo(nombre,descripcion);              
                if(!this.grupos.contains(grupoNuevo)){
                    this.grupos.add(grupoNuevo);
                    return MENSAJE_EXITO;
                }
                else
                    return MENSAJE_REPETIDO;
                              }
        else 
            return MENSAJE_ERROR;       
    }
    
    @Override
    public String modificarGrupo(Grupo grupo, String descripcion) {
        if(grupos.contains(grupo)){
                if((descripcion != null) && (!descripcion.isBlank())){
                        int indice=grupos.indexOf(grupo);
                        if(indice == (-1)){
                            return MENSAJE_MODIFICACION_SINGRUPO;
                        }
                        else{
                            grupos.get(indice).asignarDescripcion(descripcion);
                            return MENSAJE_MODIFICACION_EXITO;
                            }
                         }
                else{return MENSAJE_MODIFICACION_BLANCO;}
                }
        else{return MENSAJE_MODIFICACION_SINGRUPO;}
    }

    @Override
    public List<Grupo> verGrupos() {
        this.grupos.sort(grupoComparador); 
        return this.grupos;
    }
    
    @Override
    public Grupo verGrupo(String nombre){
        Grupo grupoBuscado = new Grupo(nombre,null);
        int indice=grupos.indexOf(grupoBuscado);
        if(indice == (-1))
            return null;
        else
            return grupos.get(indice);
    }

//    @Override
//    //Releer como se soluciono y verificar que funcione bien
//    public String borrarGrupo(Grupo grupo) {
//    boolean existeGrupo= false;
//    int indiceBorrar = (-1);
//    if((grupo!=null)&&(!grupo.verNombre().isBlank())){
//                    for(int indiceAutor=0 ; indiceAutor<gestorAutores.verAutores().size() ; indiceAutor++){
//                        for(int indiceGrupo=0 ; indiceGrupo<gestorAutores.verAutores().get(indiceAutor).verGrupos().size() ; indiceGrupo++){
//                        if( grupo.equals(gestorAutores.verAutores().get(indiceAutor).verGrupos().get(indiceGrupo).verGrupo() ) ){
//                             existeGrupo=true;
//                            }
//                        }
//                    }
//                    if(existeGrupo){return MENSAJE_REPETIDO; }
//                    else{
//                        indiceBorrar=this.grupos.indexOf(grupo);
//                        if((indiceBorrar!= (-1))){ this.grupos.remove(grupo);
//                        return MENSAJE_EXITO;}
//                        else{return MENSAJE_ERROR;}
//                        }
//                    }
//    else{return MENSAJE_ERROR;}
//    }
    
    @Override
    public String borrarGrupo(Grupo grupo){
    if((grupo!=null)&&(!grupo.verNombre().isBlank())){
        if(grupo.tieneMiembros())
                return MENSAJE_REPETIDO;//se puede agregar otro mensaje? por ej: No se pudo borrar el grupo porque hay autores que pertenecen a él
        if(this.existeEsteGrupo(grupo)) {
            this.grupos.remove(grupo);
            return MENSAJE_EXITO;
        }                
        else
            return MENSAJE_ERROR;
    }                  
    else 
        return MENSAJE_ERROR;
    }
    
    @Override
    public List<Grupo> buscarGrupos(String nombre) {
        List<Grupo> gruposBuscados = new ArrayList<>();
        if((nombre!= null) && (!nombre.isBlank())){
            for(Grupo g: grupos){
                if((g.verNombre().equals(nombre)) || (g.verNombre().startsWith(nombre))){
                    if((!gruposBuscados.contains(g))){
                     gruposBuscados.add(g);
                    }
                } 
            }
            if(gruposBuscados != null){
                gruposBuscados.sort(grupoComparador);
                return gruposBuscados;
            }      
        }
        return gruposBuscados; //QUITÉ DOS ELSE RETURN NULL
    } 
    
    @Override
    public boolean existeEsteGrupo(Grupo grupo) {
        if(grupos.contains(grupo))
            return true;
        else
            return false;
    }
    
    @Override
    public void mostrarGrupos(){
    for(Grupo t: this.grupos)
        t.mostrar();
    }
}
