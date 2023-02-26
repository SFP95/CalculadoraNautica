package Fichero.pruebaSinFichero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.MessageDigest;
import java.util.Scanner;

public class Servidor extends Conexion {
    String mensaje="";
    Scanner scan = new Scanner(System.in);

    public Servidor() throws IOException {
        super("Servidor");
    }
    public void initServidor(){
        try {
            // salida de servidor y recogida de datos
            output_Server = new DataOutputStream(skCliente.getOutputStream());
            input_server = new DataInputStream(skCliente.getInputStream());

            //FileOutputStream fichero= new FileOutputStream("ficheroCliente.txt");

            //EJERCICIO FICHEROS
            while (!mensaje.equals("salir")) {
                System.out.println("Escribe algo para enviar");
                mensaje = scan.nextLine();
                output_Server.writeUTF(mensaje);


                /*----- Lo que recibe el cliente de parte de servidor -----*/
                input_server = new DataInputStream(skCliente.getInputStream());
                mensajeRecibido = input_server.readUTF();
                System.out.println("Mensaje recibido de Servidor:\n" + mensaje);
            }
            skCliente.close();

        }catch (Exception e){
            System.out.println("¡NO SE HA PODIDO RELIZAR LA OPERACION!");
            System.out.println("Errores encontrados en: " + e.getMessage());
        }
    }
}
