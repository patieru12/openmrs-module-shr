package org.openmrs.module.basicmodule.db;

import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.module.basicmodule.model.ObsEncounterSync;
import org.openmrs.module.basicmodule.model.OfflineTransaction;
import org.openmrs.module.basicmodule.model.ShrOffline;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PrimaryCareDAO {


    public int saveTransaction(ObsEncounterSync a);

    public int saveShrOffline(ShrOffline shrOffline);

    public Patient getPatientById(Integer id);

    public List<ObsEncounterSync> getSyncData();

    public List<Order> getShrData(String upi);

    public ShrOffline getShrDataById(String upi,String type);

    public List<ShrOffline> getShrDataByUuid(String uuid);

    public Integer saveOfflineTransaction(OfflineTransaction offlineTransaction);
}
