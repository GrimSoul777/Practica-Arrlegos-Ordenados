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

    static int N = -1;
    static int MAX = 20;
    static LocalDate[] fechas = new LocalDate[MAX];
    static int ciclos = 0;

    //Para que la fecha se muestre en el formato que queremos
    static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");       //FORMATO QUE PIDEN

    public static void main(String[] args) {
        int opcion;
        do {
            menu();
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:
                    inicializar();
                    pausar();
                    break;
                case 2:
                    mostrar();
                    pausar();
                    break;
                case 3:
                    buscar();
                    pausar();
                    break;
                case 4:
                    if (N < MAX - 1) {
                        System.out.println("Ingrese la fecha a insertar (dd/MM/yyyy): ");
                    }
                    insertar();
                    pausar();
                    break;
                case 5:
                    if (N > -1) {
                        System.out.println("Ingrese la fecha a eliminar (dd/MM/yyyy): ");
                    }
                    eliminar();
                    pausar();
                    break;
                case 6:
                    modificar();
                    pausar();
                    break;
                case 7:
                    creditos();
                    pausar();
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    pausar();                                   //SIX SEVEEEEEN
            }
        } while (opcion != 8);
    }

    public static void menu() {
        System.out.println();
        System.out.println("Menu de opciones:");
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

    public static void pausar() {
        //Para que despues de cada resultado de cada operacion le tengas que picar al enter para regresar al menu
        System.out.println();
        System.out.print("Presione ENTER para regresar al menú...");
        sc.nextLine();
    }

    public static void inicializar() {
        System.out.println();
        if (N == -1) {
            System.out.println("El arreglo ya ha sido inicializado");
        } else {
            N = -1;
            System.out.println("Los datos del arreglo han sido borrados completamente");
        }
    }

    public static void mostrar() {
        System.out.println();
        if (N == -1) {
            System.out.println("Arreglo vacio");
            return;
        }

        System.out.println("Contenido del arreglo (" + (N + 1) + " de " + MAX + " localidades usadas):");
        for (int i = 0; i <= N; i++) {
            System.out.println("Localidad [" + i + "] -> " + fechas[i].format(formato));
        }
    }

    public static void insertar() {
        if (N >= MAX - 1) {
            System.out.println();
            System.out.println("El arreglo esta lleno");
            return;
        }

        String fecha_insertar = sc.nextLine();
        LocalDate v1 = LocalDate.parse(fecha_insertar, formato);

        int i = N;
        while (i >= 0 && fechas[i].isAfter(v1)) {
            fechas[i + 1] = fechas[i];
            i = i - 1;
        }
        fechas[i + 1] = v1;
        N = N + 1;

        System.out.println("Fecha insertada en la localidad [" + (i + 1) + "]");
    }

    public static int buscar() {
        System.out.println();
        if (N == -1) {
            System.out.println("Arreglo vacio");
            return -1;
        }

        System.out.print("Ingrese la fecha a buscar (dd/MM/yyyy): ");
        String nueva_fecha = sc.nextLine();
        LocalDate v = LocalDate.parse(nueva_fecha, formato);

        System.out.println("Elija el tipo de búsqueda:");
        System.out.println("1. Lineal optimizada");
        System.out.println("2. Binaria");
        System.out.print("Opción: ");
        int tipo = Integer.parseInt(sc.nextLine());

        if (tipo == 1) {
            return buscarLineal(v);
        } else {
            return buscarBinario(v);
        }
    }

    //busqueda lineal optimizada, lo hice asi para que se pueda usar en eliminar y modificar
    //solo faltaria eliminar
    public static int buscarLineal(LocalDate v) {
        ciclos = 0;
        for (int i = 0; i <= N; i++) {
            ciclos++;
            if (fechas[i].equals(v)) {
                System.out.println("Fecha encontrada en la localidad [" + i + "]");
                System.out.println("Ciclos: " + ciclos);
                return i;
            }
            if (fechas[i].isAfter(v)) {
                System.out.println("Fecha no encontrada");
                System.out.println("Ciclos: " + ciclos);
                return -1;
            }
        }
        System.out.println("Fecha no encontrada");
        System.out.println("Ciclos: " + ciclos);
        return -1;
    }

    public static int buscarBinario(LocalDate v) {
        ciclos = 0;
        int inicio = 0;
        int fin = N;

        while (fin >= inicio) {
            ciclos++;
            int p = (inicio + fin) / 2;
            if (fechas[p].equals(v)) {
                System.out.println("Fecha encontrada en la localidad [" + p + "]");
                System.out.println("Ciclos: " + ciclos);
                return p;
            }

            if (v.isBefore(fechas[p])) {
                fin = p - 1;
            } else {
                inicio = p + 1;
            }
        }
        System.out.println("Fecha no encontrada");
        System.out.println("Ciclos: " + ciclos);
        return -1;
    }

    //Falta esto, pq el profe quiere que se llame al metodo de buscar ya hecho y medio me perdi
    //actualizacion creo que ya le entendi
    //Mentira no entendi, y no quiero modificar el buscar, o maybe hacer otro buscar o no c
    //lol q mal

    //Reciclando metodos como dijo el profe
    public static int eliminar() {
        if (N == -1) {
            System.out.println();
            System.out.println("Arreglo vacio");
            return -1;
        }

        String nueva_fecha = sc.nextLine();
        LocalDate v = LocalDate.parse(nueva_fecha, formato);
        int r = buscarLineal(v);    //tambien se puede usar buscarBinario, but solo pide algun metodo de busqueda ya hecho

        if (r == -1) {
            return r;
        }

        for (int i = r; i <= N - 1; i++) {
            fechas[i] = fechas[i + 1];
        }
        N = N - 1;

        System.out.println("Fecha eliminada de la localidad [" + r + "]");
        return r;
    }

    public static int modificar() {
        System.out.println();
        if (N == -1) {
            System.out.println("Arreglo vacio");
            return -1;
        }
        
        System.out.println("ingrese la fecha a modificar (dd/MM/yyyy): ");
        int r = eliminar();     //se busca y se quita la fecha vieja

        if (r == -1) {
            return -1;
        }

        System.out.println("Ingrese la nueva fecha (dd/MM/yyyy): ");
        insertar();     //pide la nueva fecha y la inserta en su lugar (conserva el orden) como dice luchana

        return r;
    }

    public static void creditos() {
        System.out.println();
        System.out.println("Materia: Estructura de datos");
        System.out.println();
        System.out.println("Integrantes:");
        System.out.println("NOMBRE                              MATRICULA");    //Aqui agreguen su nombre y matricula
        System.out.println("Joshue Angel Regalado Martinez      25420019");
        System.out.println("Omar Emiliano Cuevas Peña           25420131");
        System.out.println("Zoe Valentina Morales García        25420058");
    }
}
//Hecho papusss
//GG CRACKS