import core.YandexSpellerSOAP;
import org.junit.Test;

import static core.YandexSpellerConstants.*;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by vitalii_balitckii@epam.com on 24/06/2018.
 * try to test SOAP via RestAssured
 */

public class TestYandexSpellerSOAPv2 {

    String TEST_WORD = "entErpreneurship";
    String xRoot = "Envelope.Body.CheckTextResponse.SpellResult.error";

    @Test
    public void simpleCall() {
        YandexSpellerSOAP
                .with()
                .text(TEST_WORD)
                .callSOAP()
                .then()
                .body(xRoot + ".word", equalTo(TEST_WORD))
                .body(xRoot + ".s", equalTo("entrepreneurship"))
                .body(xRoot + ".@code", equalTo(String.valueOf(CodeError.ERROR_UNKNOWN_WORD.code)))
                .body(xRoot + ".@len", equalTo(String.valueOf(TEST_WORD.length())));

    }

    @Test
    public void testOptionIgnoreDigits() {
        YandexSpellerSOAP
                .with()
                .options(Options.IGNORE_DIGITS.code)
                .text("entErpreneurship wordContainedNumber2")
                .callSOAP()
                .then()
                .body(xRoot + ".size()", equalTo(1));
    }

    @Test
    public void testOptionIgnoreUrls() {
        YandexSpellerSOAP
                .with()
                .options(Options.IGNORE_URLS.code)
                .text("entErpreneurship http://url.com")
                .callSOAP()
                .then()
                .body("**.find { n -> n.name() == 'word'}[0].text()", equalTo(TEST_WORD))
                .body("**.find { n -> n.name() == 'error'}.size()", equalTo(1));
    }

    @Test
    public void testErrorRepeatWord() {
        YandexSpellerSOAP
                .with()
                .options(Options.FIND_REPEAT_WORDS.code)
                .text("entErpreneurship is is happyness")
                .callSOAP()
                .then()
                .body("**.find { n -> n.name() == 'error' && n.@code == '2'}.word.text()", equalTo("is"));
    }
}

