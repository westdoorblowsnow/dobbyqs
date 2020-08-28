package dobby.dobbyqs.test;

import dobby.dobbyqs.mybatis.mapper.QuestionMapper;
import dobby.dobbyqs.mybatis.pojo.Question;
import dobby.dobbyqs.mybatis.service.QuestionService;
import dobby.dobbyqs.paper.PaperParser;
import dobby.dobbyqs.web.DobbyUtils;
import dobby.dobbyqs.web.StringUtils;
import dobby.dobbyqs.web.bean.GetQuestion;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {


    @org.junit.Test
    public void testLoop() {
        int l = ("根据子宫内膜组织学变化将月经周期分为增殖期、分泌期、月经期 3 个阶段。增殖期，月经周期第\n" +
                "5～14d，与卵巢周期中的卵泡期成熟阶段相对应。在雌激素作用下，内膜表面上、腺体、间质、血管均呈\n" +
                "增殖性变化，子宫内膜增厚。分泌期，月经周期第 15～28d，与卵巢周期中的黄体期相对应。黄体分泌的\n" +
                "孕激素、雌激素使增殖内膜继续增厚，腺体更增长弯盐，出现分泌现象；血管迅速增加，更加弯曲；问质\n" +
                "疏松并水肿。整个分泌期分为分泌早期、分泌中期及分泌晚期。月经期，月经周期第 1～4d，为子宫内膜\n" +
                "海绵状功能层从基底层崩解脱落期，这是孕酮和雌激素撤退的结果。经前 24h，内膜螺旋动脉节律性收缩\n" +
                "及舒．张，继而出现逐渐加强的血管痉挛性收缩，导致远端血管及组织缺血坏死、剥脱，脱落的内膜碎片\n" +
                "及血液一起从阴道流出，即为月经来潮。").length();
        System.out.println(l);
    }

    @org.junit.Test
    public void testStringSimilar() {
        String src = "从下面五个选项A、B、C、D、E、中选择一个最佳答案。";
        String target = "";
        final float similarityRatio = StringUtils.getSimilarityRatio(src, PaperParser.A1_TYPE_TITLE);
        System.out.println(similarityRatio);
    }

    @org.junit.Test
    public void textPressPic() throws IOException {
        String src = "C:\\Users\\pc\\Desktop\\image.jpg";
        String d = "C:\\Users\\pc\\Desktop\\fad.jpg";
        String d2 = "C:\\Users\\pc\\Desktop\\faddfd.jpg";
//        final BufferedImage bufferedImage = Thumbnails.of(src).scale(1.0f).asBufferedImage();
//        for (int i = 0; i < bufferedImage.getWidth(); i++) {
//            for (int j = 0; j < bufferedImage.getHeight(); j++) {
//                final int rgb = bufferedImage.getRGB(i, j);
//
//                if (luminance(rgb) < 0.38) {
////                    System.out.println(Integer.toHexString(rgb)+" "+luminance(rgb));
//                    bufferedImage.setRGB(i, j, getLuminance(0));
//                } else
//                if (luminance(rgb) > 0.53) {
////                    System.out.println(Integer.toHexString(rgb)+" "+luminance(rgb));
//                    bufferedImage.setRGB(i, j, getLuminance(1));
//                }
//            }
//        }
//        Thumbnails.of(bufferedImage).scale(1.0f).outputQuality(1.0f).toFile(d);

//        System.out.println(0xFFFFFF);
//        System.out.println(luminance(0xFF994589));
//        System.out.println(Integer.toHexString(0xFF994589));

        Thumbnails.of(src).scale(0.25f).outputQuality(0.8f).toFile(d2);
    }

    @org.junit.Test
    public void ss() throws IOException {
        String src = "C:\\Users\\pc\\Desktop\\image.jpg";
        String d = "C:\\Users\\pc\\Desktop\\fad.jpg";
        final BufferedImage bufferedImage = Thumbnails.of(d).scale(1.0f).asBufferedImage();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        final String string = DobbyUtils.bytesToBase64String(byteArrayOutputStream.toByteArray());
        System.out.println(string);
    }


    double luminance(int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) throw new RuntimeException("颜色数值错误！");
        return (r + g + b + 3) / (256 * 3.0);
    }

    double luminance(int rgb) {

//        System.out.println(Integer.toHexString(rgb) + " " + Integer.toHexString(((rgb << 8) >> 24)&0xFF));
//        System.out.println((((rgb << 8) >> 24) & 0xFF));
//        System.out.println((((rgb << 16) >> 24) & 0xFF));
//        System.out.println((rgb & 0xFF));
//        System.out.println((153+69+137)/(255*3.0));
        return ((((rgb << 8) >> 24) & 0xFF) + (((rgb << 16) >> 24) & 0xFF) + (rgb & 0xFF) + 3) / (256 * 3.0);
    }

    int getLuminance(double l) {
        int r = 0;
        int g = 0;
        int b = 0;
        if (l <= 0) return 0;
        else if (l == 1) return 0xFFFFFF;
        else return ((int) (255 * l) << 16) | ((int) (255 * l) << 8) | (int) (255 * l) | (0xFF << 24);
    }


    @org.junit.Test
    public void textBase64() {
        String base64 = "iVBORw0KGgoAAAANSUhEUgAAARcAAAAbCAYAAABWZssbAAAGbElEQVR42u1ce4xdQxhvm6Lq1VKhBAlXi7WPe3f3rrXCKhINiUdLvMU7KBfVaKRYj2wImqKENGybtkTJalGPfxDikeiWWv+IPzQp8QgaQrAl6/dL5iZjnJl7zpzHnXPvfMmXe86cM/N9833ffPPNN3PuhAkePHjw4MGDBw8ecgSdnZ3XAm8A3g0c6ujomOb59uD14CEWdHV1XVIsFtslQ1lcKpVe9Hx78HpIESCkC4GdDTCzlDi7aJ4NoY/vSPc9wF9y0Kdc8t2AUYvXg4XQTgMubKD+VGAEF6nl3d3d+5fL5VnSTHQ+8APX++MC35ixeyHXJ4CrINv38fs8lgSHN9M4yav91A1aW1unw1CGcTkxC3otLS07ZxTCvoxBUDA4oJ2AH3L2yZnjtObbVvYYVN2gt0QqmoT7R4A/Q86zXZJPVvaVV/vJ2lgfxSA8NUN6IxnNMieA1nrDMnApBkZ/Dpev1nzbyp6ORJ18CoXCLij/wbWcQ1b2lVf7yVIRewE/yZjm1gxpfQo8OKB8IYzjSHE9M0f6isW3rexR7zfgrQHlGxm9OCajra7roVmcy6UQ0mON6lw4u3DbUFkuXcNcQX9//xQ8m4p3rsqDrpLgO4Zz+RJ4b4B8X0P5783kXPJqP/VQxEoI51zD85LYbrud4a+IdK7jLAZ8CUuPlqyUT4Wi7p1MKAIvUNqsQOmrAwxhPhOP0nu3AMcVfKWO8g8l36T4tpU9BxLzLEox8y7fAt+0tRkbndbTvsLogXm+oHZQfy7uvwceFmd8sT5wBd5/oFoXvzOi0LWhraNrUsRmED9ak7M4CM9ult4dAo7yfZHgG7fZYbJU/iRGIVz3CwGMKG2+B1yrVioWi0eh/LMk8lJiiRUWN1FGNXJCqcg3q1kd+jgL7f0DVo+17JOVTl2yrwBg/QeldjZJ8joG939DFvNs9Y825qBsG94pijYOxf13lUplUVi6NrR1dIOWyrLQfsIAPEDz7B45884IAPgur9va2nYDoSsYFmahfNA6hR5Y1H8VuEZqbypwLCg85XrYtZxA2vLNwrmQPy6VwOMC2z7Z6tQl+wqYME6ubo6IdlYpvK3llr6N/kVk9SvLJb5nA18fHBy8LCzdqLRNdFF+hEkRYyLk/R+Uy+V9lHe/YtgVNlHMw0b0oAH4l6b87b6+vj0MvNArzyDPVUMQtE6kpw06cyHWxWMuOpc48s1S9pqo5Tk1lxW1T7Y6dcm+dO1gIO+LOjt4fkzh+/7qeIuqfy5VuDvHXbo4dKPSNtGtZYg7dM5FDeEpYDB/fD1nT9S9ktEWeJ4sedEBlH2jyxXQ2FzPfSUp37QjF7RxB09zJ9WnqDp1yb5MSV/U2c6zMEr5gI2s6BSF0xhKkm4t2mHp6oS5PcxWGneVgH/K3suYzElP+evkBK0oe0u3Hm5vbz8Qz35MYEAt08yGOvwoyqcUSco3TefCRCFDf3UpEKdPUXXqkn0ZIjsmSNepiV6dU64lKy4/6ABQ//ok6daiHZauruFRJj0DZvzJ8HaX9/T07Cc832r1mLNIIGWt/C3MUlfvuW7kVqhuPYxnrXJyyxVIU74pyv448NgXMFsOxOlTVJ26ZF+GdjbL7YhBfldvb++uNrJCvb2ZPGcSXaXFE/ZSvshINyrtsHRNnvqcAIM5T2SMz2Rkg/uP8fuGEj6W6qD8Yfm8hdh6066H2Q/gM645lzTlm4bsxbYlE4UPSfgwjO5J/D4bp09RdeqSfRn0y23rZdIAn4OyM+Lon3ku4H1qVIL3HudAD0PXhnYYujph3iiOdv8HuINEQaORm7inDQ++O66fFuvPxcyu12n25F77RvCySPC2Hvi1QcnLgz5grDekKd+UnMu2gPMd4yJkXhqnT1F16pJ9GdqZKRKhS3g2Bm2dHVf/iDT2RPlTHOg8dwO8DddXy/mVWnRtaIehqzPyQ/DS51kOLG5hJtjWFn6pa3g+EmUXpNEhSdmnyKNRpy7Zl4fayacNac2UQVDrcJlB0S/Ip0HhGE/C/R+6L5+5HqzOqh7iyT5FRxJJpy7Zl4dwzqUg1lwTXeYTPH5R/RMokWgaNXy6wDMLw/4vCJ2PUqLotGl4aSjALH8x11uO8zhPfNvB7zyWV48iaxwm18yne806b3ehddpMvDSiohe4FjbbAMLZLjpLr1EPHjx48ODBgwcPHjx48ODBQ0bwLznDYEJhyY6wAAAAAElFTkSuQmCC";
        System.out.println(base64.equals(DobbyUtils.bytesToBase64String(DobbyUtils.base64StringToBytes(base64))));
    }

    @org.junit.Test
    public void kkkkkkkk() {
        String s = "fadksjfhkasdjhfklasjd${img:0}";
        final Pattern compile = Pattern.compile("\\$\\{img:\\d+\\}");
        final Matcher matcher = compile.matcher(s);
        matcher.find();
        System.out.println(matcher.group());
    }

    @org.junit.Test
    public void file() throws IOException {
        String t0 = "临床医学检验技术(师) 模拟试卷";
        String[] t1 = {"一", "二", "三", "四", "五", "六", "七"};
        String[] t2 = {"基础知识", "相关专业知识", "专业知识", "专业实践能力"};
        String t3 = "——答案";
        String t4 = ".txt";
        String root = "C:\\Users\\pc\\Desktop\\临床医学检验技术(师)";
        for (String tt1 : t1) {
            for (String tt2 : t2) {
                File file = new File(root + "\\" + t0 + " " + tt1 + " " + tt2 + t4);
                File file2 = new File(root + "\\" + t0 + " " + tt1 + " " + tt2 + t3 + t4);
                if (!file.exists()) {
                    file.createNewFile();
                    System.out.println(file.getAbsolutePath());
                }
                if (!file2.exists()) {
                    file2.createNewFile();
                    System.out.println(file2.getAbsolutePath());
                }
//                if (file2.exists()) file2.deleteOnExit();
            }
        }
    }

}
