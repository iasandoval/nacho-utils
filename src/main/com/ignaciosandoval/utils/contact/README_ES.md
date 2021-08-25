## Contact Utils

Read in [English](README.md#contact-utils).

### Email Address

* Versión 1.0
* Utilería para validar, dar formato, enmascarar, verificar y extraer metadatos de una Dirección de Correo Electrónico.
* [Ver código fuente](EmailAddress.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/contact/EmailAddress.html).


**Ejemplo**
```java
    ContactMethod email = new EmailAddress("NACHO@nacho.com");

    System.out.println(email);
```

**Resultado:**
```json lines
    EmailAddress: {
        value: "NACHO@nacho.com",
        formatted: "nacho@nacho.com",
        userName: "nacho",
        domain: "nacho.com",
        masked: "na***@nacho.com",
        verified: false,
        isEmpty: false,
        isValid: true,
        type: "EmailAddress"
    }
```

### Phone Number

* Versión 1.0
* Utilería para validar, dar formato, enmascarar, verificar y extraer metadatos de un Número de Teléfono.
* [Ver código fuente](PhoneNumber.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/contact/PhoneNumber.html).


**Ejemplo**
```java
    ContactMethod phone = new PhoneNumber("AX(123) 456  78-90 ALKSDJ");

    System.out.println(phone);
```

**Resultado:**
```json lines
    PhoneNumber: {
        value: "1234567890",
        formatted: "(123) 456-7890",
        areaCode: "123",
        masked: "(***) ***-7890",
        verified: false,
        isEmpty: false,
        isValid: true,
        type: "PhoneNumber"
    }
```
