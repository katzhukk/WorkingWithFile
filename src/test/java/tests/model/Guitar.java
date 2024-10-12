package tests.model;

import java.util.List;

public class Guitar {
    private List<Guitars> guitars;

    public List<Guitars> getGuitars() {
        return guitars;
    }

    public static class Guitars{
        private String brand;
        private String model;
        private String type;
        private String material;
        private GuitarStrings guitarStrings;

        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        public String getType() {
            return type;
        }

        public String getMaterial() {
            return material;
        }

        public GuitarStrings getGuitarStrings() {
            return guitarStrings;
        }
    }
}
