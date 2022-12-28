package inflma.opAdmin.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface UserMapper {

    String findByUserPushId(HashMap<String, Object> param);
}
