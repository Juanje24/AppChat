package um.tds.appChat.dominio;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

import um.tds.appChat.singletons.AppChat;


public class Peer implements Runnable {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static int puertoPropio;
    private static int puertoCliente;
    private static boolean isServerOn=false;
    private static BufferedReader in;
    private static PrintWriter out;
    private static final Semaphore semaforo = new Semaphore(0);
    private static final Semaphore semaforoCliente = new Semaphore(0);

    @Override
    public void run() {
        Thread serverThread = new Thread(Peer::startServer);
        serverThread.start();
        Thread clientThread = new Thread(Peer::startClient);
        clientThread.start();
        try {
            semaforoCliente.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            try {
                String message = in.readLine();
                if(message == null) {
                    System.out.println("El cliente se ha desconectado");
                    return;
                }
                System.out.println("Mensaje recibido del contacto con id: " + message);
                try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
                }
                AppChat.INSTANCE.recibidoMensajeSimultaneo(message);
            } catch (IOException e) {
                System.out.println("Error al leer el mensaje");
            }
        }
    }

	private static void startServer() {
          int puertoProvisional = 50000;
          while(!isServerOn) {
              try {
                  serverSocket = new ServerSocket(puertoProvisional);
                  System.out.println("Servidor iniciado en el puerto " + puertoProvisional);
                  isServerOn=true;
                  puertoPropio=puertoProvisional;
                  semaforo.release();
              } catch (IOException e) {
                    System.out.println("No se pudo iniciar el servidor en el puerto " + puertoProvisional);
                    puertoProvisional++;
              }
          }
          while(isServerOn){
                try {
                    Socket socketCliente = serverSocket.accept();
                    in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                    System.out.println("Cliente conectado al puerto " + puertoPropio + " desde el puerto " + socketCliente.getPort());
                    semaforoCliente.release();
                } catch (IOException e) {
                    System.out.println("No se pudo conectar al puerto " + puertoPropio);
                }
          }
          try {
              serverSocket.close();
          } catch (IOException e) {
              e.printStackTrace();
              System.err.println("Error al cerrar el socket");
          }
    }

    private static void startClient() {
        try {
            semaforo.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        puertoCliente = (puertoPropio == 50000) ? 50001 : 50000;
        boolean isClienteConectado=false;
        while(!isClienteConectado) {
            try {
                socket = new Socket("localhost", puertoCliente);
                System.out.println("Conectado al puerto " + puertoCliente);
                isClienteConectado=true;
                out=new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    public void sendMessage(String message) {
        try{
        	out.println(message);
		} catch (Exception e) {
			System.out.println("Error al enviar el mensaje a la otra instancia");
        }
    }
}
