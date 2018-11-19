/**
 * <p>Company: 恒生电子股份有限公司</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Project: 投资赢家移动理财终端5.0</p>
 */
package com.example.hspcadmin.htmlproject.util;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.hundsun.armo.sdk.utils.HsEncrypt;

import java.util.ArrayList;
import java.util.Locale;

/**
 * 数据库操作类
 * 
 * @author yukk
 * 
 */
public class DBUtils {
	
	private static final String DATABASE_NAME = "db_xnzq";
	private static final String DBTABLE = "tbkeyvalue";
	private static final String KEY_KEY = "key";
	private static final String KEY_VALUE = "value";
	private static final String KEY_OTHER = "other";
	private static final String CREATETABLESTR = "CREATE TABLE IF NOT EXISTS " + DBTABLE + " (" + KEY_KEY + " TEXT PRIMARY KEY," + KEY_VALUE + " TEXT," + KEY_OTHER + " TEXT)";
	// 执行open（）打开数据库时，保存返回的数据库对象
	private SQLiteDatabase mSQLiteDatabase = null;
	private SQLiteOpenHelper mSqlOpenHelper;	
	private Context mContext;

	//新增functions表
	private static final String DBTABLE_FUNCTIONS = "functions";
	private static final String KEY_ID = "f_id";
	private static final String KEY_GROUP = "f_group";
	private static final String KEY_NAME = "f_name";
	private static final String KEY_TYPE = "f_type";
	private static final String KEY_MARKET = "f_market";
	private static final String KEY_ENABLE = "f_enable";
	private static final String CREATETABLESTR_FUNCTIONS = "CREATE TABLE IF NOT EXISTS " + DBTABLE_FUNCTIONS + " (" 
	+ KEY_ID + " TEXT," + KEY_GROUP + " NUMERIC," + KEY_NAME + " TEXT," + KEY_TYPE + " TEXT," + KEY_MARKET + " TEXT," + KEY_ENABLE + " TEXT)";
	
	
	/**
	 * 单实例
	 */
	private static DBUtils instance = null;

	/**
	 * 取实例
	 * 
	 * @param context
	 * @return
	 */
	public static DBUtils getInstance(Context context) {
		if (instance == null && context != null) {
			instance = new DBUtils(context);
			instance.open();
		}
		return instance;
	}

	private DBUtils(Context context) {
		this.mContext = context;
	}

	// 打开数据库，返回数据库对象
	public void open() throws SQLException {
		mSqlOpenHelper = new TzyjSQLHelper(mContext);
		mSQLiteDatabase = mSqlOpenHelper.getWritableDatabase();
		mSQLiteDatabase.setLocale(Locale.CHINESE);
	}

	// 关闭数据库
	public void close() {
		mSqlOpenHelper.close();
	}

	/* 插入一条数据 */
	public long insertData(String key, String value, String other) {
		return mSQLiteDatabase.insert(DBTABLE, KEY_KEY, getContentValues(key, value, other));
	}

	/* 删除一条数据 */
	public boolean deleteData(String key) {
		if (!ToolUtils.isNull(key)) {
			key = HsEncrypt.encode( key);
		}
		return mSQLiteDatabase.delete(DBTABLE, KEY_KEY + "=?", new String[]{key}) > 0;
	}

	/* 删除全部数据 */
	public boolean deleteAllData() {
		return mSQLiteDatabase.delete(DBTABLE, null, null) > 0;
	}

	/* 通过Cursor查询所有数据 */
	public Cursor fetchAllData() {
		return new HsCursor(mSQLiteDatabase.query(DBTABLE, new String[] { KEY_KEY, KEY_VALUE, KEY_OTHER }, null, null, null, null,null));
	}
	
	public boolean isEncrypt()
	{
	    if(mSQLiteDatabase != null)
	    {
	    	//-----------------------------------------------
	        String key = "";
	        if (!ToolUtils.isNull(key)) {
	            key = HsEncrypt.encode( key);
	        }
    	    Cursor mCursor =
    	        mSQLiteDatabase.query(true, DBTABLE, new String[] { KEY_KEY},
    	                KEY_KEY + "=?", new String[]{key}, null, null, null, null);
    	    if(mCursor.moveToFirst())
    	    {
    	        mCursor.close();
    	        return true;
    	    }
    	    mCursor.close();
	    }
	    
	    return false;
	}
	
	public void checkAndUpdate()
    {
        if(!isEncrypt())
        {
            Cursor cursor = mSQLiteDatabase.query(DBTABLE, new String[] { KEY_KEY, KEY_VALUE, KEY_OTHER }, null, null, null, null,null);
            if(cursor.moveToFirst())
            {
                do
                {
                    updateDataResovleUnEncyptKey(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                } while (cursor.moveToNext());
                
            }
            cursor.close();
            cursor = mSQLiteDatabase.query(DBTABLE_FUNCTIONS, new String[] { KEY_ID,KEY_GROUP,KEY_NAME,KEY_TYPE,KEY_MARKET,KEY_ENABLE}, null, null, null, null,null);
            if(cursor.moveToFirst())
            {
                do
                {
                    updateFunctionUnEncyptKey(cursor.getString(0),Integer.valueOf(cursor.getString(1)),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                } while (cursor.moveToNext());
                
            }
            cursor.close();
        }
    }
	

	/* 查询指定数据 */
	public Cursor fetchData(String key) throws SQLException {

		if (!ToolUtils.isNull(key)) {
			key = HsEncrypt.encode( key);
		}
		
		Cursor mCursor =

		mSQLiteDatabase.query(true, DBTABLE, new String[] { KEY_KEY, KEY_VALUE, KEY_OTHER },
				KEY_KEY + "=?", new String[]{key}, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return new HsCursor(mCursor);

	}

	public void replace(String key, String value, String other) {
		mSQLiteDatabase.replace(DBTABLE, KEY_KEY, getContentValues(key, value, other));
	}
	
	private ContentValues getContentValues(String key, String value, String other){
		
		if (!ToolUtils.isNull(key)) {
			key = HsEncrypt.encode( key);
		}
		
		if (!ToolUtils.isNull(other)) {
			other = HsEncrypt.encode( other );
		}
		
		if (!ToolUtils.isNull(value)) {
			value = HsEncrypt.encode( value);
		}
		
		
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_KEY, key);
		initialValues.put(KEY_VALUE, value);
		initialValues.put(KEY_OTHER, other);
		
		return initialValues;
	}

	/* 更新一条数据 */
	public boolean updateData(String key, String value, String other) {
		ContentValues initialValues = getContentValues(key, value, other);//new ContentValues();
		
		if (!ToolUtils.isNull(key)) {
			key = HsEncrypt.encode( key);
		}

		return mSQLiteDatabase.update(DBTABLE, initialValues, KEY_KEY + "=?", new String[]{key}) > 0;
	}
	
	public boolean updateDataResovleUnEncyptKey(String key, String value, String other) {
        ContentValues initialValues = getContentValues(key, value, other);//new ContentValues();
        
        return mSQLiteDatabase.update(DBTABLE, initialValues, KEY_KEY + "=?", new String[]{key}) > 0;
    }
	
	public boolean updateFunctionUnEncyptKey(String f_id, int group, String name, String type, String market, String enable){
        ArrayList<String> whereArgsArrayList = new ArrayList<String>();
        whereArgsArrayList.add(f_id);
        whereArgsArrayList.add("" + group);
        String queryString = KEY_ID + "=? and "+KEY_GROUP+"=?";
        ContentValues initialValues = getContentValuesFunction(f_id, group, name, type, market, enable);
//      if (!ToolUtils.isNull(f_id)) {//无效代码
//          f_id = HsEncrypt.encode( f_id);
//      }
        if(!ToolUtils.isNull(type))
        {
            queryString += " and "+KEY_TYPE+"=?";
            whereArgsArrayList.add(type);
        }
        return mSQLiteDatabase.update(DBTABLE_FUNCTIONS, initialValues, queryString, whereArgsArrayList.toArray(new String[0])) > 0;
    }

	public static final class TzyjSQLHelper extends SQLiteOpenHelper {

		public TzyjSQLHelper(Context context) {
			super(context, DATABASE_NAME, null, 2);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATETABLESTR);
			db.execSQL(CREATETABLESTR_FUNCTIONS);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int v1, int v2) {
//			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}

	/**
	 * 根据key取content
	 * 
	 * @param key
	 * @return
	 */
	public String getContent(String key) {
		Cursor cursor = fetchData(key);
		String retVal = null;
		if (cursor.moveToFirst()) {
			retVal = cursor.getString(1);
		}
		cursor.close();
		
		/*//解密
		if (!ToolUtils.isNull(retVal)) {
			retVal = HsEncrypt.decode(retVal);
		}*/
		
		return retVal;
	}

	public SQLiteDatabase getDataBase() {
		return mSQLiteDatabase;
	}

	
	/**
	 * 游标Cursor的封装，对内容作解密，现在只对getString函数作解密操作
	 * @author huangcheng
	 *
	 */
	public static class HsCursor implements Cursor {
		private Cursor mCursor;
		
		public HsCursor(Cursor cursor){
			mCursor = cursor;
		}

		@Override
		public String getString(int columnIndex) {
			String result = mCursor.getString(columnIndex);
			try
            {
			    if (!ToolUtils.isNull(result)) {
	                result = HsEncrypt.decode(result);
	            }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
			return result;
		}

		@Override
		public void close() {
			mCursor.close();
		}

		@Override
		public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {
			mCursor.copyStringToBuffer(columnIndex, buffer);
		}

		@Override
		public void deactivate() {
			mCursor.deactivate();
		}

		@Override
		public byte[] getBlob(int columnIndex) {
			return mCursor.getBlob(columnIndex);
		}

		@Override
		public int getColumnCount() {
			return mCursor.getColumnCount();
		}

		@Override
		public int getColumnIndex(String columnName) {
			return mCursor.getColumnIndex(columnName);
		}

		@Override
		public int getColumnIndexOrThrow(String columnName)
				throws IllegalArgumentException {
			return mCursor.getColumnIndexOrThrow(columnName);
		}

		@Override
		public String getColumnName(int columnIndex) {
			return mCursor.getColumnName(columnIndex);
		}

		@Override
		public String[] getColumnNames() {
			return mCursor.getColumnNames();
		}

		@Override
		public int getCount() {
			return mCursor.getCount();
		}

		@Override
		public double getDouble(int columnIndex) {
			return mCursor.getDouble(columnIndex);
		}

        @Override
		public Bundle getExtras() {
			return mCursor.getExtras();
		}

		@Override
		public float getFloat(int columnIndex) {
			return mCursor.getFloat(columnIndex);
		}

		@Override
		public int getInt(int columnIndex) {
			return mCursor.getInt(columnIndex);
		}

		@Override
		public long getLong(int columnIndex) {
			return mCursor.getLong(columnIndex);
		}

		@Override
		public int getPosition() {
			return mCursor.getPosition();
		}

		@Override
		public short getShort(int columnIndex) {
			return mCursor.getShort(columnIndex);
		}

		@Override
		public boolean getWantsAllOnMoveCalls() {
			return mCursor.getWantsAllOnMoveCalls();
		}

		@Override
		public void setExtras(Bundle bundle) {

		}

		@Override
		public boolean isAfterLast() {
			return mCursor.isAfterLast();
		}

		@Override
		public boolean isBeforeFirst() {
			return mCursor.isBeforeFirst();
		}

		@Override
		public boolean isClosed() {
			return mCursor.isClosed();
		}

		@Override
		public boolean isFirst() {
			return mCursor.isFirst();
		}

		@Override
		public boolean isLast() {
			return mCursor.isLast();
		}

		@Override
		public boolean isNull(int columnIndex) {
			return mCursor.isNull(columnIndex);
		}

		@Override
		public boolean move(int offset) {
			return mCursor.move(offset);
		}

		@Override
		public boolean moveToFirst() {
			return mCursor.moveToFirst();
		}

		@Override
		public boolean moveToLast() {
			return mCursor.moveToLast();
		}

		@Override
		public boolean moveToNext() {
			return mCursor.moveToNext();
		}

		@Override
		public boolean moveToPosition(int position) {
			return mCursor.moveToPosition(position);
		}

		@Override
		public boolean moveToPrevious() {
			return mCursor.moveToPrevious();
		}

		@Override
		public void registerContentObserver(ContentObserver observer) {
			mCursor.registerContentObserver(observer);			
		}

		@Override
		public void registerDataSetObserver(DataSetObserver observer) {
			mCursor.registerDataSetObserver(observer);			
		}

		@Override
		public boolean requery() {
			return mCursor.requery();
		}

		@Override
		public Bundle respond(Bundle extras) {
			return mCursor.respond(extras);
		}

		@Override
		public void setNotificationUri(ContentResolver cr, Uri uri) {
			mCursor.setNotificationUri(cr, uri);
		}
        public Uri getNotificationUri() {
            return null;
        }
		@Override
		public void unregisterContentObserver(ContentObserver observer) {
			mCursor.unregisterContentObserver(observer);
		}

		@Override
		public void unregisterDataSetObserver(DataSetObserver observer) {
			mCursor.unregisterDataSetObserver(observer);
		}

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
		public int getType(int columnIndex) {
			// TODO Auto-generated method stub
			return mCursor.getType(columnIndex);
		}
	}
	
	private ContentValues getContentValuesFunction(String f_id, int group, String name, String type, String market, String enable){
		
		if (!ToolUtils.isNull(f_id)) {
			f_id = HsEncrypt.encode( f_id);
		}
		
		if (!ToolUtils.isNull(name)) {
			name = HsEncrypt.encode( name );
		}
		
		if (!ToolUtils.isNull(type)) {
			type = HsEncrypt.encode( type );
		}
		
		if (!ToolUtils.isNull(market)) {
			market = HsEncrypt.encode( market);
		}
		
		if (!ToolUtils.isNull(enable)) {
			enable = HsEncrypt.encode( enable);
		}
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ID, f_id);
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_GROUP, group);
		initialValues.put(KEY_TYPE, type);
		initialValues.put(KEY_MARKET, market);
		initialValues.put(KEY_ENABLE, enable);
		
		return initialValues;
	}
	
	public long insertFunction(String f_id, int group, String name, String type, String market, String enable){
		return mSQLiteDatabase.insert(DBTABLE_FUNCTIONS, null, getContentValuesFunction(f_id, group, name, type, market, enable));
	}
	
	public boolean updateFunction(String f_id, int group, String name, String type, String market, String enable){
	    ArrayList<String> whereArgsArrayList = new ArrayList<String>();
	    
		ContentValues initialValues = getContentValuesFunction(f_id, group, name, type, market, enable);
//		if (!ToolUtils.isNull(f_id)) {//无效代码
//			f_id = HsEncrypt.encode( f_id);
//		}
		
		if (!ToolUtils.isNull(f_id)) {
            f_id = HsEncrypt.encode( f_id);
        }
        
        if (!ToolUtils.isNull(type)) {
            type = HsEncrypt.encode( type);
        }
        
        whereArgsArrayList.add(f_id);
        whereArgsArrayList.add("" + group);
        String queryString = KEY_ID + "=? and "+KEY_GROUP+"=?";
		
		if(!ToolUtils.isNull(type))
		{
			queryString += " and "+KEY_TYPE+"=?";
			whereArgsArrayList.add(type);
		}
		return mSQLiteDatabase.update(DBTABLE_FUNCTIONS, initialValues, queryString, whereArgsArrayList.toArray(new String[0])) > 0;
	}
	
	public Cursor queryFunction(String f_id, int group){
	    ArrayList<String> whereArgsArrayList = new ArrayList<String>();
	    
	    if (!ToolUtils.isNull(f_id)) {
            f_id = HsEncrypt.encode( f_id);
        }
        
	    whereArgsArrayList.add("" + group);
		String where = KEY_GROUP +" = ?";
		if(f_id != null){
		    whereArgsArrayList.add(f_id);
			where += " and "+KEY_ID+" =?";
		}
		return new HsCursor(mSQLiteDatabase.query(DBTABLE_FUNCTIONS, new String[] { KEY_ID, KEY_GROUP, KEY_NAME, KEY_TYPE, KEY_MARKET, KEY_ENABLE }, where, whereArgsArrayList.toArray(new String[0]), null, null,null));
	}
	
	public Cursor queryFunction(String f_id, int group,String type){
	    ArrayList<String> whereArgsArrayList = new ArrayList<String>();
	    
	    if (!ToolUtils.isNull(f_id)) {
            f_id = HsEncrypt.encode( f_id);
        }
        
        if (!ToolUtils.isNull(type)) {
            type = HsEncrypt.encode( type);
        }
	    
        whereArgsArrayList.add("" + group);
		String where = KEY_GROUP +" = ?";
		if(f_id != null){
	        whereArgsArrayList.add(f_id);
			where += " and "+KEY_ID+" =?";
		}
		if(type != null){
		    whereArgsArrayList.add(type);
			where += " and "+KEY_TYPE+" =?";
		}
		return new HsCursor(mSQLiteDatabase.query(DBTABLE_FUNCTIONS, new String[] { KEY_ID, KEY_GROUP, KEY_NAME, KEY_TYPE, KEY_MARKET, KEY_ENABLE }, where, whereArgsArrayList.toArray(new String[0]), null, null,null));
	}
	
	public String getVersionFunction(int group){
		String version = getFunction("version", group);
		if(version == null)
			version = "0";
		return version;
	}
	
	public String getFunction(String f_id, int group){
		String ret = null;
		Cursor cursor = queryFunction(f_id, group);
		if (cursor.moveToFirst()) {
			ret = cursor.getString(cursor.getColumnIndex(KEY_ENABLE));
		}
		cursor.close();
		
		/*//解密
		if (!ToolUtils.isNull(ret)) {
			ret = HsEncrypt.decode(ret);
		}*/
		return ret;
	}

	/**
	 * 
	 * @param f_id
	 * @param group
	 * @param type
	 * @return
	 */
	public Object getFunction(String f_id, int group, String type) {
		String ret = null;
		Cursor cursor = queryFunction(f_id, group,type);
		if (cursor.moveToFirst()) {
			ret = cursor.getString(cursor.getColumnIndex(KEY_ENABLE));
		}
		cursor.close();
		
		/*//解密
		if (!ToolUtils.isNull(ret)) {
			ret = HsEncrypt.decode(ret);
		}*/
		return ret;
	}
	
}
