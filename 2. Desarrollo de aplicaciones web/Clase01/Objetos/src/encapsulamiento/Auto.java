package encapsulamiento;

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
        // Regla de negocio
        if( velocidad >= 0 && velocidad <= 130) {
            this.velocidad = velocidad;
        } else {
            System.out.println("La velocidad es incorrecta");
        }
    }

    public void acelerar() {
        setVelocidad(getVelocidad() + 10);
    }

    
    public void acelerar(int km) {
        setVelocidad(getVelocidad() + km);
    }

    @Override
    public String toString() {
        return "Auto [marca=" + marca + ", velocidad=" + velocidad + "]";
    }
}
