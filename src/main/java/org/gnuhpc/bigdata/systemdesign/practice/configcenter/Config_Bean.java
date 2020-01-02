package org.gnuhpc.bigdata.systemdesign.practice.configcenter;

import lombok.Data;

@Data
public class Config_Bean {
    private Long config_uuid;   // 配置唯一ID
    private String change_type;	// 配置变动的类型：add-新增配置	update-更新配置	delete-删除配置
    private String download_url;// 如果是配置add或update，接收到事件的各客户端从该download_url下载新配置
    private String change_remark;// 本次配置变动的备注信息
}
