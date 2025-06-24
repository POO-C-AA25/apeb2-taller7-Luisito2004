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

public class Problema_1_Libro{
    public static void main(String[] args) {
        Palabra p1 = new Palabra("Hola");
        Palabra p2 = new Palabra("Mundo");
        Sentencia s1 = new Sentencia();
        s1.palabras.add(p1);
        s1.palabras.add(p2);
        
    }

}

class CapituloLibro{
    public String titulo;
    public ArrayList<Seccion> secciones;

    public CapituloLibro(String titulo) {
        this.titulo = titulo;
        this.secciones = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "CapituloLibro{" + "titulo=" + titulo + ", secciones=" + secciones + '}';
    }

}

class Seccion{
    public ArrayList<ComponenteSeccion> componentes;

}

class ComponenteSeccion{


}

class Parrafo extends ComponenteSeccion{
    public ArrayList<Sentencia> sentencias;

    public Parrafo(){
        this.sentencias = new ArrayList<>();
    }

    public String toString(){
        return "Parrafo{" + "sentencias=" + sentencias + '}';

    }

}

class Figura extends ComponenteSeccion{


}

class Tabla extends ComponenteSeccion{

}

class Vinieta extends ComponenteSeccion{

}
class Sentencia{
    public ArrayList<Palabra> palabras;

    public Sentencia(){
        this.palabras = new ArrayList<>();
    }

    public String toString(){
        for(Palabra palabra: palabras){
            return palabra.toString();
        }
        return "";
    }

}


class Palabra{
    public String texto;

    public Palabra(String texto){
        this.texto = texto;
    }

    public String toString(){
        return this.texto;
    }

}
