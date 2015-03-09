package ca.myseneca.rmi.server;

import java.rmi.Naming;
import java.rmi.registry.*;

public class RmiDBUtilServer {
    public RmiDBUtilServer() {
        try {
            Registry reg = LocateRegistry.createRegistry(1299);
            RmiDBUtilInterface c = new RmiDBUtilImpl();
            //reg.rebind("CalcService", c);
            Naming.rebind("rmi://localhost:1299/DBUtilService", c);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
    public static void main(String args[]) {
        new RmiDBUtilServer();
    }
}

