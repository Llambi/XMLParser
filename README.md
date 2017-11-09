# **XMLParser-Recetario2ListaCompra**
 
    Se trata de una aplicación para, a partir de un archivo XML que contiene recetas,  
    seleccionar de estas las que se quieren realizar y que genere un archivo XML con la lista de la compra necesaria.

## Leguaje de programación:

    Se ha usado Java, con la versión de compilador 1.8 y las bibliotecas de **DOM4J-2.1**  y **Jaxen-1.1**

## Instrucciones:
    Para usar la aplicación se deberán seguir los siguientes pasos:
    
    1. Arrancar la aplicación haciendo doble click sobre el archivo **Recetario2ListaCompra.jar**  
    localizado en la carpeta **Resources.
    2. Haciendo click en el botón *Seleccionar Recetario* abriremos una ventana en la que podremos  
    navegar por los distintos directorios del PC para seleccionar el archivo XML con las recetas que  
    queramos cargar.
    3. Una vez seleccionado aceptaremos la selección dando al botón *Aceptar*.
    4. Seleccionar en la lista donde se han cargado las recetas las que queramos.
    5. Cargar las recetas seleccionadas a en la lista de la compra haciendo click en el botón *Seleccionar*  
    (los pasos 4 y 5 se podrán repetir hasta que estemos conformes con la selección).
    6. Generar el archivo XML haciendo click en el botón *Generar lista de la compra* 
    7. En la ventana que se abre seleccionar el directorio donde queramos guardar el archivo  
    creado anteriormente

    Tras estos pasos se abra generado un archivo XML con el nombre **Compra.xml** en el  
    directorio que hayamos elegido en el ultimo paso.
    Para reiniciar la Lista de la compra se deberá volver a cargar el XML de nuestro recetario  
    como se explica en el paso 1.

## Pruebas:

    Se ha usado el documento XML generado para las practicas anteriores en las que se tenía que  
    simular un recetario, se encuentra adjunto en la carpeta Resources.
