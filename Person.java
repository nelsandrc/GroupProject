/**
 * @author Andrew Nelson
 * @author Tyler Goldstein
 * Represents a person for a DVD rental company.
 */
public class Person {
    private String firstName;
    private String lastName;

    /**
     * Creates a new person with the given parameters.
     * @param firstName String representing the person's first name.
     * @param lastName String representing the person's last name.
     */

    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Creates a new person with a default name.
     */
    public Person(){
        firstName = "Default First Name";
        lastName = "Default Last Name";
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Person){
            Person temp = (Person) obj;
            return ((this.firstName.equals(temp.getFirstName())) && (this.lastName.equals(temp.getLastName())));
        }
        else
            return false;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
