import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//ESTE SEGMENTO CONSTRUYE UNA URI PARA REALIZAR  UNA CONSULTA A UN SERVICIO EXTERNO (EXCHANGERATE-API)
//QUE PROPORCIONA LA TASA DE CONVERSION ENTRE 2 MONEDAS.
public class CONSULTA {
    public MONEDA cambiarMoneda(String moneda1, String moneda2, double valor) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/abbf5a23e527196636e05a3a/pair/"
                + moneda1 + "/" + moneda2 + "/" + valor);

        // SE CREA UNA ESTANCIA Y FINALIZA CON LA CONSTRUCCION DE UN REQUEST
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        //ENVIO DE UNA SOLICITUD, MANEJO DE LA RESPUESTA DE LA API, Y RETORNO DE UN RESULTADO
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), MONEDA.class);
        } catch (Exception e) {

        //ESTE SEGMENTO LANZA UNA EXCEPCION PARA NOTIFICAR UN FALLO.
            throw new RuntimeException("ERROR AL REALIZAR EL CAMBIO : " + e.getMessage());
        }
    }
}