package encapsulamiento;
public class Test {
    public static void main(String[] args) throws Exception {
        Auto auto1 = new Auto();
        Auto auto2 = new Auto("Chevrolet", 0);

        // Inicializo el estado del objeto
        auto1.setMarca("Ford");
        auto1.setVelocidad(0);

        // Comportamiento
        auto1.acelerar();
        auto1.acelerar(20);
        auto1.acelerar(200);

        // Muestro el estado del objeto
        System.out.println(auto1.toString());

        // Comportamiento auto2
        auto2.acelerar();
        auto2.acelerar(200);

        // Muestro el estado del objeto
        System.out.println(auto2);
    }
}
