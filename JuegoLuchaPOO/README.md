# JuegoLuchaPOO

Implementacion del juego de lucha en Java usando el patron estructural `Decorator`.

## Patron aplicado

Se eligio `Decorator` porque permite mantener un `Personaje` generico y agregar
un arma sin crear personajes concretos.

La clase `Personaje` conserva los metodos pedidos en el PDF:

- `atacar(Personaje oponente)`
- `estaVivo()`
- `getNombre()`
- `getPuntosDeVida()`

En esta solucion:

- `PersonajeBase` representa a cualquier jugador generico.
- `Arma` es el decorador que modifica el ataque.
- `ArmaLigera` y `ArmaPesada` son dos opciones simples de arma.

## Como compilar

```powershell
javac --release 8 src\\*.java
```

## Como ejecutar

```powershell
java -cp src JuegoLucha
```

## Estructura

- `src/Personaje.java`: clase abstracta base del juego.
- `src/PersonajeBase.java`: personaje generico con 100 HP.
- `src/Arma.java`: decorador abstracto para armas.
- `src/ArmaLigera.java`: arma 1.
- `src/ArmaPesada.java`: arma 2.
- `src/JuegoLucha.java`: flujo de turnos y punto de entrada.
