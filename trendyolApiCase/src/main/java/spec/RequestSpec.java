package spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    RequestSpecification requestSpecification;
    private String baseUrl = "https://api.spotify.com";
    private String token = "BQAEnPLok5o_kkLDaBoLiGC1VedRIedW2MUaeiBjLLUSWtZG3O-34OiYRWNQ8Nd8vrriSNOw-uQFgR4E1poT4b414xxhTQLR0-GMhhTfySrzKX4WvSobjzFGRF62zeHVw-HBh-Z97t_v_mY7dQ1u0aViU0LZ3PtB-dsd_XWggFEqiHlUpAECgSZYwOq8EoZJgYNtpH-2_hi2eOkbu4IpQIpgbAoNvGoNFci39ZeR1iDd6o_TqrQT-ykP9PubmcBNjvbYyEIgrlNpA6Jhlu8FleHTNw";
    public RequestSpec(){
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("Authorization","Bearer " +token)
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }
    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

}
