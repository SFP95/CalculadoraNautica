package DosClientes;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Servidor extends Conexion {
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
    private static Socket skCliente;
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
                UserThread nuevoUser = new UserThread(skCliente,this);
                userThreads.add(nuevoUser);
                nuevoUser.start();
            }
        }catch (Exception e){
            //mensaje de error en caso de fallos en la conexión
            System.out.println("Errores encontrado en: " + e.getMessage());
        }
    }

    //Metodo encargado de mandar los mensajer de cada hilo instanciado al resto de usuarios
    void broadcastMessaje(String messaje, UserThread excludeUser){
        for (UserThread user : this.userThreads){
            if (user != excludeUser){
                user.sedMessage(messaje);
            }
        }
    }

    //Agrega los usuarios a la lista de usuarios
    void addUserName(String name){
        this.userNames.add(name);
    }

    //Elimina los hilos y nombre de los usuarios que se desconectan
    void removeUserThread(String userName, UserThread userThread){
        boolean removed = this.userNames.remove(userName);
        if (removed){
            userThreads.remove(userThread);
        }
    }

    //Getters de la lista de usuarioos
    Set<String> getUserNames(){
        return this.userNames;
    }

    //metodo de confirmacion de usuarios conectado al servidor
    boolean hasUsers(){
        return !this.userNames.isEmpty();
    }

}

// creación del hilo users
class UserThread extends Thread{
    private  Socket soket;                  // socket cliente
    private Servidor mainServer;                // el servidor
    private DataOutputStream dataOutput;        // objeto streams salida
    private ObjectOutputStream objetOut;          // objeto streams entrada

    //constructor de la clase
    public UserThread(Socket soCliente, Servidor mainServer){
        this.soket = soCliente;
        this.mainServer = mainServer;

        try {
            this.dataOutput = new DataOutputStream(this.soket.getOutputStream());
            this.objetOut = new ObjectOutputStream(this.soket.getOutputStream());
        }catch (IOException e){
            System.out.println("Error en el Hilo de cliente: "+e.getMessage());
        }
    }

    //imprimir mensajes de los usuarios
    void printUsers(){
        if (mainServer.hasUsers()){
            try {
                String connectUsers = new String("Usuarios conectados: "+mainServer.getUserNames());
                this.objetOut.writeUTF(connectUsers);
            }catch (IOException e){
                System.out.println(" ->> Error en 'printUser()'");
            }
        }else {
            try {
                String noUserConnected = new String("No hay users conectados");
                this.objetOut.writeUTF(noUserConnected);
            }catch (IOException e){
                System.out.println(" ->> Error en 'printUser()'");
            }
        }
    }

    //para mandar mensajes
    void sedMessage(String mensaje){
        try {
            this.objetOut.writeUTF(mensaje);
        }catch (IOException e){
            System.out.println(" ->> Error en 'sendMessage()'");
        }
    }

    //clase run del hilo donde se mosntrará la impresión
    // por pantalla : nuevos usuario, mensajes de usuario y salida de usuarios
    @Override
    public void run () {
        try {
            DataInputStream dataInput = new DataInputStream(this.soket.getInputStream());
            ObjectInputStream objInput = new ObjectInputStream(dataInput);
            printUsers();
            String userName = "";

            try {
                userName = (String) objInput.readObject();
                mainServer.addUserName(userName);
            }catch (ClassNotFoundException e){
                System.out.println("Error al leer el nombre del usuario");
            }

            String serverMessaje = "Nuevo Usuario conectado: "+userName;
            mainServer.broadcastMessaje(serverMessaje,this);

            String userMessaje;
            do {
                userMessaje = (String) objInput.readObject();
                serverMessaje = "["+userName+"]: "+userMessaje;
                mainServer.broadcastMessaje(serverMessaje,this);
            }while (!userMessaje.equals("exit"));

            mainServer.removeUserThread(userName,this);
            soket.close();

            serverMessaje=userName+" se ha ido";
            mainServer.broadcastMessaje(serverMessaje,this);

        }catch (IOException e){
            System.out.println(" ->> Error IO en 'run()' dentro de 'UserThread'");
        }catch (ClassNotFoundException e){
            System.out.println(" ->> Error al leer el 'userMessaje' en 'UserThread'");
        }
    }
}