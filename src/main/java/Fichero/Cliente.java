package Fichero;

import java.io.*;
import java.security.MessageDigest;
import java.util.Scanner;

public class Cliente extends Conexion {
    String rutaFich="/home/alumno/IdeaProjects/CalculadoraNautica/src/main/java/Fichero/ficheroCliente.txt";

    //public Scanner scan=new Scanner(System.in);

    public Cliente() throws IOException {
        super("Cliente");
    }
    public  void  initCLiente(){
        try {
            // salida de servidor y recogida de datos
            output_Server = new DataOutputStream(skCliente.getOutputStream());
            input_server = new DataInputStream(skCliente.getInputStream());

            //EJERCICIO:

            /*El cliente tiene un fichero, le manda un resumen al servidor,
            este mismo hace un resumen si comprueba si coincide con el resumen recibido.
             */

            //Elegimos el fichero que queremos enviar y lo metemos en una variable
            File fichero = new File(rutaFich);

            //Obtenemos el tamaño del fichero
            int tamFichero= (int) fichero.length();

            //leemos el nombre y tmaño del fichero por pantalla
            System.out.println("Enviamos un con nombre :"+ fichero.getName());
            System.out.println("Con un tamaño de :"+ tamFichero+"\n-----------------------");

            FileOutputStream fichOut = new FileOutputStream(fichero);
            ObjectOutputStream obFichOut = new ObjectOutputStream(skCliente.getOutputStream());
            Object mensajeFich = fichOut.toString();
            String leeFich= (String) mensajeFich;
            System.out.println("Leo fichero:\n"+leeFich);

            //mandamos a servidor:
            output_Server.writeUTF(leeFich);
            System.out.println("Fichero enviado");

            //Cerramos

            skCliente.close();


            /*----- Lo que recibe el cliente de parte de servidor -----*/
            //System.out.println("----------\n"+" - Recibimos el fichero resumido por el servidor:");

           /* input_server= new DataInputStream(skCliente.getInputStream());
            String resumen = input_server.readUTF();*7

             /*if (fichOut.toString().equals(resumen)){
                 System.out.println("\t¡¡ EL RESUMEN COHINCIDE !!\n");
             }*/
        }catch (Exception e){
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
