package mx.com.luisalduucin.soapweather;


import rx.Observable;

public interface Converter<T extends Units> {
    T getAvailableUnits();

    Converter setFromUnits(String unit);

    Converter setToUnits(String unit);

    Converter setToConvertValue(String value);

    Observable<Double> convert();
}
