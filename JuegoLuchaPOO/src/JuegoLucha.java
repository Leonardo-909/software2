import java.util.Scanner;

public class JuegoLucha {
    private final Personaje jugador1;
    private final Personaje jugador2;

    public JuegoLucha(Personaje jugador1, Personaje jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public void iniciarPelea() {
        System.out.println();
        System.out.println("La pelea comienza entre " + jugador1.getNombre()
                + " y " + jugador2.getNombre() + ".");
        System.out.println();

        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            turno(jugador1, jugador2);
            if (jugador2.estaVivo()) {
                turno(jugador2, jugador1);
            }
        }

        System.out.println();
        if (jugador1.estaVivo()) {
            System.out.println(jugador1.getNombre() + " ha ganado la pelea.");
        } else {
            System.out.println(jugador2.getNombre() + " ha ganado la pelea.");
        }
    }

    private void turno(Personaje atacante, Personaje defensor) {
        System.out.println("Turno de " + atacante.getNombre() + ".");
        System.out.println(defensor.getNombre() + " tiene " + defensor.getPuntosDeVida()
                + " puntos de vida antes del ataque.");

        atacante.atacar(defensor);

        System.out.println(defensor.getNombre() + " ahora tiene " + defensor.getPuntosDeVida()
                + " puntos de vida.");
        System.out.println();
    }

    private static Personaje crearPersonaje(Scanner scanner, int numeroJugador) {
        System.out.print("Introduce el nombre del jugador " + numeroJugador + ": ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            nombre = "Jugador " + numeroJugador;
        }

        Personaje personaje = new PersonajeBase(nombre);

        System.out.println("Selecciona el arma para " + nombre + ":");
        System.out.println("1. Sin arma");
        System.out.println("2. Arma ligera");
        System.out.println("3. Arma pesada");
        System.out.print("Opcion: ");

        String opcion = scanner.nextLine().trim();
        if ("2".equals(opcion)) {
            return new ArmaLigera(personaje);
        }
        if ("3".equals(opcion)) {
            return new ArmaPesada(personaje);
        }
        return personaje;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Personaje jugador1 = crearPersonaje(scanner, 1);
        System.out.println();
        Personaje jugador2 = crearPersonaje(scanner, 2);

        JuegoLucha juego = new JuegoLucha(jugador1, jugador2);
        juego.iniciarPelea();

        scanner.close();
    }
}
