package com.flyex.hive.getAverage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {

    public static void main(String[] args) {

        String line = "2020-10-01 23:59:40.949 [d1b9b2cd6a564f0bb35ddbd5dc06ee45][STATIC][REQUEST]SOURCE_IP:10.219.18.124;URL:/car-link-pt-bo/rest/api/public/carlink/v2/am/refreshToken;METHOD:POST;HEADER:[x-forwarded-for:10.219.18.6;x-real-ip:10.219.18.6;host:carlinkapi.ngi.sgmlink.com;content-length:305;archid:INFO3.X;client_secret:u2aojdA33zgaL50rk0AMG5qz88D5MgPxujrKhgM72g8sCnkqZ2O294OTZii;versioncode:BC_3.0.0(1L)_2.4.7_200709_Release;client_id:yx9QHmgq456fc7rsI2H2MkSBG7WnuM5g3376k28aQ9uY10660AL49p8SN9K;x-tingyun-id:p35OnrDoP8k;c=2;r=286016402;u=7be54569ec0954bc9b0ddf6625c13ee6f356918ee3a571f473ba622ef3d146201e9406a3579cb751d47e1da6e2f57465be294d39c1369525cdd551793f2d54ec::E48144F90C10CCA0;content-type:application/json;user-agent:okhttp/3.10.0;via:1.1 AQAAAJ1qOEE-;x-client-ip:10.219.9.6;x-global-transaction-id:d257db2e5f75fcec0c9a2b53;];Payload:{\"refresh_Token\":\"CkC31NkvIw5cDt09W1myIJJSfip7sStywA2yd6XEsJ9awlh8v0RKdGesGq1gNybIQpcR15oBQUTqWvGspJK1AtrNHUpIEtZHIodbWqAjQxj2spUuFvsnNuSQqVtsWwImhOLRtqpbHzW7kNJjPj422UYenrFo06sjbeRF0CelVf4IrUG0fne6cDoqAUreKsCiV99cDgbzpP99yyJLcGh6e53XjwA4XGrcW6QNNUB93aWUssxLJm70Adt2LITwsphg\",\"username\":\"OCLUBW014003116\"}[RESPONSE]Payload:{\"resultCode\":\"0000\",\"message\":\"success\",\"data\":{\"username\":\"OCLUBW014003116\",\"accessToken\":\"S2hHNER1S3NtY1A4VjZnNzdRVXc4bzlhZUpZZUQxZFFYNllxbzR4dHRmbDFQbUdMQlBRU0taWmk2TEFJcGdwYWZ1NE1Gdm1WNnJvZjJOc0tqQTBKQlY5UXhtcFVZaytFYnpqVjVxQ3loZ2ZzaStNRWR5L1I2eEs5d2FOSUJWbCsxMVRpSUdQSUJUQkxWQjBHM09jMk5RazBrOFAwbVE5OTRLQWNsZzNud2hld3VNR0g3QVViNU9qWU1yd2pxMkNrc01OQ2YwQW1hUFBZY2VWeWw5UG5iSnhUQ1YrTllvQW5YSXNMUXVNTVQwUzhyQi9ZME9BeGMrc1k3WVpqL3ZSeW14N21kRTZSNjJaQStvdFE5QTVCUHZHeUx6WlV5T2NhRWpIendUK1RVVk9FTjJrVWlnTExsSDRLSjdrZ21HQzhJWXJNVzZwa0tlcTdkaytDZE5xRlZDTXdmdzR6UnEzeUNieHhQR0toWm1sc1ZzcTVSUTFaTTB0NEw0a3pKRGNpckJTaXRwVmR0SU1LUkFjSmUyUm5UNnNlVTVYUWJEU1ViNEpxeUZSQVFNMVgybHdDWE9PNGlIcnpZeCswRHVEOWxrUWtjVmRiTmtSTCtGZnZBc1pKTHc9PQ==\"}}server returns timestamp:18;status:200";

        String reg = ".*2020.*";

        Matcher matcher = Pattern.compile(reg).matcher(line);

        System.out.println(matcher.group());

    }
}