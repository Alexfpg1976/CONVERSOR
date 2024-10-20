import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CONSULTA {
    public MONEDA cambiarMoneda(String moneda1, String moneda2, double valor) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/abbf5a23e527196636e05a3a/pair/" + moneda1 + "/" + moneda2 + "/" + valor);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), MONEDA.class);
        } catch (Exception e) {
            throw new RuntimeException("ERROR AL REALIZAR EL CAMBIO: " + e.getMessage());
        }
    }
}