package CalculadoraNautica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Cliente extends Conexion{
    public Scanner scan=new Scanner(System.in);

    public Cliente() throws IOException {
        super("Clienta");
    }
    public  void  initCLiente(){
        try {
            // salida de servidor y recogida de datos
            output_Server = new DataOutputStream(skCliente.getOutputStream());
            output_cliente = new DataOutputStream(skCliente.getOutputStream());

            //EJERCICIO:
            // Recogida y muertas de mensaje de servidor pidiendo la operación
            input_server = new DataInputStream(skCliente.getInputStream());

            String mensajeServer= input_server.readUTF();
            System.out.println(mensajeServer);

            //reecogida de datos por pantalla
            String operacion= scan.next();
            output_cliente.writeUTF(operacion);

            System.out.println("--------------------\n"+"-Cliente a dicho "+operacion);

            /*----- Lo que recibe el cliente de parte de servidor -----*/

//            input_server= new DataInputStream(skCliente.getInputStream());
//
//            String respuesta= input_server.readUTF();
//            System.out.println(respuesta);

        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
