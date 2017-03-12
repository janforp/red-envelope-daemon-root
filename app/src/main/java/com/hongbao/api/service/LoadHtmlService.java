package com.hongbao.api.service;

import com.hongbao.api.dao.ReLoadHtmlDAO;
import com.hongbao.api.dao.ReLoadHtmlDetailDAO;
import com.hongbao.api.model.ReLoadHtml;
import com.hongbao.api.model.ReLoadHtmlDetail;
import com.hongbao.api.util.CommonMethod;
import com.hongbao.api.util.JsonUtil;
import com.hongbao.api.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/11/22.
 */
@Service
public class LoadHtmlService {

    @Autowired
    private ReLoadHtmlDAO reLoadHtmlDAO;
    @Autowired
    private ReLoadHtmlDetailDAO reLoadHtmlDetailDAO;


    /**
     * 查询网页链接列表
     * 个别的链接有时间段要求
     * 每个链接-设备编号只存在一条
     * @param deviceId
     * @return
     */
    public List<ReLoadHtml> selectHtmlList(String deviceId) {
        List<ReLoadHtml> list = new ArrayList<>();
        String now = CommonMethod.fmtHMS(System.currentTimeMillis());
        List<ReLoadHtml> htmlList = reLoadHtmlDAO.selectHtmlUtl(deviceId);
        for (ReLoadHtml html : htmlList) {
            String startTime = html.getStartTime();
            String endTime = html.getEndTime();
            if(!StringUtil.isEmpty(startTime) && !StringUtil.isEmpty(endTime)) {
                if(now.compareTo(startTime) >=0 && now.compareTo(endTime) <=0) {
                    list.add(html);
                }
            }else {
                list.add(html);
            }
        }
        return list;
    }


    /**
     * 打开网页成功
     *
     * @param deviceId
     * @param htmlId
     * @return
     */
    public String success(String deviceId, long htmlId) {

        // 记录明细
        ReLoadHtmlDetail detail = new ReLoadHtmlDetail();
        detail.setDeviceId(deviceId);
        detail.setHtmlId(htmlId);
        detail.setCreateTime(CommonMethod.fmtTime(System.currentTimeMillis()));
        reLoadHtmlDetailDAO.insert(detail);

        // 更新次数
        ReLoadHtml reLoadHtml = reLoadHtmlDAO.selectLockByHtmlId(htmlId);
        int totalNum = reLoadHtml.getTotalNum();
        int completeNum = reLoadHtml.getCompleteNum() + 1;

        ReLoadHtml html = new ReLoadHtml();
        html.setHtmlId(htmlId);
        html.setCompleteNum(completeNum);
        html.setUpdateTime(CommonMethod.fmtTime(System.currentTimeMillis()));
        if(completeNum >= totalNum) {
            html.setHtmlStatus(0);
        }
        reLoadHtmlDAO.updateByPrimaryKeySelective(html);

        return JsonUtil.buildSuccessJson();
    }


}
