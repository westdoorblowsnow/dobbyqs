package dobby.dobbyqs.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DobbyUtils {
    public static final Pattern ANSWER_PATTERN = Pattern.compile("^[A-O]{1,15}$");
    public static final String SEPARATER = "##";

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
            o.append(options.get(i)).append(SEPARATER);
        }
        o.delete(o.length() - 2, o.length());
        return o.toString();
    }

    public static List<String> listOptions(String options) {
        List<String> o = new ArrayList<>();
        String[] split = options.split(SEPARATER);
        for (int i = 0; i < split.length; i++) {
            o.add(split[i]);
        }
        return o;
    }
}
