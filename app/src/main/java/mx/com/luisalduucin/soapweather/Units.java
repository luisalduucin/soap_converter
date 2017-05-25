package mx.com.luisalduucin.soapweather;


import java.util.List;

public interface Units {
    List<String> getValues();
    void addUnit(String unit);
    void removeUnit(String unit);
}
