package mx.com.luisalduucin.soapweather;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class SOAPConverter implements Converter {

    private static final String WSDL_TARGET_NAMESPACE = "http://www.webserviceX.NET/";

    private static final String SOAP_ADDRESS = "http://www.webservicex.net/ConvertWeight.asmx";

    protected Map<String, String> params;

    public abstract SOAPConverter setToConvertValue(String value);

    public SOAPConverter() {
        params = new HashMap<>();
    }

    public SOAPConverter setFromUnits(String units) {
        params.put("FromUnit", units);
        return this;
    }

    public SOAPConverter setToUnits(String units) {
        params.put("ToUnit", units);
        return this;
    }

    protected abstract String getOperationName();

    public String getWsdlTargetNamespace() {
        return WSDL_TARGET_NAMESPACE;
    }

    public String getSoapAddress() {
        return SOAP_ADDRESS;
    }

    public double execute() throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(getWsdlTargetNamespace(), getOperationName());

        for (String paramName : params.keySet()) {
            request.addProperty(paramName, params.get(paramName));
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransportSE = new HttpTransportSE(getSoapAddress());
        httpTransportSE.debug = true;

        String soapAction = getWsdlTargetNamespace() + getOperationName();

        httpTransportSE.call(soapAction, envelope);

        Log.d("SOAPConverter--request", httpTransportSE.requestDump);
        Log.d("SOAPConverter--response", httpTransportSE.responseDump);

        return Double.parseDouble(envelope.getResponse().toString());
    }

}
