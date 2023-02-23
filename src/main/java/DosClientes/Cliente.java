package DosClientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Cliente extends Conexion {
    public Scanner scan=new Scanner(System.in);

    public Cliente() throws IOException {
        super("Cliente");
    }
    public  void  initCLiente(){
        try {
            // salida de servidor y recogida de datos
            output_Server = new DataOutputStream(skCliente.getOutputStream());
            output_cliente = new DataOutputStream(skCliente.getOutputStream());

            //EJERCICIO 2 clientes

            //2 hilos uno para escribir y otro para recibir (?)
            Thread escribir = new Thread();
            Thread leer = new Thread();


        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
