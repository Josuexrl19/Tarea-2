
package Domain;


public class Vehicle {
    //declaracion de los atributos
    private String name;
    private int year;
    private float mileage;
    private boolean american;
    private int series;
    //declaracion de los contructores
    public Vehicle() {
        this.name="";  // 40 bytes
        this.year=0;  // 4 bytes
        this.mileage=0; //4 bytes
        this.american=false;   //1 byte
        this.series=0;    //2bytes
    }

    public Vehicle(String name, int year, float mileage, boolean american, int series) {
        this.name = name;
        this.year = year;
        this.mileage = mileage;
        this.american = american;
        this.series = series;
    }
    
    //set y get de los atributos

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public boolean getisAmerican() {
        return american;
    }

    public void setAmerican(boolean american) {
        this.american = american;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
    
    //declaracion del toString

    @Override
    public String toString() {
        return "Vehicle " + "brand: " + name + " , year: " + year + " , mileage: " + mileage + " , american: " + american + " , series: " + series ;
    }
    
    public int sizeINBytes(){
      //reoner la suma en bytes de todos los atributos 
      return 8 + this.getName().length()*2;
              
        
    }
    

}
