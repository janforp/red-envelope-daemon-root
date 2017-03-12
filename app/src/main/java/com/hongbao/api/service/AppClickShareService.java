package com.hongbao.api.service;

import com.hongbao.api.config.Config;
import com.hongbao.api.dao.*;
import com.hongbao.api.model.ClickShareMissionListVo;
import com.hongbao.api.model.ReArticleMission;
import com.hongbao.api.model.ReShareMission;
import com.hongbao.api.model.dto.ReArticleInfo;
import com.hongbao.api.model.vo.ArticleVo;
import com.hongbao.api.util.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/11/15.
 * app分享点击任务
 */
@Service
public class AppClickShareService {

    @Autowired
    private ReShareMissionDAO reShareMissionDAO;
    @Autowired
    private ReShareMissionDetailDAO reShareMissionDetailDAO;
    @Autowired
    private ReArticleMissionDAO reArticleMissionDAO;
    @Autowired
    private ReAccountDetailDAO reAccountDetailDAO;
    @Autowired
    private ReArticleReadDAO reArticleReadDAO;
    @Autowired
    private ReArticleClickDetailDAO reArticleClickDetailDAO;

    /**
     * 查询分享任务数量
     *
     * @return
     */
    public String selectShareCount() {
        String num = "";
        int count = reShareMissionDAO.selectShareCount();
        if(count > 0) {
            num = count + "";
        }
        return num;
    }


    /**
     * 获取分享点击任务列表
     * 剩余次数为0的也取出来了
     *
     * @return
     */
    public List<ClickShareMissionListVo> getList(){

        List<ReShareMission> missions = reShareMissionDAO.getListByStartTime();
        List<ClickShareMissionListVo> vos = new ArrayList<>(missions.size());

        for (ReShareMission mission : missions){

            ClickShareMissionListVo vo = new ClickShareMissionListVo();

            vo.setMissionId(mission.getMissionId());
            vo.setIcon(mission.getMissionIcon());
            vo.setTitle(mission.getMissionTitle());
            vo.setMoney(mission.getMoney()+"元/次");
            vo.setUrl(Config.getRedBaseUrl()+"/c/p/a/appShare/detail/"+mission.getMissionId());
            vo.setLeftTimes(mission.getLeftClickTimes());
            vo.setTotalMoney(mission.getMoney().multiply(new BigDecimal(mission.getTotalClickTimes())));
            int partNum = reShareMissionDetailDAO.selectPartInNum(mission.getMissionId());
            vo.setPartNum(partNum);

            vos.add(vo);
        }
        return vos;
    }


    /**
     * 查询分享任务数量
     *
     * @return
     */
    public String selectArticleCount() {
        String num = "";
        int count = reArticleMissionDAO.selectArticleCount(CommonMethod.fmtTime(System.currentTimeMillis()));
        if(count > 0) {
            num = count + "";
        }
        return num;
    }

    /**
     * 查询用户累计收益
     *
     * @param userId
     * @return
     */
    public BigDecimal selectTotalMoney(Integer userId) {
        BigDecimal money = new BigDecimal("0.00");
        if(userId != null) {
            BigDecimal totalMoney = reAccountDetailDAO.selectShareByUserId(userId);
            if(totalMoney != null) {
                money = totalMoney;
            }
        }
        return money;
    }


    /**
     * 查询文章列表
     *
     * @param articleId
     * @return
     */
    public List<ArticleVo> selectByArticleId(Long articleId) {

        List<ArticleVo> voList = new ArrayList<>();

        List<ReArticleMission> list = reArticleMissionDAO.selectArticleList(CommonMethod.fmtTime(System.currentTimeMillis()), articleId);
        for (ReArticleMission mission : list) {
            long id = mission.getArticleId();
            ArticleVo vo = new ArticleVo();
            vo.setArticleId(id);
            vo.setArticleTitle(mission.getArticleTitle());
            vo.setArticleIcon(mission.getArticleIcon());
            vo.setReadMoney(mission.getAppReadMoney()+"元");
            vo.setShareMoney(mission.getWxClickMoney()+"元/次");
            vo.setArticleUrl(Config.getRedBaseUrl() + "/c/p/article/detail/" + id + "/0");
            voList.add(vo);
        }

        return voList;

    }

    /**
     * 查询分享记录
     *
     * @param articleId
     * @param userId
     * @return
     */
    public List<ArticleVo> selectByArticleIdAndUserId(Long articleId, Integer userId) {

        List<ArticleVo> voList = new ArrayList<>();

        String nowTime = CommonMethod.fmtTime(System.currentTimeMillis());

        List<ReArticleInfo> list = reArticleReadDAO.selectByArticleIdAndUserId(articleId, userId);
        for (ReArticleInfo info : list) {

            long id = info.getArticleId();
            int count = reArticleClickDetailDAO.selectCountByArticleIdAndUserId(id, userId);
            BigDecimal readMoney = info.getAppReadMoney();
            BigDecimal clickMoney = info.getWxClickMoney();
            BigDecimal clickTotalMoney = clickMoney.multiply(new BigDecimal(count));
            BigDecimal totalMoney = readMoney.add(clickTotalMoney);

            int status = 1;

            int isEnd = info.getIsEnd();
            if(isEnd == 0) {
                status = 0;
            }else {
                int leftNum = info.getLeftClickTimes();
                if(leftNum == 0) {
                    status = 0;
                }else {
                    String endTime = info.getEndMissionTime();
                    int compare = nowTime.compareTo(endTime);
                    if(compare > 0) {
                        status = 0;
                    }
                }
            }

            ArticleVo vo = new ArticleVo();
            vo.setArticleId(id);
            vo.setArticleTitle(info.getArticleTitle());
            vo.setArticleIcon(info.getArticleIcon());
            vo.setReadMoney(readMoney + "元");
            vo.setShareMoney(clickMoney + "元/次");
            vo.setArticleUrl(Config.getRedBaseUrl() + "/c/p/article/detail/" + id + "/0");
            vo.setTotalMoney(totalMoney + "元");
            vo.setArticleStatus(status);
            voList.add(vo);
        }

        return voList;

    }

}
