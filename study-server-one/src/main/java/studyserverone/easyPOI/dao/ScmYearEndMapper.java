package studyserverone.easyPOI.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import studyserverone.easyPOI.entity.ScmYearEnd;
import studyserverone.easyPOI.entity.ScmYearEndExample;

public interface ScmYearEndMapper {
    int countByExample(ScmYearEndExample example);

    int deleteByExample(ScmYearEndExample example);

    int deleteByPrimaryKey(String id);

    int insert(ScmYearEnd record);

    int insertSelective(ScmYearEnd record);

    List<ScmYearEnd> selectByExample(ScmYearEndExample example);

    ScmYearEnd selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ScmYearEnd record, @Param("example") ScmYearEndExample example);

    int updateByExample(@Param("record") ScmYearEnd record, @Param("example") ScmYearEndExample example);

    int updateByPrimaryKeySelective(ScmYearEnd record);

    int updateByPrimaryKey(ScmYearEnd record);
}