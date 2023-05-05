package scut.deng.didservice.pojo;

import lombok.Data;

@Data
public class PublicKey {

    private String id;

    private String type;

    private String keyString; // base64格式


}
