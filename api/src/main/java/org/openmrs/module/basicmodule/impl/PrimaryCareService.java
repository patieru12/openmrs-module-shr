package org.openmrs.module.basicmodule.impl;

import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.basicmodule.model.ObsEncounterSync;
import org.openmrs.module.basicmodule.model.OfflineTransaction;
import org.openmrs.module.basicmodule.model.ShrOffline;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PrimaryCareService extends OpenmrsService {

    @Transactional
    Integer saveTrans(ObsEncounterSync obsEncounterSync);

    @Transactional
    Integer saveShrOffline(ShrOffline shrOffline);

    @Transactional
    Patient getPatientById(Integer id);

    @Transactional
    List<ObsEncounterSync> getSyncData();

    @Transactional
    List<Order> getShrData(String upi);

    @Transactional
    ShrOffline getShrDataById(String upi,String type);
    @Transactional
    List<ShrOffline> getShrDataByUuid(String uuid);

    @Transactional
    Integer saveOfflineTransaction(OfflineTransaction offlineTransaction);

    //Boolean isOnline();

}