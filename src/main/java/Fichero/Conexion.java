package Fichero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexion {
    private  final int _PUERTO=6678;
    private  final String _HOST="127.0.0.1";
    protected ServerSocket skServidor;
    protected Socket skCliente;
    protected DataOutputStream output_Server, output_cliente;
    DataInputStream intput_cliente, input_server;

    public Conexion(String tipo) throws IOException {
        if (tipo.equalsIgnoreCase("servidor")){
            skCliente = new Socket();
            skServidor = new ServerSocket(_PUERTO);
        }else {
            skCliente=new Socket(_HOST,_PUERTO);
        }
    }
}
