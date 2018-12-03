import java.util.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<DVD> storeDVDs = new ArrayList<DVD>();
        Scanner choiceInput = new Scanner(System.in);
        boolean menu = true;

        loadDVDList(storeDVDs);
        loadCustomerList(customers);

        do {
            System.out.print("Select one of the following:\n"
                    + "1. To check whether the store carries a particular DVD\n"
                    + "2. To check out a DVD\n"
                    + "3. To check in a DVD\n"
                    + "4. To check whether a particular DVD is in stock\n"
                    + "5. To print only the titles of all DVDs\n"
                    + "6. To print a list of all the DVDs\n"
                    + "7. Print a list of customers\n"
                    + "8. Print a list of DVDs rented by a customer\n"
                    + "9. Print a list of all rented DVDs\n"
                    + "10. to exit\n");

            int choice = choiceInput.nextInt();

            switch (choice){
                case 1:
                    hasDVD(storeDVDs);
                    break;
                case 2:
                    checkOut(storeDVDs);
                    break;
                case 3:
                    checkIn(storeDVDs);
                    break;
                case 4:
                    isInStock(storeDVDs);
                    break;
                case 5:
                    printMovieNames(storeDVDs);
                    break;
                case 6:
                    printMovies(storeDVDs);
                    break;
                case 7:
                    printCustomerList(customers);
                    break;
                case 8:
                    printCustomerRentals(customers);
                    break;
                case 9:
                    printRentedMovies(storeDVDs);
                    break;
                case 10:
                    menu = false;
                    break;
                default:
                    System.out.println("Invalid entry. Please try again.");
                    break;
            }
        }while(menu);

        System.out.println("Thank you for renting from us today!");
        choiceInput.close();
    }

    public static void loadDVDList(ArrayList<DVD> DVDList) throws FileNotFoundException{
        File DVDFile = new File("movies.txt");
        Scanner DVDfileInput = new Scanner(DVDFile);

        do{
            String tempMovieName = DVDfileInput.nextLine();
            String tempMovieStar = DVDfileInput.nextLine();
            String tempMovieDirector = DVDfileInput.nextLine();
            String tempMovieProducer = DVDfileInput.nextLine();
            String tempMovieProductionCompany = DVDfileInput.nextLine();
            int tempDefaultINventory = DVDfileInput.nextInt();
            DVDfileInput.nextLine();
            DVD temp = new DVD(tempMovieName, tempMovieStar, tempMovieDirector, tempMovieProducer, tempMovieProductionCompany, tempDefaultINventory);
            DVDList.add(temp);
            System.out.println(temp);
        }while(DVDfileInput.hasNext());
        DVDfileInput.close();
    }

    public static void loadCustomerList(ArrayList<Customer> customerList) throws FileNotFoundException{
        File CustomerFile = new File("customers.txt");
        Scanner customerFileInput = new Scanner(CustomerFile);

        do{
            String tempFirstName = customerFileInput.nextLine();
            String tempLastName = customerFileInput.nextLine();
            int tempIDNumber = customerFileInput.nextInt();
            customerFileInput.nextLine();

            Customer temp = new Customer(tempFirstName, tempLastName, tempIDNumber);
            customerList.add(temp);
            System.out.println(temp);
        }while (customerFileInput.hasNext());
        customerFileInput.close();
    }

    public static void hasDVD(ArrayList<DVD> DVDList){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the name of the DVD you want to look for: ");
        String wantedMovieName = keyboard.nextLine();
        boolean hasMovie = false;

        for(int index = 0; index < DVDList.size(); index++){
            if(DVDList.get(index).getMovieName().equals(wantedMovieName))
               hasMovie = true;
        }
        if(hasMovie)
            System.out.println("The store carries " + wantedMovieName);
        else
            System.out.println("The store does not carry " + wantedMovieName);
        keyboard.close();
    }

    public static void checkOut(ArrayList<DVD> DVDlist, ArrayList<Customer> customerList){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the name of the DVD you want to rent: ");
        String wantedMovieName = keyboard.nextLine();
        System.out.println("Enter your ID number: ");
        int userID = keyboard.nextInt();
        keyboard.nextLine();

        if(isCustomer(customerList, userID) && (hasDVD(DVDlist, wantedMovieName)))//If both customer and movie exist in system
        {
            
        }
        else if(!isCustomer(customerList, userID) && (hasDVD(DVDlist, wantedMovieName))){//If customer does not exist in system but movie does exist.
            System.out.println("No user with ID number '" + userID + "' was found in our system. Please verify you typed your user ID number correctly.");
        }
        else if(isCustomer(customerList, userID) && !(hasDVD(DVDlist, wantedMovieName))){//If customer exists but movie does not exist.
            System.out.println(wantedMovieName + " was not found in our system. Please verify you typed the name correctly.");
        }
        else //If neither customer nor movie exist
        {
            System.out.println("Movie and customer ID not found. Please double check your input.");
        }



    }

    public static void checkIn(ArrayList<DVD> DVDlist){

    }

    public static void isInStock(ArrayList<DVD> DVDList){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the name of the DVD you want see in stock: ");
        String wantedMovieName = keyboard.nextLine();
        boolean inStock = false;
        int currentCount = 0;

        for(int index = 0; index < DVDList.size(); index++){
            DVD curentDVD = DVDList.get(index);
            if(curentDVD.getMovieName().equals(wantedMovieName)) {
                inStock = curentDVD.isInStock();
                currentCount = curentDVD.getCurrentInventory();
            }
        }
        if(inStock)
            System.out.println(wantedMovieName + " has" + currentCount + " copies in stock right now.");
        else
            System.out.println(wantedMovieName + " is not in stock right now or is not carried by this store.");
        keyboard.close();
    }

    public static void printMovieNames(ArrayList<DVD> DVDList){
        for(int index = 0; index < DVDList.size(); index++){
            System.out.println(DVDList.get(index).getMovieName());
        }
    }

    public static void printMovies(ArrayList<DVD> DVDList){
        for(int index = 0; index < DVDList.size(); index++){
            System.out.println(DVDList.get(index));
        }
    }

    public static void printCustomerList(ArrayList<Customer> customerList){
        for(int index = 0; index < customerList.size(); index++){
            System.out.println(customerList.get(index));
        }
    }

    public static void printCustomerRentals(ArrayList<Customer> customerList){

    }

    public static void printRentedMovies(ArrayList<DVD> DVDList){
        System.out.println("The following movies are currently rented:\n");
        for(int index = 0; index < DVDList.size(); index++){
            DVD currentDVD = DVDList.get(index);
           if (currentDVD.isRented()){
               System.out.println(currentDVD.getMovieName());
           }
        }
    }

    public static boolean hasDVD(ArrayList<DVD> DVDList, String wantedMovieName){
        for(int index = 0; index < DVDList.size(); index++){
            if(DVDList.get(index).getMovieName().equals(wantedMovieName))
               return true;
        }
        return false;
    }

    public boolean isCustomer(ArrayList<Customer> customerList ,int userID){
        for(int index = 0; index < customerList.size(); index++){
            if (customerList.get(index).getIDNumber() == userID)
                return true;
        }
        return false;
    }


}