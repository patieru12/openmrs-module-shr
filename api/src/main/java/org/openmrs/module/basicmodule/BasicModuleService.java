package org.openmrs.module.basicmodule;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.basicmodule.model.ObsEncounterSync;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BasicModuleService extends OpenmrsService {

    @Transactional
    Integer saveOfflineTransaction(ObsEncounterSync obsEncounterSync);

}