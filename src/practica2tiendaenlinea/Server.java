package practica2tiendaenlinea;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

    public static DataInputStream input = null;
    public static DataOutputStream output = null;
    public static int port = 1000;

    public static ArrayList<Product> products = new ArrayList<Product>();

    private static void sendCatalog() throws Exception {
        output.writeInt(products.size());
        for (Product p : products) {
            utils.sendObject(p, output);
        }
    }

    private static void generateTicket() throws Exception {
        int n = input.readInt();
        String ticket = "Luuna\n";
        ticket += "****************************Producto\t\t\tPrecio\tCantidad\tTotal\n";
        double total = 0;
        int indices[] = new int[n];
        int cants[] = new int[n];
        for (int i = 0; i < n; ++i) {
            indices[i] = input.readInt();
            cants[i] = input.readInt();
            if (products.get(indices[i]).getStock() < cants[i]) {
                output.writeUTF("No hay en existencia: " + products.get(indices[i]).getName());
                return;
            }
        }
        for (int i = 0; i < n; ++i) {
            Product p = products.get(indices[i]);
            int cant = cants[i];
            p.addStock(-cant);
            ticket += p.getName() + "\t\t\t$" + String.format("%.2f", p.getOriginalPrice()) + "\t" + cant + "\t$" + String.format("%.2f", cant * p.getOriginalPrice()) + "\n";
        }
        ticket += "\n\t\t\t\t\t$" + String.format("%.2f", total);
        output.writeUTF(ticket);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader csvReader = new BufferedReader(new FileReader("catalog/Products.csv"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if(!"id".equals(data[0])){
                System.out.println("what" + data[0]);
                products.add(new Product(Long.parseLong(data[0]), data[2], data[1], Double.parseDouble(data[3]), Integer.parseInt(data[4])));
            }
        }
        csvReader.close();

        ServerSocket server = new ServerSocket(port);
        System.out.println("Server ready...\n");
        while (true) {
            Socket client = server.accept();
            System.out.println(
                    "New customer " + client.getInetAddress() + ":" + client.getPort() + "\n");
            input = new DataInputStream(client.getInputStream());
            output = new DataOutputStream(client.getOutputStream());
            while (true) {
                try {
                    int action = input.readInt();
                    switch(action){
                        case 0:
                            System.out.println("Enviando lista de los productos disponibles al cliente...");
                            sendCatalog();
                        case 1:
                            System.out.println("Validando la compra del cliente...");
                            generateTicket();
                        case 2:
                            input.close();
                            output.close();
                            System.out.println("Se desconectó el cliente " + client.getInetAddress() + ":" + client.getPort() + "\n");
                            client.close();
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Ocurrió un error con el cliente, desconectando...");
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
