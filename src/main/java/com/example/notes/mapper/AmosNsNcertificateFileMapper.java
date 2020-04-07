package com.example.notes.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.notes.entity.AmosNsNcertificateFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 国籍单机-国籍证-附件 Mapper 接口
 * </p>
 *
 * @author
 * @since 2020-03-14 16:21:53
 */
public interface AmosNsNcertificateFileMapper extends BaseMapper<AmosNsNcertificateFile> {

    List<AmosNsNcertificateFile> NSNationality_certificateFirstFormJxdIns44List(Page page, @Param("query") Object query);
}
