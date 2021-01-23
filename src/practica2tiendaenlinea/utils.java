package practica2tiendaenlinea;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class utils {

    public static int buf_size = 1024;

    public static void sendObject(Object obj, DataOutputStream output) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(output);
        oos.writeObject(obj);
        oos.flush();
    }

    public static Object receiveObject(DataInputStream input) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(input);
        Object obj = ois.readObject();
        return obj;
    }

    public static byte[] fileToBytes(String path) throws Exception {
        long size = new File(path).length();
        long received = 0;
        int read = 0;
        byte buffer[] = new byte[buf_size];
        FileInputStream f = new FileInputStream(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (received < size) {
            read = f.read(buffer);
            received += read;
            baos.write(buffer, 0, read);
        }
        byte b[] = baos.toByteArray();
        baos.close();
        f.close();
        return b;
    }

    public static BufferedImage bytesToImage(byte[] b) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        BufferedImage image = ImageIO.read(bais);
        return image;
    }
}
