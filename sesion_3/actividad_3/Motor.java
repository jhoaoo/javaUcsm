public class Motor {
    private int numMotor;
    private int revPorMin;

    public Motor(int numMotor, int revPorMin) {
        this.numMotor = numMotor;
        this.revPorMin = revPorMin;
    }

    public int getNumMotor() { return numMotor; }
    public void setNumMotor(int numMotor) { this.numMotor = numMotor; }
    public int getRevoluciones() { return revPorMin; }
    public void setRevoluciones(int rev) { this.revPorMin = rev; }

    public String toString() {
        return "Motor: " + numMotor + ", RPM: " + revPorMin;
    }
}
