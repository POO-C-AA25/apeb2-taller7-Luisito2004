/**
 * Problema 4 - Sistema de nómina para trabajadores
Se desea desarrollar un sistema de nómina para los trabajadores de una empresa.
* Los datos personales de los trabajadores son nombre y apellidos, dirección y DNI. Además, existen diferentes tipos de trabajadores:

Fijos Mensuales: que cobran una cantidad fija al mes.
Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
Por Horas: cobran un precio por cada una de las horas que han realizado durante el mes.
* El precio es fijo para las primeras 40 horas y es otro para las horas realizadas a partir de la 40 hora mensual.
Jefe: cobra un sueldo fijo (no hay que calcularlo).
* Cada empleado tiene obligatoriamente un jefe (exceptuando los jefes que no tienen ninguno).
* El programa debe permitir dar de alta a trabajadores, así como fijar horas o ventas realizadas e imprimir la nómina correspondiente al final de mes.
Note

Diseñe la jerarquia de clases UML basado en herencia, que defina de mejor forma el escenario planteado.
Para probar el diseño de clases, instancia en el clase de prueba Ejecutor (la-s clase-s respectiva-s), con datos aleatorios.
En los escenarios de prueba verifique su solución con al menos 2 tipos de trabajadores.
 * @author Luis Bermeo
 */
public class Problema_4_SistemaNomina {
    public static void main(String[] args) {
        Jefe j1 = new Jefe("Jose", "Aguirre", "Mayorista", "3603595739", 3500.0);
        Jefe j2 = new Jefe("Jorge", "Macas", "Puerta de la Ciudad", "234156478", 2500.0);
        Jefe j3 = new Jefe("Christopher", "Grefa", "Ciudad Victoria", "0952352342", 1500.0);

        FijoMensual t1 = new FijoMensual(j1, "Luis", "Lopez", "San Sebastian", "123456789", 2000.0);
        Comisionista t2 = new Comisionista(j2, 10, 5000.0, "Maria", "Buri", "Eucaliptos", "987654321", 0.0);
        PorHora t3 = new PorHora(j3, 45, 10.0, 15.0, "Carlos", "Alberto", "Epoca", "246897531", 0.0);

        t2.calcularSueldo();
        t3.calcularSueldo();

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
    }
}

class Trabajador {
    private String nombres;
    private String apellidos;
    private String direccion;
    private String DNI;
    protected double sueldo;

    public Trabajador(String nombres, String apellidos, String direccion, String DNI, double sueldo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.DNI = DNI;
        this.sueldo = sueldo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDNI() {
        return DNI;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombres +
               "\nApellidos: " + apellidos +
               "\nDireccion: " + direccion +
               "\nDNI: " + DNI +
               "\nSueldo: " + sueldo;
    }
}

class FijoMensual extends Trabajador {
    private Jefe jefe;

    public FijoMensual(Jefe jefe, String nombres, String apellidos, String direccion, String DNI, double sueldo) {
        super(nombres, apellidos, direccion, DNI, sueldo);
        this.jefe = jefe;
    }

    public Jefe getJefe() {
        return jefe;
    }

    @Override
    public String toString() {
        return "\nJefe: " + jefe +
               "\n\nTrabajador Fijo Mensual: " + super.toString();
    }
}

class Comisionista extends Trabajador {
    private Jefe jefe;
    private int porcentaje;
    private double totalVentas;

    public Comisionista(Jefe jefe, int porcentaje, double totalVentas, String nombres, String apellidos, String direccion, String DNI, double sueldo) {
        super(nombres, apellidos, direccion, DNI, sueldo);
        this.jefe = jefe;
        this.porcentaje = porcentaje;
        this.totalVentas = totalVentas;
    }

    public double calcularSueldo() {
        double sueldoCalculado = totalVentas * (porcentaje / 100.0);
        setSueldo(sueldoCalculado);
        return sueldoCalculado;
    }

    @Override
    public String toString() {
        return "\nJefe: " + jefe +
               "\n\nTrabajador Comisionista:" +
               "\nPorcentaje: " + porcentaje +
               "\nTotal de Ventas: " + totalVentas + super.toString();
    }
}

class PorHora extends Trabajador {
    private Jefe jefe;
    private int horasTrabajadas;
    private double precioNormal;
    private double precioExtra;

    public PorHora(Jefe jefe, int horasTrabajadas, double precioNormal, double precioExtra, String nombres, String apellidos, String direccion, String DNI, double sueldo) {
        super(nombres, apellidos, direccion, DNI, sueldo);
        this.jefe = jefe;
        this.horasTrabajadas = horasTrabajadas;
        this.precioNormal = precioNormal;
        this.precioExtra = precioExtra;
    }

    public double calcularSueldo() {
        double sueldoCalculado;
        if (horasTrabajadas <= 40) {
            sueldoCalculado = horasTrabajadas * precioNormal;
        } else {
            sueldoCalculado = (40 * precioNormal) + ((horasTrabajadas - 40) * precioExtra);
        }
        setSueldo(sueldoCalculado);
        return sueldoCalculado;
    }

    @Override
    public String toString() {
        return "\nJefe: " + jefe +
               "\n\nTrabajador Por Hora:" +
               "\nHoras Trabajadas: " + horasTrabajadas +
               "\nPrecio por Hora Normal: " + precioNormal +
               "\nPrecio por Hora Extra: " + precioExtra + super.toString();
    }
}

class Jefe extends Trabajador {
    public Jefe(String nombres, String apellidos, String direccion, String DNI, double sueldo) {
        super(nombres, apellidos, direccion, DNI, sueldo);
    }

    @Override
    public String toString() {
        return getNombres() + " " + getApellidos();
    }
}

