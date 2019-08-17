package com.cskaoyan.mall.controller.popularize;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.popularize.AdStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("admin/storage")
public class AdStorageController {

    @Autowired
    AdStorageService adStorageService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public BaseResponseModel<Storage> create(MultipartFile[] file,String filename,Storage storage) {
        File upload = new File("G://temp/file/picture", file[0].getOriginalFilename());
        String url = "http://localhost:/admin/storage/static/pic";
        try {
            file[0].transferTo(upload);
            storage.setType(file[0].getContentType());
            int size =(int) file[0].getSize();
            storage.setSize(size);
            storage.setName(filename);
            storage.setAddTime(new Date());
            storage.setUpdateTime(new Date());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
