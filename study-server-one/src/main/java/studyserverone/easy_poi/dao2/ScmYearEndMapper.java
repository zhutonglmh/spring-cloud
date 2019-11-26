package studyserverone.easy_poi.dao2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import studyserverone.easy_poi.entity.ScmYearEnd;
import studyserverone.easy_poi.entity.ScmYearEndExample;

import java.util.List;

@Repository("scmYearEndMapper2")
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