package cn.flyzy2005.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import java.util.Collection;
import java.util.List;

/**
 * Created by Fly on 2017/4/21.
 * 重写JdbcTemplate，当queryForObject查询结果为0时，返回0而不是抛出异常
 */
public class DaoUtilJdbcTemplate extends JdbcTemplate {

    @Override
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) throws DataAccessException {
        List<T> results = query(sql, args, new RowMapperResultSetExtractor<T>(rowMapper, 1));
        return requiredSingleResult(results);
    }

    private <T> T requiredSingleResult(Collection<T> results) throws IncorrectResultSizeDataAccessException {
        int size = (results != null ? results.size() : 0);
        if (size == 0) {
            return null;
        }
        if (results.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, size);
        }
        return results.iterator().next();
    }
}

