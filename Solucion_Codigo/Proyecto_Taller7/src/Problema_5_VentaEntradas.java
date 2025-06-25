/**
 * Problema 5 - Venta de entradas al teatro
Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:

Se desea gestionar la venta de entradas para un espectáculo en un teatro.
*El patio de butacas del teatro se divide en varias zonas, cada una identificada por su nombre.
*Los datos de las zonas son los mostrados en la siguiente tabla:

NOMBRE ZONA	NÚMERO DE LOCALIDADES	PRECIO NORMA	PRECIO ABONADO
Principal	200	25$	17.5$
PalcoB	40	70$	40$
Central	400	20$	14$
Lateral	100	15.5$	10$
Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y presentar al vendedor el documento
*que justifique que tiene algún tipo de descuento(estudiante, abonado o pensionista).
*El vendedor sacará la entrada del tipo apropiado y de la zona indicada,
*en el momento de la compra se asignará a la entrada un identificador (un número entero) 
*que permitirá la identificación de la entrada en todas las operaciones que posteriormente se desee realizar con ella.

Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador.

Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:

Entradas normales: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
Entradas reducidas (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
Entradas abonado: su precio es el precio para abonados de la zona elegida.
La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.

Note

Caso de uso “Vende entrada”:

El vendedor elige la opción “vende entrada” e introduce la zona deseada, el nombre del espectador y el tipo (normal, abonado o beneficiario de entrada reducida).
Si la zona elegida no está completa, la aplicación genera una nueva entrada con los datos facilitados.
Si no existe ninguna zona con ese nombre, se notifica y finaliza el caso de uso sin generar la entrada.
Si la zona elegida está completa lo notifica y finaliza el caso de uno sin generar la entrada.
La aplicación muestra el identificador y el precio de la entrada.
Caso de uso “Consulta entrada”:

El vendedor elige la opción “consulta entrada” e introduce el identificador de la entrada.
La aplicación muestra los datos de la entrada: nombre del espectador, precio y nombre de la zona. Si no existe ninguna entrada con ese identificador, lo notifica y finaliza el caso de uso
 * @author Luis Bermeo
 */
public class Problema_5_VentaEntradas{
    public static void main(String[] args) {
        Zona zonaPrincipal = new Zona("Principal", 200, 25.0, 17.5);
        Zona zonaPalcoB = new Zona("PalcoB", 40, 70.0, 40.0);
        Zona zonaCentral = new Zona("Central", 400, 20.0, 14.0);
        Zona zonaLateral = new Zona("Lateral", 100, 15.5, 10.0);

        Vendedor vendedor = new Vendedor();

        Entrada e1 = vendedor.venderEntrada("Principal", "Luis", "normal", zonaPrincipal);
        Entrada e2 = vendedor.venderEntrada("PalcoB", "Maria", "abonado", zonaPalcoB);
        Entrada e3 = vendedor.venderEntrada("Central", "Carlos", "reducido", zonaCentral);

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);

        System.out.println(vendedor.consultarEntrada(1));
        System.out.println(vendedor.consultarEntrada(10));
    }
}

class Zona {
    public String nombre;
    public int numLocalidades;
    public double precioNormal;
    public double precioAbonado;
    public int entradasVendidas;

    public Zona(String nombre, int numLocalidades, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.numLocalidades = numLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.entradasVendidas = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean hayEntradasDisponibles() {
        return entradasVendidas < numLocalidades;
    }

    public void venderEntrada() {
        entradasVendidas++;
    }

    public double getPrecioNormal() {
        return precioNormal;
    }

    public double getPrecioAbonado() {
        return precioAbonado;
    }
}

class Entrada {
    public int id;
    public Zona zona;
    public String nombreComprador;
    public double precio;

    public Entrada(int id, Zona zona, String nombreComprador, double precio) {
        this.id = id;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Entrada ID: " + id +
                "\nComprador: " + nombreComprador +
                "\nZona: " + zona.getNombre() +
                "\nPrecio: $" + precio;
    }
}

class EntradaNormal extends Entrada {
    public EntradaNormal(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador, zona.getPrecioNormal());
    }
}

class EntradaReducida extends Entrada {
    public EntradaReducida(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador, zona.getPrecioNormal() * 0.85);
    }
}

class EntradaAbonado extends Entrada {
    public EntradaAbonado(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador, zona.getPrecioAbonado());
    }
}

class Vendedor {
    public Entrada[] entradasVendidas = new Entrada[10];
    public int contadorEntradas = 0;

    public Vendedor() {
    }
    

    public Entrada venderEntrada(String zonaNombre, String nombreComprador, String tipoEntrada, Zona zona) {
        if (!zona.getNombre().equalsIgnoreCase(zonaNombre)) {
            System.out.println("Zona no existe.");
            return null;
        }
        if (!zona.hayEntradasDisponibles()) {
            System.out.println("Zona completa.");
            return null;
        }

        zona.venderEntrada();

        Entrada entrada = null;
        contadorEntradas++;

        switch (tipoEntrada.toLowerCase()) {
            case "normal":
                entrada = new EntradaNormal(contadorEntradas, zona, nombreComprador);
                break;
            case "reducido":
                entrada = new EntradaReducida(contadorEntradas, zona, nombreComprador);
                break;
            case "abonado":
                entrada = new EntradaAbonado(contadorEntradas, zona, nombreComprador);
                break;
            default:
                System.out.println("Tipo de entrada inválido.");
                return null;
        }
        entradasVendidas[contadorEntradas - 1] = entrada;
        System.out.println("Venta exitosa. ID entrada: " + contadorEntradas + ", Precio: $" + entrada.precio);
        return entrada;
    }

    public Entrada consultarEntrada(int id) {
        if (id <= 0 || id > contadorEntradas) {
            System.out.println("Entrada no encontrada.");
            return null;
        }
        return entradasVendidas[id - 1];
    }
}


