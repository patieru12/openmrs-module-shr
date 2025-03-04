package org.openmrs.module.basicmodule.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.openmrs.*;
import org.openmrs.module.basicmodule.constants.Sync2Constants;
import org.openmrs.module.basicmodule.db.PrimaryCareDAO;
import org.openmrs.module.basicmodule.model.ObsEncounterSync;
import org.openmrs.module.basicmodule.model.OfflineTransaction;
import org.openmrs.module.basicmodule.model.ShrOffline;

import java.util.*;

public class HibernatePrimaryCareDAO implements PrimaryCareDAO {

    protected final Log log = LogFactory.getLog(getClass());

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int saveTransaction(ObsEncounterSync encounterSync) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(encounterSync);
        return 0;
    }

    @Override
    public int saveShrOffline(ShrOffline shrOffline) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(shrOffline);
        return 0;
    }

    @Override
    public Patient getPatientById(Integer id) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Patient.class).add(Restrictions.eq("patientId", id));
        List<Patient> result = crit.list();
        if (result != null && !result.isEmpty())
            return result.get(0);
        return null;
    }

    @Override
    public List<ObsEncounterSync> getSyncData() {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(ObsEncounterSync.class)
                .add(Restrictions.eq("isSynced", false));
        List<ObsEncounterSync> result = crit.list();
        if (result != null && !result.isEmpty()) {
            log.error("\n\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Not null");
            return result;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Order> getShrData(String upi) {

        Criteria crit = sessionFactory.getCurrentSession().createCriteria(Order.class)
                .add(Restrictions.eq("patient_id", upi));
        //return sessionFactory.getCurrentSession().createCriteria(Order.class).list();
        return (List<Order>) crit.list();
    }

    @Override
    public ShrOffline getShrDataById(String upi, String type) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(ShrOffline.class)
                .add(Restrictions.eq("upid", upi));
        if (!type.equalsIgnoreCase(Sync2Constants.SEARCH_SET_ALL)) {
            crit.add(Restrictions.eq("type", type));
        }

        List<ShrOffline> result = crit.list();
        if (result != null && !result.isEmpty()) {
            log.error("\n\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Not null");
            return result.get(0);
        }
        return null;
    }

    @Override
    public List<ShrOffline> getShrDataByUuid(String uuid) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(ShrOffline.class)
                .add(Restrictions.eq("encounterId", uuid));
        List<ShrOffline> result = crit.list();
        if (result != null && !result.isEmpty()) {
            //log.error("\n\n\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Not null");
            return result;
        }
        return null;
    }

    /***
     * Save the Offline Transaction From SHR module
     */
    public Integer saveOfflineTransaction(OfflineTransaction offlineTransaction){
        //log.error(offlineTransaction.getUuid());
        sessionFactory.getCurrentSession().saveOrUpdate(offlineTransaction);
        return 1;
    }
}
