import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HexFormat;

public class FileDecryption {
    public static void main(String[] args) {
        try{
            // 1. Leximi i hex key nga secret.key
            BufferedReader keyReader = new BufferedReader(new FileReader("secret.key"));
            String keyHex = keyReader.readLine().trim();
            keyReader.close();
            byte[] keyBytes = HexFormat.of().parseHex(keyHex);
            SecretKey secretKey = new SecretKeySpec(keyBytes, "DES");

            // 2. Inicializimi i cipher per dekriptim
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // 3. Leximi i hex string nga file i enkriptuar
            BufferedReader reader = new BufferedReader(new FileReader("encrypted_hex.txt"));
            StringBuilder hexData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                hexData.append(line.trim());
            }
            reader.close();

            // 4. Konvertimi i hex string ne byte array
            byte[] encryptedBytes = HexFormat.of().parseHex(hexData.toString());

            // 5. Dekriptimi i te dhenave
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // 6. Shkrimi i te dhenave te dekriptuara ne file
            FileOutputStream fos = new FileOutputStream("decrypted.txt");
            fos.write(decryptedBytes);
            fos.close();

            System.out.println("File successfully decrypted to: decrypted.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
