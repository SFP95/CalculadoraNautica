package Fichero;

import java.io.*;
import java.security.MessageDigest;

public class Servidor extends Conexion {
    // rutaFich="/home/alumno/IdeaProjects/CalculadoraNautica/src/main/java/Fichero/ficheroCliente.txt";

    static ObjectOutputStream objOut;
    static ObjectInputStream objInput;
    private static Paquete paquete;
    public Servidor() throws IOException {
        super("Servidor");
    }
    public void initServidor(){
        try {
            //Iniciamos conexion:
            skCliente =skServidor.accept(); // que queda a la espera de recibir la petición de conexión
            System.out.println("- Conexión aceptada de : "+ skCliente.getInetAddress().getHostName());
            System.out.println("------------\n");

            //EJERCICIO:  FICHERO DE CLIENTE Y HACER RESUMEN

            //metodo para recibir los datos del cliente
            System.out.println("\n-- Recibimos los datos del Cliente");
            recibirResumenCliente();
            System.out.println("---------------------------------");

            //Modificamos el mensaje del cliente:
            System.out.println("-- Modificamos el mensaje del cliente....");
            String modificado = modificarTextoCliente();
            System.out.println("---------------------------------");

            //enviamos a cliente el resument y ladoficicación
            System.out.println("-- Enviamos al cliente el resumen y el pequete");
            enviarClienteTextos(modificado);
            System.out.println("---------------------------------");

            //cerramos todas las conexiones
            cerramosConexiones();

        }catch (Exception e){
            System.out.println("Errores encontrado en 'InitServidor': " + e.getMessage());
        }
    }
    private void recibirResumenCliente () {
        try {
            //creamos flujos de datos
            input_cliente =new DataInputStream(skCliente.getInputStream());
            objInput = new ObjectInputStream(skCliente.getInputStream());

            //recogemos el resumen que manda cliente
            String resumenCli = input_cliente.readUTF();
            System.out.println("Cliente me ha mandado:\n"+resumenCli);

            //recogemos el reusmen del paquete
            paquete = (Paquete) objInput.readObject();
            System.out.println("Recogemos el resumen del paquete:\n"+paquete.getResumen());

            //comprobamos los dos resumenes
            comprobaresumenes(paquete.getResumen(),resumenCli);

        }catch (Exception e){
            System.out.println("--> Error encontrado en 'recibirResumenCliente' en Servidor: "+e.getMessage());
        }

    }

    private void comprobaresumenes (String resumen, String resumenCli) {
        String frase=" -- Los resumenes cohinciden -- ";
        System.out.println("--------------\nComprobando que los resumenes cohinciden....\n");
        if (resumen.equals(resumenCli)) System.out.println(frase);
    }

    private String modificarTextoCliente () {
        String frase = paquete.getTextoEnviar();
        frase += "\n*** El servidor dice que este es un buen fichero ***";
        paquete.setTextoEnviar(frase);
        return frase ;
    }

    private void enviarClienteTextos (String modificado) {
        try {
            objOut = new ObjectOutputStream(skCliente.getOutputStream());
            output_cliente = new DataOutputStream(skCliente.getOutputStream());

            //generamos resumen
            generarResumen(modificado);

            //enviamos el resumen solo
            output_cliente.writeUTF(paquete.getResumen());

            //enviamos el paquete
            objOut.writeObject(paquete);
            objOut.flush();

        }catch (IOException e){
            System.out.println("--> Error en 'enviarClienteTextos' dentro de Servidor : "+e.getMessage());
        }
    }

    private void generarResumen (String fraseModificada) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA");

            //texto a bytes
            byte dataBytes[] = fraseModificada.getBytes();

            //introducimos texto en bytes a resumen
            md.update(dataBytes);

            //calculamos el resumen
            byte resumen[] = md.digest();

            paquete.setTextoEnviar(fraseModificada);
            paquete.setResumen(String.valueOf(resumen));

        }catch (Exception e){
            System.out.println("--> Error encontrado en 'generarResumen': "+e.getMessage());
        }
    }

    private void cerramosConexiones () {
        System.out.println("-- CERRAMOS CONEXION --");
        try {
            input_cliente.close();
            output_cliente.close();
            objOut.close();
            objInput.close();
          //  skServidor.close();
          //  skCliente.close();
        }catch (IOException e){
            System.out.println("--> Error en 'cerrarmosConexiones': "+e.getMessage());
        }
    }

}
