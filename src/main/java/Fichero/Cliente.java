package Fichero;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Cliente extends Conexion {
    // rutaFich="/home/alumno/IdeaProjects/CalculadoraNautica/src/main/java/Fichero/ficheroCliente.txt";

    //public Scanner scan=new Scanner(System.in);

    static ObjectInputStream objInpt;
    static ObjectOutputStream objOut;
    static Paquete paquete = new Paquete();
    
    public Cliente() throws IOException {
        super("Cliente");
    }
    public  void  initCLiente(){
        try {
            //Creamos el resumen 
            creamosResumen();

            //enviamos resument de cliente y paquete a servidor
            enviarResumenyPaquete(paquete);

            //recibimos resumen y paquete de servidor
            recibirResumenyPaquete();
            
            //cerramos todas las conexiones
            cerramosConexiones();
        }catch (Exception e){
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }

    private void creamosResumen () {
        MessageDigest md;
        String fihero = "Tenemos que hacerlo con un fichero";

        try {
            md = MessageDigest.getInstance("SHA");

            //texto a bytes
            byte dataBytes[] = fihero.getBytes();

            //introducumos el resto a bytes a resumir
            md.update(dataBytes);

            //calculamos el resumen
            byte resumen[] = md.digest();

            paquete.setTextoEnviar(fihero);
            paquete.setResumen(String.valueOf(resumen));
        }catch (NoSuchAlgorithmException e){
            System.out.println(" --> Error en 'creamosResumen': "+e.getMessage());
        }
    }

    private void enviarResumenyPaquete (Paquete paquete) {
        try {
            //conetamos con el servidor


            //creamos flujos de datos
            output_Server = new DataOutputStream(skCliente.getOutputStream());
            objOut = new ObjectOutputStream(skCliente.getOutputStream());

            //enviamos resumen solo
            output_Server.writeUTF(paquete.getResumen());

            //eenviamos paquete
            objOut.writeObject(paquete);
            objOut.flush();
        }catch (IOException e){
            System.out.println("--> Error en 'enviarResumenyPaquete': "+e.getMessage());
        }
    }

    private void recibirResumenyPaquete () {
        try {
            objInpt = new ObjectInputStream(skCliente.getInputStream());
            input_server = new DataInputStream(skCliente.getInputStream());

            //recibimos el resumento solo
            String resumenRecibido = input_server.readUTF();

            //recibimos el paquete
            paquete = (Paquete) objInpt.readObject();

            //comprobamos resumenes
            boolean res = comprobarResumen(resumenRecibido);

            //leemos
            if (res){
                System.out.println("El servidor ha dicho:\n"+paquete.getTextoEnviar());
            }
        }catch (IOException e){
            System.out.println("-- Error IOEXCEPTION en 'recibirResumenyPaquete':"+e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println("-- Error ClassNotFoundException en 'recibirResumenyPaquete':"+e.getMessage());

        }
    }

    private boolean comprobarResumen (String resumenRecibido) {
        boolean res = false;
        String mensaje = "LOS MENSAJES NO COHINCIDEN";
        System.out.println("-------------\nComprobando su lo resumenes cohinciden...\n");
        if (resumenRecibido.equals(paquete.getResumen())){
            res=true;
            mensaje = "¡¡ LOS RESUMENES COHINCIDEN !!";
        }
        System.out.println(mensaje);
        return res;
    }

    private void cerramosConexiones () {
       try {
           System.out.println("-- CERRAMOS CONEXION --");
           input_server.close();
           output_Server.close();
           objInpt.close();
           objOut.close();
           skServidor.close();
           skCliente.close();
       }catch (IOException e){
           System.out.println("-- Error IOEXCEPTION en 'cerramosConexiones':"+e.getMessage());
       }

    }
}
