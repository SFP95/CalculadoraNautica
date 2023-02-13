import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Cliente extends Conexion{
    public Scanner scan=new Scanner(System.in);

    public Cliente() throws IOException {
        super("Clienta");
    }
    public  void  initCLiente(){
        try {
            // salida de servidor y recogida de datos
            output_Server = new DataOutputStream(skCliente.getOutputStream());
            output_cliente = new DataOutputStream(skCliente.getOutputStream());

            //EJERCICIO AREA CIRCULO:
            input_server = new DataInputStream(skCliente.getInputStream());

            String mensajeServer= input_server.readUTF();
            System.out.println(mensajeServer);

            //System.out.println("Dime el radio del ciruculo (cm):");
            int num= scan.nextInt();
            output_cliente.writeInt(num);
            System.out.println("Cliente a dicho "+num);

            /*----- Lo que recibe el cliente de parte de servidor -----*/

            input_server= new DataInputStream(skCliente.getInputStream());

            String resuesta= input_server.readUTF();
            System.out.println(resuesta);

        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en" + e.getMessage());
        }
    }
}
