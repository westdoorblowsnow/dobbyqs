package dobby.dobbyqs.web.type;

import dobby.dobbyqs.web.openAPI.pojo.LittleQuestion;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionTypeHandler implements TypeHandler<LittleQuestion> {

    @Override
    public void setParameter(PreparedStatement ps, int i, LittleQuestion parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public LittleQuestion getResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public LittleQuestion getResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public LittleQuestion getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
