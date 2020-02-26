# Nacho Utils

[Español](README_ES.md)

## About

The idea of this repository, is to publish utilities (Java) 
that I have used in real projects, hopping will be "util" for you xD.

I will be updating this project from time to time.
 
## Utilities
 
### Date of Birth
This is the first utility committed to the repository.
Its function is to extract "meta data" from the Date of Birth.
 
* [See source code](src/main/mx/com/nacho/utils/DateOfBirth.java).
* [See javadoc](https://iasandoval.github.io/nacho-utils/mx/com/nacho/utils/DateOfBirth.html).

 
 **Example:**
 ```java
    DateOfBirth dob = new DateOfBirth(1983, 12, 3);
    System.out.println(dob.toString());
 ``` 
 
 **Result:**
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
 
