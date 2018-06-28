import api_yandex_maps.RequestToYandexMaps;
import api_yandex_maps.Utils;
import api_yandex_maps.YandexMapApi;
import io.restassured.response.Response;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class TestYandexMaps {
    @Test
    public void simpleCall() throws Exception {
        Response response = RequestToYandexMaps.with()
                .layer(RequestToYandexMaps.LAYER_VALUE.MAP)
                .center(37.620070f,55.753630f)
                .sizeOfScreen(450, 450)
                .scaleOfMap(13)
                .points(37.620070f,55.753630f)
                .callApi();

        response.then()
                .spec(YandexMapApi.successResponse());

//        byte[] bytes = response.body().asString().getBytes(StandardCharsets.US_ASCII);
        byte[] bytes = response.body().asString().getBytes();
//        Utils.drawPicture(bytes);
        Utils.savePicture(bytes);

//        https://static-maps.yandex.ru/1.x/?ll=37.620071,55.753632&size=450.0,450.0&pt=37.62,55.75,pmwtm1&z=13.0&l=map
//        https://static-maps.yandex.ru/1.x/?ll=37.620071,55.753632&size=450,450&pt=37.62,55.75,pmwtm1&z=13.0&l=map
//        https://static-maps.yandex.ru/1.x/?ll=37.620070,55.753630&size=450,450&z=13&l=map&pt=37.620070,55.753630,pmwtm1~37.64,55.76363,pmwtm99
    }

}
