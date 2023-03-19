# KingdomCollector
App Android Studio Proyecto Integrador Hyperion 2022/23

## Authors

|Nombre |Correo electrónico|Github|
|-----|--------|--------|
|Ricard Bosch Perianes|ricardbope@gmail.com   |[@RickyBP](https://github.com/RickyBP)|
|Claudia Rodriguez Martinez  |claudiasarlle@gmail.com      |[@claudiasarlle1](https://github.com/claudiasarlle1)|
|Carlos Martínez González   |carlosmg.12@hotmail.es|[@Fullettortuga](https://github.com/Fullettortuga)|
|Joan Bonell Ruiz| joanbonellruiz@gmail.com|[@JoanBonell](https://github.com/JoanBonell)|



## Game idea

El juego que queremos desarrollar se trata de un juego de cartas, ambientado en el fútbol, concretamente es una mezcla entre un juego de cartas llamado “Triple Triad” (Minijuego que aparecía en el videojuego Final Fantasy 8) y la ya famosa Kings League de fútbol.

La temática se define con el fútbol, es decir, el tablero sería un campo de fútbol y las cartas serían los diferentes jugadores de la Kings League.

Las reglas del juego se parecen a las del “Triple Triad”, se trata de cartas con la ilustración de un personaje único en el juego. En la esquina superior izquierda, se encuentran los valores que caracterizan a dicha carta. (ver apartado de reglas)

Los valores de las cartas se comprenden entre 1-10, siendo el número 10 la letra “A” el máximo valor. Las cartas se enfrentan para ganar puntos y zonas del tablero, para que una carta se enfrente a otra se tiene que colocar en una de las casillas adyacentes. 

El tablero consta de 9 casillas, donde se colocan las cartas para poder jugar. En alguna ocasión, el tablero podrá representar iconos elementales para ciertas cartas del juego (ver regla elemental). Cuando el tablero está lleno de cartas la partida se da por finalizada, y el jugador con mayor puntuación gana la partida.

## Reglas del juego:

Abierto: los dos jugadores pueden ver las cartas del oponente.

Mano mezclada: las cartas se escogen al azar entre las disponibles.

Elemental: en el campo de juego aparecen iconos elementales. Si colocas sobre uno de ellos una carta con el mismo icono elemental, suma +1 a todos sus bordes. Si no coincide, el valor de todos los bordes baja en -1.

Cadena: Para activar la regla de la cadena previamente necesitamos haber activado la regla de suma o igual, de esta forma, las cartas volteadas con las reglas anteriores se establecen como nuevas, pudiendo dar la vuelta a cartas adyacentes a ésta si su valor es inferior en el lado que la está tocando.

Regla Igual
La Regla "Igual" establece que, si colocas una carta entre dos con el mismo valor en los bordes, las dos cartas se volverán de tu color. Vamos con una descripción visual. 

Regla Suma
"Suma" funciona de una manera parecida a igual, pero cuando el valor de los bordes sumados sea el mismo. 

Regla Pared
"Pared" es una regla que afecta a Igual y Suma. Con esta regla activa, el borde del tablero cuenta como una A (10) a la hora de hacer cálculos para Suma e Igual.

## Reglas del juego mínimas:

Los valores de las cartas se comprenden entre 1-10, siendo el número 10 la letra “A” el máximo valor. Las cartas se enfrentan para ganar puntos y zonas del tablero, para que una carta se enfrente a otra se tiene que colocar en una de las casillas adyacentes. De esta manera se suman puntos, hasta que el tablero está lleno y el jugador con mayor puntuación gana la partida.

Regla Pared
"Pared" es una regla que afecta a Igual y Suma. Con esta regla activa, el borde del tablero cuenta como una A (10) a la hora de hacer cálculos para Suma e Igual.

Regla Suma
"Suma" funciona de una manera parecida a igual, pero cuando el valor de los bordes sumados sea el mismo. 

