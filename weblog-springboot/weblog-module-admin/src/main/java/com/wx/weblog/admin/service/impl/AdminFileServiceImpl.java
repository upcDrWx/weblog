package com.wx.weblog.admin.service.impl;

import com.wx.weblog.admin.model.vo.file.UploadFileRspVO;
import com.wx.weblog.admin.service.AdminFileService;
import com.wx.weblog.admin.utils.MinioUtil;
import com.wx.weblog.common.enums.ResponseCodeEnum;
import com.wx.weblog.common.exception.BizException;
import com.wx.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: wx
 * @Date: 2024/4/23 13:08
 * @Describe:
 */
@Service
@Slf4j
public class AdminFileServiceImpl implements AdminFileService {

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Override
    public Response uploadFile(MultipartFile file) {
        try {
            // 上传文件
            String url = minioUtil.uploadFile(file);

            // 构建成功返参，将图片的访问链接返回
            return Response.success(UploadFileRspVO.builder().url(url).build());
        } catch (Exception e) {
            log.error("==> 上传文件至 Minio 错误: ", e);
            // 手动抛出业务异常，提示 “文件上传失败”
            throw new BizException(ResponseCodeEnum.FILE_UPLOAD_FAILED);
        }
    }
}
