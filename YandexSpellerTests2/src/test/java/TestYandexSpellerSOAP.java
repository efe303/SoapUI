import core.YandexSpellerSOAP;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;

import static core.YandexSpellerConstants.*;
import static core.YandexSpellerConstants.Options.*;

/**
 * Created by yulia_atlasova@epam.com on 22/06/2017.
 * try to test SOAP via RestAssured
 */
public class TestYandexSpellerSOAP {

    @Test
    public void simpleCall(){
                YandexSpellerSOAP
                        .with().text(WRONG_WORD_EN)
                        .callSOAP()
                        .then()
                        .body(Matchers.stringContainsInOrder(Arrays.asList(WRONG_WORD_EN, RIGHT_WORD_EN)));
    }

    @Test
    public void useRequestBuilderToChangeParams(){
        YandexSpellerSOAP.with()
                .language(Languages.EN)
                .text(WRONG_WORD_EN)
                .options(IGNORE_DIGITS.code + IGNORE_URLS.code)
                .callSOAP()
                .then()
                .body(Matchers.stringContainsInOrder(Arrays.asList(WRONG_WORD_EN, RIGHT_WORD_EN)));
    }
}
