import java.util.Random;

public class PersonajeBase extends Personaje {
    private final String nombre;
    private final Random random;
    private int puntosDeVida;

    public PersonajeBase(String nombre) {
        this.nombre = nombre;
        this.puntosDeVida = 100;
        this.random = new Random();
    }

    @Override
    public int generarDanio() {
        return random.nextInt((MAX_DANIO - MIN_DANIO) + 1) + MIN_DANIO;
    }

    @Override
    public void recibirDanio(int danio) {
        puntosDeVida -= danio;
        if (puntosDeVida < 0) {
            puntosDeVida = 0;
        }
    }

    @Override
    public boolean estaVivo() {
        return puntosDeVida > 0;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getPuntosDeVida() {
        return puntosDeVida;
    }
}
