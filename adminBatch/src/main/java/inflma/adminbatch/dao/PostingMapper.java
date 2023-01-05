package inflma.adminbatch.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface PostingMapper {
    String findByPostedComplete();

    void completePosting(HashMap map);

}
