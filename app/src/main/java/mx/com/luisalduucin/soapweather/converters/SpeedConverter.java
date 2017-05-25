package mx.com.luisalduucin.soapweather.converters;


import java.util.ArrayList;
import java.util.List;

import mx.com.luisalduucin.soapweather.Units;

public class SpeedConverter extends SOAPConverter {

    public final class SpeedUnits implements Units {

        private List<String> values = new ArrayList<>();

        static final String CENTIMETERS_PER_SECOND = "centimetersPersecond";
        static final String METERS_PER_SECOND = "metersPersecond";
        static final String FEET_PER_SECOND = "feetPersecond";
        static final String FEET_PER_MINUTE = "feetPerminute";
        static final String MILES_PER_HOUR = "milesPerhour";
        static final String KILOMETERS_PER_HOUR = "kilometersPerhour";
        static final String FURLONGS_PER_MIN = "furlongsPermin";
        static final String KNOTS = "knots";

        @Override
        public List<String> getValues() {
            values.add(CENTIMETERS_PER_SECOND);
            values.add(METERS_PER_SECOND);
            values.add(FEET_PER_SECOND);
            values.add(FEET_PER_MINUTE);
            values.add(MILES_PER_HOUR);
            values.add(KILOMETERS_PER_HOUR);
            values.add(FURLONGS_PER_MIN);
            values.add(KNOTS);
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

    private SpeedUnits units = new SpeedUnits();

    @Override
    Units getUnits() {
        return units;
    }

    @Override
    protected String getOperationName() {
        return "ConvertSpeed";
    }

    @Override
    String getValueName() {
        return "speed";
    }

    @Override
    String getSoapAddress() {
        return "http://www.webservicex.net/ConvertSpeed.asmx";
    }
}
