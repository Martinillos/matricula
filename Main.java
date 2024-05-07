import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // para introducirr matricula
        System.out.print("Introduce la matrícula actual (formato: NNNNLLL sin vocales): ");
        String matriculaActual = scanner.nextLine().toUpperCase(); // Convertir a mayúsculas para manejar entradas mixtas
        
        // formato
        if (!isValidMatricula(matriculaActual)) {
            System.out.println("Formato de matrícula inválido. Debe ser NNNNLLL sin vocales.");
            return;
        }
        
        // Extraer número y letra 
        String number = matriculaActual.substring(0, 4);
        String letter = matriculaActual.substring(4);
        
        if (number.equals("0000")) {
            number = incrementNumber(number);
        } else if (number.equals("9999")) {
            number = "0000";
            letter = incrementLetter(letter);
            
            if (letter.equals("ZZZ")) {
                System.out.println("Se acaban los números y hay que usar sistema nuevo");
                return;
            }
        } else {
            number = incrementNumber(number);
        }
        
        while (containsVowels(number + letter)) {
            
            if (number.equals("9999")) {
                number = "0000";
                letter = incrementLetter(letter);
            } else {
                number = incrementNumber(number);
            }
        }

        System.out.println("La siguiente matrícula es: " + number + letter);
    }

    public static boolean isValidMatricula(String matricula) {
        return matricula.matches("\\d{4}[BCDFGHJKLMNPQRSTVWXYZ]{3}");
    }

    // Función para incrementar el número
    public static String incrementNumber(String number) {
        int num = Integer.parseInt(number);
        num++;
        if (num > 9999) {
            return "9999";
        } else {
            return String.format("%04d", num);
        }
    }

    // Función para incrementar la letra
    public static String incrementLetter(String letter) {
        char[] chars = letter.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] < 'Z') {
                chars[i]++;
                break;
            } else {
                chars[i] = 'A';
            }
        }
        return String.valueOf(chars);
    }
    
    // Función para verificar vocales
    public static boolean containsVowels(String matricula) {
        return matricula.matches(".*[AEIOU].*");
    }
}
