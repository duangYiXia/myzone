package com.xxkj.myuploader.service;

import com.xxkj.myuploader.config.UploadConfig;
import com.xxkj.myuploader.dao.FileDao;
import com.xxkj.myuploader.model.File;
import com.xxkj.myuploader.utils.FileUtils;
import com.xxkj.myuploader.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * 文件上传服务
 */
@Service
public class FileService {
    @Autowired
    private FileDao fileDao;


    /**
     * 上传文件
     *
     * @param md5
     * @param file
     */
    public void upload(String name,
                       String md5,
                       MultipartFile file) throws IOException {
        String path = UploadConfig.path + FileUtils.generateFileName();
        FileUtils.write(path, file.getInputStream());
        fileDao.save(new File(name, md5, path, new Date()));
    }

    /**
     * 分块上传文件
     *
     * @param md5
     * @param size
     * @param chunks
     * @param chunk
     * @param file
     * @throws IOException
     */
    public void uploadWithBlock(String name,
                                String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                MultipartFile file) throws IOException {
        String fileName = UploadUtils.getFileName(md5, chunks);
        FileUtils.writeWithBlok(UploadConfig.path + fileName, size, file.getInputStream(), file.getSize(), chunks, chunk);
        UploadUtils.addChunk(md5, chunk);
        if (UploadUtils.isUploaded(md5)) {
            UploadUtils.removeKey(md5);
            fileDao.save(new File(name, md5, UploadConfig.path + fileName, new Date()));
        }
    }

    /**
     * 检查Md5判断文件是否已上传
     *
     * @param md5
     * @return
     */
    public boolean checkMd5(String md5) {
        File file = new File();
        file.setMd5(md5);
        return fileDao.getByFile(file) == null;
    }
}
