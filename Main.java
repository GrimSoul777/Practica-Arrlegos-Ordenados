import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Scanner;

//Esqueleto que hizo el emi prepa, buuuuuuuuuuuuuuu
//FORMATO DE FECHA: dd/MM/yyyy

public class Main {
    static Scanner sc = new Scanner(System.in);
    static int N=0;
    static int MAX=20;
    static LocalDate[] fechas = new LocalDate[MAX];
    static int ciclos = 0; //ciclos que tomo la ultima busqueda
    //variable local de la fecha, para que se pueda usar en todos los metodos
    static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy").withResolverStyle(ResolverStyle.STRICT);
    
    public static void main(String[] args) {
        int opcion;
        do {
            menu();
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer de entrada
            switch (opcion) {
                case 1:
                    //Inicializar/Borrar arreglo
                    inicializar();
                    break;
                case 2:
                    // Mostrar fechas guardadas
                    mostrar();
                    break;
                case 3:
                    // Buscar fecha
                    buscar();
                    break;
                case 4:
                    // Insertar fecha
                    insertar();
                    break;
                case 5:
                    // Eliminar fecha
                    eliminar();
                    break;
                case 6:
                    // Modificar fecha
                    modificar();
                    break;
                case 7:
                    // Creditos
                    creditos();
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        } while (opcion != 8);
    }

    public static void menu() {
        System.out.println();
        System.out.println("1. Inicializar/Borrar arreglo");
        System.out.println("2. Mostrar fechas guardadas");
        System.out.println("3. Buscar fecha");
        System.out.println("4. Insertar fecha");
        System.out.println("5. Eliminar fecha");
        System.out.println("6. Modificar fecha");
        System.out.println("7. Creditos");
        System.out.println("8. Salir");
        System.out.print("Ingrese una opcion: ");
    }

    public static void inicializar() {
        System.out.println();
        //FORMATO QUE PIDEN
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (N==-1) {
        System.out.println("El arreglo ya ha sido inicializado");
        } else {
            N=-1;
            System.out.println("Los datos del arreglo han sido borrados completamente");
        }
    }

    public static void mostrar() {
        System.out.println();
        if (N==-1) {
            System.out.println("Arreglo vacio");
        } else {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            int pos=0;
            for (int i = 0; i < N; i++) {
                pos=pos+1;
                System.out.println("[" + pos + "] " + fechas[i].format(formato));
            }
        }
    }   

    public static void buscar() {
        System.out.println();
        if (N==-1) {
            System.out.println("Arreglo vacio");
        } else {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Que fecha desea buscar (dd/MM/yyyy)?");
            String fecha_buscar = sc.nextLine();
            LocalDate buscarFecha = LocalDate.parse(fecha_buscar, formato);
            System.out.println();

            System.out.println("Que busqueda desea usar?");
            System.out.println("1. Busqueda lineal optimizada");
            System.out.println("2. Busqueda binaria");
            System.out.println("Ingrese su opcion:");
            int op = sc.nextInt();
            sc.nextLine();

            System.out.println();
            int ciclos=0;
            int pos=0;
            boolean encontrado = false;
            switch (op) {
                case 1:
                    //Busqueda lineal optimizada
                    pos=0;
                    ciclos=0;
                    encontrado=false;
                    for (int i=0; i<N; i++) {
                        ciclos++;
                        pos++;
                        if(buscarFecha.equals(fechas[i])) {
                            System.out.println("Fecha encontrada");
                            System.out.println("["+pos+"] "+fechas[i].format(formato));
                            encontrado=true;
                            break;
                        }
                        if (fechas[i].isAfter(buscarFecha)) {
                            break;
                        }
                    }

                    if (encontrado==false) {
                    System.out.println("No existe esa fecha en el arreglo");
                    }

                    System.out.println();
                    System.out.println("Ciclos hechos: "+ciclos);
                    break;

                case 2:
                    //Busqueda binaria
                    int inicio = 0;
                    int fin = N-1;
                    ciclos=0;
                    pos=0;
                    encontrado=false;
                    while (fin >= inicio) {
                        ciclos++;
                        int p = (inicio + fin)/2;

                        if(buscarFecha.equals(fechas[p])) {
                            pos=p+1;
                            System.out.println("Fecha encontrada");
                            System.out.println("["+pos+"] "+fechas[p].format(formato));
                            encontrado = true;
                            break;
                        }
                        if(buscarFecha.isBefore(fechas[p])) {
                            fin = p-1;
                        } else {
                            inicio = p+1;
                        }
                    }

                    if (encontrado==false) {
                    System.out.println("No existe esa fecha en el arreglo");
                    }

                    System.out.println();
                    System.out.println("Ciclos hechos: "+ciclos);
                    break;
            
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }

    public static void insertar() {
        if (N == MAX-1) {
            System.out.println("El arreglo está lleno.");
            return;
        }
        // Capturar fecha
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.print("Ingrese la nueva fecha (dd/MM/yyyy): ");
        String fecha_nueva = sc.nextLine();

        LocalDate nuevaFecha = LocalDate.parse(fecha_nueva, formato);

        int posicion = 0;
        while (posicion < N && fechas[posicion].isBefore(nuevaFecha)) {
        posicion++;
        }

        for (int i = N - 1; i >= posicion; i--) {
            fechas[i + 1] = fechas[i];
        }
        fechas[posicion] = nuevaFecha;
        N++;

        System.out.println("Fecha insertada");
    }

    //busqueda lineal optimizada, lo hice asi para que se pueda usar en eliminar y modificar
    //solo faltaria eliminar
    public static int buscarLineal(LocalDate v) {
        ciclos = 0;
        for (int i = 0; i <= N; i++) {
            ciclos++;
            if (v.isEqual(fechas[i])) {
                return i;
            }
            if (fechas[i].isAfter(v)) {
                return -1;
            }
        }
        return -1;
    }

    //elimina una fecha usando una busqueda ya hecha sin pedir datos ni imprimir
    public static int eliminarFecha(LocalDate v) {
        int r = buscarLineal(v); //tambien se puede usar buscarBinario, but solo pide algun metodo de busqueda ya hecho
        if (r == -1) {
            return r;
        }
        for (int i = r; i < N; i++) {
            fechas[i] = fechas[i + 1];
        }
        fechas[N] = null;
        N = N - 1;
        return r;
    }
    
    
    public static LocalDate leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            try {
                return LocalDate.parse(texto, formato);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha invalida, use el formato dd/MM/yyyy con una fecha real");
            }
        }
    }
    public static int[] buscarlineal2(LocalDate fecha){
        int ciclos = 0;
            for (int i = 0; i < N; i++) {
                ciclos++;
                if (fechas[i].isEqual(fecha)) {
                    return new int[] { i, ciclos };
                }
                if (fechas[i].isAfter(fecha)) {
                    break;
                }
            }
            return new int[] { -1, ciclos };
    }

    //Falta esto, pq el profe quiere que se llame al metodo de buscar ya hecho y medio me perdi
    //actualizacion creo que ya le entendi
    //Mentira no entendi, y no quiero modificar el buscar, o maybe hacer otro buscar o no c
    //lol q mal
    public static void eliminar() {
        if(N==0) {
            System.out.println("Arreglo vacio");
            return;

        } 
        LocalDate fecha = leerFecha("Fecha a eliminar (dd/MM/yyyy): ");
        int pos = buscarlineal2(fecha)[0];
        if (pos == -1) {
        System.out.println("No se pudo localizar la fecha " + fecha.format(formato) + "; no procede la operacion.");
        } else {
        eliminarFecha(fecha);
        System.out.println("Fecha " + fecha.format(formato) + " eliminada de la localidad " + pos + ".");
    }
}


    public static void modificar() {
        if (N==0) {
            System.out.println("Arreglo vacio");
        } else {
            LocalDate v = leerFecha("Ingrese la fecha a modificar (dd/MM/yyyy): ");
            int r = eliminarFecha(v); //se busca y se quita la fecha vieja
            if (r == -1) {
                System.out.println("No se pudo localizar la fecha, no procede la operacion");
            } else {
                insertar(); //pide la nueva fecha y la inserta en su lugar (conserva el orden) como dice luchana
            }
        }
    }

    public static void creditos() {
        System.out.println();
        System.out.println("Materia:");
        System.out.println("Estructura de datos");
        System.out.println("Integrantes:");
        System.out.println("NOMBRE                              MATRICULA");  //Aqui metan su nombre y matricula
        System.out.println("Joshue Angel Regalado Martinez      25420019");
        System.out.println("Omar Emiliano Cuevas Peña           25420131");
    }
}