package com.hongbao.api.service.user;

import com.hongbao.api.dao.ReUserDAO;
import com.hongbao.api.model.ReUser;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Janita on 2016/12/30.
 * 用户相关操作
 */
@Service
public class UserService {

    @Autowired
    private ReUserDAO reUserDAO;

    /**
     * 把该用户拉黑
     * @param userId    被拉黑的用户id
     * @return          被拉黑用户的数量
     */
    public int pushToBlack(Integer userId){

        if (!StringUtil.isEmpty(userId)){
            ReUser user = reUserDAO.selectLockByUserId(userId);
            if (user != null){
                user.setUpdateTime(System.currentTimeMillis());
                user.setUserStatus(0);
                return reUserDAO.updateByPrimaryKeySelective(user);
            }
        }
        return 0;
    }
}
