package AreaTriangulo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Cliente extends Conexion {
    public Scanner scan=new Scanner(System.in);

    public Cliente() throws IOException {
        super("Clienta");
    }
    public  void  initCLiente(){
        try {
            // salida de servidor y recogida de datos
            output_Server = new DataOutputStream(skCliente.getOutputStream());
            output_cliente = new DataOutputStream(skCliente.getOutputStream());

            //EJERCICIO Triangulo:
            System.out.println("\n-------------------------"+"CALCULAR BASE Y ALTURA DE UN TRIANGULO"+"\n-------------------------");

            //Escribimos el mensaje de la pregunta de la base del triengulo al cliente desde el servidor
            input_server = new DataInputStream(skCliente.getInputStream());
            String mensajeServer= input_server.readUTF();
            System.out.println(mensajeServer);

            String operacion= scan.next();
            output_cliente.writeUTF(operacion);


            /*----- La respuesta del calculo el cliente de realizado por servidor -----*/

            input_server= new DataInputStream(skCliente.getInputStream());

            String respuesta= input_server.readUTF();
            System.out.println(respuesta);

        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
