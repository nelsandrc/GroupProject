/**
 * @author Andrew Nelson
 * Stores information for a dvd for use by a movie rental company
 */
public class DVD {
    private String movieName;
    private String leadActor;
    private String director;
    private String producer;
    private String productionCompany;
    private int currentInventory;
    private int standardInventory;

    public DVD (String movieName, String  leadActor, String director, String producer, String  productionCompany, int standardInventory){
        this.movieName = movieName;
        this.leadActor = leadActor;
        this.director = director;
        this.producer = producer;
        this.productionCompany = productionCompany;
        this.currentInventory = standardInventory;
        this.standardInventory = standardInventory;
    }

    public DVD(){
        movieName = "Default Title";
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

    public boolean checkOutDVD(){
        if(currentInventory > 0){
            currentInventory--;
            return true;
        }
        else
            return false;
    }

    public boolean checkIn(){
        if(currentInventory < standardInventory){
            currentInventory++;
            return true;
        }
        else
            return false;
    }

    public boolean isRented(){
        return currentInventory < standardInventory;
    }

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
