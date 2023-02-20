package DosClientes;

import java.io.IOException;

public class MainServidor {
    public static void main(String[] args) throws IOException {
        Servidor servidor= new Servidor(); //creamos al servidor
        System.out.println("\n\t-----------"+
                "\n\t SERVIDOR INICIADO\n\t a la espera de petición de entrada...."+
                "\n\t-----------");
        //mensaje por pantalla par aincicar que hemos ejecutado el sevidor y se queda a la escucha
        servidor.initServidor(); //iniciamos el servidor dejándolor en escucha
    }
}
