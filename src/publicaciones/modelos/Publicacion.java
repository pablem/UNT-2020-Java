package publicaciones.modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import grupos.modelos.MiembroEnGrupo;
import grupos.modelos.Rol;
import tipos.modelos.Tipo;
import idiomas.modelos.Idioma;
import java.util.List;
import java.util.Objects;
import lugares.modelos.Lugar;
import palabrasclaves.modelos.PalabraClave;

public class Publicacion {
    
    private String titulo;
    private MiembroEnGrupo unMiembro;
    private LocalDate fechaPublicacion;
    private Tipo unTipo;
    private Idioma unIdioma;
    private Lugar unLugar;
    private List<PalabraClave> palabras;
    private String enlace;
    private String resumen;

    public Publicacion(String titulo, MiembroEnGrupo unMiembro, LocalDate fechaPublicacion, 
            Tipo unTipo, Idioma unIdioma, Lugar unLugar, List<PalabraClave> palabras, 
            String enlace, String resumen) {
        this.titulo = titulo;
        this.unMiembro = unMiembro;
        this.fechaPublicacion = fechaPublicacion;
        this.unTipo = unTipo;
        this.unIdioma = unIdioma;
        this.unLugar = unLugar;
        this.palabras = palabras;
        this.enlace = enlace;
        this.resumen = resumen;
    }
    
    public String verTitulo() {
        return titulo;
    }
    public void asignarTitulo(String titulo) {
        this.titulo = titulo;
    }
    public LocalDate verFechaPublicacion() {
        return fechaPublicacion;
    }
    public void asignarFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    public String verEnlace() {
        return enlace;
    }
    public void asignarEnlace(String enlace) {
        this.enlace = enlace;
    }
    public String verResumen() {
        return resumen;
    }
    public void asignarResumen(String resumen) {
        this.resumen = resumen;
    }
    public MiembroEnGrupo verMiembro() {
        return unMiembro;
    }
    public void asignarMiembro(MiembroEnGrupo miembro) {
        this.unMiembro = miembro;
    }
    public Tipo verTipo() {
        return unTipo;
    }
    public void asignarTipo(Tipo unTipo) {
        this.unTipo = unTipo;
    }
    public Idioma verIdioma() {
        return unIdioma;
    }
    public void asignarIdioma(Idioma unIdioma) {
        this.unIdioma = unIdioma;
    }
    public Lugar verLugar() {
        return unLugar;
    }
    public void asignarLugar(Lugar unLugar) {
        this.unLugar = unLugar;
    }
    public List<PalabraClave> verPalabras() {
        return palabras;
    }
    public void asignarPalabras(List<PalabraClave> palabras) {
        this.palabras = palabras;
    }
    
    public void mostrar() {

        String nombreGrupo = this.unMiembro.verGrupo().verNombre();
        String nombre      = this.unMiembro.verAutor().verNombres();
        String apellido    = this.unMiembro.verAutor().verApellidos();
        Rol    unRol       = this.unMiembro.verRol();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        String fecha = this.fechaPublicacion.format(formato);
        System.out.println("Título: "+this.titulo+
                           "\nAutor: "+apellido+", "+nombre+
                           "\nGrupo: "+nombreGrupo+"\nRol: "+unRol+
                           "\nFecha de Publicación: "+fecha+
                           "\nTipo: "  +this.unTipo+
                           "\nIdioma: "+this.unIdioma+
                           "\nLugar: " +this.unLugar+
                           "\nPalabras clave:\n------------------------------");
            for(PalabraClave p: this.palabras) {
                System.out.println("\t"+p);
            }
        System.out.println("Enlace: "+this.enlace+"\nResumen: "+this.resumen);
        System.out.println();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.titulo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Publicacion other = (Publicacion) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return true;
    }  
    
}