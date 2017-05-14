package mx.com.luisalduucin.soapweather;


import java.util.List;

interface Converter<T> {
    List<T> getAvailableUnits();
}
