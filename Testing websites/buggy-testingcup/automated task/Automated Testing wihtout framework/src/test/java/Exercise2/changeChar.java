package Exercise2;

import java.util.Map;

public class changeChar {
    public static String changeToAscii(String pmText) {
        if (validateText(pmText))
            return "";

        return changeKeyToValueInText(changeCharacterData.changePolishChar(), pmText);
    }

    private static String changeKeyToValueInText(Map<String, String> mapMatches, String pmText) {
        for (Map.Entry<String, String> map : mapMatches.entrySet())
            pmText = pmText.replaceAll(map.getKey(), map.getValue());

        return pmText;
    }

    private static String changeValueToKeyInText(Map<String, String> mapMatches, String pmText) {
        for (Map.Entry<String, String> map : mapMatches.entrySet())
            pmText = pmText.replaceAll(map.getValue(), map.getKey());

        return pmText;
    }

    private static boolean validateText(String pmText) {
        return (pmText == null || pmText.length() == 0);
    }

}
