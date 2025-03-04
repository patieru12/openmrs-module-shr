package org.openmrs.module.basicmodule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.basicmodule.impl.PrimaryCareService;


public class PrimaryCareBusinessLogic {

    protected static final Log log = LogFactory.getLog(PrimaryCareBusinessLogic.class);

    /**
     * Convenience method that returns the handle to the primary care service instance.
     *
     * @return PrimaryCareService service instance
     */
    public static PrimaryCareService getService() {
        return Context.getService(PrimaryCareService.class);
    }


}
