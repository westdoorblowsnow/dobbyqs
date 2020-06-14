package dobby.dobbyqs.web;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DobbyUtils {
    public static final Pattern ANSWER_PATTERN = Pattern.compile("^[A-O]{1,15}$");
    public static final String SEPARATOR = "##";
    public static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder();
    public static final Base64.Decoder BASE64_DECODER = Base64.getDecoder();

    public static int intAnswer(String answer) {
        int a = 0;
        answer = answer.trim();
        Matcher matcher = ANSWER_PATTERN.matcher(answer);
        if (matcher.find()) {
            for (int k = 0; k < answer.length(); k++) {
                a = a | 1 << 'O' - answer.charAt(k);
            }
        }
        return a;
    }

    public static String stringAnswer(int answer) {
        StringBuilder a = new StringBuilder();
        for (char i = 'A'; i <= 'O'; i++) {
            if ((answer & (1 << ('O' - i))) != 0) {
                a.append(i);
            }
        }
        return a.toString();
    }

    public static String stringOptions(List<String> options) {
        StringBuilder o = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            o.append(options.get(i)).append(SEPARATOR);
        }
        o.delete(o.length() - 2, o.length());
        return o.toString();
    }

    public static List<String> listOptions(String options) {
        List<String> o = new ArrayList<>();
        String[] split = options.split(SEPARATOR);
        for (int i = 0; i < split.length; i++) {
            o.add(split[i]);
        }
        return o;
    }


    public static byte[] base64StringToBytes(String base64) {
        return BASE64_DECODER.decode(base64);
    }

    public static String bytesToBase64String(byte[] bytes) {
        return BASE64_ENCODER.encodeToString(bytes);
    }
}
