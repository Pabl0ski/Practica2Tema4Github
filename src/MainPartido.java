import java.util.Scanner;
import java.util.PriorityQueue;


public class MainPartido {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Partido[] partidos= {
                //Jornada liga 1
                new Partido("Guadalcacín", "Leganés", 1, 7, false, true),
                new Partido("Roldán", "Mostoles", 2, 2, false, true),
                new Partido("Burela Sala", "Zaragoza", 2, 0, false, true),
                new Partido("Penya Viaxes Amarelle", "Amarelle", 1, 0, false, true),
                new Partido("Femisport", "Poio", 2, 2, false, true),
                new Partido("Majadahonda", "Universidad De Alicante", 1, 6, false, true),
                new Partido("Futsi Atletico", "UCAM Pozo Murcia", 8, 0, false, true),
                new Partido("Alcorcón", "Ourense Envialia", 3, 4, false, true),
                //Jornada liga 2
                new Partido("La Bocatelia Futsal", "RTV Roldán", 3, 1, false, true),
                new Partido("Guadalcin", "Leganés", 0, 2, false, true),
                new Partido("Majadahonda", "Futsi Navalcarnero", 3, 3, false, true),
                new Partido("Melilla", "Alcorcon", 4, 3, false, true),
                new Partido("Poio", "Mostoles", 0, 0, false, true),
                new Partido("UCAM El Pozo", "Universidad de Alicante", 1, 3, false, true),
                new Partido("Ourense Envialia", "Amarelle", 0, 0, false, true),
                new Partido("Femisport", "Zaragoza", 5,1, false, true),
                //Jornadas n

                //Jornada de copa 1
                new Partido("RTV Roldan", "La Bocatelia Futsal", 1, 2, true, false)
                new Partido("Alcorcon", "Mahadahonda", 2, 1, true, false)
                new Partido("Leganés", "Zaragoza", 3, 3, true, false
                //Agregar mas partidos si es necesario
        };
        int opcion;
        int jornada=1;
        do {
            System.out.println("Menú:");
            System.out.println("1) Ver todos los partidos de liga");
            System.out.println("2) Ver partidos empate");
            System.out.println("3) Ver 5 partidos con mayor goleada");
            System.out.println("4) Ver partidos por jornada");
            System.out.println("5) Ver todos los partidos de copa");
            System.out.println("6) Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    // Mostrar los resultados de los partidos
                    mostrarResultadosLiga(partidos);
                    // Calcular estadísticas
                    calcularEstadisticas(partidos);
                    break;
                case 2:
                    mostrarPartidosEmpate(partidos);
                    break;
                case 3:
                    mostrarTop5MayorGoleada(partidos);
                    break;
                case 4:
                    seleccionarJornada(partidos);
                    //mostrarResultadosPorJornadas(partidos, jornada);
                    //jornada++;
                    break;
                case 5:
                    identificarPartidosDeCopa(partidos);
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción incorrecta. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 6);


    }

    // Método para mostrar los resultados de los partidos
    public static void mostrarResultadosLiga(Partido[] partidos) {
        for (Partido partido : partidos) {
            if (partido.isEsLiga()) {
                String codigoPartido = partido.getEquipoLocal().substring(0, 1).toLowerCase() + partido.getGolesLocal() +
                        "." + partido.getGolesVisitante() + partido.getEquipoVisitante().substring(0, 1).toUpperCase();
                System.out.println(partido.getEquipoLocal() + " " + partido.getGolesLocal() + " - " +
                        partido.getGolesVisitante() + " " + partido.getEquipoVisitante() + " - Código del partido: " + codigoPartido);
                System.out.println();
            }
        }
    }

    public static void mostrarPartidosEmpate(Partido[] partidos) {
        System.out.println("Partidos que han terminado en empate:");
        for (Partido partido : partidos) {
            if (partido.getGolesLocal() == partido.getGolesVisitante()) {
                String codigoPartido = partido.getEquipoLocal().substring(0, 1).toLowerCase() + partido.getGolesLocal() +
                        "." + partido.getGolesVisitante() + partido.getEquipoVisitante().substring(0, 1).toUpperCase();
                System.out.println(partido.getEquipoLocal() + " " + partido.getGolesLocal() + " - " +
                        partido.getGolesVisitante() + " " + partido.getEquipoVisitante() + " - Código del partido: " + codigoPartido);
                System.out.println();
            }
        }
    }

    public static void mostrarTop5MayorGoleada(Partido[] partidos) {
        PriorityQueue<Partido> maxHeap = new PriorityQueue<>((p1, p2) ->
                Math.abs(p2.getGolesLocal() - p2.getGolesVisitante()) - Math.abs(p1.getGolesLocal() - p1.getGolesVisitante()));

        for (Partido partido : partidos) {
            maxHeap.offer(partido);
        }

        System.out.println("Top 5 partidos con mayor goleada:");
        int count = 0;
        while (!maxHeap.isEmpty() && count < 5) {
            Partido partido = maxHeap.poll();
            String codigoPartido = partido.getEquipoLocal().substring(0, 1).toLowerCase() + partido.getGolesLocal() +
                    "." + partido.getGolesVisitante() + partido.getEquipoVisitante().substring(0, 1).toUpperCase();
            System.out.println(partido.getEquipoLocal() + " " + partido.getGolesLocal() + " - " +
                    partido.getGolesVisitante() + " " + partido.getEquipoVisitante() + " - Código del partido: " + codigoPartido);
            count++;
        }
    }

    // Método para calcular estadísticas
    public static void calcularEstadisticas(Partido[] partidos) {
        int victoriasLocal = 0;
        int victoriasVisitante = 0;
        int empates = 0;

        for (Partido partido : partidos) {
            if (partido.getGolesLocal() > partido.getGolesVisitante()) {
                victoriasLocal++;
            } else if (partido.getGolesLocal() < partido.getGolesVisitante()) {
                victoriasVisitante++;
            } else {
                empates++;
            }
        }

        System.out.println("Número de partidos con victoria del local: " + victoriasLocal);
        System.out.println("Número de partidos con victoria del visitante: " + victoriasVisitante);
        System.out.println("Número de empates: " + empates);
    }

    public static void mostrarResultadosPorJornadas(Partido[] partidos, int jornada) {
        int inicio = (jornada - 1) * 8;
        int fin = Math.min(jornada * 8, partidos.length);

        System.out.println("Jornada " + jornada + ":");
        for (int i = inicio; i < fin; i++) {
            Partido partido = partidos[i];
            String codigoPartido = partido.getEquipoLocal().substring(0, 1).toLowerCase() + partido.getGolesLocal() +
                    "." + partido.getGolesVisitante() + partido.getEquipoVisitante().substring(0, 1).toUpperCase();
            System.out.println(partido.getEquipoLocal() + " " + partido.getGolesLocal() + " - " +
                    partido.getGolesVisitante() + " " + partido.getEquipoVisitante() + " - Código del partido: " + codigoPartido);
            System.out.println();
        }
    }


    public static void seleccionarJornada(Partido[] partidos) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el número de jornada que desea ver: ");
        int jornadaSeleccionada = sc.nextInt();
        mostrarResultadosPorJornadas(partidos, jornadaSeleccionada);
    }

    public static void identificarPartidosDeCopa(Partido[] partidos) {
        System.out.println("Partidos de Copa:");
        for (Partido partido : partidos) {
            if (partido.isEsCopa()) {
                String codigoPartido = partido.getEquipoLocal().substring(0, 1).toLowerCase() + partido.getGolesLocal() +
                        "." + partido.getGolesVisitante() + partido.getEquipoVisitante().substring(0, 1).toUpperCase();
                System.out.println(partido.getEquipoLocal() + " " + partido.getGolesLocal() + " - " +
                        partido.getGolesVisitante() + " " + partido.getEquipoVisitante() + " - Código del partido: " + codigoPartido);
                System.out.println();
            }
        }
    }

}
