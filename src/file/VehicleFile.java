
package File;

import Domain.Vehicle;
import Interfaz.insertVehicle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class VehicleFile {
    
    //declaracion de los atributos
    public RandomAccessFile randomAccessfile;
    private int reQuantity;//cantidad de registros que tiene un archivo
    private int reSize;//el tamaño b6ten bytes de los registros
    private String myFilePath;//ruta del archivo
    
    //constructor
public VehicleFile(File file) throws IOException{
    //guardar la ruta del archivo
    this.myFilePath= file.getPath();
    
    //tamaño maximo de cada registro en el archivo
    this.reSize=140;
    
    //validacion si el archivo existe para java
    if(file.exists() && !file.isFile()){
        throw new IOException(file.getName()+"is an inavailablefile");
    
    }else{
         //crea una nueva instancia del random acces file
         randomAccessfile= new RandomAccessFile(file, "rw");
         
         //indica cuantos registros tiene el archivo
         this.reQuantity = (int)Math.ceil((double)randomAccessfile.length()/(double)this.reSize);
         
    
    }

} // end vehicleFile 

//metodo para insertar un vehicle en una posicion especifica (se necesita ingresar la posicion del vehicle y el nombre)
    public boolean putValue(int position, Vehicle VehicleToInsert) throws IOException{
        //vamos a hacer una pequeña validacion de posicion
        if(position>= 0 && position<= this.reQuantity){
            
            //verificar que el tamaño sea el adecuado
            if(VehicleToInsert.sizeINBytes()> this.reSize){
                System.err.println("1002 - record size is too large ");
                return false;
            }else{
                //escribir en archivo (se necesita colocarse en la posicion adecuada para escribir)
                randomAccessfile.seek(position* this.reSize);
                randomAccessfile.writeUTF(VehicleToInsert.getName());
                randomAccessfile.writeInt(VehicleToInsert.getYear());
                randomAccessfile.writeFloat(VehicleToInsert.getMileage());
                randomAccessfile.writeBoolean(VehicleToInsert.getisAmerican());
                randomAccessfile.writeInt(VehicleToInsert.getSeries());
                return true;
            }
            
        }else{
            System.err.println("1001 - position is out of bounds");
            return false;
            
        }
        
       
    }//end putValue
    
    //metodo para insertar al final del archivo
    public boolean addEndRecord(Vehicle vehicle) throws IOException{
        
        boolean success = putValue(this.reQuantity, vehicle);
        
        if(success ){
            ++this.reQuantity;//aumentar la variable de la cantidad de registros
        }
        return success;
    }
  
    //obtener un estudiante de una posocion especifica 
    public Vehicle getVehicle(int position) throws IOException{
        
        //validar posicion
        if(position>= 0 && position<= this.reQuantity){
            //colocamos el brazo o en el ligar adcuado
            randomAccessfile.seek(position* this.reSize);
            //leer el vehiculo
            Vehicle VehicleTemp = new Vehicle();
            VehicleTemp.setName(randomAccessfile.readUTF());
            VehicleTemp.setYear(randomAccessfile.readInt());
            VehicleTemp.setMileage(randomAccessfile.readFloat());
            VehicleTemp.setAmerican(randomAccessfile.readBoolean());
            VehicleTemp.setSeries(randomAccessfile.readInt());
            
        if(VehicleTemp.getName().equals("deleted")){
            return null;
            
        }else{
            return VehicleTemp;
        }    
            
        }else{
            System.err.println("1003 - position is out of bounds");
            return null ;
        }
        
    }
    
    //metodo para retornar todos los vehiculos que estan dentro del archivo
    
    public ArrayList<Vehicle> getVehicleList() throws IOException{
        //crear una instancia de un array list
        ArrayList<Vehicle> arrayListOfVehicles = new ArrayList<>();
        
        //recorrer el arreglo para insertar en la lista
        for (int i = 0; i < this.reQuantity; i++) {
            Vehicle vehicleTemp = this.getVehicle(i);
            
            //insertar ese vehiculo en la lista
            if(vehicleTemp != null){
            arrayListOfVehicles.add(vehicleTemp);
            }
        }
        return arrayListOfVehicles;
    }//end metodo
    
//    public ArrayList<Vehicle> getVehicleList() throws IOException{
//         File file= new File("./vehicle.dat");
//        ArrayList<Vehicle> arrayOfVehicles= new ArrayList<>();
//        
//        for (int i = 0; i <file.length(); i++) {
//            Vehicle vehicleTemp =getVehicle(i);
//            if(vehicleTemp != null){
//           arrayOfVehicles.add(vehicleTemp);
//            }
//        }
//        return arrayOfVehicles;
//    
//    
//    
//    }
    
//    public String salidita() throws IOException{
//    ArrayList<Vehicle> array= getVehicleList();
//    String salidita="";
//        for (int i = 0; i < array.size(); i++) {
//            salidita+=array.get(i);
//        }
//    return salidita;
//    
//    }
    
    //meodo para borrar un vehicle buscado por nombre
    public boolean deleteRecord (int series) throws IOException{
        
        Vehicle vehicleTemp;
        
        for (int i = 0; i < this.reQuantity; i++) {
            vehicleTemp = this.getVehicle(i) ;
            
            if(vehicleTemp.getSeries()==series){
                vehicleTemp.setName("delete");
                return this.putValue(i, vehicleTemp);
            }//end if
        }//end for
        return false;
        
    }//end metodo
    
    public String listCars() throws IOException{
           File file= new File("./vehicle.dat");
           ArrayList<Vehicle> myArrayVehicles=getVehicleList();
           String listCompleted="Hola";
           for (int i = 0; i <myArrayVehicles.size(); i++) {
               listCompleted+=myArrayVehicles.get(i);
            
        }
           
        return listCompleted;   

}

     
     
}


