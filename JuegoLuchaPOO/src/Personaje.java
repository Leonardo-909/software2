public abstract class Personaje {
    public static final int MIN_DANIO = 10;
    public static final int MAX_DANIO = 30;

    public void atacar(Personaje oponente) {
        int danio = generarDanio();
        oponente.recibirDanio(danio);

        System.out.println(
                getNombre() + " ataca a " + oponente.getNombre()
                        + " y causa " + danio + " puntos de daño.");
    }

    public abstract int generarDanio();

    public abstract void recibirDanio(int danio);

    public abstract boolean estaVivo();

    public abstract String getNombre();

    public abstract int getPuntosDeVida();
}
