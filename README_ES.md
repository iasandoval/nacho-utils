# Nacho Utils

[English](README.md)

## Acerca de

La idea de este repositorio, es la de publicar utilerías (Java) 
que he aplicado en proyectos reales, esperando que les 
puedan ser de "utilidad" xD.

Iré actualizando este proyecto poco a poco en mi tiempo libre.

## Utilerías

### Date of Birth
Esta es la primera utilería agregada al repositorio. 
Su función es la extraer "meta datos" de la fecha de nacimiento.

* [Ver código fuente](src/main/mx/com/nacho/utils/DateOfBirth.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/mx/com/nacho/utils/DateOfBirth.html).

**Ejemplo:**
```java
    DateOfBirth dob = new DateOfBirth(1983, 12, 3);
    System.out.println(dob.toString());
``` 

**Resultando en:**
```
    DateOfBirth{
        dob=1983-12-03
        age=36
        day=3
        month=12
        year=1983
        monthText=December
        dayOfWeek=Saturday
        isWeekend=true
        isNewborn=false
        isChild=false
        isTeen=false
        isAdult=true
        isSenior=false
        category=ADULT
        ageRange=18+
        date=Sat Dec 03 00:00:00 EST 1983
        timestamp=1983-12-03 00:00:00.0
    }
```

### Social Security number
Segunda utilería agregada al repositorio. 
Su función es la validar y dar formato al número de seguro social de los 
Estados Unidos ("Social Security Number") o SSN.

El método para validar el SSN utiliza la expresion regular "Over-The-Top Validation" publicada en el artículo
["Validating Social Security Numbers through Regular Expressions"](http://rion.io/2013/09/10/validating-social-security-numbers-through-regular-expressions-2/) de Rion Williams.

* [Ver código fuente](src/main/mx/com/nacho/utils/SocialSecurityNumber.java).
* [Ver javadoc](https://iasandoval.github.io/nacho-utils/mx/com/nacho/utils/SocialSecurityNumber.html).


**Ejemplo:**
```java
    SocialSecurityNumber ssn = new SocialSecurityNumber(52063811);
    System.out.println(ssn.toString());
```
**Resultando en:**
```
    SocialSecurityNumber{
        ssn=52063811
        isValid=true
        rawSSN=52063811
        stringSSN=052063811
        formattedSSN=052-06-3811
        maskedSSN=XXX-XX-3811
    }
```
