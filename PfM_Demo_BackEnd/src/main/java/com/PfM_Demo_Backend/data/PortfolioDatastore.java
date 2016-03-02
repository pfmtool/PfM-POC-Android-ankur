package com.PfM_Demo_Backend.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class PortfolioDatastore {

    private static final Logger mLogger = Logger
            .getLogger(PortfolioDatastore.class.getName());
    private static final DatastoreService mDatastore = DatastoreServiceFactory
            .getDatastoreService();

    private static Key getKey() {
        return KeyFactory.createKey(Portfolio.PORTFOLIO_PARENT_ENTITY_NAME,
                Portfolio.PORTFOLIO_PARENT_KEY_NAME);
    }


    public static boolean add(Portfolio portfolio) {
        if (getPortfolioByName(portfolio.portfolioName, null) != null) {
            mLogger.log(Level.INFO, "portfolio exists");
            return false;
        }

        Key parentKey = getKey();


        Entity entity = new Entity(Portfolio.PORTFOLIO_ENTITY_NAME, portfolio.portfolioName,
                parentKey);
        entity.setProperty(Portfolio.FIELD_NAME_NAME, portfolio.portfolioName);
        entity.setProperty(Portfolio.FIELD_NAME_CUSIP, portfolio.cusip);


        mDatastore.put(entity);

        return true;
    }

    public static boolean update(Portfolio portfolio) {
        Entity result = null;
        try {
            result = mDatastore.get(KeyFactory.createKey(getKey(),
                    Portfolio.PORTFOLIO_ENTITY_NAME, portfolio.portfolioName));
            result.setProperty(Portfolio.FIELD_NAME_CUSIP, portfolio.cusip);


            mDatastore.put(result);
        } catch (Exception ex) {

        }
        return false;
    }

    public static boolean delete(String name) {
        // you can also use name to get key, then use the key to delete the
        // entity from datastore directly
        // because name is also the entity's key

        // query
        Query.Filter filter = new Query.FilterPredicate(Portfolio.FIELD_NAME_NAME,
                Query.FilterOperator.EQUAL, name);

        Query query = new Query(Portfolio.PORTFOLIO_ENTITY_NAME);
        query.setFilter(filter);

        // Use PreparedQuery interface to retrieve results
        PreparedQuery pq = mDatastore.prepare(query);

        Entity result = pq.asSingleEntity();
        boolean ret = false;
        if (result != null) {
            // delete
            mDatastore.delete(result.getKey());
            ret = true;
        }

        return ret;
    }

    public static ArrayList<Portfolio> query(String name) {
        ArrayList<Portfolio> resultList = new ArrayList<Portfolio>();
        if (name != null && !name.equals("")) {
            Portfolio portfolio = getPortfolioByName(name, null);
            if (portfolio != null) {
                resultList.add(portfolio);
            }
        } else {
            Query query = new Query(Portfolio.PORTFOLIO_ENTITY_NAME);
            // get every record from datastore, no filter
            query.setFilter(null);
            // set query's ancestor to get strong consistency
            query.setAncestor(getKey());

            PreparedQuery pq = mDatastore.prepare(query);

            for (Entity entity : pq.asIterable()) {
                Portfolio portfolio = getPortfolioFromEntity(entity);
                if (portfolio != null) {
                    resultList.add(portfolio);
                }
            }
        }
        return resultList;
    }

    public static Portfolio getPortfolioByName(String name, Transaction txn) {
        Entity result = null;
        try {
            result = mDatastore.get(KeyFactory.createKey(getKey(),
                    Portfolio.PORTFOLIO_ENTITY_NAME, name));
        } catch (Exception ex) {

        }

        return getPortfolioFromEntity(result);
    }

    private static Portfolio getPortfolioFromEntity(Entity entity) {
        if (entity == null) {
            return null;
        }

        return new Portfolio(
                (String) entity.getProperty(Portfolio.FIELD_NAME_NAME),
                (String) entity.getProperty(Portfolio.FIELD_NAME_CUSIP));
    }
}
