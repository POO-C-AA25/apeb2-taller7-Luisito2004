/**
 * Problema 6 - Sistema Un Banco
El banco UN BANCO mantiene las cuentas de varios clientes.
*Los datos que describen a cada una de las cuentas consisten en el número de cuenta, el nombre del cliente y el balance actual.
*Escriba una clase para implementar dicha cuenta bancaria. El método constructor debe aceptar como parámetros el número de cuenta y el nombre.
*Debe proporcionarse métodos para depositar o retirar una cantidad de dinero y obtener el balance actual.

El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS.
*Una cuenta de cheques puede sobregirarse (el balance puede ser menor que cero), pero una cuenta de ahorros no.
*Al final de cada mes, se calcula el interés sobre la cantidad que tenga la cuenta de ahorros.
*Este interés se suma al balance. Escriba clases para describir cada uno de estos tipos de cuentas, haciendo un máximo uso de la herencia.
*La clase de la cuenta de ahorros debe proporcionar un método que sea invocado para calcular el interés.
* Además, el banco está pensando en implementar una cuenta PLATINO que viene siendo similar a los otros dos tipos anteriores de cuentas bancarias,
*ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.

Note

Ud. debe implementar una clase de PRUEBA (Clase de ejecución) desde la cual se pueda evidenciar el correcto funcionamiento de cada clase con n clientes,
*y que además se pueda mostrar el balance (estado de cuenta) para cada cliente.
 * @author Luis Bermeo
 */
import java.util.ArrayList;

public class Problema_6_SistemaBanco {
    public static void main(String[] args) {
        Ahorro cuenta1 = new Ahorro("Luis Bermeo", "123456789", 600.0);
        Platino cuenta2 = new Platino("Miguel Escaleras", "246897531", 1500.0);
        Cheque cuenta3 = new Cheque("Christopher Grefa", "987654321", 4000.0);
        
        ArrayList<Cuenta> clientes = new ArrayList<>();
        clientes.add(cuenta1);
        clientes.add(cuenta2);
        clientes.add(cuenta3);
  
        Banco banco = new Banco("Banco de Loja", clientes);
        
        for(Cuenta cliente: clientes){
            System.out.println(cliente + "\n");
        }
            
    }
    
}


class Banco{
    public String nombreBanco;
    public ArrayList<Cuenta> clientes;

    public Banco(String nombreBanco, ArrayList<Cuenta> cuentas) {
        this.nombreBanco = nombreBanco;
        this.clientes = new ArrayList<>();
    }
     
}

class Cuenta{
    public String nombreCliente;
    public String numCuenta;
    public double balanceActual;

    public Cuenta(String nombreCliente, String numCuenta, double balanceActual) {
        this.nombreCliente = nombreCliente;
        this.numCuenta = numCuenta;
        this.balanceActual = balanceActual;
    }
    

    public String getNumCuenta() {
        return numCuenta;
    }

    public double getBalanceActual() {
        return balanceActual;
    }
    
    public void depositar(double deposito){
        this.balanceActual += deposito;
    }
    
    public void retirar(double retiro){
        this.balanceActual = this.balanceActual - retiro;
    }
    
    public double obtenerBalanceActual(){
        return this.getBalanceActual();
    }
    
}

class Cheque extends Cuenta{

    public Cheque(String nombreCliente, String numCuenta, double balanceActual) {
        super(nombreCliente, numCuenta, balanceActual);
    }

}

class Platino extends Cuenta{

    public Platino(String nombreCliente, String numCuenta, double balanceActual) {
        super(nombreCliente, numCuenta, balanceActual);
    }

    public void calcularInteres(){
        this.balanceActual += this.balanceActual * 0.10;
    }
    
}

class Ahorro extends Cuenta{

    public Ahorro(String nombreCliente, String numCuenta, double balanceActual) {
        super(nombreCliente, numCuenta, balanceActual);
    }
    
    public void calcularInteres(){
        this.balanceActual += this.balanceActual * 0.05;
    }
    
}
