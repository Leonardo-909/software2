/**
 * Clase principal — demostración del sistema AutoCar.
 *
 * Muestra en acción:
 *   - Factory Method para crear vehículos
 *   - Array de objetos en el Inventario (agregar, buscar, ordenar)
 *   - Builder para construir contratos paso a paso con reglas de negocio
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     SISTEMA AUTOCAR — Demo Patrones      ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ----------------------------------------------------------------
        // ESCENARIO 1: Factory Method + Inventario (array de objetos)
        // ----------------------------------------------------------------
        System.out.println("--- ESCENARIO 1: Inventario con Factory Method ---\n");

        Inventario inventario = new Inventario();

        // Creamos vehículos usando la fábrica (sin new Auto / new Van / etc.)
        inventario.agregar(VehiculoFactory.crear("auto",   "ABC123", "Tesla Model 3",    580, "Eléctrico"));
        inventario.agregar(VehiculoFactory.crear("van",    "XYZ789", "Hyundai Staria",   420, "Híbrido"));
        inventario.agregar(VehiculoFactory.crear("camion", "LMN456", "BYD T3 EV",        300, "Eléctrico"));
        inventario.agregar(VehiculoFactory.crear("auto",   "QRS321", "Toyota Prius",     950, "Híbrido"));
        inventario.agregar(VehiculoFactory.crear("van",    "TUV654", "Ford E-Transit",   317, "Eléctrico"));

        // Mostrar inventario original
        System.out.println("Inventario original:");
        inventario.mostrar();

        // Ordenar por autonomía
        inventario.ordenarPorAutonomia();
        System.out.println("\nInventario ordenado por autonomía (menor → mayor):");
        inventario.mostrar();

        // Buscar por placa
        String placaBuscada = "XYZ789";
        Vehiculo encontrado = inventario.buscarPorPlaca(placaBuscada);
        if (encontrado != null) {
            System.out.println("Búsqueda por placa '" + placaBuscada + "': " + encontrado);
        } else {
            System.out.println("Vehículo con placa '" + placaBuscada + "' no encontrado.");
        }

        // ----------------------------------------------------------------
        // ESCENARIO 2: Builder para contratos de alquiler
        // ----------------------------------------------------------------
        System.out.println("\n--- ESCENARIO 2: Contratos con Builder ---");

        Vehiculo vehiculoAlquilado = inventario.buscarPorPlaca("ABC123");

        // Contrato corto — sin descuento, con GPS y seguro
        Contrato contrato1 = new Contrato.ContratoBuilder()
                .cliente("María García")
                .vehiculo(vehiculoAlquilado)
                .plan("Semanal", 14)
                .conGPS()
                .conSeguro()
                .build();

        System.out.println(contrato1);

        // Contrato largo — aplica descuento del 10%
        Vehiculo otroVehiculo = inventario.buscarPorPlaca("QRS321");

        Contrato contrato2 = new Contrato.ContratoBuilder()
                .cliente("Empresa LogiCo S.A.S.")
                .vehiculo(otroVehiculo)
                .plan("Mensual", 45)
                .conGPS()
                .conSeguro()
                .conCargador()
                .build();

        System.out.println(contrato2);

        // Demostración de validación: contrato sin vehículo → excepción
        System.out.println("\n--- Validación Builder: contrato incompleto ---");
        try {
            Contrato invalido = new Contrato.ContratoBuilder()
                    .cliente("Pedro Pérez")
                    .plan("Diario", 3)
                    // Sin vehículo — debe lanzar excepción
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("Error capturado correctamente: " + e.getMessage());
        }

        System.out.println("\n¡Sistema AutoCar ejecutado exitosamente!");
    }
}
