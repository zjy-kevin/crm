package com.yue.crm.workbench.service.impl;

import com.yue.crm.utils.DateTimeUtil;
import com.yue.crm.utils.SqlSessionUtil;
import com.yue.crm.utils.UUIDUtil;
import com.yue.crm.workbench.dao.CustomerDao;
import com.yue.crm.workbench.dao.TranDao;
import com.yue.crm.workbench.dao.TranHistoryDao;
import com.yue.crm.workbench.domain.Customer;
import com.yue.crm.workbench.domain.Tran;
import com.yue.crm.workbench.domain.TranHistory;
import com.yue.crm.workbench.service.TranService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranServiceImpl implements TranService {
    private TranDao tranDao = SqlSessionUtil.getSqlSession().getMapper(TranDao.class);
    private TranHistoryDao tranHistoryDao = SqlSessionUtil.getSqlSession().getMapper(TranHistoryDao.class);
    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);


    public boolean save(Tran t, String customerName) {


        boolean flag = true;

        Customer cus = customerDao.getCustomerByName(customerName);

        //如果cus为null，需要创建客户
        if(cus==null){

            cus = new Customer();
            cus.setId(UUIDUtil.getUUID());
            cus.setName(customerName);
            cus.setCreateBy(t.getCreateBy());
            cus.setCreateTime(DateTimeUtil.getSysTime());
            cus.setContactSummary(t.getContactSummary());
            cus.setNextContactTime(t.getNextContactTime());
            cus.setOwner(t.getOwner());
            //添加客户
            int count1 = customerDao.save(cus);
            if(count1!=1){
                flag = false;
            }

        }

        //通过以上对于客户的处理，不论是查询出来已有的客户，还是以前没有我们新增的客户，总之客户已经有了，客户的id就有了
        //将客户id封装到t对象中
        t.setCustomerId(cus.getId());

        //添加交易
        int count2 = tranDao.save(t);
        if(count2!=1){
            flag = false;
        }

        //添加交易历史
        TranHistory th = new TranHistory();
        th.setId(UUIDUtil.getUUID());
        th.setTranId(t.getId());
        th.setStage(t.getStage());
        th.setMoney(t.getMoney());
        th.setExpectedDate(t.getExpectedDate());
        th.setCreateTime(DateTimeUtil.getSysTime());
        th.setCreateBy(t.getCreateBy());
        int count3 = tranHistoryDao.save(th);
        if(count3!=1){
            flag = false;
        }

        return flag;
    }

    public Tran detail(String id) {
        Tran t=tranDao.detail(id);
        return t;
    }

    public List<TranHistory> getHistoryListByTranId(String tranId) {
        List<TranHistory> thList=tranHistoryDao.getHistoryListByTranId(tranId);

        return thList;
    }

    public boolean changeStage(Tran t) {

        boolean flag = true;

        //改变交易阶段
        int count1 = tranDao.changeStage(t);
        if(count1!=1){

            flag = false;

        }

        //交易阶段改变后，生成一条交易历史
        TranHistory th = new TranHistory();
        th.setId(UUIDUtil.getUUID());
        th.setCreateBy(t.getEditBy());
        th.setCreateTime(DateTimeUtil.getSysTime());
        th.setExpectedDate(t.getExpectedDate());
        th.setMoney(t.getMoney());
        th.setTranId(t.getId());
        //添加交易历史
        int count2 = tranHistoryDao.save(th);
        if(count2!=1){

            flag = false;

        }

        return flag;
    }

    public Map<String, Object> getCharts() {
        int total=tranDao.getTotal();
        List<Map<String,Object>> dataList=tranDao.getCharts();
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",total);
        map.put("dataList",dataList);

        return map;
    }
}
