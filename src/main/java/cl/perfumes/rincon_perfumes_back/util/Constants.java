package cl.perfumes.rincon_perfumes_back.util;

import java.math.BigDecimal;

public class Constants {

    // ==================== PRECIOS Y DESCUENTOS ====================
    public static final BigDecimal MONTO_MINIMO_DESCUENTO = new BigDecimal("59990.00");
    public static final BigDecimal PORCENTAJE_DESCUENTO_DEFAULT = new BigDecimal("0.10"); // 10%
    public static final BigDecimal PORCENTAJE_IVA = new BigDecimal("0.19"); // 19%

    // ==================== STOCK ====================
    public static final int STOCK_MINIMO_ALERTA = 5;

    // ==================== ESTADOS DE PEDIDO ====================
    public static final String PEDIDO_PENDIENTE = "PENDIENTE";
    public static final String PEDIDO_PAGADO = "PAGADO";
    public static final String PEDIDO_PROCESANDO = "PROCESANDO";
    public static final String PEDIDO_ENVIADO = "ENVIADO";
    public static final String PEDIDO_ENTREGADO = "ENTREGADO";
    public static final String PEDIDO_CANCELADO = "CANCELADO";

    // ==================== ROLES ====================
    public static final String ROL_CLIENTE = "CLIENTE";
    public static final String ROL_ENCARGADO = "ENCARGADO";
    public static final String ROL_ADMIN_MAESTRO = "ADMIN_MAESTRO";

    // ==================== MENSAJES ====================
    public static final String MSG_LOGIN_EXITOSO = "Inicio de sesión exitoso";
    public static final String MSG_REGISTRO_EXITOSO = "Registro exitoso";
    public static final String MSG_COMPRA_EXITOSA = "¡Gracias por tu compra!";
    public static final String MSG_PEDIDO_CREADO = "Pedido creado exitosamente";
    
    // Mensajes de error
    public static final String MSG_ERROR_CREDENCIALES = "Credenciales inválidas";
    public static final String MSG_ERROR_USUARIO_INACTIVO = "Usuario inactivo";
    public static final String MSG_ERROR_EMAIL_DUPLICADO = "El email ya está registrado";
    public static final String MSG_ERROR_RUT_DUPLICADO = "El RUT ya está registrado";
    public static final String MSG_ERROR_RUT_INVALIDO = "El RUT ingresado no es válido";
    public static final String MSG_ERROR_STOCK_INSUFICIENTE = "Stock insuficiente";
    public static final String MSG_ERROR_CARRITO_VACIO = "El carrito está vacío";

    // ==================== VALIDACIONES ====================
    public static final int EDAD_MINIMA = 18;
    public static final int MIN_PASSWORD_LENGTH = 8;

    // Constructor privado
    private Constants() {
        throw new IllegalStateException("Clase de constantes - no instanciar");
    }
}
