package common.user.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import common.user.UserInfoVO;

public interface UserMapper {

	@Select("Select * from T_USER_MST")
	List<UserInfoVO> selectUser();
}
