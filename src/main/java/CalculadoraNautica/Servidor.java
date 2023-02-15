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

            //mensaje de prueba para comprobar que se recibe el operando de cliente de tipo string
            System.out.println("-He leido de cliente: "+operacion+"\n-----------");

            //Metemos la recogida en una arrays para separarlo en distintas variables : num 1, op,  y num2
            String[] operacion_cliente=operacion.split("_");

            //Menaje de probación del array
            System.out.println("-IMPRESION ARRAY: "+Arrays.toString(operacion_cliente)+"\n-----------");

            //EL PROBLEMA RESIDE AQUI:
            //meterlos las posiciones deseadas en el array para poder realizar la operacion
            /*int[] op = operacion_cliente;
            int num1= Integer.parseInt(op[0]);
            System.out.println(num1);
           /* int num2= op;
            System.out.println(num2);
            /*String op= operacion_cliente[1];
            System.out.println(op);
            ;*/

            //mensaje de comprobación para saber si se han cogido bien los datos
            //System.out.println("CalculadoraNautica.Cliente me ha dicho: "+num1+op+num2);


            //OPERACIONES:

          /*  if (op.contains("-")){
                int res = num1 - num2;
                output_cliente.writeUTF("El resultado del servidor es: "+op);
            }
            if (op == "+"){
                int res = num1 + num2;
                output_cliente.writeUTF("El resultado del servidor es: "+res);
            }
            if (op == "*"){
                int res = num1 * num2;
                output_cliente.writeUTF("El resultado del servidor es: "+res);
            }
            if (op == "/"){
                int res = num1 / num2;
                output_cliente.writeUTF("El resultado del servidor es: "+res);
            }
*/
        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
