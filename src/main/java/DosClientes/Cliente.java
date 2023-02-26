package DosClientes;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente extends Conexion {
    private String userName;

    public Cliente() throws IOException {
        super("Cliente");
    }
    public  void  initCLiente(){
        try {
            //EJERCICIO 2 clientes

            //intaciamos lo shilos ede escritura y lesctura
            new ReadThread(skCliente,this).start();
            new WriteThread(skCliente,this).start();
        } catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en Ciente: " + e.getMessage());
        }
    }

    //Getter and Setters de UserName
    void setUserName(String userName){
        this.userName = userName;
    }

    String getUserName(){
        return this.userName;
    }
}

//Clase hilo del tipo lectira que lee lo mensaje del servidor y lo mustras en pantalla
class ReadThread extends Thread{
    private DataInputStream dataInput;
    private ObjectInputStream objInput;
    private Socket socket;
    private Cliente mainCliente;

    //constructor de tipo lectura
    public ReadThread (Socket socket, Cliente mainCliente){
        this.socket = socket;
        this.mainCliente = mainCliente;
        try {
            dataInput = new DataInputStream(this.socket.getInputStream());
            objInput = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            System.err.println("--> Error en 'ReadThread' de Cliente");
        }
    }

    @Override
    public void run () {
        while (true){
            try {
                //se escribe la respuesta
                String respuesta = (String) this.objInput.readObject();
                System.out.println("\n"+respuesta);
                //monstramos el nombre del usuario
                if (mainCliente.getUserName() != null){
                    System.out.println("["+mainCliente.getUserName()+"]: ");
                }
            } catch (IOException e) {
            System.err.println("-- DESCONEXIÓN --");
            // para que no entre en bucle, salimos del while...
            break;
            } catch (ClassNotFoundException e) {
            System.out.println("--> Error al leer el mensaje del servidor");
        }
    }
        //aseguramos de cerrar los recursos
        try {
            if (this.objInput != null)objInput.close();
            if (this.socket != null) socket.close();
        } catch (IOException e) {
            System.err.println("--> Error al cerrar los recursos en el hilo 'ReadThread' en Cliente");
        }
    }
}

//Clase hilos del tipo escritura que recoge lo escrito y lo manda al servidor
class WriteThread extends Thread{
    private Socket socket;
    private Cliente mainCliente;
    private DataOutputStream dataOut;
    private ObjectOutputStream objOut;
    private Scanner scan;

    //Constructor del la clase
    public WriteThread(Socket socket, Cliente mainCliente){
        this.socket = socket;
        this.mainCliente = mainCliente;
        scan = new Scanner(System.in);

        try {
            this.dataOut = new DataOutputStream(this.socket.getOutputStream());
            this.objOut = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("--> Error en constructor de 'WriteTrhead'");
        }
    }

    @Override
    public void run () {
        //pedimos al usuario que nos de el nombre para que el
        // servidor lo use para reconocerle
        System.out.println("Introduce tu nombre de usuario: ");
        String userName = scan.nextLine();
        System.out.println(userName);

        try {
            this.objOut.writeUTF(userName);
        }catch (IOException e1) {
            System.err.println("--> Error 'WriteThread' al escribir el 'userName'");
        }

        String mensaje;

        do {
            mensaje = scan.nextLine();

            try {
                this.objOut.writeObject(mensaje);
            }catch (IOException e) {
                System.err.println("--> Error en 'WriteThread IO e'");
            }
        }while (!mensaje.equals("exit"));

        //al salir del suble cerramos los recursos
        try {
            this.socket.close();
            this.scan.close();
            this.dataOut.close();
        }catch (IOException e){
            System.err.println("--> Error al cerrar los objetos");
        }
    }
}
