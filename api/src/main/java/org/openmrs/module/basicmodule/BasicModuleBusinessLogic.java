package org.openmrs.module.basicmodule;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;

public class BasicModuleBusinessLogic {

    protected static final Log log = LogFactory.getLog(BasicModuleBusinessLogic.class);

    /**
     * Convenience method that returns the handle to the primary care service instance.
     *
     * @return PrimaryCareService service instance
     */
    public static BasicModuleService getService() {
        return Context.getService(BasicModuleService.class);
    }

}

