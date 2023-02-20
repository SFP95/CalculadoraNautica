package Fichero;

import java.io.*;
import java.security.MessageDigest;
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

            /*El cliente tiene un fichero, le manda un resumen al servidor,
            este mismo hace un resumen si comprueba si coincide con el resumen recibido.
             */
            //seleccionamos el fichero .txt que queremos mandar al servidor
            FileOutputStream fichero= new FileOutputStream("src/main/ficheroCliente.txt");

            //lo trasformamos en un objeto output
            ObjectOutputStream fichOut= new ObjectOutputStream(fichero);

            //creamos el objeto de entrada del servidor
            input_server= new DataInputStream(skCliente.getInputStream());

            //mandamos el objeto output al servidor
            System.out.println("\t-- FicheroCliente.txt enviado al Servidor --"); //no lee el fichero
            output_Server.writeUTF(fichOut.toString());

            //creamos un resumen del fichero par aluego contrartar con el del servidor
            MessageDigest md= MessageDigest.getInstance("SHA");
            byte texto[]=fichero.toString().getBytes(); //resumimos
            md.update(texto); //actualizamos
            byte res[]=md.digest(); //calculamos el resumen
            //escribimos
            fichOut.writeObject(texto);
            fichOut.writeObject(res);
            //cerramos
            fichOut.close();
            fichero.close();

            /*----- Lo que recibe el cliente de parte de servidor -----*/
            System.out.println("----------\n"+" - Recibimos el fichero resumido por el servidor:");

            input_server= new DataInputStream(skCliente.getInputStream());
            String resumen = input_server.readUTF();

             if (fichOut.toString() == resumen){
                 System.out.println("\t¡¡ EL RESUMEN COHINCIDE !!\n");
                 System.out.println("RESUMEN:\n"+resumen);
             }


        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
