package mx.com.luisalduucin.soapweather;


import mx.com.luisalduucin.soapweather.converters.SpeedConverter;
import mx.com.luisalduucin.soapweather.converters.TemperatureConverter;
import mx.com.luisalduucin.soapweather.converters.WeightConverter;

public class UnitConverter {

    public Converter weight() {
        return new WeightConverter();
    }

    public Converter temperature() {
        return new TemperatureConverter();
    }

    public Converter speed() {
        return new SpeedConverter();
    }

}
