package by.itclass.the_bank.core.accounts;

public class NegativeNumberException extends RuntimeException {

    public String getValueName() {
        return valueName;
    }

    private  String valueName;

    public NegativeNumberException(String message, String valueName){
        super((message));
        this.valueName= valueName;
    }
}
