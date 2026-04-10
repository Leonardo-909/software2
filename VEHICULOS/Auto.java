/**
 * Vehículo tipo Auto.
 */
public class Auto extends Vehiculo {

    public Auto(String placa, String modelo, int autonomiaKm, String tipoEnergia) {
        super(placa, modelo, autonomiaKm, tipoEnergia);
    }

    @Override
    public String getTipo() { return "Auto"; }
}
