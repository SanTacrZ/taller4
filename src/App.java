import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class App {

    static String[] getNombresDeCasas(int size) {
        String[] response = new String[size];
        Random random = new Random();

        String[] prefijos = { "la", "una" };
        String[] mid = { "cueva", "casa", "mansión" };
        String[] suf = {
                "de la montaña",
                "del bosque",
                "del pantano",
                "del desierto",
                "de Drácula",
                "de la bruja",
                "del vampiro",
        };

        for (int i = 0; i < size; i++) {
            int randPref = random.nextInt(prefijos.length);
            int randMid = random.nextInt(mid.length);
            int randSuf = random.nextInt(suf.length);
            response[i] = prefijos[randPref] + " " + mid[randMid] + " " + suf[randSuf];
        }
        return response;
    }

    static int[] getPrices(int size) {
        int[] response = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            response[i] = random.nextInt(1000000);
        }
        return response;
    }

    public static void main(String[] args) {
        // Asociadas
        String[] nombresDeCasas = getNombresDeCasas(10);
        int[] prices = getPrices(10);

        // Aleatoria
        String[] nombreBarrios = {
                "Floresta",
                "Aranjuez",
                "Manrique",
                "Carlos E",
                "Robledo",
        };

        HashMap<String, Integer> ventas = new HashMap<String, Integer>();

        for (String barrio : nombreBarrios) {
            ventas.put(barrio, 0);
        }

        HashMap<String, Integer> pesosBarrios = new HashMap<String, Integer>();
        int[] pesos = {3, 2, 1, 4, 5}; // Puedes ajustar estos valores
        for (int i = 0; i < nombreBarrios.length; i++) {
            pesosBarrios.put(nombreBarrios[i], pesos[i]);
        }



        int userEntry = 0;
        Random rnd = new Random();
        Scanner scner = new Scanner(System.in);
        // 0 para descartar
        // 1 para comprar
        // 2 para finalizar

        System.out.println("¡Hola te doy la bienvenida a Tinder House!");
        System.out.println("El juego consiste en hacer match con cada casa que te guste");
        System.out.println("presiona 0 para descartar, 1 para comprar y 2 para terminar el juego");
        do {
            // Aqui va el juego
            int randCasa = rnd.nextInt(nombresDeCasas.length);
            int randBarrio = rnd.nextInt(nombreBarrios.length);
            int randPrice = rnd.nextInt(prices.length);
            System.out.println("------*-------");
            System.out.println(nombresDeCasas[randCasa]);
            System.out.println(nombreBarrios[randBarrio]);
            System.out.println(prices[randPrice] + " Bolivares");
            System.out.println("------*-------");
            userEntry = scner.nextInt();
            if (userEntry == 1) {
            int actualVenta = ventas.get(nombreBarrios[randBarrio]);
            int precioVenta = prices[randPrice] * pesosBarrios.get(nombreBarrios[randBarrio]);
            int newVenta = actualVenta + precioVenta;
            ventas.replace(nombreBarrios[randBarrio], newVenta);
        }

        } while (userEntry != 2);

        System.out.println("Reporte de ventas:");

        for (String barrio : ventas.keySet()) {
            System.out.println(barrio + " vendió " + ventas.get(barrio));
        }
        int maxGanancia = Integer.MIN_VALUE;
        int minGanancia = Integer.MAX_VALUE;
        String barrioMaxGanancia = "";
        String barrioMinGanancia = "";

        for (String barrio : ventas.keySet()) {
            int ganancia = ventas.get(barrio);
            
            if (ganancia > maxGanancia || (ganancia == maxGanancia && pesosBarrios.get(barrio) < pesosBarrios.get(barrioMaxGanancia))) {
                maxGanancia = ganancia;
                barrioMaxGanancia = barrio;
            }
            
            if (ganancia < minGanancia || (ganancia == minGanancia && pesosBarrios.get(barrio) > pesosBarrios.get(barrioMinGanancia))) {
                minGanancia = ganancia;
                barrioMinGanancia = barrio;
            }
        }

        System.out.println("El barrio que más dinero ganó es: " + barrioMaxGanancia +" con un total de ventas de "+maxGanancia+" millones de bolivares");
        System.out.println("El barrio que menos dinero ganó es: " + barrioMinGanancia +" con un total de ventas de "+minGanancia+" millones de bolivares");
        
        int maxCasasVendidas = Integer.MIN_VALUE;
        String barrioMaxCasasVendidas = "";

        for (String barrio : ventas.keySet()) {
            int casasVendidas = ventas.get(barrio) / 1000000; // Dividido por el precio promedio de una casa
            if (casasVendidas > maxCasasVendidas || (casasVendidas == maxCasasVendidas && pesosBarrios.get(barrio) > pesosBarrios.get(barrioMaxCasasVendidas))) {
                maxCasasVendidas = casasVendidas;
                barrioMaxCasasVendidas = barrio;
            }
        }

        System.out.println("El barrio que más casas vendió es: " + barrioMaxCasasVendidas+" con un total de ventas de "+maxGanancia+" millones de bolivares");

    }
}
