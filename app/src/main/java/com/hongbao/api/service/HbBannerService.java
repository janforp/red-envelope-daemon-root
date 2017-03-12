package com.hongbao.api.service;

import com.hongbao.api.dao.ReBannerDAO;
import com.hongbao.api.model.dto.ReBannerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Summer on 16/8/12.
 */
@Service
public class HbBannerService {

    @Autowired
    private ReBannerDAO reBannerDAO;


    /**
     * 首页banner
     *
     * @param platform
     * @param version
     * @param packageName
     * @param channelName
     * @param flag
     * @return
     */
    public List<ReBannerInfo> getBannerList(int platform, String version, String packageName,
                                            String channelName, boolean flag) {

        return reBannerDAO.selectBannerByPlatform(platform, version, packageName, channelName, flag);

    }

}
