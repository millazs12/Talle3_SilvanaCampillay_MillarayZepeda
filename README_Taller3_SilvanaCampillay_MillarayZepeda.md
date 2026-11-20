## Spooky League: Simulador de minijuegos y  habilidades en POO 


## Descripción

El proyecto consiste en el desarrollo de un programa que simula un entorno de minijuegos en el que los jugadores pueden participar utilizando distintos tipos de monstruos, cada uno con características y ventajas particulares. El sistema cuenta con un catálogo de monstruos clasificados por raza, cuyas habilidades y atributos influyen en el potencial obtenido durante los diferentes desafíos. Se dispone de cuatro minijuegos, cada uno con condiciones específicas y la posibilidad de ser jugado una única vez por sesión. Además, determinados tipos de monstruos pueden utilizar habilidades especiales, sujetas a restricciones según su raza y con un límite de uso de una habilidad por minijuego.

Por otra parte, el programa incorpora un sistema de registro de puntuaciones que almacena el puntaje máximo histórico alcanzado por cada jugador en una sesión, permitiendo construir y consultar un ranking general. Asimismo, ofrece opciones para revisar el catálogo de monstruos, consultar el historial de la sesión y visualizar el estado de los minijuegos disponibles o ya completados. Su objetivo es proporcionar una experiencia interactiva que combine estrategia y toma de decisiones, considerando las características de cada monstruo y las condiciones particulares de cada desafío.
## Integrantes

1. Millaray Zepeda (ICCI) - 22.063.994-0 - millazs12

2. Silvana Campillay (ICI) - 21.862.853-2 - Npieer

## Estructura del proyecto

Los paquetes utilizados fueron:

 - java.io.FileNotFoundException
 - java.io.FileWriter
 - java.io.File
 - java.io.IOException
 - java.util.Scanner
 -  java.util.ArrayList
 -  java.util.LinkedList

Las clases principales son:
 - Main
 - Monstruos
 - Sistema
 - Jugador
 - HistorialItem

Interfaces usadas:

- Furioso
- Mordedor
- Regenerable
- Volador


## Instrucciones de ejecucion

**Requisitos:**
Tener instalado Java (JDK 8 o superior) y un entorno de desarrollo como Eclipse.

**Clonar e ingresar al proyecto:**
Para descargar el código y entrar a la carpeta desde la terminal, hay que ejecutar esto:

git clone https://github.com/millazs12/Talle3_SilvanaCampillay_MillarayZepeda.git

cd Taller3_MillarayZepeda_SilvanaCampillay

Los archivos de texto (Monstruos.txt y Registros.txt) ya vienen incluidos en la raíz del proyecto para que el programa pueda leerlos sin problemas.

**Cómo ejecutar el programa:**

 - Opción 1: Usando un IDE (Eclipse)
  1. Importar el proyecto como Java Project existente.
  2. Ubicar la clase Main.java dentro del paquete taller3.
  3. Hacer clic derecho y seleccionar Run As > Java Application.
 

- Opción 2: Usando la terminal
  
Estando dentro de la carpeta del proyecto

1. Compilar:
   
javac src/taller3/Main.java

2. Y luego ejecutar:
   
java -cp src taller3.Main
