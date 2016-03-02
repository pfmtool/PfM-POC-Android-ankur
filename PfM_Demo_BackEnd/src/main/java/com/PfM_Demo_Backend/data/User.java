package com.PfM_Demo_Backend.data;

public class User {
	public static final String USER_PARENT_ENTITY_NAME = "UserParent";
	public static final String USER_PARENT_KEY_NAME = "UserParent";

	public static final String USER_ENTITY_NAME = "User";
	public static final String FIELD_NAME_NAME = "uName";
	public static final String FIELD_NAME_PASS = "pass";
	public static final String KEY_NAME = FIELD_NAME_NAME;

	public String mName;
	public String mPassword;


	public User(String _name, String _pass) {
		mName = _name;
		mPassword = _pass;
	}

}
