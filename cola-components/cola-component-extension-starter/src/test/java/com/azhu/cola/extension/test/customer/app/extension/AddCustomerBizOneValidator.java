package com.azhu.cola.extension.test.customer.app.extension;

import com.azhu.cola.exception.BizException;
import com.azhu.cola.extension.Extension;
import com.azhu.cola.extension.test.customer.client.AddCustomerCmd;
import com.azhu.cola.extension.test.customer.client.Constants;
import com.azhu.cola.extension.test.customer.domain.CustomerType;
import com.azhu.cola.extension.test.customer.app.extensionpoint.AddCustomerValidatorExtPt;

/**
 * AddCustomerBizOneValidator
 *
 * @author Frank Zhang
 * @date 2018-01-07 1:31 AM
 */
@Extension(bizId = Constants.BIZ_1)
public class AddCustomerBizOneValidator implements AddCustomerValidatorExtPt {

    public void validate(AddCustomerCmd addCustomerCmd) {
        //For BIZ TWO CustomerTYpe could not be VIP
        if(CustomerType.VIP == addCustomerCmd.getCustomerDTO().getCustomerType())
            throw new BizException("Customer Type could not be VIP for Biz One");
    }
}
