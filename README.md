# Contagio_GOT

Proyecto realizado por:

  -Alberto Fernandez-Baillo (@albeferodriguez)
  
  -Carlos Piña
  
  -Juan Luis Romero (@JuanluR8)
  

Instrucciones de uso de la aplicación.
Consideraciones previas:

•	La aplicación utiliza la librería javacsv.jar para el procesamiento de los datos obtenidos del Dataset, así como su adaptación y procesamiento para el correcto uso en la herramienta.

•	Antes del lanzamiento de la aplicación desde un IDE, comprobar en las preferencias del proyecto el correcto enrutamiento de dicha librería. Ésta se puede encontrar en la carpeta librería CSV del proyecto.

Al iniciar la aplicación, se mostrarán al usuario diferentes opciones de selección sobre qué personaje desea elegir para simular el modelo de contagio:

1.	Elegir Personaje, donde el usuario podrá seleccionar el personaje sobre el que simular el contagio entre todos los posibles. Al usuario se le mostrarán distintas opciones sobre qué personaje elegir, mostrándolos por bloques según los nombres de los personajes.

2.	Usar los personajes por defecto, donde el usuario podrá seleccionar entre los personajes que se dan preseleccionados por el desarrollador.

Una vez elegido el personaje sobre el que se desea simular el modelo, se mostrarán los contagios disponibles, siendo:

1.	Psoriagrís, que corresponde con el modelo de contagio SIR.

2.	Caminantes blancos, que corresponde con el modelo de contagio SI.

Posteriormente, se pide al usuario que se introduzca la posibilidad de contagio, pidiendo un valor entre 0 y 100, que corresponde con la probabilidad entre 0% y 100% de contagio al resto de personajes.

En el caso de la psoriagrís, también se pedirá la probabilidad de curación, también entre 0 y 100, mediante la cual un personaje infectado podrá morir/inmunizarse  o mantenerse infectado.

Tras recoger toda la información, la aplicación generará los resultados, obteniendo un archivo .csv por cada paso de ejecución que se guardará en la carpeta Resultados, cada fichero con el formato

NodosGOT_<num-infectados>_<personaje-inicial>_t<tiempo-de-ejecución>.csv

Nota: Para la correcta interpretación de los resultados se recomienda el uso de la herramienta Gephi. 

Nota2: Si se van a realizar dos ejecuciones con el mismo personaje se deben respaldar los .csv generados en la anterior ejecución ya que de no hacerlo se sobreescribiran o mezclaran los .csv generados.
