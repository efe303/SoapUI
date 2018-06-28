package api_yandex_maps;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import static org.hamcrest.Matchers.lessThan;

public class YandexMapApi {
    public static ResponseSpecification successResponse(){
        return new ResponseSpecBuilder()
                .expectContentType("image/png")
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }
}
