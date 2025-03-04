package org.openmrs.module.basicmodule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.basicmodule.model.ObsEncounterSync;
import org.openmrs.scheduler.tasks.AbstractTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RunShrSchedulerSHR extends AbstractTask {
    protected final Log log = LogFactory.getLog(getClass());


    @Override
    public void execute() {
        run();
    }

    private final Logger LOGGER = LoggerFactory.getLogger(RunShrSchedulerSHR.class);

    public void run() {

        LOGGER.error("-----------OBS SIZE -- RUnning RunShrSchedulerSHR");

        LOGGER.error("-----------OBS getting completed --");
        try {
            List<ObsEncounterSync> obsEncounters = PrimaryCareBusinessLogic.getService().getSyncData();
            LOGGER.error(">>>>>>>>>>>>>>>>> SHR SYNC List size : " + obsEncounters.size());
            if (!obsEncounters.isEmpty()) {
                for (ObsEncounterSync obsEncounter : obsEncounters) {
                    Boolean status = processData(obsEncounter);
                    if (status) {
                        obsEncounter.setIsSynced(true);
                        obsEncounter.setSyncPushDate(new Date());
                        PrimaryCareBusinessLogic.getService().saveTrans(obsEncounter);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("-----------SHR SYNC ERROR", e);
        }
        LOGGER.error("-----------SHR SYNC ENDED");
    }

    private Boolean processData(ObsEncounterSync obsEncounter) {

        RestClientCustom restClientCustom = new RestClientCustom();
        // Boolean status = restClientCustom.syncData(obsEncounter.getType(), obsEncounter.getUuid());
        Boolean status = restClientCustom.syncData(obsEncounter);


        return status;
    }
}