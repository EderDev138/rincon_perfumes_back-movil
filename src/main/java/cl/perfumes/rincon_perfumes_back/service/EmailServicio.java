package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.model.entidades.PedidoEntidad;

/**
 * Servicio para envío de correos electrónicos
 */
public interface EmailServicio {
    
    /**
     * Envía correo con el QR de retiro del pedido
     * @param pedido El pedido con el QR generado
     * @return true si se envió correctamente
     */
    boolean enviarCorreoQrRetiro(PedidoEntidad pedido);
    
    /**
     * Envía correo de confirmación de pedido
     * @param pedido El pedido creado
     * @return true si se envió correctamente
     */
    boolean enviarCorreoConfirmacion(PedidoEntidad pedido);
    
    /**
     * Envía correo genérico con adjunto
     * @param destinatario Email del destinatario
     * @param asunto Asunto del correo
     * @param contenidoHtml Contenido HTML del correo
     * @param nombreAdjunto Nombre del archivo adjunto (puede ser null)
     * @param adjuntoBase64 Contenido del adjunto en Base64 (puede ser null)
     * @return true si se envió correctamente
     */
    boolean enviarCorreo(String destinatario, String asunto, String contenidoHtml, 
                         String nombreAdjunto, String adjuntoBase64);
    
    /**
     * Genera URL de WhatsApp con mensaje prellenado
     * @param telefono Número de teléfono (con código de país)
     * @param mensaje Mensaje a enviar
     * @return URL de WhatsApp
     */
    String generarUrlWhatsApp(String telefono, String mensaje);
}
