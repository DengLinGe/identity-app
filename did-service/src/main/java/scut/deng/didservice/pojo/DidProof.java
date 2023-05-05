package scut.deng.didservice.pojo;

import lombok.Data;

@Data
public class DidProof {
    private String type;
    private String creator;
    private String signatureValue;
}
