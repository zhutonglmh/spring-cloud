package studyserverone.easy_poi.dao2;

import org.apache.ibatis.annotations.Param;
import studyserverone.easy_poi.entity.ScmYearEnd;
import studyserverone.easy_poi.entity.ScmYearEndExample;

import java.util.List;

public interface ScmYearEndMapper2 {
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