public class ArmaPesada extends Arma {
    public ArmaPesada(Personaje personaje) {
        super(personaje);
    }

    @Override
    protected String getTipoArma() {
        return "arma pesada";
    }

    @Override
    protected int getBonoDanio() {
        return 7;
    }
}
