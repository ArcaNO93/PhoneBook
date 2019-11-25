package com.example.phonebook.model.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.example.phonebook.model.data.Contact;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "contacts.db";
    private static String DB_PATH = "";
    private static SQLiteDatabase mDB;
    private static boolean mUpdateNeed = false;
    private final Context mContext;

    private static final String CONTACT_NAME = "NAME";
    private static final String CONTACT_PHONE = "PHONE";
    private static final String CONTACT_EMAIL = "EMAIL";
    private static final String CONTACT_ADDRESS = "ADDRESS";

    DataBaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;
        copyDB();
        this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mUpdateNeed = true;
    }

    public void copyDB() {
        if(new File(DB_PATH + DB_NAME).exists()) {
          this.getReadableDatabase();
          this.close();
          try {
              copyDBFile();
          } catch (IOException e) {
              throw new Error("Copy DB error");
          }
        }
    }

    public void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;

        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);

        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public void updateDB() {
        if (mUpdateNeed) {
            File dbFile = new File(DB_PATH + DB_NAME);

            if (dbFile.exists())
                dbFile.delete();

            copyDB();

            mUpdateNeed = false;
        }
    }

    public boolean openDB() throws SQLException {
        mDB = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDB != null;
    }

    @Override
    public synchronized void close() {
        if (mDB != null)
            mDB.close();

        super.close();
    }

    public void addContact(Contact contact) {
        ContentValues cv = new ContentValues();

        cv.put(CONTACT_NAME, contact.getContactName());
        cv.put(CONTACT_PHONE, contact.getContactPhone());
        cv.put(CONTACT_EMAIL, contact.getContactEmail());
        cv.put(CONTACT_ADDRESS, contact.getContactAddress());

        mDB.insert("contacts", null, cv);
    }

    public Contact getContact(String _name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("contacts", new String[] { CONTACT_NAME, CONTACT_PHONE, CONTACT_EMAIL, CONTACT_ADDRESS },
                CONTACT_NAME + "=?", new String[] { _name }, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact();
        contact.setContactName(cursor.getString(0));
        contact.setContactPhone(cursor.getString(1));
        contact.setContactEmail(cursor.getString(2));
        contact.setContactAddress(cursor.getString(3));
        cursor.close();

        return contact;
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM " + "contacts";

        Cursor cursor = mDB.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setContactName(cursor.getString(0));
                contact.setContactPhone(cursor.getString(1));
                contact.setContactEmail(cursor.getString(2));
                contact.setContactAddress(cursor.getString(3));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    public void updateContact(Contact contact) {
        ContentValues cv = new ContentValues();

        cv.put(CONTACT_NAME, contact.getContactName());
        cv.put(CONTACT_PHONE, contact.getContactPhone());
        cv.put(CONTACT_EMAIL, contact.getContactEmail());
        cv.put(CONTACT_ADDRESS, contact.getContactAddress());

        mDB.update("contacts", cv, CONTACT_NAME + " = ?", new String[] { String.valueOf(contact.getContactName()) });
    }

    public void deleteContact(Contact contact) {
        mDB.delete("contacts", CONTACT_NAME + " = ?", new String[] { String.valueOf(contact.getContactName()) });
    }

    public void deleteAllContacts() {
        mDB.delete("contacts", null, null);
    }
}
