package repository;

import Annotations.ID;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Scanner;

public class EntityRepositoryImpl<T> implements EntityRepository<T> {

    private final Gson gson;

    private final String fileName;

    private T[] entityReposit;
    private final Class<T[]> readType;

    public EntityRepositoryImpl(Class<T[]> readType, String fileName) {
        this.gson = new Gson();
        this.readType = readType;
        this.fileName = fileName;
        this.read();
    }

    private void read() {
        InputStream InputStream = null;
        try { InputStream = new FileInputStream(fileName);}
        catch (FileNotFoundException e){
                throw new RuntimeException(e);
            }

        Scanner sc = new Scanner(InputStream);
        String temp = "";
        temp = sc.nextLine();
        entityReposit = gson.fromJson(temp, readType);

        sc.close();
    }

    private void write () {
        String stringForWrite = gson.toJson(entityReposit);
        OutputStream outputStream = null;
        try { outputStream = new FileOutputStream(fileName);}
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PrintStream writer = new PrintStream(outputStream);
        writer.println(stringForWrite);

        writer.close();
    }

    @Override
    public void updateEntityInRepo(T entity) {
        Field fieldID = getFieldID();
        for (int i = 0, entityRepositLength = entityReposit.length; i < entityRepositLength; i++) {
            try {
                if (fieldID.get(entityReposit[i]).equals(fieldID.get(entity))) {
                    entityReposit[i] = entity;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
                }
        }
        write();
    }

    public void updateEntityInRepo(T entitySource, T entityDestin) {
        Field fieldID = getFieldID();
        for (int i = 0, entityRepositLength = entityReposit.length; i < entityRepositLength; i++) {
            try {
                if (fieldID.get(entityReposit[i]).equals(fieldID.get(entitySource))) {
                    entityReposit[i] = entitySource;
                }
            } catch (IllegalAccessException e) { throw new RuntimeException(e);}
            try {
                if (fieldID.get(entityReposit[i]).equals(fieldID.get(entityDestin))) {
                    entityReposit[i] = entityDestin;
                }
            } catch (IllegalAccessException e) {throw new RuntimeException(e);}
        }
        write();
    }

    private String getTypeID() {
        Field[] fields = entityReposit[0].getClass().getDeclaredFields();
        String nameID = "";
        for (Field tempField : fields) {
            if (tempField.isAnnotationPresent(ID.class)) {
                try {
                    nameID = (String) tempField.get(entityReposit[0]);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nameID;
    }

    private Field getFieldID() {
        Field[] fields = entityReposit[0].getClass().getDeclaredFields();
        for (Field tempField : fields) {
            if (tempField.isAnnotationPresent(ID.class)) {
                return tempField;
            }
        }
        return null;
    }

    public T getEntityByKey(String key){
        Field fieldID = getFieldID();
        for (T entity : entityReposit) {
            try { if (key.equals(fieldID.get(entity))){
                    return entity;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
