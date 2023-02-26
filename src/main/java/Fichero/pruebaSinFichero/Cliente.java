package Fichero.pruebaSinFichero;

import java.io.*;
import java.security.MessageDigest;
import java.util.Scanner;

public class Cliente extends Conexion {
    String mensaje="";
    Scanner scan = new Scanner(System.in);

    public Cliente() throws IOException {
        super("Cliente");
    }
    public  void  initCLiente(){
        try {
            skCliente =skServidor.accept(); // que queda a la espera de recibir la petición de conexión
            System.out.println("- Conexión aceptada de : "+ skCliente.getInetAddress().getHostName());
            System.out.println("------------\n");

            //EJERCICIO:  FICHERO DE CLIENTE Y HACER RESUMEN
            while (!mensaje.equals("salir")) {
                output_cliente = new DataOutputStream(skCliente.getOutputStream());
                input_cliente = new DataInputStream(skCliente.getInputStream());

                mensajeRecibido = input_cliente.readUTF();
                System.out.println("MENSAJE RECIBIDO : \n" + mensajeRecibido);
                System.out.println("\n----------\nEscribe 'enviar' para mandarlo de vuelta:");
                mensaje = scan.nextLine();
                output_cliente.writeUTF("" + mensaje);
            }
            skServidor.close();
        }catch (Exception e){
            System.out.println("¡NO SE HA PODIDO RELIZAR LA OPERACION!");
            System.out.println("Errores encontrados en: " + e.getMessage());
        }
    }
}
