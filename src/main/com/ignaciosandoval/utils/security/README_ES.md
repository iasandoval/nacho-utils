## Security Utils

Read in [English](README.md#security-utils).

### EncryptUtil

* Versión 2.0 **Actualizado al 2021-05-05:**
* La Clase y la implementación has sido actualizada para permitir el Cifrado de texto y archivos.
* Ahora utiliza la transformación "Advanced Encryption Standard using the Galois/Counter Mode and No Padding" (AES/GCM/NoPadding).

* [Ver código fuente](EncryptUtil.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/security/EncryptUtil.html).

**Ejemplo 1: Cifrado de texto**
```java
    // Cifra el texto "nacho" con la llave "secret". 
    String encrypted = new EncryptUtil("secret").encrypt("nacho").toString();
    // Desencripta el texto con la llave "secret"
    String decrypted = new EncryptUtil("secret").decrypt(encrypted).toString();

    System.out.println("Encrypted Text: " + encrypted);
    System.out.println("Decrypted Text: " + decrypted);
```

**Ejemplo 1 - Resultado:**

**Nota:** El resultado puede variar, ya que se utiliza "sal" al realizar el cifrado.
```shell
    Encrypted Text: GDa5uPI/Sx6cH52xciJzqRg2ubjyP0senB+dsXIic6k9HVHPipG7GhzyuSS2sF5v0XOnOqY=
    Decrypted Text: nacho
```

**Ejemplo 2: Cifrar archivos**

**Nota:** Puedes pasar el mismo archivo para cifrarlo, pero este ejemplo muestra como un archivo es cifrado, manteniendo
el archivo original y creando dos archivos nuevos: encriptado y desencriptado respectivamente.

```java
    // Mantener el archivo original
    File file = new File ("files/chicken.pdf");
    
    // Copia el archivo a Cifrar, agregando "_encrypted" al nombre del archivo.
    File fileEncrypted = new File ("files/chicken_encrypted.pdf");
    Files.copy(file.toPath(), fileEncrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
    // Cifra y escribe el archivo. Genera el archivo "files/chicken_encrypted.pdf". 
    new EncryptUtil("secret").encrypt(fileEncrypted).writeFile();

    // Copia el archivo a desencriptar, agregando "_decrypted" al nombre del archivo.
    File fileDecrypted = new File ("files/chicken_decrypted.pdf");
    Files.copy(fileEncrypted.toPath(), fileDecrypted.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
    // Desencripta y escribe el archivo. Genera el archivo "files/chicken_decrypted.pdf".
    new EncryptUtil("secret").decrypt(fileDecrypted).writeFile();
```

**Ejemplo 2 - Resultado:**

* El código generará dos archivos, ```chicken_encrypted.pdf``` y ```chicken_decrypted.pdf```.

**Ejemplo 3: Cifrar un archivo en Base64**

```java
    // Archivo a cifrar
    File file = new File ("files/file.txt");
    
    // Cifra el archivo y regresa su representación en Base64
    String encrypted = new EncryptUtil("secret").encrypt(file).toString();
    
    // Desencripta el archivo en Base64
    String decrypted = new EncryptUtil("secret").decrypt(encrypted).toString();
    
    System.out.println("Encrypted File: " + encrypted);
    System.out.println("Decrypted File: " + decrypted);
```

**Ejemplo 3 - Resultado:**

**Nota:** El resultado puede variar, ya que se utiliza "sal" al realizar el cifrado.

```shell
Encrypted File: rDRwr8JVSUd/j0KK08xHcqw0cK/CVUlHf49CitPMR3IMFCJ7iUMfGlz9NPoAzNZnmwFtK16sim8AqlaXMT2jHP
                uunS8BVomNlRaGQP0c+D4PLAhfaDsHwdlbmjR9KoCIxND2kxCUySdoYx5+CnDDwactDvLU9RjVu9FLf/vdhBuM
                L4kR5cAhp1xq9MG9HfCDIX9E0oxAM27MFrzUlokeztTSDZ21O5AOy9lhaz8yq0OWF/FQCNuQj49hdK2XuBpTrE
                y07zKRgx+I83XuO2h53g==
Decrypted File: Pepe Pecas pica papas con un pico, con un pico pica papas Pepe Pecas.Si Pepe Pecas pica 
                papas con un pico, ¿dónde está el pico con que Pepe Pecas pica papas?
```

**Ejemplo 4: Obtener los bytes**

Si lo prefieres, puedes obtener los bytes ejecutando el método ```getBytes()```.
Es de utilidad, si la intención es la de realizar algo personalizado con los datos.

```java
    // Cifra el texto "nacho" con la llave "secret"
    byte[] encrypted = new EncryptUtil("secret").encrypt("nacho").getBytes();

            // Desencripta el texto con la llave "secret"
            byte[] decrypted = new EncryptUtil("secret").decrypt(encrypted).getBytes();

            System.out.println("Encrypted Data: " + encrypted);
            System.out.println("Decrypted Data: " + decrypted);
```

**Ejemplo 4 - Resultado:**

**Nota:** El resultado puede variar, ya que se muestra la referencia en memoria de los datos.
```shell
    Encrypted Data: [B@28ffffe0
    Decrypted Data: [B@754b13a8
```