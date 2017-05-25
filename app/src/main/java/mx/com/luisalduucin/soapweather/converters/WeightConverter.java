package mx.com.luisalduucin.soapweather.converters;

import java.util.ArrayList;
import java.util.List;

import mx.com.luisalduucin.soapweather.Units;

public class WeightConverter extends SOAPConverter<WeightConverter.WeightUnits> {

    public final class WeightUnits implements Units {

        private List<String> values = new ArrayList<>();

        static final String GRAINS = "Grains";
        static final String GRAMS = "Grams";
        static final String PENNYWEIGHT = "Pennyweight";
        static final String OUNCESTROYAPOTH = "OuncesTroyApoth";
        static final String POUNDALS = "Poundals";
        static final String KILOGRAMS = "Kilograms";
        static final String STONES = "Stones";

        @Override
        public List<String> getValues() {
            values.add(GRAINS);
            values.add(GRAMS);
            values.add(PENNYWEIGHT);
            values.add(OUNCESTROYAPOTH);
            values.add(POUNDALS);
            values.add(KILOGRAMS);
            values.add(STONES);
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

    private WeightUnits units = new WeightUnits();

    @Override
    WeightUnits getUnits() {
        return units;
    }

    @Override
    String getOperationName() {
        return "ConvertWeight";
    }

    @Override
    String getValueName() {
        return "Weight";
    }

    @Override
    String getSoapAddress() {
        return "http://www.webservicex.net/ConvertWeight.asmx";
    }
}
