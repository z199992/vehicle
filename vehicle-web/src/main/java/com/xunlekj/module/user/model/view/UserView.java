package com.xunlekj.module.user.model.view;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserView {
    private String id;
    @Schema(description = "账号")
    private String account;
    @Schema(description = "昵称")
    private String nickName;
    @Schema(description = "创建时间")
    private String createTime;
}
