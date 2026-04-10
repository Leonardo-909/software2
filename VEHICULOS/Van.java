/**
 * Vehículo tipo Van.
 */
public class Van extends Vehiculo {

    public Van(String placa, String modelo, int autonomiaKm, String tipoEnergia) {
        super(placa, modelo, autonomiaKm, tipoEnergia);
    }

    @Override
    public String getTipo() { return "Van"; }
}
