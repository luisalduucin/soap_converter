package mx.com.luisalduucin.soapweather;


import mx.com.luisalduucin.soapweather.converters.WeightConverter;

public class UnitConverter {

    public WeightConverter getWeightConverter() {
        return new WeightConverter();
    }

}
