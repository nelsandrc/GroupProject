import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    private int IDNumber;
    private ArrayList<DVD> customerDVD;

    public Customer(String firstName, String lastName, int IDNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.IDNumber = IDNumber;
        customerDVD = new ArrayList<DVD>();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Customer){
            Customer temp = (Customer) obj;
            return this.IDNumber == temp.getIDNumber();
        }
        else
            return false;
    }

    @Override
    public String toString() {
        String str = "Name: " + firstName + " " + lastName + "\nID Number: " + IDNumber + customerDVD.toString();
        return str;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getIDNumber() {
        return IDNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setIDNumber(int IDNumber) {
        this.IDNumber = IDNumber;
    }

}
