/**
 * Problema 3 - Sistema de envío de mensajes a móviles
Implemente un sistema de envío de mensajes a móviles.
* Existen dos tipos de mensajes que se pueden enviar entre móviles, mensajes de texto (SMS) y mensajes que contienen imágenes (MMS).
* Por un lado, los mensajes de texto contienen un mensaje en caracteres que se desea enviar de un móvil a otro. 
*Por otro lado, los mensajes que contienen imágenes almacenan información sobre la imagen a enviar, la cual se representará por el nombre del fichero que la contiene. 
*Independientemente del tipo de mensaje, cada mensaje tendrá asociado un remitente de dicho mensaje y un destinatario. 
*Ambos estarán definidos obligatoriamente por un número de móvil, y opcionalmente se podrá guardar información sobre su nombre. 
* Además, los métodos enviarMensaje y visualizarMensaje deben estar definidos.

Note

Para probar el diseño jerarquico de clases, instancia en el clase de prueba Ejecutor, con datos ficticios.
 * @author Luis Bermeo
 */
public class Problema_3_Mensajes {
    public static void main(String[] args) {
        NumTelefono num1 = new NumTelefono("0994758901", "Luis");
        NumTelefono num2 = new NumTelefono("0983568986", "Maria");

        SMS sms1 = new SMS(num1, num2, "Hola, Como estas?");
        MMS mms1 = new MMS(num1, num2, "Imagen.png");

        System.out.println(sms1.enviarMensaje());
        System.out.println(sms1.visualizarMensaje());
        System.out.println(mms1.enviarMensaje());
        System.out.println(mms1.visualizarMensaje());
    }
}

class Mensaje {
    private NumTelefono remitente;
    private NumTelefono destinatario;

    public Mensaje(NumTelefono remitente, NumTelefono destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public String enviarMensaje() {
        return "Mensaje enviado de " + remitente.getNumero() + " a " + destinatario.getNumero();
    }

    public String visualizarMensaje() {
        return "Mensaje generico";
    }

    public NumTelefono getRemitente() {
        return remitente;
    }

    public NumTelefono getDestinatario() {
        return destinatario;
    }
}

class SMS extends Mensaje {
    private String contenido;

    public SMS(NumTelefono remitente, NumTelefono destinatario, String contenido) {
        super(remitente, destinatario);
        this.contenido = contenido;
    }

    @Override
    public String visualizarMensaje() {
        return "SMS: " + contenido;
    }

    @Override
    public String toString() {
        return "SMS de " + getRemitente().getNombre() + " a " + getDestinatario().getNombre() + ": " + contenido;
    }
}

class MMS extends Mensaje {
    private String nombreImagen;

    public MMS(NumTelefono remitente, NumTelefono destinatario, String nombreImagen) {
        super(remitente, destinatario);
        this.nombreImagen = nombreImagen;
    }

    @Override
    public String visualizarMensaje() {
        return "MMS: " + nombreImagen;
    }

    @Override
    public String toString() {
        return "MMS de " + getRemitente().getNombre() + " a " + getDestinatario().getNombre() + ": " + nombreImagen;
    }
}

class NumTelefono {
    private String numero;
    private String nombre;

    public NumTelefono(String numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }
}


