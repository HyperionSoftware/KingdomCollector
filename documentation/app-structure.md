# Estructura de la aplicación (MVVM)

La aplicación sigue el patrón de arquitectura **Modelo-Vista-VistaModelo** (MVVM), que separa claramente las responsabilidades y proporciona una estructura organizada y mantenible para el desarrollo de la aplicación.

## Modelos
- `Card`: Representa una carta del juego con sus propiedades y atributos.
- `Deck`: Representa un mazo de cartas que pertenece a un jugador. Contiene una lista de cartas y métodos para gestionar el mazo.
- `Player`: Representa un jugador en el juego, ya sea humano o controlado por la IA. Contiene el nombre del jugador, su mazo de cartas y sus puntos en el juego.

## Vistas
- `GameActivity`: La actividad principal del juego que muestra el tablero y las interacciones del usuario.
- `WinnerFragment`: Un fragmento que se muestra cuando el juego ha terminado y muestra al ganador y los puntos finales.

## Modelos de Vista (ViewModels)
- `BoardViewModel`: Representa el estado del tablero y proporciona métodos para acceder y modificar los datos del tablero.
- `DeckViewModel`: Representa el estado de un mazo de cartas y proporciona métodos para acceder y modificar el mazo, así como seleccionar una carta del mazo.
- `CellViewModel`: Representa el estado de una celda en el tablero y proporciona métodos para acceder y modificar los datos de la celda, como la carta colocada en ella.

## Controlador (Controller)
- `GameController`: Controla la lógica del juego, incluyendo el inicio de un nuevo juego, el manejo de turnos, la colocación de cartas en el tablero, el cálculo de puntos y la verificación del ganador. Coordina la interacción entre las vistas y los modelos de vista.

## Administrador de Preferencias (SharedPreferencesManager)
- `SharedPreferencesManager`: Gestiona el almacenamiento de datos en las preferencias compartidas de la aplicación, como las cartas seleccionadas por el jugador humano.

## Firebase
- Clases y métodos relacionados con la autenticación y el almacenamiento de datos en Firebase.

Esta estructura sigue el patrón MVVM, donde los modelos representan los datos y la lógica de negocio, las vistas muestran la interfaz de usuario y los modelos de vista actúan como intermediarios entre los modelos y las vistas, proporcionando los datos necesarios y realizando la comunicación entre ellos. El controlador se encarga de coordinar la lógica del juego, mientras que el administrador de preferencias maneja el almacenamiento de datos persistentes. La integración con Firebase permite la autenticación de usuarios y el almacenamiento de datos en la nube.

Esta estructura siguiendo el patrón MVVM facilita la escalabilidad, mantenibilidad y testabilidad de la aplicación, al separar claramente las responsabilidades y permitir una mayor modularidad en el desarrollo del código.
