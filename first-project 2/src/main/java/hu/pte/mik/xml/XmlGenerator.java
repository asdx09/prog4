package hu.pte.mik.xml;

import hu.pte.mik.exception.XmlSerializationException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XmlGenerator {

    /**
     * XML-t konvertál string formátumra.
     *
     * @param object Az objektum, amit szeretnénk XML-re alakítani.
     * @return Az objektum XML formátumra konvertálva.
     */
    public String convertToXml(Object object) {
        this.checkIfSerializable(object);

        try {
            StringBuilder sb = new StringBuilder("<?xml version=\"1.0\"?>").append(System.lineSeparator());

            this.process(object, sb);

            return sb.toString();
        } catch (Exception e) {
            throw new XmlSerializationException(e.getMessage(), e);
        }
    }

    /**
     * Ellenőrizzük, hogy az adott osztály feldolgozható-e, ha nem {@link XmlSerializationException}-t dobunk.
     *
     * @param object Az ellenőrizendő objektum.
     */
    private void checkIfSerializable(Object object) {
        if (object == null) {
            throw new XmlSerializationException("The xml object must not be null!");
        }

        Class<?> clazz = object.getClass();

        if (!clazz.isAnnotationPresent(XmlSerializable.class)) {
            throw new XmlSerializationException(clazz.getName() + " not annotated with @XmlSerializable");
        }
    }

    /**
     * Meghívja a feldoldozást.
     *
     * @param object        Az objektum, amit szeretnénk XMl-re alakítani.
     * @param sb            Ebben építjük fel az XML-t.
     * @throws IllegalAccessException    Reflection miatti exception.
     * @throws InvocationTargetException Reflection miatti exception.
     */
    private void process(Object object, StringBuilder sb) throws IllegalAccessException, InvocationTargetException {
        this.prepare(object);
        this.serialize(object, sb);
    }

    /**
     *
     * Összeállítja az objektumból az XML formátumot.
     *
     * @param object        Az objektum, amit szeretnénk XML-re alakítani.
     * @param sb            Ebben építjük fel az XML-t
     * @throws IllegalAccessException    Reflection miatti exception.
     * @throws InvocationTargetException Reflection miatti exception.
     */
    private void serialize(Object object, StringBuilder sb) throws IllegalAccessException, InvocationTargetException {
        Class<?> clazz = object.getClass();

        XmlSerializable xmlSerializable = clazz.getAnnotation(XmlSerializable.class);
        String classKey = "".equals(xmlSerializable.key()) ? clazz.getSimpleName().toUpperCase() : xmlSerializable.key().toUpperCase();

        this.appendStartTag(sb, classKey);
        sb.append(System.lineSeparator());
        List<Field> fields = new ArrayList<>();
        this.addParentFields(clazz, fields);
        Collections.addAll(fields, clazz.getDeclaredFields());

        for (Field field : fields) {
            if (field.isAnnotationPresent(XmlElement.class)) {
                boolean isAccessible = field.canAccess(object);
                field.setAccessible(true);
                XmlElement xmlElement = field.getAnnotation(XmlElement.class);
                String key = "".equals(xmlElement.key()) ? field.getName().toUpperCase() : xmlElement.key().toUpperCase();
                Object value = field.get(object);

                this.appendStartTag(sb, key);
                if (value != null && value.getClass().isAnnotationPresent(XmlSerializable.class)) {
                    sb.append(System.lineSeparator());
                    this.process(value, sb);
                } else {
                    sb.append(value);
                }
                this.appendEndTag(sb, key);
                field.setAccessible(isAccessible);
            }
        }

        this.appendEndTag(sb, classKey);

    }

    /**
     * Hozzáfűz a {@link StringBuilder}-hez egy kezdő XML tag-et.
     *
     * @param sb            A {@link StringBuilder}, amihez fűzzük a tag-et.
     * @param classKey      A kulcs, ami a neve lesz a tag-nek.
     */
    private void appendStartTag(StringBuilder sb, String classKey) {
        sb.append("<")
                .append(classKey)
                .append(">");
    }

    /**
     * Hozzáfűz a {@link StringBuilder}-hez egy záró XML tag-et.
     *
     * @param sb            A {@link StringBuilder}, amihez fűzzük a tag-et.
     * @param classKey      A kulcs, ami a neve lesz a tag-nek.
     */
    private void appendEndTag(StringBuilder sb, String classKey) {
        sb.append("</")
                .append(classKey)
                .append(">")
                .append(System.lineSeparator());
    }

    /**
     * Hozzáadja a listához az összes annotált ősosztály mezőjét.
     *
     * @param clazz  Az osztály, amely őseinek a mezőit hozzá akarjuk fűzni a listához.
     * @param fields A mező lista, amihez az értékeket adjuk.
     */
    private void addParentFields(Class<?> clazz, List<Field> fields) {
        Class<?> superClass = clazz.getSuperclass();
        if (superClass.isAnnotationPresent(XmlSerializable.class)) {
            Collections.addAll(fields, superClass.getDeclaredFields());
            this.addParentFields(superClass, fields);
        }
    }

    /**
     * Meghívja az objektum annotált metódusait.
     *
     * @param object Az objektum, amiben a metódusokat szeretnénk meghívni.
     * @throws InvocationTargetException Reflection miatti exception.
     * @throws IllegalAccessException    Reflection miatti exception.
     */
    private void prepare(Object object) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = object.getClass();

        List<Method> methods = new ArrayList<>();
        Collections.addAll(methods, clazz.getDeclaredMethods());
        this.addParentMethods(clazz, methods);
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSerialization.class)) {
//                boolean isAccessible = method.canAccess(object);
//                method.setAccessible(true);
//                method.invoke(object);
//                method.setAccessible(isAccessible);

                boolean isAccessible = method.canAccess(object);
                method.trySetAccessible();
                method.invoke(object);
                if(!isAccessible) {
                    method.setAccessible(false);
                }
            }
        }
    }

    /**
     * Hozzáadja a listához az összes annotált ősosztály metódusát.
     *
     * @param clazz   Az osztály, aminek az őseiben a metódusokat vizsgáljuk.
     * @param methods Ehhez a listához fűzzűk hozzá a metódust.
     */
    private void addParentMethods(Class<?> clazz, List<Method> methods) {
        Class<?> superClass = clazz.getSuperclass();
        if (superClass.isAnnotationPresent(XmlSerializable.class)) {
            Collections.addAll(methods, superClass.getDeclaredMethods());
            this.addParentMethods(superClass, methods);
        }
    }

}
