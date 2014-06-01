package com.projects.pro_passwords;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UsersDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_USERNAME,
			MySQLiteHelper.COLUMN_PASSWORD };

	public UsersDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public User createUser(String username, String password) {
		ContentValues userValues = new ContentValues();

		userValues.put(MySQLiteHelper.COLUMN_USERNAME, username);
		userValues.put(MySQLiteHelper.COLUMN_PASSWORD, password);

		long i = database.insert(MySQLiteHelper.TABLE_USERS, null, userValues);
		Cursor userCursor = database.query(MySQLiteHelper.TABLE_USERS,
				allColumns, MySQLiteHelper.COLUMN_USERNAME + " = '" + username
						+ "'", null, null, null, null);
		userCursor.moveToFirst();
		User newUser = cursorToUser(userCursor);
		userCursor.close();

		return newUser;
	}

	public void deleteUser(User user) {
		String username = user.getUsername();
		System.out.println("Comment deleted with id: " + username);
		database.delete(MySQLiteHelper.TABLE_USERS,
				MySQLiteHelper.COLUMN_USERNAME + " = " + username, null);
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_USERS, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			User user = cursorToUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return users;
	}

	public String getPassword(String username) {

		Cursor userCursor = database.query(MySQLiteHelper.TABLE_USERS,
				allColumns, MySQLiteHelper.COLUMN_USERNAME + " = '" + username
						+ "'", null, null, null, null);

		boolean found = userCursor.moveToFirst();
		if (found) {
			User user = cursorToUser(userCursor);
			userCursor.close();
			return user.getPassword();
		} else
			return null;
	}

	private User cursorToUser(Cursor cursor) {
		User user = new User();
		user.setUsername(cursor.getString(0));
		user.setPassword(cursor.getString(1));
		return user;
	}

}
