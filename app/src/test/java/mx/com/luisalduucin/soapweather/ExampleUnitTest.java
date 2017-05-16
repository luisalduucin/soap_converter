package mx.com.luisalduucin.soapweather;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ksoap2.serialization.SoapPrimitive;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import mx.com.luisalduucin.soapweather.converters.WeightConverter;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ExampleUnitTest {

    @BeforeClass
    public static void setup() throws IOException {
        ShadowLog.stream = System.out;
    }

    @Test
    public void weightConverterGramsToGrams() throws IOException, XmlPullParserException {
        UnitConverter unitConverter = new UnitConverter();
        WeightConverter weightConverter = unitConverter.getWeightConverter();
        List<WeightConverter.Units> availableUnits = weightConverter.getAvailableUnits();

        double convertedValue = weightConverter.setFromUnits(availableUnits.get(12).toString())
                .setToUnits(availableUnits.get(3).toString())
                .setToConvertValue("23")
                .execute();

        assertEquals(23000.0, convertedValue, 0);
    }

}