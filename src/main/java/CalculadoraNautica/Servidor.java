package CalculadoraNautica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Servidor extends Conexion{
    String operacion;

    public Servidor() throws IOException {
        super("Servidor");
    }
    public void initServidor(){

        try {
            skCliente =skServidor.accept(); // que queda a la espera de recibir la petición de conexión
            System.out.println("\t- Conexión aceptada de : "+ skCliente.getInetAddress().getHostName());
            System.out.println("\t--------------------");

            //EJERCICIO:
            //escribir mensaje al cliente para pedirle el servidor y mansarselo
            output_cliente= new DataOutputStream(skCliente.getOutputStream());
            output_cliente.writeUTF("¿Que necesita calcular?");

            //recogida de datos introducidos por cliente y mostrado en pantalla servidor
            intput_cliente=new DataInputStream(skCliente.getInputStream());
            operacion = intput_cliente.readUTF();

            // para  while : intput_cliente = respuesta.ignoreCase(salir);

            //mensaje de prueba para comprobar que se recibe el operando de cliente de tipo string
            System.out.println("-He leido de cliente: "+operacion+"\n-----------");

            //Metemos la recogida en una arrays para separarlo en distintas variables : num 1, op,  y num2
            String[] operacion_cliente=operacion.split("[-+/*]");

            //Menaje de probación del array
            System.out.println("-IMPRESION ARRAY: "+Arrays.toString(operacion_cliente)+"\n-----------");


            //meterlos las posiciones deseadas en el array para poder realizar la operacion
           //DEBE SER DOUBLE Y NO INT

            double num1= Integer.parseInt(operacion_cliente[0]);
            System.out.println("num1: "+num1);
            double num2= Integer.parseInt(operacion_cliente[1]);
            System.out.println("num2: "+num2);


            //OPERACIONES:  //tratas de hacerlo con Case: y  no ifs

            if (operacion.contains("-")){
                double res = num1 - num2;
                output_cliente.writeUTF("El resultado del servidor es: "+res);
            }
            if (operacion.contains("+")){
                double res = num1 + num2;
                output_cliente.writeUTF("El resultado del servidor es: "+res);
            }
            if (operacion.contains("*")){
                double res = num1 * num2;
                output_cliente.writeUTF("El resultado del servidor es: "+res);
            }
            if (operacion.contains("/")){
                double res = num1 / num2;
                output_cliente.writeUTF("El resultado del servidor es: "+res);
            }

        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
