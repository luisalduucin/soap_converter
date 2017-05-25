package mx.com.luisalduucin.soapweather;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ExampleUnitTest {

    @BeforeClass
    public static void setup() throws IOException {
        ShadowLog.stream = System.out;
    }

    @Test
    public void weightConverterKilogramsToGrams() throws IOException, XmlPullParserException {

        Converter weightConverter = new UnitConverter().weight();
        List<String> availableUnits = weightConverter.getAvailableUnits().getValues();

        TestSubscriber<Double> testSubscriber = new TestSubscriber<>();
        int kilogramsIndex = 5;
        int gramsIndex = 1;
        weightConverter
                .setFromUnits(availableUnits.get(kilogramsIndex))
                .setToUnits(availableUnits.get(gramsIndex))
                .setToConvertValue("23")
                .convert()
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<Double> convertedValue = testSubscriber.getOnNextEvents();

        double expected = 23000.0;
        int delta = 0;
        assertEquals(expected, convertedValue.get(0), delta);
    }

    @Test
    public void temperatureConverterCelsiusToFahrenheit() throws IOException, XmlPullParserException {

        Converter temperatureConverter = new UnitConverter().temperature();
        List<String> availableUnits = temperatureConverter.getAvailableUnits().getValues();

        TestSubscriber<Double> testSubscriber = new TestSubscriber<>();
        int celciusIndex = 0;
        int farenheitIndex = 1;
        temperatureConverter
                .setFromUnits(availableUnits.get(celciusIndex))
                .setToUnits(availableUnits.get(farenheitIndex))
                .setToConvertValue("23")
                .convert()
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<Double> convertedValue = testSubscriber.getOnNextEvents();

        double expected = 73.4;
        int delta = 0;
        assertEquals(expected, convertedValue.get(0), delta);
    }

    @Test
    public void speedConverterCelsiusToFahrenheit() throws IOException, XmlPullParserException {

        Converter speedConverter = new UnitConverter().speed();
        List<String> availableUnits = speedConverter.getAvailableUnits().getValues();

        TestSubscriber<Double> testSubscriber = new TestSubscriber<>();
        int kilometersPerHourIndex = 5;
        int knotsIndex = 7;
        speedConverter
                .setFromUnits(availableUnits.get(kilometersPerHourIndex))
                .setToUnits(availableUnits.get(knotsIndex))
                .setToConvertValue("23")
                .convert()
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<Double> convertedValue = testSubscriber.getOnNextEvents();


        double expected = 12.41900647948164;
        int delta = 0;
        assertEquals(expected, convertedValue.get(0), delta);
    }

}