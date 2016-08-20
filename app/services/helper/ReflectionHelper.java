package services.helper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import play.Logger;

public class ReflectionHelper<T> {

	@SuppressWarnings("unchecked")
	public Class<T> getClass(Object entity) {

		Class<T> entityClass = null;

		try {
			Type genericSuper = entity.getClass().getGenericSuperclass();

			if (genericSuper instanceof ParameterizedType) {
				entityClass = (Class<T>) Class
						.forName(((ParameterizedType) genericSuper).getActualTypeArguments()[0].getTypeName());
			}

		} catch (ClassNotFoundException e) {
			Logger.error("Model not found", e);
		}

		return entityClass;
	}
	
	public T getInstance(Object entity){
		T entityEmpty = null;
		
		try {
			entityEmpty = getClass(entity).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			Logger.error("Model not found / cannot be instantated", e);
		}
		
		return entityEmpty;
	}
}
