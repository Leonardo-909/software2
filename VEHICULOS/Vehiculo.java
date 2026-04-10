/**
 * Clase abstracta base para todos los vehículos del sistema AutoCar.
 * Patrón: Factory Method — define la interfaz común para todos los vehículos.
 */
public abstract class Vehiculo {

    protected String placa;
    protected String modelo;
    protected int autonomiaKm;   // Autonomía en kilómetros (clave para ordenamiento)
    protected String tipoEnergia; // "Eléctrico" o "Híbrido"

    public Vehiculo(String placa, String modelo, int autonomiaKm, String tipoEnergia) {
        this.placa       = placa;
        this.modelo      = modelo;
        this.autonomiaKm = autonomiaKm;
        this.tipoEnergia = tipoEnergia;
    }

    // Método abstracto que cada subclase implementa
    public abstract String getTipo();

    // --- Getters ---
    public String getPlaca()       { return placa; }
    public String getModelo()      { return modelo; }
    public int    getAutonomia()   { return autonomiaKm; }
    public String getTipoEnergia() { return tipoEnergia; }

    @Override
    public String toString() {
        return String.format("[%s] %s | Placa: %s | Autonomía: %d km | Energía: %s",
                getTipo(), modelo, placa, autonomiaKm, tipoEnergia);
    }
}
