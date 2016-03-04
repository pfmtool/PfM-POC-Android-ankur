package com.PfM_Demo_Backend.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Transaction;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDatastore {

	private static final Logger mLogger = Logger.getLogger(UserDatastore.class.getName());
	private static final DatastoreService mDatastore = DatastoreServiceFactory.getDatastoreService();

	private static Key getKey() {
		return KeyFactory.createKey(User.USER_PARENT_ENTITY_NAME, User.USER_PARENT_KEY_NAME);
	}


	public static boolean add(User user) {
		if (getUserByName(user.mName, null) != null) {
			mLogger.log(Level.INFO, "user exists");
			return false;
		}

		Key parentKey = getKey();


		Entity entity = new Entity(User.USER_ENTITY_NAME, user.mName,
				parentKey);
		entity.setProperty(User.FIELD_NAME_NAME, user.mName);
		entity.setProperty(User.FIELD_NAME_PASS, user.mPassword);


		mDatastore.put(entity);

		return true;
	}

	public static boolean ifUserExist(User user){
		if (getUserByName(user.mName, null) != null) {
			mLogger.log(Level.INFO, "user exists");
			return true;
		}
		return false;
	}

	public static boolean update(User contact) {
		Entity result = null;
		try {
			result = mDatastore.get(KeyFactory.createKey(getKey(),
					User.USER_ENTITY_NAME, contact.mName));
			result.setProperty(User.FIELD_NAME_PASS, contact.mPassword);


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
		Filter filter = new FilterPredicate(User.FIELD_NAME_NAME,
				FilterOperator.EQUAL, name);

		Query query = new Query(User.USER_ENTITY_NAME);
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

	public static ArrayList<User> query(String name) {
		ArrayList<User> resultList = new ArrayList<User>();
		if (name != null && !name.equals("")) {
			User contact = getUserByName(name, null);
			if (contact != null) {
				resultList.add(contact);
			}
		} else {
			Query query = new Query(User.USER_ENTITY_NAME);
			// get every record from datastore, no filter
			query.setFilter(null);
			// set query's ancestor to get strong consistency
			query.setAncestor(getKey());

			PreparedQuery pq = mDatastore.prepare(query);

			for (Entity entity : pq.asIterable()) {
				User contact = getContactFromEntity(entity);
				if (contact != null) {
					resultList.add(contact);
				}
			}
		}
		return resultList;
	}

	public static User getUserByName(String name, Transaction txn) {
		Entity result = null;
		try {
			result = mDatastore.get(KeyFactory.createKey(getKey(),
					User.USER_ENTITY_NAME, name));
		} catch (Exception ex) {

		}

		return getContactFromEntity(result);
	}
	public static boolean authenticateUser(User user) {
		Entity result = null;
		try {
			result = mDatastore.get(KeyFactory.createKey(getKey(), User.USER_ENTITY_NAME, user.mName));
			if(result!=null){
				if(user.mPassword.equals ((String)result.getProperty(User.FIELD_NAME_PASS))){
					return true;
				}
			}
		} catch (Exception ex) {
		}

		return false;
	}

	private static User getContactFromEntity(Entity entity) {
		if (entity == null) {
			return null;
		}

		return new User(
				(String) entity.getProperty(User.FIELD_NAME_NAME),
				(String) entity.getProperty(User.FIELD_NAME_PASS));
	}
}
