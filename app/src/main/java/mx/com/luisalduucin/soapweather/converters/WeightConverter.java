package mx.com.luisalduucin.soapweather.converters;

import java.util.Arrays;
import java.util.List;

import mx.com.luisalduucin.soapweather.SOAPConverter;

public class WeightConverter extends SOAPConverter {

    public enum Units {

        Grains("Grains"),
        Scruples("Scruples"),
        Carats("Carats"),
        Grams("Grams"),
        Pennyweight("Pennyweight"),
        DramAvoir("DramAvoir"),
        DramApoth("DramApoth"),
        OuncesAvoir("OuncesAvoir"),
        OuncesTroyApoth("OuncesTroyApoth"),
        Poundals("Poundals"),
        PoundsTroy("PoundsTroy"),
        PoundsAvoir("PoundsAvoir"),
        Kilograms("Kilograms"),
        Stones("Stones"),
        QuarterUS("QuarterUS"),
        Slugs("Slugs"),
        weight100UScwt("weight100UScwt"),
        ShortTons("ShortTons"),
        MetricTonsTonne("MetricTonsTonne"),
        LongTons("LongTons");

        private final String unit;

        Units(String unit) {
            this.unit = unit;
        }
    }

    @Override
    public SOAPConverter setToConvertValue(String value) {
        params.put("Weight", value);
        return this;
    }

    @Override
    protected String getOperationName() {
        return "ConvertWeight";
    }

    @Override
    public List<Units> getAvailableUnits() {
        return Arrays.asList(Units.values());
    }
}
