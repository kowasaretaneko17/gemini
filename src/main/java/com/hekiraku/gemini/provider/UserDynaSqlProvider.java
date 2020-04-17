package com.hekiraku.gemini.provider;
import com.hekiraku.gemini.domain.entity.UserEntity;
import org.apache.ibatis.jdbc.SQL;

/**
 * 构建组：大道金服科技部
 * 作者:weiyimeng
 * 邮箱:weiyimeng@ddjf.com.cn
 * 日期:2019/4/19
 * 功能说明：
 */
public class UserDynaSqlProvider {
    /**
     * 通过id查询user表信息
     */
    public String selectById(String id){
        SQL selectById = new SQL()
                .SELECT("*")
                .FROM("g_user")
                .WHERE("id=#{id}");
        return selectById.toString();
    }

    /**
     * 通过userName查询user表
     * @param userName
     * @return
     */
    public String selectByUserName(String userName){
        SQL selectIdByUserName = new SQL()
                .SELECT("id")
                .FROM("g_user")
                .WHERE("user_name=#{userName}");
        SQL selectAllById = new SQL()
                .SELECT("*")
                .FROM("g_user")
                .WHERE("id=("+selectIdByUserName.getSelf()+")");
        return selectAllById.toString();
    }

    /**
     * 通过userNum查询user表
     * @param userNum
     * @return
     */
    public String selectByUserNum(String userNum){
        SQL selectIdByUserNum = new SQL()
                .SELECT("id")
                .FROM("g_user")
                .WHERE("user_num=#{userNum}");
        SQL selectAllById = new SQL()
                .SELECT("*")
                .FROM("g_user")
                .WHERE("id=("+selectIdByUserNum.getSelf()+")");
        return selectAllById.toString();
    }
    /**
     * 根据email查询数据
     */
    public String selectByEmail(String email){
        SQL selectByEmail = new SQL()
                .SELECT("id")
                .FROM("g_user")
                .WHERE("email=#{email}");
        SQL selectAllById = new SQL()
                .SELECT("*")
                .FROM("g_user")
                .WHERE("id=("+selectByEmail.getSelf()+")");
        return selectAllById.toString();
    }
    /**
     * 根据nickName查询数据
     */
    public String selectByNickName(String nickName){
        SQL selectByNickName = new SQL()
                .SELECT("id")
                .FROM("g_user")
                .WHERE("nick_name=#{nickName}");
        SQL selectAllById = new SQL()
                .SELECT("*")
                .FROM("g_user")
                .WHERE("id=("+selectByNickName.getSelf()+")");
        return selectAllById.toString();
    }
    /**
     * 根据phone查询数据
     */
    public String selectByPhone(String phone){
        SQL selectByPhone = new SQL()
                .SELECT("id")
                .FROM("g_user")
                .WHERE("phone=#{phone}");
        SQL selectAllById = new SQL()
                .SELECT("*")
                .FROM("g_user")
                .WHERE("id=("+selectByPhone.getSelf()+")");
        return selectAllById.toString();
    }
    /**
     * 通过userName查询所有信息
     * @param userName
     * @return
     */
    public String selectAllByUserName(String userName) {
        SQL selectAllByUserName = new SQL()
                .SELECT("*")
                .FROM("g_user gu")
                .LEFT_OUTER_JOIN("g_user_role gur on gu.id = gur.user_id")
                .LEFT_OUTER_JOIN("g_role gr on gr.id=gur.role_id")
                .LEFT_OUTER_JOIN("g_res_role grr on gr.id = grr.role_id")
                .LEFT_OUTER_JOIN("g_resource gre on gre.id = grr.res_id")
                .WHERE("gu.user_name=#{userName}");
        return selectAllByUserName.toString();
    }
    /**
     * 创造一条数据
     */
    public String createUser(UserEntity userEntity){
        SQL createUser = new SQL()
                .INSERT_INTO("g_user")
                .VALUES("user_num","#{userNum}")
                .VALUES("user_name","#{userName}")
                .VALUES("phone","#{phone}")
                .VALUES("email","#{email}")
                .VALUES("`password`","#{password}")
                .VALUES("nick_name","#{nickName}")
                .VALUES("`lock`","#{lock}")
                .VALUES("create_time","#{createTime}")
                .VALUES("update_time","#{updateTime}")
                .VALUES("create_user_id","#{createUserId}")
                .VALUES("update_user_id","#{updateUserId}")
                .VALUES("rev","#{rev}")
                .VALUES("delete_flag","#{deleteFlag}");
        return createUser.toString();
    }
    /**
     * 给某人添加角色
     */
    public String addRoleForUser(Long roleId,Long userId){
        SQL addRoleForUser = new SQL()
                .INSERT_INTO("g_user_role")
                .VALUES("role_id","#{roleId}")
                .VALUES("user_id","#{userId}");
        return addRoleForUser.toString();
    }
}
