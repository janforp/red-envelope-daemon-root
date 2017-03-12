package com.hongbao.api.service;

import com.alibaba.fastjson.JSONArray;
import com.hongbao.api.dao.ReUserAndriodAppDAO;
import com.hongbao.api.dao.ReUserAndriodAppHistoryDAO;
import com.hongbao.api.model.ReUserAndriodApp;
import com.hongbao.api.model.ReUserAndriodAppHistory;
import com.hongbao.api.model.dto.AppInfo;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/10/14.
 */
@Service
public class AndriodAppService {

    @Autowired
    private ReUserAndriodAppDAO reUserAndriodAppDAO;
    @Autowired
    private ReUserAndriodAppHistoryDAO reUserAndriodAppHistoryDAO;

    /**
     * 保存用户本地app信息
     *
     * @param userId
     * @param app
     */
    public void saveAppData(int userId, String app) {

        // 删除app实时表
        reUserAndriodAppDAO.deleteByUserId(userId);

        if(!StringUtil.isEmpty(app)) {

            List<AppInfo> appInfoList = JSONArray.parseArray(app, AppInfo.class);

            String now = CommonMethod.fmtTime(System.currentTimeMillis());

            List<ReUserAndriodApp> list = new ArrayList<>();
            List<ReUserAndriodAppHistory> historyList = new ArrayList<>();

            for (AppInfo info : appInfoList) {

                String appName = info.getAppName();
                String appPackage = info.getAppPackage();

                // 实时表
                ReUserAndriodApp reUserAndriodApp = new ReUserAndriodApp();
                reUserAndriodApp.setUserId(userId);
                reUserAndriodApp.setAppName(appName);
                reUserAndriodApp.setAppPackage(appPackage);
                reUserAndriodApp.setRecordTime(now);
                list.add(reUserAndriodApp);

                // 查询是否已经存在历史表
                ReUserAndriodAppHistory history = reUserAndriodAppHistoryDAO.selectByAppPackage(userId, appPackage);
                if(history == null) {
                    history = new ReUserAndriodAppHistory();
                    history.setUserId(userId);
                    history.setAppName(appName);
                    history.setAppPackage(appPackage);
                    history.setRecordTime(now);
                    historyList.add(history);
                }

            }

            // 插入app实时表
            reUserAndriodAppDAO.insertBatch(list);

            // 插入app历史表
            if(historyList.size() > 0) {
                reUserAndriodAppHistoryDAO.insertBatch(historyList);
            }

        }

    }


}
