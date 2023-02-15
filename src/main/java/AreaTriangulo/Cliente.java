package AreaTriangulo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
            System.out.println("\n***************************************"+"\nCALCULAR BASE Y ALTURA DE UN TRIANGULO"+"\n***************************************");

            //Escribimos el mensaje de la pregunta de la base del triengulo al cliente desde el servidor

            //imprimimos mensaje del servidor piciendo la base
            input_server = new DataInputStream(skCliente.getInputStream());

            String mensajeBase= input_server.readUTF();
            System.out.println(mensajeBase);

            //capturamos la respuesta en un variable y la mandamos al servidor
            int base= scan.nextInt();
            output_cliente.writeInt(base);

            System.out.println("*******");

            //imprimimos mensaje del servidor piciendo la altura
            input_server = new DataInputStream(skCliente.getInputStream());

            String mensajeAltura= input_server.readUTF();
            System.out.println(mensajeAltura);

            //capturamos la respuesta en un variable y la mandamos al servidor
            int altura= scan.nextInt();
            output_cliente.writeInt(altura);

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
