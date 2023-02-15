package Fichero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Servidor extends Conexion {
    String operacion;

    public Servidor() throws IOException {
        super("Servidor");
    }
    public void initServidor(){
        try {
            skCliente =skServidor.accept(); // que queda a la espera de recibir la petición de conexión
            System.out.println("- Conexión aceptada de : "+ skCliente.getInetAddress().getHostName());
            System.out.println("------------\n");

            //EJERCICIO:  FICHERO DE CLIENTE Y HACER RESUMEN





           /* output_cliente= new DataOutputStream(skCliente.getOutputStream());
            output_cliente.writeUTF("¿Que necesita calcular?");
            intput_cliente=new DataInputStream(skCliente.getInputStream());

            //recogida de datos introducidos por cliente y mostrado en pantalla servidor
            operacion = intput_cliente.readUTF();
            //System.out.println("-He leido de cliente: "+operacion+"\n-----------");

            //Metemos la recogida en una arrays de tipo string para poder recogerlo y
            // separarlo en distintas variables : num 1, op,  y num2
            String[] operacion_cliente=operacion.split(" ");
           // System.out.println(Arrays.toString(operacion_cliente));


            int num1= Integer.parseInt(operacion_cliente[0]);
            String op= operacion_cliente[1];
            int num2= Integer.parseInt((operacion_cliente[2]));

            System.out.println("CalculadoraNautica.Cliente me ha dicho: "+num1+op+num2);
            */


        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
