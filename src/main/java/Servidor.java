import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Servidor extends Conexion{
    int num;
    String operador;

    public Servidor() throws IOException {
        super("Servidor");
    }
    public void initServidor(){
        try {
            skCliente =skServidor.accept(); // que queda a la espera de recibir la petición de conexión
            System.out.println("- Conexión aceptada de : "+ skCliente.getInetAddress().getHostName());
            System.out.println("------------\n");

            //EJERCICIO:
            output_cliente= new DataOutputStream(skCliente.getOutputStream());
            output_cliente.writeUTF("¿Que necesita calcular?");
            intput_cliente=new DataInputStream(skCliente.getInputStream());

            //recogida de datos introducidos por cliente y mostrado en pantalla servidor
            int radio = intput_cliente.readInt();
            System.out.println("-He leido: "+radio);

            //Calculo del aurea del circulo
           /* int res= (int) (Math.PI+radio*radio);
            System.out.println("-El area es: "+res);
            //mmensaje con el resultado mostrando tanto el radio como el resultado del calculo
            output_cliente.writeUTF("El area de  circulo con radio "+ radio + " es de : "+res+" cm.");*/

            //OPERACIONES
            operador=intput_cliente.readUTF();

            if (operador.contentEquals("-")){
                int res = num - num;
                output_cliente.writeInt(Integer.parseInt("El resultado del servidor es: "+res));
            }
            if (operador.contentEquals("+")){
                int res = num + num;
                output_cliente.writeInt(Integer.parseInt("El resultado del servidor es: "+res));
            }
            if (operador.contentEquals("*")){
                int res = num * num;
                output_cliente.writeInt(Integer.parseInt("El resultado del servidor es: "+res));
            }
            if (operador.contentEquals("/")){
                int res = num / num;
                output_cliente.writeInt(Integer.parseInt("El resultado del servidor es: "+res));
            }

        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en" + e.getMessage());
        }
    }
}
