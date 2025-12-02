package cl.perfumes.rincon_perfumes_back.util;

public class RutValidator {

    public static boolean validar(String rut) {
        if (rut == null || rut.trim().isEmpty()) {
            return false;
        }

        // Limpiar formato
        rut = rut.replace(".", "")
                 .replace("-", "")
                 .trim()
                 .toUpperCase();

        if (rut.length() < 2) {
            return false;
        }

        String rutNumeros = rut.substring(0, rut.length() - 1);
        char dv = rut.charAt(rut.length() - 1);

        try {
            Integer.parseInt(rutNumeros);
        } catch (NumberFormatException e) {
            return false;
        }

        return calcularDV(rutNumeros) == dv;
    }

    private static char calcularDV(String rutNumeros) {
        int suma = 0;
        int multiplicador = 2;

        for (int i = rutNumeros.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(rutNumeros.charAt(i)) * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
        }

        int resto = suma % 11;
        int dv = 11 - resto;

        if (dv == 11) return '0';
        if (dv == 10) return 'K';
        return (char) (dv + '0');
    }

    public static String formatear(String rut) {
        if (rut == null || rut.trim().isEmpty()) {
            return rut;
        }

        rut = rut.replace(".", "")
                 .replace("-", "")
                 .trim()
                 .toUpperCase();

        if (rut.length() < 2) {
            return rut;
        }

        String numeros = rut.substring(0, rut.length() - 1);
        char dv = rut.charAt(rut.length() - 1);

        StringBuilder formatted = new StringBuilder();
        int contador = 0;

        for (int i = numeros.length() - 1; i >= 0; i--) {
            if (contador > 0 && contador % 3 == 0) {
                formatted.insert(0, ".");
            }
            formatted.insert(0, numeros.charAt(i));
            contador++;
        }

        formatted.append("-").append(dv);
        return formatted.toString();
    }

    public static String limpiar(String rut) {
        if (rut == null) {
            return null;
        }
        return rut.replace(".", "")
                  .replace("-", "")
                  .trim()
                  .toUpperCase();
    }
}
