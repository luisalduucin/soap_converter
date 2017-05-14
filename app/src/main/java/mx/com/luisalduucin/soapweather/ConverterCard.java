package mx.com.luisalduucin.soapweather;


class ConverterCard {
    private String title;
    private String value;
    private int imageResource;

    ConverterCard(String title, String value) {
        this.title = title;
        this.value = value;
    }

    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
