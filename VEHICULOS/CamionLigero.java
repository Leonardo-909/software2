/**
 * Vehículo tipo Camión Ligero.
 */
public class CamionLigero extends Vehiculo {

    public CamionLigero(String placa, String modelo, int autonomiaKm, String tipoEnergia) {
        super(placa, modelo, autonomiaKm, tipoEnergia);
    }

    @Override
    public String getTipo() { return "Camión Ligero"; }
}
