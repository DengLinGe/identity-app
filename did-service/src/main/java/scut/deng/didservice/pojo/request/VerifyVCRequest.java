package scut.deng.didservice.pojo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VerifyVCRequest {
    @NotNull
    public String did;
    @NotNull
    public String uuid;
    @NotNull
    public String encodeMsg;
}
