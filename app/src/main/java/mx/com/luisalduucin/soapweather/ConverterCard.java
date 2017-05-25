package mx.com.luisalduucin.soapweather;


import android.content.Context;

import java.util.List;

class ConverterCard {
    private String title;
    private Context context;
    private Converter converter;

    ConverterCard(String title, Context context, Converter converter) {
        this.title = title;
        this.context = context;
        this.converter = converter;
    }

    public List<String> getUnits() {
        return converter.getAvailableUnits().getValues();
    }

    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }
}
