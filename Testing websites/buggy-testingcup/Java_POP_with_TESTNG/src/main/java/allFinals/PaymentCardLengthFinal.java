package allFinals;

import java.util.HashMap;
import java.util.Map;

public class PaymentCardLengthFinal {
    private static final Map<String, String> cardLength;

    static {
        cardLength = new HashMap<>();
        cardLength.put("American Express", "[0-9]{15}");
        cardLength.put("American Express Corporate", "[0-9]{15}");
        cardLength.put("Australian BankCard", "[0-9]{16}");
        cardLength.put("Diners Club", "[0-9]{14}");
        cardLength.put("Discover", "[0-9]{16}");
        cardLength.put("JCB", "[0-9]{16}");
        cardLength.put("MasterCard", "[0-9]{16}");
        cardLength.put("Visa", "[0-9]{16}|[0-9]{13}");
        cardLength.put("Dankort (PBS)", "[0-9]{11}|[0-9]{16}");
        cardLength.put("Switch/Solo (Paymentech)", "[0-9]{16}");
    }

    public static Map<String, String> getCardLength() {
        return cardLength;
    }
}