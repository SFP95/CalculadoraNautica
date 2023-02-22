package AreaTriangulo;

import java.io.*;

public class Servidor extends Conexion {

    public Servidor() throws IOException {
        super("Servidor");
    }
    public void initServidor(){
        try {
            // se queda a la espera de recibir la petición de conexión
            skCliente =skServidor.accept();

            //mensase de comprovación de conexción con el  servidor, mostrando la dirección IP y el host
            System.out.println("\t- Conexión aceptada de : "+ skCliente.getInetAddress().getHostName()+"\n\t------------\n");

            //EJERCICIO:

            //pedimos al cliente que nos de el primer dato en este caso : la base del triángulo

            //recogida de datos introducidos por cliente y mostrado en pantalla servidor
            while (skCliente.isConnected()){
                //para base:
                output_cliente= new DataOutputStream(skCliente.getOutputStream());
                output_cliente.writeUTF("Dime la base del triangulo");

                intput_cliente=new DataInputStream(skCliente.getInputStream());
                int base = intput_cliente.readInt();
                System.out.println("Cliente me ha dado la base "+ base);

                System.out.println("******");

                //para altura:

                output_cliente= new DataOutputStream(skCliente.getOutputStream());
                output_cliente.writeUTF("Dime la altura del triangulo:");

                intput_cliente=new DataInputStream(skCliente.getInputStream());
                int altura = intput_cliente.readInt();
                System.out.println("Cliente me ha dado la altura "+ altura);


                System.out.println("-------\n");
                System.out.println("Cliente me ha dicho que calcule: "+base+" y "+altura);

                //operacion de la base por al altura
                System.out.println("******");

                int operacion= base*altura;

                System.out.println("El resultado es: "+ operacion);

                //mandamos la respuetsa a cliente

                output_cliente.writeUTF("El aŕea del Triangulo es: "+ operacion);

            }

        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
