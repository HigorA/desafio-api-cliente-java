package br.com.desafio.dao;

import org.springframework.web.reactive.function.client.WebClient;

public class ClienteApi {

    private static String apiUrl = "https://api.jsonbin.io/v3/b/63fa39efc0e7653a057e6fa7";
    private static String headerName = "X-MASTER-KEY";
    private static String headerValue = "$2b$10$Z2MvHCup.2lASOgJ77yhIeIbibWiTu4UXSNp6rYwq.pRbwYxybu8G";

    private static WebClient webClient = WebClient.create();

    public static String requisicaoApi() {
        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(apiUrl)
                .header(headerName,headerValue)
                .retrieve();

        return responseSpec.bodyToMono(String.class).block();
    }
}
