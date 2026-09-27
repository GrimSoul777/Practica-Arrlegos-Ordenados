import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//Esqueleto que hizo el emi prepa, buuuuuuuuuuuuuuu
public class Main {

    public static DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int n;
    private LocalDate[] arreglo;

    public Procesos_Fechas(int capacidad) {
        arreglo = new LocalDate[capacidad];
        n = 0;
    }

    //Inicializar / Borrar arreglo
    public void Iniciar(){
        n=0;
    }
    //Mostrar Arreglo
    public void Mostrar(){
    //Proceso mostrar arreglo
    }
    //BusquedaLineal
    public int[] BuscarL(LocalDate fecha){
        //Proceso busqueda lineal
        return new int[1]; //return solo para que no marque rojo
    }
    //Busqueda Binaria
    public int[] BuscarB(LocalDate fecha){
        //Proceso busaqueda binaria
        return new int[1]; //return solo para que no marque rojo
    }
    //Insertar
    public int Insertar(LocalDate fecha){
    //Proceso insertar fecha
    return -1;//return solo para que no marque rojo 
    }
    //Eliminar
    public void Eliminar(LocalDate fecha){
    //Proceso eliminar fecha
    }

}