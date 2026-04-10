/**
 * Contrato de alquiler de AutoCar.
 *
 * Clase con campos obligatorios (cliente, vehículo, plan) y opcionales
 * (GPS, seguro, cargador portátil, descuento aplicado).
 *
 * Se construye EXCLUSIVAMENTE a través de ContratoBuilder para garantizar
 * consistencia y aplicar reglas de negocio durante la construcción.
 */
public class Contrato {

    // --- Campos obligatorios ---
    private final String   cliente;
    private final Vehiculo vehiculo;
    private final String   planAlquiler;   // "Diario" | "Semanal" | "Mensual"
    private final int      duracionDias;

    // --- Campos opcionales ---
    private final boolean  incluyeGPS;
    private final boolean  incluyeSeguro;
    private final boolean  incluyCargador;

    // --- Calculado por el Builder ---
    private final double   precioFinal;

    // Constructor privado: solo el Builder puede instanciar
    private Contrato(ContratoBuilder b) {
        this.cliente        = b.cliente;
        this.vehiculo       = b.vehiculo;
        this.planAlquiler   = b.planAlquiler;
        this.duracionDias   = b.duracionDias;
        this.incluyeGPS     = b.incluyeGPS;
        this.incluyeSeguro  = b.incluyeSeguro;
        this.incluyCargador = b.incluyCargador;
        this.precioFinal    = b.precioFinal;
    }

    @Override
    public String toString() {
        return String.format(
            "\n========== CONTRATO AUTOCAR ==========\n" +
            "  Cliente      : %s\n"  +
            "  Vehículo     : %s\n"  +
            "  Plan         : %s (%d días)\n" +
            "  GPS          : %s\n"  +
            "  Seguro       : %s\n"  +
            "  Cargador     : %s\n"  +
            "  Precio final : $%.2f\n" +
            "======================================",
            cliente, vehiculo, planAlquiler, duracionDias,
            incluyeGPS     ? "Sí" : "No",
            incluyeSeguro  ? "Sí" : "No",
            incluyCargador ? "Sí" : "No",
            precioFinal
        );
    }

    // =========================================================================
    // PATRÓN BUILDER — Escenario 2
    //
    // Permite construir el contrato paso a paso, validando reglas de negocio
    // (ej: descuento automático si duración > 30 días) y evitando que el
    // objeto quede en estado inválido (sin cliente, sin vehículo, etc.).
    // =========================================================================
    public static class ContratoBuilder {

        // Precios base por plan ($ / día)
        private static final double PRECIO_DIARIO   = 50.0;
        private static final double PRECIO_SEMANAL  = 40.0;
        private static final double PRECIO_MENSUAL  = 30.0;
        private static final double COSTO_GPS       = 5.0;
        private static final double COSTO_SEGURO    = 10.0;
        private static final double COSTO_CARGADOR  = 3.0;
        private static final double DESCUENTO_LARGO = 0.10; // 10% si > 30 días

        // Obligatorios
        private String   cliente;
        private Vehiculo vehiculo;
        private String   planAlquiler;
        private int      duracionDias;

        // Opcionales (false por defecto)
        private boolean incluyeGPS     = false;
        private boolean incluyeSeguro  = false;
        private boolean incluyCargador = false;

        // Calculado al construir
        private double precioFinal;

        // --- Setters encadenables ---

        public ContratoBuilder cliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public ContratoBuilder vehiculo(Vehiculo vehiculo) {
            this.vehiculo = vehiculo;
            return this;
        }

        public ContratoBuilder plan(String plan, int dias) {
            this.planAlquiler = plan;
            this.duracionDias = dias;
            return this;
        }

        public ContratoBuilder conGPS() {
            this.incluyeGPS = true;
            return this;
        }

        public ContratoBuilder conSeguro() {
            this.incluyeSeguro = true;
            return this;
        }

        public ContratoBuilder conCargador() {
            this.incluyCargador = true;
            return this;
        }

        // --- Build con validaciones y cálculo de precio ---

        public Contrato build() {
            // Validaciones de campos obligatorios
            if (cliente == null || cliente.isBlank())
                throw new IllegalStateException("El contrato requiere un cliente.");
            if (vehiculo == null)
                throw new IllegalStateException("El contrato requiere un vehículo.");
            if (planAlquiler == null || duracionDias <= 0)
                throw new IllegalStateException("El contrato requiere un plan y duración válida.");

            // Calcular precio base según plan
            double precioDia;
            switch (planAlquiler.toLowerCase()) {
                case "semanal":  precioDia = PRECIO_SEMANAL;  break;
                case "mensual":  precioDia = PRECIO_MENSUAL;  break;
                default:         precioDia = PRECIO_DIARIO;   break;
            }

            precioFinal = precioDia * duracionDias;

            // Accesorios opcionales
            if (incluyeGPS)     precioFinal += COSTO_GPS     * duracionDias;
            if (incluyeSeguro)  precioFinal += COSTO_SEGURO  * duracionDias;
            if (incluyCargador) precioFinal += COSTO_CARGADOR * duracionDias;

            // Regla de negocio: descuento del 10% para contratos > 30 días
            if (duracionDias > 30) {
                precioFinal *= (1 - DESCUENTO_LARGO);
                System.out.println("  [Regla] Descuento del 10% aplicado por duración > 30 días.");
            }

            return new Contrato(this);
        }
    }
}
