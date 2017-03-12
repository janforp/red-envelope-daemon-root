package com.hongbao.api.service;

import com.hongbao.api.dao.ReNavigationDAO;
import com.hongbao.api.model.ReNavigation;
import com.hongbao.api.model.dto.ReNavigationInfo;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 16/8/15.
 */
@Service
public class HbNavigationService {

    @Autowired
    private ReNavigationDAO reNavigationDAO;


    /**
     * 首页导航栏
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @return
     */
    public List<ReNavigationInfo> getNavigationList(int platform, String version, String packageName, String channelName, boolean flag) {

        return reNavigationDAO.selectNavigationByPlatform(platform, version, packageName, channelName, flag);

    }

}
