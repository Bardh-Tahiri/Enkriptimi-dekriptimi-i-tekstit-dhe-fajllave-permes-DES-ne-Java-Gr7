import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.util.Formatter;
public class FileDecryption {
    public static void main(String[] args) {
        try{
            // 1. Gjenerimi i Celesit ne DES
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(56); // DES-i perdor celsa 56-bit
            SecretKey secretKey = keyGen.generateKey();

            // 2. Ruajtja e Celsit Heksadecimal ne nje file (Opcionale)
            byte[] keyBytes = secretKey.getEncoded();
            StringBuilder keyHex = new StringBuilder();
            Formatter keyFormatter = new Formatter(keyHex);
            for (byte b : keyBytes) {
                keyFormatter.format("%02X", b);
            }
            keyFormatter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
