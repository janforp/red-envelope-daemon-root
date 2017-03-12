package com.hongbao.api.service;

import com.hongbao.api.config.Config;
import com.hongbao.api.dao.ReCoinItemDAO;
import com.hongbao.api.model.ReCoinItem;
import com.hongbao.api.model.dto.CoinItemInfo;
import com.hongbao.api.model.dto.CoinItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Summer on 2016/10/9.
 */
@Service
public class HbCoinService {

    @Autowired
    private ReCoinItemDAO reCoinItemDAO;

    /**
     * 商城列表
     *
     * @return
     */
    public List<CoinItemInfo> selectItemList() {

        List<CoinItemInfo> list = new ArrayList<>();

        List<ReCoinItem> itemList = reCoinItemDAO.selectIndexPage();

        for (ReCoinItem item : itemList) {
            CoinItemInfo info = new CoinItemInfo();
            info.setItemImg(item.getItemImg());
            info.setItemCoin(item.getItemCoin());
            info.setItemTitle(item.getItemTitle());
            info.setItemUrl(Config.getRedBaseUrl() + "/c/p/coin/item/" + item.getItemId());
            list.add(info);
        }

        return list;

    }

    /**
     * 商城
     *
     * @return
     */
    public CoinItemModel selectItemModel() {

        List<CoinItemInfo> list = new ArrayList<>();

        List<ReCoinItem> itemList = reCoinItemDAO.selectIndexPage();
        for (ReCoinItem item : itemList) {
            CoinItemInfo info = new CoinItemInfo();
            info.setItemTitle(item.getItemTitle());
            info.setItemCoin(item.getItemCoin());
            info.setItemImg(item.getItemImg());
            info.setItemUrl(Config.getRedBaseUrl() + "/c/p/coin/item/" + item.getItemId());
            list.add(info);
        }

        CoinItemModel itemModel = new CoinItemModel();
        itemModel.setOrderId(2);
        itemModel.setTitle("金币商城");
        itemModel.setDesc("全场包邮");
        itemModel.setList(list);

        return itemModel;

    }

}
