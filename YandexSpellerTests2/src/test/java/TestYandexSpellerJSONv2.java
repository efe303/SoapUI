import beans.YandexSpellerAnswer;
import core.YandexSpellerApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static core.YandexSpellerConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * Created by vitalii_balitckii@epam.com.
 */
public class TestYandexSpellerJSONv2 {

    final String TEST_WORD = "entErpreneurship";
    final String CORRECT_WORD = "entrepreneurship";

    @Test
    public void simpleSpellerApiCall() {
        RestAssured
                .given()
                .queryParam(PARAM_TEXT, TEST_WORD)
                .params(PARAM_LANG, Languages.EN, "CustomParameter", "valueOfParam")
                .accept(ContentType.JSON)
                .auth().basic("abcName", "abcPassword")
                .header("custom header1", "header1.value")
                .and()
//                .body("some body payroll")
                .log().everything()

                .when()
                .get(YANDEX_SPELLER_API_URI)
                .prettyPeek()

                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("word", hasItem(TEST_WORD))
//                .body("s", arrayWithSize(3))
                .time(lessThan(20000L));
    }

    @Test
    public void simpleSpellerApiCall2() {
        List<YandexSpellerAnswer> answers =
                YandexSpellerApi.getYandexSpellerAnswers(
                        YandexSpellerApi.with()
                                .text(TEST_WORD)
                                .callApi());

        assertThat("expected number of answers is wrong.", answers.size(), equalTo(1));
        assertThat(answers.get(0).word, equalTo(TEST_WORD));
        assertThat(answers.get(0).s.get(0), equalTo(CORRECT_WORD));
    }

    // use base request and response specifications to form request and validate response.
    @Test
    public void useBaseRequestAndResponseSpecifications() {
        List<YandexSpellerAnswer> answers =
                YandexSpellerApi.getYandexSpellerAnswers(
                        YandexSpellerApi.with()
                                .text(TEST_WORD)
                                .callApi()
                                .then()
                                .spec(YandexSpellerApi.successResponse())
                                .extract()
                                .response()
                );

        assertThat("expected number of answers is wrong.", answers.size(), equalTo(1));
        assertThat(answers.get(0).word, equalTo(TEST_WORD));
        assertThat(answers.get(0).s.get(0), equalTo(CORRECT_WORD));

    }

}
