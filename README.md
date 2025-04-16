# 🔐 Enkriptimi dhe dekriptimi i tekstit dhe fajllave permes DES ne Java

Ky projekt demonstron mënyrën sesi mund të enkriptohet dhe dekriptohet një file tekstual duke përdorur algoritmin **DES (Data Encryption Standard)** në gjuhën programuese **Java**.

## 📁 Përmbajtja e Projektit

- `FileEncryption.java` – Kryen:
  - Gjenerimin e çelësit sekret DES.
  - Ruajtjen e çelësit në format hexadecimal në një file (`secret.key`).
  - Leximin e përmbajtjes nga një file tekst (`PlainText.txt`).
  - Enkriptimin e përmbajtjes.
  - Ruajtjen e të dhënave të enkriptuara në format hexadecimal në një file të ri (`encrypted_hex.txt`).

- `FileDecryption.java` – Kryen:
  - Leximin e çelësit nga file-i `secret.key`.
  - Leximin e të dhënave të enkriptuara nga `encrypted_hex.txt`.
  - Dekriptimin e përmbajtjes.
  - Ruajtjen e tekstit të dekriptuar në një file (`decrypted.txt`).

## ⚙️ Si funksionon

1. **Gjenerimi i Çelësit**
   - Përdoret `KeyGenerator` për të krijuar një çelës 56-bit për DES.
   - Ky çelës ruhet në një file (`secret.key`) në format hexadecimal.

2. **Enkriptimi**
   - Përdoret `Cipher` me algoritmin `DES/ECB/NoPadding`.
   - File-i i lexuar (`PlainText.txt`) enkriptohet dhe ruhet si tekst hexadecimal në `encrypted_hex.txt`.

3. **Dekriptimi**
   - Çelësi lexohet nga file-i dhe përdoret për të inicializuar `Cipher` në mënyrën DECRYPT_MODE.
   - File-i `encrypted_hex.txt` konvertohet në byte array dhe dekriptohet.
   - Rezultati ruhet si `decrypted.txt`.

## 📝 Kërkesa

- Java 8 ose më e re.
- File-i i inputit `PlainText.txt` duhet të ekzistojë përpara ekzekutimit të `FileEncryption.java`.
- Gjatësia e përmbajtjes duhet të jetë shumëfish i 8 bajtëve për shkak të `NoPadding`. Në të kundërten, shtoni `PKCS5Padding` ose bëni padding manual.

## 🚀 Si ta përdorësh

1. Sigurohu që `PlainText.txt` ekziston në root-in e projektit.
2. Ekzekuto `FileEncryption.java` për të enkriptuar përmbajtjen.
3. Ekzekuto `FileDecryption.java` për të dekriptuar dhe krijuar `decrypted.txt`.

## 🛡️ Vërrejtje sigurie

- DES është i vjetruar dhe **jo i rekomanduar për përdorim në aplikacione reale që kërkojnë siguri të lartë**. Ai është përdorur këtu vetëm për qëllime mësimore.
- Për siguri më të lartë, konsideroni përdorimin e AES (Advanced Encryption Standard).
