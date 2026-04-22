public class ArmaLigera extends Arma {
    public ArmaLigera(Personaje personaje) {
        super(personaje);
    }

    @Override
    protected String getTipoArma() {
        return "arma ligera";
    }

    @Override
    protected int getBonoDanio() {
        return 4;
    }
}
