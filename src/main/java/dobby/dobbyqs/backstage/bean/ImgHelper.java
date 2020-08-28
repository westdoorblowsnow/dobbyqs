package dobby.dobbyqs.backstage.bean;

import dobby.dobbyqs.web.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgHelper {

    private static final Pattern imgPattern = Pattern.compile("\\$\\{img:\\d+\\}");
    private static final Pattern NUM = Pattern.compile("\\d{1,2}");

    public String src(int questionId, String str) {
        if (StringUtils.isBlank(str)) return null;
        final Matcher matcher = imgPattern.matcher(str);
        if (matcher.find()) {
            final Matcher matcher1 = NUM.matcher(matcher.group());
            matcher1.find();
            return "/backstage/image?questionId=" + questionId + "&num=" + Integer.parseInt(matcher1.group());
        }
        return null;
    }

    public String rawStr(String str) {
        if (StringUtils.isBlank(str)) return null;
        final Matcher matcher = imgPattern.matcher(str);
        if (matcher.find()) {
            return str.substring(0, matcher.start());
        } else {
            return str;
        }
    }
}
