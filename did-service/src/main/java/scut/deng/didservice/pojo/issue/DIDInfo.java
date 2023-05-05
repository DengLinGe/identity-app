package scut.deng.didservice.pojo.issue;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class DIDInfo {

    @NotNull(message = "did不能为空")
    public String did;
    @NotNull(message = "website不能为空")
    public String website;
    @NotNull(message = "description不能为空")
    public String description;
    @NotNull(message = "endpoint不能为空")
    public String endpoint;
    @NotNull(message = "serviceType不能为空")
    public String serviceType;
    @NotNull(message = "requestData不能为空")
    public List<String> requestData;

}
