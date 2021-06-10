import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class Server implements Hello {
    @Override
    public String sayHello() throws RemoteException {
        return "Hello";
    }

    public static void main(String[] args) {
        try {
            //create object what will regis into rmi server
            Server server = new Server();
            //create stub listen on random port to serve when client connect to
            Hello stub = (Hello) UnicastRemoteObject.exportObject(server, 0);
            //get registry to regis stub
            Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
            //regis stub to registry, when client connect to registry and resolve the stub will connect to stub base on info of registry
            registry.bind("Hello", stub);
            System.out.println("system ready");
        } catch (Throwable throwable) {
            System.err.println("Server exception: " + throwable.toString());
            throwable.printStackTrace();
        }
    }
}
