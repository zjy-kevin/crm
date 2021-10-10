package com.yue.workbench.test;

import com.yue.crm.utils.ServiceFactory;
import com.yue.crm.utils.UUIDUtil;
import com.yue.crm.workbench.domain.Activity;
import com.yue.crm.workbench.domain.Tran;
import com.yue.crm.workbench.service.ActivityService;
import com.yue.crm.workbench.service.TranService;
import com.yue.crm.workbench.service.impl.ActivityServiceImpl;
import com.yue.crm.workbench.service.impl.TranServiceImpl;
import org.junit.Assert;
import org.junit.Test;

public class ActivityTest {
    @Test
    public void testSave(){
        Activity a=new Activity();
        a.setId(UUIDUtil.getUUID());
        a.setName("宣讲会");
        ActivityService as= (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag=as.save(a);
        Assert.assertEquals(flag,true);
    }
/*
    @Test
    public void testUpdate(){


    }

    @Test
    public void testUpdate1(){


    }*/

    @Test
    public void testSave2(){
        Tran t = new Tran();
        t.setId(UUIDUtil.getUUID());
        TranService ts = (TranService) ServiceFactory.getService(new TranServiceImpl());
        boolean flag=ts.save(t,"阿里巴巴");
        Assert.assertEquals(flag,true);
    }
}
