package DosClientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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

            //EJERCICIO: dos clientes by Gonzalo
            while (true) {
                skCliente = skServidor.accept();



            }



        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }
}
