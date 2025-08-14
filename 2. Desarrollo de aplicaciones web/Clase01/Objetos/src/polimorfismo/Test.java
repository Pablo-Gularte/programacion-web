package polimorfismo;

public class Test {
    public static void main(String[] args) {
        Auto auto = new Auto("Ford", 0);
        AutoCarrera autoCarrera = new AutoCarrera("Chevrolet", 0, "Alerón trasero");

        auto.acelerar();
        autoCarrera.acelerar();

        System.out.println(auto);
        System.out.println(autoCarrera);
    }
}
