package polimorfismo;

public class AutoCarrera extends Auto {
    private String tipoAleron;

    public AutoCarrera() {};

    public AutoCarrera(String marca, int velocidad, String tipoAleron) {
        super(marca, velocidad);
        setTipoAleron(tipoAleron);
    }

    public String getTipoAleron() {
        return tipoAleron;
    }

    public void setTipoAleron(String tipoAleron) {
        this.tipoAleron = tipoAleron;
    }

    @Override
    public void acelerar() {
        super.setVelocidad(getVelocidad() + 100);
    }

    @Override
    public String toString() {
        return "AutoCarrera [tipoAleron=" + tipoAleron + ", getMarca()=" + getMarca() + ", getVelocidad()="
                + getVelocidad() + "]";
    }

}
