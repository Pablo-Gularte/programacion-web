package polimorfismo;

public class Auto {
    private String marca;
    private int velocidad;

    public Auto() {
    }

    public Auto(String marca, int velocidad) {
        setMarca(marca);
        setVelocidad(velocidad);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public String toString() {
        return "Auto [marca=" + marca + ", velocidad=" + velocidad + "]";
    }

    public void acelerar() {
        setVelocidad(getVelocidad() + 10);
    }
}
