package me.tuke.sktuke.util;

import java.lang.reflect.Field;

/**
 * Just a simple reflection class, just to not depend on Skript 2.2+ (I think it is the only thing I use from it)
 * @author Leandro Pereira
 *
 */
public class ReflectionUtils {
	
	/**
	 * Check if a class exists.
	 * @param clz - The class path, like 'org.bukkit.entity.Player'
	 * @return true if it exists
	 */
	public static boolean hasClass(String clz){
		try {
			Class.forName(clz);
			return true;
		} catch (Exception e){
			
		}
		return false;
		
	}
	/**
	 * Checks if a method exists or not
	 * @param clz - The class to check.
	 * @param method - The method's name
	 * @param ret -  The return of method, can be null if void
	 * @param parameters - The parameters of method, can be null if none
	 * @return - true if it exists
	 */
	public static boolean hasMethod(Class<?> clz, String method, Class<?> ret, Class<?>...parameters){
		try{
			clz.getClass().getDeclaredMethod(method, parameters);
		} catch(Exception e){
			
		}
		return false;
	}
	/**
	 * Get a class from a string.
	 * @param clz - The string path of a class
	 * @return The class
	 */
	public static Class<?> getClass(String clz){
		try {
			return Class.forName(clz);
		} catch (Exception e){
			
		}
		return null;
	}
	/**
	 * Return a new instance of a class.
	 * @param clz - The class
	 * @return A instance object of clz.
	 */
	public static <T> T newInstance(Class<T> clz){
		try {
			return clz.newInstance();
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Use to set a object from a private field.
	 * @param from - The class to set the field
	 * @param obj - The instance of class, you can use null if the field is static.
	 * @param field - The field name
	 * @return True if successful.
	 */
	public static <T> boolean setField(Class<T> from, Object obj, String field, Object newValue){
		try {
			Field f = from.getDeclaredField(field);
			f.setAccessible(true);
			f.set(obj, newValue);
			return true;
		} catch (Exception e){
			
		}
		return false;
	}
	/**
	 * Use to get a object from a private field. If it will return null in case it was unsuccessful.
	 * @param from - The class to get the field
	 * @param obj - The instance of class, you can use null if the field is static.
	 * @param field - The field name
	 * @return The object value.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getField(Class<T> from, Object obj, String field){
		try{
			Field f = from.getDeclaredField(field);
			f.setAccessible(true);
			return (T) f.get(obj);
		} catch (Exception e){
			
		}
		return null;
		
	}
}
