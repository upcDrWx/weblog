package com.wx.weblog.admin.controller;

import com.wx.weblog.admin.service.AdminFileService;
import com.wx.weblog.common.aspect.ApiOperationLog;
import com.wx.weblog.common.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: wx
 * @Date: 2024/4/23 13:10
 * @Describe:
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 文件模块")
public class AdminFileController {
    @Autowired
    private AdminFileService adminFileService;

    @PostMapping("/file/upload")
    @ApiOperation(value = "文件上传")
    @ApiOperationLog(description = "文件上传")
    public Response uploadFile(@RequestParam MultipartFile file) {
        return adminFileService.uploadFile(file);
    }
}
