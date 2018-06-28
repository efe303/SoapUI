package core;

/**
 * Created by yulia_atlasova@epam.com on 22/06/2017.
 * Constants of YandexSpeller
 */
public class YandexSpellerConstants {

    //useful constants for API under test
    public static final String YANDEX_SPELLER_API_URI = "https://speller.yandex.net/services/spellservice.json/checkText";
    public static final String PARAM_TEXT = "text";
    public static final String PARAM_OPTIONS = "options";
    public static final String PARAM_LANG = "lang";
    public static final String WRONG_WORD_EN = "requisitee";
    public static final String RIGHT_WORD_EN = "requisite";
    public static final String WRONG_WORD_UK = "питаня";
    public static final String WORD_WITH_WRONG_CAPITAL = "moscow";
    public static final String WORD_WITH_LEADING_DIGITS = "11" + RIGHT_WORD_EN;


    public enum Languages {
        RU("ru"),
        UK("uk"),
        EN("en");
        String languageCode;

        private Languages(String lang) {
            this.languageCode = lang;
        }
    }

    public enum CodeError {
        ERROR_UNKNOWN_WORD(1, "Слова нет в словаре."),
        ERROR_REPEAT_WORD(2, "Повтор слова."),
        ERROR_CAPITALIZATION(3, "Неверное употребление прописных и строчных букв."),
        ERROR_TOO_MANY_ERRORS(4, "Текст содержит слишком много ошибок. " +
                "При этом приложение может отправить Яндекс.Спеллеру оставшийся " +
                "непроверенным текст в следующем запросе.");

        public final int code;
        public final String descriptinon;

        private CodeError(int code, String description) {
            this.code = code;
            this.descriptinon = description;
        }
    }

    public enum Options {
        IGNORE_DIGITS(2, "Пропускать слова с цифрами, например, \"авп17х4534\"."),
        IGNORE_URLS(4, "Пропускать интернет-адреса, почтовые адреса и имена файлов."),
        FIND_REPEAT_WORDS(8, "Подсвечивать повторы слов, идущие подряд. Например, \"я полетел на на Кипр\"."),
        IGNORE_CAPITALIZATION(512, "Игнорировать неверное употребление ПРОПИСНЫХ/строчных букв, например, в слове \"москва\".");

        public final int code;
        public final String descriptinon;

        private Options(int code, String description) {
            this.code = code;
            this.descriptinon = description;
        }
    }

}
