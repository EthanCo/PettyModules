package com.ethanco.locatecitysample.response;

/**
 * @Description 聚合数据 定位城市
 * Created by EthanCo on 2016/10/10.
 */

public class City2Response {
    /**
     * address : CN|吉林|长春|None|CERNET|1|None
     * content : {"address":"吉林省长春市","address_detail":{"city":"长春市","city_code":53,"district":"","province":"吉林省","street":"","street_number":""},"point":{"x":"125.31364243","y":"43.89833761"}}
     * status : 0
     */

    private String address;
    /**
     * address : 吉林省长春市
     * address_detail : {"city":"长春市","city_code":53,"district":"","province":"吉林省","street":"","street_number":""}
     * point : {"x":"125.31364243","y":"43.89833761"}
     */

    private ContentBean content;
    private int status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ContentBean {
        private String address;
        /**
         * city : 长春市
         * city_code : 53
         * district :
         * province : 吉林省
         * street :
         * street_number :
         */

        private AddressDetailBean address_detail;
        /**
         * x : 125.31364243
         * y : 43.89833761
         */

        private PointBean point;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public AddressDetailBean getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(AddressDetailBean address_detail) {
            this.address_detail = address_detail;
        }

        public PointBean getPoint() {
            return point;
        }

        public void setPoint(PointBean point) {
            this.point = point;
        }

        public static class AddressDetailBean {
            private String city;
            private int city_code;
            private String district;
            private String province;
            private String street;
            private String street_number;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getCity_code() {
                return city_code;
            }

            public void setCity_code(int city_code) {
                this.city_code = city_code;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getStreet_number() {
                return street_number;
            }

            public void setStreet_number(String street_number) {
                this.street_number = street_number;
            }
        }

        public static class PointBean {
            private String x;
            private String y;

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }
        }
    }
}
