package Fichero;

import java.io.*;
import java.security.MessageDigest;
import java.util.Locale;

public class Servidor extends Conexion {
    byte[] recibiendoFichero;
    int tamFichero;
    String rutaFich="/home/alumno/IdeaProjects/CalculadoraNautica/src/main/java/Fichero/ficheroCliente.txt";

    public Servidor() throws IOException {
        super("Servidor");
    }
    public void initServidor(){
        try {
            skCliente =skServidor.accept(); // que queda a la espera de recibir la petición de conexión
            System.out.println("- Conexión aceptada de : "+ skCliente.getInetAddress().getHostName());
            System.out.println("------------\n");

            //EJERCICIO:  FICHERO DE CLIENTE Y HACER RESUMEN
            output_cliente =new DataOutputStream(skCliente.getOutputStream());
            input_cliente =new DataInputStream(skCliente.getInputStream());
            FileInputStream fichero = new FileInputStream(rutaFich);

            //leemos mensaje entrada cliente
            ObjectInputStream fichInput = new ObjectInputStream(skCliente.getInputStream());
            Object mensajeFichero = fichInput.readObject();

            //1a Lectua fichero:
            String datos = (String) mensajeFichero;
            System.out.println("Datos: " + datos);

            //Resumen fichero:
            mensajeFichero = fichInput.readObject();
            byte resumenOriginal[] = (byte[]) mensajeFichero;

            //comparamos el resumen con el del cliente:
            if (fichInput.equals(resumenOriginal)){
                System.out.println("LOS RESUMENES COINCIDEN");
                output_cliente.writeUTF("LOS RESUMENES COINCIDEN");
            }

            /*
            //recibimos el fichero de cliente: y lo leemos en formato texto y numeríco
            input_cliente=new DataInputStream(skCliente.getInputStream());

            fichero =input_cliente.readUTF();
            tamFichero=input_cliente.readInt();

            //comprovacion de lelvada del fichero
            System.out.println("ARCHIVO RECIVIDO: \n"+ fichero);

            //ceamos flujo de salida para indicar donde guardamos el fichero
            FileOutputStream guardoFichero = new FileOutputStream("/home/alumno/IdeaProjects/CalculadoraNautica/src/main/java/Fichero/ficheroGuardadoServidor.txt"+fichero);
            BufferedOutputStream bos = new BufferedOutputStream(guardoFichero);
            BufferedInputStream bis = new BufferedInputStream(skCliente.getInputStream());

            //creamos array de bytes para lees los datos del ficher0
            byte[] datosFichero = new byte[tamFichero];

            //obtenermos el archivo mediate lectura de bytes enviados
            for (int i=0;i<datosFichero.length;i++){
                datosFichero[i] = (byte) bis.read();
            }

            //escribimos el archivo
            bos.write(datosFichero);

            //cerramos flujos
            bos.flush();
            bis.close();
            bos.close();


             */
            skServidor.close();

            System.out.println("-- CERRAMOS CONEXION --");

        }catch (Exception e){
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
