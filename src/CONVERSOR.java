import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CONVERSOR {
    private final double valorEntrada;
    private final String moneda1;
    private final String moneda2;
    private final double valorConvertido;
    private static int CONTADOR;
    private final String horaFormateada;
    private final int conta;

    public CONVERSOR(MONEDA moneda, LocalDateTime localDate, double valorEntrada) {
        this.valorEntrada = valorEntrada;
        this.moneda1 = moneda.base_code();
        this.moneda2 = moneda.target_code();
        this.valorConvertido = moneda.conversion_result();
        this.conta = ++CONTADOR;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.horaFormateada = formatter.format(localDate);
    }

    @Override
    public String toString() {
        return this.conta + ") EL VALOR " + this.valorEntrada + "[" + this.moneda1 + "]" +
                " CORRESPONDE AL VALOR FINAL DE =>>> " + this.valorConvertido +
                " [" + this.moneda2 + "] -- FECHA Y HORA : " + this.horaFormateada + "\n";
    }
}