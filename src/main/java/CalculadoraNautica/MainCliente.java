package CalculadoraNautica;

import java.io.IOException;

public class MainCliente {
    public static void main(String[] args) throws IOException {
        Cliente cliente= new Cliente(); //creamos al servidor
        System.out.println("\n\t-----------"+
                "\n\t CLIENTE INICIADO\n\t esperando la aceptacion de petición al servidor...."+
                "\n\t-----------");
        //mensaje por pantalla par aincicar que hemos ejecutado el sevidor y espera la resuesta de aceptaicon del servidor
        cliente.initCLiente(); //iniciamos el cliente

    }
}
