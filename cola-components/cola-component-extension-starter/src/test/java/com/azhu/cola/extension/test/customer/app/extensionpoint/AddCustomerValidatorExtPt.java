package com.azhu.cola.extension.test.customer.app.extensionpoint;

import com.azhu.cola.extension.ExtensionPointI;
import com.azhu.cola.extension.test.customer.client.AddCustomerCmd;

/**
 * AddCustomerValidatorExtPt
 *
 * @author Frank Zhang
 * @date 2018-01-07 1:27 AM
 */
public interface AddCustomerValidatorExtPt extends ExtensionPointI {

    public void validate(AddCustomerCmd addCustomerCmd);
}
