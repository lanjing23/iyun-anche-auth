package iyun.anche.auth.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import iyun.anche.auth.service.Users;
import org.springframework.stereotype.Component;

@Component
public interface IUserRepository extends BaseMapper<Users> {
}
