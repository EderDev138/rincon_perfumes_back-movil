package cl.perfumes.rincon_perfumes_back.service;

import cl.perfumes.rincon_perfumes_back.dto.response.QrResponse;
import cl.perfumes.rincon_perfumes_back.model.entidades.PedidoEntidad;

/**
 * Servicio para generación y validación de códigos QR de retiro
 */
public interface QrServicio {
    
    /**
     * Genera un código QR para un pedido
     * @param pedido El pedido para el cual generar el QR
     * @return El pedido actualizado con el QR generado
     */
    PedidoEntidad generarQrParaPedido(PedidoEntidad pedido);
    
    /**
     * Obtiene la información del QR de un pedido
     * @param pedidoId ID del pedido
     * @return Respuesta con datos del QR
     */
    QrResponse obtenerQrPorPedido(Long pedidoId);
    
    /**
     * Valida un código de retiro y marca como retirado si es válido
     * @param codigoRetiro El código único de retiro
     * @return Respuesta con el resultado de la validación
     */
    QrResponse validarCodigoRetiro(String codigoRetiro);
    
    /**
     * Genera solo el código de retiro único
     * @param pedidoId ID del pedido
     * @return Código único generado
     */
    String generarCodigoRetiro(Long pedidoId);
    
    /**
     * Genera imagen QR en Base64
     * @param contenido Contenido a codificar en el QR
     * @return String en Base64 de la imagen PNG
     */
    String generarQrBase64(String contenido);
    
    /**
     * Reenvía el QR por email y genera URL de WhatsApp
     * @param pedidoId ID del pedido
     * @return Respuesta con URLs de envío
     */
    QrResponse reenviarQr(Long pedidoId);
}