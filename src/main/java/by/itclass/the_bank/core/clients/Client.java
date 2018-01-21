package by.itclass.the_bank.core.clients;

public class Client {

    private String name;
    private String secondName;
    private final String passportID;

    public Client(String name, String secondName, String passportID) {
        setName(name);
        setSecondName(secondName);
        if (passportID == null || passportID.isEmpty()){
            throw new IllegalArgumentException("номер паспорта не может быть пустым");
        }
        this.passportID = passportID;
    }

    public String getName() {
        return name;
    }
    public String getSecondName() {
        return secondName;
    }
    public String getPassportID() {
        return passportID;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = name;
    }

    public void setSecondName(String secondName) {
        if (secondName == null || secondName.isEmpty()){
            throw new IllegalArgumentException("Фамилия не может быть пустой");
        }
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", passportID='" + passportID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return  false;
        }
        if (obj == this){
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return  false;
        }
        Client other = (Client) obj;
        return this.getPassportID().compareTo(other.getPassportID()) == 0;
    }

    @Override
    public int hashCode() {
        return passportID.hashCode();
    }
}
