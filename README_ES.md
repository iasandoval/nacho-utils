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

