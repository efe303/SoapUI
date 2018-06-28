package api_yandex_maps;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RequestToYandexMaps {
    final String URL = "https://static-maps.yandex.ru/1.x/";

    private Map<String, String> params = new HashMap<String, String>();

    // https://tech.yandex.ru/maps/doc/staticapi/1.x/dg/concepts/input_params-docpage/
    public enum PARAMS {
        LAYER("l", "l=sat,skl"),
        CENTER("ll", "ll=37.620070,55.753630"),
        AREA("spn", "spn=0.01,0.01"),
        SCALE_OF_MAP("z", "z=10"),
        SIZE_OF_SCREEN("size", "size=450,450"),
        SCALE_OF_OBJECTS("scale", "scale=2.0"),
        POINTS("pt", "pt=37.620070,55.753630,pmwtm1"),
        FIGURES("pl", "pl=c:ff0000ff,w:3, \\\n" +
                "80peAuGUQQMIUgAAAAAAAAAAAAAQJwAA"),
        LANG("lang", "lang=tr_TR"),
        KEY("base64", "base64");

        public final String name;
        public final String example;

        PARAMS(String name, String example) {
            this.name = name;
            this.example = example;
        }
    }

    public enum LAYER_VALUE {
        MAP("map"),
        SAT("sat"),
        SKL("skl"),
        TRF("trf");

        String name;
        LAYER_VALUE(String name) {
            this.name = name;
        }
    }

    final Function<Float, String> CENTER_FORMAT = (f) -> String.format("%.6f", f);
    final Function<Float, String> AREA_FORMAT = (f) -> String.format("%.2f", f);
    final Function<Float, String> POINT_FORMAT = (f) -> String.format("%.2f", f);

    private RequestToYandexMaps() { }

    public static RequestToYandexMaps with() {
        return new RequestToYandexMaps();
    }

    public RequestToYandexMaps layer(LAYER_VALUE layer) {
        params.put(PARAMS.LAYER.name, layer.name);
        return this;
    }

    public RequestToYandexMaps center(float a, float b) {
        params.put(PARAMS.CENTER.name, CENTER_FORMAT.apply(a) + "," + CENTER_FORMAT.apply(b));
        return this;
    }

    public RequestToYandexMaps area(float a, float b) {
        params.put(PARAMS.AREA.name, AREA_FORMAT.apply(a) + "," + AREA_FORMAT.apply(b));
        return this;
    }

    public RequestToYandexMaps scaleOfMap(int z) {
        params.put(PARAMS.SCALE_OF_MAP.name, "" + z);
        return this;
    }

    public RequestToYandexMaps sizeOfScreen(int a, int b) {
        params.put(PARAMS.SIZE_OF_SCREEN.name, "" + a + "," + b);
        return this;
    }

    public RequestToYandexMaps scaleOfObjects(float a, float b) {
        params.put(PARAMS.SCALE_OF_OBJECTS.name, "" + a + "," + b);
        return this;
    }

    public RequestToYandexMaps points(float a, float b) {
        params.put(PARAMS.POINTS.name,
                "" + POINT_FORMAT.apply(a) + "," + POINT_FORMAT.apply(b) + ",pmwtm1");
        return this;
    }

    public RequestToYandexMaps figures() {
        // TODO
        return this;
    }

    public RequestToYandexMaps lang() {
        // TODO
        return this;
    }

    public RequestToYandexMaps key() {
        // TODO
        return this;
    }

    public Response callApi() {
        return RestAssured.with()
                .queryParams(params)
                .log().all()
                .get(URL).prettyPeek();
    }

}
