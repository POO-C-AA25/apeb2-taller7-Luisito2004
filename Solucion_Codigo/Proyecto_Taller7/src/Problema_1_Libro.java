/**
*Problema 1 - Jerarquía de clases para el capítulo de libro
*Dibujad un diagrama de clases que muestre la estructura de un capítulo de libro; 
*un capítulo está compuesto por varias secciones, cada una de las cuales comprende varios párrafos y figuras.
*Un párrafo incluye varias sentencias, cada una de las cuales contiene varias palabras.

Note

Suponga que en un futuro se prevé que el sistema gestione además de párrafos y figuras otros componentes, como tablas, listas, viñetas, etc.
Suponga además que una palabra puede aparecer en varias sentencias.
 * @author Luis Bermeo
 */
import java.util.ArrayList;

public class Problema_1_Libro {
    public static void main(String[] args) {
        Palabra p1 = new Palabra("Hola");
        Palabra p2 = new Palabra("mundo");
        Palabra p3 = new Palabra("!");

        Sentencia s1 = new Sentencia();
        s1.palabras.add(p1);
        s1.palabras.add(p2);
        s1.palabras.add(p3);

        Parrafo parrafo = new Parrafo();
        parrafo.sentencias.add(s1);

        Figura figura = new Figura("Diagrama de clases UML");

        Seccion seccion1 = new Seccion();
        seccion1.componentes.add(parrafo);
        seccion1.componentes.add(figura);

        CapituloLibro cap = new CapituloLibro("Capitulo 1 - Introduccion");
        cap.secciones.add(seccion1);

        System.out.println(cap);
    }
}

class CapituloLibro {
    public String titulo;
    public ArrayList<Seccion> secciones;

    public CapituloLibro(String titulo) {
        this.titulo = titulo;
        this.secciones = new ArrayList<>();
    }

    public String toString() {
        return "CapituloLibro{" + "titulo='" + titulo + "', secciones=" + secciones + '}';
    }
}

class Seccion {
    public ArrayList<ComponenteSeccion> componentes;

    public Seccion() {
        this.componentes = new ArrayList<>();
    }

    public String toString() {
        return "Seccion{" + "componentes=" + componentes + '}';
    }
}

// Clase base para extensibilidad
class ComponenteSeccion {

    public ComponenteSeccion() {
    }
    
    public String toString() {
        return "Componente generico";
    }
}

class Parrafo extends ComponenteSeccion {
    public ArrayList<Sentencia> sentencias;

    public Parrafo() {
        this.sentencias = new ArrayList<>();
    }

    public String toString() {
        return "Parrafo{" + "sentencias=" + sentencias + '}';
    }
}

class Figura extends ComponenteSeccion {
    public String descripcion;

    public Figura(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return "Figura{" + "descripcion='" + descripcion + "'}";
    }
}

class Tabla extends ComponenteSeccion {
    public String contenido;

    public Tabla(String contenido){
        this.contenido = "Tabla de ejemplo";
    }
    
    public String toString() {
        return "Tabla{" + "contenido='" + contenido + "'}";
    }
}

class Vinieta extends ComponenteSeccion {
    public String contenido;

    public Vinieta(){
        this.contenido = "Elemento de vinieta";
    }
    
    public String toString() {
        return "Vinieta{" + "contenido='" + contenido + "'}";
    }
}

class Sentencia {
    public ArrayList<Palabra> palabras;

    public Sentencia() {
        this.palabras = new ArrayList<>();
    }

    public String toString() {
        String resultado = "";
        for (Palabra palabra : palabras) {
            resultado += palabra + " ";
        }
        return resultado.trim();
    }
}

class Palabra {
    public String texto;

    public Palabra(String texto) {
        this.texto = texto;
    }

    public String toString() {
        return texto;
    }
}

