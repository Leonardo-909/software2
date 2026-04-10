/**
 * PATRÓN FACTORY METHOD — Escenario 1
 *
 * Centraliza la creación de vehículos. El cliente pide un tipo ("auto", "van",
 * "camion") y la fábrica devuelve la instancia concreta sin necesidad de
 * múltiples if/switch dispersos por el código.
 *
 * Ventaja: agregar un nuevo tipo de vehículo solo requiere crear una nueva
 * subclase de Vehiculo y añadir un caso aquí.
 */
public class VehiculoFactory {

    /**
     * Crea y devuelve un vehículo según el tipo solicitado.
     *
     * @param tipo        "auto" | "van" | "camion"
     * @param placa       Placa única del vehículo
     * @param modelo      Nombre/modelo comercial
     * @param autonomiaKm Autonomía en kilómetros
     * @param tipoEnergia "Eléctrico" | "Híbrido"
     * @return Instancia concreta de Vehiculo
     * @throws IllegalArgumentException si el tipo no es reconocido
     */
    public static Vehiculo crear(String tipo, String placa, String modelo,
                                  int autonomiaKm, String tipoEnergia) {
        switch (tipo.toLowerCase()) {
            case "auto":
                return new Auto(placa, modelo, autonomiaKm, tipoEnergia);
            case "van":
                return new Van(placa, modelo, autonomiaKm, tipoEnergia);
            case "camion":
                return new CamionLigero(placa, modelo, autonomiaKm, tipoEnergia);
            default:
                throw new IllegalArgumentException("Tipo de vehículo desconocido: " + tipo);
        }
    }
}
