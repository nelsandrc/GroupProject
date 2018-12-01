public class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

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
