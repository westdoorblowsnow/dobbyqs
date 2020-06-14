package dobby.dobbyqs.paper_parse;

import dobby.dobbyqs.web.bean.PostPaper;

public interface PaperParse {
    PostPaper parser(String paper,String answer);
}
