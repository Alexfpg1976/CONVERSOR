public record MONEDA(String base_code, String target_code, double conversion_result) {
    public String cambioDeMoneda(double valor) {
        return "EL VALOR " + valor + " [" + this.base_code + "]" +
                " CORRESPONDE AL VALOR FINAL DE =>>> " + this.conversion_result +
                " [" + this.target_code + "]";
    }
}