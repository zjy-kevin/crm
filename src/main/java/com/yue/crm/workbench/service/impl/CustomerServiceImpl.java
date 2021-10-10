package com.yue.crm.workbench.service.impl;

import com.yue.crm.utils.SqlSessionUtil;
import com.yue.crm.workbench.dao.CustomerDao;
import com.yue.crm.workbench.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
        private CustomerDao customerDao= SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);

        public List<String> getCustomerName(String name) {
                List<String> sList=customerDao.getCustomerName(name);
                return sList;
        }
}
