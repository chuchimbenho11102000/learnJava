public class Computer {
    private String name;
    private String model;
    private String cpu;
    private Integer ram;

    public Computer(String name, String model, String cpu, Integer ram) {
        this.name = name;
        this.model = model;
        this.cpu = cpu;
        this.ram = ram;
    }

    public Computer() {}

    public Computer(String cpu,Integer ram) {
        this.cpu = cpu;
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", cpu='" + cpu + '\'' +
                ", ram=" + ram + "gb"+
                '}';
    }
}
