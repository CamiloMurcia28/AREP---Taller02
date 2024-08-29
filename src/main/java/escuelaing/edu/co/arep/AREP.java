package escuelaing.edu.co.arep;

/**
 * @author Camilo Murcia 
 */
import escuelaing.edu.co.arep.utils.MedAppointmentService;
import escuelaing.edu.co.arep.services.RESTService;
import escuelaing.edu.co.arep.services.AddMedAppointmentService;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
 
public class AREP {
    private static AREP instance;
    private static final int PORT = 35000;
    public static String WEB_ROOT;
    private boolean running = false;

    public static AREP getInstance(){
        if(instance == null){
            instance = new AREP();
        }
        return instance;
    }

    public void start() throws IOException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(PORT);

        running = true;
        while (running) {
            Socket clientSocket = serverSocket.accept();
            threadPool.submit(new ClientHandler(clientSocket));
        }
    }

    public boolean isRunning(){
        return running;
    }

    public void setStaticFileLocation(String webRoot){
        WEB_ROOT = webRoot;
    }
}
 
