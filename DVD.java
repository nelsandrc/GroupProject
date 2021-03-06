/**
 * @author Andrew Nelson
 * @author Tyler Goldstein
 * Stores information for a DVD for use by a movie rental company
 */
public class DVD {
    private String movieName;
    private String leadActor;
    private String director;
    private String producer;
    private String productionCompany;
    private int currentInventory;
    private int standardInventory;

    /**
     * Creates a new movie object with the given parameters.
     * @param movieName String representing the title of the movie.
     * @param leadActor String representing the star of the movie.
     * @param director String representing the director of the movie.
     * @param producer String representing the producer of the movie.
     * @param productionCompany String representing the production company of the movie.
     * @param standardInventory Integer representing how many copies of the movie the store owns in total.
     */
    public DVD (String movieName, String  leadActor, String director, String producer, String  productionCompany, int standardInventory){
        this.movieName = movieName;
        this.leadActor = leadActor;
        this.director = director;
        this.producer = producer;
        this.productionCompany = productionCompany;
        this.currentInventory = standardInventory;
        this.standardInventory = standardInventory;
    }

    /**
     * Creates a new movie with default parameters.
     */
    public DVD(){
        movieName = "Default Title";
        leadActor = "N/A";
        director = "N/A";
        producer = "N/A";
        productionCompany = "N/A";
        currentInventory = standardInventory = 0;
    }
    public DVD(String _movieName){
        movieName =_movieName;
        leadActor = "N/A";
        director = "N/A";
        producer = "N/A";
        productionCompany = "N/A";
        currentInventory = standardInventory = 0;
    }

    @Override
    public String toString() {
        return ("Movie: " + movieName + "\nStar: " + leadActor + "\nDirector: " + director + "\nProducer: " + producer + "\nProduction Company: " + productionCompany + "\n");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DVD){
            DVD temp = (DVD) obj;
            return this.movieName.equals(((DVD) obj).movieName);
        }
        else
            return false;
    }


    /**
     * Removes one copy of the DVD from the current store inventory
     * @return Returns true if the system can succesfully check out the DVD.
     */
    public boolean checkOutDVD(){
        if(currentInventory > 0){
            currentInventory--;
            return true;
        }
        else
            return false;
    }

    /**
     * Returns one copy of the DVD to the current store inventory
     * @return Returns true if the system can succesfully check in the DVD.
     */
    public boolean checkInDVD(){
        if(currentInventory < standardInventory){
            currentInventory++;
            return true;
        }
        else
            return false;
    }

    /**
     * Checks if the DVD is currently rented by anyone
     * @return Returns true if the DVD is checked out.
     */
    public boolean isRented(){
        return currentInventory < standardInventory;
    }

    /**
     * Checks if there any copies of the DVD currently in stock.
     * @return Returns true if there is at least one copy of the DVD in stock.
     */
    public boolean isInStock(){
        return currentInventory > 0 && standardInventory > 0;
    }

    public int getCurrentInventory() {
        return currentInventory;
    }

    public int getStandardInventory() {
        return standardInventory;
    }

    public String getDirector() {
        return director;
    }

    public String getLeadActor() {
        return leadActor;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getProducer() {
        return producer;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setCurrentInventory(int currentInventory) {
        this.currentInventory = currentInventory;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setLeadActor(String leadActor) {
        this.leadActor = leadActor;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public void setStandardInventory(int standardInventory) {
        this.standardInventory = standardInventory;
    }

}
