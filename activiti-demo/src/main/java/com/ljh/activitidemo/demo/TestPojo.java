package com.ljh.activitidemo.demo;

import java.util.Optional;

public class TestPojo {
    private static class Country {
        private Province province;

        public Optional<Province> getProvince() {
            return Optional.ofNullable(province);
        }

        public void setProvince(Province province) {
            this.province = province;
        }
    }

    private static class City {
    }

    private static class Province {
        private City city;

        public Optional<City> getCity() {
            return Optional.ofNullable(city);
        }

        public void setCity(City city) {
            this.city = city;
        }
    }

    public static void main(String[] args) {
        Country country = null;
        City city = Optional.ofNullable(country).flatMap(Country::getProvince).flatMap(Province::getCity).orElse(new City());
        System.out.println(city);
//        System.out.println(country.getProvince().getCity());
    }
}