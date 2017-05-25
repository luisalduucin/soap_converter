package mx.com.luisalduucin.soapweather.converters;

import mx.com.luisalduucin.soapweather.Converter;
import mx.com.luisalduucin.soapweather.SOAPClient;
import mx.com.luisalduucin.soapweather.Units;
import rx.Observable;

abstract class SOAPConverter<T extends Units> implements Converter<T> {

    private SOAPClient soapClient = new SOAPClient();

    public SOAPClient getSOAPClient() {
        return soapClient;
    }

    abstract T getUnits();

    abstract String getOperationName();

    abstract String getValueName();

    abstract String getSoapAddress();

    @Override
    public T getAvailableUnits() {
        return getUnits();
    }

    @Override
    public SOAPConverter setFromUnits(String unit) {
        getSOAPClient().addRequestParameter("FromUnit", unit);
        return this;
    }

    @Override
    public SOAPConverter setToUnits(String unit) {
        getSOAPClient().addRequestParameter("ToUnit", unit);
        return this;
    }

    @Override
    public SOAPConverter setToConvertValue(String value) {
        getSOAPClient().addRequestParameter(getValueName(), value);
        return this;
    }

    @Override
    public Observable<Double> convert() {

        return getSOAPClient().execute(getOperationName(), getSoapAddress());

    }

}
