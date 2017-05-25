package mx.com.luisalduucin.soapweather;


import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

import static android.R.attr.password;

public class SOAPClient {

    private static final String WSDL_TARGET_NAMESPACE = "http://www.webserviceX.NET/";

    private Map<String, String> params;

    public SOAPClient() {
        params = new HashMap<>();
    }

    public void addRequestParameter(String paramName, String paramValue) {
        params.put(paramName, paramValue);
    }

    private String getWsdlTargetNamespace() {
        return WSDL_TARGET_NAMESPACE;
    }

    public Observable<Double> execute(final String operationName, final String soapAddress) {

        return Observable.create(new Observable.OnSubscribe<Double>() {
            @Override
            public void call(Subscriber<? super Double> subscriber) {
                SoapObject request = new SoapObject(getWsdlTargetNamespace(), operationName);

                for (String paramName : params.keySet()) {
                    request.addProperty(paramName, params.get(paramName));
                }

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
                envelope.dotNet = true;
                envelope.implicitTypes = true;
                envelope.setAddAdornments(false);

                envelope.setOutputSoapObject(request);

                HttpTransportSE httpTransportSE = new HttpTransportSE(soapAddress);
                httpTransportSE.debug = true;

                String soapAction = getWsdlTargetNamespace() + operationName;

                try {
                    httpTransportSE.call(soapAction, envelope);
                } catch (IOException | XmlPullParserException e) {
                    e.printStackTrace();
                }

                Log.d("SOAPConverter--request", httpTransportSE.requestDump);
                Log.d("SOAPConverter--response", httpTransportSE.responseDump);

                SoapPrimitive soapResponse = null;
                try {
                    soapResponse = (SoapPrimitive) envelope.getResponse();
                } catch (SoapFault soapFault) {
                    soapFault.printStackTrace();
                }

                Double response = Double.valueOf(soapResponse != null ? soapResponse.toString() : null);

                subscriber.onNext(response);
                subscriber.onCompleted();
            }
        });

    }
}
