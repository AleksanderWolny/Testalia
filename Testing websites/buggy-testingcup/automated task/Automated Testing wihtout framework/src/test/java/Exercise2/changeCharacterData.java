package Exercise2;

import java.util.HashMap;
import java.util.Map;

public class changeCharacterData {

    public static Map<String, String> changePolishChar() {
        Map<String, String> polishAscii = new HashMap<>();
        polishAscii.put("ą", "a");
        polishAscii.put("Ą", "A");
        polishAscii.put("ć", "c");
        polishAscii.put("Ć", "C");
        polishAscii.put("ę", "e");
        polishAscii.put("Ę", "E");
        polishAscii.put("ł", "l");
        polishAscii.put("Ł", "L");
        polishAscii.put("ń", "n");
        polishAscii.put("Ń", "N");
        polishAscii.put("ó", "o");
        polishAscii.put("Ó", "O");
        polishAscii.put("ś", "s");
        polishAscii.put("Ś", "S");
        polishAscii.put("ż", "z");
        polishAscii.put("Ż", "Z");
        polishAscii.put("ź", "z");
        polishAscii.put("Ź", "Z");

        return polishAscii;
    }
}
