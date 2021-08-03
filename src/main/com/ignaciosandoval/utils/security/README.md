## Security Utils

Leer en [Español](README_ES.md#security-utils).

### EncryptUtil

* Version 2.0 **Updated as of 2021-05-05:**
* Class and implementation refactor to allow Text or Files encryption/decryption.
* Now uses Advanced Encryption Standard using the Galois/Counter Mode and No Padding (AES/GCM/NoPadding) transformation.

* [See Source Code](EncryptUtil.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/security/EncryptUtil.html).

**Example 1: Encrypt/Decrypt Text**
```java
    // Encrypt text "nacho" using key "secret". 
    String encrypted = new EncryptUtil("secret").encrypt("nacho").toString();
    // Decrypt encrypted text using key "secret"
    String decrypted = new EncryptUtil("secret").decrypt(encrypted).toString();

    System.out.println("Encrypted Text: " + encrypted);
    System.out.println("Decrypted Text: " + decrypted);
```

**Example 1 - Result:**

**Note:** The result may vary, as the method uses "salt" while performing the encryption.
```shell
    Encrypted Text: GDa5uPI/Sx6cH52xciJzqRg2ubjyP0senB+dsXIic6k9HVHPipG7GhzyuSS2sF5v0XOnOqY=
    Decrypted Text: nacho
```

**Example 2: Encrypt/Decrypt Files**

**Note:** You can pass the same file to cipher it, but this example is to show how
a File is Encrypted and Decrypted by keeping the original file and by creating their corresponding encrypted
and decrypted files.
```java
    // Keep original file
    File file = new File ("files/chicken.pdf");
    
    // Copy file to Encrypt, adding "_encrypted" to the file name.
    File fileEncrypted = new File ("files/chicken_encrypted.pdf");
    Files.copy(file.toPath(), fileEncrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
    // Encrypt and Write File. This will create file "files/chicken_encrypted.pdf". 
    new EncryptUtil("secret").encrypt(fileEncrypted).writeFile();

    // Copy File to Decrypt, adding "_decrypted" to the file name.
    File fileDecrypted = new File ("files/chicken_decrypted.pdf");
    Files.copy(fileEncrypted.toPath(), fileDecrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
    // Decrypt File and Write File. This will create file "files/chicken_decrypted.pdf".
    new EncryptUtil("secret").decrypt(fileDecrypted).writeFile();
```

**Example 2 - Result:**

* The code will generate 2 files, ```chicken_encrypted.pdf``` and ```chicken_decrypted.pdf```.

**Example 3: Encrypt/Decrypt File to Base64**

```java
    // Text file to encrypt
    File file = new File ("files/file.txt");
    
    // Encrypt file and return its Base64 representation
    String encrypted = new EncryptUtil("secret").encrypt(file).toString();
    
    // Decrypt Base64 File
    String decrypted = new EncryptUtil("secret").decrypt(encrypted).toString();
    
    System.out.println("Encrypted File: " + encrypted);
    System.out.println("Decrypted File: " + decrypted);
```

**Example 3 - Result:**

**Note:** The result may vary, as the method uses "salt" while performing the encryption.

```shell
Encrypted File: rDRwr8JVSUd/j0KK08xHcqw0cK/CVUlHf49CitPMR3IMFCJ7iUMfGlz9NPoAzNZnmwFtK16sim8AqlaXMT2jHP
                uunS8BVomNlRaGQP0c+D4PLAhfaDsHwdlbmjR9KoCIxND2kxCUySdoYx5+CnDDwactDvLU9RjVu9FLf/vdhBuM
                L4kR5cAhp1xq9MG9HfCDIX9E0oxAM27MFrzUlokeztTSDZ21O5AOy9lhaz8yq0OWF/FQCNuQj49hdK2XuBpTrE
                y07zKRgx+I83XuO2h53g==
Decrypted File: Pepe Pecas pica papas con un pico, con un pico pica papas Pepe Pecas.Si Pepe Pecas pica 
                papas con un pico, ¿dónde está el pico con que Pepe Pecas pica papas?
```

**Example 4: Get data bytes**

If you prefer, you can get the encrypted/decrypted data calling the method ```getBytes()```.
This is helpful, if the intention is to perform some custom treatment to the data.

```java
    // Encrypt text "nacho" using key "secret"
    byte[] encrypted = new EncryptUtil("secret").encrypt("nacho").getBytes();

            // Decrypt encrypted text using key "secret"
            byte[] decrypted = new EncryptUtil("secret").decrypt(encrypted).getBytes();

            System.out.println("Encrypted Data: " + encrypted);
            System.out.println("Decrypted Data: " + decrypted);
```

**Example 4 - Result:**

**Note:** The result may vary, as this is printing the memory reference of the data.
```shell
    Encrypted Data: [B@28ffffe0
    Decrypted Data: [B@754b13a8
```