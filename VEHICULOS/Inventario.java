/**
 * Inventario de vehículos de AutoCar.
 *
 * Gestiona un array de objetos de tamaño fijo (no lista dinámica).
 * Operaciones: agregar, buscar por placa y ordenar por autonomía (burbuja).
 */
public class Inventario {

    private static final int CAPACIDAD_MAXIMA = 100;
    private Vehiculo[] vehiculos;
    private int cantidad;

    public Inventario() {
        this.vehiculos = new Vehiculo[CAPACIDAD_MAXIMA];
        this.cantidad  = 0;
    }

    // -----------------------------------------------------------------------
    // Agregar
    // -----------------------------------------------------------------------

    /**
     * Agrega un vehículo al inventario.
     *
     * @param v Vehículo a agregar
     * @return true si se agregó, false si el inventario está lleno
     */
    public boolean agregar(Vehiculo v) {
        if (cantidad >= CAPACIDAD_MAXIMA) {
            System.out.println("Inventario lleno. No se pudo agregar: " + v.getPlaca());
            return false;
        }
        vehiculos[cantidad++] = v;
        return true;
    }

    // -----------------------------------------------------------------------
    // Buscar por placa
    // -----------------------------------------------------------------------

    /**
     * Busca un vehículo por su placa (búsqueda lineal).
     *
     * @param placa Placa a buscar
     * @return El vehículo encontrado o null si no existe
     */
    public Vehiculo buscarPorPlaca(String placa) {
        for (int i = 0; i < cantidad; i++) {
            if (vehiculos[i].getPlaca().equalsIgnoreCase(placa)) {
                return vehiculos[i];
            }
        }
        return null;
    }

    // -----------------------------------------------------------------------
    // Ordenar por autonomía (Burbuja — Bubble Sort)
    // -----------------------------------------------------------------------

    /**
     * Ordena el inventario por autonomía de menor a mayor (in-place).
     * Algoritmo: burbuja — O(n²), adecuado para el tamaño del inventario.
     */
    public void ordenarPorAutonomia() {
        for (int i = 0; i < cantidad - 1; i++) {
            for (int j = 0; j < cantidad - 1 - i; j++) {
                if (vehiculos[j].getAutonomia() > vehiculos[j + 1].getAutonomia()) {
                    Vehiculo temp    = vehiculos[j];
                    vehiculos[j]     = vehiculos[j + 1];
                    vehiculos[j + 1] = temp;
                }
            }
        }
    }

    // -----------------------------------------------------------------------
    // Mostrar inventario
    // -----------------------------------------------------------------------

    public void mostrar() {
        System.out.println("=== INVENTARIO AUTOCAR (" + cantidad + " vehículos) ===");
        for (int i = 0; i < cantidad; i++) {
            System.out.println("  " + (i + 1) + ". " + vehiculos[i]);
        }
        System.out.println("==============================================");
    }

    public int getCantidad() { return cantidad; }
}
