package inflma.opAdmin.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostingMapper {
    List<Long> findByPostedComplete();
}
