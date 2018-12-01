import java.util.ArrayList;

public class Customer extends Person{
    private int IDNumber;
    private ArrayList<DVD> customerDVD;

    public Customer(String firstName, String lastName, int IDNumber){
        super();
        this.IDNumber = IDNumber;
        customerDVD = new ArrayList<DVD>();
    }

    public boolean equals(Object obj) {
        if(obj instanceof Customer){
            Customer temp = (Customer) obj;
            return this.IDNumber == temp.getIDNumber();
        }
        else
            return false;
    }

    public String toString() {
        return (super.toString() + "\nID Number: " + IDNumber + customerDVD.toString());
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public String getLastName(){
        return  super.getLastName();
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    public int getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(int IDNumber) {
        this.IDNumber = IDNumber;
    }

}
