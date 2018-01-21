package by.itclass.the_bank.core.storages;

public class Share implements Valuable {

    private final String  name;
    private double value;

    public Share(String name, double value){
        if(name == null){
            throw new IllegalArgumentException("Акция должна иметь имя");
        }
        if (value <= 0){
            throw new IllegalArgumentException("Цена акции");
        }
        this.name = name;
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Share{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
