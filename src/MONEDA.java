//ESTE SEGMENTO DEFINE UNA CLASE RECORD LLAMADA MONEDA QUE ALMACENA LOS
//LOS DETALLES DE UNA CONVERSION DE MONEDA
public record MONEDA(String base_code, String target_code, double conversion_result) {
    public String cambioDeMoneda(double valor) {
        return "EL VALOR " + valor + " [" + this.base_code + "]" +
                " CORRESPONDE AL VALOR FINAL DE : " + this.conversion_result +
                " [" + this.target_code + "]";
    }
}