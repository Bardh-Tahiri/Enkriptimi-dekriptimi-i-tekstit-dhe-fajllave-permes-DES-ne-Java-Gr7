import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Formatter;

public class FileEncryption {
    public static void main(String args[]){
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
            // Shkrimi i Celsit ne file
            FileWriter keyWriter = new FileWriter("secret.key");
            keyWriter.write(keyHex.toString());
            keyWriter.close();
            System.out.println("Key saved to secret.key: " + keyHex);

            // 3. Inicializimi i klases Cipher per enkriptim
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // 4. Leximi i input file
            FileInputStream fis = new FileInputStream("PlainText.txt");
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] block = new byte[64];
            int bytesRead;
            while ((bytesRead = fis.read(block)) != -1) {
                buffer.write(block, 0, bytesRead);
            }
            fis.close();
            // 5. Enkriptimi i file
            byte[] encryptedBytes = cipher.doFinal(buffer.toByteArray());

            // 6. Konvertimi i file te enkriptuar nga byte ne Heksadecimal
            StringBuilder hexOutput = new StringBuilder();
            Formatter formatter = new Formatter(hexOutput);
            for (byte b : encryptedBytes) {
                formatter.format("%02X", b);
            }
            formatter.close();

            // 7. Shkrimi i file te enkriptuar ne nje file te ri
            FileWriter writer = new FileWriter("encrypted_hex.txt");
            writer.write(hexOutput.toString());
            writer.close();

            System.out.println("File encrypted and saved as: encrypted_hex.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
