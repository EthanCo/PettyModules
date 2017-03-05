package com.ethanco.simpleframe.bean;

import java.util.List;

/**
 * Created by EthanCo on 2016/4/4.
 */
public class Weather {
    /**
     * reason : 查询成功
     * result : {"data":{"realtime":{"city_code":"101210701","city_name":"温州","date":"2014-10-15","time":"09:00:00","week":3,"moon":"九月廿二","dataUptime":1413337811,"weather":{"temperature":"19","humidity":"54","info":"雾","img":"18"},"wind":{"direct":"北风","power":"1级","offset":null,"windspeed":null}},"life":{"date":"2014-10-15","info":{"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}},"weather":[{"date":"2014-10-15","info":{"day":["0","晴","24","东北风","3-4 级"],"night":["0","晴","13","东北风","3-4 级"]},"week":"三","nongli":"九月廿二"},{"date":"2014-10-16","info":{"dawn":["0","晴","13","东北风","3-4 级"],"day":["0","晴","25","东北风","3-4 级"],"night":["1","多云","15","东北风","3-4 级"]},"week":"四","nongli":"九月廿三"},{"date":"2014-10-17","info":{"dawn":["1","多云","15","东北风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["1","多云","16","东北风","3-4 级"]},"week":"五","nongli":"九月廿四"},{"date":"2014-10-18","info":{"dawn":["1","多云","16","东北风","3-4 级"],"day":["1","多云","26","东风","3-4 级"],"night":["1","多云","18","东风","3-4 级"]},"week":"六","nongli":"九月廿五"},{"date":"2014-10-19","info":{"dawn":["1","多云","18","东风","3-4 级"],"day":["1","多云","27","东风","3-4 级"],"night":["1","多云","19","东南风","3-4 级"]},"week":"日","nongli":"九月廿六"},{"date":"2014-10-20","info":{"dawn":["1","多云","19","东南风","3-4 级"],"day":["1","多云","27","东南风","3-4 级"],"night":["2","阴","18","南风","3-4 级"]},"week":"一","nongli":"九月廿七"},{"date":"2014-10-21","info":{"dawn":["2","阴","18","南风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["2","阴","17","","微风"]},"week":"二","nongli":"九月廿八"}],"pm25":{"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"97","pm25":"72","pm10":"97","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"},"dateTime":"2014年10月15日09时","cityName":"温州"},"date":null,"isForeign":0}}
     * error_code : 0
     */

    private String reason;
    /**
     * data : {"realtime":{"city_code":"101210701","city_name":"温州","date":"2014-10-15","time":"09:00:00","week":3,"moon":"九月廿二","dataUptime":1413337811,"weather":{"temperature":"19","humidity":"54","info":"雾","img":"18"},"wind":{"direct":"北风","power":"1级","offset":null,"windspeed":null}},"life":{"date":"2014-10-15","info":{"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}},"weather":[{"date":"2014-10-15","info":{"day":["0","晴","24","东北风","3-4 级"],"night":["0","晴","13","东北风","3-4 级"]},"week":"三","nongli":"九月廿二"},{"date":"2014-10-16","info":{"dawn":["0","晴","13","东北风","3-4 级"],"day":["0","晴","25","东北风","3-4 级"],"night":["1","多云","15","东北风","3-4 级"]},"week":"四","nongli":"九月廿三"},{"date":"2014-10-17","info":{"dawn":["1","多云","15","东北风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["1","多云","16","东北风","3-4 级"]},"week":"五","nongli":"九月廿四"},{"date":"2014-10-18","info":{"dawn":["1","多云","16","东北风","3-4 级"],"day":["1","多云","26","东风","3-4 级"],"night":["1","多云","18","东风","3-4 级"]},"week":"六","nongli":"九月廿五"},{"date":"2014-10-19","info":{"dawn":["1","多云","18","东风","3-4 级"],"day":["1","多云","27","东风","3-4 级"],"night":["1","多云","19","东南风","3-4 级"]},"week":"日","nongli":"九月廿六"},{"date":"2014-10-20","info":{"dawn":["1","多云","19","东南风","3-4 级"],"day":["1","多云","27","东南风","3-4 级"],"night":["2","阴","18","南风","3-4 级"]},"week":"一","nongli":"九月廿七"},{"date":"2014-10-21","info":{"dawn":["2","阴","18","南风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["2","阴","17","","微风"]},"week":"二","nongli":"九月廿八"}],"pm25":{"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"97","pm25":"72","pm10":"97","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"},"dateTime":"2014年10月15日09时","cityName":"温州"},"date":null,"isForeign":0}
     */

    private ResultEntity result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultEntity {
        /**
         * realtime : {"city_code":"101210701","city_name":"温州","date":"2014-10-15","time":"09:00:00","week":3,"moon":"九月廿二","dataUptime":1413337811,"weather":{"temperature":"19","humidity":"54","info":"雾","img":"18"},"wind":{"direct":"北风","power":"1级","offset":null,"windspeed":null}}
         * life : {"date":"2014-10-15","info":{"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}}
         * weather : [{"date":"2014-10-15","info":{"day":["0","晴","24","东北风","3-4 级"],"night":["0","晴","13","东北风","3-4 级"]},"week":"三","nongli":"九月廿二"},{"date":"2014-10-16","info":{"dawn":["0","晴","13","东北风","3-4 级"],"day":["0","晴","25","东北风","3-4 级"],"night":["1","多云","15","东北风","3-4 级"]},"week":"四","nongli":"九月廿三"},{"date":"2014-10-17","info":{"dawn":["1","多云","15","东北风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["1","多云","16","东北风","3-4 级"]},"week":"五","nongli":"九月廿四"},{"date":"2014-10-18","info":{"dawn":["1","多云","16","东北风","3-4 级"],"day":["1","多云","26","东风","3-4 级"],"night":["1","多云","18","东风","3-4 级"]},"week":"六","nongli":"九月廿五"},{"date":"2014-10-19","info":{"dawn":["1","多云","18","东风","3-4 级"],"day":["1","多云","27","东风","3-4 级"],"night":["1","多云","19","东南风","3-4 级"]},"week":"日","nongli":"九月廿六"},{"date":"2014-10-20","info":{"dawn":["1","多云","19","东南风","3-4 级"],"day":["1","多云","27","东南风","3-4 级"],"night":["2","阴","18","南风","3-4 级"]},"week":"一","nongli":"九月廿七"},{"date":"2014-10-21","info":{"dawn":["2","阴","18","南风","3-4 级"],"day":["1","多云","26","东北风","3-4 级"],"night":["2","阴","17","","微风"]},"week":"二","nongli":"九月廿八"}]
         * pm25 : {"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"97","pm25":"72","pm10":"97","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"},"dateTime":"2014年10月15日09时","cityName":"温州"}
         * date : null
         * isForeign : 0
         */

        private DataEntity data;

        public DataEntity getData() {
            return data;
        }

        public void setData(DataEntity data) {
            this.data = data;
        }

        public static class DataEntity {
            /**
             * city_code : 101210701
             * city_name : 温州
             * date : 2014-10-15
             * time : 09:00:00
             * week : 3
             * moon : 九月廿二
             * dataUptime : 1413337811
             * weather : {"temperature":"19","humidity":"54","info":"雾","img":"18"}
             * wind : {"direct":"北风","power":"1级","offset":null,"windspeed":null}
             */

            private RealtimeEntity realtime;
            /**
             * date : 2014-10-15
             * info : {"chuanyi":["较舒适","建议着薄外套或牛仔衫裤等服装。年老体弱者宜着夹克衫、薄毛衣等。昼夜温差较大，注意适当增减衣服。"],"ganmao":["较易发","昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"wuran":["良","气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"],"ziwaixian":["中等","属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"]}
             */

            private LifeEntity life;
            /**
             * key : Wenzhou
             * show_desc : 0
             * pm25 : {"curPm":"97","pm25":"72","pm10":"97","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"}
             * dateTime : 2014年10月15日09时
             * cityName : 温州
             */

            private Pm25Entity pm25;
            private Object date;
            private int isForeign;
            /**
             * date : 2014-10-15
             * info : {"day":["0","晴","24","东北风","3-4 级"],"night":["0","晴","13","东北风","3-4 级"]}
             * week : 三
             * nongli : 九月廿二
             */

            private List<WeatherEntity> weather;

            public RealtimeEntity getRealtime() {
                return realtime;
            }

            public void setRealtime(RealtimeEntity realtime) {
                this.realtime = realtime;
            }

            public LifeEntity getLife() {
                return life;
            }

            public void setLife(LifeEntity life) {
                this.life = life;
            }

            public Pm25Entity getPm25() {
                return pm25;
            }

            public void setPm25(Pm25Entity pm25) {
                this.pm25 = pm25;
            }

            public Object getDate() {
                return date;
            }

            public void setDate(Object date) {
                this.date = date;
            }

            public int getIsForeign() {
                return isForeign;
            }

            public void setIsForeign(int isForeign) {
                this.isForeign = isForeign;
            }

            public List<WeatherEntity> getWeather() {
                return weather;
            }

            public void setWeather(List<WeatherEntity> weather) {
                this.weather = weather;
            }

            public static class RealtimeEntity {
                private String city_code;
                private String city_name;
                private String date;
                private String time;
                private int week;
                private String moon;
                private int dataUptime;
                /**
                 * temperature : 19
                 * humidity : 54
                 * info : 雾
                 * img : 18
                 */

                private WeatherEntity weather;
                /**
                 * direct : 北风
                 * power : 1级
                 * offset : null
                 * windspeed : null
                 */

                private WindEntity wind;

                public String getCity_code() {
                    return city_code;
                }

                public void setCity_code(String city_code) {
                    this.city_code = city_code;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public int getWeek() {
                    return week;
                }

                public void setWeek(int week) {
                    this.week = week;
                }

                public String getMoon() {
                    return moon;
                }

                public void setMoon(String moon) {
                    this.moon = moon;
                }

                public int getDataUptime() {
                    return dataUptime;
                }

                public void setDataUptime(int dataUptime) {
                    this.dataUptime = dataUptime;
                }

                public WeatherEntity getWeather() {
                    return weather;
                }

                public void setWeather(WeatherEntity weather) {
                    this.weather = weather;
                }

                public WindEntity getWind() {
                    return wind;
                }

                public void setWind(WindEntity wind) {
                    this.wind = wind;
                }

                public static class WeatherEntity {
                    private String temperature;
                    private String humidity;
                    private String info;
                    private String img;

                    public String getTemperature() {
                        return temperature;
                    }

                    public void setTemperature(String temperature) {
                        this.temperature = temperature;
                    }

                    public String getHumidity() {
                        return humidity;
                    }

                    public void setHumidity(String humidity) {
                        this.humidity = humidity;
                    }

                    public String getInfo() {
                        return info;
                    }

                    public void setInfo(String info) {
                        this.info = info;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }
                }

                public static class WindEntity {
                    private String direct;
                    private String power;
                    private Object offset;
                    private Object windspeed;

                    public String getDirect() {
                        return direct;
                    }

                    public void setDirect(String direct) {
                        this.direct = direct;
                    }

                    public String getPower() {
                        return power;
                    }

                    public void setPower(String power) {
                        this.power = power;
                    }

                    public Object getOffset() {
                        return offset;
                    }

                    public void setOffset(Object offset) {
                        this.offset = offset;
                    }

                    public Object getWindspeed() {
                        return windspeed;
                    }

                    public void setWindspeed(Object windspeed) {
                        this.windspeed = windspeed;
                    }
                }
            }

            public static class LifeEntity {
                private String date;
                private InfoEntity info;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoEntity getInfo() {
                    return info;
                }

                public void setInfo(InfoEntity info) {
                    this.info = info;
                }

                public static class InfoEntity {
                    private List<String> chuanyi;
                    private List<String> ganmao;
                    private List<String> kongtiao;
                    private List<String> wuran;
                    private List<String> xiche;
                    private List<String> yundong;
                    private List<String> ziwaixian;

                    public List<String> getChuanyi() {
                        return chuanyi;
                    }

                    public void setChuanyi(List<String> chuanyi) {
                        this.chuanyi = chuanyi;
                    }

                    public List<String> getGanmao() {
                        return ganmao;
                    }

                    public void setGanmao(List<String> ganmao) {
                        this.ganmao = ganmao;
                    }

                    public List<String> getKongtiao() {
                        return kongtiao;
                    }

                    public void setKongtiao(List<String> kongtiao) {
                        this.kongtiao = kongtiao;
                    }

                    public List<String> getWuran() {
                        return wuran;
                    }

                    public void setWuran(List<String> wuran) {
                        this.wuran = wuran;
                    }

                    public List<String> getXiche() {
                        return xiche;
                    }

                    public void setXiche(List<String> xiche) {
                        this.xiche = xiche;
                    }

                    public List<String> getYundong() {
                        return yundong;
                    }

                    public void setYundong(List<String> yundong) {
                        this.yundong = yundong;
                    }

                    public List<String> getZiwaixian() {
                        return ziwaixian;
                    }

                    public void setZiwaixian(List<String> ziwaixian) {
                        this.ziwaixian = ziwaixian;
                    }
                }
            }

            public static class Pm25Entity {
                private String key;
                private int show_desc;
                /**
                 * curPm : 97
                 * pm25 : 72
                 * pm10 : 97
                 * level : 2
                 * quality : 良
                 * des : 可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。
                 */

                private Pm25EntityBean pm25;
                private String dateTime;
                private String cityName;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public int getShow_desc() {
                    return show_desc;
                }

                public void setShow_desc(int show_desc) {
                    this.show_desc = show_desc;
                }

                public Pm25EntityBean getPm25() {
                    return pm25;
                }

                public void setPm25(Pm25EntityBean pm25) {
                    this.pm25 = pm25;
                }

                public String getDateTime() {
                    return dateTime;
                }

                public void setDateTime(String dateTime) {
                    this.dateTime = dateTime;
                }

                public String getCityName() {
                    return cityName;
                }

                public void setCityName(String cityName) {
                    this.cityName = cityName;
                }

                public static class Pm25EntityBean {
                    private String curPm;
                    private String pm25;
                    private String pm10;
                    private int level;
                    private String quality;
                    private String des;

                    public String getCurPm() {
                        return curPm;
                    }

                    public void setCurPm(String curPm) {
                        this.curPm = curPm;
                    }

                    public String getPm25() {
                        return pm25;
                    }

                    public void setPm25(String pm25) {
                        this.pm25 = pm25;
                    }

                    public String getPm10() {
                        return pm10;
                    }

                    public void setPm10(String pm10) {
                        this.pm10 = pm10;
                    }

                    public int getLevel() {
                        return level;
                    }

                    public void setLevel(int level) {
                        this.level = level;
                    }

                    public String getQuality() {
                        return quality;
                    }

                    public void setQuality(String quality) {
                        this.quality = quality;
                    }

                    public String getDes() {
                        return des;
                    }

                    public void setDes(String des) {
                        this.des = des;
                    }
                }
            }

            public static class WeatherEntity {
                private String date;
                private InfoEntity info;
                private String week;
                private String nongli;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoEntity getInfo() {
                    return info;
                }

                public void setInfo(InfoEntity info) {
                    this.info = info;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getNongli() {
                    return nongli;
                }

                public void setNongli(String nongli) {
                    this.nongli = nongli;
                }

                public static class InfoEntity {
                    private List<String> day;
                    private List<String> night;

                    public List<String> getDay() {
                        return day;
                    }

                    public void setDay(List<String> day) {
                        this.day = day;
                    }

                    public List<String> getNight() {
                        return night;
                    }

                    public void setNight(List<String> night) {
                        this.night = night;
                    }
                }
            }
        }
    }
}
