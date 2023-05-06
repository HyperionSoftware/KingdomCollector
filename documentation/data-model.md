# Diagrama

<img width="996" alt="Diagrama UML" src="https://user-images.githubusercontent.com/101675872/234635731-6a41c2c4-b904-4458-8888-fad8a0f1577e.png">

## Persistencia

Para cada una de estas clases, se podrían utilizar las funciones de Firebase para leer y escribir datos en tiempo real y mantener los datos sincronizados entre la aplicación y la base de datos en Firebase. Por ejemplo, para guardar los datos en Firebase se podrían utilizar los métodos setValue() o updateChildren(), mientras que para leer los datos se podrían utilizar los métodos addListenerForSingleValueEvent() o addChildEventListener(), dependiendo de la necesidad.

### Collection

Se podría crear un objeto DatabaseReference para la colección de cartas y guardar la información de la colección en la base de datos, incluyendo la lista de cartas y las cartas seleccionadas (selectedCards).

### Player

Se podría crear un objeto DatabaseReference para cada jugador y guardar la información del jugador en la base de datos, incluyendo su nombre, la lista de cartas de su mazo (deck) y su puntuación (points).

### Game

Se podría crear un objeto DatabaseReference para cada juego y guardar la información del juego en la base de datos, incluyendo la lista de jugadores, el tablero de juego (board) y el mazo de cartas (deck). También se podría agregar una referencia a la lista de jugadores dentro de cada objeto DatabaseReference del jugador para establecer una relación entre ellos.
