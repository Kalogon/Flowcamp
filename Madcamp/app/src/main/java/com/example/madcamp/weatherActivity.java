package com.example.madcamp;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class weatherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude",1.0);
        double longitude = intent.getDoubleExtra("longitude",1.0);

        Geocoder t_Geocoder = new Geocoder(this);
        // for getFromLocation()
        // getFromLocation()함수를 이용하여 GeoPoint값을 지역 값으로 가져오는데 필요하다.
        // 오버로딩된 생성자의 두 번째 인자를 사용하여 언어 선택 가능하다(Locale).
        // location value for transform
        // 변환하고자 하는 GeoPoint 값이다.
        // http://www.mygeoposition.com/ 에서 값을 얻을 수 있다.


        GeoToWeather gtw = new GeoToWeather(t_Geocoder, latitude,longitude);
        // for getWeather()


        Weather t_Weather = gtw.getWeather();
        // GeoPoint를 GeoCoder로 Reverse Geocoding한다.
        // 해당 지역의 날씨 정보를 Google API를 이용하여 Parsing한 후 Weather형으로 반환한다.



        /*String tStr = "날씨 = " + t_Weather.m_sCurrentState
                + "\n화씨 = " + t_Weather.m_nTempF
                + "\n섭씨 = " + t_Weather.m_nTempC
                + "\n지역 = " + t_Weather.m_sRegion
                + "\n습도 = " + t_Weather.m_sHumidity
                + "\n바람 = " + t_Weather.m_sWindCondition;


        TextView t_textview = findViewById(R.id.textView_w1);
        t_textview.setText(tStr);
        t_textview.setTextSize(30);

        setContentView(t_textview);*/

    }

}








/*public class weatherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude",1.0);
        double longitude = intent.getDoubleExtra("longitude",1.0);

        TextView latitude_t = (TextView) findViewById(R.id.textView_w1);
        TextView longitude_t = (TextView) findViewById(R.id.textView_w2);

        String tmp = Double.toString(latitude);
        String tmp2 = Double.toString(longitude);
        latitude_t.setText(tmp);
        longitude_t.setText(tmp2);

        String Response = "";
        String[] ResponseData ;
        String end = "";

        weatherActivity WeatherAPI = new weatherActivity();

        //fn_time 함수를 사용하여 현재시간 받아오기
        String timedata = WeatherAPI.fn_time();

        //현재시간을 활용하여 기상청 시간데이터 형식에 맞게 변환
        String HH = WeatherAPI.fn_timeChange(timedata);
        String YMD = timedata.substring(0, 8);

        //경도 //위도
        String nx = "60";
        String ny = "127";

        //연결하기 위한 파라미터들
        Response = WeatherAPI.fn_APIConnect(YMD, HH, nx, ny);
        TextView response_t = (TextView) findViewById(R.id.textView_w3);
        response_t.setText(Response);

    }
    public String fn_time()
    {

        SimpleDateFormat Format = new SimpleDateFormat("yyyyMMDD HHmmss");
        Date time = new Date();

        String timedata = Format.format(time);

        return timedata;

    }*/
    /*
    *******************************************************************************
     * 필요데이터 : 경도 및 위도, 현재날짜 및 시간, 발급받은 서비스키
     * 위도 경도에 따른 지역 날씨 예보 정보 가져옴
     * 3시간 단위로 날씨 데이터 업데이트(기상청)
     * @param baseDate
     * @param baseTime
     * @param nx
     * @param ny
     * @return
     * *******************************************************************************/
    /*public String fn_APIConnect(String baseDate, String baseTime, String nx, String ny)
    {
        String ConnectValue = "";
        BufferedReader br;
        String servicekey = "nzcYHrhVdEEjrZZPQ%2Bqh7Rt4WW%2FjqwoIY7NmqeRFxhOq5fnfMlKyHNa%2B1ZTTNJZEo7gwjh%2Bqs%2Bb6E%2Biuc%2FxSzg%3D%3D";

        String address = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?serviceKey="
                + servicekey + "&base_date=" + baseDate + "&base_time=" + baseTime + "&nx=" + nx + "&ny=" + ny + "&_type=json";
        try
        {
            URL url = new URL(address);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
            {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            else
            {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String ResData = br.readLine();

            if(ResData == null)
            {
                System.out.println("응답데이터 == NULL");
            }
            else
            {
                ConnectValue = fn_Jsonp(ResData);

            }
            br.close();
            conn.disconnect();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return ConnectValue;
    }*/
    /*
    ***************************************************************************
     * JSON 데이터 파싱함수
     * 데이터 추출
     * @param Data
     * **************************************************************************/
    /*public String fn_Jsonp(String Data)
    {

        JSONObject WeatherData;
        String VALUE = "";
        String date = "";
        String time = "";
        String DataValue = "";
        String info = "";

        try
        {
            JSONParser parsar = new JSONParser();
            JSONObject obj = (JSONObject) parsar.parse(Data);
            JSONObject response = (JSONObject) obj.get("response");
            JSONObject body = (JSONObject)response.get("body");
            JSONObject items = (JSONObject)body.get("items");
            JSONArray item = (JSONArray)items.get("item");

            for(int i = 0; i < item.size(); i++)
            {
                WeatherData = (JSONObject) item.get(i);

                date = WeatherData.get("baseDate").toString();
                time = WeatherData.get("baseTime").toString();
                DataValue = WeatherData.get("fcstValue").toString();
                info = WeatherData.get("category").toString();


                if(info.equals("POP")) {

                    info = "강수확률";
                    DataValue  = DataValue+" %";
                }
                if(info.equals("REH")) {

                    info = "습도";
                    DataValue = DataValue+" %";
                }
                if(info.equals("SKY")) {
                    info = "하늘상태";
                    if(DataValue.equals("1")) {
                        DataValue = "맑음";
                    }else if(DataValue.equals("2")) {
                        DataValue = "비";
                    }else if(DataValue.equals("3")) {
                        DataValue = "구름많음";
                    }else if(DataValue.equals("4")) {
                        DataValue = "흐림";
                    }
                }
                if(info.equals("UUU")) {
                    info = "동서성분풍속";
                    DataValue = DataValue+" m/s";
                }
                if(info.equals("VVV")) {
                    info = "남북성분풍속";
                    DataValue = DataValue+" m/s";
                }
                if(info.equals("T1H")) {
                    info = "기온";
                    DataValue = DataValue+"℃";
                }
                if(info.equals("R06")) {
                    info = "6시간강수량";
                    DataValue = DataValue + " mm";

                }
                if(info.equals("S06")) {
                    info = "6시간적설량";
                    DataValue = DataValue + " mm";
                }
                if(info.equals("PTY")){
                    info = "강수형태";
                    if(DataValue.equals("0")) {
                        DataValue = "없음";
                    }else if(DataValue.equals("1")) {
                        DataValue = "비";
                    }else if(DataValue.equals("2")) {
                        DataValue = "눈/비";
                    }else if(DataValue.equals("3")) {
                        DataValue = "눈";
                    }
                }
                if(info.equals("T3H")) {
                    info = "3시간기온";
                    DataValue = DataValue + " ℃";
                }
                if(info.equals("VEC")) {
                    info = "풍향";
                    DataValue = DataValue + " m/s";
                }


                VALUE += info + "," +  DataValue + "," + date + "," + time + ",";
            }

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return VALUE;
    }*/

    /*
    *********************************************************************
     * 현재시간을 가져와서 ex) 1000 형태로 만들어줌
     * 3시간 마다 업데이트 되기 때문에 각 시간에 따라 업데이트 시간으로 설정
     * @param timedata
     * @return
     * *********************************************************************/
    /*
    public String fn_timeChange(String timedata)
    {
        String hh = timedata.substring(9, 11);
        String baseTime = "";
        hh = hh + "00";

        // 현재 시간에 따라 데이터 시간 설정(3시간 마다 업데이트) //
        switch(hh) {

            case "0200":
            case "0300":
            case "0400":
                baseTime = "0200";
                break;
            case "0500":
            case "0600":
            case "0700":
                baseTime = "0500";
                break;
            case "0800":
            case "0900":
            case "1000":
                baseTime = "0800";
                break;
            case "1100":
            case "1200":
            case "1300":
                baseTime = "1100";
                break;
            case "1400":
            case "1500":
            case "1600":
                baseTime = "1400";
                break;
            case "1700":
            case "1800":
            case "1900":
                baseTime = "1700";
                break;
            case "2000":
            case "2100":
            case "2200":
                baseTime = "2000";
                break;
            default:
                baseTime = "2300";

        }
        return baseTime;
    }

}
*/