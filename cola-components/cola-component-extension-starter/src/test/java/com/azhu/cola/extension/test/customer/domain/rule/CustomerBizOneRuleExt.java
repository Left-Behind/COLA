package com.azhu.cola.extension.test.customer.domain.rule;

import com.azhu.cola.exception.BizException;
import com.azhu.cola.extension.Extension;
import com.azhu.cola.extension.test.customer.client.Constants;
import com.azhu.cola.extension.test.customer.domain.CustomerEntity;
import com.azhu.cola.extension.test.customer.domain.SourceType;

/**
 * CustomerBizOneRuleExt
 *
 * @author Frank Zhang
 * @date 2018-01-07 12:10 PM
 */
@Extension(bizId = Constants.BIZ_1)
public class CustomerBizOneRuleExt implements CustomerRuleExtPt{

    @Override
    public boolean addCustomerCheck(CustomerEntity customerEntity) {
        if(SourceType.AD == customerEntity.getSourceType()){
            throw new BizException("Sorry, Customer from advertisement can not be added in this period");
        }
        return true;
    }
}
