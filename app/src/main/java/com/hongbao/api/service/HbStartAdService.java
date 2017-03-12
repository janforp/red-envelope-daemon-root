package com.hongbao.api.service;

import com.hongbao.api.dao.ReStartAdDAO;
import com.hongbao.api.model.dto.ReStartAdInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Summer on 16/8/15.
 */
@Service
public class HbStartAdService {

    @Autowired
    private ReStartAdDAO reStartAdDAO;

    /**
     * 启动页广告
     * @param platform
     * @return
     */
    public ReStartAdInfo getStartAd(int platform) {

        if(platform == 0) { // ios
            return reStartAdDAO.selectIosAdRandom();
        }else { // andriod
            return reStartAdDAO.selectAndriodAdRandom();
        }

    }

}
