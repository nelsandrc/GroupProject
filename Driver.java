/*
 * @author Andrew Nelson
 * @author Tyler Goldstein
 *
 * @brief Driver class for the DVD store application. This class utilizes the Person, Customer, and DVD classes.
 * It contains functions that manipulate array lists which contain the DVDs that customers check in and out.
 *
 * @date 2018-12-06
 */

import java.util.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<DVD> storeDVDs = new ArrayList<DVD>();
        Scanner choiceInput = new Scanner(System.in);
        boolean menu = true;
        int userChoice = -1;

        loadDVDList(storeDVDs);
        loadCustomerList(customers);

        System.out.println("Welcome to the Stout DVD Rental Service.");

            do {
                System.out.print("Please select one of the following:\n"
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

                String tempInput = choiceInput.nextLine();
                try {
                    userChoice= Integer.parseInt(tempInput);
                }
                catch(NumberFormatException nfe) {
                    userChoice = -1;
                }
                switch (userChoice) {
                    case -1:
                        System.out.println("Please enter an integer.");
                        break;
                    case 1:
                        hasDVD(storeDVDs);
                        break;
                    case 2:
                        checkOut(storeDVDs, customers);
                        break;
                    case 3:
                        checkIn(storeDVDs, customers);
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
            } while (menu);
        System.out.println("Thank you for renting from us today!");
        choiceInput.close();
    }

    /**
     * Reads information from the DVD text file and initializes the store's inventory.
     * @param DVDList The array list of DVDs in the store
     * @throws FileNotFoundException Throws exception if the DVD information file doesn't exist
     */
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
            //System.out.println(temp + " " + temp.getCurrentInventory());
        }while(DVDfileInput.hasNext());
        DVDfileInput.close();
    }

    /**
     * Reads information from the customer text file and intializes the store's customer list.
     * @param customerList The array list of the customers in the store's system.
     * @throws FileNotFoundException Throws exception if the customfer information file doesn't exist.
     */
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
           // System.out.println(temp);
        }while (customerFileInput.hasNext());
        customerFileInput.close();
    }

    /**
     * Checks if the store carries a DVD that matches an input title.
     * @param DVDList The array list of DVDs in the store
     */
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
    }

    /**
     * Checks out a DVD to a customer and reduces currrent stock of that DVD by one,
     * @param DVDlist The array list of DVDs in the store
     * @param customerList The array list of the customers in the store's system.
     */
    public static void checkOut(ArrayList<DVD> DVDlist, ArrayList<Customer> customerList){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter the name of the DVD you want to rent: ");
        String wantedMovieName = keyboard.nextLine();


        System.out.println("Enter your ID number: ");
        int userID = keyboard.nextInt();
        keyboard.nextLine();


        if(isCustomer(customerList, userID) && (hasDVDInStore(DVDlist, wantedMovieName)))//If both customer and movie exist in system
        {
            DVD workingDVD = new DVD();
            Customer workingCustomer = new Customer();
            workingDVD = getDVD(DVDlist, wantedMovieName);
            workingCustomer = getCustomer(customerList, userID);


            if(workingCustomer.getCustomerDVD().contains(workingDVD)){
                System.out.println("Sorry, " + workingCustomer.getFirstName() + ", you can only have one copy of each movie. You seem to already have a copy of " + wantedMovieName);
            }
            else {
                if(workingDVD.checkOutDVD()){
                    workingCustomer.checkOutCustomer(workingDVD);
                    System.out.println("You have succesfully checked out " + wantedMovieName + ". Enjoy the movie!");
                }
                else
                    System.out.println( wantedMovieName + " is currently out of stock. Please come back at another time.");

            }

        }
        else if(!isCustomer(customerList, userID) && (hasDVDInStore(DVDlist, wantedMovieName))){//If customer does not exist in system but movie does exist.
            System.out.println("No user with ID number '" + userID + "' was found in our system. Please verify you typed your user ID number correctly.");
        }
        else if(isCustomer(customerList, userID) && !(hasDVDInStore(DVDlist, wantedMovieName))){//If customer exists but movie does not exist.
            System.out.println("Sorry, " + wantedMovieName + " was not found in our system. Please verify you typed the name correctly.");
        }
        else //If neither customer nor movie exist
        {
            System.out.println("Movie and customer ID not found. Please double check your input.");
        }
    }

    /**
     * Checks in a DVD from a customer and increases the current sttock of that DVD by one.
     * @param DVDlist The array list of DVDs in the store
     * @param customerList The array list of the customers in the store's system.
     */
    public static void checkIn(ArrayList<DVD> DVDlist, ArrayList<Customer> customerList){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter the name of the DVD you want to return: ");
        String wantedMovieName = keyboard.nextLine();


        System.out.println("Enter your ID number: ");
        int userID = keyboard.nextInt();
        keyboard.nextLine();


        if(isCustomer(customerList, userID) && (hasDVDInStore(DVDlist, wantedMovieName)))//If both customer and movie exist in system
        {
            DVD workingDVD =  getDVD(DVDlist, wantedMovieName);
            Customer workingCustomer = getCustomer(customerList, userID);


            if(workingCustomer.getCustomerDVD().contains(workingDVD)&&workingDVD.checkInDVD()) {
                workingCustomer.checkInCustomer(workingDVD);
                System.out.println("You have succesfully returned " + wantedMovieName + ". We hoped you liked it as much as we did!");
            }
            else {
                System.out.println("Sorry, " + workingCustomer.getFirstName() + ", you aren't currently renting " + workingDVD.getMovieName());
            }

        }
        else if(!isCustomer(customerList, userID) && (hasDVDInStore(DVDlist, wantedMovieName))){//If customer does not exist in system but movie does exist.
            System.out.println("No user with ID number '" + userID + "' was found in our system. Please verify you typed your user ID number correctly.");
        }
        else if(isCustomer(customerList, userID) && !(hasDVDInStore(DVDlist, wantedMovieName))){//If customer exists but movie does not exist.
            System.out.println("Sorry, " + wantedMovieName + " was not found in our system. Please verify you typed the name correctly.");
        }
        else //If neither customer nor movie exist
        {
            System.out.println("Movie and customer ID not found. Please double check your input.");
        }

    }

    /**
     * Prints how many copies of a DVD the store currently has.
     * @param DVDList The array list of DVDs in the store
     */
    public static void isInStock(ArrayList<DVD> DVDList){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the name of the DVD you want see in stock: ");
        String wantedMovieName = keyboard.nextLine();
        boolean inStock = false;
        int currentCount = 0;

        for(int index = 0; index < DVDList.size() - 1; index++){
            DVD curentDVD = DVDList.get(index);
            if(curentDVD.getMovieName().equals(wantedMovieName)) {
                inStock = curentDVD.isInStock();
                currentCount = curentDVD.getCurrentInventory();
            }
        }
        if(inStock)
            System.out.println(wantedMovieName + " has " + currentCount + " copies in stock right now.");
        else
            System.out.println(wantedMovieName + " is not in stock right now or is not carried by this store.");

    }

    /**
     * Prints only the names of all movies in the store.
     * @param DVDList The array list of DVDs in the store
     */
    public static void printMovieNames(ArrayList<DVD> DVDList){
        for(int index = 0; index < DVDList.size(); index++){
            System.out.println(DVDList.get(index).getMovieName());
        }
    }

    /**
     * Prints the full information of each DVD in the store.
     * @param DVDList The array list of DVDs in the store
     */
    public static void printMovies(ArrayList<DVD> DVDList){
        for(int index = 0; index < DVDList.size(); index++){
            System.out.println(DVDList.get(index));
        }
    }

    /**
     * Prints the name and ID number of each customer in the system.
     * @param customerList The array list of the customers in the store's system.
     */
    public static void printCustomerList(ArrayList<Customer> customerList){
        for(int index = 0; index < customerList.size(); index++){
            System.out.println(customerList.get(index));
        }
    }

    /**
     * Prints the title of each movie a particular customer currently has checked out.
     * @param customerList The array list of the customers in the store's system.
     */
    public static void printCustomerRentals(ArrayList<Customer> customerList){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter your ID number: ");
        int userID = keyboard.nextInt();
        keyboard.nextLine();

        if(isCustomer(customerList, userID)){
            Customer workingCustomer  = getCustomer(customerList,userID);
            workingCustomer.printRentedMovies();
        }
        else {
            System.out.println("No user with ID number '" + userID + "was found in our system. Please verify you typed your user ID number correctly.");
        }
    }

    /**
     * Prints the title of each movie that is checked out from the store by anyone.
     * @param DVDList The array list of DVDs in the store
     */
    public static void printRentedMovies(ArrayList<DVD> DVDList){
        int counter = 0;
        System.out.println("The following movies are currently rented:\n");
        for(int index = 0; index < DVDList.size(); index++){
            DVD currentDVD = DVDList.get(index);
               if (currentDVD.isRented()){
                   counter++;
                   System.out.println(currentDVD.getMovieName());
               }
        }
        if(counter == 0){
            System.out.println("None");
        }
    }

    /**
     * Boolean method that checks if the store has a DVD in the system.
     * @param DVDList The array list of DVDs in the store
     * @param wantedMovieName
     * @return Returns true if the store has the DVD in the system and false if it does not.
     */
    public static boolean hasDVDInStore(ArrayList<DVD> DVDList, String wantedMovieName){
        for(int index = 0; index < DVDList.size(); index++){
            if(DVDList.get(index).getMovieName().equals(wantedMovieName))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Boolean method that checks if the store has a particular customer in the system.
     * @param customerList The array list of the customers in the store's system.
     * @param userID Integer representing the ID number of the customer.
     * @return Returns true if the store has the customer in the system and false if it does not.
     */
    public static boolean isCustomer(ArrayList<Customer> customerList ,int userID){
        for(int index = 0; index < customerList.size(); index++){
            if (customerList.get(index).getIDNumber() == userID) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a customer from the store's system that matches the given customer ID.
     * @param customerList The array list of the customers in the store's system.
     * @param userID Integer representing the ID of the customer.
     * @return Customer matching the ID number or default customer if not found.
     */
    public static Customer getCustomer(ArrayList<Customer> customerList ,int userID){
        for(int index = 0; index < customerList.size(); index++){
            if (customerList.get(index).getIDNumber() == userID) {
                return customerList.get(index);
            }
        }
        return new Customer();
    }

    /**
     * Gets a DVD from the store's inventory that matches given movie name.
     * @param DVDList The array list of DVDs in the store
     * @param wantedMovieName String representing the title of the movie
     * @return DVD matching the wanted movie name or default DVD if not found.
     */
    public static DVD getDVD(ArrayList<DVD> DVDList, String wantedMovieName){
        for(int index = 0; index < DVDList.size(); index++){
            if(DVDList.get(index).getMovieName().equals(wantedMovieName))
            {
                return DVDList.get(index);
            }
        }
        return new DVD();
    }

}