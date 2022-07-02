# Procedura pentru generarea unui installer al jocului pentru Windows 

# Generare .jar
-  command prompt la *StraferLiberator\strafer-liberator-master*
- scrie **gradlew desktop:dist**
- **desktop-1.0.jar** se gaseste in *StraferLiberator\strafer-liberator-master\desktop\build\libs*
- copiezi **desktop-1.0.jar** in *StraferLiberator\Deploy\Windows\resurse*
----------
# Generare .exe

- deschizi launch4j
- "Öpen configuration"

- slectezi *StraferLiberator\Deploy\Windows\configurari\launch4jconfig.xml*

- **BASIC**
   - **Output file:** *StraferLiberator\Deploy\Windows\resurse\StraferLiberator64.exe*
   - **Jar:**         *desktop-1.0.jar*   (path relativ la .exe)
   - **Icon:** *StraferLiberator\Deploy\Windows\resurse\icons\icon.ico*  (path full)
   - bifat "don't wrap jar, launch only", "process priortiy Normal", "Stay alive"
- **JRE**
    - **Bundled JRE paths:** *jre* (path relativ la .exe)
    - **Min. JRE version:** *11.0.15*

-"save configuration"
-"Build Wrapper"
**StraferLiberator64.exe** se gaseste in *StraferLiberator\Deploy\Windows\resurse*

- **copiezi:**
        - *StraferLiberator\strafer-liberator-master\assets\dialogs*
        - *StraferLiberator\strafer-liberator-master\assets\fonts*
        - *StraferLiberator\strafer-liberator-master\assets\icons*
        - *StraferLiberator\strafer-liberator-master\assets\images*
        - *StraferLiberator\strafer-liberator-master\assets\maps*
        - *StraferLiberator\strafer-liberator-master\assets\saves*
        - *StraferLiberator\strafer-liberator-master\assets\sounds*
- **in** 
*StraferLiberator\Deploy\Windows\resurse*
(trebuie ca folderele din assets sa fie relative la exe)

----------

# Creat Installer
- deschizi install4j
- "Open project"
- selectezi *StraferLiberator\Deploy\configuratii deploy\installer.install4j*
- **Media**
    - **New media file**
        -"Installer"
        
                    
- **General Settings**
    - **Application info**
        -Self explanatory
    - **JRE Bundles**
        -**JDK release:** "AdoptOpenJDK", "11/jdk-11.0.15+10"
- **Files**
    - **Define Distribution tree**
        -apesi + 
        -selectezi *StraferLiberator\Deploy\Windows\resurse*
- **Media**
    - **Windows** (ala creat mai sus)
        - **5.Bundled JRE**
            - **bifezi generate JRE bundle**
            -apesi +
            -"Default JDK modules"
            -"all modules
- **Build**
    - **Start Build**
 
 
 Pentru instalare trimiti tot folderul *installer* si click pe executabil ca sa dai install pe un pc cu windows