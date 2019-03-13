package com.yltang.tools.datasource;

import com.yltang.tools.datasource.annotation.DataEntity;
import com.yltang.tools.datasource.annotation.DataOrigin;
import com.yltang.tools.datasource.entity.DataOriginType;

@DataEntity(name ="用户信息")
@DataOrigin({DataOriginType.EXCEL})
public class PrUser {

}
