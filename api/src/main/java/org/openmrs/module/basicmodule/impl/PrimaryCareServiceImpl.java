package org.openmrs.module.basicmodule.impl;

import org.openmrs.Order;
import org.openmrs.Patient;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.basicmodule.db.PrimaryCareDAO;
import org.openmrs.module.basicmodule.model.ObsEncounterSync;
import org.openmrs.module.basicmodule.model.OfflineTransaction;
import org.openmrs.module.basicmodule.model.ShrOffline;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class PrimaryCareServiceImpl extends BaseOpenmrsService implements PrimaryCareService {

    private PrimaryCareDAO dao;

    public void setDao(PrimaryCareDAO dao) {
        this.dao = dao;
    }

    @Override
    public Integer saveTrans(ObsEncounterSync a) {
        return dao.saveTransaction(a);
    }

    @Override
    public Integer saveShrOffline(ShrOffline shrOffline) {
        return dao.saveShrOffline(shrOffline);
    }


    @Override
    public Patient getPatientById(Integer id) {
        return dao.getPatientById(id);
    }

    @Override
    public List<ObsEncounterSync> getSyncData(){
        return dao.getSyncData();
    }

    @Override
    public List<Order> getShrData(String upi) {
        return dao.getShrData(upi);
    }
    @Override
    public ShrOffline getShrDataById(String upi,String type){
        return dao.getShrDataById(upi,type);
    }
    @Override
    public List<ShrOffline> getShrDataByUuid(String uuid){
        return dao.getShrDataByUuid(uuid);
    }

    /***
     * Add Offline Transaction related Methods
     */
    public Integer saveOfflineTransaction(OfflineTransaction offlineTransaction){
        return dao.saveOfflineTransaction(offlineTransaction);
    }
    /*
    public Boolean isOnline(){
        //Check if openHIM can Answer Request
        boolean status = false;
        Socket sock = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.google.com", 80);

        try {
            sock.connect(address, 3000);
            if (sock.isConnected()) status = true;
        } catch (Exception e) {
            status = false;
        } finally {
            try {
                sock.close();
            } catch (Exception e) {
            }
        }
        return false;// status;
    }*/
}
