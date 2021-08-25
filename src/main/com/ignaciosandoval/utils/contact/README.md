## Contact Utils

Leer en [Español](README_ES.md#contact-utils).

### Email Address

* Version 1.0
* Utility class to Validate, Format, Mask, Verify and extract metadata from an Email Address.
* [See Source Code](EmailAddress.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/contact/EmailAddress.html).


**Example**
```java
    ContactMethod email = new EmailAddress("NACHO@nacho.com");

    System.out.println(email);
```

**Result:**
```json
    EmailAddress: {
        value: 'NACHO@nacho.com',
        formatted: 'nacho@nacho.com',
        userName: 'nacho',
        domain: 'nacho.com',
        masked: 'na***@nacho.com',
        verified: false,
        isEmpty: false,
        isValid: true,
        type: 'EmailAddress'
    }
```

### Phone Number

* Version 1.0
* Utility class to Validate, Format, Mask, Verify and extract metadata from a Phone Number.
* [See Source Code](PhoneNumber.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/com/ignaciosandoval/utils/contact/PhoneNumber.html).


**Example**
```java
    ContactMethod phone = new PhoneNumber("AX(123) 456  78-90 ALKSDJ");

    System.out.println(phone);
```

**Result:**
```json
    PhoneNumber: {
        value: '1234567890',
        formatted: '(123) 456-7890',
        areaCode: '123',
        masked: '(***) ***-7890',
        verified: false,
        isEmpty: false,
        isValid: true,
        type: 'PhoneNumber'
    }
```
