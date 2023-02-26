package Fichero;

import java.io.*;
import java.security.MessageDigest;

public class Servidor extends Conexion {
    public Servidor() throws IOException {
        super("Servidor");
    }
    public void initServidor(){
        try {
            skCliente =skServidor.accept(); // que queda a la espera de recibir la petición de conexión
            System.out.println("- Conexión aceptada de : "+ skCliente.getInetAddress().getHostName());
            System.out.println("------------\n");

            //EJERCICIO:  FICHERO DE CLIENTE Y HACER RESUMEN

            System.out.println("- Recibiendo el fichero del cliente");

            //recogida del fichero del cliente
            input_cliente=new DataInputStream(skCliente.getInputStream());
            ObjectInputStream fichIn= new ObjectInputStream(input_cliente);
            Object mensaje = fichIn.readObject();
            System.out.println("---- FICHERO LEIDO:\n\t"+mensaje);

            //hacemos el resumen del fichero
            //transformamos el mensaje del cliente para podrer resumirlo

           // FileOutputStream fichero= new FileOutputStream(ficheroCliente);
            MessageDigest md= MessageDigest.getInstance("SHA");
/*
            byte texto[]= fichero.toString().getBytes(); //resumimos
            md.update(texto); // actualizamos
            byte resumen[]=md.digest(); //calculamos el resument
            //escribimos
            fichIn.writeObject(texto);
            fichIn.writeObject(resumen);
            //cerramos
            fichIn.close();
            fichero.close();

            //mandamos el resumen al cliente
            output_cliente = new DataOutputStream(skCliente.getOutputStream());
            System.out.println("- Mandando resumen al servidor"); //error aqui
            output_cliente.writeUTF(fichOut.toString());*/


        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
