package inflma.opAdmin.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    String findByUserPushId(java.util.HashMap<java.lang.String,java.lang.Object> param);
}
