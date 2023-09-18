package com.azhu.apocalypse.media;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author azhu
 * @date 2021/10/29 9:57 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "media")
public class MediaPO {

    @TableId
    private Integer id;

    private String url;

    private String originalFilename;

    private String suffix;

    private String extra;

    private String md5;

    private LocalDateTime createdAt;

}
