/**
 * @author Andrew Nelson
 * @author Tyler Goldstein
 * Represents a customer utilizing the Person class for use by a DVD rental company.
 */

import java.util.ArrayList;

public class Customer extends Person{
    private int IDNumber;
    private ArrayList<DVD> customerDVD;

    /**
     * Creates a new Customer with the given parameters.
     * @param firstName String representing the customer's first name.
     * @param lastName  String representing the customer's last name.
     * @param IDNumber  Integer representing the customer's ID number.
     */
    public Customer(String firstName, String lastName, int IDNumber){
        super(firstName, lastName);
        this.IDNumber = IDNumber;
        customerDVD = new ArrayList<DVD>();
    }

    /**
     * Creates a customer with default parameters
     */
    public Customer(){
        super();
        IDNumber = -1;
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

    /**
     * Adds a DVD to the list of movies currently rented by the customer.
     * @param dvd The DVD you want to add to the list.
     */
    public void checkOutCustomer(DVD dvd){
        this.customerDVD.add(dvd);
    }

    /**
     * Removes a DVD from the list of movies currently rented by the customer.
     * @param dvd The DVD you want to remove from the list.
     */
    public void checkInCustomer(DVD dvd){
        this.customerDVD.remove(dvd);
    }

    public String toString() {
        return (super.toString() + "\nID Number: " + IDNumber);
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

    public ArrayList<DVD> getCustomerDVD() {
        return customerDVD;
    }

    /**
     * Prints the movies currently rented by the customer to the screen or prints that the customer is not renting any movies.
     */
    public void printRentedMovies(){
        int counter = 0;
        for (int index = 0; index < customerDVD.size(); index++){
            System.out.println(customerDVD.get(index).getMovieName());
            counter++;
        }
        if (counter == 0){
            System.out.println(this.getFirstName() + " is not currently renting any movies.");
        }
    }
}
