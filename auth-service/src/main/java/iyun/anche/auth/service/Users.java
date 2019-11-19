package iyun.anche.auth.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author liusx
 * @since 2019/10/11 下午2:55
 */
@Data
@TableName(value = "users")
public class Users {

    @TableField("id")
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("pwd")
    private String pwd;

    @TableField("identity_card")
    private String identityCard;

    @TableField("create_date")
    private Date createDate;

    @TableField("update_date")
    private Date updateDate;
}
