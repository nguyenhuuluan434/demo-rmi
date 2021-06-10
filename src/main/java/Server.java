import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {
    @Override
    public String sayHello() throws RemoteException {
        return "Hello";
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(server, 3099);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);
            System.out.println("system ready");
        } catch (Throwable throwable) {
            System.err.println("Server exception: " + throwable.toString());
            throwable.printStackTrace();
        }
    }
}
