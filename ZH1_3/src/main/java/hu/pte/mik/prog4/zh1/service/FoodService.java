package hu.pte.mik.prog4.zh1.service;

import hu.pte.mik.prog4.zh1.exceptions.ZH1XmlException;
import hu.pte.mik.prog4.zh1.model.Food;
import hu.pte.mik.prog4.zh1.repository.FoodRepository;
import hu.pte.mik.prog4.zh1.xml.ZH1Element;
import hu.pte.mik.prog4.zh1.xml.ZH1Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class FoodService {

    private static final Logger LOGGER = Logger.getLogger(FoodService.class.toString());

    private final FoodRepository foodRepository;

    public FoodService() {
        this.foodRepository = FoodRepository.getInstance();
    }

    public Food findById(Long id) {
        return this.foodRepository.findById(id);
    }

    public List<Food> findAll() {
        return this.foodRepository.findAll();
    }

    public Food save(String restaurantName, String foodName, String price) {
        return this.foodRepository.create(restaurantName, foodName, price);
    }

    public Food delete(Long id) {
        return this.foodRepository.delete(id);
    }

    public void convertToXml(Object object) {
        this.checkIfSerializable(object);

        StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\"?>").append(System.lineSeparator());
        try {
            this.serialize(object, stringBuilder);
        } catch (IllegalAccessException e) {
            //TODO Dobjon ZH1XmlException.
            throw new ZH1XmlException(e);
        }

        LOGGER.info(stringBuilder.toString());
    }

    private void checkIfSerializable(Object object) {
        if (object == null) {
            //TODO Dobjon ZH1XmlException-t üzenettel, hogy az object null.
            throw new ZH1XmlException("az object null");
        }

        //TODO Nézze meg, hogy a class annotávla van-e a ZH1Serializable annotációval és ha nem dobjon ZH1XmlException-t üzenettel.
        Class<?> clazz = object.getClass();
        if(!clazz.isAnnotationPresent(ZH1Serializable.class)){
            throw new ZH1XmlException("Az osztály nincs annotálva a ZH1Serializable osztállyal");
        }
    }

    private void serialize(Object object, StringBuilder stringBuilder) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        String name=clazz.getAnnotation(ZH1Serializable.class).text();
        if(Objects.equals(name, ""))
        {
            name=clazz.getSimpleName();
        }
        //TODO Ha a ZH1Serializable annotációnak van text nevű mezője, akkor szedje ki annak az értékét, ha nincs, akkor a class nevét használja és adja át az értékét az alábbi classKey változónak.
        String classKey = name;

        this.appendStartTag(stringBuilder, classKey);
        stringBuilder.append(System.lineSeparator());
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, clazz.getDeclaredFields());

        for (Field field : fields) {
            if(field.isAnnotationPresent(ZH1Element.class)) {
                field.setAccessible(true);
                String fieldName = field.getAnnotation(ZH1Element.class).text();
                //TODO Nézze meg, hogy a field annotávla van-e a ZH1Element annotációval és csak akkor hajtsa végre a for ciklusban található kódot, ha van ilyen annotációja.
                //TODO Tegye elérhetővé az adott field-et, majd a ciklus végén állítsa vissza a hozzáférhetőség eredeti értékét.

                //TODO Ha a ZH1Element annotációnak van text nevű mezője, akkor szedje ki annak az értékét, ha nincs, akkor a field nevét használja és adja át az értékét az alábbi key változónak.
                //TODO A Food class price field esetén az annotáció text mezőjének a neve legyen "PRICE_FT".
                String key = "".equals(fieldName) ? field.getName() : fieldName;
                Object value = field.get(object);
                this.appendStartTag(stringBuilder, key);
                stringBuilder.append(value);
                this.appendEndTag(stringBuilder, key);
                field.setAccessible(false);
            }
        }
        this.appendEndTag(stringBuilder, classKey);
    }

    private void appendStartTag(StringBuilder stringBuilder, String key) {
        stringBuilder.append("<")
                .append(key)
                .append(">");
    }

    private void appendEndTag(StringBuilder stringBuilder, String key) {
        stringBuilder.append("</")
                .append(key)
                .append(">")
                .append(System.lineSeparator());
    }

}
