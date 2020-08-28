package dobby.dobbyqs.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvUtil {
    /**
     * 日志对象
     */
    static Logger LOGGER = LoggerFactory.getLogger(CsvUtil.class);
    /**
     * 解析csv文件并转成bean
     *
     * @param file  csv文件
     * @param clazz 类
     * @param <T>   泛型
     * @return 泛型bean集合
     */
    public static <T> List<T> getCsvData(String file, Class<T> clazz) throws IOException {
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(new FileInputStream(file), "GB2312");
        } catch (Exception e) {
            throw e;
        }

        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(clazz);

        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(in)
                .withSeparator(',')
//                .withQuoteChar('\'')
                .withMappingStrategy(strategy).build();
        return csvToBean.parse();
    }

    static String root = "C:\\Users\\pc\\Desktop\\中药学(士)\\";
    static String name = "中药学(士) 提分卷";
    static String[] nums = {"一", "二", "三", "四", "五", "六", "七"};
    static String[] subjects = {"基础知识", "相关专业知识", "专业知识", "专业实践能力"};


    public static void main(String[] args) throws IOException {
        int sum = 0;
        List<String> strings = new ArrayList<>();
        List<String> strings1 = new ArrayList<>();
        for (String num : nums) {
            for (String subject : subjects) {
                try {
                    List<CsvQuestionBean> csvData = getCsvData(root + name +num +" "+ subject+".csv", CsvQuestionBean.class);
                    for (CsvQuestionBean csvQuestionBean : csvData) {
                        System.out.println(csvQuestionBean);
                        String[] split = csvQuestionBean.getOptions().split("n");
                        System.out.println(Arrays.toString(split));
                        if (split.length==5){
                            sum++;
                        }else {
                            strings.add(root + name +num +" "+ subject+".csv");
                            strings1.add(csvQuestionBean.toString());
                        }
                    }
                    System.out.println(csvData.size());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    strings.add(root + name +num +" "+ subject+".csv");
                    strings1.add(e.getMessage());
                }
            }
        }
        System.out.println("sum:"+sum);
        System.out.println(strings);
        System.out.println(strings1);
    }
}

