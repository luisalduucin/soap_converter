package mx.com.luisalduucin.soapweather.converters;


import java.util.ArrayList;
import java.util.List;

import mx.com.luisalduucin.soapweather.Units;

public class TemperatureConverter extends SOAPConverter<TemperatureConverter.TemperatureUnits> {

    public final class TemperatureUnits implements Units {

        private List<String> values = new ArrayList<>();

        static final String CELSIUS = "degreeCelsius";
        static final String FAHRENHEIT = "degreeFahrenheit";
        static final String RANKINE = "degreeRankine";
        static final String REAUMUR = "degreeReaumur";
        static final String KELVIN = "kelvin";

        @Override
        public List<String> getValues() {
            values.add(CELSIUS);
            values.add(FAHRENHEIT);
            values.add(RANKINE);
            values.add(REAUMUR);
            values.add(KELVIN);
            return values;
        }

        @Override
        public void addUnit(String unit) {
            values.add(unit);
        }

        @Override
        public void removeUnit(String unit) {
            values.remove(unit);
        }
    }

    private TemperatureUnits units = new TemperatureUnits();

    @Override
    TemperatureUnits getUnits() {
        return units;
    }

    @Override
    protected String getOperationName() {
        return "ConvertTemp";
    }

    @Override
    String getValueName() {
        return "Temperature";
    }

    @Override
    String getSoapAddress() {
        return "http://www.webservicex.net/ConvertTemperature.asmx";
    }
}
