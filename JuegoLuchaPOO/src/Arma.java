public abstract class Arma extends Personaje {
    protected final Personaje personaje;

    protected Arma(Personaje personaje) {
        this.personaje = personaje;
    }

    protected abstract String getTipoArma();

    protected abstract int getBonoDanio();

    @Override
    public int generarDanio() {
        int danioBase = personaje.generarDanio();
        return Math.min(MAX_DANIO, danioBase + getBonoDanio());
    }

    @Override
    public void recibirDanio(int danio) {
        personaje.recibirDanio(danio);
    }

    @Override
    public boolean estaVivo() {
        return personaje.estaVivo();
    }

    @Override
    public String getNombre() {
        return personaje.getNombre();
    }

    @Override
    public int getPuntosDeVida() {
        return personaje.getPuntosDeVida();
    }
}
